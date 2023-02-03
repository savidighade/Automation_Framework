package util_Classes;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.cts.oneframework.configprovider.ConfigProvider;
//import org.cts.oneframework.configprovider.exceptions.PropertyFileNotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentConfiguration {
	
	//private static WebDriver driver;
	private static ExtentReports extent;
	public static final String WORKING_DIR = System.getProperty("user.dir");
	private static final String TIME_STAMP = new
	SimpleDateFormat("dd.MM.yyyy.HH.mm").format(new Date());
	private static final String EXTENT_REPORTS_FOLDER = WORKING_DIR +
	"/AutomationReports";
	private static final String REPORT_NAME = "ExtentReport_" + TIME_STAMP
	+ ".html";
	private static final String EXTENT_REPORTS_PATH =
	EXTENT_REPORTS_FOLDER + File.separator + REPORT_NAME;
	private static Logger logger =
	LogManager.getLogger(ExtentConfiguration.class.getName());

	
	private ExtentConfiguration() {
	}

	public static ExtentReports getInstance() {
	if (extent == null) {
	createReportsFolder();
	attachReporters();
	//addStepWithScreenshotInReport(driver, "Test", Status.INFO);
	}
	return extent;
	}

	
	private static void createReportsFolder() {
		File file = new File(EXTENT_REPORTS_FOLDER);
		if (!file.exists() && !file.mkdir()) {
		logger.warn("Failed to create directory!");
		}
		}

	
	private static ExtentHtmlReporter initHtmlReporter() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORTS_PATH);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(REPORT_NAME);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Execution-Status");
		htmlReporter.config().setCSS("css-string");
		htmlReporter.config().setJS("js-string");
		htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		return htmlReporter;
		}

	/*
	 * private static ExtentKlovReporter initKlovReporter() { ExtentKlovReporter
	 * klovReporter = new
	 * ExtentKlovReporter(ConfigProvider.getAsString("project.name"),
	 * ConfigProvider.getAsString("report.name"));
	 * klovReporter.initMongoDbConnection(ConfigProvider.getAsString("mongodb.host")
	 * , ConfigProvider.getAsInt("mongodb.port"));
	 * klovReporter.initKlovServerConnection(ConfigProvider.getAsString("klov.host")
	 * ); return klovReporter; }
	 */

	// Screenshot method
	/*
	 * public static void takeScreenshotMethod() throws IOException {
	 * 
	 * TakesScreenshot ts=(TakesScreenshot) driver; File
	 * source=ts.getScreenshotAs(OutputType.FILE); String
	 * path=System.getProperty("user.dir")+"\\Screenshot.png"; File destination=new
	 * File(path); org.openqa.selenium.io.FileHandler.copy(source, destination); }
	 */

	private static ExtentReports attachReporters() {
		String klovReporterRequired = null;
		extent = new ExtentReports();
		extent.attachReporter(initHtmlReporter());

		/*
		 * try { klovReporterRequired = ConfigProvider.getAsString("KlovReport"); }
		 * catch (PropertyFileNotFoundException e) { return extent; }
		 */
		/*
		 * if (klovReporterRequired != null &&
		 * (klovReporterRequired.equalsIgnoreCase("true") ||
		 * klovReporterRequired.equalsIgnoreCase("yes")))
		 * extent.attachReporter(initKlovReporter());
		 */
		return extent;
	}
	
	public static String captureScreenshot(WebDriver driver, String screenshotName) 
	{
	//String randomNumber = RandomStringUtils.randomNumeric(5);
	//String destinationPath = screenshotName + ".png";
		String destinationPath =
				"C:\\Users\\savita.dighade\\eclipse-workspace\\Automation_Framework\\Screenshots\\"
				+ screenshotName + ".png";
	TakesScreenshot ts = (TakesScreenshot) driver;
	File srcFile = ts.getScreenshotAs(OutputType.FILE);
	try {
	FileUtils.copyFile(srcFile, new File(destinationPath));
	} catch (IOException e) 
	{
	logger.warn("Failed to capture screenshot...");
	}
	return destinationPath;
	}

	public static void addStepWithScreenshotInReport(WebDriver driver,
	String message, Status status) {
	ExtentTest extentTest = ExtentTestManager.getTest();
	if (extentTest != null) {
	if (driver != null) {
	String path = captureScreenshot(driver, "screenshot");
	try {
	extentTest.log(status, message,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	} catch (IOException e) {
	logger.warn(e.getMessage());
	}
	} else {
	extentTest.log(status, message);
	}
	}
	}
	}




