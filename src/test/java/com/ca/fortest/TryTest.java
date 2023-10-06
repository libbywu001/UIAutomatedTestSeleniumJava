/**
 * @author libby
 * @date Oct. 4, 2023 10:34:50 p.m.
 * Description: 
 */
package com.ca.fortest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.ca.action.SeleniumAction;
import com.ca.drivers.SeleniumDriver;
import com.ca.logger.LoggerControler;

/**
 * DemoTest
 */
public class TryTest {
	static final LoggerControler log = LoggerControler.getLoggerControler(TryTest.class);

	@AfterMethod
	public void closeBrowers() {
		SeleniumDriver.closeBrowers();
	}

	@Test
	public void fortest() throws InterruptedException {
		log.info("===========begin=========");
		WebDriver driver = SeleniumDriver.openBrowers("chrome");
		driver.get("https://www.google.com");
		SeleniumAction.mySendKey(By.id("APjFqb"), "try my best");
		// name btnK
		SeleniumAction.myClick(By.name("btnK"));
		Thread.sleep(3000);
	}
}
