package katzevman_David.com.gearBest.Infra.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.web.By2;

public class gearBestLandingPage extends AbstractPage {
	
	private static final By2 newUserCoupon = new By2("Coupon on login", By.className("siteNewUser_detail"));
	private static final By2 gearBestHomeButton = new By2("The Home Button", By.className("headLogo"));
	private static final By2 searchBox = new By2("Main search box", By.className("headSearch_formIpt"));
	private static final By2 goButton = new By2("'Go' button", By.xpath("icon-search]"));
//	private static final By2 signInSecurlyButtonBy = new By2("'Sign-in securely' button", By.id("a-autoid-0-announce"));
//	private static final By2 amazonMusicLink = new By2("'Amazon Music' link", By.xpath("//a[text()='Amazon Music']"));
	
	public gearBestLandingPage(WebDriver driver) throws Exception {
		super(driver,newUserCoupon, gearBestHomeButton, searchBox, goButton);
	}

	public void writeToSearchbox(String searchTerm) {
		bot.writeToElement(searchBox, searchTerm);
	}
//	
//	public GearBestSearchResultsPage clickOnGoButton() throws Exception {
//		bot.click(goButton);
//		return new GearBestSearchResultsPage(driver);
//	}
//	
//	public void WaitForSignInSecurlyButtonEnabled() {
//		bot.waitForElementToBeClickable(signInSecurlyButtonBy);
//	}
	
//	public void clickAmazonMusicLink() {
//		bot.click(amazonMusicLink);
//	}
	

}
