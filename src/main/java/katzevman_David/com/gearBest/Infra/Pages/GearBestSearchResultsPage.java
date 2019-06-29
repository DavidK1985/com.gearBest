package katzevman_David.com.gearBest.Infra.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.web.By2;
import net.bytebuddy.asm.Advice.Return;

public class GearBestSearchResultsPage extends AbstractPage{

	private static final By2 gearBestHomeButton = new By2("The Home Button", By.className("headLogo"));



	public GearBestSearchResultsPage(WebDriver driver) throws Exception {
		super(driver, gearBestHomeButton);
	}

	public void goToHomePage () {
		bot.click(gearBestHomeButton);
	}

	public String getSearchResultTitleByIndex(int resultIndex) throws Exception {

		By2 resultTitle = new By2("Title of search result #" + resultIndex, By.xpath("//li[@data-index='" + resultIndex + "']"));
		return bot.getElementText(resultTitle);
	}

	public GearBestProductPage clickOnSearchResultTitle(int resultIndex) throws Exception {

		By2 randomResultchoice = new By2("results number "+ resultIndex +" in the search page" , By.xpath("//li[@data-index='" + resultIndex + "']"));
		bot.click(randomResultchoice);
		return new GearBestProductPage(driver);
	}

	public String ChosenProduct(int resultIndex) {
		By2 productName = new By2("The product name", By.xpath("//li[@data-index='"+resultIndex+"']//a[@title]"));
		String ChosenProduct = bot.getElementText(productName);
		report.step("The Headline found in the search results page is: "+ChosenProduct);
		return ChosenProduct;

	}




	public void comperResult(String resultTitle, String searchTerm) throws Exception {
		boolean comperResult= true;

		String result = resultTitle.toLowerCase();

		if(result.contains(searchTerm)) {
			comperResult=true;}
		else {
			comperResult=false;
		}

		if(comperResult == true) {
			report.step("The result contains the search term: "+ searchTerm);}
		else {
			report.step("The result does not contains the search term: "+ searchTerm);
		}
	}

}
