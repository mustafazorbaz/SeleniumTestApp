package com.n11.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.n11.pages.Page;

public class TestCases extends Page { 
	 
	@Test
	public void webSiteControl(){ 
		driver.get(getUrl());
		System.out.println("N11 Alışveriş sitesi başarılı bir şekilde açıldı");
		 

	} 

}
