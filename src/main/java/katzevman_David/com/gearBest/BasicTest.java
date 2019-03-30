package katzevman_David.com.gearBest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
		driver.get("https://www.gearbest.com/");
		
		WebElement newUserCoupon = driver.findElement(By.className("siteNewUser_detail"));
		if(newUserCoupon.isDisplayed()) {
			WebElement newUserCouponXButton = driver.findElement(By.id("layui-layer1"));
			newUserCouponXButton.click();	
		}
		
	}
}
