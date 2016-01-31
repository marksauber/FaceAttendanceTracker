package face_mapper;
import java.sql.Date;
import org.bytedeco.javacpp.opencv_core.Mat;

/**
 * Model for a Face Element object.
 * See project description.
 * @author Davis Batten
 *
 */
public class FaceElement {
	
	private Mat image; //necessary?
	private Date timestamp;
	private User user;
	private int x; //x coordinate of upper left corner
	private int y;//y coordinate of upper left corner
	
	
	//constructor
	public FaceElement(Mat image, Date timestamp, int userId, int x, int y){
		this.image = image;
		this.timestamp = timestamp;
		this.x = x;
		this.y = y;
		this.user = User.findById(userId);
		
	}
	
}
