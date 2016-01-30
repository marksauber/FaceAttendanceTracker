import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/**
 * Model for a Face Map object.
 * See project description.
 * @author Davis Batten
 *
 */
public class FaceMap extends JFrame{
	
	private Set<FaceElement> faces;
	
	//This will construct a new FaceMap, which is a JFrame.
	public FaceMap(HashSet<FaceElement> faceSet){
		//getting an iterator over our set of faces 
		faces = faceSet; 
		Iterator iterator = faces.iterator();
		
		//JGraphx boilerplate 
		mxGraph graph = new mxGraph(); 
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		
		try {
			//Iterating over the face set to add a vertex for each face. 
			//Edges aren't added now. We also add information about each face in the vertex 
			while(iterator.hasNext()) {
				//get information about this user 
				FaceElement face = (FaceElement) iterator.next();
				User user = face.getUser(); 
				//put the user's information into a string 
				String information = user.getName() + "\n" + user.getId();
				//add this vertex to the graph 
				graph.insertVertex(parent, null, information, face.getXCoord(), face.getYCoord(), 50, 50);
			}
		}
		finally {
			graph.getModel().endUpdate();
		}
		
		mxGraphComponent graphComponenet = new mxGraphComponent(graph);
		getContentPane().add(graphComponenet);
	}
	
	public static void main(String[] args) {
		//TODO remove testing 
		HashSet<FaceElement> faceSet = new HashSet<FaceElement>(); 
		faceSet.add(new FaceElement(null, null, new User("Ben", null, "001"), 25, 25));
		faceSet.add(new FaceElement(null, null, new User("John", null, "002"), 100, 100));
		faceSet.add(new FaceElement(null, null, new User("khoshekh", null, "004"), 200, 40));
		faceSet.add(new FaceElement(null, null, new User("chemistry", null, "004"), 130, 10));
		
		FaceMap frame = new FaceMap(faceSet); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}
	
	//Adds a face to the map;
	public void addFace(FaceElement face){
		faces.add(face);
	}
}
