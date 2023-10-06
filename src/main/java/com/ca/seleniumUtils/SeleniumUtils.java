package com.ca.seleniumUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ca.drivers.SeleniumDriver;

/**
 * @author libby.Wu
 * @date 2023/08/04 Description:
 */
public class SeleniumUtils extends SeleniumDriver {
	public static WebElement findElement(final By by) {
		WebElement element = null;
		try {
			element = new WebDriverWait(driver, 30).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}
			});
		} catch (Exception e) {
			System.out.println("Element-" + by + "lookup timeout");
			e.printStackTrace();
		}
		return element;
	}

	public static List<WebElement> findElements(final By by) {
		List<WebElement> elements = null;
		try {
			elements = new WebDriverWait(driver, 30).until(new ExpectedCondition<List<WebElement>>() {
				public List<WebElement> apply(WebDriver driver) {
					return driver.findElements(by);
				}
			});
		} catch (Exception e) {
			System.out.println("Element-" + by + " failed");
			e.printStackTrace();
		}
		return elements;
	}

	public static boolean isexist(final By by) {
		boolean flag = false;
		try {
			SeleniumUtils.findElement(by);
			flag = true;
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
