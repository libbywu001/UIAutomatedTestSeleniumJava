package com.ca.page;

import org.openqa.selenium.By;

/**
 * @author libby.wu
 * @date Oct. 11, 2023 9:34:21 a.m. Description: home page
 *       https://ebp-test.ebuilding***.ca/
 */
public class HomePage {
	// login entrance
	public static By login = By.linkText("Login");
	// sign up
	public static By signUp_rightUpperCorner = By.xpath("//*[@id=\"__next\"]/main/div/header/div/nav/div[2]/button");
	// sign up on the left
	public static By signUp_left = By.xpath("//*[@id=\"__next\"]/main/div/main/div[1]/div/div/button");

}
