package Verwaltung;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddStudentDialog extends Dialog
{
	
	protected Object result;
	protected Shell  dialog;
	private Label lblName;
	private Text txtName;
	private Text txtVorname;
	private Text txtGeburtsDatum;
	private Text txtKlasse;
	private Text txtKlassenStufe;
	private Label lblGeschlecht;
	private Text txtGeschlecht;
	private Student student;
	private String[] data;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddStudentDialog(Shell parent, int style)
	{
		super(parent, style);
		setText("Schueler hinzufuegen");
		open();
	}
	
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open()
	{
		createContents();
		dialog.open();
		dialog.layout();
		Display display = getParent().getDisplay();
		
		while (!dialog.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
			
		}
		result = data;
		return result;
	}
	
	/**
	 * Create contents of the dialog.
	 */
	private void createContents()
	{
		
		dialog = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		dialog.setSize(450, 321);
		dialog.setText(getText());
		dialog.setLayout(null);
		
		lblName = new Label(dialog, SWT.NONE);
		lblName.setBounds(81, 3, 51, 31);
		lblName.setText("Name:");
		
		txtName = new Text(dialog, SWT.BORDER);
		txtName.setBounds(212, 3, 151, 31);
		
		Label lblVorname = new Label(dialog, SWT.NONE);
		lblVorname.setBounds(57, 37, 75, 25);
		lblVorname.setText("Vorname:");
		
		txtVorname = new Text(dialog, SWT.BORDER);
		txtVorname.setBounds(212, 34, 151, 31);
		
		Label lblGeburtsdatum = new Label(dialog, SWT.NONE);
		lblGeburtsdatum.setText("Geburtsdatum:");
		lblGeburtsdatum.setBounds(14, 68, 118, 25);
		
		txtGeburtsDatum = new Text(dialog, SWT.BORDER);
		txtGeburtsDatum.setBounds(212, 65, 151, 31);
		
		Label lblKlasse = new Label(dialog, SWT.NONE);
		lblKlasse.setBounds(81, 99, 51, 25);
		lblKlasse.setText("Klasse:");
		
		txtKlasse = new Text(dialog, SWT.BORDER);
		txtKlasse.setBounds(212, 96, 151, 31);
		
		Label lblKlassenstufe = new Label(dialog, SWT.NONE);
		lblKlassenstufe.setText("Klassenstufe:");
		lblKlassenstufe.setBounds(31, 130, 101, 25);
		
		txtKlassenStufe = new Text(dialog, SWT.BORDER);
		txtKlassenStufe.setBounds(212, 127, 151, 31);
		
		lblGeschlecht = new Label(dialog, SWT.NONE);
		lblGeschlecht.setText("Geschlecht:");
		lblGeschlecht.setBounds(41, 161, 91, 25);
		
		txtGeschlecht = new Text(dialog, SWT.BORDER);
		txtGeschlecht.setBounds(212, 158, 151, 31);
		// Button "OK"
		Button btnAddStudent = new Button(dialog, SWT.NONE);
		btnAddStudent.addSelectionListener(new SelectionAdapter() { // Button Click Event
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Student Data
				
					data[0] = txtName.getText();
					data[1] = txtVorname.getText();
					data[2] = txtGeburtsDatum.getText();
					data[3] = txtKlasse.getText();
					data[4] = txtKlassenStufe.getText();
					data[5] = txtGeschlecht.getText();
					System.out.println(txtName.getText());
					
				
				
				// Checking if one of the textfields has no information
				for (String textfield : data)
				{
					if (textfield.length() == 0) 
					{
						MessageBox box = new MessageBox(dialog, SWT.OK);
						box.setText("Daten fehlen!");
						
						box.setMessage("Eins oder mehrere Felder enthalten keine Daten.\n"
								+	"Um einen neuen Schueler zu erstellen muessen alle Daten angegeben sein!");

						break;
					}
					
				}
				
				// Student Data
				String Name 		= data[0];
				String Vorname		= data[1];
				Long   Geburtsdatum	= java.util.Date.parse(data[2]);
				String Klasse		= data[3];
				String Klassenstufe	= data[4];
				String Geschlecht	= data[5];
				// Creating the Student!
				student = new Student(Name, Vorname, Klasse, Klassenstufe, Geburtsdatum, Geschlecht);
				System.out.println(student.GetName());
			}
		});
		
		btnAddStudent.setBounds(66, 208, 105, 35);
		btnAddStudent.setText("Hinzufuegen");
		
		Button btnCancelAddStudent = new Button(dialog, SWT.NONE);
		btnCancelAddStudent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) // Button "Abbrechen" Click Event
			{ 
				dialog.close();
			}
		});
		btnCancelAddStudent.setBounds(258, 208, 105, 35);
		btnCancelAddStudent.setText("Abbrechen");
		
	}
	
	// Gets the created Student and returns it
	public Student getStudent() 
	{
		return student;
	}
}
