package day0304;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TableViewHW extends JFrame implements ActionListener {

	public TableViewHW() {

		TableDAOHW tDAO = TableDAOHW.getInstance();
		try {
			List<String> listAllTable = tDAO.selectAllTab();

			JPanel panel = new JPanel();

			this.setTitle("Table list");
			this.setVisible(true);
			this.setSize(500, 300);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			ButtonGroup group = new ButtonGroup();

			if (listAllTable.isEmpty()) {
				System.out.println("테이블이 없습니다.");
			} else {
				for (String st : listAllTable) {
					JRadioButton radio = new JRadioButton(st);
					group.add(radio);
					panel.add(radio);
				} // end for
			} // end else

			this.add(panel);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// TableView

	@Override
	public void actionPerformed(ActionEvent e) {

	}// actionPerformed

	public static void main(String[] args) {
		new TableViewHW();
	}// main

}// class
