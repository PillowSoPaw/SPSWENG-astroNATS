package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controller.EditClientDetailsController;
import model.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditClientDetailsGUI extends JFrame implements ActionListener
{
	private EditClientDetailsController editClientDetailsController = new EditClientDetailsController();
	private ManageClientsGUI manageClientsGUI;
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
	private Client client;
	//add the parameters inside action listener of ManageClientsGUI
	public EditClientDetailsGUI(Client client, ManageClientsGUI manageClientsGUI)
	{
		this.manageClientsGUI = manageClientsGUI;
		this.client = client;
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
		
		for (int i = 0; i < 31; i++)
		{
			cmbDayItems[i] = new String("" + (i + 1));
		}
		
		for (int i = 0; i < 151; i++) 
		{
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
		
		firstNameTextField = new JTextArea(client.getsFname());
		firstNameTextField.setBounds(141, 29, 231, 19);
		getContentPane().add(firstNameTextField);
		
		middleNameTextField = new JTextArea(client.getsMname());
		middleNameTextField.setBounds(141, 59, 231, 19);
		getContentPane().add(middleNameTextField);
		
		lastNameTextField = new JTextArea(client.getsLname());
		lastNameTextField.setBounds(141, 89, 231, 19);
		getContentPane().add(lastNameTextField);
		
		emailTextField = new JTextArea(client.getsEmail());
		emailTextField.setBounds(141, 131, 231, 19);
		getContentPane().add(emailTextField);
		
		addressTextField = new JTextArea(client.getsAddress());
		addressTextField.setBounds(141, 192, 231, 19);
		getContentPane().add(addressTextField);
		
		contactNumberTextField = new JTextArea(client.getsContactNumber());
		contactNumberTextField.setBounds(141, 222, 231, 19);
		getContentPane().add(contactNumberTextField );
		genderComboBox = new JComboBox(genderItems);
		genderComboBox.setBounds(141, 252, 231, 22);
		int iGender=0;
		if(client.getsGender().equals("Female"))
			iGender = 1;
		genderComboBox.setSelectedIndex(iGender);
		getContentPane().add(genderComboBox);
		
				dayComboBox = new JComboBox(cmbDayItems);
				dayComboBox.setSelectedIndex(client.getBirthday().getDate()-1);
				dayComboBox.setBounds(250, 156, 46, 25);
				getContentPane().add(dayComboBox);
				monthComboBox = new JComboBox(cmbMonthItems);
				System.out.println("Month" + client.getBirthday().getMonth());
				monthComboBox.setSelectedIndex(client.getBirthday().getMonth());
				monthComboBox.setBounds(141, 156, 99, 25);
				getContentPane().add(monthComboBox);
				yearComboBox = new JComboBox(cmbYearItems);
				yearComboBox.setSelectedIndex(client.getBirthday().getYear()+35);
				yearComboBox.setBounds(306, 156, 66, 25);
				getContentPane().add(yearComboBox);
				
		saveButton = new JButton("Save");
		saveButton.setBackground(new Color(0, 128, 0));
		saveButton.setBounds(208, 305, 127, 30);
		getContentPane().add(saveButton);
		saveButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.RED);
		cancelButton.setBounds(50, 305, 127, 30);
		cancelButton.addActionListener(this);
		getContentPane().add(cancelButton);
				
		//Adding listeners for confirm and date Combo Boxes
		monthComboBox.addActionListener(new ListenerAddEvent());
		yearComboBox.addActionListener(new ListenerAddEvent());
		
		refreshChoices();
		
		//yearComboBox.setSelectedIndex(150);
		//monthComboBox.setSelectedIndex(currMonth);
		//dayComboBox.setSelectedIndex(currDay - 1);
	}
	
	class ListenerAddEvent implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent a)
		{
			if (a.getSource().equals(saveButton)) 
			{
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
			} else if (a.getActionCommand() == "comboBoxChanged") 
			{
				refreshChoices();
			}
			else if( a.getSource() == cancelButton )
			{
				dispose();
			}
		}
	}
	
	public void refreshChoices() 
	{
		int year, month, nod;

		month = monthComboBox.getSelectedIndex();
		year = yearComboBox.getSelectedIndex() + (currYear - 150);

		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		String cmbDayItems[] = new String[nod];

		for (int i = 0; i < nod; i++)
		{
			cmbDayItems[i] = new String("" + (i + 1));
		}
		DefaultComboBoxModel days = new DefaultComboBoxModel(cmbDayItems);
		dayComboBox.setModel(days);
		try
		{
			dayComboBox.setSelectedIndex(client.getBirthday().getDate()-1);
		}
		catch( Exception e)
		{
			dayComboBox.setSelectedIndex(nod-1);
		}
		repaint();
	}// Please use this and combo boxes for anything that requires dates 
	 // so that the user will not make any errors in placing dates.
	 // This accounts for dates that doesn't exist (ex.: February 29 xxxx)
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource() == saveButton )
		{
			if(firstNameTextField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a first name.");
			else if(lastNameTextField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a last name.");
			else if(emailTextField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter an email.");
			else if(addressTextField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter an address.");
			else if(contactNumberTextField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a contact number.");
			else
			{
				//String sMiddleName = middleNameText.getText();
				//if(!middleNameText.getText().equals(""))
				//	sMiddleName = sMiddleName + " ";
				//String sName = firstNameText.getText() +" "+ sMiddleName + lastNameText.getText();
				String sFname = firstNameTextField.getText();
				String sMname = middleNameTextField.getText();
				String sLname = lastNameTextField.getText();
				String sAddress = addressTextField.getText();
				String sEmail = emailTextField.getText();
				String sBirthday = (yearComboBox.getSelectedIndex()+ (currYear - 150))+"-"+(monthComboBox.getSelectedIndex()+1)+"-"+ (dayComboBox.getSelectedIndex()+1) ;
				String sContactNumber = contactNumberTextField.getText();
//				System.out.println(sName);
//				System.out.println(sAddress);
//				System.out.println(sEmail);
//				System.out.println(sBirthday);
//				System.out.println(sContactNumber);
//				System.out.println(sDateJoined);
//				System.out.println(sDateLastVisited);
				//addClient(String sName, String sAddress, String sContactNumber, String sEmail, String sDateJoined, String sDateLastVisited, String sBirthday) 
				try 
				{
					String sGender =genderComboBox.getSelectedItem().toString();
					System.out.println("Edit client gui");
					System.out.println(client.getsClientId());
					System.out.println(sFname);
					System.out.println(sMname);
					System.out.println(sLname);
					System.out.println(sAddress);
					System.out.println(sContactNumber);
					System.out.println(sEmail);
					System.out.println(sBirthday);
					System.out.println(sGender);
					editClientDetailsController.editClient(client.getsClientId(), sFname, sMname, sLname, sAddress, sContactNumber, sEmail, sBirthday, sGender);
					JOptionPane.showMessageDialog(null, "Successfully updated client!");
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				} catch (ParseException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				manageClientsGUI.addClient();
				manageClientsGUI.getData();
			}
		}
		else if( e.getSource() == cancelButton )
		{
			dispose();
		}
		
	}
}
