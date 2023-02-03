package util_Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

public class CheckoutTest {
	public static Logger logger = Logger.getLogger(CheckoutTest.class.getName());
	public static WebDriver driver;
	ExtentTestManager extentManager;

	@BeforeTest
	public void loadLog4jFile() throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream("src/test/resources/Config/log4j.properties"));
		PropertyConfigurator.configure(props);
	}

	@Test(priority = 1)

	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\savita.dighade\\Downloads\\chromedriver_win32\\chromedriver.exe");
		

		driver = new ChromeDriver();

		driver.manage().window().maximize();

	}

	@Test(priority = 2)

	public void RecordPage() throws IOException {
		driver.get("https://www.amazon.in/");

		driver.findElement(By.xpath("//*[@id='nav-signin-tooltip']")).click();

		driver.findElement(By.xpath("//*[@id='ap_email']")).sendKeys("9834057849");

		driver.findElement(By.xpath("//*[@type='submit']")).click();

		driver.findElement(By.xpath("//*[@type='password']")).sendKeys("Nitin2020@");

		driver.findElement(By.xpath("//*[@type='submit']")).click();

		driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys("Iphone");

		extentManager.getTest().log(Status.INFO, "Search product");
		
		driver.findElement(By.xpath("//*[@id='nav-search-submit-button']")).click();
		ExtentConfiguration.addStepWithScreenshotInReport(driver, "Test1.png", Status.INFO);
		//ExtentConfiguration.captureScreenshot(driver, "Test");
		
		extentManager.getTest().log(Status.DEBUG, "Click on submit");
		driver.findElement(
				By.xpath("//*[@data-asin='B0BDJ52N7F']//*[@class='a-size-medium a-color-base a-text-normal']")).click();

		// driver.findElement(By.xpath("//*[text()=' Buy Now ']")).click();
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}


