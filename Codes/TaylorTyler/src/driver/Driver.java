package driver;

import javax.swing.JFrame;

import view.AddTransactionPanel;
import view.TransactionGUI;
import controller.Controller;

public class Driver
{
	public static void main(String[] args)
	{
		//AddTransactionPanel panel = new AddTransactionPanel();
		TransactionGUI panel = new TransactionGUI();
		Controller controller = new Controller(panel);
		
	}
}
