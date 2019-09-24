package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WritingTest {

	public static void main(String[] args) throws Throwable {
		File srcFile=new File("C:\\Users\\jaganadharaju\\Desktop\\TestData.xlsx");
	      FileInputStream fis=new FileInputStream(srcFile);
	      XSSFWorkbook wb=new XSSFWorkbook(fis);
	      XSSFSheet ws = wb.getSheet("supplier");
	     ws.getRow(0).createCell(2).setCellValue("results");
	     FileOutputStream fos=new FileOutputStream(srcFile);
	     wb.write(fos);
		System.out.println("GIT EDIT");
		
	}
}
