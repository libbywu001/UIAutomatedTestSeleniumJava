package com.ca.action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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

	// Double click to clear and enter
	public static void mySendKeyAfterDoubleClickToClean(By by, String str, WebDriver driver) {
		Actions actions = new Actions(driver);
		WebElement element = SeleniumUtils.findElement(by);
		actions.click(element).build().perform();
		// Double-click the mouse to select the default value
		actions.doubleClick(element).build().perform();
		// Press the Delete key with the mouse to clear the default value
		actions.sendKeys(Keys.DELETE).build().perform();
		// Enter new value
		element.sendKeys(str);
		System.out.println("mySendKeyAfterDoubleClickToClean()-" + str);
	}
	//drop down box
	public static void DropdownboxAndSelection(By by, String str,WebDriver driver) {
		WebElement element = SeleniumUtils.findElement(by);
        Select sel = new Select(element);
        sel.selectByValue(str);
		System.out.println("DropdownboxAndSelection-" + by + ",str = " + str);
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
