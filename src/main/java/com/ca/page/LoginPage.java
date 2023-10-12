package com.ca.page;

import org.openqa.selenium.By;

/**
 * @author libby.Wu
 * @date 2023/08/04 Description: xx login
 */
public class LoginPage {
	// email
	public static By uname = By.name("email");
	// password
	public static By pwd = By.name("password");
	// remember me check box
	public static By rememberMeCheckBox = By.name("tos");
	// login button
	public static By loginButton = By.xpath("//*[@id=\"__next\"]/main/div/div[2]/form/button");
	// Register
	public static By register = By.linkText("Register");
	// Forgot your password?
	public static By forgotPassWord = By.linkText("Forgot your password?");
	//Page information after successful login
	public static By successLoginText = By.xpath("//*[@id=\"__next\"]/main/div[2]/div/div/div[2]/div[1]");

}
