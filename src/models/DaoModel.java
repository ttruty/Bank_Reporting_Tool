package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;

import records.BankRecords;

public class DaoModel {

	//Declare DB objects 
	DBConnect conn = null;
	Statement stmt = null;

		// constructor
		public DaoModel() { //create db object instance
		 conn = new DBConnect();
	}
		
	public void createTable() {
		
//		String tableName = "t_trut_tab";
//		int pid; //primary key for DB
//		String id;
//		double income;
//		String pep;
		
		 try {

			 // Open a connection
			 System.out.println("Connecting to database to create Table...");
			 System.out.println("Connected database successfully...");

			 //check if table already exists 
			 DatabaseMetaData dbm = conn.connect().getMetaData();
			 ResultSet rs = dbm.getTables(null, null, "t_trut_tab", null);
			 
			 if (rs.next())
			 {
				 System.out.println("Table already exist ...");	
				 // delete all rows and reset pid to 1 to start count
				 stmt = conn.connect().createStatement();
			     String sql = "DELETE FROM t_trut_tab"; 
			     stmt.executeUpdate(sql); 
			     //reset auto incrementding counter to 1 for debugging table
			     String auto = "ALTER TABLE t_trut_tab AUTO_INCREMENT=1";  // reset counter to 1;
			     stmt.executeUpdate(auto);
			 }
			 else {
				 // Execute create query
				 System.out.println("Creating table in given database...");
	
				 stmt = conn.connect().createStatement();
	
				 String sql = "CREATE TABLE t_trut_tab " + 
				              "(pid INTEGER not NULL AUTO_INCREMENT, " + 
				  	          " id VARCHAR(10), " +
						      " income numeric(8,2), " + 
						      " pep VARCHAR(3), " + 
					          " PRIMARY KEY ( pid ))";
					 	 
				 stmt.executeUpdate(sql);
				 System.out.println("Created table in given database...");
				 conn.connect().close(); //close db connection 
				 }
			 }
			 catch (SQLException se) { // Handle errors for JDBC
				 se.printStackTrace();
			 }
		 }
	
	
	// INSERT INTO METHOD
	public void insertRecords(BankRecords[] robjs) {
		
		//Prepared statements
		PreparedStatement updateRecords = null;
		
		
	  try {
	  // Execute a query
	  System.out.println("Inserting records into the table...");
	  stmt = conn.connect().createStatement();
	  String sql = null;
	  
  	  // Include all object data to the database table
	  for (int i = 0; i < robjs.length; ++i) {
		  // finish string assignment to insert all object data 
		  
		  // insert object with prepared statements
	      sql = "INSERT INTO t_trut_tab(id, income, pep) " +
	    	       "VALUES (' "+robjs[i].getId()+" ', ' "+robjs[i].getIncome()+" ', ' "+robjs[i].getPep().charAt(0)+" ' )";
	      // was recieving errer that the getPep was too long for sql bd, changing to only accept first char seems to fix

	      stmt.executeUpdate(sql); 
		
		  }
	  conn.connect().close();
		  } catch (SQLException se) { se.printStackTrace();  }
	 }// INSERT INTO METHOD
	
	
	// RETRIEVE RECORDS FROM DB
	public ResultSet retrieveRecords() {
		ResultSet rs = null;

		try {
			System.out.println("Retrieving records from table...");
			stmt = conn.connect().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "SELECT pid, id, income, pep FROM t_trut_tab ORDER BY pep DESC";
		
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			conn.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;

	} // RETRIEVE RECORDS
}
