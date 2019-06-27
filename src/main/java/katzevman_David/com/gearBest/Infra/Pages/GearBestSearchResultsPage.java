package katzevman_David.com.gearBest.Infra.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.web.By2;

public class GearBestSearchResultsPage extends AbstractPage{

	private static final By2 gearBestHomeButton = new By2("The Home Button", By.className("headLogo"));
	


	public GearBestSearchResultsPage(WebDriver driver) throws Exception {
		super(driver, gearBestHomeButton);
	}

	public void goToHomePage () {
		bot.click(gearBestHomeButton);
	}

//	public int countSearchResults () {
//		int counter= By.className("es_hl_color")); ;
//	}

}
