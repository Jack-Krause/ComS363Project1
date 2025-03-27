package project1_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
				testModify(stmt);
				
				modifyRecords(stmt);
				testModify(stmt);
				
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
						delete from register\r\n
						where course_number IN (\r\n
						select cnum from (\r\n
							select c.cnumber as cnum\r\n
							from courses c\r\n
							join (\r\n
								select level, department_code, min(cnumber) as min_c\r\n
								from courses\r\n
								group by level, department_code\r\n
							) as keep\r\n
						 	on c.level = keep.level\r\n
							and c.department_code = keep.department_code\r\n
							where c.cnumber <> keep.min_c\r\n
							) as t\r\n
						);
						""";
				String dd = 
						"""
						delete from courses\r\n
						where cnumber in (\r\n
							select cnum from (\r\n
								select c.cnumber as cnum\r\n
								from courses c\r\n
								join (\r\n
									select level, department_code, min(cnumber) as min_c\r\n
									from courses\r\n
									group by level, department_code\r\n
								) as keep\r\n
								on c.level = keep.level\r\n
								and c.department_code = keep.department_code\r\n
								where c.cnumber <> keep.min_c\r\n
							) as t\r\n
						);

						""";
				
				
				st.addBatch(modifyOne);
				st.addBatch(modifyTwo);
				st.addBatch(modifyThree);
				st.addBatch(modifyFour);
				st.addBatch(dd);
				
				int[] res = st.executeBatch();
				st.clearBatch();
				
				for (int i: res) {
					System.out.print(i + " ");
				}
				System.out.println();
			}
		}
		
		
		private static void testModify(Statement st) throws SQLException {
			if (st != null) {
				String selectFour =
						"""
						select c.*, r.*\r\n
						from courses c\r\n
						left join register r on r.course_number = c.cnumber
						join\r\n
						(\r\n
							select level, department_code, min(cnumber) as min_c\r\n
							from courses\r\n
							group by level, department_code\r\n
						) as keep\r\n
						on c.level = keep.level\r\n
						and c.department_code = keep.department_code\r\n
						where c.cnumber <> keep.min_c;
						""";
				
				ResultSet rs = st.executeQuery(selectFour);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				
				while (rs.next()) {
				    for (int i = 1; i <= columnsNumber; i++) {
				        if (i > 1) System.out.print(", ");
				        String columnValue = rs.getString(i);
				        System.out.println(columnValue);
				        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
				    }
				    System.out.println();
				    System.out.println();
				}
				
				
			}
		}
		
				
} // class
