package day0307;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RunCarSearchDAO {

	public void searchCarList() {
		CarSearchDAO csDAO = CarSearchDAO.getInstance();
		
		String inputMaker = JOptionPane.showInputDialog("제조사 입력");
		if(inputMaker != null && !inputMaker.isEmpty()) {
			try {
				List<CarVO> list = csDAO.selectCarList(inputMaker);
				
				StringBuilder output = new StringBuilder();
				output.append(inputMaker).append("입력 결과\n");
				output.append("제조국\t제조사\t모델명\t년식\t가격\t옵션\n");
				
				if(list.isEmpty()) {
					output.append("검색된 데이터가 존재하지 않습니다.");
				}//end if
				
				for(CarVO cVO : list) {
					output
					.append(cVO.getCountry()).append("\t")
					.append(cVO.getMaker()).append("\t")
					.append(cVO.getModel()==null?"":cVO.getModel()).append("\t")
					.append(cVO.getCar_year()==null?"":cVO.getCar_year()).append("\t")
					.append(cVO.getPrice()).append("\t")
					.append(cVO.getCar_option()==null?"":cVO.getCar_option()).append("\n");
				}//end for
				
				JTextArea jta = new JTextArea(output.toString(), 10, 80);
				JScrollPane jsp = new JScrollPane(jta);
				JOptionPane.showMessageDialog(null, jsp);
				
			}catch(SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
	}//searchCarList
	
	public static void main(String[] args){
		RunCarSearchDAO rcDAO = new RunCarSearchDAO();
		rcDAO.searchCarList();
	}// main
	
	
}//class
