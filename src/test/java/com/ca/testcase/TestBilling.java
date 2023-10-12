package com.ca.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ca.drivers.SeleniumDriver;
import com.ca.module.BillingModule;
import com.ca.module.LoginModule;
import com.ca.page.OrderDetailsPage;
import com.ca.seleniumUtils.SeleniumUtils;

/**
 * @author libby.wu
 * @date Oct. 11, 2023 11:06:47 a.m. Description: ebp billing
 */
public class TestBilling {
	private static String url = "https://ebp-test.ebuilding****.ca/dashboard/billing";
	WebDriver driver;

	@BeforeClass
	public void openBrowers() throws InterruptedException {
		// Initialize WebDriver instance
		driver = SeleniumDriver.openBrowers("chrome");
		driver.get(url);
		LoginModule.login("libbytest***@example.com", "Test@***");
		Thread.sleep(3000);
	}

	@AfterClass
	public void closeBrowers() {
		SeleniumDriver.closeBrowers();
	}

	//@Test
	public void testScrollPage() throws InterruptedException {
		System.out.println("======testScrollPage()-executeJS=====");
		driver.get(url);
		BillingModule.scrollPage();
		Thread.sleep(5000);
	}

	// !Use dependsOnMethods to define the order of use case execution
	//@Test(dependsOnMethods = "testScrollPage")
	@Test
	public void testDetails() throws InterruptedException {
		System.out.println("======testDetails()-executeJS=====");
		driver.get(url);
		Thread.sleep(6000);
		BillingModule.checkDetails();
		Thread.sleep(3000);
		String act = SeleniumUtils.findElement(OrderDetailsPage.billDetailMessages).getText();
		System.out.println("======testDetails()-act:" + act);
		// TODO: assert
		Assert.assertEquals(act, "Current Order Details");
	}
}
