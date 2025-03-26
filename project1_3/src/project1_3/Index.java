package project1_3;
import java.sql.*;


public class Index {
	
	private static Connection connect = null;
	
	public static void main(String[] args) {
		
		try {
			//Set up connection parameters
			String userName = "coms363";
			String password = "password";
			String dbServer = "jdbc:mysql://localhost:3306/project1";
			//Set up connection
			connect = DriverManager.getConnection(dbServer,userName,password);
		} catch(Exception e) {
			System.err.println("error connecting");
			e.printStackTrace();
		}
	
		
	//initiate sql statement
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			
			long t = timeQuery(stmt);
			
		} catch (SQLException e) {
			System.err.println("error in queries");
			e.printStackTrace();
		}
		
		finally {
			try {
				// Close connection
				if (stmt != null) {
					stmt.close();
				}
				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private static long timeQuery(Statement st) throws SQLException {
		
		if (st != null) {
			long startTime = System.nanoTime();
		
			Query.queryThree(st);
		
			return (long) 0.00;
			
		}
		
		System.out.println("error: statement is null");
		return (long) 0.00;
	}
	
	
	
	
}//class