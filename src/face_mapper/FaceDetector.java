package face_mapper;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

public class FaceDetector {
	/*
	public static void main(String[] args){
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//System.out.println("Running Face Detection");
		
		CascadeClassifier faceDetector 
				= new CascadeClassifier("haarcascade_frontalface_alt.xml");
		
		Mat image = imread("test.jpg");
		
		RectVector faceDetections = detectFaces(faceDetector, image);
		
		for(int i = 0; i < faceDetections.size(); i++){
			Rect face = faceDetections.get(i);
			drawRectangle(face, image);
		}
		
		String filename = "output.png";
		System.out.println(String.format("Writing %s", filename));
		
		imwrite(filename, image);
	}*/
	
	/**
	 * detects faces in the given image
	 * @param faceDetector - cascade classifier used to detect faces
	 * @param image - mat of the image to detect faces in
	 * @return faces detected in a MatOfRect
	 */
	public static RectVector detectFaces(CascadeClassifier faceDetector, Mat image){
		RectVector faceDetections = new RectVector();
		
		//detection options
		double scaleFactor = 1.2;
		int minNeighbors = 5;
		int flags = 0;
		Size minSize = new Size(30,30);
		Size maxSize = new Size(10000,10000);
		
		faceDetector.detectMultiScale(
				image, faceDetections, scaleFactor, minNeighbors, flags, minSize, maxSize);
		
		System.out.println(String.format("Detected %s faces.", faceDetections.size()));
		
		return faceDetections;
		
		
	}
	
	/**
	 * Draw a green rectangle around the face detected
	 * @param face - a detected face
	 * @param originalImage - copy of the original image
	 */
	public static void drawRectangle(Rect face, Mat originalImage){
		Point start = new Point(face.x(), face.y());
		Point end = new Point(face.x() + face.width(), face.y() + face.height());
		Scalar green = new Scalar(0, 255, 0, 0);
		rectangle(originalImage, face, green);
	}

}
