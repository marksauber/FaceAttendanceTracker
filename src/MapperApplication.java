import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;


/**
 * Controller for the FaceMapper Project.
 * @author Davis Batten
 *
 */


public class MapperApplication {
	
	public static void main(String[] args){
		System.out.println("Mapper application running");
		
		CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");
		
		String trainingDir = "trainingImages/";
		String testImagePath = "testImages/davis_test.jpg";
		FaceRecognizer faceRecognizer = FaceRecognitionUtility.newRecognizer(trainingDir);
		
		Mat testImage = imread(testImagePath);
		Mat testImageGray = imread(testImagePath, CV_LOAD_IMAGE_GRAYSCALE);
		
		RectVector faces = FaceDetector.detectFaces(faceDetector, testImageGray);
		
		
		for(int i = 0; i < faces.size(); i++){
			Rect face = faces.get(i);
			FaceDetector.drawRectangle(face, testImage);
			Mat faceMat = new Mat(testImageGray, face);
			int predictedLabel = faceRecognizer.predict(faceMat);
			String box_text = String.format("Prediction = %d", predictedLabel);
			System.out.println(box_text);
		}
		
		String filename = "output.png";
		System.out.println(String.format("Writing %s", filename));
		
		imwrite(filename, testImage);
		
	}

}
