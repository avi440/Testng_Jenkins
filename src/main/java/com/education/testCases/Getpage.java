package com.education.testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.core.StringContains.containsString;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import static com.education.testCases.ObjectFileReader.getELementFromFile; 
import static com.education.testCases.BaseClass.readConfig;


//import junit.framework.Assert;

public class Getpage extends BaseUI   {
	
	
	
	
	protected WebDriver webdriver;
	String pageName;
	private long start; 

	public Getpage(WebDriver driver, String pageName) {
		// TODO Auto-generated constructor stub
		 super(driver, pageName); 
		 setStart(System.currentTimeMillis());// find this 
		 this.webdriver = driver; 
		 this.pageName = pageName; 
	}

	public void captureScreenshot(WebDriver driver,String tname) throws IOException {
		 TakesScreenshot ts = (TakesScreenshot)driver;
	     File Source = ts.getScreenshotAs(OutputType.FILE);
	     File destination = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
//	     String filePath = destination.getAbsolutePath();
	     FileUtils.copyFile(Source, destination);
//	     return filePath;
	     System.out.println("Take screenshot: " + tname);
	  }
	
	protected WebElement element(String elementToken) throws NoSuchElementException, TimeoutException {
		hardWaitForlEBrowser(2); 
		return element(elementToken,""); 
	} 
	
			
	protected WebElement element(String elementToken, String replacement) throws NoSuchElementException, TimeoutException {
	    return element(elementToken, replacement, 0); 
			} 

	protected WebElement element(String elementToken, String replacement, int index)
			throws NoSuchElementException, TimeoutException { 
		WebElement elem = null; 
		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacement, index))); 
			} catch(StaleElementReferenceException ex1) {
				wait.hardWait(1);
				elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacement, index))); 
				logMessage("Find Element " + elementToken + " after catching Stale Element Exception"); 
				}
		   catch (NoSuchElementException excp) { 
			   fail("FAILED: Element " + elementToken + "' not found on the " + this.pageName + " !!!");
			  }
	 
		return elem; 

	} 


	

	protected By getLocator(String elementToken, String replacement) {
		return getLocator(elementToken, replacement, 0); 
	} 

	protected By getLocator(String elementToken, String replacement, int index) { 
		String[] locator = getELementFromFile(this.pageName, elementToken);
		if(index<0){ 
			logMessage("elementToken="+elementToken+"; replacement="+replacement+"; index="+index);
			logMessage("locator[1]="+locator[1]+"; locator[2]="+locator[2]); 
	} 
	 if(index>0 && replacement.length()==0)
		 locator[2] = locator[2].replace("(n)","("+index+")"); 
	 else 
		 locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement); 
	 if(index<0) logMessage("locator[2]="+locator[2]); 
	 return getBy(locator[1].trim(), locator[2].trim()); 
	}
	
	protected By  getLocator(String elementToken, String replacement1, String replacement2) {
		 String[] locator = getELementFromFile(this.pageName, elementToken); 
		 locator[2] = locator[2].replaceFirst("\\S\\{.+?\\}", replacement1); 
		 locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement2);
		 return getBy(locator[1].trim(), locator[2].trim()); 
	} 
	
	
	
	
	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)){ 
		case id: 
			return By.id(locatorValue); 
	    case xpath:
	    	return By.xpath(locatorValue); 
	    case css:
	    	return By.cssSelector(locatorValue); 
	    case name:
	    	return By.name(locatorValue); 
	    case classname:
	    	return By.className(locatorValue); 
	    case linkText: 
	    	return By.linkText(locatorValue); 
	    case portialLinkText: 
	    	return By.partialLinkText(locatorValue); 
	    default:
	    	return By.id(locatorValue); 
		}
	}
   	
	    	public void setStart(long start) { 
	    		this.start = start; 
	    	}


	    	protected boolean isElementDisplayed(String elementName) throws NoSuchElementException, TimeoutException { 
	    		wait.waitForElementToBeVisible(element(elementName));
	    		boolean result = element(elementName).isDisplayed(); 
	    		assertTrue(result, "ASSERTION FAILED: element '' "+ elementName +
	    				                                        "' is not displayed."); 
	    		logMessage("ASSERTION PASSED: element " + elementName + " is displayed.");
	    		return result; 
	    	}

}
