package katzevman_David.com.gearBest.Infra.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.web.By2;


public class ExternalLinks extends AbstractPage{

	private static final By2 googleStoreHomeButton = new By2("The home button for the google store", By.xpath("//div[@class='gb_lc']"));
	private static final By2 appStoreHomeButton = new By2("The home button for the Apple store", By.xpath("//ul[@class='ac-gn-list']//li[@class='ac-gn-item ac-gn-apple']"));

	private static final By2 faceBookLogo = new By2("The FaceBook logo", By.xpath("//html[@id='facebook']"));
	private static final By2 googleLogo = new By2("The Google logo", By.xpath("//div[@class='GuHSXd']"));


	public ExternalLinks(WebDriver driver) throws Exception {
		super(driver);
	}

	public void externalSiteConfirmation(String siteName)  {
		boolean linkFailure = false;

		while (!linkFailure) {
			if(bot.isElementDisplayed1(googleStoreHomeButton)) {
				linkFailure = true;
				report.step("We have reached the google store app for Gear Best");
				siteName = "google store page";
				break;
			}
			if(bot.isElementDisplayed1(appStoreHomeButton)) {
				linkFailure = true;
				report.step("We have reached the Apple store app for Gear Best");
				siteName = "Apple store page";
				break;
			}
			if (!linkFailure) {
				report.step("It would seem that the site we were brought to was not the one we were expecting ("+siteName+")");
				break;
			}
		}
	}

	public void switchToNewWindow(int windowNumber) {
		//Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
		//Perform the click operation that opens new window

		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window
		boolean linkFailure = false;

		while(linkFailure == false) {
			if(bot.isElementDisplayed1(faceBookLogo)) {
				report.step("We have reached the FaceBook login from the Gear Best sign in page");
				linkFailure = true;
				break;

			}
			if(bot.isElementDisplayed1(googleLogo)) {
				report.step("We have reached the Google login from the Gear Best sign in page");
				linkFailure = true;
				break;
			}
			if (linkFailure == false) {
				report.step("It would seem that the site we were brought to was not the one we were expecting");
				break;
			}

		}

		//Close the new window if that window no more required
		driver.close();

		//Switch back to original browser (first window)

		driver.switchTo().window(winHandleBefore);

		//continue with original browser (first window)
	}


}
