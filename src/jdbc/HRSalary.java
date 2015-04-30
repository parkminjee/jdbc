package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HRSalary {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("월급[min max]>");
		int minSalary = scanner.nextInt();
		int maxSalary = scanner.nextInt();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. connection 생성
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbURL, "hr", "hr");
			
			//3. statement 생성
			String sql = "select first_name, last_name, salary from EMPLOYEES where salary > ? and salary < ?";

			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, minSalary);
			ps.setInt(2, maxSalary);
			
			
			//4. sql문 날리기
			rs = ps.executeQuery();
			
			//5. 결과 출력
			while(rs.next()){
				String firstname = rs.getString(1);
				String name = rs.getString(2);
				int salary = rs.getInt(3);
				
				System.out.println(firstname+" "+name+":"+salary);
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
			if(ps!=null){
				try {
					ps.close();
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
