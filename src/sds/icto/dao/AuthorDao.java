package sds.icto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sds.icto.vo.AuthorVo;

public class AuthorDao {
	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection conn = null;

		// 1.드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2. connection 생성
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "icto55", "icto55");

		return conn;
	}

	public List<AuthorVo> fetch() throws ClassNotFoundException, SQLException {
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		
		//1. connection 생성
		Connection conn = getConnection();
		
		//2. statment 생성
		Statement stmt = conn.createStatement();

		//3. sql
		String sql = "select * from author";
		ResultSet rs = stmt.executeQuery(sql);
		
		//4.결과생성
		while (rs.next()) {
			Long id = rs.getLong(1);
			String name = rs.getString(2);
			String bio = rs.getString(3);

			AuthorVo vo = new AuthorVo();
			vo.setId(id);
			vo.setName(name);
			vo.setBio(bio);

			list.add(vo);
		}
		
		
		//5.close
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

		return list;
	}

	public void insert(AuthorVo vo) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. connection 생성
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbURL, "icto55", "icto55");

			// 3. statement(sql) 준비
			String sql = "insert into author values( seq_author.nextval,?,?)";
			stmt = conn.prepareStatement(sql);

			// 4. binding
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getBio());

			stmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("oracle library가 없습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원정리

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

		}
	}
}
