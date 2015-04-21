package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

import model.Employee;
import model.Receipt;
import controller.ViewReceiptsController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewReceiptsGUI extends JPanel implements ActionListener
{
	private ViewReceiptsController viewReceiptsController;
	
	public Border blackline;
	public String title;
	private JTextField searchClientTextField;
	private JScrollPane receiptsScrollPane;
	private JButton searchClientButton;
	private JLabel sortByLabel;
	private JComboBox categoryComboBox;
	private JTable receiptsTable;
	private JButton viewDetailsButton;
	private DefaultTableModel receiptTableModel;

	private String[] columnNamesreceiptsTable = { "Receipt ID", "Client Name", "Date" };
	private ArrayList<Receipt> receipts;
	private ArrayList<Object[]> serviceLineItems;
	private ArrayList<Object[]> productLineItems;
	
	public ViewReceiptsGUI( ViewReceiptsController viewReceiptsController )
	{
		this.viewReceiptsController = viewReceiptsController;
		this.viewReceiptsController.setView(this);
		
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
		sortByLabel.setBounds(619, 11, 46, 26);
		add(sortByLabel);

		categoryComboBox = new JComboBox();
		categoryComboBox.setBounds(665, 14, 129, 20);
		add(categoryComboBox);

		viewDetailsButton = new JButton("View Details");
		viewDetailsButton.setBounds(681, 439, 113, 33);
		viewDetailsButton.setEnabled(false);
		viewDetailsButton.addActionListener(this);
		add(viewDetailsButton);

		receiptTableModel = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;// This causes all cells to be not editable
			}
		};

		for (int i = 0; i < columnNamesreceiptsTable.length; i++)
		{
			receiptTableModel.addColumn(columnNamesreceiptsTable[i]);
		}

		receiptsTable = new JTable(receiptTableModel);
		receiptsTable.getTableHeader().setReorderingAllowed(false);
		receiptsTable.getTableHeader().setResizingAllowed(false);
		receiptsScrollPane = new JScrollPane(receiptsTable);
		receiptsScrollPane.setBounds(10, 48, 784, 380);
		receiptsScrollPane.setBorder(blackline);
		add(receiptsScrollPane);
		
		ListSelectionModel listSelectionModel = receiptsTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() 
		{
	        public void valueChanged(ListSelectionEvent e)
	        { 
	            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
	            viewDetailsButton.setEnabled(!lsm.isSelectionEmpty());
	        }
		});
		
		getData();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource() == viewDetailsButton)
		{
			int row = receiptsTable.getSelectedRow();
			viewReceiptsController.getLineItemsOfReceipt(row + 1);
			new ShowReceiptGUI((String) receiptTableModel.getValueAt(row, 1), (String) receiptTableModel.getValueAt(row, 2), serviceLineItems, productLineItems);
		}
	}
	
	public void getData()
	{
		viewReceiptsController.getReceipts();
	}
	
	public void getReceiptList( Iterator i )
	{
		receipts = new ArrayList<>(0);
		
		while( i.hasNext() == true )
		{
			receipts.add((Receipt) i.next());
		}
		
		for( int x = 0; x < receipts.size(); x++ )
		{
			receiptTableModel.addRow(new Object[]{ receipts.get(x).getsReceiptId(), receipts.get(x).getClient().getsName(), receipts.get(x).getDateOfReceipt().toString() + " " + receipts.get(x).getTimeOfReceipt().toString() });
		}
	}
	
	public void getServiceList( Iterator i )
	{
		serviceLineItems = new ArrayList<>(0);
		
		while( i.hasNext() == true )
		{
			serviceLineItems.add((Object[]) i.next());
		}
	}
	
	public void getProductList( Iterator i )
	{
		productLineItems = new ArrayList<>(0);
		
		while( i.hasNext() == true )
		{
			productLineItems.add((Object[]) i.next());
		}
	}
}
