package day0304;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;
import oracle.jdbc.proxy.annotation.Pre;

public class TableDAO {
	private static TableDAO tDAO;
	
	private TableDAO() {
	}
	
	public static TableDAO getInstance() {
		if(tDAO == null) {
			tDAO=new TableDAO();
		}//end if
		return tDAO;
	}//getInstance
	
	public List<String> selectAllTab()throws SQLException{
		List<String> list=new ArrayList<String>();
		
		DbConnection dbCon=DbConnection.getInstance();
		
		String id="scott";
		String pass="tiger";
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=dbCon.getConnection(id, pass);
			String selectTab="select tname from tab";
			
			pstmt=con.prepareStatement(selectTab);
			
			String tname="";
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				tname=rs.getString("tname");
				list.add(tname);
			}//end while
			
		}finally {
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectAllTab
}//class






