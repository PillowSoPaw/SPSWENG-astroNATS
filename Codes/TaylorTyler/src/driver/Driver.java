package driver;

import javax.swing.JFrame;

import view.LogInGUI;
import view.MainGUI;
import controller.Controller;
import controller.LogInController;

public class Driver
{
	public static void main(String[] args)
	{
		//TransactionGUI panel = new TransactionGUI();
		//Controller controller = new Controller(panel);
		LogInController logInController = new LogInController();
		LogInGUI panel = new LogInGUI(logInController);
		logInController.setView((LogInGUI) panel);
	}
}
