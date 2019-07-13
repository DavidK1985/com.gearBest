package katzevman_David.com.gearBest.Infra.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import katzevman_David.com.gearBest.Infra.web.By2;

public class GearBest_LandingPage extends AbstractPage {

	private static final By2 newUserCoupon = new By2("Coupon on login", By.className("siteNewUser_content"));
	private static final By2 gearBestHomeButton = new By2("The Home Button", By.className("headLogo"));
	private static final By2 searchBox = new By2("Main search box", By.className("headSearch_formIpt"));
	private static final By2 goButton = new By2("'Go' button", By.id("js-btnSubmitSearch"));
	private static final By2 newUserCouponXButton = new By2 ("X Button", By.className("layui-layer-setwin"));
	private static final By2 superDealsBanner = new By2 ("Super deals banner", By.xpath("//div[@class='indexDeals_banner']"));
	private static final By2 SignInBy = new By2 ("Sign in icon", By.xpath("//span[@class='headUser_linkLogin']"));



	//	https://www.gearbest.com/flash-sale.html
	//	private static final By2 signInSecurlyButtonBy = new By2("'Sign-in securely' button", By.id("a-autoid-0-announce"));
	//	private static final By2 amazonMusicLink = new By2("'Amazon Music' link", By.xpath("//a[text()='Amazon Music']"));

	public GearBest_LandingPage(WebDriver driver) throws Exception {
		super(driver,newUserCoupon);
	}

	
	public void closePopup() {
		if(bot.isElementDisplayed1(newUserCouponXButton)) {
			bot.click(newUserCouponXButton);
		}
	}

	public void writeToSearchbox(String searchTerm) {
		bot.writeToElement(searchBox, searchTerm);
	}

	public GearBest_SearchResultsPage clickOnGoButton() throws Exception {
		bot.click(goButton);
		return new GearBest_SearchResultsPage(driver);
	}

	public GearBest_ProductFlashSalePage clickSuperDealsBanner() throws Exception {
		scrallDownPage();
		bot.click(superDealsBanner);
		return new GearBest_ProductFlashSalePage(driver);
	}

	public int mainCategoriesCount() {
		List<WebElement> mainCategoriesCounter = driver.findElements(By.xpath("//ul[@class='headCate']/li"));
		int CategoriesCount = mainCategoriesCounter.size();
		return CategoriesCount;
	}

	public By2[] createMainCategoriesArray (int mainCategoriesCount) {
		By2[] mainCategories = new By2[mainCategoriesCount];
		for (int i = 0; i < mainCategoriesCount; i++) {
			By2 boxMainCategories = new By2 ("Main Categories shortcuts", By.xpath("//ul[@class='headCate']/li["+i+"]"));
			mainCategories[i] = boxMainCategories;
			//			System.out.println(mainCategories[i]);
		}
		return mainCategories;
	}

	public String choseRandomCategory(int resultIndex) throws Exception{
		int CategoriesCount = mainCategoriesCount();
		By2 [] mainCategories = createMainCategoriesArray(CategoriesCount);
		By2 randomCategory = mainCategories[resultIndex];
		String chosenCategory = bot.getElementText(randomCategory);
		report.step("The randomly choshen category is: "+ chosenCategory);
		bot.click(randomCategory);
		return chosenCategory;

	}

	public ExternalLinks mobileIosApp() throws Exception {
		scrallDownPage();

		By2 mobileSiteLink = new By2 ("Icon for the external stores", By.xpath("//aside//i[@class='icon-qrcode']"));
		By2 iosLink = new By2 ("Button for the Apple store", By.xpath("//aside//a/img[@alt='App for ios']"));

		bot.hoverOverElement(mobileSiteLink);
		bot.click(iosLink);
		
		return new ExternalLinks(driver);

	}

	public ExternalLinks mobileAndroidApp() throws Exception{
		scrallDownPage();

		By2 mobileSiteLink = new By2 ("Icon for the external stores", By.xpath("//aside//i[@class='icon-qrcode']"));
		By2 androidLink = new By2 ("Button for the Goggle store", By.xpath("//aside//a/img[@alt='App for android']"));

		bot.hoverOverElement(mobileSiteLink);
		bot.click(androidLink);
		
		return new ExternalLinks(driver);
	}
	
	public GearBest_SignInPage signIn() throws Exception{
		
		
		while(bot.isElementDisplayed1(SignInBy)) {
			bot.hoverOverElement(SignInBy);			
			By2 signInButton = new By2 ("Button to move to the sign in page", By.xpath("//a[@class='headUser_btnLogin']"));
			bot.click(signInButton);
		}
		return new GearBest_SignInPage(driver);
	}


	//	
	//	public void WaitForSignInSecurlyButtonEnabled() {
	//		bot.waitForElementToBeClickable(signInSecurlyButtonBy);
	//	}

	//	public void clickAmazonMusicLink() {
	//		bot.click(amazonMusicLink);
	//	}


}
