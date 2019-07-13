package katzevman_David.com.gearBest.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.GearBest_LandingPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_SearchResultsPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;


public class BasicUserFlow extends AbstractTest {

	private String searchTerm;
	// search for an object from the shopping cart text file on gear best and see that the result contains it
	
	@Test (groups = {"Sanity"})
	public void _0_01_gearBestLandingPageSearch() throws Exception {

		initTestParams();
		report.step("this test will search for an object from the shopping cart text file on gearbest.com and and see that the result contains it");

		// Step 1 - Browse to GearBest.com landing page
		report.startLevel("Step 1 - Browse to GearBest.com landing page");
		browseToUrl(MainConfig.baseUrl);
		GearBest_LandingPage gearBestLandingPage = new GearBest_LandingPage(driver);
		report.endLevel();

		// Step 2 - Close the coupon
		report.startLevel("Step 2 - Close the auto coupon in the landing page");
		gearBestLandingPage.closePopup();
		report.endLevel();

		// Step 3 - Write a search term from the shopping cart text file in the search bar and click the search button
		report.startLevel("Step 3 - Write a search term from the shopping cart text file in the search bar");
		gearBestLandingPage.writeToSearchbox(searchTerm);
		GearBest_SearchResultsPage gearBestSearchResultsPage = gearBestLandingPage.clickOnGoButton();
		report.endLevel();

		// Step 4 - compare the results to the search term		
		report.startLevel("Step 4 - check if the results contain the search term by randomly pulling on one of the head lines");
		String resultTitle = gearBestSearchResultsPage.getSearchResultTitleByIndex(resultIndex());
		gearBestSearchResultsPage.comperResult(resultTitle, searchTerm);
		report.endLevel();
	}

	private void initTestParams() throws Exception {

		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/resources/config/shoppingCartTest.properties");
		prop.load(input);
		searchTerm = prop.getProperty("searchTerm");
	}
	
	private int resultIndex() {
		Random objGenerator = new Random();
		int randomNumber = objGenerator.nextInt(5);
		return randomNumber;

	}
}