package katzevman_David.com.gearBest.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.GearBest_LandingPage;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_SignInPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;
import katzevman_David.com.gearBest.Infra.entities.personalDetails;


public class DataProviderFunctions extends AbstractTest{

	@Test(dataProvider = "csvParamsProvider")
	public void searchFromMainLandingPage(personalDetails email, personalDetails password) throws Exception{

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
		report.startLevel("Step 3 - ");
		GearBest_SignInPage gearBest_SignInPage = gearBestLandingPage.signIn();
		report.endLevel();

		// Step 4 - enter a random email
		report.startLevel("Step 4 - Enter email & password");
			gearBest_SignInPage.writeToEmailInputBox(email.email);
			gearBest_SignInPage.writeToPasswordInputBox(password.password);
			gearBest_SignInPage.clickSignInButton();	
		
		report.endLevel();

		//		AssertUtils.assertTrue(searchResultTitle.contains(searchItem.expectedResult), "Expecting to see '" + searchItem.expectedResult + "' in first result");
	}


	@DataProvider(name = "csvParamsProvider")
	public Object[][] dataProvider3() throws Exception {

		FileInputStream fstream = new FileInputStream("src/main/resources/config/products.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		int numOfLines = 0;
		String line;

		ArrayList<personalDetails> personalDetails = new ArrayList<personalDetails>();

		while ((line = br.readLine()) != null) {

			if (numOfLines > 0) {

				String[] splitStr = line.split(",");
				personalDetails personalDetail = new personalDetails(splitStr[0], (splitStr[1]));
				personalDetails.add(personalDetail);
			}

			numOfLines++;
		}

		br.close();

		Object[][] params = new Object[numOfLines-1][2];

		for (int i=0; i<numOfLines-1; i++) {
			params[i][0] = personalDetails.get(i);
			params[i][1] = personalDetails.get(i);

		}

		return params;
	}
}
