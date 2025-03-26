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
			
			String setLocalInfile = 
					"""
					SET GLOBAL local_infile=1;
					""";
			
			String insertStudents = 
					"""
					load data local infile 'C://Users//jackm//OneDrive//CS3630//Project1//students.csv'\r\n
					into table students\r\n
					fields terminated by ','\r\n
					enclosed by '"'\r\n
					lines terminated by '\r\n'\r\n
					ignore 1 rows\r\n
					(sid, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone);
					""";
			
			String insertDepartments = 
					"""
					load data local infile 'C://Users//jackm//OneDrive//CS3630//Project1//departments.csv'\r\n
					into table departments\r\n
					fields terminated by ','\r\n
					enclosed by '"'\r\n
					lines terminated by '\r\n'\r\n
					ignore 1 rows\r\n
					(dcode, dname, phone, college);
					""";
			
			String insertCourses = 
					"""
					load data local infile 'C://Users//jackm//OneDrive//CS3630//Project1//courses.csv'\r\n
					into table courses\r\n
					fields terminated by ','\r\n
					enclosed by '"'\r\n
					lines terminated by '\r\n'\r\n
					ignore 1 rows\r\n
					(cnumber, cname, description, credithours, level, department_code);
					""";
			
			String insertDegrees = 
					"""
					load data local infile 'C://Users//jackm//OneDrive//CS3630//Project1//degrees.csv'\r\n
					into table degrees\r\n
					fields terminated by ','\r\n
					enclosed by '"'\r\n
					lines terminated by '\r\n'\r\n
					ignore 1 rows\r\n
					(dgname, level, department_code);
					""";
			
			String insertMajors = 
					"""
					load data local infile 'C://Users//jackm//OneDrive//CS3630//Project1//major.csv'\r\n
					into table major\r\n
					fields terminated by ','\r\n
					enclosed by '"'\r\n
					lines terminated by '\r\n'\r\n
					ignore 1 rows\r\n
					(sid, name, level);
					""";
			
			String insertMinors =
					"""
					load data local infile 'C://Users//jackm//OneDrive//CS3630//Project1//minor.csv'\r\n
					into table minor\r\n
					fields terminated by ','\r\n
					enclosed by '"'\r\n
					lines terminated by '\r\n'\r\n
					ignore 1 rows\r\n
					(sid, name, level);
					""";
			
			String insertRegisters =
					"""
					load data local infile 'C://Users//jackm//OneDrive//CS3630//Project1//register.csv'\r\n
					into table register\r\n
					fields terminated by ','\r\n
					enclosed by '"'\r\n
					lines terminated by '\r\n'\r\n
					ignore 1 rows\r\n
					(sid, course_number, regtime, grade);
					""";
		
			
			stmt.addBatch(setLocalInfile);
			stmt.addBatch(insertStudents);
			stmt.addBatch(insertDepartments);
			stmt.addBatch(insertCourses);
			stmt.addBatch(insertDegrees);
			stmt.addBatch(insertMajors);
			stmt.addBatch(insertMinors);
			stmt.addBatch(insertRegisters);
			
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
