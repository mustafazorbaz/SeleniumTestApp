package com.n11.test;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters; 
import org.openqa.selenium.JavascriptExecutor; 
import org.openqa.selenium.WebElement;  
import org.openqa.selenium.support.ui.WebDriverWait;
 
import com.n11.pages.Page;

/**
 * 
 * @author Mustafa ZORBAZ
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases extends Page {
	WebDriverWait wait= new WebDriverWait(driver, 500);
	static String selectedFavoriteProduct;
	private int favoriesCount;
	private int willDeleteFavorite;
	private static boolean controlValue=true;
	 
	/**
	 * <http://www.n11.com/> sitesine gelecek ve anasayfanin acildigini onaylayacak
	 */
	@Test
	public void test_1_1_webSiteControl(){ 
		driver.get(getUrl());
		waitForPageLoad(); 
		Assert.assertTrue(driver.getTitle().equals("n11.com - Alışverişin Uğurlu Adresi"));
		System.out.println("N11 Alışveriş sitesi başarılı bir şekilde açıldı");
	}  
	
	/**
	 * Login ekranini acip, bir kullanici ile login olacak ( daha once siteye uyeliginiz varsa o olabilir )
	 */
	@Test
	public void test_1_2_webSiteLoginPage(){ 
		findByClass("btnSignIn").click();
		wait.until(elementClickableById("loginButton")); 
		Assert.assertTrue(driver.getTitle().equals("Giriş Yap - n11.com"));
		System.out.println("Kullanıcı girişi için sayfa başarılı bir şekilde açıldı...");
	}
	
	/**
	 * Bir kullanici ile login olacak ( daha once siteye uyeliginiz varsa o olabilir )
	 * Burada kullanıcı adını ve şifreyi değiştiribilirsiniz.
	 */
	@Test
	public void test_1_3_webSiteLogin(){  
		findById("email").sendKeys("mustafazorbaz7@gmail.com");
		findById("password").sendKeys("deneme123456"); 
		findById("loginButton").click();
		waitForPageLoad(); 
		Assert.assertTrue(findByClass("user").getText().equals("Mustafa Zorbaz"));
		System.out.println("Kullanıcı Girişi Başarılı ...");
	 
	}
	/**
	 * Ekranin ustundeki Search alanina 'samsung' yazip Ara butonuna tiklayacak 
	 * Gelen sayfada samsung icin sonuc bulundugunu onaylayacak 
	 * @throws InterruptedException
	 */
	@Test
	public void test_1_4_webSiteSearch () throws InterruptedException{  
		findById("searchData").sendKeys("samsung"); 
		findByClass("searchBtn").click();  
		Thread.sleep(100); //Minumum bekleme ile arada oluşabilecek hata önlenmektedir.
		String count= findByXpad("//*[@class='resultText ']/strong").getText(); 
		Assert.assertTrue(!count.equals(""));
		System.out.println("Samsung için sonuç bulundu");
	 
	}
	
	/**
	 * Arama sonuclarindan 2. sayfaya tiklayacak ve acilan sayfada 2. sayfanin su an gosterimde oldugunu onaylayacak
	 * @throws InterruptedException
	 */
	@Test
	public void test_1_5_webSiteSearchSecondPage () throws InterruptedException{
		Thread.sleep(200);
		findByXpad("//*[@class='pagination']/a[2]").click();  
		waitForPageLoad();
		Assert.assertTrue(driver.getTitle().contains("Samsung - n11.com - 2/"));
		System.out.println("2. Sayfa başarılı bir şekilde açıldı...");
	 
	} 
	
	/**
	 * Ustten 3. urunun icindeki 'favorilere ekle' butonuna tiklayacak 
	 */
	@Test
	public void test_1_6_webSiteSelectThirdProductAndAddToFavorites (){  
		wait.until(elementClickableByXpad("//li[3]/div/div[2]/span[2]"));
		selectedFavoriteProduct=getElementTextByXpad("//li[3]/div/div/a/h3");
		findByXpad("//li[3]/div/div[2]/span[2]").click();
		System.out.println("Favoriye Eklenen Ürün   : "+selectedFavoriteProduct);
	 
	}
	
	/**
	 * Ekranin en ustundeki pop-up ile 'favorilerim' linkine tiklayacak 
	 */
	@Test
	public void test_1_7_webSiteClickMyFavorites () {  
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", findByXpad("//*[@class='myAccountMenu hOpenMenu']/div[1]/a[2]"));
		waitForPageLoad(); 
		System.out.println("Favoriler için hesap sayfası başarılı bir şekilde açıldı...");
	 
	}
	
	/**
	 * Yeni açılan ekranda en ustundeki 'favorilerim' linkine tiklayacak 
	 */
	@Test
	public void test_1_8_webSiteOpenMyFavorites () {  
		findByXpad("//*[@class='wishGroupListItem favorites']/div[1]/a[1]").click();	
		waitForPageLoad(); 
		System.out.println("Favorilerim sayfası başarılı bir şekilde açıldı...");
	 
	}
	
	/**
	 * Acilan sayfada bir onceki sayfada izlemeye alinmis urunun bulundugunu onaylayacak
	 */
	@Test
	public void test_1_9_webSiteFindProduct () {  
		List<WebElement> productTitles= findListByXpad("//* [@id='view']/ul/li/div/div/a/h3"); 
		for (WebElement productTitle : productTitles) {
			favoriesCount+=1;
			String watchesProduct=productTitle.getText();
			//System.out.println(watchesProduct);
			if (watchesProduct.equals(selectedFavoriteProduct)) {
				System.out.println("Favoriye Eklenen Ürün Onaylandı.Ürünün Başlığı :"+watchesProduct+"\n");
				willDeleteFavorite=favoriesCount;
				Assert.assertTrue(watchesProduct.equals(selectedFavoriteProduct));
			}
		} 
	}
	
	/**
	 * Favorilere alinan bu urunun yanindaki 'Kaldir' butonuna basarak, favorilerimden cikaracak
	 * @throws InterruptedException
	 */
	@Test
	public void test_2_1_webSiteDeleteProduct () throws InterruptedException {  
		List<WebElement> productTitles= findListByXpad("//*[@id='view']/ul/li/div/div/a/h3"); 
		for (WebElement productTitle : productTitles) {
			favoriesCount+=1;
			String watchesProduct=productTitle.getText(); 
			if (watchesProduct.equals(selectedFavoriteProduct)) { 
				findByXpad("//*[@id='view']/ul/li/div/div[3]/span").click(); 
				 Thread.sleep(3000); 
				 Assert.assertTrue(findByClass("message").getText().equals("Ürününüz listeden silindi.")); 
				 findByClass("closeBtn").click(); 
				 waitForPageLoad();
				System.out.println("Ürününüz listeden başarılı bir şekilde silindi....");

			}
		}  
	}
	
	/**
	 * Sayfada bu urunun artik favorilere alinmadigini onaylayacak. 
	 * @throws InterruptedException
	 */
	@Test
	public void test_2_2_webSiteDeleteConfirmationForProduct () throws InterruptedException { 
		List<WebElement> productTitlesList= findListByXpad("//*[@class='listView']/ul/li/div/div/a/h3");
		for (WebElement productTitleValue : productTitlesList) {
			favoriesCount+=1;
			String watchesProduct=productTitleValue.getText(); 
			if (watchesProduct.equals(selectedFavoriteProduct))  
				controlValue=false;
		}  
		Assert.assertFalse(controlValue);
		System.out.println("Ürününüz favorilerde yer almıyor....");
 
	}

	

}
