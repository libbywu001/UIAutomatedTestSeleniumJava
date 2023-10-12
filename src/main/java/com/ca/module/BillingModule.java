package com.ca.module;

import com.ca.action.SeleniumAction;
import com.ca.page.BillingPage;

/**
 * @author libby.wu
 * @date Oct. 11, 2023 10:53:20 a.m. Description: ebp billing
 */
public class BillingModule {
	// Scroll the page to the bottom
	public static void scrollPage() {
		SeleniumAction.executeJS("window.scrollTo(0, document.body.scrollHeight)");

	}

	public static void checkDetails() {
		SeleniumAction.executeJS(BillingPage.details, "arguments[0].click();");

	}
}
