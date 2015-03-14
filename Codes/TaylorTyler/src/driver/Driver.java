package driver;

import javax.swing.JFrame;

import view.AddTransactionPanel;
import controller.Controller;

public class Driver
{
	public static void main(String[] args)
	{
		AddTransactionPanel panel = new AddTransactionPanel();
		Controller controller = new Controller(panel);
	}
}
