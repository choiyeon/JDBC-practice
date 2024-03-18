package day0304;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class TableViewEvent extends WindowAdapter implements ActionListener {
	
	private TableView tv;
	
	public TableViewEvent(TableView tv) {
		this.tv=tv;
		
	}//TableViewEvent
	public JRadioButton[] createJRadio() {
		JRadioButton[] jrb=null;
		
		
		TableDAOHW tDAO=TableDAOHW.getInstance();
		ButtonGroup bg=tv.getBg();
		try {
			List<String> list=tDAO.selectAllTab();
			jrb=new JRadioButton[list.size()];
			
			String tname="";
			for(int i= 0 ; i < list.size(); i++ ) {
				tname=list.get(i);
				jrb[i]=new JRadioButton( tname );
				jrb[i].addActionListener(this);
				bg.add(jrb[i]);
			}//end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(tv, "테이블 검색중 문제 발생");
			e.printStackTrace();
		}//end catch
		
		return jrb;
	}//
	
	@Override
	public void windowClosing(WindowEvent e) {
		tv.dispose();
	}//windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {
		JOptionPane.showMessageDialog(tv, ae.getActionCommand()+"테이블을 선택하셨습니다.");
	}//actionPerformed

}//class
