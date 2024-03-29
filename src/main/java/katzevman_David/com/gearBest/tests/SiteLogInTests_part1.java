package katzevman_David.com.gearBest.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.ExternalLinks;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_LandingPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_SignInPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;


public class SiteLogInTests_part1 extends AbstractTest {


	@Test (groups = {"Sanity"})
	public void _0_09_gearBest_basicFunctions_LoginTests_1() throws Exception {

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
		report.startLevel("Step 3 - Go to the sign in page");
		GearBest_SignInPage gearBest_SignInPage = gearBestLandingPage.signIn();
		report.endLevel();

		// Step 4 - checking sign in functionality
		report.startLevel("Step 4 - checking register menue functionality");
		gearBest_SignInPage.clickRegisterMenu();
		report.endLevel();

		// Step 5 - checking the external links to sign in
		report.startLevel("Step 5 - checking the external links to sign in");
		ExternalLinks externalLinks = gearBest_SignInPage.faceBookSignInButton();
		externalLinks.switchToNewWindow(2);
		report.endLevel();
	}


	private void initTestParams() throws Exception {
		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/resources/config/shoppingCartTest.properties");
		prop.load(input);

	}


}