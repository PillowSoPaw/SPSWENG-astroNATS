/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author Joseph
 */
public class HomeGUI extends JFrame
{
    
    private JPanel logoPanel;
    private JPanel optionsPanel;
    
    private JLabel logoLabel;
    private JLabel greetingLabel;
    private JButton addTransactionButton;
    private JButton viewTransactionButton;
    private JButton addClientButton;
    private JButton viewClientsButton;
    private JButton inventoryButton;
    private JButton reportsButton;
    private JButton logOutButton;
    private Image logo;
    private String username;
    
    public HomeGUI(String username)
    {   
        logoPanel = new JPanel();
        optionsPanel = new JPanel();
        
        if(username == null)
        {
            this.username = "anon";
        }
        else
        {
            this.username = username;
        }
        
        //logoIcon = new ImageIcon("/src/logo.png", "TaylorTyler Herbal Hair Spa and Salon");
        try{
        logo = ImageIO.read(new File("src\\View\\logo.png"));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        logo = logo.getScaledInstance(300, 275, logo.SCALE_DEFAULT);
        logoLabel = new JLabel(new ImageIcon(logo));
        greetingLabel = new JLabel("Welcome, " + username + "!"); //username - name of the user logged-in
        addTransactionButton = new JButton("Add Transaction");
        viewTransactionButton = new JButton("View Transactions");
        addClientButton = new JButton("Add Client");
        viewClientsButton = new JButton("View Clients");
        inventoryButton = new JButton("Inventory");
        reportsButton = new JButton("Reports");
        logOutButton = new JButton("Log Out");
        
        this.setSize(300, 560);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        logoPanel.setLayout(null);
        optionsPanel.setLayout(null);
        
        Border blackline = BorderFactory.createLineBorder(Color.black);
        
        logoPanel.setBounds(10, 10, 275, 150);
        logoPanel.setBackground(new Color(155, 157, 82));
        optionsPanel.setBounds(10, 165, 275, 350);
        optionsPanel.setBackground(new Color(155, 157, 82));
        optionsPanel.setBorder(blackline);
        logoPanel.setBorder(blackline);
        
        
        
        logoLabel.setBounds(0, 10, 275, 125);
        greetingLabel.setBounds(90, 110, 130, 50);
        logoPanel.add(logoLabel);
        logoPanel.add(greetingLabel);
        
        addTransactionButton.setBounds(70, 10, 150, 25);
        viewTransactionButton.setBounds(70, 60, 150, 25);
        addClientButton.setBounds(70, 110, 150, 25);
        viewClientsButton.setBounds(70, 160, 150, 25);
        inventoryButton.setBounds(70, 210, 150, 25);
        reportsButton.setBounds(70, 260, 150, 25);
        logOutButton.setBounds(70, 310, 150, 25);
        optionsPanel.add(addTransactionButton);
        optionsPanel.add(viewTransactionButton);
        optionsPanel.add(addClientButton);
        optionsPanel.add(viewClientsButton);
        optionsPanel.add(inventoryButton);
        optionsPanel.add(reportsButton);
        optionsPanel.add(logOutButton);
        
        setBackground(new Color(155, 157, 82));
        this.add(logoPanel);
        this.add(optionsPanel);
        
        
    }
    
    public static void main(String[] args) {
        new HomeGUI("Ryan");
    }

    
}
