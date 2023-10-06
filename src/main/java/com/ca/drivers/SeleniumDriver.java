package com.ca.drivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author libby.Wu
 * @date 2023/08/04 Description:
 */
public class SeleniumDriver {
	public static WebDriver driver = null;

	public static WebDriver openBrowers(String browers) {
		String projectPath = System.getProperty("user.dir");
		if (browers.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver");
			driver = new ChromeDriver();
		} else if (browers.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.err.println("Browser type is incorrect or it is not supported yet!" + browers);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	public static void closeBrowers() {
		driver.quit();
	}
}
