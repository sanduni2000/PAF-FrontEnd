package model;

import java.sql.*; 

public class Cunsumption 
{ 
	//A common method to connect to the DB
    private Connection connect() 
      { 
           Connection con = null; 
           try
              { 
                   Class.forName("com.mysql.jdbc.Driver"); 
 
  //Provide the correct details: DBServer/DBName, username, password 
                   con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", ""); 
               } 
           catch (Exception e) 
             {e.printStackTrace();} 
           return con; 
    } 
    
  public String insertconsumption(String Dname,String Month, String Note) 
  { 
   String output = ""; 
   
  try
  { 
    Connection con = connect();
    
  if (con == null) 
  {return "Error while connecting to the database for inserting."; } 
  
  int DUsedUnits = Integer.parseInt(GetUnits(Dname));
  String DZipCode = GetZipcode(Dname);
 
 // create a prepared statement
 String query = " insert into consumption(`ID`,`Dname`,`DZipCode`,`DUsedUnits`,`Month` ,`Note`)"
 + " values (?, ?, ?, ?, ?, ?)"; 
 
       PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
            preparedStmt.setInt(1, 0); 
            preparedStmt.setString(2, Dname); 
            preparedStmt.setString(3,DZipCode); 
            preparedStmt.setInt(4, DUsedUnits); 
            preparedStmt.setString(5, Month);
            preparedStmt.setString(6, Note); 
 
//execute the statement

            preparedStmt.execute(); 
  con.close(); 
  output = "Inserted successfully"; 
 } 
  catch (Exception e) 
 { 
      output = "Error while inserting the Details."; 
      System.err.println(e.getMessage()); 
 } 
  return output; 
 
 }
  // Get Zip code 
  public String GetZipcode(String Dname) {
		 String output="";
		 
		 try {
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for reading."; 
				 }
				 
				 
				 String query = "SELECT DZipCode FROM `distric zip codes` WHERE Disname= '"+Dname+"'" ; 
				 
				 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 
				 while (rs.next()) 
				 { 
				  output = rs.getString("DZipCode");
				  
				 }
				 System.out.println("hello"+output);
			 }
		 
		 catch(Exception ex) {
			 System.out.println("hello"+ex);
		 }
		 return output;
	 }
  
  // get total units according to District
  public String GetUnits(String Dname) {
		 String output="";
		 
		 try {
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for reading."; 
				 }
				 
				 
				 String query = "SELECT SUM(consumedUnits) FROM unit_records WHERE district = '"+Dname+"'" ; 
				 
				 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 
				 while (rs.next()) 
				 { 
				  output = Integer.toString(rs.getInt("SUM(consumedUnits)"));
				  
				 }
				 System.out.println("hello"+output);
			 }
		 
		 catch(Exception ex) {
			 System.out.println("hello"+ex);
		 }
		 return output;
	 }

			 
  public String readCunsumption() 
 { 
 String output = ""; 
  try
 { 
 Connection con = connect(); 
  if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 
 // Prepare the html table to be displayed
  
  
   output = "<table border='1'><tr><th>ID</th><th>Dname</th><th>DZipCode</th>" +
   "<th>DUsedUnits</th>" + 
   "<th>Month</th>" + 
   "<th>Note</th>" +
   "<th>Update</th><th>Remove</th></tr>"; 
 
  String query = "select * from 	consumption	"; 
  Statement stmt = con.createStatement(); 
  ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
    while (rs.next()) 
    { 
       String ID = Integer.toString(rs.getInt("ID")); 
       String Dname = rs.getString("Dname"); 
       String DZipCode = rs.getString("DZipCode"); 
       String DUsedUnits = Double.toString(rs.getDouble("DUsedUnits")); 
       String Month = rs.getString("Month");
       String Note = rs.getString("Note"); 
 
 // Add into the html table
       
 output += "<tr><td>" + ID + "</td>";
 output += "<td>" + Dname + "</td>"; 
 output += "<td>" + DZipCode + "</td>"; 
 output += "<td>" + DUsedUnits + "</td>"; 
 output += "<td>" + Month + "</td>";
 output += "<td>" + Note + "</td>"; 
 
 
 // buttons
 
 output += "<td><input name='btnUpdate' type='button' value='Update'nclass='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='ID' type='hidden' value='" + ID 
 + "'>" + "</form></td></tr>"; 
 } 
      con.close(); 
 // Complete the html table
           output += "</table>"; 
 } 
   catch (Exception e) 
 { 
 output = "Error while reading the details."; 
 System.err.println(e.getMessage()); 
 } 
   return output; 
 } 

  
    public String updateCunsumption(String ID,String Dname, String Month,String Note) 

{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE consumption SET Dname=?,Month=?,Note=? WHERE ID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	
	 
	 // binding values
	 preparedStmt.setString(1, Dname); 
	 preparedStmt.setString(2, Month);
	 preparedStmt.setString(3, Note); 
	 preparedStmt.setInt(4, Integer.parseInt(ID)); 
	 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

	public String deleteCunsumption(String ID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; }
	 
	
	 // create a prepared statement
	 String query = "delete from consumption where ID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(ID)); 
	 
	
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
//	//Search
//	
public String searchCunsumption(String Dname) {
		
		String output="";
		try{ 
			Connection con = connect(); 
			if (con == null)  {
				return "Error while connecting to the database";
				} 
				
				output = "<html>"
						+"<table border='1'><tr>"+
						"<th>Dname</th>"+
						"<th>DZipCode</th>" +
						"<th>DUsedUnits</th>" +
						"<th>Month</th>" +
						"<th>Note</th>" ; 

			// create a prepared statement
			String query = "select * from consumption where Dname='"+Dname+"'"; 
			Statement stmt = con.createStatement(); 
		 	ResultSet rs = stmt.executeQuery(query);
		 	while(rs.next()) {
		 		
		 		//String AccountNum = rs.getString("AccountNum"); 
		 	 
				
				String DZipCode= rs.getString("DZipCode"); 
				String DUsedUnits = rs.getString("DUsedUnits");
				String Month = rs.getString("Month");
				String Note = rs.getString("Note");
				
		
				
 				output += "<td>" + Dname + "</td>"; 
 				output += "<td>" + DZipCode + "</td>"; 
 				output += "<td>" + DUsedUnits + "</td>"; 
 				output += "<td>" + Month + "</td>"; 
 				output += "<td>" + Note + "</td>"; 
 				 

				
		 	}
			
			con.close(); 
	 
				output += "</table></html>"; 

		} 
		catch (Exception e) { 
			output = "Error while searching";  
			System.err.println(e.getMessage()); 
		} 
		return output;
	

}





	}
