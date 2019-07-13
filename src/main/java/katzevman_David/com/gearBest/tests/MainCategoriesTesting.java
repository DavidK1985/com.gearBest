package katzevman_David.com.gearBest.tests;

import java.util.Random;

import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.GearBest_AccsesDeniedPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_LandingPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_ProductPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_SearchResultsPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;

public class MainCategoriesTesting extends AbstractTest {

	private int resultNumber = resultIndex();
	// search for an object from the shopping cart text file on gear best and after finding that item returning to the home page

	@Test 
	public void _0_06_gearBestIndevidualCategoryTest() throws Exception {

		// Step 1 - Browse to GearBest.com landing page
		report.startLevel("Step 1 - Browse to GearBest.com landing page");
		browseToUrl(MainConfig.baseUrl);
		GearBest_LandingPage gearBestLandingPage = new GearBest_LandingPage(driver);
		report.endLevel();

		// Step 2 - Close the coupon
		report.startLevel("Step 2 - Close the auto coupon in the landing page");
		gearBestLandingPage.closePopup();
		report.endLevel();

		// Step 3 - Pick a random category out of the category box in the front page
		report.startLevel("Step 3 - Pick a random category out of the category box in the front page");
		String chosenCategory = gearBestLandingPage.choseRandomCategory(resultNumber);
		report.endLevel();

		// Step 4 - Pick a random search result 
		report.startLevel("Step 4 - Pick a random search result out of the category result page");
		GearBest_SearchResultsPage gearBestSearchResultsPage = new GearBest_SearchResultsPage(driver);
		GearBest_ProductPage gearBestProductPage = gearBestSearchResultsPage.clickOnSearchResultTitle(resultNumber);

		Thread.sleep(1000);
		
		GearBest_AccsesDeniedPage gearBestAccsesDeniedPage = new GearBest_AccsesDeniedPage(driver);
		boolean failedToLoad = gearBestAccsesDeniedPage.accessDenied();
		while(failedToLoad) {
			resultNumber = resultIndex();
			gearBestSearchResultsPage.clickOnSearchResultTitle(resultNumber);
			failedToLoad = gearBestAccsesDeniedPage.accessDenied();
			if(!failedToLoad){
			report.step("Access Denied issue side steped");
			}
		}
		report.endLevel();

		// Step 5 - Summary 
		report.startLevel("Step 5 - Results summery");
		String productTitle = gearBestProductPage.GetproductTitle();
		String categoryTree = gearBestProductPage.GetCategoryTree();
		report.step("In the '"+chosenCategory+"' we can find '"+ productTitle+"'\nas seen in the category tree: '"+ categoryTree+"'");
		report.endLevel();
	}	

	private int resultIndex() {
		Random objGenerator = new Random();
		int randomNumber = objGenerator.nextInt(14);
		return randomNumber;

	}
}

