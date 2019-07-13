package katzevman_David.com.gearBest.Infra.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.web.By2;

public class GearBest_SearchResultsPage extends AbstractPage{

	private static final By2 gearBestHomeButton = new By2("The Home Button", By.className("headLogo"));
	private static final By2 amountOfResultsFound = new By2("Aamount of results found ", By.xpath("//h1[@class='cateMain_cateTitle']/span"));
	private static final By2 noResultsFound = new By2("No results found ", By.xpath("//p[@class='noSearchWarp_keyWord']"));



	public GearBest_SearchResultsPage(WebDriver driver) throws Exception {
		super(driver);
	}

	public void goToHomePage () {
		bot.click(gearBestHomeButton);
	}

	public String getSearchResultTitleByIndex(int resultIndex) throws Exception {

		By2 resultTitle = new By2("Title of search result #" + resultIndex, By.xpath("//ul[@class='clearfix js_seachResultList']//li[@data-index="+resultIndex+"]//p[@class='gbGoodsItem_titleInfor']"));

		return bot.getElementText(resultTitle);
	}

	public GearBest_ProductPage clickOnSearchResultTitle(int resultIndex) throws Exception {

		By2 randomResultchoice = new By2("results number "+ resultIndex +" in the search page" , By.xpath("//li[@data-index='" + resultIndex + "']"));
		bot.click(randomResultchoice);
		return new GearBest_ProductPage(driver);
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
		if(bot.isElementDisplayed1(noResultsFound)) {
		report.step("No results found for this search");
		return 0;
		}
		String value = bot.getElementText(amountOfResultsFound);
		String digits = value.replaceAll("[^0-9.]", "");
		int resultsfound = Integer.parseInt(digits);
		return resultsfound;
	}

	public String titleShort(String title) {
		
		String[] words = title.split("\\W+"); 
		String finalResultsItem = "";
		for (int i = 0; i < words.length; i++) {
			  finalResultsItem = finalResultsItem+words[i]+" ";
			 if(i==3) {
				 return finalResultsItem;
			 } 
		}
		return null;
		
	}
	
	
	public void trueFlashSale(int resultsFound,Float ProductPrice, String resultTitle) {
		if(resultsFound==0) {
			report.step("This product '"+resultTitle+"' was not found using its own title");
		}
		else if(resultsFound==1) {
			report.step("This product is one of a kind");
		}
		else if (resultsFound>1) {
			report.step("If there are similar results we try to eliminate them as better or otherwise prove there is a better deal");
			
			
			
			for (int i = 0; i < resultsFound; i++) {
//				maybe impruve with a 2D array?				
				String [] itemResults = new String [resultsFound];
				for (int j = 0; j < resultsFound; j++) {
				By2 tempResultTitle = new By2("product Title", By.xpath("//ul[@class='clearfix js_seachResultList' and not (@data-error-correct-name)]//li[@data-index="+j+"]//a[@title]"));			
				itemResults[j] =  bot.getElementText(tempResultTitle);
			}
//				creating two shorter strings to compare the results by using a dedicated function and making them both
//				lower case to allow a better possibility of a match
			
				String itemResultsComper = titleShort(itemResults[i]);
				String resultTitleComper = titleShort(resultTitle);
				
				if (resultTitleComper.equalsIgnoreCase(itemResultsComper)) {
					report.step("The original product '"+resultTitle+"' has a similar name to this result: '"+itemResults[i]);
//					we will now compare there prices to see if it is a better deal
					Float [] itemResultPrice = new Float [resultsFound];
					
					for (int j = 0; j < resultsFound; j++) {
					By2 tempResultPrice = new By2("product Title", By.xpath("//ul[@class='clearfix js_seachResultList' and not (@data-error-correct-name)]//li[@data-index="+j+"]//p[@data-currency]"));
					String value = bot.getElementText(tempResultPrice);
					String digits = value.replaceAll("[^0-9.]", "");
					float  newPrice = Float.valueOf(digits);
					itemResultPrice[j] = newPrice;
					}
					
					if(ProductPrice <= itemResultPrice[i]) {
						i++;	
					}
					else if(ProductPrice > itemResultPrice[i]) {
						report.step("The product '"+itemResults[i]+"' is similar to '"+resultTitle+"' and costs less");
						break;
					}
				}
				else if(resultsFound>0){
					report.step("While we found similar products to '"+resultTitle+"' they are not the same and the costs differ as a result");
				}
			}
		}
	}


}
