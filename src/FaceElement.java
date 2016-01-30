import java.awt.Image;
import java.sql.Date;

/**
 * Model for a Face Element object.
 * See project description.
 * @author Davis Batten
 *
 */
public class FaceElement {
	
	private Image image;
	private Date timestamp;
	private User user;
	private int x; //x coordinate of upper left corner
	private int y;//y coordinate of upper left corner
	
	
	//constructor
	public FaceElement(Image image, Date timestamp, User user, int x, int y){
		this.image = image;
		this.timestamp = timestamp;
		this.user = user;
		this.x = x;
		this.y = y;
	}
	
	public User getUser() {
		return user; 
	}
	
	public int getXCoord() {
		return x; 
	}
	
	public int getYCoord() {
		return y; 
	}
	
}
