package com.n11.test;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Setting {
	protected static WebDriver driver;
	private static String webUrl="https://www.n11.com/";
	
	 
	@BeforeClass
	public static void before(){
		System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver.exe"); 
		driver=new FirefoxDriver();
		 
	} 
	public String getUrl(){
		return webUrl;
	}
	public WebDriver getDriver(){
		return driver;
	}
}
