package com.ca.action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.ca.drivers.SeleniumDriver;
import com.ca.seleniumUtils.SeleniumUtils;

/**
 * @author libby.Wu
 * @date 2023/08/04 Description:
 */
public class SeleniumAction extends SeleniumDriver {
	public static void myClick(By by) {
		SeleniumUtils.findElement(by).click();
		System.out.println("click-" + by);
	}

	public static void mySendKey(By by, String str) {
		WebElement element = SeleniumUtils.findElement(by);
		element.clear();
		element.sendKeys(str);
		System.out.println("enter-" + str);
	}

	public static void executeJS(By by, String script) {
		WebElement element = SeleniumUtils.findElement(by);
		((JavascriptExecutor) driver).executeScript(script, element);
		System.out.println("executeJS-" + by + ",script = " + script);
	}

	public static void executeJS(String script) {
		((JavascriptExecutor) driver).executeScript(script);
		System.out.println("executeJS- " + script);
	}
}
