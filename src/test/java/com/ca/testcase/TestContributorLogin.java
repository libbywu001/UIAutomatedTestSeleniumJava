package com.ca.testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ca.drivers.SeleniumDriver;
import com.ca.module.HomeModule;
import com.ca.module.LoginModule;
import com.ca.page.LoginPage;
import com.ca.seleniumUtils.SeleniumUtils;

/**
 * @author libby.wu
 * @date Oct. 11, 2023 10:07:36 a.m. Description: ebp login for contributors
 */
public class TestContributorLogin {
	private static String url = "https://ebp-test.ebuilding****.ca/signin";

	@BeforeMethod
	public void openBrowers() {
		HomeModule.openHome(url);
	}

	@AfterMethod
	public void closeBrowers() {
		SeleniumDriver.closeBrowers();
	}

	@DataProvider(name = "loginCorrect_contributor")
	public Object[][] getCorrectPara() {
		return new Object[][] { { "libbytest***@example.com", "Test@****", "libbytest***@example.com" },

		};
	}

	@Test(dataProvider = "loginCorrect_contributor", priority = 1)
	public void testCorrectLogin(String uname, String pwd, String exp) {
		LoginModule.login(uname, pwd);
		String act = SeleniumUtils.findElement(LoginPage.successLoginText).getText();
		Assert.assertEquals(act, exp);

	}
}
