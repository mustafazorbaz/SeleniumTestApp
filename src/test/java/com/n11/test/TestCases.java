package com.n11.test;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import com.n11.pages.FavouritePage;
import com.n11.pages.HomePage;
import com.n11.pages.LoginPage;
import com.n11.pages.SearchPage;

/**
 * 
 * @author Mustafa ZORBAZ
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases  extends  BaseTest{
	/**
	 * <http://www.n11.com/> sitesine gelecek ve anasayfanin acildigini onaylayacak
	 */
	@Test
	public void test_1_1_homePageControl(){ 
		HomePage homePage=new HomePage(driver);
		driver.get(homePage.getUrl());
		homePage.waitForPageLoad();
		System.out.println("title ="+driver.getTitle());
		Assert.assertTrue(driver.getTitle().equals("n11.com - Alışverişin Uğurlu Adresi")); 
		System.out.println("N11 Alışveriş sitesi başarılı bir şekilde açıldı");
	}
	
	/**
	 * Login ekranini acip, bir kullanici ile login olacak ( daha once siteye uyeliginiz varsa o olabilir )
	 */
	@Test
	public void test_1_2_loginPage(){ 
		HomePage homePage=new HomePage(driver);
		homePage.getLoginPage();
		Assert.assertTrue(driver.getTitle().equals("Giriş Yap - n11.com"));
		System.out.println("Kullanıcı girişi için sayfa başarılı bir şekilde açıldı...");
	}
	
	/**
	 * Bir kullanici ile login olacak ( daha once siteye uyeliginiz varsa o olabilir )
	 * Burada kullanıcı adını ve şifreyi değiştiribilirsiniz.
	 */
	@Test
	public void test_1_3_signIn(){  
		LoginPage loginPage =new LoginPage(driver);
		loginPage.enterLoginForm("mustafazorbaz7@gmail.com", "deneme123456");
		loginPage.submit();
		loginPage.waitForPageLoad(); 
		Assert.assertTrue(loginPage.getUserName().equals("Mustafa Zorbaz"));
		System.out.println("Kullanıcı Girişi Başarılı ...");
	 
	}
	
	/**
	 * Ekranin ustundeki Search alanina 'samsung' yazip Ara butonuna tiklayacak 
	 * Gelen sayfada samsung icin sonuc bulundugunu onaylayacak 
	 * @throws InterruptedException
	 */
	@Test
	public void test_1_4_dataSearch () throws InterruptedException{  
		SearchPage searchPage =new SearchPage(driver);			 
		searchPage.enterDataForSearch("samsung");
		searchPage.submitForSearch();
		Thread.sleep(100); //Minumum bekleme ile arada oluşabilecek hata önlenmektedir. 
		Assert.assertTrue(!searchPage.getCountResult().equals(""));
		System.out.println("Samsung için sonuç bulundu");
	 
	}
	

	/**
	 * Arama sonuclarindan 2. sayfaya tiklayacak ve acilan sayfada 2. sayfanin su an gosterimde oldugunu onaylayacak
	 * @throws InterruptedException
	 */
	@Test
	public void test_1_5_searchSecondPage () throws InterruptedException{
		Thread.sleep(200); //Minumum bekleme ile arada oluşabilecek hata önlenmektedir. 
		SearchPage searchPage =new SearchPage(driver);	
		searchPage.clickSecondButton(); 
		searchPage.waitForPageLoad();
		Assert.assertTrue(driver.getTitle().contains("Samsung - n11.com - 2/"));
		System.out.println("2. Sayfa başarılı bir şekilde açıldı...");
	 
	}

	/**
	 * Ustten 3. urunun icindeki 'favorilere ekle' butonuna tiklayacak 
	 */
	@Test
	public void test_1_6_selectThirdProductAndAddToFavorites (){ 
		SearchPage searchPage =new SearchPage(driver);
		searchPage.selectThirdProduct();
		searchPage.addFavoriteProduct();
		System.out.println("Favoriye Eklenen Ürün   : "+searchPage.getSelectedFavoriteProduct());
	 
	}
	 
	/**
	 * Ekranin en ustundeki pop-up ile 'favorilerim' linkine tiklayacak 
	 */
	@Test
	public void test_1_7_clickMyFavorites () {  
		FavouritePage favouritePage =new FavouritePage(driver);
		favouritePage.clickPopupMyFavorite();
		System.out.println("Favoriler için hesap sayfası başarılı bir şekilde açıldı...");
	 
	}
	
	/**
	 * Yeni açılan ekranda en ustundeki 'favorilerim' linkine tiklayacak 
	 */
	@Test
	public void test_1_8_openMyFavorites () {  
		FavouritePage favouritePage =new FavouritePage(driver);
		favouritePage.myFavoriteListShow(); 
		System.out.println("Favorilerim sayfası başarılı bir şekilde açıldı...");
	 
	}
	
	/**
	 * Acilan sayfada bir onceki sayfada izlemeye alinmis urunun bulundugunu onaylayacak
	 */
	@Test
	public void test_1_9_findFavoriteProduct () {  
		FavouritePage favouritePage =new FavouritePage(driver); 
		for (WebElement productTitle : favouritePage.myFavoriteList()) { 
			String watchesProduct=productTitle.getText(); 
			if (watchesProduct.equals(favouritePage.getSelectedFavoriteProduct())) {
				System.out.println("Favoriye Eklenen Ürün Onaylandı.Ürünün Başlığı :"+watchesProduct+"\n"); 
				Assert.assertTrue(watchesProduct.equals(favouritePage.getSelectedFavoriteProduct()));
			}
		} 
	}
	
	/**
	 * Favorilere alinan bu urunun yanindaki 'Kaldir' butonuna basarak, favorilerimden cikaracak
	 * @throws InterruptedException
	 */
	@Test
	public void test_2_1_webSiteDeleteProduct () throws InterruptedException { 
		FavouritePage favouritePage =new FavouritePage(driver); 
		for (WebElement productTitle : favouritePage.myFavoriteList()) {  
			String watchesProduct=productTitle.getText(); 
			if (watchesProduct.equals(favouritePage.getSelectedFavoriteProduct())) { 
				favouritePage.clickLinkDeleteProduct();
				 Thread.sleep(3000); 
				 Assert.assertTrue(favouritePage.getMessage().equals("Ürününüz listeden silindi.")); 
				 favouritePage.closeContent();  
				System.out.println("Ürününüz listeden başarılı bir şekilde silindi....");

			}
		}  
	}
	
	/**
	 * Sayfada bu urunun artik favorilere alinmadigini onaylayacak. 
	 * @throws InterruptedException
	 */
	@Test
	public void test_2_2_deleteConfirmationForProduct () {
		boolean controlValue=true;
		FavouritePage favouritePage =new FavouritePage(driver); 
		for (WebElement productTitle : favouritePage.myFavoriteList()) {   
			String watchesProduct=productTitle.getText(); 			
			if (watchesProduct.equals(favouritePage.getSelectedFavoriteProduct())) 
				controlValue=false;
		}  
		Assert.assertFalse(controlValue);
		System.out.println("Ürününüz favorilerde yer almıyor....");
 
	}

}
