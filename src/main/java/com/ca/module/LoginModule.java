package com.ca.module;

import com.ca.action.SeleniumAction;
import com.ca.drivers.SeleniumDriver;
import com.ca.page.LoginPage;

/**
 * @author libby.Wu
 * @date 2023/08/04 Description:
 */
public class LoginModule extends SeleniumDriver {
	public static void login(String uname, String pwd) {

		SeleniumAction.mySendKey(LoginPage.uname, uname);
		SeleniumAction.mySendKey(LoginPage.pwd, pwd);
       SeleniumAction.myClick(LoginPage.loginButton);
		// SeleniumAction.executeJS(LoginPage.login,"arguments[0].click();");
	}
}
