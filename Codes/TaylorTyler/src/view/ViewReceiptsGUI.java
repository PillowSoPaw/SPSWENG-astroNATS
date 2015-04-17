package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewReceiptsGUI extends JPanel implements ActionListener
{	
	public Border blackline;
	public String title;
	private JTextField searchClientTextField;
	private JScrollPane receiptsScrollPane;
	private JButton searchClientButton;
	private JLabel sortByLabel;
	private JComboBox categoryComboBox;
	private JTable receiptsTable;
	private JButton viewDetailsButton;
	
	public ViewReceiptsGUI()
	{	
		blackline = BorderFactory.createLineBorder(Color.black);
		
		this.title = "View Receipts";
		this.setBackground(new Color(128, 128, 0));
		this.setLayout(null);
		this.setBounds(0, 0, 821, 483);
		this.setBorder(blackline);
		
		searchClientTextField = new JTextField();
		searchClientTextField.setText("Enter Client Name here...");
		searchClientTextField.setBounds(10, 11, 193, 26);
		add(searchClientTextField);
		searchClientTextField.setColumns(10);
		
		searchClientButton = new JButton("Search");
		searchClientButton.setBounds(213, 11, 89, 26);
		add(searchClientButton);
		
		sortByLabel = new JLabel("Sort by:");
		sortByLabel.setForeground(Color.WHITE);
		sortByLabel.setBounds(480, 11, 46, 26);
		add(sortByLabel);
		
		categoryComboBox = new JComboBox();
		categoryComboBox.setBounds(526, 14, 129, 20);
		add(categoryComboBox);
		
		viewDetailsButton = new JButton("View Details");
		viewDetailsButton.setBounds(681, 439, 113, 33);
		viewDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == viewDetailsButton)
				{
					new ShowReceiptGUI();
				}
			}
		});
		add(viewDetailsButton);
		
		String sample = "sampledata";
		//column names for notificationsTable
		String[] columnNamesreceiptsTable = {"Receipt ID", "Client Name", "Date"};
		Object[][] rowDatareceiptsTable = 
			{	columnNamesreceiptsTable,
				{sample, sample, sample},
				{sample, sample, sample},
				{sample, sample, sample},
				{sample, sample, sample},
				{sample, sample, sample},
				{sample, sample, sample},
				{sample, sample, sample}
			};
		receiptsTable = new JTable(rowDatareceiptsTable, columnNamesreceiptsTable);
		receiptsTable.setSize(784, 380);
		receiptsTable.setBorder(blackline);
		
		receiptsScrollPane = new JScrollPane(receiptsTable);
		receiptsScrollPane.setBounds(10, 48, 784, 380);
		receiptsScrollPane.setBorder(blackline);
		add(receiptsScrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
