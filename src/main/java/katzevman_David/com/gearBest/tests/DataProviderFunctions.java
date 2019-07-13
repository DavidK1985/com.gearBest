package katzevman_David.com.gearBest.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.utils.AssertUtils;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_LandingPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_SearchResultsPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;
import katzevman_David.com.gearBest.Infra.entities.SearchItem;


public class DataProviderFunctions extends AbstractTest{

	@Test(dataProvider = "csvParamsProvider")
	public void searchFromMainLandingPage(SearchItem searchItem) throws Exception{
		browseToUrl(MainConfig.baseUrl);

		GearBest_LandingPage gearBestLandingPage = new GearBest_LandingPage(driver);
		gearBestLandingPage.closePopup();
		gearBestLandingPage.writeToSearchbox(searchItem.searchTerm);
		
		GearBest_SearchResultsPage gearBestSearchResultsPage = gearBestLandingPage.clickOnGoButton();
		takeScreenshot("\"" + searchItem.searchTerm + "\" - Search results");
		
		String searchResultTitle = gearBestSearchResultsPage.getSearchResultTitleByIndex(searchItem.itemIndex);
		
		AssertUtils.assertTrue(searchResultTitle.contains(searchItem.expectedResult), "Expecting to see '" + searchItem.expectedResult + "' in first result");
	}

//	@Test(dataProvider = "simpleParamsProvider")
//	public void searchFromMainLandingPage1(String searchTerm, int index, String expected) {
//		
//		report.log("searchTerm = " + searchTerm);
//		report.log("itemIndex = " + index);
//		report.log("expectedResult = " + expected);
//	}
//	
//	@DataProvider(name = "simpleParamsProvider")
//	public Object[][] dataProvider1() {
//	
//		Object[][] params = new Object[2][3];
//		
//		params[0][0] = "IPhone";
//		params[0][1] = 3;
//		params[0][2] = "IPhone 6";
//		
//		params[1][0] = "Galaxy";
//		params[1][1] = 4;
//		params[1][2] = "Galaxy S9+";
//
//		return params;
//	}
//	
//	@DataProvider(name = "objectParamsProvider")
//	public Object[][] dataProvider2() {
//		
//		Object[][] params = new Object[3][1];
//		
//		SearchItem searchItem1 = new SearchItem("IPhone", 3, "IPhone 6");
//		SearchItem searchItem2 = new SearchItem("Galaxy", 4, "Galaxy S9+");
//		SearchItem searchItem3 = new SearchItem("Nokia", 1, "Nokia 3200");
//		
//		params[0][0] = searchItem1;
//		params[1][0] = searchItem2;
//		params[2][0] = searchItem3;
//
//		return params;
//	}
	
	@DataProvider(name = "csvParamsProvider")
	public Object[][] dataProvider3() throws Exception {
		
		FileInputStream fstream = new FileInputStream("src/main/resources/config/products.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		int numOfLines = 0;
		String line;

		ArrayList<SearchItem> searchItems = new ArrayList<SearchItem>();
		
		while ((line = br.readLine()) != null) {
			
			if (numOfLines > 0) {
				
				String[] splitStr = line.split(",");
				SearchItem searchItem = new SearchItem(splitStr[0], Integer.parseInt(splitStr[1]), splitStr[2]);
				searchItems.add(searchItem);
			}
			
			numOfLines++;
		}
		
		br.close();
		
		Object[][] params = new Object[numOfLines-1][1];
		
		for (int i=0; i<numOfLines-1; i++) {
			params[i][0] = searchItems.get(i);
		}

		return params;
	}
}
