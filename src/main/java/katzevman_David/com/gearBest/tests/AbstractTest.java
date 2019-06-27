package katzevman_David.com.gearBest.tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import katzevman_David.com.gearBest.Infra.config.MainConfig;
import katzevman_David.com.gearBest.Infra.web.WebDriverFactory;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import il.co.topq.difido.model.Enums.Status;

@Listeners(il.co.topq.difido.ReportManagerHook.class)
public abstract class AbstractTest {

	protected static ReportDispatcher report = ReportManager.getInstance();
	protected static WebDriver driver;

	@BeforeMethod
	public void beforeTest() throws IOException {
		
		MainConfig.initFromFile("src/main/resources/config/MainConfig.properties");
		
		if (driver == null) {
			
			driver = WebDriverFactory.getWebDriver(MainConfig.webDriverType);
		}
	}
	
	public void browseToUrl(String url) {
		report.log("Browsing to URL: " + url);
		driver.get(url);
	}
	
	public static void takeScreenshot(String description) throws Exception {
		
		if (driver != null) {
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			report.addImage(screenshotFile, description);
		}
		else {
			report.log("driver == null; Can't take screenshot.", Status.warning);
		}
	}
	
	@AfterMethod
	public void afterTest() throws Exception {
		
		takeScreenshot("Browser state at test end");
//		MainConfig.closeBrowserAtTestEnd = true;
		if (driver != null && MainConfig.closeBrowserAtTestEnd) {
			driver.close();
		}
	}
}
