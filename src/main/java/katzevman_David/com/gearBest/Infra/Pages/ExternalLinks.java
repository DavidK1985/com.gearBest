package katzevman_David.com.gearBest.Infra.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.web.By2;

public class ExternalLinks extends AbstractPage{

	private static final By2 googleStoreHomeButton = new By2("The home button for the google store", By.xpath("//div[@class='gb_lc']"));
	private static final By2 appStoreHomeButton = new By2("The home button for the Apple store", By.xpath("//ul[@class='ac-gn-list']//li[@class='ac-gn-item ac-gn-apple']"));

	public ExternalLinks(WebDriver driver) throws Exception {
		super(driver);
	}

	public void externalSiteConfirmation() throws Exception {
		boolean linkFailure = false;

		while (!linkFailure) {
			if(bot.isElementDisplayed1(googleStoreHomeButton)) {
				linkFailure = true;
				report.step("We have reached the google store app for Gear Best");
				break;
			}
			if(bot.isElementDisplayed1(appStoreHomeButton)) {
				report.step("We have reached the Apple store app for Gear Best");
				linkFailure = true;
				break;
			}
			if (!linkFailure) {
				report.step("It would seem that the site we were brout to was not the google or apple stores");
				break;
			}
		}
	}
}
