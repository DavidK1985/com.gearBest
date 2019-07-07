package katzevman_David.com.gearBest.Infra.Pages;

import java.lang.reflect.Array;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import katzevman_David.com.gearBest.Infra.web.By2;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class GearBestLandingPage extends AbstractPage {
	
	private static final By2 newUserCoupon = new By2("Coupon on login", By.className("siteNewUser_content"));
	private static final By2 gearBestHomeButton = new By2("The Home Button", By.className("headLogo"));
	private static final By2 searchBox = new By2("Main search box", By.className("headSearch_formIpt"));
	private static final By2 goButton = new By2("'Go' button", By.id("js-btnSubmitSearch"));
	private static final By2 newUserCouponXButton = new By2 ("X Button", By.className("layui-layer-setwin"));
	private static final By2 superDealsBanner = new By2 ("Super deals banner", By.xpath("//div[@class='indexDeals_banner']"));
	
	

//	https://www.gearbest.com/flash-sale.html
//	private static final By2 signInSecurlyButtonBy = new By2("'Sign-in securely' button", By.id("a-autoid-0-announce"));
//	private static final By2 amazonMusicLink = new By2("'Amazon Music' link", By.xpath("//a[text()='Amazon Music']"));
	
	public GearBestLandingPage(WebDriver driver) throws Exception {
		super(driver,newUserCoupon);
	}
	
	//, gearBestHomeButton, searchBox, goButton
	public void closePopup() {
		if(bot.isElementDisplayed1(newUserCouponXButton)) {
			bot.click(newUserCouponXButton);
			}
		}

	public void writeToSearchbox(String searchTerm) {
		bot.writeToElement(searchBox, searchTerm);
	}
	
	public GearBestSearchResultsPage clickOnGoButton() throws Exception {
		bot.click(goButton);
		return new GearBestSearchResultsPage(driver);
	}
	
	public GearBestProductFlashSalePage clickSuperDealsBanner() throws Exception {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
        bot.click(superDealsBanner);
		return new GearBestProductFlashSalePage(driver);
	}
	
	public int mainCategoriesCount() {
		List<WebElement> mainCategoriesCounter = driver.findElements(By.xpath("//ul[@class='headCate']/li"));
		int CategoriesCount = mainCategoriesCounter.size();
		return CategoriesCount;
		}
	
	public By2[] creatMainCategoriesArray (int mainCategoriesCount) {
		By2[] mainCategories = new By2[mainCategoriesCount];
		for (int i = 0; i < mainCategoriesCount; i++) {
			By2 boxMainCategories = new By2 ("Main Categories shortcuts", By.xpath("//ul[@class='headCate']/li["+i+"]"));
			mainCategories[i] = boxMainCategories;
			System.out.println(mainCategories[i]);
			}
		return mainCategories;
	}
	
	public String choseRandomCategory(int resultIndex) throws Exception{
		int CategoriesCount = mainCategoriesCount();
		By2 [] mainCategories = creatMainCategoriesArray(CategoriesCount);
		By2 randomCategory = mainCategories[resultIndex];
		String chosenCategory = bot.getElementText(randomCategory);
		report.step("the randomly choshen category is: "+ chosenCategory);
		bot.click(randomCategory);
		return chosenCategory;
		
	}
//	
//	public void WaitForSignInSecurlyButtonEnabled() {
//		bot.waitForElementToBeClickable(signInSecurlyButtonBy);
//	}
	
//	public void clickAmazonMusicLink() {
//		bot.click(amazonMusicLink);
//	}
	

}
