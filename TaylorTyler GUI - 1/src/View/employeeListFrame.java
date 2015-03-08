package View;

import java.awt.Dimension;

import javax.swing.*;

public class employeeListFrame extends JFrame{
	
	protected JTextArea test;
	protected JButton btn;
	
	public employeeListFrame(){
		
		test = new JTextArea("hello",5,10);
		test.setPreferredSize(new Dimension(100,100));
		test.setBounds(54,333,200,20);
		
		btn = new JButton("test");
		btn.setBounds(70,400,200,20);
		
		
		
		this.add(test);
		this.add(btn);
		
		this.setLayout(null);
		
		this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
	}
	
	public String getText(){
		return test.getSelectedText();
	}
}
