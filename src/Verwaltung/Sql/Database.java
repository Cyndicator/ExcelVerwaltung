package Sql;

import java.util.List;
import java.sql.Driver;
import java.sql.*;

import com.mysql.jdbc.*;
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
				// TODO Auto-generated catch block
				e.printStackTrace();		
		}  
	}
	
	// Requests the complete Table "schueler from the Database excelmanagementdata.
		public static void insertNewStudent(Connection conn, Student student) 
				throws ClassNotFoundException, SQLException 
		{			
			   
			   String query = "INSERT INTO schueler ("
					    + "Name,"
					    + "KlassenBezeichnung,"
					    + "Vorname,"
					    + "Geburtsdatum,"
					    + "Klassenstufe,"
					    + "Geschlecht)"
					    + "VALUES (?, ?, ?, ?, ?, ?)";
			   try 
			   {
				    // set all the preparedstatement parameters
				    java.sql.PreparedStatement st = conn.prepareStatement(query);
				    st.setString(1, student.GetName());
				    st.setString(2, student.GetKlassenName());
				    st.setString(3, student.GetVorname());
				    st.setDate	(4, (Date) student.GetGeburtsdatum());
				    st.setString(5, student.GetKlassenStufe());
				    st.setString(6, student.GetGeschlecht());

				    // execute the preparedstatement insert
				    st.executeUpdate();
				    
				    
				    st.close();
				    
				    conn.close();
				    System.out.print("Connection closed!\n");
				  } 
				  catch (SQLException se)
				  {
				    // log exception
				    throw se;
				  }
			}//end main
}