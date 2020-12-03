package Verwaltung;



public class Teacher {

	private String Vorname;
	private String Nachname;
	private String Lehrer_Kuerzel;
	private String Lehrer_Klasse;
	private String SAP_Personalnummer;
	private Object Geburtsdatum;
	private String Geschlecht;
	
	//constructor
	public Teacher(
			 	String aVorname 
			 ,	String aNachname 
			 ,	String aLehrer_Kuerzel 
			 ,	String aLehrer_Klasse 
			 ,	String aSAP_Personalnummer 
			 ,	Object aGeburtsdatum 
			 ,	String aGeschlecht
			 )
	{
		Vorname = aVorname;
		Nachname = aNachname;
		Lehrer_Kuerzel = aLehrer_Kuerzel;
		Lehrer_Klasse = aLehrer_Klasse;
		SAP_Personalnummer = aSAP_Personalnummer;
		Geburtsdatum = aGeburtsdatum;
		Geschlecht = aGeschlecht;		
	}
	
	
	
	//Start getter
	
	/** Gibt den Vorname des Lehrer-Objekts aus*/
	public String GetVorname() 
	{
		return Vorname;
	}
	
	/**Gibt den Nachname des Lehrer-Objekts aus*/
	public String GetNachname() 
	{
		return Nachname;
	}
	
	/**Git das Kürzel des Lehrer-Objekts aus*/
	public String GetLehrer_Kuerzel() 
	{
		return Lehrer_Kuerzel;
	}
	
	/**Gibt die Klasse, bei der der Lehrer der Klassenlehrer ist aus*/
	public String GetLehrer_Klasse() 
	{
		return Lehrer_Klasse;
	}
	
	/**Gibt die Personalnummer des Lehrer-Objekts aus*/
	public String GetSAP_Personalnummer() 
	{
		return SAP_Personalnummer;
	}
	
	/**Gibt das Geburtsdatum des Lehrer-Objekts aus*/
	public Object GetGeburtsdatum() 
	{
		return Geburtsdatum;
	}
	
	/**Gibt das Geschlecht des Lehrer-Objekts aus*/
	public String GetGeschlecht() 
	{
		return Geschlecht;
	}
	//Ende getter
	
	//Start setter

	/** Setzt den Vornamen des Lehrer-Objekts*/
	public void SetVorname(String NewVorname) 
	{
		this.Vorname = NewVorname;
	}
	
	/**Setzt den Nachnamen des Lehrer-Objekts*/
	public void SetNachname(String newNachname) 
	{
		this.Nachname = newNachname;
	}
	
	/**Setzt das Kürzel des Lehrer-Objekts*/
	public void SetLehrer_Kuerzel(String NewLehrer_Kuerzel) 
	{
		this.Lehrer_Kuerzel = NewLehrer_Kuerzel;
	}
	
	/**Setzt die Klasse in der der Lehrer Klassenlehrer ist*/
	public void SetLehrer_Klasse(String NewLehrer_Klasse) 
	{
		this.Lehrer_Klasse = NewLehrer_Klasse;
	}
	
	/**Setzt die Personalnummer Lehrer-Objekts*/
	public void SetSAP_Personalnummer(String NewSAP_Personalnummer) 
	{
		this.SAP_Personalnummer = NewSAP_Personalnummer;
	}
	
	/**Setzt das Geburtsdatum des Lehrer-Objekts*/
	public void SetGeburtsdatum(Object object) 
	{
		this.Geburtsdatum =  object;
	}
	
	/**Setzt das Geschlecht des Lehrer-Objekts*/
	public void SetGeschlecht(String NewGeschlecht) 
	{
		this.Geschlecht = NewGeschlecht;
	}
	
	//Ende Setter
}
