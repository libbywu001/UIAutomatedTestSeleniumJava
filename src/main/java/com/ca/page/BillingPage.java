package com.ca.page;

import org.openqa.selenium.By;

/**
 * @author libby.wu
 * @date Oct. 11, 2023 10:53:49 a.m. Description: ebp billing
 */
public class BillingPage {
	// pending payments - first row
	public static By firstPendingPayments = By
			.xpath("//*[@id=\"__next\"]/main/div[2]/main/form[1]/div/div[3]/table/tbody/tr[1]");
	// pending payments - first details
	public static By details = By
			.xpath("//*[@id=\"__next\"]/main/div[2]/main/form[1]/div/div[3]/table/tbody/tr[1]/td[6]/button");
}
