package katzevman_David.com.gearBest.Infra.web;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;

public class TabAndIframeUtils {

private static ReportDispatcher report = ReportManager.getInstance();
	
	public static void switchToTabByTitle(WebDriver driver, String title) throws Exception {
		
		Set<String> windowHandles = driver.getWindowHandles();
		boolean foundTab = false;
		
		for (String windowHandle : windowHandles) {
			
			driver.switchTo().window(windowHandle);
			
			if (driver.getTitle().equals(title)) {
				foundTab = true;
				break;
			}
		}
		
		if (foundTab) {
			report.log("Switched to tab with title: '" + title + "'");
		}
		else {
			throw new Exception("Tab with title: '" + title + "' wasn't found!");
		}
	}
	
	
	public static void switchToTabByUrl(WebDriver driver, String url) throws Exception {
		
		Set<String> windowHandles = driver.getWindowHandles();
		boolean foundTab = false;
		
		for (String windowHandle : windowHandles) {
			
			driver.switchTo().window(windowHandle);
			
			if (driver.getCurrentUrl().contains(url)) {
				foundTab = true;
				break;
			}
		}
		
		if (foundTab) {
			report.log("Switched to tab with URL containing: '" + url + "'");
		}
		else {
			throw new Exception("Tab with URL containing: '" + url + "' wasn't found!");
		}
	}
	
	public static void switchToIframe(WebDriver driver, By iframeBy) {
		
		WebElement ifrmaeElement = driver.findElement(iframeBy);
		driver.switchTo().frame(ifrmaeElement);
		report.log("Switched to iframe: " + iframeBy);
	}
}
