package katzevman_David.com.gearBest.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.GearBestLandingPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBestProductPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBestSearchResultsPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;


public class Test2 extends AbstractTest {

	private String searchTerm;
	private int resultNumber = resultIndex();
	// search for an object from the shopping cart text file on gear best and see that the result contains it
	@Test
	public void _0_02_gearBestIndevidualProductTest() throws Exception {

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

		// Step 3 - Write a search term from the shopping cart text file in the search bar and click the search button
		report.startLevel("Step 3 - Write a search term from the shopping cart text file in the search bar");
		gearBestLandingPage.writeToSearchbox(searchTerm);
		GearBestSearchResultsPage gearBestSearchResultsPage = gearBestLandingPage.clickOnGoButton();
		report.endLevel();

		// Step 4 - Checking the search result on a random item 
		report.startLevel("Step 4 - Checking the search result by comering the search item to random item");
		String resultTitle = gearBestSearchResultsPage.getSearchResultTitleByIndex(resultNumber);
		gearBestSearchResultsPage.comperResult(resultTitle, searchTerm);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//		gearBestSearchResultsPage.clickOnSearchResultTitle(resultNumber);
		report.endLevel();
		
		// Step 5 - entering the product page 
		report.startLevel("Step 5 - compering the product name to the one seen in the result page");
		String resultsPageProductName = gearBestSearchResultsPage.ChosenProduct(resultNumber);
		GearBestProductPage gearBestProductPage = gearBestSearchResultsPage.clickOnSearchResultTitle(resultNumber);
		report.endLevel();
	
		// Step 6 - product page 
		report.startLevel("Step 6 - compering the product name to the one seen in the result page");
		gearBestProductPage.nameComperison(resultsPageProductName);
		report.endLevel();
		
		// Step 7 - product page discount Check
		report.startLevel("Step 7 - click on product number "+resultNumber+" and see if there is a discount");
		gearBestProductPage.discountCheck();
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