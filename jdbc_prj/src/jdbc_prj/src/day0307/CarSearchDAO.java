package day0307;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;

public class CarSearchDAO {

	private static CarSearchDAO csDAO;

	private CarSearchDAO() {

	}// CarSearchDAO

	public static CarSearchDAO getInstance() {
		if (csDAO == null) {
			csDAO = new CarSearchDAO();
		} // end if
		return csDAO;
	}// getInstance

	public List<CarVO> selectCarList(String maker) throws SQLException {
		List<CarVO> list = new ArrayList<CarVO>();

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 1.
		try {
			// 2.
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			// 3.
			StringBuilder selectCar = new StringBuilder();
			selectCar
			.append(" select country, maker, model, car_year, price, car_option  ")
			.append(" from (select cc.country, cc.maker, cmo.model, cmo.car_year, cmo.price, cmo.car_option,  ")
			.append(" row_number() over(order by hiredate desc) rnum ")
			.append(" from   CAR_MAKER cma, CAR_COUNTRY cc, CAR_MODEL cmo  ")
			.append(" where  (cma.maker(+)=cc.maker and cmo.model(+)=cma.model) and cc.maker=?)  ")
			.append(" where rnum between 1 and 10  ");
			
			pstmt = con.prepareStatement(selectCar.toString());
			// 4.
			pstmt.setString(1, maker);
			// 5.
			rs = pstmt.executeQuery();
			
			CarVO cVO = null;
			while(rs.next()) {
				cVO = new CarVO( rs.getString("country"), rs.getString("maker"), rs.getString("model"), 
						rs.getString("car_year"), rs.getString("car_option"), rs.getInt("price") );
				list.add(cVO);
			}//end while
		} finally {
			// 6.
			dbCon.dbClose(rs, pstmt, con);
		} // end finally

		return list;
	}// selectCarList

//	public static void main(String[] args) throws SQLException {
//		System.out.println(CarSearchDAO.getInstance().selectCarList("현대"));
//
//	}// main

}// class
