package project1_3;
import java.sql.*;

public class CreateTables {
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

		}
		
	
		//initiate sql statement
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			
			stmt.addBatch("drop table if exists register;");
			stmt.addBatch("drop table if exists offers;");
			stmt.addBatch("drop table if exists administer;");
			stmt.addBatch("drop table if exists minor;");
			stmt.addBatch("drop table if exists major;");
			stmt.addBatch("drop table if exists courses;");
			stmt.addBatch("drop table if exists degrees;");
			stmt.addBatch("drop table if exists students;");
			stmt.addBatch("drop table if exists departments;");
			
			stmt.executeBatch();
			System.out.println("dropped tables");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			
			String create_department = "CREATE TABLE departments (\r\n" + 
					"	dname varchar(50) not null,\r\n" + 
					"	dcode integer not null,\r\n" + 
					"   phone varchar(10),\r\n" + 
					"   college varchar(20),\r\n" + 
					"   primary key (dcode), \r\n" + 
					"   unique (dname)\r\n" + 
					");";
			
			String create_student = "CREATE TABLE students (\r\n" + 
					"	p_phone varchar(20),\r\n" + 
					"   p_addr varchar(20),\r\n" + 
					"	c_phone varchar(20),\r\n" + 
					"   c_addr varchar(20),\r\n" + 
					"	dob varchar(10),\r\n" + 
					"   gender varchar(1),\r\n" + 
					"	name varchar(20),\r\n" + 
					"	sid integer not null,\r\n" + 
					"	ssn integer not null,\r\n" + 
					"	primary key (ssn),\r\n" + 
					"	unique (sid),\r\n" + 
					");";
			
			
			// To update data in a database, call the executeUpdate(String SQL) method
			stmt.executeUpdate(create_department);	
				
//			stmt.addBatch(create_student);
//			stmt.executeBatch();
			
			System.out.println("Created students table");  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
}