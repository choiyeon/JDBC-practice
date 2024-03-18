package day0306;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import kr.co.sist.dao.DbConnection;

/**
 * Procedure를 호출하는 CallableStatement 사용
 */
public class UseCallableStatement {

	public UseCallableStatement(int num, int num2) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		CallableStatement cstmt = null;
		// 1. 드라이브 로딩
		try {
			// 2. 커넥션 얻기
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			// 3. 쿼리문 생성객체 얻기
			cstmt = con.prepareCall("{ call plus_proc(?,?,?) }");
			// 4. 바인드변수 값 설정
			// in parameter : 값을 입력하기 위한 변수
			cstmt.setInt(1, num);
			cstmt.setInt(2, num2);
			// out parameter : 값을 저장하기 위한 변수
			cstmt.registerOutParameter(3, Types.NUMERIC);
			// 5. 프로시저 실행 후 결과 얻기
			cstmt.execute();
			// out parameter에 저장된 값 얻기
			int result = cstmt.getInt(3);
			System.out.println(num + " + " + num2 + " = " + result);
		} finally {
			// 6. 연결 끊기
			dbCon.dbClose(null, cstmt, con);
		} // end finally
	}// UseCallableStatement

	public static void main(String[] args) throws InterruptedException {
		try {
			for (int i = 0; i < 30; i++) {
				new UseCallableStatement(3, 6);
				Thread.sleep(1000);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// main

}// class
