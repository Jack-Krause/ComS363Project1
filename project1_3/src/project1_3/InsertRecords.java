package project1_3;
import java.sql.*;

public class InsertRecords {
	private static Connection connect = null;
	
	public static void main(String[] args) {
		
		try {
			//Set up connection parameters
			String userName = "coms363";
			String password = "password";
			String dbServer = "jdbc:mysql://localhost:3306/project1?allowLoadLocalInfile=true";
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
			
			String set_local_infile = 
					"""
					SET GLOBAL local_infile=1;
					""";
			
			String insert_students = 
					"""
					load data local infile 'C://Users//jackm//OneDrive//CS3630//Project1//students.csv'\r\n
					into table students\r\n
					fields terminated by ','\r\n
					enclosed by '"'\r\n
					lines terminated by '\r\n'\r\n
					ignore 1 rows\r\n
					(sid, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone);
					""";
			
			String insert_departments = 
					"""
					load data local infile 'C://Users//jackm//OneDrive//CS3630//Project1//departments.csv'\r\n
					into table departments\r\n
					fields terminated by ','\r\n
					enclosed by '"'\r\n
					lines terminated by '\r\n'\r\n
					ignore 1 rows\r\n
					(dcode, dname, phone, college);
					""";
			
			
			
			stmt.addBatch(set_local_infile);
			stmt.addBatch(insert_students);
			stmt.addBatch(insert_departments);
			
			System.out.println("inserting into tables");
			int[] res = stmt.executeBatch();
			for (int i : res) System.out.println(i);
			
			stmt.clearBatch();
		} catch (SQLException e) {
			System.err.println("error inserting into tables");
			e.printStackTrace();
		}
		
		finally {
			try {
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

}
