package katzevman_David.com.gearBest.Infra.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;

import katzevman_David.com.gearBest.Infra.web.ActionBot;
import katzevman_David.com.gearBest.Infra.web.By2;

public abstract class AbstractPage {

	protected ReportDispatcher report = ReportManager.getInstance();
	protected WebDriver driver;
	protected ActionBot bot;
	protected By2[] pageUniqueElements;
	
	

//
//	//universal
//	protected final By2 toTopOfPage = new By2("Side categories menu 1", By.xpath("//aside//i[@class='icon-top1']"));
//	protected final By2 MobileSiteLink = new By2("Side categories menu 8", By.xpath("//aside//i[@class='icon-qrcode']"));
//	protected final By2 MobileSiteLinkIos = new By2("Side categories menu 8", By.xpath("//aside//div[@class='siteAside_appImg']/a/img[@alt='App for ios']"));
//	protected final By2 MobileSiteLinkAndroid = new By2("Side categories menu 8", By.xpath("//aside//div[@class='siteAside_appImg']/a/img[@alt='App for android']"));
//	//found in landing page
//	protected final By2 collection = new By2("Side categories menu 2", By.xpath("//aside//i[@class='icon-bag2']"));
//	protected final By2 superDeals = new By2("Side categories menu 3", By.xpath("//aside//i[@class='icon-deals']"));
//	protected final By2 New = new By2("Side categories menu 4", By.xpath("//aside//i[@class='icon-new']"));
//	protected final By2 topBrands = new By2("Side categories menu 5", By.xpath("//aside//i[@class='icon-brands']"));
//	protected final By2 hotCategories = new By2("Side categories menu 6", By.xpath("//aside//i[@class='icon-cate2']"));
//	protected final By2 errorReport = new By2("Side categories menu 7", By.xpath("//aside//i[@class='icon-edit']"));
	
	public AbstractPage(WebDriver driver, By2... pageUniqueElements) throws Exception {
		this.driver = driver;
		this.bot = new ActionBot(driver);
		this.pageUniqueElements = pageUniqueElements;
		assertInPage();
	}
	
	
	public void assertInPage() throws Exception {
		
		WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
		
		try {
			for (By2 uniqueElement : pageUniqueElements) {
				webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(uniqueElement.by));
			}
			report.log("*** On page: " + this.getClass().getSimpleName());
		}
		catch (TimeoutException ex) {
			throw new Exception("Not on the expected page: " + this.getClass().getSimpleName() + "\n" + ex.getMessage());
		}
	}
	
	public void scrallDownPage() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
	}
}

