import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {
	
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("Running Face Detection");
		
		CascadeClassifier faceDetector 
				= new CascadeClassifier("haarcascade_frontalface_alt.xml");
		
		
		Mat image = Imgcodecs.imread("test.jpg");
		MatOfRect faceDetections = new MatOfRect();
		
		//detection options
		double scaleFactor = 1.2;
		int minNeighbors = 5;
		int flags = 0;
		Size minSize = new Size(30,30);
		Size maxSize = new Size(10000,10000);
		
		faceDetector.detectMultiScale(
				image, faceDetections, scaleFactor, minNeighbors, flags, minSize, maxSize);
		
		System.out.println(String.format("Detected %s faces.", faceDetections.toArray().length));
		
		for(Rect rect : faceDetections.toArray()){
			Point start = new Point(rect.x, rect.y);
			Point end = new Point(rect.x + rect.width, rect.y + rect.height);
			Imgproc.rectangle(image, start, end, new Scalar(0,250,0));
			System.out.println(rect.toString());
			
		}
		
		String filename = "output.png";
		System.out.println(String.format("Writing %s", filename));
		
		Imgcodecs.imwrite(filename, image);
	}

}
