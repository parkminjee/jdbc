package jdbc;

import java.sql.SQLException;
import java.util.List;

import sds.icto.dao.AuthorDao;
import sds.icto.vo.AuthorVo;

public class AuthorDaoTest {
	public static void main(String[] args) {
		try{
		//insertTest();
		selectTest();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void insertTest() {
		AuthorDao dao = new AuthorDao();

		// 하나 입력
		AuthorVo vo = new AuthorVo();
		vo.setName("장자");
		vo.setBio("");
		dao.insert(vo);

		// 2nd 입력
		AuthorVo vo2 = new AuthorVo();
		vo2.setName("순자");
		vo2.setBio("");
		dao.insert(vo2);
		
		
		System.out.println("OK");
	}

	public static void selectTest() throws ClassNotFoundException, SQLException {
		AuthorDao dao = new AuthorDao();
		List<AuthorVo> list = dao.fetch();
		
		for(AuthorVo vo : list){
			System.out.println(vo.getId()+":"+vo.getName()+":"+vo.getBio());
		}
	}
}
