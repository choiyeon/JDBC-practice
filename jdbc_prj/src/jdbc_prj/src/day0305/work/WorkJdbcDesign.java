package day0305.work;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class WorkJdbcDesign extends JFrame {
	
	private JLabel jlNumber, jlName, jlImage, jlAge, jlDate, jlbImg;
	private JTextField jtfNumber, jtfName, jtfAge, jtfDate;
	private JComboBox<String> jcbImg;
	private JList<String> jl;
	
	private DefaultListModel<String> dlm;
	private DefaultComboBoxModel<String> dcbm;
	
	private JButton jbtnAdd, jbtnModify, jbtnDelete, jbtnExit;
	

	public WorkJdbcDesign() {
		design();
	}//WorkJDBCDesign
	
	private void design() {
		
		jlNumber = new JLabel("번호");
		jlName = new JLabel("이름");
		jlImage = new JLabel("이미지");
		jlAge = new JLabel("나이");
		jlDate = new JLabel("입력일 ");
		
		ImageIcon ii = new ImageIcon("D:/dev/workspace/java_se/image/img4.png");
		jlbImg = new JLabel(ii); //이미지ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ
		
		jtfNumber = new JTextField(10);
		jtfName = new JTextField(10);
		jtfAge = new JTextField(10);
		jtfDate = new JTextField(10);
		
		dcbm = new DefaultComboBoxModel<String>();
		jcbImg = new JComboBox<String>(dcbm);
		
		dcbm.addElement("img1.png");
		dcbm.addElement("img2.png");
		dcbm.addElement("img3.png");
		dcbm.addElement("img4.png");
		jcbImg.setSelectedIndex(3);
		
		dlm = new DefaultListModel<String>();
		jl = new JList<String>(dlm);
		
		jbtnAdd = new JButton("추가");
		jbtnModify = new JButton("변경");
		jbtnDelete = new JButton("삭제");
		jbtnExit = new JButton("종료");
		
		
		setLayout(null);
		
		add(jlNumber);
		add(jlName);
		add(jlImage);
		add(jlAge);
		add(jlDate);
		
		add(jlbImg);
		
		add(jtfNumber);
		add(jtfName);
		add(jtfAge);
		add(jtfDate);
		
		add(jcbImg);
		add(jl);
		
		add(jbtnAdd);
		add(jbtnModify);
		add(jbtnDelete);
		add(jbtnExit);
		
		
		jlNumber.setBounds(20,20,50,15);
		jlName.setBounds(20,60,50,15);
		jlImage.setBounds(20,100,50,15);
		jlAge.setBounds(20,140,50,15);
		jlDate.setBounds(20,180,50,15);
		
		jlbImg.setBounds(455,15,175,185);
		
		jtfNumber.setBounds(70,15,100,25);
		jtfName.setBounds(70,55,100,25);
		jtfAge.setBounds(70,135,100,25);
		jtfDate.setBounds(70,175,100,25);
		
		jtfNumber.setEditable(false);
		jtfDate.setEditable(false); //번호, 입력일 읽기전용
		
		jcbImg.setBounds(70,95,100,25); //콤보박스
		jl.setBounds(190,15,250,185); //리스트
		
		jbtnAdd.setBounds(50,215,60,30);
		jbtnModify.setBounds(130,215,60,30);
		jbtnDelete.setBounds(210,215,60,30);
		jbtnExit.setBounds(290,215,60,30);
		
		//이벤트
		WorkJdbcEvent wJBTNe = new WorkJdbcEvent(this);
		jbtnAdd.addActionListener(wJBTNe);
		jbtnModify.addActionListener(wJBTNe);
		jbtnDelete.addActionListener(wJBTNe);
		jbtnExit.addActionListener(wJBTNe);
		jcbImg.addActionListener(wJBTNe);
		
		jl.addMouseListener(wJBTNe); //리스트 마우스리스너
		
		addWindowListener(wJBTNe);
		
		
		
		setVisible(true);
		setResizable(false);//창 크기 변경 불가능
		setBounds(200,200,665,300);
		
	}//design

	public static void main(String[] args) {
		new WorkJdbcDesign();
	}//main

	public JLabel getJlNumber() {
		return jlNumber;
	}

	public JLabel getJlName() {
		return jlName;
	}

	public JLabel getJlImage() {
		return jlImage;
	}

	public JLabel getJlAge() {
		return jlAge;
	}

	public JLabel getJlDate() {
		return jlDate;
	}

	public JLabel getJlbImg() {
		return jlbImg;
	}

	public JTextField getJtfNumber() {
		return jtfNumber;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfAge() {
		return jtfAge;
	}

	public JTextField getJtfDate() {
		return jtfDate;
	}

	public JComboBox<String> getJcbImg() {
		return jcbImg;
	}

	public JList<String> getJl() {
		return jl;
	}

	public DefaultListModel<String> getDlm() {
		return dlm;
	}

	public DefaultComboBoxModel<String> getDcbm() {
		return dcbm;
	}

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public JButton getJbtnModify() {
		return jbtnModify;
	}

	public JButton getJbtnDelete() {
		return jbtnDelete;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

	
}//class
