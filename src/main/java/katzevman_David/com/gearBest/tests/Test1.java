package katzevman_David.com.gearBest.tests;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.GearBestLandingPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;


public class Test1 extends AbstractTest {
	
	private String searchTerm;
	// search something on gear best
	@Test
	public void gearBestLandingPageSearch()  throws Exception {
		
		initTestParams();
		// Step 1 - Browse to GearBest.com landing page
				report.startLevel("Step 1 - Browse to GearBest.com landing page");
				browseToUrl(MainConfig.baseUrl);
				GearBestLandingPage gearBestLandingPage = new GearBestLandingPage(driver);
				gearBestLandingPage.closePopup();
				report.endLevel();
				
		// Step 2 - Write a random search term in the top search bar and click the search button
				report.startLevel("Step 2 - Write a random search term in the top search bar");
				gearBestLandingPage.writeToSearchbox(searchTerm);
				gearBestLandingPage.clickOnGoButton();
				report.endLevel();
				
			// Step 3 - compare the results to the search term		
//				report.startLevel("Step 3 - count how manny results had the search term");
//
//
//				report.endLevel();

	}


	private void initTestParams() throws Exception {
		
		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/resources/config/shoppingCartTest.properties");

		prop.load(input);
		searchTerm = prop.getProperty("searchTerm");
//		while (searchTerm==null){
//		searchTerm = prop.getProperty("searchTerm");
//		ArrayList searchList = new ArrayList();
//		int i=0;
//		
//		searchList.add(i, searchTerm);
//		i++;
//		
//		}
	}
}