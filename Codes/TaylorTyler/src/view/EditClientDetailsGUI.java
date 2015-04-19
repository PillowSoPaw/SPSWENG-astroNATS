package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditClientDetailsGUI extends JFrame
{
	
	private ManageClientsGUI mainFrame;
	public Border blackline;
	private JTextArea firstNameTextField;
	private JTextArea middleNameTextField;
	private JTextArea lastNameTextField;
	private JTextArea emailTextField;
	private JTextArea addressTextField;
	private JTextArea contactNumberTextField;
	
	private JComboBox dayComboBox;
	private JComboBox monthComboBox;
	private JComboBox yearComboBox;
	private JComboBox genderComboBox;
	
	private JButton saveButton;
	
	private int currYear;
	private JButton cancelButton;
	
	//add the parameters inside action listener of ManageClientsGUI
	public EditClientDetailsGUI(/*String firstname, String middlename, String lastname, String email, Date birthday, String address, String contactNo, String gender*/)
	{
		getContentPane().setForeground(Color.WHITE);
		setResizable(false);
		//this.mainFrame = mainFrame; //Pass the view clients GUI
		
		//Calendar stuff
		currYear = Calendar.getInstance().get(Calendar.YEAR);
		int currDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int currMonth = Calendar.getInstance().get(Calendar.MONTH);

		String[] cmbDayItems = new String[31];
		String[] cmbMonthItems = { "January", "February", "March", "April", "May",
                "June", "July", "August", "September",
                "October", "November", "December" };
		String[] cmbYearItems = new String[151];
		
		String[] genderItems = {"Male", "Female"};
		
		for (int i = 0; i < 31; i++) {
			cmbDayItems[i] = new String("" + (i + 1));
		}
		
		for (int i = 0; i < 151; i++) {
			cmbYearItems[i] = new String("" + ((currYear - 151) + i + 1));
		}
		
		
		
		//Calendar stuff
		blackline = BorderFactory.createLineBorder(Color.black);
		//this.title = "View Clients";
		getContentPane().setBackground(new Color(128, 128, 0));
		getContentPane().setLayout(null);
		this.setSize(411, 400);
		this.setLocationRelativeTo(null);
		this.setTitle("Edit Client Details");
		//this.setBorder(blackline);
		
		//JPanel mainPanel = new JPanel();
		//this.setBackground(new Color(240, 240, 240));
		//this.setBorder(new LineBorder(new Color(0, 0, 0)));
		//mainPanel.setBounds(10, 11, 374, 350);
		//mainPanel.setLayout(null);
		setVisible(true);
		
		// JLabels
		
		JLabel firstNameLabel = new JLabel("First Name*:");
		firstNameLabel.setForeground(Color.WHITE);
		firstNameLabel.setBounds(18, 34, 93, 14);
		getContentPane().add(firstNameLabel);
		
		JLabel middleNameLabel = new JLabel("Middle Name:");
		middleNameLabel.setForeground(Color.WHITE);
		middleNameLabel.setBounds(18, 64, 93, 14);
		getContentPane().add(middleNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name*:");
		lastNameLabel.setForeground(Color.WHITE);
		lastNameLabel.setBounds(18, 94, 93, 14);
		getContentPane().add(lastNameLabel);
		
		JLabel separatorLabel = new JLabel("__________________________________________________");
		separatorLabel.setBounds(18, 111, 359, 14);
		getContentPane().add(separatorLabel);
		
		JLabel emailLabel = new JLabel("E-mail*:");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setBounds(18, 136, 46, 14);
		getContentPane().add(emailLabel);
		
		JLabel birthdayLabel = new JLabel("Birthday (MM/DD/YY):");
		birthdayLabel.setForeground(Color.WHITE);
		birthdayLabel.setBounds(18, 167, 117, 14);
		getContentPane().add(birthdayLabel);
		
		JLabel addressLabel = new JLabel("Address*:");
		addressLabel.setForeground(Color.WHITE);
		addressLabel.setBounds(18, 197, 117, 14);
		getContentPane().add(addressLabel);
		
		JLabel contactNumberLabel = new JLabel("Contact Number*:");
		contactNumberLabel.setForeground(Color.WHITE);
		contactNumberLabel.setBounds(18, 227, 117, 14);
		getContentPane().add(contactNumberLabel);
		
		JLabel genderLabel = new JLabel("Gender*:");
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setBounds(18, 257, 117, 14);
		getContentPane().add(genderLabel);
		
		//Text Areas
		
		firstNameTextField = new JTextArea();
		firstNameTextField.setBounds(141, 29, 231, 19);
		getContentPane().add(firstNameTextField);
		
		middleNameTextField = new JTextArea();
		middleNameTextField.setBounds(141, 59, 231, 19);
		getContentPane().add(middleNameTextField);
		
		lastNameTextField = new JTextArea();
		lastNameTextField.setBounds(141, 89, 231, 19);
		getContentPane().add(lastNameTextField);
		
		emailTextField = new JTextArea();
		emailTextField.setBounds(141, 131, 231, 19);
		getContentPane().add(emailTextField);
		
		addressTextField = new JTextArea();
		addressTextField.setBounds(141, 192, 231, 19);
		getContentPane().add(addressTextField);
		
		contactNumberTextField = new JTextArea();
		contactNumberTextField.setBounds(141, 222, 231, 19);
		getContentPane().add(contactNumberTextField );
		
		genderComboBox = new JComboBox(genderItems);
		genderComboBox.setBounds(141, 252, 231, 22);
		getContentPane().add(genderComboBox);
		
				dayComboBox = new JComboBox(cmbDayItems);
				dayComboBox.setBounds(250, 156, 46, 25);
				getContentPane().add(dayComboBox);
				monthComboBox = new JComboBox(cmbMonthItems);
				monthComboBox.setBounds(141, 156, 99, 25);
				getContentPane().add(monthComboBox);
				yearComboBox = new JComboBox(cmbYearItems);
				yearComboBox.setBounds(306, 156, 66, 25);
				getContentPane().add(yearComboBox);
				
		saveButton = new JButton("Save");
		saveButton.setBackground(new Color(0, 128, 0));
		saveButton.setBounds(208, 305, 127, 30);
		getContentPane().add(saveButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.RED);
		cancelButton.setBounds(50, 305, 127, 30);
		getContentPane().add(cancelButton);
				
		//Adding listeners for confirm and date Combo Boxes
		saveButton.addActionListener(new ListenerAddEvent());
		monthComboBox.addActionListener(new ListenerAddEvent());
		yearComboBox.addActionListener(new ListenerAddEvent());
		
		refreshChoices();
		
		yearComboBox.setSelectedIndex(150);
		monthComboBox.setSelectedIndex(currMonth);
		dayComboBox.setSelectedIndex(currDay - 1);
	}
	
	class ListenerAddEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			if (a.getSource().equals(saveButton)) {
				if(!firstNameTextField.getText().equals("") && !middleNameTextField.getText().equals("")
				   && !lastNameTextField.getText().equals("") && !emailTextField.getText().equals("")){
					int month, year, day;
					
					month = monthComboBox.getSelectedIndex() + 1;
					year = yearComboBox.getSelectedIndex() + (currYear - 150);
					day = dayComboBox.getSelectedIndex() + 1;
					
					/*
						DEVS do the adding to the database here :)
					*/
					
					//dispose();
				}
			} else if (a.getActionCommand() == "comboBoxChanged") {
				refreshChoices();
			}
		}
	}
	
	public void refreshChoices() {
		int year, month, nod;

		month = monthComboBox.getSelectedIndex();
		year = yearComboBox.getSelectedIndex() + (currYear - 150);

		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		String cmbDayItems[] = new String[nod];

		for (int i = 0; i < nod; i++) {
			cmbDayItems[i] = new String("" + (i + 1));
		}

		DefaultComboBoxModel days = new DefaultComboBoxModel(cmbDayItems);
		dayComboBox.setModel(days);
		repaint();
	}// Please use this and combo boxes for anything that requires dates 
	 // so that the user will not make any errors in placing dates.
	 // This accounts for dates that doesn't exist (ex.: February 29 xxxx)
}
