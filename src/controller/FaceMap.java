package controller;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/**
 * Model for a Face Map object.
 * See project description.
 * @author Davis Batten, Ben Nelson
 *
 */
public class FaceMap extends JComponent{
	
	private Set<FaceElement> faces;
	private mxGraphComponent graphComponent; 
	//This will construct a new FaceMap
	public FaceMap(HashSet<FaceElement> faceSet){
		//getting an iterator over our set of faces 
		faces = faceSet; 
		Iterator<FaceElement> iterator = faces.iterator();
		
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
			//TODO implement adding edges
		}
		finally {
			graph.getModel().endUpdate();
		}
		
		graphComponent = new mxGraphComponent(graph);
	}
	
	public mxGraphComponent getGraph() {
		return graphComponent; 
	}
	
	//TODO remove testing 
	public static mxGraphComponent testFaceMap() {
		HashSet<FaceElement> faceSet = new HashSet<FaceElement>(); 
		faceSet.add(new FaceElement(null, null, new User("Ben", null, "001"), 25, 25));
		faceSet.add(new FaceElement(null, null, new User("John", null, "002"), 100, 100));
		faceSet.add(new FaceElement(null, null, new User("khoshekh", null, "004"), 200, 40));
		faceSet.add(new FaceElement(null, null, new User("chemistry", null, "004"), 130, 10));
		
		return new FaceMap(faceSet).getGraph(); 
		
	}
}
