package face_mapper;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import java.sql.Date;
import java.util.ArrayList;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;
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
		
		//Scalar colors
		Scalar green = new Scalar(0,255,0,0);
		//Scalar blue = new Scalar(255,0,0,0);
		//Scalar red = new Scalar(0,0,255,0);
		
		//TODO get image from GUI
		
		Mat testImage = imread(testImagePath);
		Mat testImageGray = imread(testImagePath, CV_LOAD_IMAGE_GRAYSCALE);
		
		//Detect faces
		RectVector faces = FaceDetector.detectFaces(faceDetector, testImageGray);
		
		//Get current timestamp
		Date time = new Date(System.currentTimeMillis());
		
		//Make a FaceMap to hold FaceElements as they are created
		FaceMap faceMap = new FaceMap();
		
		ArrayList<Integer> attendance = new ArrayList<Integer>();
		
		//Do face recognition
		for(int i = 0; i < faces.size(); i++){
			//Draw detection square
			Rect face = faces.get(i);
			FaceDetector.drawRectangle(face, testImage);
			
			//Predict UserId from face
			Mat faceMat = new Mat(testImageGray, face);
			int predictedId = faceRecognizer.predict(faceMat);
			String box_text = String.format("%s", predictedId); // TODO look up user from ID
			System.out.println(box_text);
			//TODO add box text to image
			System.out.println("x:" + face.x() + "  y:" + face.y());
			putText(testImage, box_text, new Point(face.x(), face.y() - 10), CV_FONT_HERSHEY_DUPLEX , 1.0, green);
			
			//Build FaceElement and add  to FaceMap
			FaceElement fe = new FaceElement(testImage, time, predictedId, face.x(), face.y());
			faceMap.addFace(fe);
			
			//TODO update attendance
			attendance.add(predictedId);
		}
	
		//should call take attendance here
		
		//Output result to history
		String filename = "history/" + time.getTime() + ".jpg";
		System.out.println(String.format("Writing %s", filename));
		imwrite(filename, testImage);
		
		//Output temporary result
		String filenameTemp = "output.png";
		System.out.println(String.format("Writing %s", filenameTemp));
		imwrite(filenameTemp, testImage);
		
	}

}
