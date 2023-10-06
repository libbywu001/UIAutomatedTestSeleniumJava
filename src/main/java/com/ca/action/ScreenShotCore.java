package com.ca.action;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import com.ca.drivers.SeleniumDriver;
import com.ca.logger.LoggerControler;
import com.ca.utils.CustomFile;
import com.ca.utils.DateFormatUtil;
import com.google.common.io.Files;

/**
 * @author libby.wu
 * @date 2023/10/02 Description: ScreenShotCore
 */
public class ScreenShotCore extends SeleniumDriver {
	static final LoggerControler log = LoggerControler.getLoggerControler(ScreenShotCore.class);
	static String path = System.getProperties().getProperty("user.dir") + "/error/";

	/**
	 * Error screenshots, screenshots named by date
	 **/
	public static void screenShots() {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		// CHECK_LOG_FORMAT = "yyyyMMdd";REPORT_CSV_FORMAT = "yyyyMMdd_HHmmss";
		CustomFile myFile = new CustomFile();
		String myPath = path + DateFormatUtil.format(DateFormatUtil.CHECK_LOG_FORMAT);
//        System.out.print(myPath);
		myFile.createFile1(myPath);
		try {
			String times = String.valueOf(System.currentTimeMillis());
			Files.copy(file, new File(myPath + "/" + DateFormatUtil.format(DateFormatUtil.REPORT_CSV_FORMAT) + ".jpg"));
			// FileUtils.copyFile(file,new File(myPath + "/" +
			// DateFormat.format(DateFormat.REPORT_CSV_FORMAT) + ".png"));
		} catch (IOException e) {
			log.error("Screenshot failed!");
			e.printStackTrace();
		}
	}

	/**
	 * Error screenshot, name the screenshot by passing in name
	 **/
	public static void screenShots1(String name) {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		try {
			CustomFile myFile = new CustomFile();
			myFile.createFile1(path + DateFormatUtil.format(DateFormatUtil.CHECK_LOG_FORMAT));
			log.info(DateFormatUtil.format(DateFormatUtil.ZH_DATE_FORMAT));
			Files.copy(file,
					new File(path + DateFormatUtil.format(DateFormatUtil.CHECK_LOG_FORMAT) + "/" + name + ".jpg"));
		} catch (IOException e) {
			log.error("截图失败！！");
			e.printStackTrace();
		}
	}
}
