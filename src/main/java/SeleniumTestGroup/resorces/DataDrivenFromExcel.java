package SeleniumTestGroup.resorces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenFromExcel {
	
		public static ArrayList<String> getData(String testcaseName) throws IOException {
			ArrayList<String> a = new ArrayList<String>();
			FileInputStream fis = new FileInputStream("C:\\Users\\sumit.singh\\Documents\\demodata.xlsx");
			try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
				int sheetcount = wb.getNumberOfSheets();
				for(int i=0;i<sheetcount;i++) {
					if(wb.getSheetName(i).equalsIgnoreCase("demo")) {
						XSSFSheet sheet = wb.getSheetAt(i);
						
						Iterator<Row> rows = sheet.iterator();
						
						Row fr =rows.next();
						Iterator<Cell> cell = fr.cellIterator();
						int k=0;
						int col=0;
						while(cell.hasNext()) {
							if(cell.next().getStringCellValue().equalsIgnoreCase("Testcases")) {
								
								col = k;
							}
							k++;
						}
						System.out.println(col);
						
						while(rows.hasNext())
						{
						Row r = rows.next();
						if(r.getCell(col).getStringCellValue().equalsIgnoreCase(testcaseName)) {
							Iterator<Cell> c = r.cellIterator();
							while(c.hasNext()) {
							a.add(c.next().getStringCellValue());
						
							}
							
						}
						
						
						}
					}
				}
			}
			return a;

		}
		


		/*
		 * public static void main(String[] args) throws IOException { // TODO
		 * Auto-generated method stub
		 * 
		 * ArrayList<String> a = getData("Add Profile"); System.out.println(a.get(0));
		 * System.out.println(a.get(1));
		 * 
		 * 
		 * }
		 */
}
