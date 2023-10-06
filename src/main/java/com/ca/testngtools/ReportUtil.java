package com.ca.testngtools;

import java.util.Calendar;
import org.testng.Reporter;

/**
 * @author libby.wu
 * @date 2023/10/03
 * Description: 
 */
public class ReportUtil {
	   private static String reportName = "WebUI-Automated test report";
	    private static String splitTimeAndMsg = "=======";
	    public static void log(String msg) {
	        long timeMillis = Calendar.getInstance().getTimeInMillis();
	        Reporter.log(timeMillis + splitTimeAndMsg + msg, true);
	    }

	    public static String getReportName() {
	        return reportName;
	    }

	    public static String getSpiltTimeAndMsg() {
	        return splitTimeAndMsg;
	    }
}
