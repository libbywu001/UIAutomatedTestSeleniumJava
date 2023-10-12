package com.ca.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ca.drivers.SeleniumDriver;
import com.ca.module.CreateProjectModule;
import com.ca.module.LoginModule;
import com.ca.page.CreateNewProjectPage;
import com.ca.seleniumUtils.SeleniumUtils;

/**
 * @author libby.wu
 * @date Oct. 11, 2023 10:37:10 p.m. Description: ebp for test
 */
public class TestCreatNewProj {
	private static String url = "https://ebp-test.ebuilding****.ca/dashboard/blueprint";
	WebDriver driver;

	@BeforeClass
	public void openBrowers() throws InterruptedException {
		// Initialize WebDriver instance
		driver = SeleniumDriver.openBrowers("chrome");
		driver.get(url);
		LoginModule.login("libbytest***@example.com", "Test@****");
		Thread.sleep(3000);
	}

	@AfterClass
	public void closeBrowers() {
		SeleniumDriver.closeBrowers();
	}

	@DataProvider(name = "CreateNewProject_success")
	public Object[][] getCorrectPara() {
		return new Object[][] { { System.getProperty("user.dir") + "/data/projectpic.jpg",
				System.getProperty("user.dir") + "/data/21-411 4403 20th St - Bldg 4.dwg",
				String.valueOf(System.currentTimeMillis()), "for test", "1138", "10000", "2000", "8", "Meter",
				"Our team is currently verifying your blueprint!" },

		};
	}

	@Test(dataProvider = "CreateNewProject_success", priority = 1)
	public void testCreateNewPro(String projectPic, String projectFile, String projectName, String projectDesc,
			String addressPre, String landSizeInput, String livingAreaInput, String ceilingHeightInput,
			String buildingHeightUnit, String exp) throws InterruptedException {

		driver.get(url);
		CreateProjectModule.createNewProjOnlyInput(projectPic, projectFile, projectName, projectDesc, addressPre,
				landSizeInput, livingAreaInput, ceilingHeightInput, buildingHeightUnit, driver);
		Thread.sleep(3000);
		String act = SeleniumUtils.findElement(CreateNewProjectPage.submitSuccessMessage).getText();
		Assert.assertEquals(act, exp);
	}

}
