package view;

import java.awt.Cursor;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.Border;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import controller.AddProductsController;
import controller.AddTransactionController;
import controller.AddServicesController;
import controller.InventoryController;
import controller.LogInController;
import controller.ViewClientsController;
import controller.ViewNotificationsController;
import controller.ViewReceiptsController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class MainGUI extends JFrame implements ActionListener, MouseListener
{
	private AddTransactionController addTransactionController;
	private AddServicesController addServicesController;
	private AddProductsController addProductsController;
	private ViewClientsController viewClientsController;
	private ViewNotificationsController viewNotificationsController;
	private ViewReceiptsController viewReceiptsController;
	private InventoryController inventoryController;
	private LogInController logInController;
	
	private JPanel logoPanel;
	private Image logoImage;
	private JLabel logoLabel;
	
	private JPanel optionsPanel;
	private JLabel OptionsLabel;
	private JButton addTransactionButton;
	private JButton viewTransactionsButton;
	private JButton addClientButton;
	private JButton viewClientsButton;
	private JButton inventoryButton;
		
	private JPanel mainPanel;
		
	private JPanel topPanel;
	private JComboBox branchComboBox;
		
	private Border blackline;
	private JButton reportsButton;
		
	private String panelTitle;
	private String frameTitle;
	private JLabel logOutLabel;
	private JLabel divLabel;
	private JLabel manageAccountLabel;
	private JLabel notificationLabel;
	private JLabel panelLabel;
	private JLabel branchNameLabel;
//	private static int currentSecond; //for dynamic date and time
//	private static String[] months;
//	private static GregorianCalendar cal;
	private JLabel dateLabel;
	private JLabel timeLabel;
//	private static String month;
//	private static String day;
//	private static int year;
//	private static String hour;
//	private static String minute;
//	private static String second;
			
	public MainGUI() 
	{
		addTransactionController = new AddTransactionController();
		addServicesController = new AddServicesController();
		addProductsController = new AddProductsController();
		viewClientsController = new ViewClientsController();
		viewNotificationsController = new ViewNotificationsController();
		viewReceiptsController = new ViewReceiptsController();
		inventoryController = new InventoryController();
		logInController = new LogInController();
		
		getContentPane().setLayout(null);
		
		try
		{
	        logoImage = ImageIO.read(new File("src\\view\\logo.png"));
		}
		catch(IOException e)
		{
	        JOptionPane.showMessageDialog(null, null, e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		
		logoImage = logoImage.getScaledInstance(245, 225, Image.SCALE_DEFAULT);
		
		blackline = BorderFactory.createLineBorder(Color.black);
		
		mainPanel = new AddTransactionGUI( addTransactionController, addServicesController, addProductsController );
		addTransactionController.setView((AddTransactionGUI) mainPanel);
		mainPanel.setBackground(new Color(128, 128, 0));
		//mainPanel.setBackground(new Color(189, 183, 107));
		mainPanel.setBounds(253, 67, 821, 483);
		mainPanel.setBorder(blackline);
		getContentPane().add(mainPanel);
		
		optionsPanel = new JPanel();
		optionsPanel.setBackground(new Color(189, 183, 107));
		optionsPanel.setBounds(10, 126, 233, 424);
		optionsPanel.setLayout(null);
		optionsPanel.setBorder(blackline);
		getContentPane().add(optionsPanel);
		
		addTransactionButton = new JButton("Add Transaction");
		addTransactionButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		addTransactionButton.setBounds(36, 36, 158, 37);
		addTransactionButton.setEnabled(false);
		optionsPanel.add(addTransactionButton);
		
		addTransactionButton.addActionListener(this);
		
		OptionsLabel = new JLabel("Options");
		OptionsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		OptionsLabel.setBounds(84, 2, 82, 23);
		optionsPanel.add(OptionsLabel);
		
		viewTransactionsButton = new JButton("View Receipts");
		viewTransactionsButton.addActionListener(this);
		viewTransactionsButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		viewTransactionsButton.setBounds(36, 97, 158, 37);
		optionsPanel.add(viewTransactionsButton);
		
		addClientButton = new JButton("Manage Employees");
		addClientButton.addActionListener(this);
		addClientButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		addClientButton.setBounds(36, 163, 158, 37);
		optionsPanel.add(addClientButton);
		
		viewClientsButton = new JButton("Manage Clients");
		viewClientsButton.addActionListener(this);
		viewClientsButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		viewClientsButton.setBounds(36, 228, 158, 37);
		optionsPanel.add(viewClientsButton);
		
		inventoryButton = new JButton("Inventory");
		inventoryButton.addActionListener(this);
		inventoryButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		inventoryButton.setBounds(36, 293, 158, 37);
		optionsPanel.add(inventoryButton);
		
		reportsButton = new JButton("Reports");
		reportsButton.addActionListener(this);
		reportsButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		reportsButton.setBounds(36, 356, 158, 37);
		optionsPanel.add(reportsButton);
		
		topPanel = new JPanel();
		topPanel.setBackground(new Color(189, 183, 107));
		topPanel.setBounds(253, 11, 821, 51);
		topPanel.setBorder(blackline);
		topPanel.setLayout(null);
		getContentPane().add(topPanel);
		
		logOutLabel = new JLabel("Log out");
		logOutLabel.setBounds(760, 32, 46, 14);
		logOutLabel.addMouseListener(this);
		logOutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		topPanel.add(logOutLabel);
		
		divLabel = new JLabel("|");
		divLabel.setBounds(744, 32, 17, 14);
		topPanel.add(divLabel);
		
		manageAccountLabel = new JLabel("Manage Account");
		manageAccountLabel.setBounds(633, 32, 107, 14);
		manageAccountLabel.addMouseListener(this);
		manageAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		topPanel.add(manageAccountLabel);
		
		updatePanelTitle();
		panelLabel = new JLabel(panelTitle);
		panelLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelLabel.setBounds(307, 11, 148, 29);
		topPanel.add(panelLabel);
		
		branchComboBox = new JComboBox();
		branchComboBox.setModel(new DefaultComboBoxModel(new String[] {"Branch 1", "Branch 2", "Branch 3"}));
		branchComboBox.setBounds(10, 17, 148, 20);
		branchComboBox.setVisible(true);
		topPanel.add(branchComboBox);
		
		
		GregorianCalendar cal = new GregorianCalendar();
		String[] months = {
			      "Jan", "Feb", "Mar", "Apr",
			      "May", "Jun", "Jul", "Aug",
			      "Sep", "Oct", "Nov", "Dec"};
		
		String month = months[cal.get(Calendar.MONTH)];
		String day = cal.get(Calendar.DATE) + "";
		if(cal.get(Calendar.DATE) < 10 && cal.get(Calendar.DATE) >= 0)
		{
			day = "0" + day;
		}
		String year = cal.get(Calendar.YEAR) + "";
		
		dateLabel = new JLabel("Date: " + month + " " + day + " " + year);
		
		dateLabel.setBounds(613, 11, 107, 15);
		topPanel.add(dateLabel);
		
		//boolean checkAmPm = true;
		String hour = cal.get(Calendar.HOUR) + "";
		if(hour.equals("0"))
		{
			hour ="12";
		}
		if(cal.get(Calendar.HOUR) < 10 && cal.get(Calendar.HOUR) >= 0)
		{
			if(hour != "12")
			{
				hour = "0" + hour;
			}
		}
		String minute = cal.get(Calendar.MINUTE) + "";
		if(cal.get(Calendar.MINUTE) < 10 && cal.get(Calendar.MINUTE) >= 0)
		{
			minute = "0" + minute;
		}
		String second = cal.get(Calendar.SECOND) + "";
		if(cal.get(Calendar.SECOND) < 10 && cal.get(Calendar.SECOND) >= 0)
		{
			second = "0" + second;
		}
		
		timeLabel = new JLabel("Time: " + hour + ":" + minute + ":" + second);
		
		timeLabel.setBounds(727, 11, 84, 15);
		topPanel.add(timeLabel);
	
		logoPanel = new JPanel();
		logoPanel.setBackground(new Color(189, 183, 107));
		logoPanel.setBounds(10, 11, 233, 111);
		logoPanel.setLayout(null);
		logoPanel.setBorder(blackline);
		getContentPane().add(logoPanel);
		
		logoLabel = new JLabel(new ImageIcon(logoImage));
		logoLabel.setBounds(12, 11, 213, 85);
		logoPanel.add(logoLabel);
		
		branchNameLabel = new JLabel("Branch Name");
		branchNameLabel.setBounds(80, 84, 123, 37);
		logoPanel.add(branchNameLabel);
		
		//this.setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(189, 183, 107));
		this.setLocation(129, 60);
		this.setSize(1100, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		frameTitle = "TaylorTyler Herbal Hair Spa and Salon"; //title - for testing //frameTitle should change depending on which button is clicked
		this.setTitle(frameTitle);
		start(dateLabel, timeLabel);
		
		notificationLabel = new JLabel("Notifications");
		notificationLabel.setBounds(526, 32, 97, 14);
		notificationLabel.addMouseListener(this);
		notificationLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		topPanel.add(notificationLabel);
		
		JLabel divLabel2 = new JLabel("|");
		divLabel2.setBounds(613, 32, 17, 14);
		topPanel.add(divLabel2);
		mainPanel.repaint();
		
	}
	
	public void updatePanelTitle()
	{
		this.panelTitle = mainPanel.getClass().getName().substring(5, (mainPanel.getClass().getName().length()-3));
		ArrayList<Integer> indexSpaces = new ArrayList();
		for(int i = 1; i < panelTitle.length(); i++)
		{
			if(Character.isUpperCase(panelTitle.charAt(i)))
			{
				indexSpaces.add(i);
			}
		}
		for(int i = 0; i < indexSpaces.size(); i++)
		{
			String a = panelTitle.substring(0, indexSpaces.get(i)) + " ";
			String b = panelTitle.substring(indexSpaces.get(i), panelTitle.length());
			this.panelTitle = a + b;
		}
		//planning to change this part. change variable title of every GUI into static final then provide get function then set it here.
		//for now this will be the one we're using
	}
//	private static void reset(int currentSecond){
//        Calendar calendar = Calendar.getInstance();
//        currentSecond = calendar.get(Calendar.SECOND);
//    }
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == addTransactionButton)
		{
			addTransactionButton.setEnabled(false);
			inventoryButton.setEnabled(true);
			viewClientsButton.setEnabled(true);
			addClientButton.setEnabled(true);
			reportsButton.setEnabled(true);
			viewTransactionsButton.setEnabled(true);
			getContentPane().remove(mainPanel);
			mainPanel = new AddTransactionGUI( addTransactionController, addServicesController, addProductsController );
			addTransactionController.setView((AddTransactionGUI) mainPanel);
			mainPanel.setBounds(253, 67, 821, 483);
			getContentPane().add(mainPanel);
			updatePanelTitle();
			panelLabel.setText(panelTitle);
			getContentPane().validate();
			getContentPane().repaint();
		}
		else if( e.getSource() == viewTransactionsButton )
		{
			addClientButton.setEnabled(true);
			addTransactionButton.setEnabled(true);
			inventoryButton.setEnabled(true);
			viewClientsButton.setEnabled(true);
			reportsButton.setEnabled(true);
			viewTransactionsButton.setEnabled(false);
			getContentPane().remove(mainPanel);
			mainPanel = new ViewReceiptsGUI(viewReceiptsController);
			mainPanel.setBounds(253, 67, 821, 483);
			getContentPane().add(mainPanel);
			updatePanelTitle();
			panelLabel.setText(panelTitle);
			getContentPane().validate();
			getContentPane().repaint();
		}
		else if( e.getSource() == addClientButton )
		{
			addClientButton.setEnabled(false);
			addTransactionButton.setEnabled(true);
			inventoryButton.setEnabled(true);
			viewClientsButton.setEnabled(true);
			reportsButton.setEnabled(true);
			viewTransactionsButton.setEnabled(true);
			getContentPane().remove(mainPanel);
			mainPanel = new ManageEmployeesGUI();
			mainPanel.setBounds(253, 67, 821, 483);
			getContentPane().add(mainPanel);
			updatePanelTitle();
			panelLabel.setText(panelTitle);
			getContentPane().validate();
			getContentPane().repaint();
		}
		else if(e.getSource() == viewClientsButton)
		{
			viewClientsButton.setEnabled(false);
			addTransactionButton.setEnabled(true);
			inventoryButton.setEnabled(true);
			addClientButton.setEnabled(true);
			reportsButton.setEnabled(true);
			viewTransactionsButton.setEnabled(true);
			getContentPane().remove(mainPanel);
			mainPanel = new ManageClientsGUI(viewClientsController);
			viewClientsController.setView((ManageClientsGUI) mainPanel);
			mainPanel.setBounds(253, 67, 821, 483);
			getContentPane().add(mainPanel);
			updatePanelTitle();
			panelLabel.setText(panelTitle);
			getContentPane().validate();
			getContentPane().repaint();
		}
		else if(e.getSource() == inventoryButton)
		{
			inventoryButton.setEnabled(false);
			addTransactionButton.setEnabled(true);
			viewClientsButton.setEnabled(true);
			addClientButton.setEnabled(true);
			reportsButton.setEnabled(true);
			viewTransactionsButton.setEnabled(true);
			getContentPane().remove(mainPanel);
			mainPanel = new InventoryGUI(inventoryController);
			mainPanel.setBounds(253, 67, 821, 483);
			getContentPane().add(mainPanel);
			updatePanelTitle();
			panelLabel.setText(panelTitle);
			getContentPane().validate();
			getContentPane().repaint();
		}
		else if( e.getSource() == reportsButton )
		{
			inventoryButton.setEnabled(true);
			addTransactionButton.setEnabled(true);
			viewClientsButton.setEnabled(true);
			addClientButton.setEnabled(true);
			reportsButton.setEnabled(false);
			viewTransactionsButton.setEnabled(true);
			getContentPane().remove(mainPanel);
			mainPanel = new DailyReportsGUI();
			mainPanel.setBounds(253, 67, 821, 483);
			getContentPane().add(mainPanel);
			updatePanelTitle();
			panelLabel.setText(panelTitle);
			getContentPane().validate();
			getContentPane().repaint();
		}
	}
	
	public void start(final JLabel dateLabel, final JLabel timeLabel)
	{
//		final int currentSecond = 0;
//		reset(currentSecond);
        Timer timer = new Timer(1000, new ActionListener(){
        public void actionPerformed( ActionEvent e ) {
                
//        		reset(currentSecond);
                GregorianCalendar cal = new GregorianCalendar();
                String[] months = {
      			      "Jan", "Feb", "Mar", "Apr",
      			      "May", "Jun", "Jul", "Aug",
      			      "Sep", "Oct", "Nov", "Dec"};
        		String month = months[cal.get(Calendar.MONTH)];
        		String day = cal.get(Calendar.DATE) + "";
        		if(cal.get(Calendar.DATE) < 10 && cal.get(Calendar.DATE) >= 0)
        		{
        			day = "0" + day;
        		}
        		String year = cal.get(Calendar.YEAR) + "";
        		
        		dateLabel.setText("Date: " + month + " " + day + " " + year);
        		
        		String hour = cal.get(Calendar.HOUR) + "";
        		if(hour.equals("0"))
        		{
        			hour ="12";
        		}
        		if(cal.get(Calendar.HOUR) < 10 && cal.get(Calendar.HOUR) >= 0)
        		{
        			if(hour != "12")
        			{
        				hour = "0" + hour;
        			}
        		}
        		String minute = cal.get(Calendar.MINUTE) + "";
        		if(cal.get(Calendar.MINUTE) < 10 && cal.get(Calendar.MINUTE) >= 0)
        		{
        			minute = "0" + minute;
        		}
        		String second = cal.get(Calendar.SECOND) + "";
        		if(cal.get(Calendar.SECOND) < 10 && cal.get(Calendar.SECOND) >= 0)
        		{
        			second = "0" + second;
        		}
        		
        		timeLabel.setText("Time: " + hour + ":" + minute + ":" + second);
                //currentSecond++;
            }
        });
        timer.start();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getSource().equals(manageAccountLabel))
		{
			new ManageAccountGUI();
		} 
		else if (e.getSource().equals(logOutLabel))
		{
			new LogInGUI(logInController);
			dispose();
		}
		else if( e.getSource().equals(notificationLabel))
		{
			addTransactionButton.setEnabled(true);
			inventoryButton.setEnabled(true);
			viewClientsButton.setEnabled(true);
			addClientButton.setEnabled(true);
			reportsButton.setEnabled(true);
			viewTransactionsButton.setEnabled(true);
			getContentPane().remove(mainPanel);
			mainPanel = new ViewNotificationsGUI(viewNotificationsController);
			viewNotificationsController.setView((ViewNotificationsGUI) mainPanel);
			mainPanel.setBounds(253, 67, 821, 483);
			getContentPane().add(mainPanel);
			updatePanelTitle();
			panelLabel.setText(panelTitle);
			getContentPane().validate();
			getContentPane().repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
