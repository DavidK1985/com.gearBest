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


public class Test3 extends AbstractTest {

	private String searchTerm;
	private String searchTerm2;
	private int resultNumber = resultIndex();
	// search for an object from the shopping cart text file on gear best and after finding that item returning to the home page
	//and searching for another item
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
		report.endLevel();

		// Step 5 - entering the product page 
		report.startLevel("Step 5 - entering the product page");
		gearBestSearchResultsPage.ChosenProduct(resultNumber);
		GearBestProductPage gearBestProductPage = gearBestSearchResultsPage.clickOnSearchResultTitle(resultNumber);
		report.endLevel();

		// Step 6 - Going back to the home page 
		report.startLevel("Step 6 - Going back to the home page");
		gearBestProductPage.backToHomePage();
		report.endLevel();

		// Step 7 - Write a search term from the shopping cart text file in the search bar and click the search button
		report.startLevel("Step 7 - Write a new search term from the shopping cart text file in the search bar");
		gearBestLandingPage.writeToSearchbox(searchTerm2);
		gearBestLandingPage.clickOnGoButton();
		report.endLevel();
	}


	private void initTestParams() throws Exception {

		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/resources/config/shoppingCartTest.properties");
		prop.load(input);
		searchTerm = prop.getProperty("searchTerm");
		searchTerm2 = prop.getProperty("searchTerm2");
	}

	private int resultIndex() {
		Random objGenerator = new Random();
		int randomNumber = objGenerator.nextInt(5);
		return randomNumber;

	}
}