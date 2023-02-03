package page_Classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage 
{
	public static WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		this.driver=driver;
	}

	public void verfiyAmazonFunctionality()
	{
		driver.findElement(By.xpath("//*[@id='nav-signin-tooltip']")).click();

		driver.findElement(By.xpath("//*[@id='ap_email']")).sendKeys("9834057849");

		driver.findElement(By.xpath("//*[@type='submit']")).click();

		driver.findElement(By.xpath("//*[@type='password']")).sendKeys("Nitin2020@");

		driver.findElement(By.xpath("//*[@type='submit']")).click();

		driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys("Iphone");
		
		driver.findElement(By.xpath("//*[@id='nav-search-submit-button']")).click();
		
		driver.findElement(
				By.xpath("//*[@data-asin='B0BDJ52N7F']//*[@class='a-size-medium a-color-base a-text-normal']")).click();


	}
}
