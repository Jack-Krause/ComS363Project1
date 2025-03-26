package project1_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyRecords {
	
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
				
				modifyRecords(stmt);
				
			} catch (SQLException e) {
				System.err.println("error in updates");
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
		
		private static void modifyRecords(Statement st) throws SQLException {
			if (st != null) {
				String modifyOne = 
						"""
						update students\r\n
						set name = 'Scott'\r\n
						where ssn = 144673371;
						""";
				
				String modifyTwo = 
						"""
						update major m\r\n
						join students s on s.sid = m.sid\r\n
						set m.name = 'Computer Science', m.level = 'MS'\r\n
						where s.ssn = 144673371;
						""";
				
				String modifyThree =
						"""
						delete from register\r\n
						where regtime = 'Summer2024';
						""";
				
				String modifyFour =
						"""
						delete c\r\n
						from courses c\r\n
						join
						(
							select
							from
							where
							
							union
							
							select
							from
							where
						)
						
						""";
				
				
				st.addBatch(modifyOne);
				st.addBatch(modifyTwo);
				st.addBatch(modifyThree);
				int[] res = st.executeBatch();
				st.clearBatch();
				
				for (int i: res) {
					System.out.print(i + " ");
				}
				System.out.println();
			}
		}
				
} // class
