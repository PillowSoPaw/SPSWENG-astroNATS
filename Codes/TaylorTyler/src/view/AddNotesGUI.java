package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AddNotesGUI extends JFrame
{
	
	private JTextArea noteTextArea;
	private JLabel charCountLabel;
	private JButton addNoteButton;
	private JButton cancelButton;
	
	public AddNotesGUI() 
	{
		getContentPane().setBackground(new Color(128, 128, 0));
		
		getContentPane().setLayout(null);
		
		noteTextArea = new JTextArea();
		noteTextArea.setBounds(10, 11, 449, 175);
		getContentPane().add(noteTextArea);
		
		charCountLabel = new JLabel("256");
		charCountLabel.setBounds(433, 190, 36, 21);
		getContentPane().add(charCountLabel);
		
		addNoteButton = new JButton("Add Note");
		addNoteButton.setBounds(10, 201, 89, 23);
		getContentPane().add(addNoteButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(109, 201, 89, 23);
		getContentPane().add(cancelButton);
		this.setResizable(false);
		this.setTitle("Add Notes");
		this.setLocationRelativeTo(null);
		this.setSize(475, 275);
		this.setVisible(true);
	}
}
