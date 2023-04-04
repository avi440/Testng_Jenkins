package com.education.testCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWait {
	
	WebDriver driver;
	WebDriverWait wait; 
	int timeout; 
	
	public SeleniumWait(WebDriver driver, int timeout) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
		this.timeout = timeout; 
		}
	

 
 public WebElement waitForElementToBeVisible(WebElement element) {
	 return (WebElement) wait.until(ExpectedConditions.visibilityOf(element)); 
 } 
 
 

 
 public boolean waitForElementToBeInVisible(By locator) { 
	 return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)) != null; 
 }
 public WebElement waitForElementToBeClickable(WebElement element) {
	 return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element)); 
 }
 

 public void waitForPageTitleToAppearCompletely(String txtPageTitle) { 
	 WebDriverWait wait = new WebDriverWait(driver, 30);
	 wait.until(ExpectedConditions.titleIs(txtPageTitle)); 
 }
 


 public void resetImplicitTimeout(int newTimeOut) { 
	 try { 
		 driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS); 
	 }
	 catch (Exception e) { } 
 }
	 

	


 
 
 

void hardWait(int seconds) {
	// TODO Auto-generated method stub
	 try { 
		 Thread.sleep(seconds * 1000); 
	 }catch (InterruptedException ex) {
		 ex.printStackTrace(); 
  }
	
} 



	

}
