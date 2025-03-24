package project1_3;
import java.sql.*;

public class InsertRecords {
	private static Connection connect = null;
	
	public static void main(String[] args) {
		
		try {
			//Set up connection parameters
			String userName = "coms363";
			String password = "password";
			String dbServer = "jdbc:mysql://localhost:3306/project1";
			//Set up connection
			connect = DriverManager.getConnection(dbServer, userName, password);
		} catch(Exception e) {
			System.err.println("error connecting");
			e.printStackTrace();
		}
		
	
		//initiate sql statement
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			
			System.out.println("Dropping tables");
			stmt.executeBatch();
			
			
			
			stmt.clearBatch();
		} catch (SQLException e) {
			System.err.println("Error Dropping tables");
			e.printStackTrace();
		}
		
	}

}
