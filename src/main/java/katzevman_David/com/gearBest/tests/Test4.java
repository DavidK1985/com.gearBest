package katzevman_David.com.gearBest.tests;

import java.util.Random;

import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.GearBestAccsesDeniedPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBestLandingPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBestProductFlashSalePage;
import katzevman_David.com.gearBest.Infra.Pages.GearBestSearchResultsPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;

public class Test4 extends AbstractTest{

	private int resultNumber = resultIndex();

	// going into the flash sale page on gear best and see if the product that appears on the sale does not appear in a 
	// different sale at a different price
	
	@Test (groups = {"Regression"})
	public void _0_04_gearBestFlashSale() throws Exception {

		// Step 1 - Browse to GearBest.com landing page
		report.startLevel("Step 1 - Browse to GearBest.com landing page");
		browseToUrl(MainConfig.baseUrl);
		GearBestLandingPage gearBestLandingPage = new GearBestLandingPage(driver);
		report.endLevel();

		// Step 2 - Close the coupon
		report.startLevel("Step 2 - Close the auto coupon in the landing page");
		gearBestLandingPage.closePopup();
		report.endLevel();

		// Step 3 - scrawling down and pressing the SuperDealsBanner
		report.startLevel("Step 3 - scrawl down and click the SuperDeals Banner");

		GearBestProductFlashSalePage gearBestProductFlashSalePage = gearBestLandingPage.clickSuperDealsBanner();
		report.endLevel();

		// Step 4 - get the details for an item on sale
		report.startLevel("Step 4 - ");
		String resultTitle = gearBestProductFlashSalePage.getSearchResultTitleByIndex(resultNumber);
		Float ProductPrice = gearBestProductFlashSalePage.getSearchResultPriceByIndex(resultNumber);
		report.endLevel();

		// Step 5 - activate a search with the chosen item
		report.startLevel("Step 5 - ");
		gearBestProductFlashSalePage.writeToSearchbox(resultTitle);
		GearBestSearchResultsPage gearBestSearchResultsPage = gearBestLandingPage.clickOnGoButton();

		GearBestAccsesDeniedPage gearBestAccsesDeniedPage = new GearBestAccsesDeniedPage(driver);
		boolean failedToLoad = gearBestAccsesDeniedPage.accessDenied();
		while(failedToLoad) {
			resultNumber = resultIndex();
			failedToLoad = gearBestAccsesDeniedPage.accessDenied();
			resultTitle = gearBestProductFlashSalePage.getSearchResultTitleByIndex(resultNumber);
			ProductPrice = gearBestProductFlashSalePage.getSearchResultPriceByIndex(resultNumber);
			gearBestProductFlashSalePage.writeToSearchbox(resultTitle);
			gearBestLandingPage.clickOnGoButton();
			failedToLoad = gearBestAccsesDeniedPage.accessDenied();
		}

		report.endLevel();

		// Step 6 - see if there are any other products that are similar
		report.startLevel("Step 6 - ");
		int productAmount = gearBestSearchResultsPage.amountOfResults();
		report.endLevel();

		// Step 7 - if there are any other products that are similar compare prices, if the one on hot sell is cheaper 
		// then report PASS other wise report FAIL
		report.startLevel("Step 7 - ");
		gearBestSearchResultsPage.trueFlashSale(productAmount, ProductPrice, resultTitle);
		report.endLevel();


	}

	private int resultIndex() {
		Random objGenerator = new Random();
		int randomNumber = objGenerator.nextInt(10);
		return randomNumber;

	}
}
