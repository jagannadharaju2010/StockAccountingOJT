package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingTest {

	public static void main(String[] args) throws Throwable {
      File srcFile=new File("C:\\Users\\jaganadharaju\\Desktop\\TestData.xlsx");
      FileInputStream fis=new FileInputStream(srcFile);
      XSSFWorkbook wb=new XSSFWorkbook(fis);
      XSSFSheet ws = wb.getSheet("supplier");
     int rcnt = ws.getLastRowNum();
     for(int i=1;i<rcnt;i++){
    	 if(ws.getRow(i).getCell(0).getCellType()==Cell.CELL_TYPE_NUMERIC)
    	 {
    		 int data = (int)ws.getRow(i).getCell(0).getNumericCellValue();
    		 String cellData = String.valueOf(data);
    		 System.out.println(cellData);
    		
    	 }
    	 else
    	 {
    	String supplierName = ws.getRow(i).getCell(0).getStringCellValue();
    	String address = ws.getRow(i).getCell(1).getStringCellValue();
    	System.out.println(supplierName+"  "+address);
     }
     fis.close();
     wb.close();
	}

}
}
