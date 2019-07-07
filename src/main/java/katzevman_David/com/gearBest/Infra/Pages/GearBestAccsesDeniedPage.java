package katzevman_David.com.gearBest.Infra.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.web.By2;

public class GearBestAccsesDeniedPage extends AbstractPage{

	private static final By2 accessDenied = new By2("A headline shown when a failure to access the product page", By.xpath("//h1[contains(text(),'Access Denied')]"));

	public GearBestAccsesDeniedPage(WebDriver driver) throws Exception {
		super(driver);
	}

	public boolean accessDenied() throws Exception {
		//		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		boolean webFailure = false; 
		if(bot.isElementDisplayed1(accessDenied)) {
			webFailure = true;
			report.step("there has been a failure to access the product page, will run previous step again");
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("window.history.go(-1)");
		}
		return webFailure;
	}
}