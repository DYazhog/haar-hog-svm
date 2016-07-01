import java.io.File;
import java.io.IOException;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
 import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author ANASS Karim
 */
public class training {    
 public static void trainning () throws IOException{
CascadeClassifier faceDetector = new CascadeClassifier("C:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
Size sz = new Size(128,128);
File folder = new File("C:\\happy\\");
File[] listOfFiles = folder.listFiles();
int i=1;
    for (File file : listOfFiles) {
        if (file.isFile()) {
          System.out.println(file.getName());
          Mat image = Imgcodecs.imread("C:\\happy\\"+file.getName())         ;
          MatOfRect faceDetections = new MatOfRect();
          faceDetector.detectMultiScale(image, faceDetections);
          System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
          Mat ROI = null;
          for (Rect rect : faceDetections.toArray()) {
         Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 0));    
    }
    for (Rect rect : faceDetections.toArray()) {
        Mat faceImage = image.submat(rect);
        Imgproc.resize( faceImage, faceImage, sz );
        Imgcodecs.imwrite("C:\\happy\\crop\\"+String.valueOf(System.currentTimeMillis())+ ".png", faceImage);

}
    }
}

          }
}
