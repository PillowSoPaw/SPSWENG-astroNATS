package view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInGUI extends JFrame
{	
	private JLabel logoLabel;
	private Image logoImage;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JButton logInButton;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	public LogInGUI()
	{
		try
		{
	        logoImage = ImageIO.read(new File("src\\view\\logo.png"));
	    }
	    catch(IOException e)
		{
	        JOptionPane.showMessageDialog(null, null, e.getMessage(), JOptionPane.ERROR_MESSAGE);
	    }
		
		logoImage = logoImage.getScaledInstance(245, 225, logoImage.SCALE_DEFAULT);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(189, 183, 107));
		
		logoLabel = new JLabel(new ImageIcon(logoImage));
		logoLabel.setBounds(76, 31, 242, 85);
		
		getContentPane().add(logoLabel);
		
		usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		usernameLabel.setBounds(67, 143, 67, 22);
		getContentPane().add(usernameLabel);
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel.setBounds(67, 186, 69, 14);
		getContentPane().add(passwordLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(133, 145, 181, 22);
		getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(133, 184, 181, 22);
		getContentPane().add(passwordField);
		
		logInButton = new JButton("Log In");
		logInButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == logInButton)
				{
					new MainGUI();
					dispose();
				}
			}
		});
		logInButton.setForeground(new Color(255, 250, 250));
		logInButton.setBackground(new Color(34, 139, 34));
		logInButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		logInButton.setBounds(139, 228, 108, 23);
		getContentPane().add(logInButton);
		
		this.setTitle("TaylorTyler Herbal Hair Spa and Salon");
		this.setSize(400, 315);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
	}
}
