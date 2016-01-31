package face_mapper;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;

/**
 * Controller for the FaceMapper Project.
 * @author Davis Batten
 *
 */


public class MapperApplication {
	
	public static void main(String[] args){
		System.out.println("Mapper application running");
		
		//Load Haar Cascade
		CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_default.xml");
		
		//Build and train new Face Recognizer
		String trainingDir = "trainingImages/";
		String testImagePath = "testImages/davis_test.jpg";
		FaceRecognizer faceRecognizer = FaceRecognitionUtility.newRecognizer(trainingDir);
		
		//TODO get image from GUI
		
		Mat testImage = imread(testImagePath);
		Mat testImageGray = imread(testImagePath, CV_LOAD_IMAGE_GRAYSCALE);
		
		//Detect faces
		RectVector faces = FaceDetector.detectFaces(faceDetector, testImageGray);
		
		//Get current timestamp
		Date time = new Date(System.currentTimeMillis());
		
		//Make a FaceMap to hold FaceElements as they are created
		FaceMap faceMap = new FaceMap();
		
		//Do face recognition
		for(int i = 0; i < faces.size(); i++){
			//Draw detection square
			Rect face = faces.get(i);
			FaceDetector.drawRectangle(face, testImage);
			
			//Predict UserId from face
			Mat faceMat = new Mat(testImageGray, face);
			int predictedId = faceRecognizer.predict(faceMat); //TODO prediction accuracy?
			String box_text = String.format("Prediction = %d", predictedId);
			System.out.println(box_text);
			//TODO add box text to image
			
			//Build FaceElement and add  to FaceMap
			FaceElement fe = new FaceElement(testImage, time, predictedId, face.x(), face.y());
			faceMap.addFace(fe);
			
			//TODO update attendance
		}
	
		
		
		//Output result
		String filename = "output.png";
		System.out.println(String.format("Writing %s", filename));
		imwrite(filename, testImage);
		
	}

}
