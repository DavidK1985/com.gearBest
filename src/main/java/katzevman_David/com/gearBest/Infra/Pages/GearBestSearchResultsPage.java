package katzevman_David.com.gearBest.Infra.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.web.By2;

public class GearBestSearchResultsPage extends AbstractPage{

	private static final By2 gearBestHomeButton = new By2("The Home Button", By.className("headLogo"));
	private static final By2 amountOfResultsFound = new By2("Aamount of results found ", By.xpath("//h1[@class='cateMain_cateTitle']/span"));


	public GearBestSearchResultsPage(WebDriver driver) throws Exception {
		super(driver);
	}

	public void goToHomePage () {
		bot.click(gearBestHomeButton);
	}

	public String getSearchResultTitleByIndex(int resultIndex) throws Exception {

		By2 resultTitle = new By2("Title of search result #" + resultIndex, By.xpath("//ul[@class='clearfix js_seachResultList']//li[@data-index="+resultIndex+"]//p[@class='gbGoodsItem_titleInfor']"));
		
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
		searchTerm = searchTerm.toLowerCase();
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

	public int amountOfResults() {
		String value = bot.getElementText(amountOfResultsFound);
		String digits = value.replaceAll("[^0-9.]", "");
		int resultsfound = Integer.parseInt(digits);
		return resultsfound;
	}

	public void trueFlashSale(int resultsFound,Float ProductPrice, String resultTitle) {
		if(resultsFound==1)
			report.step("This product is one of a kind");
		else if (resultsFound>1) {
			for (int i = 0; i < resultsFound; i++) {
				String [] itemResults = new String [resultsFound];

				By2 tempResultTitle = new By2("product Title", By.xpath("//ul[@class='clearfix js_seachResultList' and not (@data-error-correct-name)]//li[@data-index="+i+"]//a[@title]"));
				itemResults[i] = bot.getElementText(tempResultTitle);

				itemResults[i]=itemResults[i].toLowerCase();
				resultTitle = resultTitle.toLowerCase();
				
				if (resultTitle.equalsIgnoreCase(itemResults[i])) {
					Float [] itemResultPrice = new Float [resultsFound];
					By2 tempResultPrice = new By2("product Title", By.xpath("//ul[@class='clearfix js_seachResultList' and not (@data-error-correct-name)]//li[@data-index=1]//p[@data-currency]"));
					String value1 = bot.getElementText(tempResultPrice);
					String digits1 = value1.replaceAll("[^0-9.]", "");
					float  newPrice = Float.valueOf(digits1);
					itemResultPrice[i] = newPrice;

					if(ProductPrice <= itemResultPrice[i]) {
						i++;	
					}
					else if(ProductPrice > itemResultPrice[i]) {
						report.step("The product "+itemResults[i]+" is similar to "+resultTitle+" and costs less");
						break;
					}
					
				}
				else {
					report.step("The product "+itemResults[i]+" is similar but not the same as "+resultTitle+" and costs differ as a result");
				}
			}
		}
	}


}
