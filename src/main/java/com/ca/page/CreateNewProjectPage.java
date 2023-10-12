package com.ca.page;

import org.openqa.selenium.By;

/**
 * @author libby.wu
 * @date Oct. 11, 2023 10:06:29 p.m. Description:
 */
public class CreateNewProjectPage {
	// upload a picture
	public static By uploadPic = By
			.xpath("//*[@id=\"__next\"]/main/div[2]/main/div/div/form/div/div[1]/div/div[1]/div[2]/div/div[2]/button");
	// upload blueprint
	public static By upFiles = By
			.xpath("//*[@id=\"__next\"]/main/div[2]/main/div/div/form/div/div[1]/div/div[2]/div[2]/div/div/input");
	// project name
	public static By projectName = By.name("projectName");
	// projectDescription
	public static By projectDescription = By.name("projectDescription");
	// address
	public static By addressInfo = By.id("react-select-search_address-input");
	// landSizeInput
	public static By landSizeInput = By.name("landSizeInput");
	// livingAreaInput
	public static By livingAreaInput = By.name("livingAreaInput");
	// ceilingHeightInput
	public static By ceilingHeightInput = By.name("ceilingHeightInput");
	// submitButton
	public static By submitButton = By
			.xpath("//*[@id=\"__next\"]/main/div[2]/main/div/div/form/div/div[2]/div[2]/div[9]/button");
	//
	public static By submitSuccessMessage = By
			.xpath("//*[@id=\"__next\"]/main/div[2]/main/div/div/div/div/div[2]/div[2]/div/div/p[1]");

}
