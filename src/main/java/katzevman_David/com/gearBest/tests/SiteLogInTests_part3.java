package katzevman_David.com.gearBest.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.GearBest_LandingPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_SignInPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;


public class SiteLogInTests_part3 extends AbstractTest {


	@Test (groups = {"Regression"})
	public void _0_11_gearBest_basicFunctions_LoginTests_3() throws Exception {

		initTestParams();
		// Step 1 - Browse to GearBest.com landing page
		report.startLevel("Step 1 - Browse to GearBest.com landing page");
		browseToUrl(MainConfig.baseUrl);
		GearBest_LandingPage gearBestLandingPage = new GearBest_LandingPage(driver);
		report.endLevel();

		// Step 2 - Close the coupon
		report.startLevel("Step 2 - Close the auto coupon in the landing page");
		gearBestLandingPage.closePopup();
		report.endLevel();

		// Step 3 - Go to the sign in page
		report.startLevel("Step 3 - click the sign in button");
		GearBest_SignInPage gearBest_SignInPage = gearBestLandingPage.signIn();
		report.endLevel();

		// Step 4 - enter a random email
		report.startLevel("Step 4 - checking the sign in with email only");
		String email = gearBest_SignInPage.randomEmailGenerator();
		gearBest_SignInPage.writeToEmailInputBox(email);
		gearBest_SignInPage.clickSignInButton();
		gearBest_SignInPage.inValidAct();
		report.endLevel();


		// Step 5 - Go to the sign in page
		report.startLevel("Step 5 - checking the sign in with password only");
		gearBest_SignInPage.writeToEmailInputBox("");
		String password = gearBest_SignInPage.randomPasswordGenerator();
		gearBest_SignInPage.writeToPasswordInputBox(password);
		gearBest_SignInPage.clickSignInButton();
		gearBest_SignInPage.inValidAct();
		report.endLevel();

		//"Step 6 - checking the sign in with a password and an email"
		report.startLevel("Step 6 - checking the sign in with a password and an email");
		email = gearBest_SignInPage.randomEmailGenerator();
		gearBest_SignInPage.writeToEmailInputBox(email);
		password = gearBest_SignInPage.randomPasswordGenerator();
		gearBest_SignInPage.writeToPasswordInputBox(password);
		gearBest_SignInPage.clickSignInButton();
		report.endLevel();
		
//		//"Step 7 - checking the sign in with a password and an email"
//		report.startLevel("Step 7 - error handeling");
//		gearBest_SignInPage.incorrectLoginInformation();
//		report.endLevel();

	}



	private void initTestParams() throws Exception {
		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/resources/config/shoppingCartTest.properties");
		prop.load(input);

	}


}