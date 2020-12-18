package Excel_ReadWrite;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Verwaltung.Student;
 
/**
 * A very simple program that writes some data to an Excel file
 * using the Apache POI library.
 * @author www.codejava.net
 *
 */
public class ExportToExcel {
	public void writeExcel(List<Student> allStudents, String excelFilePath) throws IOException {
		Workbook workbook = getWorkbook(excelFilePath);
		Sheet sheet = workbook.createSheet();
		
		createHeaderRow(sheet);
		int rowCount = 0; //int rowCount = 1;
		
		for (Student aStudent : allStudents) {
			Row row = sheet.createRow(++rowCount);
			writeBook(aStudent, row);
		}
		
		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}	
	}
	
	private void createHeaderRow(Sheet sheet) {
		
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		cellStyle.setFont(font);
				
		Row row = sheet.createRow(0);
		
		Cell cellVorname = row.createCell(0);
		cellVorname.setCellStyle(cellStyle);
		cellVorname.setCellValue("Vorname");
		
		Cell cellName = row.createCell(1);
		cellName.setCellStyle(cellStyle);
		cellName.setCellValue("Name");

		Cell cellKlassenStufe = row.createCell(2);
		cellKlassenStufe.setCellStyle(cellStyle);
		cellKlassenStufe.setCellValue("KlassenStufe");
		
		Cell cellKlassenName = row.createCell(3);
		cellKlassenName.setCellStyle(cellStyle);
		cellKlassenName.setCellValue("KlassenName");
		
		Cell cellGeburtsdatum = row.createCell(4);
		cellGeburtsdatum.setCellStyle(cellStyle);
		cellGeburtsdatum.setCellValue("Geburtsdatum");
		
		Cell cellGeschlecht = row.createCell(5);
		cellGeschlecht.setCellStyle(cellStyle);
		cellGeschlecht.setCellValue("Geschlecht");
		
		
		Cell cellEmail = row.createCell(6);
		cellEmail.setCellStyle(cellStyle);
		cellEmail.setCellValue("Email");
		
	}
	

	private void writeBook(Student aStudent, Row row) {
		
		//Row row = sheet.createRow(1);
		
		Cell cell = row.createCell(0);
		cell.setCellValue(aStudent.GetVorname());

		cell = row.createCell(1);
		cell.setCellValue(aStudent.GetName());		
		
		cell = row.createCell(2);
		cell.setCellValue(aStudent.GetKlassenStufe());
		
		cell = row.createCell(3);
		cell.setCellValue(aStudent.GetKlassenName());
		
		cell = row.createCell(4);
		cell.setCellValue(aStudent.GetGeburtsdatum());
		
		cell = row.createCell(5);
		cell.setCellValue(aStudent.GetGeschlecht());
		
		cell = row.createCell(6);
		cell.setCellValue(aStudent.GetEmail());
	}
	

	private Workbook getWorkbook(String excelFilePath) 
			throws IOException {
		Workbook workbook = null;
		
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}
		
		return workbook;
	}
	
}
