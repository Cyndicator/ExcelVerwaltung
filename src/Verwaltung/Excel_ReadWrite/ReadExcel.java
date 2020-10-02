package Excel_ReadWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Verwaltung.Student;

public class ReadExcel 
{
	// Reads the input excel file and creates for each student a new Object of class Student
	public List<Student> readStudentsFromExcelFile(String excelFilePath) 
			throws IOException, SQLException, ParseException 
	{
		List<Student> allStudents = new ArrayList<Student>();
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = getWorkbook(inputStream, excelFilePath);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		Student student = new Student(null, null, null, null, null, null, null);
		
		while (iterator.hasNext()) 
		{
			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			

			while (cellIterator.hasNext()) 
			{
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				int row = nextRow.getRowNum();
				
				if(row == 0) 
				{
					break;
				}
				
				switch (columnIndex) 
				{
						case 0:
							student.SetName(
									getCellValue(nextCell).toString()
									);
							break;
							
						case 1:
							student.SetKlassenName(
									getCellValue(nextCell).toString()
									);
							break;
							
						case 2:
							student.SetVorname(
									getCellValue(nextCell).toString()
									);
							break;
							
						case 3: // Birthday
							// Getting field content
							String DateString = getCellValue(nextCell).toString();
							
							// Converting the date into a valid format
							java.util.Date myString 
												= SimpleDateFormat.getDateInstance(
													java.text.DateFormat.SHORT, Locale.GERMANY)
															.parse(DateString);
		
							student.SetGeburtsdatum(
										DateString
									);
										break;

							
						case 4:
							student.SetKlassenStufe(
									getCellValue(nextCell).toString()
									);
							break;
						case 5:
							student.SetGeschlecht(
									getCellValue(nextCell).toString()
									);
							break;
				}
			
			}
			allStudents.add(student); 
		}

		workbook.close();
		inputStream.close();
		
		for(int i = 0; i < allStudents.size(); i++) 
		{
			System.out.print(allStudents.get(i).GetName() + "\n");
		}
		
		return allStudents;
	}
	
	
	// Gets the cell value of the given cell
	private Object getCellValue(Cell cell) 
	{
		switch (cell.getCellType()) 
		{
		case STRING:
			return cell.getStringCellValue();

		case BOOLEAN:
			return cell.getBooleanCellValue();

		case NUMERIC:
			return cell.getNumericCellValue();
		
		case BLANK:
			break;
		}

		return null;
	}
	
	
	// Creats the Workbook depending on which ending "xls" or "xlsx" the Excel Data
	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath)
			throws IOException 
	{
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) 
		{
			workbook = new XSSFWorkbook(inputStream);
		}
		else if (excelFilePath.endsWith("xls")) 
		{
			workbook = new HSSFWorkbook(inputStream);
		} 
		else 
		{
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}
	
}