package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import commonFunLibrary.StockLibrary;

public class DataDrivenTest {

	public static void main(String[] args) throws Throwable {
		StockLibrary app=new StockLibrary();
		app.appLaunch("http://webapp.qedge.com/login.php");
		app.appLogin("admin", "master");
		File srcFile=new File("C:\\Users\\jaganadharaju\\workspace\\StockAccouting\\TestInputs\\TestData.xlsx");
		FileInputStream fis=new FileInputStream(srcFile);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet ws=wb.getSheet("supplier");
		int rcnt = ws.getLastRowNum();
		System.out.println(rcnt);
		for(int i=1;i<=rcnt;i++)
		{
			String sName = ws.getRow(i).getCell(0).getStringCellValue();
			String add = ws.getRow(i).getCell(1).getStringCellValue();
			String city = ws.getRow(i).getCell(2).getStringCellValue();
			String country = ws.getRow(i).getCell(3).getStringCellValue();
			String cPerson = ws.getRow(i).getCell(4).getStringCellValue();
			int pN = (int)ws.getRow(i).getCell(5).getNumericCellValue();
			String pNumber=String.valueOf(pN);
			String email = ws.getRow(i).getCell(6).getStringCellValue();
			int mN = (int)ws.getRow(i).getCell(7).getNumericCellValue();
			String mNumber=String.valueOf(mN);
			String note = ws.getRow(i).getCell(8).getStringCellValue();
			String results = app.supplierCreation(sName, add, city, country, cPerson, pNumber, email, mNumber, note);
	         ws.getRow(i).createCell(9).setCellValue(results);
	         FileOutputStream fos=new FileOutputStream(srcFile);
	         wb.write(fos);
		}
		wb.close();
		app.appLogout();
		app.closeapp();
	}

}
