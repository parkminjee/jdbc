package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest2 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. connection 생성
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbURL, "hr", "hr");
			
			//3. statement 생성
			stmt = conn.createStatement();
			
			//4. sql문 날리기
			String sql = "select first_name, last_name from EMPLOYEES";
			rs = stmt.executeQuery(sql);
			
			//5. 결과 출력
			while(rs.next()){
				String f_name = rs.getString(1);
				String name = rs.getString(2);
				
				System.out.println(f_name+" "+name);
			}
			
			System.out.println("연결 성공!!!");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("oracle library가 없습니다.");
		} catch (SQLException e){
			e.printStackTrace();
		}finally{
			//6. 자원정리
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
