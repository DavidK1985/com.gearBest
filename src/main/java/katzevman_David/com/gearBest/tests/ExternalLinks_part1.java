package katzevman_David.com.gearBest.tests;

import org.testng.annotations.Test;

import katzevman_David.com.gearBest.Infra.Pages.ExternalLinks;
import katzevman_David.com.gearBest.Infra.Pages.GearBest_LandingPage;
import katzevman_David.com.gearBest.Infra.config.MainConfig;

public class ExternalLinks_part1 extends AbstractTest {


	@Test (groups = {"Sanity"})
	public void _0_07_gearBestLinkTest1() throws Exception {
		// Step 1 - Browse to GearBest.com landing page
		report.startLevel("Step 1 - Browse to GearBest.com landing page");
		browseToUrl(MainConfig.baseUrl);
		GearBest_LandingPage gearBestLandingPage = new GearBest_LandingPage(driver);
		report.endLevel();

		// Step 2 - Close the coupon
		report.startLevel("Step 2 - Close the auto coupon in the landing page");
		gearBestLandingPage.closePopup();
		report.endLevel();

		// Step 3 - scrawling down and pressing the link
		report.startLevel("Step 3 - scrawl down and click on one of the side category");
		ExternalLinks externalLinks = gearBestLandingPage.mobileIosApp();
		report.endLevel();

		// Step 4 - confirmation
		report.startLevel("Step 4 - confirm the link sent us to the app store");
		externalLinks.externalSiteConfirmation("App store");
		report.endLevel();
	}


}
