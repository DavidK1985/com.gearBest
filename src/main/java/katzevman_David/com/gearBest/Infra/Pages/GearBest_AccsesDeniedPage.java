package katzevman_David.com.gearBest.Infra.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.web.By2;

public class GearBest_AccsesDeniedPage extends AbstractPage{

	private static final By2 accessDenied = new By2("The failure to access the product page headline", By.xpath("//h1[contains(text(),'Access Denied')]"));

	public GearBest_AccsesDeniedPage(WebDriver driver) throws Exception {
		super(driver);
	}

	public boolean accessDenied() throws Exception {
		

		boolean webFailure = false; 
		if(bot.isElementDisplayed1(accessDenied)) {
			webFailure = true;
			report.step("there has been a failure to access the product page, will run previous step again with diffrent product");
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("window.history.go(-1)");
		}
		return webFailure;
	}
}