package com.education.testCases;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.Reporter;

import static com.education.testCases.ObjectFileReader.getPageTitleFromFile;
import static org.testng.Assert.assertTrue;
import static com.education.testCases.BaseClass.readConfig;


//import junit.framework.Assert;

public class BaseUI  {
	
	public String browser = readConfig.getBrowser();
	
	 WebDriver driver; 
	protected SeleniumWait wait; 
	private String pageName ;
	private int demoWaitSeconds = 1; 
//	ObjectFileReader object = new ObjectFileReader();

	public BaseUI(WebDriver driver, String pageName) {
		// TODO Auto-generated constructor stub
			PageFactory.initElements(driver, this); 
			this.driver = driver;
			this.pageName = pageName; 
			this.wait = new SeleniumWait(driver, Integer.parseInt(readConfig.getExplicitWait())); 
		}
	
		protected String getPageTitle() {
			return driver.getTitle(); 
		} 
		
		protected void logMessage(String message) {
			Reporter.log(message, true); 
		}
		
		protected void hover(WebElement element) {
			Actions hoverOver = new Actions(driver);
			hoverOver.moveToElement(element).build().perform(); 
		} 
	protected String hoverAndGetHTML(WebElement element) {
		String a = new String(); 
		Actions hoverOver = new Actions(driver); 
		hoverOver.moveToElement(element).build().perform(); 
		a = element.getText();
		return a; 
	} 
	public void hoverAndClick(WebElement element) {
		Actions hoverClick = new Actions(driver); 
		hoverClick.moveToElement(element).click().build().perform(); 
	} 
	
	
	    
	    public void hardWaitForlEBrowser(int seconds) {
	    	if (readConfig.getBrowser().equalsIgnoreCase( "IE")||
	    		readConfig.getBrowser().equalsIgnoreCase("ie")||
	    		readConfig.getBrowser().equalsIgnoreCase("internetexplorer")) { 
	    		wait.hardWait(seconds); 
	    } 
	    } 
	    
	    protected void selectProvidedTextFromDropDown(WebElement e1, String text) {
			try { wait.waitForElementToBeVisible(e1);
			scrollDown(e1); 
			Select sel = new Select(e1); 
			sel.selectByVisibleText(text); 
			hardWaitForDemo(); 
			} catch(StaleElementReferenceException exl){ 
				wait.hardWait(2);
				wait.waitForElementToBeVisible(e1); 
				scrollDown(e1); Select sel = new Select(e1); 
				sel.selectByVisibleText(text); 
				logMessage("Selected Element " + e1 + 
						" after catching Stale Element Exception"); 
			}
		}
	    
	    protected void scrollDown(WebElement element) { 
			((JavascriptExecutor)driver).executeScript( "arguments[0].scrollIntoView(true);", element); 
			hardWaitForDemo(); 
		} 
	    
	    public void hardWaitForDemo() { 
	    	hardWaitForDemo(getDemoWaitSeconds());
	    	} 
	    public void hardWaitForDemo(int seconds) { 
	    	if (getDemoWaitSeconds()!=0) 
	    		wait.hardWait(seconds); 
	    	}
	    
	    public int getDemoWaitSeconds() {
	    	return demoWaitSeconds; 
	    }
	    
	   

}
