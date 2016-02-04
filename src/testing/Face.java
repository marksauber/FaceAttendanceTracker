package testing;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import view.ViewAttendance;

public class Face {
	private String path;
	private JLabel lbl_photo;
	private JLabel lbl_faceMap;
	private ImageIcon image;
	private JFrame frmFaceMapper;
	private JTextField textField_addPhoto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Face window = new Face();
					window.frmFaceMapper.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Face() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmFaceMapper = new JFrame();
		frmFaceMapper.setTitle("Face Mapper");
		frmFaceMapper.setBounds(100, 100, 935, 499);
		frmFaceMapper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField_addPhoto = new JTextField();
		textField_addPhoto.setText("add photo");
		textField_addPhoto.setColumns(10);
		
		lbl_photo = new JLabel("");
		lbl_faceMap = new JLabel("");
		
		//creates the menu bar 
		JMenuBar menuBar = new JMenuBar();
		frmFaceMapper.setJMenuBar(menuBar);
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
				
		//adds browse button and displays photo and file path
		JButton btn_browse = new JButton("browse");
		btn_browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				path = f.getAbsolutePath();
				textField_addPhoto.setText(path);
				lbl_photo.setIcon(resizePhoto(path, lbl_photo));
			}
		});
		
		//gets new face map from output.png after sending the file path through mapperAplication
		JButton btn_attendance = new JButton("Take Attendance");
		btn_attendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				face_mapper.MapperApplication.main(null, path);
				
				lbl_faceMap.setIcon(resizePhoto("output.jpg", lbl_faceMap));
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frmFaceMapper.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lbl_photo, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbl_faceMap, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_addPhoto, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_browse, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addComponent(btn_attendance, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_faceMap, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(lbl_photo, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_addPhoto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_browse))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_attendance, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		frmFaceMapper.getContentPane().setLayout(groupLayout);
	}
	
	private ImageIcon resizePhoto(String path, JLabel lbl)
	{
		ImageIcon myImage = new ImageIcon(path);
		Image image = myImage.getImage();
		Image newImage = image.getScaledInstance(lbl.getWidth(),lbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon img = new ImageIcon(newImage);
		return img;
	}
}