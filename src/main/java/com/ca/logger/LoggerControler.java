
package com.ca.logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author libby.wu
 * @date 2023/10/02 Description: logger
 */
public class LoggerControler {
	private static Logger logger = null;
	private static LoggerControler loggerControler = null;

	public static LoggerControler getLoggerControler(Class<?> T) {
		String proPath = System.getProperty("user.dir") + File.separator + "config" + File.separator
				+ "log4j.properties";
		if (logger == null) {
			Properties props = new Properties(); // for the log4j.Properties file
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(proPath);
				props.load(inputStream);
				PropertyConfigurator.configure(props);
				logger = Logger.getLogger(String.valueOf(T));
				loggerControler = new LoggerControler();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return loggerControler;
	}

	public void debug(Object msg) {
		logger.debug(msg);
	}

	public void info(Object msg) {
		logger.info(msg);
	}

	public void warn(Object msg) {
		logger.warn(msg);
	}

	public void error(Object msg) {
		logger.error(msg);
	}
}
