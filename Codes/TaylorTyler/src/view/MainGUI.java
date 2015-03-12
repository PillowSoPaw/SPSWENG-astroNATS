package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGUI extends JFrame{
    private JPanel toppanel;
    private JPanel leftpanel;
    private JPanel mainpanel;
    
    private JButton clientButton;
    private JButton inventoryButton;
    private JButton Button3;
    private JButton Button4;    
    private JButton transactionsButton;
    private JButton Button6;
    
    public MainGUI(){
        setSize(400, 300);
        setVisible(true);
        
        toppanel = new JPanel();
        
    }
}