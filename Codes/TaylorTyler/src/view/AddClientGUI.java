package view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.AddClientController;
import controller.AddTransactionController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddClientGUI extends JFrame implements ActionListener
{
	private AddClientController addClientController = new AddClientController();
	private ViewClientsGUI mainFrame;
	
	private JTextArea firstNameText;
	private JTextArea middleNameText;
	private JTextArea lastNameText;
	private JTextArea emailText;
	private JTextArea addressText;
	private JTextArea contactNumberText;
	
	private JComboBox cmbDay;
	private JComboBox cmbMonth;
	private JComboBox cmbYear;
	private JComboBox cmbGender;
	private String dateToday;
	
	private JButton confirm;
	
	private int currYear;
	private ManageClientsGUI manageClientsGUI;
	public AddClientGUI(ManageClientsGUI manageClientsGUI){
		//this.mainFrame = mainFrame; //Pass the view clients GUI
		this.setLocationRelativeTo(null);
		this.manageClientsGUI = manageClientsGUI;
		//Calendar stuff
		currYear = Calendar.getInstance().get(Calendar.YEAR);
		int currDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int currMonth = Calendar.getInstance().get(Calendar.MONTH);
		dateToday = currYear+"-" + (currMonth +1) + "-" + currDay;
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
			cmbYearItems[i] = new String("" + (i + (currYear - 150)));
		}
		
		//Calendar stuff
		
		getContentPane().setBackground(new Color(128, 128, 0));
		setSize(400, 400);
		setTitle("Add Client");
		setResizable(false);
		
		getContentPane().setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(128, 128, 0));
		mainPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainPanel.setBounds(10, 11, 374, 350);
		mainPanel.setLayout(null);
		setVisible(true);
		
		getContentPane().add(mainPanel);
		
		// JLabels
		
		JLabel fnameLabel = new JLabel("First Name*:");
		fnameLabel.setBounds(10, 39, 93, 14);
		mainPanel.add(fnameLabel);
		
		JLabel mnameLabel = new JLabel("Middle Name:");
		mnameLabel.setBounds(10, 69, 93, 14);
		mainPanel.add(mnameLabel);
		
		JLabel lnameLabel = new JLabel("Last Name*:");
		lnameLabel.setBounds(10, 99, 93, 14);
		mainPanel.add(lnameLabel);
		
		JLabel separator0 = new JLabel("__________________________________________________");
		separator0.setBounds(10, 116, 359, 14);
		mainPanel.add(separator0);
		
		JLabel emailLabel = new JLabel("E-mail*:");
		emailLabel.setBounds(10, 141, 46, 14);
		mainPanel.add(emailLabel);
		
		JLabel birthdayLabel = new JLabel("Birthday (MM/DD/YY):");
		birthdayLabel.setBounds(10, 172, 117, 14);
		mainPanel.add(birthdayLabel);
		
		JLabel addressLabel = new JLabel("Address*:");
		addressLabel.setBounds(10, 202, 117, 14);
		mainPanel.add(addressLabel);
		
		JLabel contactNumberLabel = new JLabel("Contact Number*:");
		contactNumberLabel.setBounds(10, 232, 117, 14);
		mainPanel.add(contactNumberLabel);
		
		JLabel genderLabel = new JLabel("Gender*:");
		genderLabel.setBounds(10, 262, 117, 14);
		mainPanel.add(genderLabel);
		
		//Text Areas
		
		firstNameText = new JTextArea();
		firstNameText.setBounds(133, 34, 231, 19);
		mainPanel.add(firstNameText);
		
		middleNameText = new JTextArea();
		middleNameText.setBounds(133, 64, 231, 19);
		mainPanel.add(middleNameText);
		
		lastNameText = new JTextArea();
		lastNameText.setBounds(133, 94, 231, 19);
		mainPanel.add(lastNameText);
		
		emailText = new JTextArea();
		emailText.setBounds(133, 136, 231, 19);
		mainPanel.add(emailText);
		
		addressText = new JTextArea();
		addressText.setBounds(133, 197, 231, 19);
		mainPanel.add(addressText);
		
		contactNumberText = new JTextArea();
		contactNumberText.setBounds(133, 227, 231, 19);
		mainPanel.add(contactNumberText );
		
		cmbGender = new JComboBox(genderItems);
		cmbGender.setBounds(133, 257, 231, 22);
		mainPanel.add(cmbGender);
		
		JLabel lblAddClient = new JLabel("Add Client");
		lblAddClient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddClient.setBounds(0, 0, 374, 30);
		mainPanel.add(lblAddClient);
		
				cmbDay = new JComboBox(cmbDayItems);
				cmbDay.setBounds(242, 161, 46, 25);
				mainPanel.add(cmbDay);
				cmbMonth = new JComboBox(cmbMonthItems);
				cmbMonth.setBounds(133, 161, 99, 25);
				mainPanel.add(cmbMonth);
				cmbYear = new JComboBox(cmbYearItems);
				cmbYear.setBounds(298, 161, 66, 25);
				mainPanel.add(cmbYear);
				
		confirm = new JButton("Confirm");
		confirm.setBounds(37, 309, 294, 30);
		confirm.addActionListener(this);
		mainPanel.add(confirm);
				
		//Adding listeners for confirm and date Combo Boxes
		cmbMonth.addActionListener(new ListenerAddEvent());
		cmbYear.addActionListener(new ListenerAddEvent());
		
		refreshChoices();
		
		cmbYear.setSelectedIndex(150);
		cmbMonth.setSelectedIndex(currMonth);
		cmbDay.setSelectedIndex(currDay - 1);
		
	}
	
	class ListenerAddEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			if (a.getSource().equals(confirm)) {
				if(!firstNameText.getText().equals("") && !middleNameText.getText().equals("")
				   && !lastNameText.getText().equals("") && !emailText.getText().equals("")){
					int month, year, day;
					
					month = cmbMonth.getSelectedIndex() + 1;
					year = cmbYear.getSelectedIndex() + (currYear - 150);
					day = cmbDay.getSelectedIndex() + 1;
					
					/*
						DEVS do the adding to the database here :)
					*/
					
					dispose();
				}
			} else if (a.getActionCommand() == "comboBoxChanged") {
				refreshChoices();
			}
		}
	}
	
	public void refreshChoices() {
		int year, month, nod;

		month = cmbMonth.getSelectedIndex();
		year = cmbYear.getSelectedIndex() + (currYear - 150);

		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		String cmbDayItems[] = new String[nod];

		for (int i = 0; i < nod; i++) {
			cmbDayItems[i] = new String("" + (i + 1));
		}

		DefaultComboBoxModel days = new DefaultComboBoxModel(cmbDayItems);
		cmbDay.setModel(days);
		repaint();
	}// Please use this and combo boxes for anything that requires dates 
	 // so that the user will not make any errors in placing dates.
	 // This accounts for dates that doesn't exist (ex.: February 29 xxxx)

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource() == confirm )
		{
			if(firstNameText.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a first name.");
			else if(lastNameText.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a last name.");
			else if(emailText.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter an email.");
			else if(addressText.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter an address.");
			else if(contactNumberText.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter a contact number.");
			else
			{
				String sMiddleName = middleNameText.getText();
				if(!middleNameText.getText().equals(""))
					sMiddleName = sMiddleName + " ";
				String sName = firstNameText.getText() +" "+ sMiddleName + lastNameText.getText();
				String sAddress = addressText.getText();
				String sEmail = emailText.getText();
				String sBirthday = (cmbYear.getSelectedIndex()+ (currYear - 150))+"-"+(cmbMonth.getSelectedIndex()+1)+"-"+ (cmbDay.getSelectedIndex()+1) ;
				String sContactNumber = contactNumberText.getText();
				String sDateJoined = dateToday;
				String sDateLastVisited = dateToday;
				System.out.println(sName);
				System.out.println(sAddress);
				System.out.println(sEmail);
				System.out.println(sBirthday);
				System.out.println(sContactNumber);
				System.out.println(sDateJoined);
				System.out.println(sDateLastVisited);
				//addClient(String sName, String sAddress, String sContactNumber, String sEmail, String sDateJoined, String sDateLastVisited, String sBirthday) 
				try 
				{
					String sGender =cmbGender.getSelectedItem().toString();
					addClientController.addClient(sName, sAddress, sContactNumber, sEmail, sDateJoined, sDateLastVisited, sBirthday, sGender);
					JOptionPane.showMessageDialog(null, "Successfully added client!");
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
		
	}
}
