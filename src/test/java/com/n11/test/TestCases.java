package com.n11.test;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.n11.pages.Page;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases extends Page {
	WebDriverWait wait= new WebDriverWait(driver, 500);
	static String selectedFavoriteProduct;
	private int favoriesCount;
	private int willDeleteFavorite;
	  
	@Test
	public void test_1_1_webSiteControl(){ 
		driver.get(getUrl());
		waitForPageLoad(); 
		Assert.assertTrue(driver.getTitle().equals("n11.com - Alışverişin Uğurlu Adresi"));
		System.out.println("N11 Alışveriş sitesi başarılı bir şekilde açıldı");
	}  
	@Test
	public void test_1_2_webSiteLoginPage(){ 
		findByClass("btnSignIn").click();
		wait.until(elementClickableById("loginButton")); 
		Assert.assertTrue(driver.getTitle().equals("Giriş Yap - n11.com"));
		System.out.println("Kullanıcı girişi için sayfa başarılı bir şekilde açıldı...");
	}
	@Test
	public void test_1_3_webSiteLogin(){  
		findById("email").sendKeys("mustafazorbaz7@gmail.com");
		findById("password").sendKeys("deneme123456"); 
		findById("loginButton").click();
		waitForPageLoad(); 
		Assert.assertTrue(findByClass("user").getText().equals("Mustafa Zorbaz"));
		System.out.println("Kullanıcı Girişi Başarılı ...");
	 
	}
	@Test
	public void test_1_4_webSiteSearch (){  
		findById("searchData").sendKeys("samsung"); 
		findByClass("searchBtn").click();  
		String count= findByXpad("//*[@class='resultText ']/strong").getText(); 
		Assert.assertTrue(!count.equals(""));
		System.out.println("Samsung için sonuç bulundu");
	 
	}
	@Test
	public void test_1_5_webSiteSearchSecondPage (){  
		findByXpad("//*[@class='pagination']/a[2]").click();  
		waitForPageLoad();
		Assert.assertTrue(driver.getTitle().contains("Samsung - n11.com - 2/"));
		System.out.println("2. Sayfa başarılı bir şekilde açıldı...");
	 
	} 
	@Test
	public void test_1_6_webSiteSelectThirdProductAndAddToFavorites (){  
		wait.until(elementClickableByXpad("//li[3]/div/div[2]/span[2]"));
		selectedFavoriteProduct=getElementTextByXpad("//li[3]/div/div/a/h3");
		findByXpad("//li[3]/div/div[2]/span[2]").click();
		System.out.println("Favoriye Eklenen Ürün   : "+selectedFavoriteProduct);
	 
	}
	@Test
	public void test_1_7_webSiteClickMyFavorites () {  
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", findByXpad("//*[@class='myAccountMenu hOpenMenu']/div[1]/a[2]"));
		waitForPageLoad(); 
		System.out.println("Favoriler için hesap sayfası başarılı bir şekilde açıldı...");
	 
	}
	@Test
	public void test_1_8_webSiteOpenMyFavorites () {  
		findByXpad("//*[@class='wishGroupListItem favorites']/div[1]/a[1]").click();	
		waitForPageLoad(); 
		System.out.println("Favorilerim sayfası başarılı bir şekilde açıldı...");
	 
	}
	@Test
	public void test_1_9_webSiteFindProduct () {  
		List<WebElement> productTitles= findListByXpad("//* [@id='view']/ul/li/div/div/a/h3"); 
		for (WebElement productTitle : productTitles) {
			favoriesCount+=1;
			String watchesProduct=productTitle.getText();
			System.out.println(watchesProduct);
			if (watchesProduct.equals(selectedFavoriteProduct)) {
				System.out.println("Favoriye Eklenen Ürün Onaylandı.Ürünün Başlığı :"+watchesProduct+"\n");
				willDeleteFavorite=favoriesCount;
				Assert.assertTrue(watchesProduct.equals(selectedFavoriteProduct));
			}
		} 
	}
	@Test
	public void test_2_1_webSiteDeleteProduct () throws InterruptedException {  
		List<WebElement> productTitles= findListByXpad("//* [@id='view']/ul/li/div/div/a/h3"); 
		for (WebElement productTitle : productTitles) {
			favoriesCount+=1;
			String watchesProduct=productTitle.getText();
			System.out.println(watchesProduct);
			if (watchesProduct.equals(selectedFavoriteProduct)) {
				findByXpad("//* [@id='view']/ul/li/div/div[3]/span").click(); 
				Thread.sleep(300); 
				Assert.assertTrue(findByClass("message").getText().equals("Ürününüz listeden silindi."));
				findByClass("confirm").click();
			}
		}  
	}

	

}
