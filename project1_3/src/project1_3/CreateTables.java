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
			System.err.println("error connecting");
			e.printStackTrace();
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
			
			System.out.println("dropping tables");
			stmt.executeBatch();
			
			stmt.clearBatch();
		} catch (SQLException e) {
			System.err.println("error clearing tables");
			e.printStackTrace();
		}
		try {
			
			String createDepartment = "CREATE TABLE departments (\r\n" + 
					"	dname varchar(50) not null,\r\n" + 
					"	dcode integer not null,\r\n" + 
					"   phone varchar(10),\r\n" + 
					"   college varchar(20),\r\n" + 
					"   primary key (dcode), \r\n" + 
					"   unique (dname)\r\n" + 
					");";
			
			String createStudent = "CREATE TABLE students (\r\n" + 
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
					"	unique (sid)\r\n" + 
					");";
			
			String createCourses = "CREATE TABLE courses (\r\n" +
					"	cname varchar(50),\n\n" +
    				"	cnumber integer not null,\r\n" +
    				"	description varchar(50),\r\n" +
    				"	credithours integer,\r\n" +
    				"	level varchar(20),\r\n" +
    				"	department_code integer,\r\n" +
    				"	primary key (cnumber),\r\n" +
    				"	foreign key (department_code) references departments(dcode)\r\n" +
    				");";
			
			String createDegrees = 
					"""
					create table degrees (\r\n
						    dgname varchar(50) not null,\r\n
						    level varchar(5) not null,\r\n
						    department_code integer,\r\n
						    primary key (dgname, level),\r\n
						    foreign key (department_code) references departments(dcode)\r\n
						);
					""";
			
			String createRegister = 
					"""
					create table register (\r\n
						sid integer not null,\r\n
						course_number integer not null,\r\n
						regtime varchar(20),\r\n
						grade integer,\r\n
						primary key (sid, course_number),\r\n
						foreign key (sid) references students(sid),\r\n
						foreign key (course_number) references courses(cnumber)\r\n
					);
					""";
			
			
			String createOffers = 
					"""
					create table offers (
						cnumber integer not null,\r\n
						dcode integer not null,\r\n
						primary key (cnumber),\r\n
						foreign key (cnumber) references courses(cnumber),\r\n
						foreign key (dcode) references departments(dcode)\r\n
					);
					""";
			
			String createAdminister = 
					"""
					create table administer (\r\n
						dcode integer not null,\r\n
						dgname varchar(50) not null,\r\n
						foreign key (dcode) references departments(dcode),\r\n
						foreign key (dgname) references degrees(dgname)\r\n
					);
					""";
			
			String createMinor = 
					"""
					create table minor (\r\n
						sid integer not null,\r\n
						name varchar(50) not null,\r\n
						level varchar(5) not null,\r\n
						primary key (sid, name, level),\r\n
						foreign key (sid) references students(sid),\r\n
						foreign key (name, level) references degrees(dgname, level)\r\n
					);
					""";
			
			String createMajor =
					"""
					create table major (\r\n
						sid integer not null,\r\n
						name varchar(50) not null,\r\n
						level varchar(5) not null,\r\n
						primary key (sid, name, level),\r\n
						foreign key (sid) references students(sid),\r\n
						foreign key (name, level) references degrees(dgname, level)\r\n
					);
					""";
    			
			// To update data in a database, call the executeUpdate(String SQL) method
//			stmt.executeUpdate(create_department);	
				
			stmt.addBatch(createStudent);
			stmt.addBatch(createDepartment);
			stmt.addBatch(createCourses);
			stmt.addBatch(createDegrees);
			stmt.addBatch(createRegister);
			stmt.addBatch(createOffers);
			stmt.addBatch(createAdminister);
			stmt.addBatch(createMinor);
			stmt.addBatch(createMajor);
			
			System.out.println("creating tables");  
			stmt.executeBatch();
			stmt.clearBatch();		
			
		} catch (SQLException e) {
			System.err.println("error creating tables");
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