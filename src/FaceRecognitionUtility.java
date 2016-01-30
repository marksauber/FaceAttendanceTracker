
import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_face.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

/**
 * Utility to aid in the creation of a new FaceRecognizer
 * @author Davis Batten
 *
 */
public class FaceRecognitionUtility {
	
	/**
	 * creates a new FaceRecognizer trained using the images found at the given training directory
	 * @param trainingDir - location of the sample images used to train the recognizer
	 * @return FaceRecognizer
	 */
	public static FaceRecognizer newRecognizer(String trainingDir){
		File root = new File(trainingDir);
		//filter for valid file types
		FilenameFilter imgFilter = new FilenameFilter(){
			public boolean accept(File dir, String name){
				name = name.toLowerCase();
				return name.endsWith(".jpg")|| name.endsWith(".jpeg")|| name.endsWith(".png")|| name.endsWith(".pgm");
			}
		};
		
		//get all sample image files and labels
		File[] imageFiles = root.listFiles(imgFilter);
		MatVector images = new MatVector(imageFiles.length);
		Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
		IntBuffer labelsBuf = labels.getIntBuffer();
		
		//get images and match with labels
		int counter = 0;
		for (File image : imageFiles){
			Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
			int label = Integer.parseInt(image.getName().split("\\-")[0]);
			System.out.println(label);
            images.put(counter, img);
            labelsBuf.put(counter, label);
            counter++;
		}
		
		//train new recognizer
		FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
		//FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
		//FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
		faceRecognizer.train(images, labels);
		
		return faceRecognizer;
	}
	
}
