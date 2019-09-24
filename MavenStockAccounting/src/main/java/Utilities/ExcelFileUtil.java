package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
Workbook wb;

// It will load Excel sheet
public ExcelFileUtil() throws Throwable
{
	FileInputStream fis=new FileInputStream("C:\\Users\\jaganadharaju\\workspace\\MavenStockAccounting\\TestInputs\\InputSheet.xlsx");
	wb=WorkbookFactory.create(fis);
	
}

//Row count
public int rowCount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
	//return wb.getSheet(sheetname).getLastRowNum()-wb.getSheet(sheetname).getFirstRowNum();
}

//Column count
public int columnCount(String sheetname,int row)
{
	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	
}

//Reading the data
public String getData(String sheetname,int row,int column)
{
	String data="";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int cellData=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
	   data= String.valueOf(cellData);
	}
	else
	{
		data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
	
}

//Storing data into Excel Sheet pass or fail or not executed

public void setData(String sheetname, int row,int column,String status) throws Throwable
{
	//wb.getSheet(sheetname).getRow(row).createCell(column).setCellValue(status);
	Sheet sh=wb.getSheet(sheetname);
	Row rownum=sh.getRow(row);
	Cell cell=rownum.createCell(column);
	cell.setCellValue(status);
	
	if(status.equalsIgnoreCase("pass"))
	{
		//Create cell Style
		CellStyle style=wb.createCellStyle();
		//Create Font
		Font font = wb.createFont();
		//Apply colour to text
		font.setColor(IndexedColors.GREEN.index);
		//Apply bold to Text
		font.setBold(true);
		//Set Font
		style.setFont(font);
		//Set Cell Style
		rownum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		//Create cell Style
		CellStyle style = wb.createCellStyle();
		//Create Font
		Font font = wb.createFont();
		//Apply colour to text
		font.setColor(IndexedColors.RED.index);
		//Apply bold to text
		font.setBold(true);
		//set font
		style.setFont(font);
		//set cell style
		rownum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Not Executed"))
	{
		//Create cell Style
				CellStyle style = wb.createCellStyle();
				//Create Font
				Font font = wb.createFont();
				//Apply colour to text
				font.setColor(IndexedColors.BLUE.index);
				//Apply bold to text
				font.setBold(true);
				//set font
				style.setFont(font);
				//set cell style
				rownum.getCell(column).setCellStyle(style);
	}
	FileOutputStream fos=new FileOutputStream("C:\\Users\\jaganadharaju\\workspace\\StockAccouting\\TestOutput\\OutputSheet.xlsx");
	wb.write(fos);
	fos.close();
}

public static void main(String[] args) throws Throwable {
	ExcelFileUtil excel=new ExcelFileUtil();
	System.out.println(excel.rowCount("sheet1"));
	System.out.println(excel.columnCount("sheet1", 1));
	System.out.println(excel.getData("sheet1", 2, 3));
	excel.setData("Sheet1", 2, 4, "Fail");
	excel.setData("Sheet1", 1, 4, "Pass");
	excel.setData("Sheet1", 3, 4, "Not Executed");
}
}
