package katzevman_David.com.gearBest.Infra.Pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import katzevman_David.com.gearBest.Infra.entities.Product;
import katzevman_David.com.gearBest.Infra.web.By2;

public class GearBestProductFlashSalePage extends AbstractPage{

	private static final By2 searchBox = new By2("Main search box", By.className("headSearch_formIpt"));

	public GearBestProductFlashSalePage(WebDriver driver) throws Exception {
		super(driver);
	}

	public String getSearchResultTitleByIndex(int resultIndex) throws Exception {
		By2 ProductTitle = new By2("The name the of the active item sale", By.xpath("//li[@data-index="+resultIndex+"]//div[@class='goodsItem_title']"));
		return bot.getElementText(ProductTitle);
	}

	public float getSearchResultPriceByIndex(int resultIndex) throws Exception {
		By2 ProductPrice = new By2("The current price for the item on sale", By.xpath("//li[@data-index="+resultIndex+"]//div[@class='goodsItem_detail']"));
		String value = bot.getElementText(ProductPrice);
		String digits = value.replaceAll("[^0-9.]", "");
		float  resultsfound = Float.valueOf(digits);
		return resultsfound;
	}
	
	public int getSearchResultDiscountByIndex(int resultIndex) throws Exception {
		By2 ProductDiscount = new By2("The current price for the item", By.xpath("//li[@data-index="+resultIndex+"]//span[@class='goodsItem_discount']"));
		String value = bot.getElementText(ProductDiscount);
		String digits = value.replaceAll("[^0-9.]", "");
		int resultsfound = Integer.parseInt(digits);
		return resultsfound;
	}

	public void writeToSearchbox(String resultTitle) {
		bot.writeToElement(searchBox, resultTitle);
	}
}
