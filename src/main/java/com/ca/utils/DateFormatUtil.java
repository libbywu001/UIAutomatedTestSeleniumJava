package com.ca.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.ca.logger.LoggerControler;

/**
 * @author libby.wu
 * @date 2023/10/03
 */
public class DateFormatUtil {
	final static LoggerControler log = LoggerControler.getLoggerControler(DateFormatUtil.class);
	public static final String ZH_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String ZN_DATE_FORMAT = "yyyy年MM月dd日 HH:mm:ss";
	public static final String ZC_DATE_FORMAT = "yyyy年MM月dd日";
	public static final String SHORT_DATE_FORMAT = "yy-MM-dd HH:mm";
	public static final String CHECK_LOG_FORMAT = "yyyyMMdd";
	public static final String REPORT_CSV_FORMAT = "yyyyMMdd_HHmmss";
	private static SimpleDateFormat simpleDateFormat;

	public static String format(String type) {
		simpleDateFormat = new SimpleDateFormat(type);
		String sTime = simpleDateFormat.format(new Date());
		log.info("current time-" + sTime);
		return sTime;
	}

	public static String getTime() {
		long cTime = System.currentTimeMillis() / 100;
		String randTime = String.valueOf(cTime);
		return randTime;
	}
}
