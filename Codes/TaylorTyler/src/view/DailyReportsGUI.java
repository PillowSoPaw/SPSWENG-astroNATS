package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class DailyReportsGUI extends JPanel{

	private JLabel labelReports;
	private JTable reportsTable;
	private JScrollPane reportsScroll;
	private JLabel lblPreviousWeek;
	
	private JButton prevButton;
	private JButton nextButton;
	private JLabel lblNextWeek;
	private JLabel lblMonthWeeknumber;
	
	private int currMonth; //Current Month
	private int currYear; //Current Year
	private int dayMarker; //Tells the first day of each table.
	private int amtDaysInMonth; //Tells how many days are in the current month.
	
	public DailyReportsGUI(){
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(128, 128, 0));
		setSize(821, 483);
		//setResizable(false);
		setVisible(true);
		setLayout(null);
		
		String[] column = {"Product", "Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7"};
		String[][] rows = 
			{
				{"", "Manager", "Manager", "Manager", "Manager", "Manager", "Manager", "Manager"},
				{"Sample", "5", "6", "6",  "7", "6", "6", "6"} 
			};
		
		reportsTable = new JTable(rows, column);
		reportsTable.setSize(600, 400);
		
		reportsScroll = new JScrollPane(reportsTable);
		reportsScroll.setBounds(10, 85, 795, 294);
		add(reportsScroll);
		
		JLabel lblDailyReports = new JLabel("Daily Reports");
		lblDailyReports.setForeground(UIManager.getColor("Button.foreground"));
		lblDailyReports.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDailyReports.setHorizontalAlignment(SwingConstants.CENTER);
		lblDailyReports.setBounds(10, 11, 795, 35);
		add(lblDailyReports);
		
		prevButton = new JButton("<");
		prevButton.setFont(new Font("Tahoma", Font.BOLD, 35));
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		prevButton.setBounds(10, 390, 89, 35);
		add(prevButton);
		
		lblPreviousWeek = new JLabel("Previous Week");
		lblPreviousWeek.setBounds(10, 430, 89, 14);
		add(lblPreviousWeek);
		
		nextButton = new JButton(">");
		nextButton.setFont(new Font("Tahoma", Font.BOLD, 35));
		nextButton.setBounds(716, 390, 89, 35);
		add(nextButton);
		
		WeekButtonListener buttonListener = new WeekButtonListener();
		
		nextButton.addActionListener(buttonListener);
		prevButton.addActionListener(buttonListener);
		
		lblNextWeek = new JLabel("Next Week");
		lblNextWeek.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNextWeek.setBounds(716, 430, 89, 14);
		add(lblNextWeek);
		
		lblMonthWeeknumber = new JLabel("Month: December Year: 2015 Week: (Number)");
		lblMonthWeeknumber.setForeground(UIManager.getColor("Button.background"));
		lblMonthWeeknumber.setBackground(new Color(0, 51, 102));
		lblMonthWeeknumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMonthWeeknumber.setBounds(10, 48, 795, 26);
		add(lblMonthWeeknumber);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 795, 35);
		add(panel);
		
		//Instantiating date variables
		
		currYear = Calendar.getInstance().get(Calendar.YEAR);
		currMonth = Calendar.getInstance().get(Calendar.MONTH);
		dayMarker = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 6;
		
		if(dayMarker <= 0)
			dayMarker = 1;
		
		GregorianCalendar cal = new GregorianCalendar(currYear, currMonth, 1);
		amtDaysInMonth = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) + 1;
		
		changeWeek(2);
		
		repaint();
	}
	
	private void changeWeek(int direction){ //0 is previous, 1 is next, 2 refresh
		if(direction == 0){
			if((dayMarker - 7) > -6){
				dayMarker-= 7;
			}else{
				currMonth--;
				if(currMonth < 0){
					currYear--;
					currMonth = 11;
					GregorianCalendar cal = new GregorianCalendar(currYear, currMonth, 1);
					cal = new GregorianCalendar(currYear, currMonth, 1);
				}
				GregorianCalendar cal = new GregorianCalendar(currYear, currMonth, 1);
				dayMarker = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - 6;
			}
		}else if(direction == 1){
			if((dayMarker + 7) < amtDaysInMonth){
				dayMarker+= 7;
			}else {
				dayMarker = 1;
				currMonth++;
				if(currMonth >= 12){
					currMonth = 0;
					currYear++;
				}
			}
		}
		String[] column = new String[8];
		column[0] = "Products";
		
		GregorianCalendar cal = new GregorianCalendar(currYear, currMonth, 1);
		amtDaysInMonth = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)  + 1;
		
		for(int i = 0; i < 7; i++){
			if((dayMarker + i) < amtDaysInMonth && (dayMarker + i) > 0)
				column[i + 1] = "" + (dayMarker + i);
			else column[i + 1] = "";
		}
		//Get data from database to create this
			
		String[][] rows = 
			{
				{"", "Manager", "Manager", "Manager", "Manager", "Manager", "Manager", "Manager"},
				{"Sample", "5", "6", "6",  "7", "6", "6", "6"} 
			};
		
		DefaultTableModel model = new DefaultTableModel(rows, column);
		reportsTable.setModel(model);
		refreshDateLabel();
		repaint();
	}
	
	private void refreshDateLabel(){
		String month, year, week;
		
		month = "NaN";
		
		switch(currMonth){
			case 0: month = "January";break;
			case 1: month = "Febrary";break;
			case 2: month = "March";break;
			case 3: month = "April";break;
			case 4: month = "May";break;
			case 5: month = "June";break;
			case 6: month = "July";break;
			case 7: month = "August";break;
			case 8: month = "September";break;
			case 9: month = "October";break;
			case 10: month = "November";break;
			case 11: month = "December";break;
		}
		
		year = "" + currYear;
		week = "" + (dayMarker/7);

		lblMonthWeeknumber.setText("Month: " + month + " Year: " + year);
	}
	
	private class WeekButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(prevButton)){
				changeWeek(0);
			}if(e.getSource().equals(nextButton)){
				changeWeek(1);
			}
		}
	}
}