package day0304;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class TableView extends JFrame{
	private JRadioButton[] jrb;
	private ButtonGroup bg;
	
	public TableView() {
		super("테이블 리스트");
		
		bg=new ButtonGroup();
		TableViewEvent tve=new TableViewEvent(this);
		addWindowListener(tve);
		jrb=tve.createJRadio();
		
		setLayout(new FlowLayout());
		for(JRadioButton tempJrb : jrb) {
			add( tempJrb );
		}//end for
		
		setSize(800, 300);
		setVisible(true);
		
	}//TableView
	
	public JRadioButton[] getJrb() {
		return jrb;
	}//getJrb

	public ButtonGroup getBg() {
		return bg;
	}//getBg

	public static void main(String[] args) {
		new TableView();
	}//main

}//class
