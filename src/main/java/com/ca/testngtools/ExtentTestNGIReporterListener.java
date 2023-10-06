package com.ca.testngtools;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ResourceCDN;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.TestAttribute;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ca.utils.CustomFile;
import com.ca.utils.DateFormatUtil;

/**
 * @author libby.wu
 * @date 2023/10/03 Description:
 */
public class ExtentTestNGIReporterListener implements IReporter {
	// Generated path and file name
	private static final String OUTPUT_FOLDER = "test-output/";
	private static final String FILE_NAME = "index" + DateFormatUtil.format(DateFormatUtil.REPORT_CSV_FORMAT) + ".html";

	private ExtentReports extent;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		init();
		boolean createSuiteNode = false;
		if (suites.size() > 1) {
			createSuiteNode = true;
		}
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();
			// If there are no use cases in the suite, skip them directly and do not
			// generate them in the report.
			if (result.size() == 0) {
				continue;
			}
			// Count the total number of successful, failed, and skipped use cases under the
			// suite
			int suiteFailSize = 0;
			int suitePassSize = 0;
			int suiteSkipSize = 0;
			ExtentTest suiteTest = null;
			// When there are multiple suites, the test results of the same suite are
			// grouped into one category in the report and a first-level node is created.
			if (createSuiteNode) {
				suiteTest = extent.createTest(suite.getName()).assignCategory(suite.getName());
			}
			boolean createSuiteResultNode = false;
			if (result.size() > 1) {
				createSuiteResultNode = true;
			}
			Date suiteStartTime = null, suiteEndTime = new Date();
			for (ISuiteResult r : result.values()) {
				ExtentTest resultNode;
				ITestContext context = r.getTestContext();
				if (createSuiteResultNode) {
					// If the suite is not created, the SuiteResult will be created as a first-level
					// node, otherwise it will be created as a child node of the suite.
					if (null == suiteTest) {
						resultNode = extent.createTest(context.getName());
					} else {
						resultNode = suiteTest.createNode(context.getName());
					}
				} else {
					resultNode = suiteTest;
				}
				if (resultNode != null) {
					resultNode.assignCategory(suite.getName(), context.getName());
					if (suiteStartTime == null) {
						suiteStartTime = context.getStartDate();
					}
					suiteEndTime = context.getEndDate();
					resultNode.getModel().setStartTime(context.getStartDate());
					resultNode.getModel().setEndTime(context.getEndDate());
					int passSize = context.getPassedTests().size();
					int failSize = context.getFailedTests().size();
					int skipSize = context.getSkippedTests().size();
					suitePassSize += passSize;
					suiteFailSize += failSize;
					suiteSkipSize += skipSize;
					if (failSize > 0) {
						resultNode.getModel().setStatus(Status.FAIL);
					}
					resultNode.getModel().setDescription(
							String.format("Pass: %s ; Fail: %s ; Skip: %s ;", passSize, failSize, skipSize));
				}

				System.out.println("suiteFailSize:" + suiteFailSize);

				buildTestNodes(resultNode, context.getFailedTests(), Status.FAIL);
				buildTestNodes(resultNode, context.getPassedTests(), Status.PASS);
			}
			if (suiteTest != null) {
				suiteTest.getModel().setDescription(
						String.format("Pass: %s ; Fail: %s ; Skip: %s ;", suitePassSize, suiteFailSize, suiteSkipSize));
				suiteTest.getModel().setStartTime(suiteStartTime == null ? new Date() : suiteStartTime);
				suiteTest.getModel().setEndTime(suiteEndTime);
				if (suiteFailSize > 0) {
					suiteTest.getModel().setStatus(Status.FAIL);
				}
			}

		}
		extent.flush();

	}

	private void init() {
		File reportDir = new File(OUTPUT_FOLDER);
		if (!reportDir.exists() && !reportDir.isDirectory()) {
			reportDir.mkdir();
		}

		// Set generated html
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
		htmlReporter.config().setDocumentTitle(ReportUtil.getReportName());
		htmlReporter.config().setReportName(ReportUtil.getReportName());
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		// Set system information style.card-panel.environment th:first-child{
		// width:30%;}
		htmlReporter.config().setCSS(
				".node.level-1  ul{ display:none;} .node.level-1.active ul{display:block;}  .card-panel.environment  th:first-child{ width:30%;}");
		// Remove key listener event
		htmlReporter.config().setJS("$(window).off(\"keydown\");");
		htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);
		Properties properties = System.getProperties();
		extent.setSystemInfo("os.name", properties.getProperty("os.name", "未知"));
		extent.setSystemInfo("os.arch", properties.getProperty("os.arch", "未知"));
		extent.setSystemInfo("os.version", properties.getProperty("os.version", "未知"));
		extent.setSystemInfo("java.version", properties.getProperty("java.version", "未知"));
		extent.setSystemInfo("java.home", properties.getProperty("java.home", "未知"));
		extent.setSystemInfo("user.name", properties.getProperty("user.name", "未知"));
		extent.setSystemInfo("user.dir", properties.getProperty("user.dir", "未知"));
	}

	private void buildTestNodes(ExtentTest extenttest, IResultMap tests, Status status) {

		String[] categories = new String[0];
		if (extenttest != null) {
			List<TestAttribute> categoryList = extenttest.getModel().getCategoryContext().getAll();
			categories = new String[categoryList.size()];
			for (int index = 0; index < categoryList.size(); index++) {
				categories[index] = categoryList.get(index).getName();
			}
		}

		ExtentTest test;

		if (tests.size() > 0) {
			Set<ITestResult> treeSet = new TreeSet<ITestResult>(new Comparator<ITestResult>() {
				@Override
				public int compare(ITestResult o1, ITestResult o2) {
					return o1.getStartMillis() < o2.getStartMillis() ? -1 : 1;
				}
			});
			treeSet.addAll(tests.getAllResults());
			for (ITestResult result : treeSet) {
				Object[] parameters = result.getParameters();
				String name = "";
				for (Object param : parameters) {
					name += param.toString();
				}
				if (name.length() > 0) {
					if (name.length() > 50) {
						name = name.substring(0, 49) + "...";
					}
				} else {
					name = result.getMethod().getMethodName();
				}

				if (extenttest == null) {
					test = extent.createTest(name);
				} else {
					test = extenttest.createNode(name).assignCategory(categories);
				}
				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				List<String> outputList = Reporter.getOutput(result);
				for (String output : outputList) {

					test.debug(output.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
				}
				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				Iterator logIterator = test.getModel().getLogContext().getIterator();
				while (logIterator.hasNext()) {
					Log log = (Log) logIterator.next();
					String details = log.getDetails();
					if (details.contains(ReportUtil.getSpiltTimeAndMsg())) {
						String time = details.split(ReportUtil.getSpiltTimeAndMsg())[0];
						log.setTimestamp(getTime(Long.valueOf(time)));
						log.setDetails(details.substring(time.length() + ReportUtil.getSpiltTimeAndMsg().length()));
					} else {
						log.setTimestamp(getTime(result.getEndMillis()));
					}
				}
				test.getModel().setStartTime(getTime(result.getStartMillis()));
				test.getModel().setEndTime(getTime(result.getEndMillis()));

				// =================== Insert screenshot =========================
				String startTimeStr = String.valueOf(getTime(result.getStartMillis()));
				String endTimeStr = String.valueOf(getTime(result.getEndMillis()));
				Matcher matcher = Pattern.compile("([0-9]\\d:[0-9]\\d:[0-9]\\d)").matcher(startTimeStr);
				if (matcher.find()) {
					String timeStr1 = matcher.group();
					startTimeStr = timeStr1.replaceAll(":", "");
				}

				Matcher matcher1 = Pattern.compile("([0-9]\\d:[0-9]\\d:[0-9]\\d)").matcher(endTimeStr);
				if (matcher1.find()) {
					String timeStr1 = matcher1.group();
					endTimeStr = timeStr1.replaceAll(":", "");
				}

				System.out.println("startTimeStr:" + startTimeStr + "|" + endTimeStr);

				try {
					if (status.equals(Status.FAIL)) {
						String fileDir = DateFormatUtil.format(DateFormatUtil.CHECK_LOG_FORMAT);
						String path = System.getProperty("user.dir");
						List fileNameList = CustomFile.getFileName(path + "\\error\\" + fileDir);
						for (int i = 0; i < fileNameList.size(); i++) {
							String fileName = (String) fileNameList.get(i);
							System.out.println("fileName:" + fileName);
							Matcher matcher2 = Pattern.compile("(\\d{6}.jpg)").matcher(fileName);
							if (matcher2.find()) {
								String fileTime = matcher2.group().replaceAll(".jpg", "");
								System.out.println("fileTime" + fileTime);
								if (Integer.parseInt(fileTime) >= Integer.parseInt(startTimeStr)
										&& Integer.parseInt(fileTime) <= Integer.parseInt(endTimeStr) + 2) {
									test.fail("error screenshot")
											.addScreenCaptureFromPath(path + "\\error\\" + fileDir + "\\" + fileName);
									break;
								}
							}
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
