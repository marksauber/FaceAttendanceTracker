package testing;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Face {
	private String path;
	private ImageIcon image;
	private JFrame frmFaceMapper;
	private JTextField textField_addPhoto;
	/**
	 * @wbp.nonvisual location=102,159
	 */
	private final Panel panel_photo = new Panel();
	/**
	 * @wbp.nonvisual location=292,169
	 */
	private final Panel panel_faceMap = new Panel();
	/**
	 * @wbp.nonvisual location=562,169
	 */
	private final Panel panel_attendance = new Panel();

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
		
		//attendance scroll pane
		ScrollPane scroll_attendance = new ScrollPane();
		panel_attendance.add(scroll_attendance);
		JLabel lbl_attendance = new JLabel("Attendance Table");
		scroll_attendance.add(lbl_attendance);
		
		//face map scroll pane
		ScrollPane scroll_faceMap = new ScrollPane();
		panel_faceMap.add(scroll_faceMap);
		JLabel lbl_faceMap = new JLabel("Face Map");
		scroll_faceMap.add(lbl_faceMap);
		
		//searched photo pane
		ScrollPane scroll_photo = new ScrollPane();
		panel_photo.add(scroll_photo);
		JLabel lbl_photo = new JLabel("added photo");
		scroll_photo.add(lbl_photo);
		
		
		frmFaceMapper = new JFrame();
		frmFaceMapper.setTitle("Face Mapper");
		frmFaceMapper.setBounds(100, 100, 789, 415);
		frmFaceMapper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField_addPhoto = new JTextField();
		textField_addPhoto.setText("add photo");
		textField_addPhoto.setColumns(10);
		
		JButton btn_browse = new JButton("browse");
		btn_browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				path = f.getAbsolutePath();
				textField_addPhoto.setText(path);
				
//				try {
//					BufferedImage myPicture = ImageIO.read(new File(path));
//					myPicture = resizeImage(myPicture,50,50,0);
//					lbl_photo = new JLabel(new ImageIcon(myPicture));
//					lbl_photo.setBounds(0, 0, 50, 50);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
		});
		//image = new ImageIcon(getClass().getResource(path));
		//lbl_photo = new JLabel(image);
		//scroll_photo.add(lbl_photo);
		
		JButton btn_attendance = new JButton("View Attendance");
		GroupLayout groupLayout = new GroupLayout(frmFaceMapper.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(textField_addPhoto, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_browse, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addComponent(btn_attendance, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(310)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_addPhoto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_browse))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_attendance, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		frmFaceMapper.getContentPane().setLayout(groupLayout);
	}

	 BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) throws IOException {  
		BufferedImage resizedImage = new BufferedImage(width, height, type);  
		Graphics2D graphics = resizedImage.createGraphics();  
		graphics.drawImage(originalImage, 0, 0, width, height, null);  
		graphics.dispose();  
		return resizedImage;  
	}  
}