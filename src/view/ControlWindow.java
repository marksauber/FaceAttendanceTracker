package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControlWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlWindow frame = new ControlWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ControlWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		
		////////////
		//Menu Bar//
		////////////
		
		//creates the menu bar 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//adds an view tab to the menu bar 
		JMenu menu_View = new JMenu("View");
		menuBar.add(menu_View);
		//adds "view attendance history" to view menu. Clicking will open a new window to view attendance history 
		JMenuItem menu_item_ViewAttendanceHistory = new JMenuItem("View Attendance History");
		menu_item_ViewAttendanceHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAttendance va = new ViewAttendance();
				va.setVisible(true);
			}
		});
		menu_View.add(menu_item_ViewAttendanceHistory);
		
		////////////////////////
		//Application Controls//
		////////////////////////
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JPanel ButtonPanel = new JPanel();
		contentPane.add(ButtonPanel, BorderLayout.SOUTH);
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnTakeAttendance = new JButton("Take Attendance");
		btnTakeAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		ButtonPanel.add(btnTakeAttendance);
	}

}
