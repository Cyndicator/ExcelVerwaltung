package Verwaltung;

public class Student 
{
	// Student Data
	private String Vorname;
	private String Name;
	private String Email;
	private String KlassenStufe;
	private String KlassenName;
	private Long   Geburtsdatum;
	private String Geschlecht;
	private String Hash;
	
	// Constructor
	public Student(
	   		  String aVorName
			, String aName
			, String aEmail
			, String aKlassenStufe
			, String aKlassenName
			, Long   aGeburtsdatum
			, String aGeschlecht
			) {
			Vorname 	 = aVorName;
			Name 		 = aName;
			Email 		 = aEmail;
			KlassenStufe = aKlassenStufe;
			KlassenName  = aKlassenName;
			Geburtsdatum = aGeburtsdatum;
			Geschlecht   = aGeschlecht;
			}
	
	// Constructor II (without E-Mail)
	public Student(
	   		  String aVorName
			, String aName
			, String aKlassenStufe
			, String aKlassenName
			, Long   aGeburtsdatum
			, String aGeschlecht
			) {
			Vorname 	 = aVorName;
			Name 		 = aName;
			Email 		 = null;
			KlassenStufe = aKlassenStufe;
			KlassenName  = aKlassenName;
			Geburtsdatum = aGeburtsdatum;
			Geschlecht   = aGeschlecht;
			}
	
	
	// Getter
	public String GetVorname() 
	{
		return Vorname;
	}
	
	public String GetName() 
	{
		return Name;
	}
	
	public String GetEmail() 
	{
		return Email;
	}
	
	public String GetKlassenStufe() 
	{
		return KlassenStufe;
	}
	
	public String GetKlassenName() 
	{
		return KlassenName;
	}
	
	public Long GetGeburtsdatum() 
	{
		return Geburtsdatum;
	}
	
	public String GetGeschlecht() 
	{
		return Geschlecht;
	}
	
	/**
	 * @return the hash
	 */
	public String GetHash()
	{
		return Hash;
	}
	// Ende Getter




	// Setter
	public void SetVorname(String NewVorname) 
	{
		this.Vorname = NewVorname;
	}
	
	public void SetName(String newName) 
	{
		this.Name = newName;
	}
	
	public void SetEmail(String NewEmail) 
	{
		this.Email = NewEmail;
	}
	
	public void SetKlassenStufe(String NewKlassenStufe) 
	{
		this.KlassenStufe = NewKlassenStufe;
	}
	
	public void SetKlassenName(String NewKlassenName) 
	{
		this.KlassenName = NewKlassenName;
	}
	
	public void SetGeburtsdatum(Long NewDate) 
	{
		this.Geburtsdatum =  NewDate;
	}
	
	public void SetGeschlecht(String NewGeschlecht) 
	{
		this.Geschlecht = NewGeschlecht;
	}
	
	/**
	 * @param  calculates the hash of the students data to create a unique identification number.
	 * @throws Exception 
	 */
	public void SetHash(Student student) throws Exception
	{
		try 
		{
			Hash = Sql.CreateHash.sha1Student(student);
		} 
		catch(Exception e) 
		{
			throw new Exception("Couldn't create the Hash for the student: "
									+ student.GetName()
									+ ", " 
									+ student.GetVorname()
							    );
		}
	}
	//Ende Setter
	
	
	
}
