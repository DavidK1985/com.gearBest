package katzevman_David.com.gearBest.Infra.utils;

import org.testng.Assert;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import il.co.topq.difido.model.Enums.Status;

public class AssertUtils {

	private static ReportDispatcher report = ReportManager.getInstance();
	
	public static void assertEquals(Object actual, Object expected, String message) {
		assertEquals(actual, expected, message, false);
	}
	
	public static void assertEquals(Object actual, Object expected, String message, boolean softAssert) {
		
		try {
			Assert.assertEquals(actual, expected, message);
			report.log(message + "; Both are equal to: " + expected + " - OK");
		}
		catch (AssertionError e) {
			
			report.log(e.getMessage(), Status.failure);

			if (!softAssert) {
				throw e;
			}
		}
	}
	
	
	public static void assertTrue(boolean condition, String message) {
		assertTrue(condition, message, false);
	}
	
	public static void assertTrue(boolean condition, String message, boolean softAssert) {
		
		try {
			Assert.assertTrue(condition, message);
			report.log(message + " - OK");
		}
		catch (AssertionError e) {
			
			report.log(e.getMessage(), Status.failure);

			if (!softAssert) {
				throw e;
			}
		}
	}
}
