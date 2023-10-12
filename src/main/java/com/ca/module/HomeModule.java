package com.ca.module;

import com.ca.drivers.SeleniumDriver;

/**
 * @author libby.wu
 * @date Oct. 11, 2023 9:24:27 a.m. Description: ebp-homeModule
 */
public class HomeModule {
	// open homepage
	public static void openHome(String url) {
		SeleniumDriver.openBrowers("chrome").get(url);
	}
}
