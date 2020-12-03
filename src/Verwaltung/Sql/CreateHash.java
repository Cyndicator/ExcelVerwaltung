package Sql;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Verwaltung.Student;

/*Creates a unique hash value that is used for the identification of each object. 
 * And if a change in the students data has been taken place
 */
public class CreateHash
{
	// Creats a hash value for the given student object
	public static String sha1Student(Student student) 
	{

		String result = null;
		try
		{
			
			String[] studentdata = 
				{
				  student.GetName()
				, student.GetKlassenName()
				, student.GetVorname()
				, student.GetGeburtsdatum().toString()
				, student.GetKlassenStufe()
				, student.GetGeschlecht()
				};
		
			result = CreateHash.sha1(studentdata);
		
		} 
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
     /*
      * Takes the privided stringarray and calculates the Hash of them(SHA-1 Algorithm)
      * */
    private static String sha1(String[] input) 
    		throws NoSuchAlgorithmException 
    {
    	// Variable to hold the concated String
    	String concatedStrings = "";
    	
    	// Concat Strings to one final String
    	for (String string : input)
		{
    		concatedStrings += string;
		}
    	
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(concatedStrings.getBytes());
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < result.length; i++) 
        {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
}
