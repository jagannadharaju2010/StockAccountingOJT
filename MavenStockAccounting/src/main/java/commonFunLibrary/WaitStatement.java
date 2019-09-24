package commonFunLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitStatement 
{

	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver", 
				"D:\\Sel_live_jag\\chromedriver.exe");
			WebDriver	driver=new ChromeDriver();
				driver.get("Http://gmail.com");
				driver.manage().window().maximize();
				
			//	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebDriverWait myWait=new WebDriverWait(driver, 20);
				driver.findElement(By.id("identifierId")).sendKeys("vasu@Qedgetech.com");
				
				driver.findElement(By.xpath("//*[@id='identifierNext']/span/span")).click();
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));	
				driver.findElement(By.name("password")).sendKeys("StockAccounting");

	}

}
