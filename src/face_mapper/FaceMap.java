package face_mapper;
import java.util.HashSet;
import java.util.Set;

/**
 * Model for a Face Map object.
 * See project description.
 * @author Davis Batten
 *
 */
public class FaceMap {
	
	private Set<FaceElement> faces;
	
	//Constructor
	public FaceMap(){
		faces = new HashSet<FaceElement>(); //may need to change set implementation
	}
	
	//Adds a face to the map;
	public void addFace(FaceElement face){
		faces.add(face);
	}
	
	//Function that returns the number of faces in the map
	public int size(){
		return faces.size();
	}
	
}
