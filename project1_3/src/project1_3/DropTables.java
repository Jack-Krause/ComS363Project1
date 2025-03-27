package project1_3;

import java.sql.*;

public class DropTables {
	
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
				
				testDropTables(stmt);
				dropTables(stmt);
				testDropTables(stmt);
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
		
		
		private static void dropTables(Statement st) throws SQLException {
			if (st != null) {
				String[] dropStatements = { "set FOREIGN_KEY_CHECKS=0", "drop table if exists register",
						"drop table if exists offers", "drop table if exists minor", "drop table if exists major",
						"drop table if exists courses", "drop table if exists degrees", "drop table if exists students",
						"drop table if exists departments", "drop table if exists administer", "set FOREIGN_KEY_CHECKS=1" };
				for (String statement : dropStatements) {
					st.executeUpdate(statement);
				} 
			}
		}
		
		
		private static void testDropTables(Statement st) throws SQLException {
			if (st != null) {
				ResultSet rs = st.executeQuery("show tables;");
				if (!rs.isBeforeFirst())
					System.out.println("successfull dropping of tables");
				else {
					System.out.println("tables still exist");
					while (rs.next())
						System.out.println(rs.getString(1));
				} 
			}
			
		}
		
				
} // class
