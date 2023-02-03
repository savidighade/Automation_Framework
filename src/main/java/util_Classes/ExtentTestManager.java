package util_Classes;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class ExtentTestManager 
{

	private ExtentTestManager() {
	}

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	

	public static synchronized ExtentTest getTest() {
	return extentTest.get();
	}

	public static synchronized void endTest() {
	if (extentTest.get() != null)
	extent.removeTest(extentTest.get());
	}

	public static synchronized ExtentTest startTest(String testName, final
	String desc)
	{
	extent = ExtentConfiguration.getInstance();
	extentTest.set(extent.createTest(testName + "_" +
	Thread.currentThread().getId(), desc));
	return extentTest.get();
	}
	
			}
			

	
	

	


