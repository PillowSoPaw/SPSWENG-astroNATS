package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import view.AddClientGUI.ListenerAddEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DailyReportsGUI extends JPanel{
	
	private JTable tableDailyReport;
	private JScrollPane scrollDailyReport;
	private JComboBox cmbDay;
	private JComboBox cmbMonth;
	private JComboBox cmbYear;
	
	private int currDay;
	private int currMonth;
	private int currYear;
	private JLabel lblDate;
	
	
	public DailyReportsGUI(){
		setBackground(new Color(128,128, 0));
		setSize(821, 483);
		setLayout(null);
		setVisible(true);
		
		String[] column = {"Employee", "Amount"};
		String[][] rows = {
							{"Henry", "500"}
						  };
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		DefaultTableModel tableModel = new DefaultTableModel() {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		for(int i = 0; i < column.length; i++)
			tableModel.addColumn(column[i]);
		
		for(int i = 0; i < rows.length; i++)
			tableModel.addRow(rows[i]);
		
		tableDailyReport = new JTable();
		tableDailyReport.setModel(tableModel);
		tableDailyReport.setSize(801, 380);
		tableDailyReport.setBorder(blackline);
		scrollDailyReport = new JScrollPane(tableDailyReport);
		scrollDailyReport.setBounds(10, 78, 801, 380);
		add(scrollDailyReport);
		
		
		
		cmbDay = new JComboBox();
		cmbDay.setBounds(289, 47, 50, 20);
		add(cmbDay);
		
		String[] months = {"January", "February", "March", "April", "May", "June",
						   "July", "August", "September", "October", "November",
						   "December"};
		
		cmbMonth = new JComboBox(months);
		cmbMonth.setBounds(349, 47, 100, 20);
		add(cmbMonth);

		currYear = Calendar.getInstance().get(Calendar.YEAR);
		currMonth = Calendar.getInstance().get(Calendar.MONTH);
		currDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		cmbMonth.addActionListener(new ListenerAddEvent());
		
		String[] years = new String[151];
		for(int i = 0; i < 151; i++){
			years[i] = "" + (currYear - (150 - i));
		}

		cmbYear = new JComboBox(years);
		cmbYear.setBounds(459, 47, 76, 20);
		add(cmbYear);
		
		cmbYear.setSelectedIndex(150);
		cmbMonth.setSelectedIndex(currMonth);
		cmbYear.addActionListener(new ListenerAddEvent());
		
		lblDate = new JLabel("DATE");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDate.setBounds(10, 11, 801, 33);
		add(lblDate);
		 
		refreshChoices();
		cmbDay.setSelectedIndex(currDay - 1);
	}
	
	class ListenerAddEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			if (a.getActionCommand() == "comboBoxChanged") {
				refreshChoices();
			}
		}
	}
	
	public void refreshChoices() {
		int year, month, nod;
		
		month = cmbMonth.getSelectedIndex();
		year = cmbYear.getSelectedIndex() + (currYear - 100);

		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		String cmbDayItems[] = new String[nod];

		for (int i = 0; i < nod; i++) {
			cmbDayItems[i] = new String("" + (i + 1));
		}

		DefaultComboBoxModel days = new DefaultComboBoxModel(cmbDayItems);
		cmbDay.setModel(days);
		repaint();
	}
}
