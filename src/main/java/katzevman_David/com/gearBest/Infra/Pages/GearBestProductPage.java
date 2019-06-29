package katzevman_David.com.gearBest.Infra.Pages;

import static org.testng.Assert.assertEquals;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.web.By2;

public class GearBestProductPage extends AbstractPage {


	private static final By2 currentPrice = new By2("The current price for the item", By.xpath("//div[@class='goodsIntro_priceWrap']/span[@data-currency]"));
	private static final By2 pastPrice = new By2("The old price for the item", By.xpath("//div[@class='goodsIntro_priceWrap']/del[@data-currency]"));
	private static final By2 shownDiscount = new By2("The Discount shown for this item", By.xpath("//div[@class='goodsIntro_priceWrap']/span[@class='goodsIntro_discount']"));
	private static final By2 productTitle= new By2("The product name", By.xpath("//div[@class='goodsIntro_titleBox']"));
	private static final By2 productTitleAddOn= new By2("The product name", By.xpath("//span[@class='goodsIntro_title-prop']"));
	private static final By2 gearBestHomeButton = new By2("The Home Button", By.className("headLogo"));
	
	public GearBestProductPage(WebDriver driver) throws Exception {
		super(driver);
	}

	public void discountCheck() throws Exception {

		if(currentPrice == pastPrice) {
			report.step("This result does not contains a discount");}
		else {
			String value1 = bot.getElementText(currentPrice);
			String digits1 = value1.replaceAll("[^0-9.]", "");
			float  newPrice = Float.valueOf(digits1);

			String value2 = bot.getElementText(pastPrice);
			String digits2 = value2.replaceAll("[^0-9.]", "");
			float  oldPrice = Float.valueOf(digits2);

			float discount =newPrice / oldPrice *100;
			discount = 100-discount;
			discount = Math.round(discount);
			int finalDiscount = (int)discount;
			report.step("The Price for this product contains a discount of: "+finalDiscount+"%");
		}

	}

	public void nameComperison(String resultsPageProductName) {
		String productPageHeader = bot.getElementText(productTitle);
		String productPageHeaderAddOn = bot.getElementText(productTitleAddOn);
		if (productPageHeader.equalsIgnoreCase(resultsPageProductName)) {
			report.step("The headline from the results page is identical to the product page");
		}
		else {
			report.step("The headline from the results page is not identical to the product page," 
		+"the diffrens between them is the added word: "+productPageHeaderAddOn+" in the product page headline");

		}

	}
	
	public void backToHomePage() {
		bot.click(gearBestHomeButton);
	}


}