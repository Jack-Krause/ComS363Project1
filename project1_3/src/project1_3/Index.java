package project1_3;
import java.sql.*;
import java.util.concurrent.TimeUnit;


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
			
			timeQuery(stmt);
			
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
	
	
	private static void timeQuery(Statement st) throws SQLException {
		
		if (st != null) {
			// measure time without index
			long startTimeNoIdx = System.nanoTime();
			Query.queryThree(st);
			long endTimeNoIdx = System.nanoTime();
			long executionTimeNoIdx = endTimeNoIdx - startTimeNoIdx;
			long executionSecondsNoIdx = TimeUnit.MICROSECONDS.convert(executionTimeNoIdx, TimeUnit.NANOSECONDS);

			
			// create index, run query and measure time again
			Query.queryThreeIndexed(st);
			long startTimeIdx = System.nanoTime();
			Query.queryThree(st);
			long endTimeIdx = System.nanoTime();
			long executionTimeIdx = endTimeIdx - startTimeIdx;
			long executionSecondsIdx = TimeUnit.MICROSECONDS.convert(executionTimeIdx, TimeUnit.NANOSECONDS);
			
			System.out.println(
					"Execution time (no indexing): " + 
					"(microseconds: " + 
					executionSecondsNoIdx + 
					"), (nanoseconds: " + 
					executionTimeNoIdx +
					")"
					);
			
			System.out.println(
					"Execution time (with indexing): " + 
					"(microseconds: " + 
					executionSecondsIdx + 
					"), (nanoseconds: " + 
					executionTimeIdx +
					")"
					);
			
			System.out.println("indexing is approx. " + (Math.round(executionTimeNoIdx / executionTimeIdx)) + "x faster");
		
			Query.queryThreeRemoveIndex(st);
			
		} else {
			System.out.println("error: statement is null");
		}
		
	}
	
	
	
	
}//class