package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadingData {

	public static void main(String[] args) throws Throwable {
		Properties configproperties=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\jaganadharaju\\workspace\\StockAccouting\\PropertyFile\\environment.properties");
	    configproperties.load(fis);
	    System.out.println(configproperties.getProperty("Browser"));
	    System.out.println(configproperties.getProperty("URL"));
	    System.out.println(configproperties.getProperty("Username"));
	    System.out.println(configproperties.getProperty("Password"));
	    
	}
}
