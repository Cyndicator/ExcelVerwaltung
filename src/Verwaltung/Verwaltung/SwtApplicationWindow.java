 
package Verwaltung;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Excel_ReadWrite.ReadExcel;
import Sql.Database;


public class SwtApplicationWindow 
{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ;
    protected Shell shlSwtApplication;
    public java.util.List<Student> allStudents = new ArrayList<Student>();
    private Table table_Students;
    private Table table_Classes;
    private Text textSuche;
    
    
    
    
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
        shlSwtApplication.setSize(808, 564);
        shlSwtApplication.setText("ExcelManagementTool");
        GridLayout gl_shlSwtApplication = new GridLayout(1, false);
        gl_shlSwtApplication.verticalSpacing = 3;
        shlSwtApplication.setLayout(gl_shlSwtApplication);
        
        
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
        		// Filter the for xls and xlsx filetypes
        	    FileNameExtensionFilter filter 
        	    					= new FileNameExtensionFilter(
        	    							"Datentabelle ("
        	    							+ ".xls"
        	    							+ ", .xlsx or .csv"
        	    							, "xls"
        	    							, "xlsx"
        	    							, "csv"
        	    				);
        	    // Activating the filter
        	    fc.setFileFilter(filter);
        	
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
                    	
                    	Iterator<Student> AnyStudent = allStudents.iterator();

                    	for (int i = 0; i < allStudents.size(); i++)
						{
                    		Student student = allStudents.get(i);
                    		TableItem item = new TableItem(table_Students,  SWT.NONE);
                    		

                           
                    		// Filling up the students table with the imported data
                    		for (int j = 0; j <= 5; j++) 
                            {
                    			
                    			switch(j) 
                    			{
                    				
                    				case 0: // Name column
                    					System.out.println("Name: " + student.GetName());
                    					item.setText(j, student.GetName());
                    					continue;
                    				
                    				case 1: // Class Name column
                    					System.out.println("Class: " + student.GetKlassenName());
                    					item.setText(j, student.GetKlassenName());
                    					continue;
                    				
                    				case 2: // FirstName column
                    					item.setText(j, student.GetVorname());
                    					continue;
                    				
                    				case 3: // Day of birth column
                    					item.setText(
                    							j
                    							, new java.sql.Date(
                    									student.GetGeburtsdatum()
                    									)
                    							.toString()
                    							);
                    					continue;
                    					
                    				case 4: // Class Level column
                    					item.setText(j, student.GetKlassenStufe());
                    					continue;
                    					
                    				case 5: // Gender column
                    					item.setText(j, student.GetGeschlecht());	
                    					continue;
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
        
        // Sash Container for Search Label and Textfield
        SashForm sashSearch = new SashForm(shlSwtApplication, SWT.NONE);
        GridData gd_sashSearch = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_sashSearch.minimumWidth = 150;
        sashSearch.setLayoutData(gd_sashSearch);
        // Textfield textSuche
        textSuche = new Text(sashSearch, SWT.BORDER);
        
        Button btnSuchen = new Button(sashSearch, SWT.NONE);
        btnSuchen.setImage(SWTResourceManager.getImage("C:\\Users\\Fredel\\Downloads\\icons8-suche-32.png"));
        btnSuchen.setText("Suchen");
        sashSearch.setWeights(new int[] {511, 109});
        
       
        // Tab Folder 
        CTabFolder tab = new CTabFolder(shlSwtApplication, SWT.BORDER);
        tab.setMRUVisible(true);
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
        table_Students = new Table(tab, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE);
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
        tblclmnKlassenbezeichnung.setMoveable(false);
        tblclmnKlassenbezeichnung.setWidth(175);
        tblclmnKlassenbezeichnung.setText("Klassenbezeichnung");
        // Column 3: "Vorname"
        TableColumn tblclmnVorname = new TableColumn(table_Students, SWT.LEFT);
        tblclmnVorname.setMoveable(false);
        tblclmnVorname.setWidth(100);
        tblclmnVorname.setText("Vorname");
        //Column 4: "Geburtstag"
        TableColumn tblclmnKlassenstufe = new TableColumn(table_Students, SWT.LEFT);
        tblclmnKlassenstufe.setMoveable(false);
        tblclmnKlassenstufe.setWidth(100);
        tblclmnKlassenstufe.setText("Geburtstag");
        // Column 5: "Klassenstufe"
        TableColumn tblclmnGeschlecht = new TableColumn(table_Students, SWT.LEFT);
        tblclmnGeschlecht.setMoveable(false);
        tblclmnGeschlecht.setWidth(139);
        tblclmnGeschlecht.setText("Klassenstufe");
        // Column 5: "Geschlecht"
        TableColumn tblclmnGeburtstag = new TableColumn(table_Students, SWT.LEFT);
        tblclmnGeburtstag.setMoveable(false);
        tblclmnGeburtstag.setWidth(100);
        tblclmnGeburtstag.setText("Geschlecht");
        
        
        
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
        tblclmnStudentsInClass.setWidth(136);
        tblclmnStudentsInClass.setText("Anzahl Schueler");
        // Column teacher
        TableColumn tblclmnTeacher = new TableColumn(table_Classes, SWT.NONE);
        tblclmnTeacher.setWidth(124);
        tblclmnTeacher.setText("Klassenlehrer");
        
        SashForm Sash_Buttons_Bottom = new SashForm(shlSwtApplication, SWT.NONE);
        
        Button btnBotNewStudent = new Button(Sash_Buttons_Bottom, SWT.NONE);
        // Button Schueler hinzufuegen 
        btnBotNewStudent.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) 
        	{    		

        		allStudents.add((Student) new AddStudentDialog(shlSwtApplication, SWT.DIALOG_TRIM).result);
        	}
        });
        btnBotNewStudent.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_CROSS));

        btnBotNewStudent.setAlignment(SWT.LEFT);
        btnBotNewStudent.setText("Neuer Sch\u00FCler");
        
        Button btnBotEdit = new Button(Sash_Buttons_Bottom, SWT.NONE);
        btnBotEdit.setEnabled(false);

       
        btnBotEdit.setGrayed(true);
        btnBotEdit.setText("editieren");
        Sash_Buttons_Bottom.setWeights(new int[] {1, 1});

        

    }
}