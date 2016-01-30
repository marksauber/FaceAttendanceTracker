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
	
	//Constructor
	public FaceMap(){
		mxGraph graph = new mxGraph(); 
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();
		try {
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30);
			Object v2 = graph.insertVertex(parent, null, "Hello", 240, 150, 80, 30);
			graph.insertEdge(parent, null, "" , v1, v2);
		}
		finally {
			graph.getModel().endUpdate();
		}
		
		mxGraphComponent graphComponenet = new mxGraphComponent(graph);
		getContentPane().add(graphComponenet);
	}
	
	public static void main(String[] args) {
		FaceMap frame = new FaceMap(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}
	
	//Adds a face to the map;
	public void addFace(FaceElement face){
		faces.add(face);
	}
}
