package kr.co.sist.design;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.prepared.evt.ZipcodeSearchDesignEvent;

public class ZipcodeSearchDesign extends JFrame {

	private JTextField jtfDong;
	private JButton jbtnSearch;
	private JTable jtabResult;
	private DefaultTableModel dtmJtabResult;
	
	public ZipcodeSearchDesign() {
		super("우편번호 검색");
		
		Font font = new Font("맑은 고딕", Font.BOLD, 14);
		
		jtfDong = new JTextField(10);
		jbtnSearch = new JButton("검색");
		JLabel jlblDong = new JLabel("동 이름");
		
		jlblDong.setFont(font);
		jtfDong.setFont(font);
		jbtnSearch.setFont(font);
		
		String[] columnName = {"우편번호", "주소"};
		//행이 없고, 컬럼명만 가진 DefaultTableModel를 생성
		dtmJtabResult = new DefaultTableModel(columnName, 0);
		jtabResult = new JTable(dtmJtabResult);
		JScrollPane jspJtaResult = new JScrollPane(jtabResult);
		jspJtaResult.setBorder(new TitledBorder("검색 결과"));
		
		//JTable 컬럼의 넓이 변경.
		jtabResult.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtabResult.getColumnModel().getColumn(1).setPreferredWidth(720);
		
		//배치
		JPanel jpNorth = new JPanel();
		jpNorth.add(new JLabel("동 이름"));
		jpNorth.add(jtfDong);
		jpNorth.add(jbtnSearch);
		
		add("North", jpNorth);
		add("Center", jspJtaResult);
		
		//이벤트 추가
		ZipcodeSearchDesignEvent zsde = new ZipcodeSearchDesignEvent(this);
		jtfDong.addActionListener(zsde);
		jbtnSearch.addActionListener(zsde);
		addWindowListener(zsde);
		
		setBounds(100, 100, 800, 600);
		setVisible(true);
		
	}//ZipcodeSearchDesign
	
	
	public JTextField getJtfDong() {
		return jtfDong;
	}//getJtfDong

	public JButton getJbtnSearch() {
		return jbtnSearch;
	}//getJbtnSearch

	public JTable getJtabResult() {
		return jtabResult;
	}//getJtabResult

	public DefaultTableModel getDtmJtabResult() {
		return dtmJtabResult;
	}//getDtmJtabResult


	public static void main(String[] args) {
		new ZipcodeSearchDesign();
	}//main

}//class
