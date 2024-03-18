package day0305;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import kr.co.sist.dao.DbConnection;

public class workDAO {

	private static workDAO wDAO;

	private workDAO() {

	}// workDAO

	public static workDAO getInstance() {
		if (wDAO == null) {
			wDAO = new workDAO();
		}
		return wDAO;
	}// getInstance

	public void insertWork(workVO wVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			String insertWork = "insert into work_jdbc(num, name, img, age, input_date) " + "values (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(insertWork);

			pstmt.setInt(1, wVO.getNum());
			pstmt.setString(2, wVO.getName());
			pstmt.setString(3, wVO.getImg());
			pstmt.setInt(4, wVO.getAge());
			pstmt.setDate(5, wVO.getInput_date());

			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	}// insertWork

	public int updateWork(workVO wVO) throws SQLException {
		int cnt = 0;

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			StringBuilder updateWork = new StringBuilder();
			updateWork
			.append(" update work_jdbc ")
			.append(" set name=?, img=?, age=? ")
			.append(" where num=? ");
			pstmt = con.prepareStatement(updateWork.toString());

			pstmt.setString(1, wVO.getName());
			pstmt.setString(2, wVO.getImg());
			pstmt.setInt(3, wVO.getAge());
			pstmt.setInt(4, wVO.getNum());

			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
		return cnt;
	}//updateWork
	
	public int deleteWork(int num) throws SQLException{
		int cnt = 0;
		
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			String deleteWork = "delete from work_jdbc where num=?";
			pstmt = con.prepareStatement(deleteWork);

			pstmt.setInt(1, num);

			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
		
		return cnt;
	}//deleteWork
}// class
