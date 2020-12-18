package Sql;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import Verwaltung.Student;

// Connecting to the Database
public class Database
{
	
	// Creates the Connection to the Database
	public static Connection Open(String host, String port, String userName, String password) throws SQLException
	{
		// The Connection
		com.mysql.jdbc.Connection con = null;
		try
		{
			// MySQL jdbc Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Opening the connection
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
																		"jdbc:mysql://"
																		+ host 
																		+ ":" 
																		+ port 
		 																+ "/excelmanagementdata"
																		, userName
																		, password
																		);
			System.out.print("connection established\n\n");
			return con;
		
		} 
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return con;
	}
	
	// Requests the complete Table "schueler" from the Database excelmanagementdata.
	public static void requestStudentTable(com.mysql.jdbc.Connection con) 
	{			
		try
		{
			// Preparing the request query
			Statement stmt = (Statement) con.createStatement();  
			// Result 
			ResultSet rs = (ResultSet) stmt.executeQuery("select * from schueler");  
			rs.absolute(0);
			while(rs.next()) 
			{
				System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			}		
			
			con.close();
			
		} 
		catch (SQLException e)
		{
				e.printStackTrace();		
		}  
	}
	
	// Requests the complete Table "schueler from the Database excelmanagementdata.
		public static void insertNewStudent(Connection conn, Student student) 
				throws ClassNotFoundException, SQLException 
		{			
			// Holds the Date
			java.sql.Date Date = new java.sql.Date(student.GetGeburtsdatum());
			
			   String query 
			   			= "INSERT INTO schueler ("
					    + " Name,"
					    + " KlassenBezeichnung,"
					    + " Vorname,"
					    + " Geburtsdatum,"
					    + " Klassenstufe,"
					    + " Geschlecht,"
					    + " ID)"
					    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
			   try 
			   {
				    // set all the preparedstatement parameters
				    PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
				    st.setString(1, student.GetName());
				    st.setString(2, student.GetKlassenStufe());
				    st.setString(3, student.GetVorname());
				    st.setDate	(4,  Date);
				    st.setString(5, student.GetKlassenStufe());
				    st.setString(6, student.GetGeschlecht());
				    st.setString(7, student.GetHash());
				    
				    // execute the preparedstatement insert
				    st.executeUpdate();
				    st.close();
				    conn.close();
				    System.out.println("Connection closed");
			   } 
			   catch (SQLException se)
			   {
				    // log exception
				    throw se;
			   }
			} //end main
}