package kr.co.sist.statement.service;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.statement.dao.StatementDAO;
import kr.co.sist.statement.vo.EmployeeVO;

import static java.lang.Integer.parseInt;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import static java.lang.Double.parseDouble;

/**
 * CRUD를 사용하는 클래스
 */
public class RunStatementDAO {

	public void addEmp() {
		String inputData = JOptionPane.showInputDialog("추가할 사원정보를 입력해주세요.\n사원번호, 사원명, 직무, 연봉");
		if (inputData != null) {
			String[] tempData = inputData.split(",");
			if (tempData.length != 4) {
				JOptionPane.showMessageDialog(null, "입력 형식을 확인해주세요");
				return;
			} // end if

			// 입력된 데이터 하나로 묶어 관리
			try {
				EmployeeVO eVO = new EmployeeVO(parseInt(tempData[0]), tempData[1], tempData[2],
						parseDouble(tempData[3]), null);
				// DB에 추가하면 된다.
				StatementDAO sDAO = new StatementDAO();
				try {
					sDAO.insertEmp(eVO);
					JOptionPane.showMessageDialog(null, tempData[0] + "번 사원정보가 추가 되었습니다.");
				} catch (SQLException se) {
					se.printStackTrace();
					String errMsg = "";
					switch (se.getErrorCode()) {
					case 1:
						errMsg = tempData[0] + "번 사원번호가 중복되었습니다.";
						break;
					case 1438:
						errMsg = "연봉은 정수 5자리 실수 2자리로 입력가능합니다.";
						break;
					case 12899:
						errMsg = "사원명과 직무는 한글 3자까지만 가능합니다.";
						break;
					default:
						errMsg = "문제가 발생했습니다.";
					}// end switch
					JOptionPane.showMessageDialog(null, errMsg);
				} // end catch

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "사원번호나 연봉은 숫자형태이어야 한다.");
			} // end catch

		} // end if
	}// addEmp

	public void modifyEmp() {
		String inputData = JOptionPane.showInputDialog("사원정보 변경\n사원번호, 직무, 연봉을 입력하면 사원번호에 해당하는 사원의 직무와 연봉을 변경합니다.");
		if (inputData != null) {
			String[] tempData = inputData.split(",");
			if (tempData.length != 3) {
				JOptionPane.showMessageDialog(null, "입력은 사원번호,직무,연봉의 형식입니다.");
				return;
			} // end if

			try {
				EmployeeVO eVO = new EmployeeVO(parseInt(tempData[0]), null, tempData[1], parseDouble(tempData[2]),
						null);

				// DBMS테이블의 update변경을 수행
				StatementDAO sDAO = new StatementDAO();
				int cnt = sDAO.updateEmp(eVO);// 0~n건 변경

				String msg = tempData[0] + "번 사원은 존재하지 않습니다. 사원번호를 확인해주세요";
				if (cnt != 0) {// 1건이상 변경되었을 때
					msg = tempData[0] + "번 으로 " + cnt + "건 변경 되었습니다.";
				} // end if

				JOptionPane.showMessageDialog(null, msg);

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "사원번호와 연봉은 숫자형태 입니다.");
			} // end catch
			catch (SQLException se) {
				se.printStackTrace();
				String errMsg = "";
				switch (se.getErrorCode()) {
				case 1438:
					errMsg = "연봉은 정수 5자리 실수 2자리로 입력가능합니다.";
					break;
				case 12899:
					errMsg = "직무는 한글 3자까지만 가능합니다.";
					break;
				default:
					errMsg = "문제가 발생했습니다.";
				}// end switch
				JOptionPane.showMessageDialog(null, errMsg);
			} // end catch
		} // end if
	}// modifyEmp

	public void removeEmp() {
		String inputData = JOptionPane.showInputDialog("사원정보 삭제\n사원번호를 입력하면 사원번호에 해당하는 사원의 정보를 삭제합니다.");
		if (inputData == null) {
			JOptionPane.showMessageDialog(null, "사원번호를 입력해주세요.");
			return;
		} // end if

		// 유효성 검증(사원번호는 숫자 4자리)
		if (inputData.length() > 4) {
			JOptionPane.showMessageDialog(null, "사원번호는 4자리입니다.");
			return;
		} // end if

		try {
			int empno = parseInt(inputData);//사원번호의 범위는  : 0 ~ 9999 사이
			
			// DBMS테이블의 delete변경을 수행
			StatementDAO sDAO = new StatementDAO();
			
			int cnt = sDAO.deleteEmp(empno);// 0~n건 변경

			//cnt가 0이면 입력한 사원번호가 없는 번호일때
			String msg = inputData + "번 사원정보를 확인하세요.";
			
			//cnt가 1건 이상일때, 입력한 사원번호가 존재할 때
			if(cnt != 0) {
				msg = inputData + "번 사원정보가 " + cnt + "건 삭제되었습니다.";
			}
			
			JOptionPane.showMessageDialog(null, msg);


		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "사원번호는 4자리 숫자입니다.");
		} catch (SQLException se) {
			se.printStackTrace();
			JOptionPane.showMessageDialog(null, "삭제 중 문제가 발생했습니다.");
		} // end catch
	}// removeEmp

	public void searchALLEmp() {

		// DBMS에서 조회된 결과를 받아서 사용자에게 보여준다.
		StatementDAO sDAO = new StatementDAO();
		try {
			List<EmployeeVO> listAllEmp = sDAO.selectALLEmp();
			StringBuilder output = new StringBuilder();
			output.append("사원번호\t사원명\t직무\t연봉\t입사일\n");
			if (listAllEmp.isEmpty()) {
				output.append("데이터가 없습니다.");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				for (EmployeeVO eVO : listAllEmp) {
					output.append(eVO.getEmpno()).append("\t").append(eVO.getEname()).append("\t").append(eVO.getJob())
							.append("\t").append(eVO.getSal()).append("\t").append(sdf.format(eVO.getHiredate()))
							.append("\n");
				} // end for
			} // end else

			JTextArea jta = new JTextArea(output.toString(), 10, 80);
			JScrollPane jsp = new JScrollPane(jta);
			JOptionPane.showMessageDialog(null, jsp);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// searchALLEmp

	public void searchOneEmp() {
		String inputData = JOptionPane.showInputDialog("사원검색\n사원번호를 입력해주세요.");
		if (inputData == null) {
			JOptionPane.showMessageDialog(null, "사원번호를 입력해주세요.");
			return;
		} // end if

		try {
			int empno = parseInt(inputData);
			// DBMS에서 조회된 결과를 받아서 사용자에게 보여준다.
			StatementDAO sDAO = new StatementDAO();

			EmployeeVO eVO = sDAO.selectOneEmp(empno);

			StringBuilder output = new StringBuilder();
			output.append(empno).append("번 사원번호 검색 결과\n=============================\n");
			if (eVO == null) {// 검색결과가 없을 경우
				output.append("사원번호를 확인하세요.");
			} else {
				output.append("사원명 : ").append(eVO.getEname()).append("\n");
				output.append("직무 : ").append(eVO.getJob()).append("\n");
				output.append("연봉 : ").append(eVO.getSal()).append("\n");
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				output.append("입사일 : ").append(sdf.format(eVO.getHiredate()));
			} // end if

			JTextArea jta = new JTextArea(output.toString(), 10, 80);
			JScrollPane jsp = new JScrollPane(jta);
			JOptionPane.showMessageDialog(null, jsp);

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "사원번호는 숫자형태여야 합니다.");
		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch
	}// searchOneEmp

	public void menu() {
		boolean exitFlag = false;

		String inputMenu = "";
		do {
			inputMenu = JOptionPane
					.showInputDialog("메뉴 선택\n1. 사원정보 추가\n2. 사원정보 변경\n3. 사원정보 삭제 \n4. 전체 사원 검색 \n5. 특정 사원 검색 \n6. 종료");

			if (inputMenu != null) {
				switch (inputMenu) {
				case "1":
					addEmp();
					break;
				case "2":
					modifyEmp();
					break;
				case "3":
					removeEmp();
					break;
				case "4":
					searchALLEmp();
					break;
				case "5":
					searchOneEmp();
					break;
				case "6":
					exitFlag = true;
					break;
				default:
					JOptionPane.showMessageDialog(null, "메뉴는 1, 2, 3, 4, 5, 6 중 하나만 입력해주세요");

				}// end switch
			} // end if
		} while (!exitFlag);
	}// menu

	public static void main(String[] args) {
		RunStatementDAO rsDAO = new RunStatementDAO();
		rsDAO.menu();
	}// main

}// class
