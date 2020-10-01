package Verwaltung;

import java.sql.Date;

public class Student 
{
	private String Vorname;
	private String Name;
	private String Email;
	private String KlassenStufe;
	private String KlassenName;
	private Date Geburtsdatum;
	private String Geschlecht;
	
	// Constructor
	public Student(
			  String aVorName
			, String aName
			, String aEmail
			, String aKlassenStufe
			, String aKlassenName
			, Date aGeburtsdatum
			, String aGeschlecht
			) 
	{
		Vorname 	 = aVorName;
		Name 		 = aName;
		Email 		 = aEmail;
		KlassenStufe = aKlassenStufe;
		KlassenName  = aKlassenName;
		Geburtsdatum = aGeburtsdatum;
		Geschlecht   = aGeschlecht;
	}
	
	
	// Getter
	/**gets the first name of the student*/
	public String GetVorname() 
	{
		return Vorname;
	}
	
	/**gets the family name of the student*/
	public String GetName() 
	{
		return Name;
	}
	
	/**gets the email adress of the student*/
	public String GetEmail() 
	{
		return Email;
	}
	
	/**gets the grade of the student*/
	public String GetKlassenStufe() 
	{
		return KlassenStufe;
	}
	
	/**gets the name of the students class*/
	public String GetKlassenName() 
	{
		return KlassenName;
	}
	
	/**gets the students date of birth*/
	public Date GetGeburtsdatum() 
	{
		return Geburtsdatum;
	}
	
	/**gets the students gender*/
	public String GetGeschlecht() 
	{
		return Geschlecht;
	}
	// Ende Getter
	
	// Setter
	/**sets the first name of the student*/
	public void SetVorname(String NewVorname) 
	{
		this.Vorname = NewVorname;
	}
	
	/**sets the family name of the student*/
	public void SetName(String newName) 
	{
		this.Name = newName;
	}
	
	/**sets the email adress of the student*/
	public void SetEmail(String NewEmail) 
	{
		this.Email = NewEmail;
	}
	
	/**sets the grade of the student*/
	public void SetKlassenStufe(String NewKlassenStufe) 
	{
		this.KlassenStufe = NewKlassenStufe;
	}
	
	/**sets the name of the students class*/
	public void SetKlassenName(String NewKlassenName) 
	{
		this.KlassenName = NewKlassenName;
	}
	
	/**sets the students date of birth*/
	public void SetGeburtsdatum(Date NewDate) 
	{
		this.Geburtsdatum =  NewDate;
	}
	
	/**sets the students gender*/
	public void SetGeschlecht(String NewGeschlecht) 
	{
		this.Geschlecht = NewGeschlecht;
	}
	//Ende Setter
	
	
	
}
