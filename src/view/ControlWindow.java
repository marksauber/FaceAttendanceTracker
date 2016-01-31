package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		setBounds(100, 100, 1000, 1000);
		
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
		
		//This panel will contain the Image Panel and the FaceMap Panel 
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		contentPane.add(container, BorderLayout.CENTER);
		
		//The Image Panel - displays the image used to take attendance 
		//TODO - actually implement so that the picture can be changed 
		//TODO - image resizing ? 
		JPanel imagePanel = new JPanel(); 
		BufferedImage attendancePicture = null; 
		try {
			attendancePicture = ImageIO.read(new File("test.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(attendancePicture));
		imagePanel.add(picLabel);
		container.add(imagePanel);
		
		//The FaceMap Panel - displays the facemap created from the above image
		container.add(controller.FaceMap.testFaceMap());
		
		//The Button Panel - just contains the button to take attendance 
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
