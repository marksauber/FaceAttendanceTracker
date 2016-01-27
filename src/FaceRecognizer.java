
import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_face.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

public class FaceRecognizer {
	public static void main(String[] args){
		String trainingDir = "trainingImages/";
		Mat testImage = imread("testImages/keanu_test2.jpg", CV_LOAD_IMAGE_GRAYSCALE);
		
		File root = new File(trainingDir);
		FilenameFilter imgFilter = new FilenameFilter(){
			public boolean accept(File dir, String name){
				name = name.toLowerCase();
				return name.endsWith(".jpg")|| name.endsWith(".jpeg")|| name.endsWith(".png")|| name.endsWith(".pgm");
			}
		};
		
		
		//get all sample images and labels
		File[] imageFiles = root.listFiles(imgFilter);
		MatVector images = new MatVector(imageFiles.length);
		Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
		IntBuffer labelsBuf = labels.getIntBuffer();
		

		int counter = 0;
		for (File image : imageFiles){
			Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
			int label = Integer.parseInt(image.getName().split("_")[0]);
			System.out.println(label);
            images.put(counter, img);
            labelsBuf.put(counter, label);
            counter++;
		}
		
		LBPHFaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
		faceRecognizer.train(images, labels);
		
		int predictedLabel = faceRecognizer.predict(testImage);
		System.out.println("Predicted label:" + predictedLabel);
		
	}
}
