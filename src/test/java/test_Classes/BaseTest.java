package test_Classes;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import page_Classes.CheckoutPage;

public class BaseTest {
	public static WebDriver driver;
	public static CheckoutPage cop;

	@BeforeSuite
	public void initBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\savita.dighade\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");

	}

	@BeforeClass
	public void createObject() {
		cop = new CheckoutPage(driver);
	}

	/*@AfterSuite
	public void tearDown() {
		driver.quit();*/
	}


