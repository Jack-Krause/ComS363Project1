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
				
				queryOne(stmt);
				queryTwo(stmt);
				queryThree(stmt);
				
				
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
		
		protected static void queryOne(Statement st) throws SQLException {
			if (st != null) {
				
				String query1 = 
						"""
						select cnumber, cname, AVG(r.grade) as avg_grade\r\n
						from courses as c\r\n
						join register as r on r.course_number = c.cnumber\r\n
						group by c.cnumber, c.cname;
						""";
				
				ResultSet rs = st.executeQuery(query1);
				while (rs.next()) {
					int cNumber = rs.getInt("cnumber");
					String cName = rs.getString("cname");
					float avgGrade = rs.getFloat("avg_grade");
					
					System.out.println(cNumber + ", " + cName + ", " + avgGrade);
				}
				
			}
		}
		
		protected static void queryTwo(Statement st) throws SQLException {
			if (st != null) {
				
				String query2 = 
						"""
						select count(distinct(s.sid)) as female_count\r\n
						from (\r\n
							select maj.sid\r\n
							from major as maj\r\n
							join degrees as deg\r\n
							on maj.name = deg.dgname\r\n
							and maj.level = deg.level\r\n
							join departments as dept\r\n
							on deg.department_code = dept.dcode\r\n
							where dept.college = 'LAS'\r\n
							union\r\n
							
							select min.sid\r\n
							from minor as min\r\n
							join degrees as deg\r\n
							on min.name = deg.dgname\r\n
							and min.level = deg.level\r\n
							join departments as dept\r\n
							on deg.department_code = dept.dcode\r\n
							where dept.college = 'LAS'\r\n
						) as union_degrees\r\n
						join students as s\r\n
						on union_degrees.sid = s.sid\r\n
						where s.gender = 'F';
						""";
				
				ResultSet rs = st.executeQuery(query2);
				while (rs.next()) {
					System.out.println(rs.getInt("female_count"));
					
				}
				
			}
		}
		
		
		protected static void queryThree(Statement st) throws SQLException {
			if (st != null) {
				
				String query3 =
						"""
						with deg_stu as (\r\n
							select name as degname, level, sid\r\n
							from major\r\n
							union\r\n
							select name as degname, level, sid\r\n
							from minor\r\n
						)\r\n
						select ds.degname, ds.level\r\n
						from deg_stu ds\r\n
						join students s\r\n
						on ds.sid = s.sid\r\n
						group by ds.degname, ds.level\r\n
						having sum(case when s.gender = 'M' then 1 else 0 end) >\r\n
							   sum(case when s.gender = 'F' then 1 else 0 end);
						""";
				
				ResultSet rs = st.executeQuery(query3);
				while (rs.next()) {
					System.out.println(rs.getString("degname") + ", " + rs.getString("level"));
					
				}
				
			}
		}

} // class
