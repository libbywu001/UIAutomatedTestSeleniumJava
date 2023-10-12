package com.ca.module;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.ca.action.SeleniumAction;
import com.ca.page.CreateNewProjectPage;

/**
 * @author libby.wu
 * @date Oct. 11, 2023 10:27:14 p.m. Description:
 */
public class CreateProjectModule {
	public static void createNewProj(String projectPic, String projectFile, String projectName, String projectDesc,
			String addressPre, String landSizeInput, String livingAreaInput, String ceilingHeightInput) {
		// upload file
		SeleniumAction.mySendKey(CreateNewProjectPage.uploadPic, projectPic);
		SeleniumAction.mySendKey(CreateNewProjectPage.upFiles, projectFile);
		SeleniumAction.mySendKey(CreateNewProjectPage.projectName, projectName);
		SeleniumAction.mySendKey(CreateNewProjectPage.projectDescription, projectDesc);
		// special handling
		// After waiting for the alternative content to appear, the first data input is
		// selected by default.
		SeleniumAction.mySendKey(CreateNewProjectPage.addressInfo, addressPre);

		SeleniumAction.mySendKey(CreateNewProjectPage.landSizeInput, landSizeInput);

		SeleniumAction.mySendKey(CreateNewProjectPage.livingAreaInput, livingAreaInput);

		SeleniumAction.mySendKey(CreateNewProjectPage.ceilingHeightInput, ceilingHeightInput);
		SeleniumAction.myClick(CreateNewProjectPage.submitButton);

	}

	// special
	public static void createNewProjOnlyInput(String projectPic, String projectFile, String projectName,
			String projectDesc, String addressPre, String landSizeInput, String livingAreaInput,
			String ceilingHeightInput, String buildingHeightUnit,WebDriver driver) throws InterruptedException {
		// upload file
		SeleniumAction.mySendKey(CreateNewProjectPage.upFiles, projectFile);
		SeleniumAction.mySendKey(CreateNewProjectPage.projectName, projectName);
		SeleniumAction.mySendKey(CreateNewProjectPage.projectDescription, projectDesc);
		// address
		SeleniumAction.mySendKey(CreateNewProjectPage.addressInfo, addressPre);
		Thread.sleep(8000);
		driver.findElement(CreateNewProjectPage.addressInfo).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(CreateNewProjectPage.addressInfo).sendKeys(Keys.ENTER);
		System.out.println("========== look here ===========");
		Thread.sleep(3000);

		// SeleniumAction.mySendKey(CreateNewProjectPage.landSizeInput, landSizeInput);
		SeleniumAction.mySendKeyAfterDoubleClickToClean(CreateNewProjectPage.landSizeInput, landSizeInput, driver);

		// SeleniumAction.mySendKey(CreateNewProjectPage.livingAreaInput,
		// livingAreaInput);
		SeleniumAction.mySendKeyAfterDoubleClickToClean(CreateNewProjectPage.livingAreaInput, livingAreaInput, driver);


		//drop down box
		//SeleniumAction.DropdownboxAndSelection(CreateNewProjectPage.ceilingHeightInput, buildingHeightUnit, driver);
		SeleniumAction.mySendKeyAfterDoubleClickToClean(CreateNewProjectPage.ceilingHeightInput, ceilingHeightInput,
				driver);
		// SeleniumAction.myClick(CreateNewProjectPage.submitButton);

		SeleniumAction.myClick(CreateNewProjectPage.submitButton);
	}
}
