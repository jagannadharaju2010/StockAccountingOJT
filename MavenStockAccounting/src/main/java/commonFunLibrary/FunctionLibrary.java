package commonFunLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.FileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Utilities.PropertyFileUtility;

public class FunctionLibrary {
WebDriver driver;


//Start Browser
public static WebDriver startBrowser(WebDriver driver) throws Throwable
{
	if(PropertyFileUtility.getValueForKey("Browser").equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\jaganadharaju\\workspace\\MavenStockAccounting\\ExecutableFiles\\geckodriver.exe");
		driver=new FirefoxDriver();
	}
	else if(PropertyFileUtility.getValueForKey("Browser").equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jaganadharaju\\workspace\\MavenStockAccounting\\ExecutableFiles\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	else if(PropertyFileUtility.getValueForKey("Browser").equalsIgnoreCase("ie"))
	{
		System.setProperty("webdriver.ie.driver","C:\\Users\\jaganadharaju\\workspace\\MavenStockAccounting\\ExecutableFiles\\IEDriverServer.exe");
		driver=new InternetExplorerDriver();
	}
	return driver;
}

//openApplication
public static void openApplication(WebDriver driver) throws Throwable
{
	driver.get(PropertyFileUtility.getValueForKey("URL"));
	driver.manage().window().maximize();
}

//ClickAction
public static void clickAction(WebDriver driver,String locatorType,String locatorValue)
{
	if(locatorType.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(locatorValue)).click();
	}
	else if(locatorType.equalsIgnoreCase("name"))
	{
		driver.findElement(By.name(locatorValue)).click();
	}
	else if(locatorType.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(locatorValue)).click();
	}
}

//typeAction
public static void typeAction(WebDriver driver,String locatorType, String locatorValue, String data)
{
	if(locatorType.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(locatorValue)).clear();
		driver.findElement(By.id(locatorValue)).sendKeys(data);
	}
	else if(locatorType.equalsIgnoreCase("name"))
	{
		driver.findElement(By.name(locatorValue)).clear();
		driver.findElement(By.name(locatorValue)).sendKeys(data);
	}
	else if(locatorType.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(locatorValue)).clear();
		driver.findElement(By.xpath(locatorValue)).sendKeys(data);
	}
}

//closeBrowser
public static void closeBrowser(WebDriver driver)
{
	driver.close();
}

//waitforElement
public static void waitForElement(WebDriver driver,String locatorType, String locatorValue, String waittime)
{
	WebDriverWait myWait=new WebDriverWait(driver, Integer.parseInt(waittime));
	if(locatorType.equalsIgnoreCase("id"))
	{
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
	}
	else if(locatorType.equalsIgnoreCase("name"))
	{
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
	}
	else if(locatorType.equalsIgnoreCase("xpath"))
	{
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
	}
}

//PageDown
public static void pageDown(WebDriver driver)
{
	Actions act=new Actions(driver);
	act.sendKeys(Keys.PAGE_DOWN).build().perform();
}

//StockCategories
public static void mouseClick(WebDriver driver)
{
	Actions mouse=new Actions(driver);
	mouse.moveToElement(driver.findElement(By.id("mi_a_stock_items"))).build().perform();
	mouse.moveToElement(driver.findElement(By.id("mi_a_stock_categories"))).build().perform();
}

//CaptureData
public static void captureData(WebDriver driver,String locatorType, String locatorValue) throws IOException
{
	String data="";
	if(locatorType.equalsIgnoreCase("id"))
	{
		data=driver.findElement(By.id(locatorValue)).getAttribute("value");
	}
	else if(locatorType.equalsIgnoreCase("name"))
	{
		data=driver.findElement(By.name(locatorValue)).getAttribute("value");
	}
	else if(locatorType.equalsIgnoreCase("xpath"))
	{
		data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
	}
	FileWriter fw=new FileWriter("C:\\Users\\jaganadharaju\\workspace\\MavenStockAccounting\\DataCapture\\Data.txt");
	BufferedWriter bw=new BufferedWriter(fw);
	bw.write(data);
	bw.flush();
	bw.close();
}

//TableValidation

public static void tableValidation(WebDriver driver,String column) throws Throwable
{
	//reading the data from text file
  FileReader fr=new FileReader("C:\\Users\\jaganadharaju\\workspace\\MavenStockAccounting\\DataCapture\\Data.txt");
  BufferedReader br=new BufferedReader(fr);
  String exp_data = br.readLine();
  System.out.println(exp_data);
  
  //converting string value into integer
  int colNum=Integer.parseInt(column);
  //search panel
	if(driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("Search.Panel"))).isDisplayed())
	{
		 System.out.println("Done");
		driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("Search.Panel"))).click();
        Thread.sleep(1000);
        driver.findElement(By.id(PropertyFileUtility.getValueForKey("Search.Box"))).clear();
       
        driver.findElement(By.id(PropertyFileUtility.getValueForKey("Search.Box"))).sendKeys(exp_data);
        driver.findElement(By.id(PropertyFileUtility.getValueForKey("Search.Button"))).click();
	}
	else
	{
		driver.findElement(By.id(PropertyFileUtility.getValueForKey("Search.Box"))).clear();
        driver.findElement(By.id(PropertyFileUtility.getValueForKey("Search.Box"))).sendKeys(exp_data);
        driver.findElement(By.id(PropertyFileUtility.getValueForKey("Search.Button"))).click();
	}
	//supplier webtable
	WebElement webtable = driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("webtable")));
//row count
	List<WebElement> rows = webtable.findElements(By.tagName("tr"));
	
	for(int i=1;i<=rows.size();i++)
	{
		//capturing supplier number
		String act_data = driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colNum+"]/div/span")).getText();
	    System.out.println(act_data);
	    //validation
	    Assert.assertEquals(act_data, exp_data);
	    
	    break;
	}
}

public static String generateDate()
{
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_DD_SS");
	return sdf.format(date);
}

public static void main(String[] args) throws Throwable {
	WebDriver driver=null;
	driver=FunctionLibrary.startBrowser(driver);
	FunctionLibrary.openApplication(driver);
}
}
