package com.n11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.n11.test.Setting;

public class Page extends Setting {
	
	public WebElement findById(String id){
		WebElement element=driver.findElement(By.id(id));
		return element;
	}
	public  WebElement findByClass(String className) {
		WebElement element=driver.findElement(By.className(className));
		return element;
	}
	public  WebElement findByName(String name) {
		WebElement element=driver.findElement(By.name(name));
		return element;
	}
	public WebElement findByXpad(String name) {
		WebElement Element=driver.findElement(By.xpath(name));
		return Element;
	}
	public ExpectedCondition<WebElement> elementClickableById(String id){
		ExpectedCondition<WebElement> clickable=ExpectedConditions.elementToBeClickable((By.id(id)));
		return clickable;
	}
	public ExpectedCondition<WebElement> elementClickableByClassName(String name){
		ExpectedCondition<WebElement> clickable=ExpectedConditions.elementToBeClickable((By.className(name)));
		return clickable;
	} 
	public void waitForPageLoad() {

	    Wait<WebDriver> wait = new WebDriverWait(driver, 10);
	    wait.until(new Function<WebDriver, Boolean>() {
	        public Boolean apply(WebDriver driver) {
	            System.out.println("Current Window State       : "
	                + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
	            return String
	                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
	                .equals("complete");
	        }
	    });
	}
}
