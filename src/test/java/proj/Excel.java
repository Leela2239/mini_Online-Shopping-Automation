package proj;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	public static  String Input() throws IOException {
		FileInputStream file = new FileInputStream("C:\\New folder\\example\\test\\ExcelFile.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sh = wb.getSheetAt(0);
		XSSFRow rw = sh.getRow(0);
		String data = String.valueOf(rw.getCell(0));
		return data;
		
	}
		public static void output(String p1,String p2,String tot) throws IOException {
			FileOutputStream file = new FileOutputStream("C:\\New folder\\example\\test\\output.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sh = wb.createSheet("Results");
			XSSFRow rw = sh.createRow(0);
			XSSFCell cell0=rw.createCell(0);
			cell0.setCellValue("Price1");
			XSSFCell cell1=rw.createCell(1);
			cell1.setCellValue("Price2");
			XSSFCell cell2=rw.createCell(2);
			cell2.setCellValue("Total_Price");
			XSSFRow rw1 = sh.createRow(1);
			rw1.createCell(0).setCellValue(p1);
			rw1.createCell(1).setCellValue(p2);
			rw1.createCell(2).setCellValue(tot);
			wb.write(file);
			wb.close();
			file.close();
				
}}
