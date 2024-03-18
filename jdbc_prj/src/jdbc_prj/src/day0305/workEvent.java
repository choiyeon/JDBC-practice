package day0305;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class workEvent extends WindowAdapter implements ActionListener {

	private workDesign wd;
	private JTextField jtfnum, jtfage, jtfname, jtfdate;
	private JComboBox<String> jcbimg;
	private JButton jbtadd, jbtmodify, jbtremove, jbtexit;
	private JList<String> output;
	private JLabel jlimg;

	public workEvent(workDesign wd) {
		this.wd = wd;
	}// workDAO

	@Override
	public void windowClosing(WindowEvent e) {
		wd.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == wd.getJbtadd()) {
			add();
		}
		if (e.getSource() == wd.getJbtmodify()) {
			modify();
		}
		if (e.getSource() == wd.getJbtremove()) {
			remove();
		}
		if (e.getSource() == wd.getJbtexit()) {
			wd.dispose();
		}

	}// actionPerformed

	private void add() {
		String name = wd.getJtfname().getText().trim();
		String age = wd.getJtfage().getText().trim();
		String image = wd.getJcbimg().getToolTipText();
		
		if(name.isEmpty() || age.isEmpty()) {
			JOptionPane.showMessageDialog(null, "이름과 나이를 입력해주세요");
			return;
		}//end if
		
		workVO wVO = new workVO(0, Integer.parseInt(age), name, image, null);
		
		//DB에 추가
		workDAO wDAO = workDAO.getInstance();
		try {
			wDAO.insertWork(wVO);
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}// add

	private void modify() {

	}// modify

	private void remove() {

	}// remove

}// class
