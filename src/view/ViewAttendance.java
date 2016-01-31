package view;

import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import database.DatabaseSupport;
import database.Person;

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
		
		//Attendance Tree 
		
		JPanel attendancePanel = new JPanel();
		//Tree where we will store the history of attendance by person. 
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("attendance");
		//add each person to the top node, and to each person add what meetings they attended
		ArrayList<Person> persons = DatabaseSupport.getAttendance();
		
		for(Person p : persons) {
			//add node for person 
			//TODO - change to name 
			DefaultMutableTreeNode person = new DefaultMutableTreeNode(p.getId());
			for (Entry<String, Integer> entry : p.getattendance().entrySet()) {
				 //key is the date value is 1 if present 
				 if(entry.getValue() == 1) {
					 //add key 
					 DefaultMutableTreeNode date = new DefaultMutableTreeNode(entry.getKey());
					 person.add(date);
				 } 
		    }
			top.add(person);
		}
		
		JTree history = new JTree(top);
		attendancePanel.add(history);
		contentPane.add(attendancePanel);
		
		
		
		
		
		
//		/*
//		 * Meeting History Panel : 
//		 * 
//		 * Displays a list of past meeting dates. Clicking on a meeting will populate the JTable
//		 * below this panel with the information of who was at that meeting, what there ID was, etc. 
//		 */
//		
//		JPanel meetingsPanel = new JPanel();
//		contentPane.add(meetingsPanel);
//		meetingsPanel.setLayout(new BorderLayout(0, 0));
//		
//		JList<String> meetingsList = new JList<String>();
//		meetingsPanel.add(meetingsList, BorderLayout.CENTER);
//		
//		/*
//		 * Meeting Attendance Panel : 
//		 * 
//		 * Once a meeting has been selected from the meeting history panel, the information on who attended will 
//		 * be displayed in this JTable 
//		 */
//		JPanel attendancePanel = new JPanel();
//		contentPane.add(attendancePanel);
//		attendancePanel.setLayout(new BorderLayout(0, 0));
//		
//		attendanceTable = meetingAttendance();
//		attendancePanel.add(new JScrollPane(attendanceTable), BorderLayout.NORTH);
	}

	//Returns the full list of meetings that have happened
	private static String[] meetingsData() {
		//TODO - only filler data for now 
		String[] meetings = {"1/25/2016", "1/26/2016", "1/27/2016"};
		return meetings; 
		
		//
	} 
	
	//Returns the information on attendees to a particular meeting
	private static JTable meetingAttendance() {
		//TODO - only filler data for now 
		String[] columnNames = {"Name", "ID"};
		Object[][] data = {
				{"Ben Nelson", "4815162342"}, 
				{"Mark Sauber", "2357111317"}, 
				{"Chris Kelley", "248163264128"},
				{"Davis Batten", "9021090210"}};
		
		return new JTable(data, columnNames);
	}
}
