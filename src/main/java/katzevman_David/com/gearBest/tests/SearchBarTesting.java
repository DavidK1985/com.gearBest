package katzevman_David.com.gearBest.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.GearBest_AccsesDeniedPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_LandingPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_ProductPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_SearchResultsPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;


public class SearchBarTesting extends AbstractTest {

	private String searchTerm;
	private String searchTerm2;
	private int resultNumber = resultIndex();
	// search for an object from the shopping cart text file on gear best and after finding that item returning to the home page
	//and searching for another item

//	(groups = {"Regression"})
	@Test 
	public void _0_04_gearBestSearchbarTests() throws Exception {

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

		// Step 3 - Write a search term from the shopping cart text file in the search bar and click the search button
		report.startLevel("Step 3 - Write a search term from the shopping cart text file in the search bar");
		gearBestLandingPage.writeToSearchbox(searchTerm);
		GearBest_SearchResultsPage gearBestSearchResultsPage = gearBestLandingPage.clickOnGoButton();
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
		GearBest_ProductPage gearBestProductPage = gearBestSearchResultsPage.clickOnSearchResultTitle(resultNumber);
		report.endLevel();

		// Step 6 - Going back to the home page 
		report.startLevel("Step 6 - Going back to the home page");
		GearBest_AccsesDeniedPage gearBestAccsesDeniedPage = new GearBest_AccsesDeniedPage(driver);
		boolean failedToLoad = gearBestAccsesDeniedPage.accessDenied();
		while(failedToLoad) {
			resultNumber = resultIndex();
			gearBestSearchResultsPage.clickOnSearchResultTitle(resultNumber);
			failedToLoad = gearBestAccsesDeniedPage.accessDenied();
		}
		gearBestProductPage.backToHomePage();
		report.endLevel();

		// Step 7 - Write a search term from the shopping cart text file in the search bar and click the search button
		report.startLevel("Step 7 - Write a new search term from the shopping cart text file in the search bar");
		gearBestLandingPage.writeToSearchbox(searchTerm2);
		gearBestLandingPage.clickOnGoButton();

		failedToLoad = gearBestAccsesDeniedPage.accessDenied();
		while(failedToLoad) {
			int counter = 0;
			gearBestLandingPage.writeToSearchbox(searchTerm2);
			gearBestLandingPage.clickOnGoButton();
			failedToLoad = gearBestAccsesDeniedPage.accessDenied();
			counter++;
			if(counter>3) {
				report.step("After over 7 triels we can confirm that the search bar is non respunsive");
				break;			
			}
		}

		report.endLevel();

		// Step 8 - confirming the search results
		report.startLevel("Step 8 - confirming the search results match the search term");
		resultNumber = resultIndex();
		resultTitle = gearBestSearchResultsPage.getSearchResultTitleByIndex(resultNumber);
		gearBestSearchResultsPage.comperResult(resultTitle, searchTerm2);
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
		int randomNumber = objGenerator.nextInt(10);
		return randomNumber;

	}
}