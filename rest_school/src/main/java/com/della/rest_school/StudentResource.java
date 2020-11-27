package com.della.rest_school;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List; 

@Path("students")
public class StudentResource {
	private static Connection conn;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Student> getStudentResource() {
    	List<Student> students = new ArrayList<Student>();

		if (conn == null)
		{
			conn = getDBConnection();
		}
		
		if (conn != null)
		{
			System.out.println("Connection done!");
			
	    	ResultSet rs = null;
	    	PreparedStatement schoolPrepareStat = null;
	    	String getQueryStatement = "SELECT * FROM Students";
			try {
		    	schoolPrepareStat = conn.prepareStatement(getQueryStatement);
		    	rs = schoolPrepareStat.executeQuery();
				// Let's iterate through the java ResultSet
				while (rs.next()) {
					Student s1 = new Student();
					s1.setId(rs.getInt("ID"));
					s1.setLastName(rs.getString("LastName"));
					s1.setFirstName(rs.getString("FirstName"));
					s1.setAge(rs.getInt("Age"));
					students.add(s1);
					}
				}
			catch (SQLException ex){
				System.out.println("MySQL Connection Failed!");
				ex.printStackTrace();
		    }
		    finally {
		        // it is a good idea to release
		        // resources in a finally{} block
		        // in reverse-order of their creation
		        // if they are no-longer needed
		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException sqlEx) { } // ignore
		            rs = null;
		        }
		        if (schoolPrepareStat != null) {
		            try {
		        	    schoolPrepareStat.close();
		            } catch (SQLException sqlEx) { } // ignore
		            schoolPrepareStat = null;
		        }
		    }
		}
		
		return students;
	}
	
	/*
	public Student postStudentResource() {
		
	 */
	
	private Connection getDBConnection() {
		try {
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root", "Della@1992");
			return conn;
		}
		catch(SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    return null;
		}
	}
}
