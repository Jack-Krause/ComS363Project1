package project1_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
	
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
				
				String query_1 = 
						"""
						select cnumber, cname, AVG(r.grade) as avg_grade\r\n
						from courses as c\r\n
						join register as r on r.course_number = c.cnumber\r\n
						group by c.cnumber, c.cname;
						""";
				
				ResultSet rs = stmt.executeQuery(query_1);
				
				while (rs.next()) {
					int cnumber = rs.getInt("cnumber");
					String cname = rs.getString("cname");
					float avg_grade = rs.getFloat("avg_grade");
					
					System.out.println(cnumber + ", " + cname + ", " + avg_grade);
				}
				
				
			} catch (SQLException e) {
				System.err.println("error clearing tables");
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
