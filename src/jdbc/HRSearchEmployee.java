package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HRSearchEmployee {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("<검색어>");
		String keyword = scanner.next();

		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. connection 생성
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbURL, "hr", "hr");

			// 3. statement(sql) 준비
			String sql = "select first_name, last_name, email, phone_number, hire_date from EMPLOYEES where first_name like ? or last_name like ?";
			stmt = conn.prepareStatement(sql);

			// 4. binding
			stmt.setString(1, "%"+keyword+"%");
			stmt.setString(2, "%"+keyword+"%");
			rs = stmt.executeQuery();

			// 5. 결과 출력

			while (rs.next()) {

				String firstname = rs.getString(1);
				String lastname = rs.getString(2);
				String email = rs.getString(3);
				String phone_number = rs.getString(4);
				String hiredate = rs.getString(5);

				System.out.println(firstname + ":" + lastname + ":" + email
						+ ":" + phone_number + ":" + hiredate + ":");

			}

			System.out.println("연결 성공!!!");
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("oracle library가 없습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원정리
			
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			scanner.close();
			
		}
	}

}
