import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;

/**
 * Controller for the FaceMapper Project.
 * @author Davis Batten
 *
 */


public class MapperApplication {
	
	public static void main(){
		//TODO
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");
		
		String trainingDir = "trainingImages/";
		FaceRecognizer faceRecognizer = FaceRecognitionUtility.newRecognizer(trainingDir);
		
		
		Mat testImage = Imgcodecs.imread("testImages/keanu_test2.jpg");
		Mat testImageGray = Imgcodecs.imread("testImages/keanu_test2.jpg", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
		
		Rect[] faces = FaceDetector.detectFaces(faceDetector, testImageGray);
		

		
		for(Rect face : faces){
			
			FaceDetector.drawRectangle(face, testImage);
			Mat faceMat1 = testImage.submat(face);
			int predictedLabel = faceRecognizer.predict(faceMat);
			//System.out.println("Predicted label:" + predictedLabel);
		}
		
		//int predictedLabel = faceRecognizer.predict(testImage);
		//System.out.println("Predicted label:" + predictedLabel);
	}

}
