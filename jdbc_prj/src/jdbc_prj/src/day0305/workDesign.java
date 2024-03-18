package day0305;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class workDesign extends JFrame {
	private JTextField jtfnum, jtfage, jtfname, jtfdate;
	private JComboBox<String> jcbimg;
	private JButton jbtadd, jbtmodify, jbtremove, jbtexit;
	private JList<String> output;
	private JLabel jlimg;
	
	public workDesign() {
		super("work");
		jtfnum = new JTextField(10);
		jtfnum.setEditable(false);
		jtfage = new JTextField(10);
		jtfname = new JTextField(10);
		jtfdate = new JTextField(10);
		jtfdate.setEditable(false);
		
		String[] img = {"img1.png", "img2.png", "img3.png", "img4.png"};
		jcbimg = new JComboBox<String>(img);
		
		jbtadd = new JButton("추가");
		jbtmodify = new JButton("변경");
		jbtremove = new JButton("삭제");
		jbtexit = new JButton("종료");
		
		output = new JList<String>();
		
		//배치
		JPanel jpSouth = new JPanel();
		jpSouth.add(jbtadd);
		jpSouth.add(jbtmodify);
		jpSouth.add(jbtremove);
		jpSouth.add(jbtexit);
		
		JPanel jpWest = new JPanel(new GridLayout(5, 2));
		jpWest.add(new JLabel("번호"));
		jpWest.add(jtfnum);
		jpWest.add(new JLabel("이름"));
		jpWest.add(jtfname);
		jpWest.add(new JLabel("이미지"));
		jpWest.add(jcbimg);
		jpWest.add(new JLabel("나이"));
		jpWest.add(jtfage);
		jpWest.add(new JLabel("입력일"));
		jpWest.add(jtfdate);
		
		jpWest.setPreferredSize(new Dimension(200, jpWest.getPreferredSize().height));
		
		JScrollPane jspOutput = new JScrollPane(output);
		jspOutput.setBorder(new TitledBorder("출력 결과"));
		
		add("South", jpSouth);
		add("West", jpWest);
		add("East", jspOutput);		
		
		//이벤트 추가
		workEvent we = new workEvent(this);
		jtfage.addActionListener(we);
		jtfname.addActionListener(we);
//		jtfimg.addActionListener(we);
		
		jbtadd.addActionListener(we);
		jbtmodify.addActionListener(we);
		jbtremove.addActionListener(we); 
		jbtexit.addActionListener(we);

		addWindowListener(we);
		
		setBounds(100, 100, 500, 300);
		setVisible(true);
	}//workDesign
	
	public JTextField getJtfnum() {
		return jtfnum;
	}

	public JTextField getJtfage() {
		return jtfage;
	}

	public JTextField getJtfname() {
		return jtfname;
	}

	public JComboBox<String> getJcbimg() {
		return jcbimg;
	}

	public JTextField getJtfdate() {
		return jtfdate;
	}

	public JButton getJbtadd() {
		return jbtadd;
	}

	public JButton getJbtmodify() {
		return jbtmodify;
	}

	public JButton getJbtremove() {
		return jbtremove;
	}

	public JButton getJbtexit() {
		return jbtexit;
	}

	public JList<String> getOutput() {
		return output;
	}

	public JLabel getJlimg() {
		return jlimg;
	}

	public static void main(String[] args) {
		new workDesign();
	}//main

}//class
