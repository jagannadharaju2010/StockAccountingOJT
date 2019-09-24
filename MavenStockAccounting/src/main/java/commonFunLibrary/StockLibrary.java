package commonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import Utilities.PropertyFileUtility;

public class StockLibrary {
WebDriver driver;
String res;

public String appLaunch(String url){
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\jaganadharaju\\workspace\\MavenStockAccounting\\ExecutableFiles\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get(url);
	driver.manage().window().maximize();
	//validation
	if(driver.findElement(By.id("username")).isDisplayed())
	{
		res="pass";
	}else{
		res="Fail";
	}
	return res;
	
}
//Login into stockaccounting

public String appLogin(String username,String password){
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys(username);
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys(password);
	driver.findElement(By.id("btnsubmit")).click();
	if(driver.findElement(By.id("logout")).isDisplayed())
	{
		res="pass";
		System.out.println("pass");
	}
	else{
		res="Fail";
		System.out.println("fail");
	}
	return res;
	
}

//Create Stock Category

public String stockCategory(String catName) throws InterruptedException{
	WebElement text = driver.findElement(By.xpath("//li[@id='mi_a_stock_items']"));
	Thread.sleep(1000);
	Actions act=new Actions(driver);
	act.moveToElement(text).build().perform();
	driver.findElement(By.linkText("Stock Categories")).click();
	driver.findElement(By.xpath("(//a[@class='btn btn-default ewAddEdit ewAdd btn-sm'])[1]")).click();
	driver.findElement(By.id("x_Category_Name")).sendKeys(catName);
	Thread.sleep(1000);
	driver.findElement(By.id("btnAction")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[@class='ajs-button btn btn-primary']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("(//button[text()='OK'])[6]")).click();
	Thread.sleep(1000);
	if(driver.findElement(By.xpath("//button[@class='btn btn-default ewSearchToggle']")).isDisplayed())
	{
		driver.findElement(By.xpath("//button[@class='btn btn-default ewSearchToggle']")).click();
		driver.findElement(By.id("psearch")).sendKeys(catName);
		driver.findElement(By.id("btnsubmit")).click();
	}
	else
	{
		driver.findElement(By.id("psearch")).sendKeys(catName);
		driver.findElement(By.id("btnsubmit")).click();
	}
	String act_data = driver.findElement(By.xpath("//span[@id='el1_a_stock_categories_Category_Name']")).getText();
	if(catName.equals(act_data))
	{
		res="pass";
	}
	else
	{
		res="fail";
	}
	return res;
}

//Create one supplier
public String supplierCreation(String sName,String add,String city,String country,String cntPerson,String phnNumber,String email,String mobNum,String not) throws InterruptedException{
	driver.findElement(By.id("mi_a_suppliers")).click();
	driver.findElement(By.xpath("(//a[@class='btn btn-default ewAddEdit ewAdd btn-sm'])[1]")).click();
	Thread.sleep(1000);
	String exp_data = driver.findElement(By.id("x_Supplier_Number")).getAttribute("value");
	System.out.println(exp_data);
	driver.findElement(By.id("x_Supplier_Name")).sendKeys(sName);
	driver.findElement(By.name("x_Address")).sendKeys(add);
	driver.findElement(By.id("x_City")).sendKeys(city);
	driver.findElement(By.id("x_Country")).sendKeys(country);
	driver.findElement(By.id("x_Contact_Person")).sendKeys(cntPerson);
	driver.findElement(By.id("x_Phone_Number")).sendKeys(phnNumber);
	driver.findElement(By.id("x__Email")).sendKeys(email);
	driver.findElement(By.id("x_Mobile_Number")).sendKeys(mobNum);
	driver.findElement(By.id("x_Notes")).sendKeys(not);
	Thread.sleep(1000);
	//scroll down page
	Actions pagedown=new Actions(driver);
	pagedown.sendKeys(Keys.PAGE_DOWN).build().perform(); 
	Thread.sleep(1000);
	driver.findElement(By.id("btnAction")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[text()='OK!']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("(//button[text()='OK'])[6]")).click();
	Thread.sleep(3000);
	if(driver.findElement(By.xpath("//button[@class='btn btn-default ewSearchToggle']")).isDisplayed())
	{
		driver.findElement(By.xpath("//button[@class='btn btn-default ewSearchToggle']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("psearch")).clear();
		driver.findElement(By.id("psearch")).sendKeys(exp_data);
		driver.findElement(By.id("btnsubmit")).click();
	}
	else
	{
		Thread.sleep(1000);
		driver.findElement(By.id("psearch")).clear();
		driver.findElement(By.id("psearch")).sendKeys(exp_data);
		driver.findElement(By.id("btnsubmit")).click();
	}
	String act_data = driver.findElement(By.xpath("//span[@id='el1_a_suppliers_Supplier_Number']")).getText();
	System.out.println(act_data);
	if(exp_data.equals(act_data)){
		res="pass";
	}
	else
	{
		res="fail";
	}
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[@class='btn btn-default ewSearchToggle  active']")).click();
	return res;
}

//Logout from StockAccouting

public String appLogout() throws InterruptedException
{
	driver.findElement(By.id("logout")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[text()='OK!']")).click();
	//validation
	if(driver.findElement(By.id("username")).isDisplayed())
	{
		res="pass";
	}else
	{
		res="Fail";
	}
	return res;
	
}
//close the StockAccouting
public void closeapp(){
	driver.close(); 
	
	
	
}

public static void main(String args[]) throws Throwable{
	StockLibrary fl=new StockLibrary();
	fl.appLaunch(PropertyFileUtility.getValueForKey("url"));
	fl.appLogin("admin", "master");
	String result = fl.stockCategory("redmi");
	System.out.println(result);
	fl.supplierCreation("Samsung", "Ameerpet", "Hyderabad", "India", "jaswanth", "2244585", "jaswanth@qedge.com", "9655448596", "notes");
	fl.appLogout();
	fl.closeapp();
}
}
