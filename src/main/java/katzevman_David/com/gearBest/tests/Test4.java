package katzevman_David.com.gearBest.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.GearBestLandingPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;

public class Test4 extends AbstractTest{

	private String searchTerm;
	// search for an object from the shopping cart text file on gear best and see that the result contains it
	@Test
	public void _0_04_gearBestFlashSale() throws Exception {
		initTestParams();
		// Step 1 - Browse to GearBest.com landing page
		report.startLevel("Step 1 - Browse to GearBest.com landing page");
		browseToUrl(MainConfig.baseUrl);
		GearBestLandingPage gearBestLandingPage = new GearBestLandingPage(driver);
		report.endLevel();

		// Step 2 - Close the coupon
		report.startLevel("Step 2 - Close the auto coupon in the landing page");
		gearBestLandingPage.closePopup();
		report.endLevel();

		// Step 3 - 
		report.startLevel("Step 3 - ");
		

		gearBestLandingPage.clickSuperDealsBanner();

		report.endLevel();


	}

	private void initTestParams() throws Exception {

		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/resources/config/shoppingCartTest.properties");
		prop.load(input);
		searchTerm = prop.getProperty("searchTerm");
	}
}
