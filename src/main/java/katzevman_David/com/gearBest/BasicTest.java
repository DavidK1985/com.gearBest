package katzevman_David.com.gearBest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.utils.AssertUtils;

public class BasicTest 
{
	public WebDriver driver;
	
	@BeforeMethod
	public void beforTest() {
		if(driver == null) {
			System.setProperty("webdriver.chrome.driver", "webdrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			//			driver.manage().window().maximize();
		}
	}

//	System.setProperty("webdriver.chrome.driver", "WebDrivers\\chromedriver.exe");
	
	@Test
	public void _01_0_openAndSearchGearBest() {
		String webAdrres = "https://www.gearbest.com/";
		driver.get(webAdrres);
		
		WebElement newUserCoupon = driver.findElement(By.className("siteNewUser_detail"));
		WebElement gearBestHomeButton = driver.findElement(By.className("headLogo"));
		if(newUserCoupon.isDisplayed()) {
			WebElement newUserCouponXButton = driver.findElement(By.id("layui-layer1"));
			newUserCouponXButton.click();
			
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//			gearBestHomeButton.click();
		}
		
//		String newWebAdrres = driver.getCurrentUrl();
//		
//		AssertUtils.assertEquals(newWebAdrres , webAdrres, "our current web addres is"+newWebAdrres);
//		
//		System.out.println("our current web addres is"+newWebAdrres);
	}
	
	
}
