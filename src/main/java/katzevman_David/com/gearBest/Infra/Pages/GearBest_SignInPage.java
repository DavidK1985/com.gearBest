package katzevman_David.com.gearBest.Infra.Pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import katzevman_David.com.gearBest.Infra.web.By2;

public class GearBest_SignInPage extends AbstractPage{

	private static final By2 signIn = new By2("Header", By.xpath("//a[@class='authTab_link']"));
	private static final By2 register = new By2("Header", By.xpath("//a[@class='authTab_link']"));
	private static final By2 emailBox = new By2("Email input box", By.xpath("//input[@id='email']"));
	private static final By2 passwordBox = new By2("password input box", By.xpath("//input[@id='password']"));
	private static final By2 signInConfirmationButton = new By2("Confirmation button", By.xpath("//input[@id='js-btnSubmit']"));

	private static final By2 faceBookSignInButton = new By2("The sign in through facebbok button", By.xpath("//i[@class='icon-fb']"));
	private static final By2 gmailSignInButton = new By2("The sign in through Gmail button", By.xpath("//i[@class='icon-gplus']"));

	private static final By2 signInErrorMSG = new By2("This message type appears when the login is invalid", By.xpath("//p[@class='form_msg form_msg-error']"));
	private static final By2 informationErrorMSG = new By2("This message type appears when the login is invalid", By.xpath("//div[contains(text(),'incorrect']"));

	public GearBest_SignInPage(WebDriver driver) throws Exception {
		super(driver,signIn);
	}

	public void clickSignInMenue() {
		bot.click(signIn);
		List<WebElement> Counter = driver.findElements(By.xpath("//div[@class='authForm_wrap']//div[@class='authForm_item js-formGroup']"));
		int totalCount = Counter.size();
		if(totalCount==2) {
			report.step("by clicking the back to sign in we see the options change back into a sign in format");
		}
	}

	public void clickRegisterMenue() {
		bot.click(register);
		List<WebElement> Counter = driver.findElements(By.xpath("//div[@class='authForm_item js-formGroup']"));
		int totalCount = Counter.size();
		if(totalCount>2) {
			report.step("by clicking the register we see the sign in option change into a diffrent format");
		}
	}

	public void writeToEmailInputBox(String email) {
		bot.writeToElement(emailBox, email);
	}
	public void writeToPasswordInputBox(String password) {
		bot.writeToElement(passwordBox, password);
	}

	public ExternalLinks clickSignInButton() throws Exception{
		bot.click(signInConfirmationButton);
		return new ExternalLinks(driver);
	}

	public ExternalLinks faceBookSignInButton() throws Exception {
		bot.click(faceBookSignInButton);
		report.step("By pressing the facebook icon we are moved to the facebookpage registry");
		return new ExternalLinks(driver);
	}

	public ExternalLinks gmailSignInButton() throws Exception {
		bot.click(gmailSignInButton);
		return new ExternalLinks(driver);
	}

	public void inValidAct() {
		if(bot.isElementDisplayed1(signInErrorMSG)) {
			report.step("This way to login is invalid");
		}
	}
	public void incorrectLoginInformation() throws Exception {
		if(bot.isElementDisplayed1(informationErrorMSG)) {
			report.step("This way to login is invalid");
		}
	}

	public String randomEmailGenerator() {
		String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
		String email;

		java.util.Random rand = new java.util.Random();
		Set<String> identifiers = new HashSet<String>();

		String randomIdentifier;
		StringBuilder builder = new StringBuilder();
		while(builder.toString().length() == 0) {
			int length = rand.nextInt(5)+5;
			for(int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if(identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		randomIdentifier = builder.toString();

		email=randomIdentifier+"@gmail.com";

		return email;
	}

	public String randomPasswordGenerator() {
		String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
		String password;

		java.util.Random rand = new java.util.Random();
		Set<String> identifiers = new HashSet<String>();

		StringBuilder builder = new StringBuilder();
		while(builder.toString().length() == 0) {
			int length = rand.nextInt(5)+5;
			for(int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if(identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		password = builder.toString();


		return password;
	}

}
