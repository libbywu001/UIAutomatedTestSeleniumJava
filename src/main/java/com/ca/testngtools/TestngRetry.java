package com.ca.testngtools;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.ca.logger.LoggerControler;

/**
 * 
 */
public class TestngRetry implements IRetryAnalyzer {
	private static LoggerControler logger = LoggerControler.getLoggerControler(TestngRetry.class);
	private int retryCount = 1;
	private static int maxRetryCount;

	static {
		ConfigReader config = ConfigReader.getInstance();
		maxRetryCount = config.getRetryCount();
		logger.info("retrycount=" + maxRetryCount);
		logger.info("sourceCodeDir=" + config.getSourceCodeDir());
		logger.info("sourceCodeEncoding=" + config.getSrouceCodeEncoding());
	}

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount <= maxRetryCount) {
			String message = "Retry for [" + result.getName() + "] on class [" + result.getTestClass().getName()
					+ "] Retry " + retryCount + " times";
			logger.info(message);
			Reporter.setCurrentTestResult(result);
			Reporter.log("RunCount=" + (retryCount + 1));
			retryCount++;
			return true;
		}
		return false;
	}

}
