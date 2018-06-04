package com.n11.test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.n11.pages.Page;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases extends Page {
	WebDriverWait wait= new WebDriverWait(driver, 500);
	  
	@Test
	public void test_1_webSiteControl(){ 
		driver.get(getUrl());
		waitForPageLoad(); 
		Assert.assertTrue(driver.getTitle().equals("n11.com - Alışverişin Uğurlu Adresi"));
		System.out.println("N11 Alışveriş sitesi başarılı bir şekilde açıldı");
	}  
	@Test
	public void test_2_webSiteLoginPage(){ 
		findByClass("btnSignIn").click();
		wait.until(elementClickableById("loginButton")); 
		Assert.assertTrue(driver.getTitle().equals("Giriş Yap - n11.com"));
		System.out.println("Kullanıcı girişi için sayfa başarılı bir şekilde açıldı...");
	}
	@Test
	public void test_3_webSiteLogin(){  
		findById("email").sendKeys("mustafazorbaz7@gmail.com");
		findById("password").sendKeys("deneme123456"); 
		findById("loginButton").click();
		waitForPageLoad(); 
		Assert.assertTrue(findByClass("user").getText().equals("Mustafa Zorbaz"));
		System.out.println("Kullanıcı Girişi Başarılı ...");
	 
	}
	
	

	

}
