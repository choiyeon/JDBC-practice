package day0305.work;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WorkJdbcEvent extends WindowAdapter implements ActionListener, MouseListener {
	
	private WorkJdbcDesign wJBTNd;
	
	private JTextField jtfNumber, jtfName, jtfAge, jtfDate;
	
	private JLabel jlbImg;
	private JComboBox<String> jcbImg;
	
	private JList<String> jl;
	private DefaultListModel<String> dlm;
	
	private int selectItemNumber;
	
	
	public WorkJdbcEvent(WorkJdbcDesign wJBTNd) {
		this.wJBTNd = wJBTNd;
		
		jtfNumber = wJBTNd.getJtfNumber();
		jtfName = wJBTNd.getJtfName();
		jtfAge = wJBTNd.getJtfAge();
		jtfDate = wJBTNd.getJtfDate();
		
		jlbImg = wJBTNd.getJlbImg();
		jcbImg = wJBTNd.getJcbImg();
		
		jl = wJBTNd.getJl();
		dlm = wJBTNd.getDlm();
		
		setJl();
		
	}//WorkJDBCEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == wJBTNd.getJcbImg()) { //이미지 콤보박스
			
			setImg();
			
			
		}//end if
		
		if(e.getSource() == wJBTNd.getJbtnAdd()) { //추가
			
			if( jtfName.getText().isEmpty() || jtfAge.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "이름과 나이를 입력해주세요.");
				return;
			}//end if
			
			if( !(jtfAge.getText().matches("[0-9]+")) ) {
				JOptionPane.showMessageDialog(null, "나이의 입력값은 숫자여야합니다.");
				return;
			}//end if
			
			addWork();
			
		}//end if
		
		if(e.getSource() == wJBTNd.getJbtnModify()) { //변경
			
			if(jtfNumber.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "변경할 항목을 선택해주세요");
				return;
			}//end if
			
			if( jtfName.getText().isEmpty() || jtfAge.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "이름과 나이를 입력해주세요.");
				return;
			}//end if
			
			if( !(jtfAge.getText().matches("[0-9]+")) ) {
				JOptionPane.showMessageDialog(null, "나이의 입력값은 숫자여야합니다.");
				return;
			}//end if
			
			modifyWork();
			
		}//end if
		
		if(e.getSource() == wJBTNd.getJbtnDelete()) { //삭제
			
			if(jtfNumber.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "삭제할 항목을 선택해주세요");
				return;
			}//end if
			
			if( jtfName.getText().isEmpty() || jtfAge.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "이름과 나이를 입력해주세요.");
				return;
			}//end if
			
			if( !(jtfAge.getText().matches("[0-9]+")) ) {
				JOptionPane.showMessageDialog(null, "나이의 입력값은 숫자여야합니다.");
				return;
			}//end if
			
			deleteWork();
			
		}//end if
		
		if(e.getSource() == wJBTNd.getJbtnExit()) { //종료
			wJBTNd.dispose();
		}//end if
		
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent e) {
		wJBTNd.dispose();
	}//windowClosing
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == jl) {
			setTextFiled();
		}//end if
		
	}//mouseClicked

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 라벨에 이미지 세팅
	 */
	public void setImg() {
		
		String imgUrl = "D:/dev/workspace/java_se/image/"
				+ jcbImg.getSelectedItem().toString().trim();
		ImageIcon ii = new ImageIcon(imgUrl);
		jlbImg.setIcon(ii);
		
	}//setImg
	
	/**
	 * 추가
	 */
	public void addWork() {
		
		int num = 1;
		if(!dlm.isEmpty()) {
			String[] tempArr = dlm.get(dlm.getSize()-1).split(",");
			num = Integer.parseInt(tempArr[0])+1;
		}//end if
		
		String name = jtfName.getText();
		String img = jcbImg.getSelectedItem().toString();
		int age = Integer.parseInt(jtfAge.getText())+1;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String date = sdf.format(new Date());
		
		String temp = num + "," + name + "," + img + "," + age + "," + date;
		
		WorkJdbcVO wjVO = new WorkJdbcVO(num, age, name, img, null);
		
		WorkJdbcDAO wjDAO = WorkJdbcDAO.getInstance();
		int cnt=0;
		try {
			cnt = wjDAO.insertWorkJdbc(wjVO);
			//JList에 추가
			dlm.addElement(temp);
			JOptionPane.showMessageDialog(null, cnt + "건 추가되었습니다.");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}//end catch
		
	}//addWork
	
	
	/**
	 * 변경
	 */
	public void modifyWork() {
		
		int num = selectItemNumber;
		String name = jtfName.getText();
		String img = jcbImg.getSelectedItem().toString();
		int age = Integer.parseInt(jtfAge.getText());
		String date = jtfDate.getText();
				
		String temp = num + "," + name + "," + img + "," + age + "," + date;
		
		WorkJdbcVO wjVO = new WorkJdbcVO(num, age, name, img, null);
		System.out.println(num);
		WorkJdbcDAO wjDAO = WorkJdbcDAO.getInstance();
		int cnt=0;
		try {
			cnt = wjDAO.updateWorkJdbc(wjVO);
			//JList에 수정사항 적용
			dlm.setElementAt(temp, jl.getSelectedIndex());
			JOptionPane.showMessageDialog(null, cnt + "건 변경되었습니다.");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}//end catch
		
	}//modifyWork
	
	/**
	 * 삭제
	 */
	public void deleteWork() {
		
		switch(JOptionPane.showConfirmDialog(null, "정말 삭제할까요?")) {
		case JOptionPane.NO_OPTION : 
		case JOptionPane.CANCEL_OPTION : return;
		}//end switch
		
		WorkJdbcDAO wjDAO = WorkJdbcDAO.getInstance();
		int num = selectItemNumber;
		
		int cnt = 0;
		try {
			cnt = wjDAO.deleteWorkJdbc(num);
			//JList에서 삭제
			dlm.remove(jl.getSelectedIndex());
			JOptionPane.showMessageDialog(null, cnt + "건 삭제되었습니다.");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}//end catch
		
	}//deleteWork
	
	/**
	 * jlist에서 값을 선택하면, jtf에 정보를 세팅해줌
	 */
	public void setTextFiled() {
		
		String[] tempArr = jl.getSelectedValue().split(",");
		
//		String imgIndex = tempArr[2].substring(3, 4);
		int imgIndex = 3;
		String imgName = tempArr[2];
		switch(imgName) {
		case "img1.png" : imgIndex = 0; break;
		case "img2.png" : imgIndex = 1; break;
		case "img3.png" : imgIndex = 2; break;
		case "img4.png" : 
		}//end switch
				
		selectItemNumber = Integer.parseInt(tempArr[0]);
		
		jtfNumber.setText(tempArr[0]);
		jtfName.setText(tempArr[1]);
//		jcbImg.setSelectedIndex(Integer.parseInt(imgIndex)-1);
		jcbImg.setSelectedIndex(imgIndex);
		jtfAge.setText(tempArr[3]);
		jtfDate.setText(tempArr[4]);
		
	}//setText
	
	public void setJl() {
		
		WorkJdbcDAO wjDAO = WorkJdbcDAO.getInstance();
		
		int number = 0;
		String name = "";
		String img = "";
		int age = 0;
		String date = "";
		String temp = "";
		
		try {
			for(WorkJdbcVO wjVO : wjDAO.getWorkJdbc()) {
				
				number = wjVO.getNum();
				name = wjVO.getName();
				img = wjVO.getImage();
				age = wjVO.getAge();
				date = wjVO.getInput_date().toString();
				temp = number + "," + name + "," + img + "," + age + "," + date;
				dlm.addElement(temp);
				
			}//end for
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//setJl
	
}//class
