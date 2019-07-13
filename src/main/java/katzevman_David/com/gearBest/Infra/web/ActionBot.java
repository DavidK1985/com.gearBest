package katzevman_David.com.gearBest.Infra.web;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;

public class ActionBot {

	private ReportDispatcher report = ReportManager.getInstance();
	private WebDriver driver;
	private WebDriverWait webDriverWait;
	
	public ActionBot(WebDriver driver) {
		this.driver = driver;
		webDriverWait = new WebDriverWait(driver, 5);
	}
	
	public void click(By2 elementLocator) {

		report.log("Click on element: " + elementLocator);
		waitForElementToBeClickable(elementLocator);
		WebElement element = driver.findElement(elementLocator.by);
		//executeJavaScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}
	
	public void executeJavaScript(String javaScript, WebElement element) {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		if (element != null) {
			jsExecutor.executeScript(javaScript, element);
		}
		else {
			jsExecutor.executeScript(javaScript);
		}
	}
	
	public void writeToElement(By2 elementLocator, String text) {

		report.log("Write '" + text + "' to element: " + elementLocator);
		WebElement element = driver.findElement(elementLocator.by);
		element.clear();
		element.sendKeys(text);
	}
	
	public String getElementText(By2 elementLocator) { 
		String text = driver.findElement(elementLocator.by).getText();
		report.log("Element " + elementLocator + " inner text: " + text);
		return text;
	}
	
	public void waitForElement(By2 elementLocator) {
		report.log("Wait for element: " + elementLocator);
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(elementLocator.by));
	}
	
	public void waitForElementToBeClickable(By2 elementLocator) {
		report.log("Wait for element: " + elementLocator + " to be clickable");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(elementLocator.by));
	}
	
	public boolean isElementDisplayed1(By2 elementLocator) {
	
		List<WebElement> elements = driver.findElements(elementLocator.by);
		if (elements.size() > 0) {
			
			boolean isDisplayed = elements.get(0).isDisplayed();
			report.log("Element " + elementLocator + " is displayed: " + isDisplayed);
			return isDisplayed;
		}
		
		report.log("Element " + elementLocator + " is NOT displayed");
		return false;
	}
	
	public boolean isElementDisplayed2(By2 elementLocator) {
		
		try {
			WebElement xButton = driver.findElement(elementLocator.by);
			return xButton.isDisplayed();
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public void hoverOverElement(By2 elementLocator) {
		
		report.log("Hover over element: " + elementLocator);
		
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(elementLocator.by);
		action.moveToElement(element).build().perform();
	}
}

