package driverFactory;

import org.testng.annotations.Test;

public class AppTest {

	@Test
	public void kickStart() throws Throwable
	{
		try
		{
			DriverScript ds=new DriverScript();
		
		ds.startTest();    
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
