 
package Verwaltung;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JFileChooser;

import org.eclipse.core.databinding.observable.Realm;
import org.apache.commons.math3.util.MultidimensionalCounter.Iterator;
import org.eclipse.core.*;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import Excel_ReadWrite.ReadExcel;
import Sql.Database;

import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;

import com.mysql.jdbc.Connection;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public class SwtApplicationWindow 
{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ;
    protected Shell shlSwtApplication;
    public java.util.List<Student> allStudents = new ArrayList<Student>();
    private Table table_Students;
    private Table table_Classes;
    
    
	public static void main(String[] args) 
    {
    	SwtApplicationWindow window = new SwtApplicationWindow();
    	window.open();
    }
    
    // open window
    public void open()
    {
        final Display display = Display.getDefault();
        createContents();
        shlSwtApplication.open();
        shlSwtApplication.layout();

        while (!shlSwtApplication.isDisposed()) 
        {
            if (!display.readAndDispatch()) 
            {
                display.sleep();
            }
        }
    }
    
    
    protected void createContents() 
    {
        shlSwtApplication = new Shell();
        shlSwtApplication.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
        shlSwtApplication.setSize(860, 449);
        shlSwtApplication.setText("ExcelManagementTool");
        shlSwtApplication.setLayout(new GridLayout(1, true));
        
        // Menu
        Menu menu = new Menu(shlSwtApplication, SWT.BAR);
        shlSwtApplication.setMenuBar(menu);
        
        // Import Button Menu
        MenuItem Menu_Import_Btn = new MenuItem(menu, SWT.NONE);
        Menu_Import_Btn.addSelectionListener(new SelectionAdapter() // Click event
        {
        	// Widget Selected event
        	@Override
        	public void widgetSelected(SelectionEvent e) 
        	{
        		// Create a file chooser
        		final JFileChooser fc = new JFileChooser();
        		// In response to a button click:
        		int returnVal = fc.showOpenDialog(null);
        		
        		if (returnVal == JFileChooser.APPROVE_OPTION) 
        		{
        			
                    // Get chosen File
        			File file = fc.getSelectedFile();
                    
                    // Caller for the Reader Function
                    try 
                    {
                    	ReadExcel reader;
                    	reader = new ReadExcel();
                    	String path = file.getPath();
                    	// Filling up the list
                    	allStudents = reader.readStudentsFromExcelFile(path);
                    	
                    	java.util.Iterator<Student> AnyStudent = allStudents.iterator();
                        
                    	for (int i = 0; i < allStudents.size(); i++) 
                        {
                    		Student student = allStudents.get(i);
                    		TableItem item = new TableItem(table_Students, SWT.NONE);
                            // Filling up the students table with the imported data
                    		for (int j = 0; j <= 5; j++) 
                            {
                    			switch(j) 
                    			{
                    				case 0: // Name column
                    					item.setText(j, student.GetName());
                    					break;
                    				
                    				case 1: // Class Name column
                    					item.setText(j, student.GetKlassenName());
                    					break;
                    				
                    				case 2: // FirstName column
                    					item.setText(j, student.GetVorname());
                    					break;
                    				
                    				case 3: // Day of birth column
                    					item.setText(j, student.GetGeburtsdatum().toString());
                    					break;
                    					
                    				case 4: // Class Level column
                    					item.setText(j, student.GetKlassenStufe());
                    					break;
                    					
                    				case 5: // Gender column
                    					item.setText(j, student.GetGeschlecht());	
                        				break;
                    			}
                            }
                          
                        }	
                        
                    	// Writing the list of students into the Database
                    	while(AnyStudent.hasNext()) 
                    	{
                    		Database.insertNewStudent(Database.Open("localhost", "3306", "root", null), AnyStudent.next());
                    	}
                    	
					} 
                    catch (Exception e1) 
                    {
						e1.printStackTrace();
					}
	            } 
        		else 
	            {
        			System.out.print("Open command cancelled by user.\n");
	            }
                //log.setCaretPosition(log.getDocument().getLength());	
        	}
        });
        Menu_Import_Btn.setToolTipText("Import Excel Data");
        Menu_Import_Btn.setText("Import");
        
        // Export Button Menu
        MenuItem Menu_Export_Btn = new MenuItem(menu, SWT.NONE);
        Menu_Export_Btn.setToolTipText("Export Excel Data");
        Menu_Export_Btn.setID(1);
        Menu_Export_Btn.setText("Export");
        
       
        // Tab Folder 
        CTabFolder tab = new CTabFolder(shlSwtApplication, SWT.BORDER);
        tab.setSimple(false);
        tab.setMaximized(true);
        tab.marginWidth = 10;
        tab.marginHeight = 10;
        tab.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        tab.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
        
        
       
        // Tab Folder Item "Schueler"
        CTabItem tabItemSchueler = new CTabItem(tab, SWT.NONE);
        tabItemSchueler.setText("Schueler");
        
        
        // Student Table creation 
        table_Students = new Table(tab, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
        tabItemSchueler.setControl(table_Students);
        table_Students.setHeaderVisible(true);
        table_Students.setLinesVisible(true);
        
        // Column 1: "Name"
        TableColumn tblclmnName = new TableColumn(table_Students, SWT.LEFT);
        tblclmnName.setMoveable(true);
        tblclmnName.setWidth(100);
        tblclmnName.setText("Name");
        // Column 2: "Klassenbezeichnung"
        TableColumn tblclmnKlassenbezeichnung = new TableColumn(table_Students, SWT.LEFT);
        tblclmnKlassenbezeichnung.setMoveable(true);
        tblclmnKlassenbezeichnung.setWidth(175);
        tblclmnKlassenbezeichnung.setText("Klassenbezeichnung");
        // Column 3: "Vorname"
        TableColumn tblclmnVorname = new TableColumn(table_Students, SWT.LEFT);
        tblclmnVorname.setMoveable(true);
        tblclmnVorname.setWidth(100);
        tblclmnVorname.setText("Vorname");
        //Column 4: "Klassenstufe"
        TableColumn tblclmnKlassenstufe = new TableColumn(table_Students, SWT.LEFT);
        tblclmnKlassenstufe.setMoveable(true);
        tblclmnKlassenstufe.setWidth(100);
        tblclmnKlassenstufe.setText("Klassenstufe");
        // Column 5: "Geschlecht"
        TableColumn tblclmnGeschlecht = new TableColumn(table_Students, SWT.LEFT);
        tblclmnGeschlecht.setMoveable(true);
        tblclmnGeschlecht.setWidth(100);
        tblclmnGeschlecht.setText("Geschlecht");
        // Column 5: "Geschlecht"
        TableColumn tblclmnGeburtstag = new TableColumn(table_Students, SWT.LEFT);
        tblclmnGeburtstag.setMoveable(true);
        tblclmnGeburtstag.setWidth(100);
        tblclmnGeburtstag.setText("Geburtstag");
        
        
        
        // Tab Item Classes
        CTabItem tbtmKlassen = new CTabItem(tab, SWT.NONE);
        tbtmKlassen.setText("Klassen");
        // Creating the Table for the Classes
        table_Classes = new Table(tab, SWT.BORDER | SWT.FULL_SELECTION);
        tbtmKlassen.setControl(table_Classes);
        table_Classes.setHeaderVisible(true);
        table_Classes.setLinesVisible(true);
        // Column Klasse
        TableColumn tblclmnClass = new TableColumn(table_Classes, SWT.NONE);
        tblclmnClass.setWidth(100);
        tblclmnClass.setText("Klasse");
        // Column Anzahl_Schueler
        TableColumn tblclmnStudentsInClass = new TableColumn(table_Classes, SWT.NONE);
        tblclmnStudentsInClass.setWidth(100);
        tblclmnStudentsInClass.setText("Anzahl Schueler");
        // Column teacher
        TableColumn tblclmnTeacher = new TableColumn(table_Classes, SWT.NONE);
        tblclmnTeacher.setWidth(100);
        tblclmnTeacher.setText("Klassenlehrer");

        

    }
}