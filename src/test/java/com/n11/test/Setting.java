package com.n11.test;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass; 
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver; 

/**
 * 
 * @author Mustafa ZORBAZ
 *
 */
public class Setting {
	protected static WebDriver driver;
	private static String webUrl="https://www.n11.com/";
	
	 
	@BeforeClass
	public static void before(){
		System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe"); 
		driver=new ChromeDriver();
	}
	@AfterClass
	public static void after() throws InterruptedException{
		Thread.sleep(3000);
	} 
	public String getUrl(){
		return webUrl;
	}
	public WebDriver getDriver(){
		return driver;
	}
}
