package Excel_ReadWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Sql.CreateHash;
import Verwaltung.Student;


public class ReadExcel
{
	
	// Reads the input excel file and creates for each student a new Object of class
	// Student
	public List<Student> readStudentsFromExcelFile(String excelFilePath)
			throws IOException, SQLException, ParseException
	{
		
		List<Student>   allStudents = new ArrayList<Student>();
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
		Workbook workbook   = getWorkbook(inputStream, excelFilePath);
		Sheet    firstSheet = workbook.getSheetAt(0);
		
		boolean skipFirstRow = true;
		
		for (int i = firstSheet.getFirstRowNum(); i <= firstSheet.getLastRowNum(); i++)
		{
			// Skip the first row
			if(skipFirstRow) 
    		{
				skipFirstRow = false;
    			continue;
    		}
			
			Row row = firstSheet.getRow(i);
			
			// Creating an empty Sudent
			Student student = new Student(null, null, null, null, null, null, null);
			
			// Set the Name
			student.SetName(getCellValue(row.getCell(0)).toString());
			
			// Set the class name
			student.SetKlassenName(getCellValue(row.getCell(1)).toString());
			
			// Set the first Name	
			student.SetVorname(getCellValue(row.getCell(2)).toString());
			
			// Set Day of birth
			java.util.Date cellvalue = row.getCell(3).getDateCellValue();
			student.SetGeburtsdatum(cellvalue.getTime());
			
			// Set class level
			student.SetKlassenStufe(getCellValue(row.getCell(4)).toString());
			
			// Set gender
			System.out.println(getCellValue(row.getCell(5)));
			student.SetGeschlecht(getCellValue(row.getCell(5)).toString());
			
			//Set hash
			try
			{
				student.SetHash(student);
				
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			// Add the student to the List
			allStudents.add(student);
			
		}
		
		workbook.close();
		inputStream.close();
		
		for (Student students : allStudents)
		{
			System.out.print(students.GetName() + "\n");
		}
		
		return allStudents;
	}
	
	// Gets the cell value of the given cell | Returns false if cell is blank
	private Object getCellValue(Cell cell) throws ParseException
	{
		switch (cell.getCellType())
		{
			
			case STRING:
				return cell.getStringCellValue();
			
			case BOOLEAN:
				return cell.getBooleanCellValue();
			
			case NUMERIC:
				return cell.getNumericCellValue();
			
			case ERROR:
				throw new ParseException("Warning! There was an error occuring when reading the cell value", 1);
			
			case BLANK:
				return false;
			
			case _NONE:
				return false;
			default:
				
				break;
			
		}
		
		return null;
	}
	
	// Creats the Workbook depending on which ending "xls" or "xlsx" the Excel Data
	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException
	{
		Workbook workbook = null;
		
		if (excelFilePath.endsWith("xlsx"))
		{
			workbook = new XSSFWorkbook(inputStream);
			
		} else if (excelFilePath.endsWith("xls"))
		{
			workbook = new HSSFWorkbook(inputStream);
			
		} else
		{
			throw new IllegalArgumentException("The specified file is not Excel file");
			
		}
		
		return workbook;
	}
	
}
