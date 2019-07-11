package katzevman_David.com.gearBest.tests;

import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.GearBestLandingPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;

public class Test7 extends AbstractTest {
		
		
		@Test (groups = {"Regression"})
		public void _0_06_gearBestLinkTest1() throws Exception {
			// Step 1 - Browse to GearBest.com landing page
			report.startLevel("Step 1 - Browse to GearBest.com landing page");
			browseToUrl(MainConfig.baseUrl);
			GearBestLandingPage gearBestLandingPage = new GearBestLandingPage(driver);
			report.endLevel();

			// Step 2 - Close the coupon
			report.startLevel("Step 2 - Close the auto coupon in the landing page");
			gearBestLandingPage.closePopup();
			report.endLevel();
			
			// Step 3 - scrawling down and pressing the link
			report.startLevel("Step 3 - scrawl down and click on one of the side category");
			gearBestLandingPage.mobileAndroidApp();

			report.endLevel();
		}
		
		
}
