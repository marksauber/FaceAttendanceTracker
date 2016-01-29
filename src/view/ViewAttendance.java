package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTree;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JList;

public class ViewAttendance extends JFrame {

	private JPanel contentPane;
	private JTable attendanceTable;

	/**
	 * Create the frame.
	 */
	public ViewAttendance() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		/*
		 * Meeting History Panel : 
		 * 
		 * Displays a list of past meeting dates. Clicking on a meeting will populate the JTable
		 * below this panel with the information of who was at that meeting, what there ID was, etc. 
		 */
		
		JPanel meetingsPanel = new JPanel();
		contentPane.add(meetingsPanel);
		meetingsPanel.setLayout(new BorderLayout(0, 0));
		
		//TODO remove - sample data for meetings 
		String[] meetings = {"1/25/2016", "1/26/2016", "1/27/2016"};
		
		JList<String> meetingsList = new JList<String>(meetings);
		meetingsPanel.add(meetingsList, BorderLayout.CENTER);
		
		/*
		 * Meeting Attendance Panel : 
		 * 
		 * Once a meeting has been selected from the meeting history panel, the information on who attended will 
		 * be displayed in this JTable 
		 */
		JPanel attendancePanel = new JPanel();
		contentPane.add(attendancePanel);
		attendancePanel.setLayout(new BorderLayout(0, 0));
		
		//TODO remove - sample data for table 
		String[] columnNames = {"Name", "ID"};
		Object[][] data = {
				{"Ben Nelson", "4815162342"}, 
				{"Mark Sauber", "2357111317"}, 
				{"Chris Kelley", "248163264128"},
				{"Davis Batten", "9021090210"}};
		
		attendanceTable = new JTable(data, columnNames);
		attendancePanel.add(new JScrollPane(attendanceTable), BorderLayout.NORTH);
	}

}
