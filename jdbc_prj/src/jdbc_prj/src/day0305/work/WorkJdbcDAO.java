package day0305.work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;

public class WorkJdbcDAO {
	
	private static WorkJdbcDAO wjDAO;
	
	private WorkJdbcDAO() {
	}//WorkJDBCDAO
	
	public static WorkJdbcDAO getInstance() {
		if(wjDAO==null) {
			wjDAO = new WorkJdbcDAO();
		}//end if
		
		return wjDAO;
	}//getInstance
	
	
	//////////////////////////////
	// PreparedStatementDAO 참고 //
	//////////////////////////////
	
	
	/**
	 * 입력
	 * @param wjVO
	 */
	public int insertWorkJdbc(WorkJdbcVO wjVO) throws SQLException {
		int cnt=0;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		// 2. 커넥션 얻기
			String id="scott";
			String pass="tiger";
			con=dbCon.getConnection(id, pass);
		// 3. 쿼리문 생성객체 얻기 (값이 들어가는 위치는 바인드변수 (?)를 사용한다.)
			String insertWorkJDBC =
				"insert into work_jdbc(num, name, image, age) values(?,?,?,?)";
			pstmt=con.prepareStatement(insertWorkJDBC);
		// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, wjVO.getNum());
			pstmt.setString(2, wjVO.getName());
			pstmt.setString(3, wjVO.getImage());
			pstmt.setDouble(4, wjVO.getAge());	
			
		// 5. 쿼리문 수행 후 결과 얻기 (주의 : Statement의 executeXXX(SQL)는 절대로 사용하지 않음.)
			cnt = pstmt.executeUpdate();
			
		}finally {
		// 6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		}//end finally
		
		return cnt;
	}//insertWorkJdbc
	
	/**
	 * 수정
	 * @param wjVO
	 */
	public int updateWorkJdbc(WorkJdbcVO wjVO) throws SQLException {
		int cnt=0;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		// 2. 커넥션 얻기
			String id="scott";
			String pass="tiger";
			con=dbCon.getConnection(id, pass);
		// 3. 쿼리문 생성객체 얻기 (값이 들어가는 위치는 바인드변수 (?)를 사용한다.)
			String updateWorkJDBC =
				"update work_jdbc set name=?, image=?, age=? where num=?";
			pstmt=con.prepareStatement(updateWorkJDBC);
		// 4. 바인드 변수에 값 설정
			pstmt.setString(1, wjVO.getName());
			pstmt.setString(2, wjVO.getImage());
			pstmt.setInt(3, wjVO.getAge());
			pstmt.setInt(4, wjVO.getNum());
			
		// 5. 쿼리문 수행 후 결과 얻기 (주의 : Statement의 executeXXX(SQL)는 절대로 사용하지 않음.)
			cnt = pstmt.executeUpdate();
			
		}finally {
		// 6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		}//end finally
		
		return cnt;
	}//insertWorkJdbc
	
	/**
	 * 삭제
	 * @param wjVO
	 */
	public int deleteWorkJdbc(int num) throws SQLException {
		int cnt=0;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		// 1. 드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		// 2. 커넥션 얻기
			String id="scott";
			String pass="tiger";
			con=dbCon.getConnection(id, pass);
		// 3. 쿼리문 생성객체 얻기 (값이 들어가는 위치는 바인드변수 (?)를 사용한다.)
			String deleteWorkJDBC =
				"delete from work_jdbc where num=?";
			pstmt=con.prepareStatement(deleteWorkJDBC);
		// 4. 바인드 변수에 값 설정
			pstmt.setInt(1, num);
		// 5. 쿼리문 수행 후 결과 얻기 (주의 : Statement의 executeXXX(SQL)는 절대로 사용하지 않음.)
			cnt = pstmt.executeUpdate();
			
		}finally {
		// 6. 연결 끊기
			dbCon.dbClose(null, pstmt, con);
		}//end finally
		
		return cnt;
	}//insertWorkJdbc
	
	/**
	 * 값 얻어다가 jlist에 넣는용,,,
	 * 집코드DAO참고
	 * @return
	 */
	public List<WorkJdbcVO> getWorkJdbc() throws SQLException {
		List<WorkJdbcVO> list = new ArrayList<WorkJdbcVO>();
		
		DbConnection dbCon = DbConnection.getInstance();
		// 1.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		// 2.
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
		// 3. 쿼리문 생성객체 얻기
			StringBuilder getWorkJdbc = new StringBuilder();
			getWorkJdbc
			.append(" select num, name, image, age, input_date ")
			.append(" from work_jdbc ")
			.append(" order by num "); //숫자 순번대로 보여야 이쁘니까~
			
			pstmt=con.prepareStatement(getWorkJdbc.toString());
			
		// 5. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			WorkJdbcVO sjVO = null;
			while(rs.next()) {
				sjVO = new WorkJdbcVO(rs.getInt("num"),
						rs.getInt("age"),
						rs.getString("name"),
						rs.getString("image"), 
						rs.getDate("input_date"));
				list.add(sjVO);
			}//end while
			
		}finally {
		// 6. 연결끊기
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//insertWorkJdbc

	
}//class
