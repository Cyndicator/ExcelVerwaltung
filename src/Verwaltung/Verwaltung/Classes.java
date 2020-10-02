package Verwaltung;

import java.util.ArrayList;
import java.util.List;


public class Classes {
	
	private String KlassenStufe;
	private String KlassenName;
	private List<Student> Schueler = new ArrayList<Student>();
	
	
	public Classes(String aKlassenStufe, String aKlassenName) 
	{
		KlassenStufe = aKlassenStufe;
		KlassenName  = aKlassenName;
	}
	
	
	
	
	//Start getter
	
	/**Gets the list of the students*/
	public List<Student> getSchueler()
	{
		return Schueler;
	}

	/**Gets the grade of the class*/
	public String getKlassenStufe()
	{
		return KlassenStufe;
	}
	
	/**Gets the name of the class*/
	public String getKlassenName()
	{
		return KlassenName;
	}
	//Ende getter
	
	//Start setter
	
	/** Sets the List of the students*/
	public void setSchueler(List<Student> newSchueler)
	{
		this.Schueler = newSchueler;
	}
	
	/**Sets the grade of the class*/
	public void setKlassenStufe(String newKlassenStufe) 
	{
		this.KlassenStufe = newKlassenStufe;
	}
	
	/** Sets the name of the class*/
	public void setKlassenName(String newKlassenName)
	{
		this.KlassenName = newKlassenName;
	}

	//Ende setter
}
