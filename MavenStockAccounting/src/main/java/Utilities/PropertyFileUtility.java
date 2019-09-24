package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyFileUtility {

	public static String getValueForKey(String key) throws Throwable
	{
		Properties configproperties=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\jaganadharaju\\workspace\\MavenStockAccounting\\PropertyFile\\environment.properties");
	    configproperties.load(fis);
	    return configproperties.getProperty(key);
	}
}
