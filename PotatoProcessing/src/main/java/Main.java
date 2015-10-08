//TODO: Threshold
//TODO: Find Contours
//TODO: Count shapes
//TODO: Move stuff into separate classes
//TODO: Unit tests

import com.atul.JavaOpenCV.Imshow;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.opencv.core.*;
import org.opencv.highgui.*;
import org.opencv.imgproc.Imgproc;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException, NullPointerException {
        //test
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        System.out.println(Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat m  = Mat.eye(3, 3, CvType.CV_8UC1);
        //System.out.println("m = \n " + m.dump());

        //This sucks
        File desktopFile = new File("C:\\Users\\Will\\Google Drive\\seizure.JPG");
        File laptopFile = new File("C:\\Users\\"); //TODO
        String workingFile;

        if (desktopFile.isFile()) {
            workingFile = desktopFile.getPath();
        }
        else if (laptopFile.isFile()) {
            workingFile = laptopFile.getPath();
        }
        else {
            System.err.println("No valid images.");
            workingFile = "nope";

        }

        //one fucking week.
        Mat src=Highgui.imread(workingFile,Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat moo2 = src.clone();

        //int rng = (int)(Math.random() * 10);
        Random rng =  new Random();
        System.out.println(rng);

        int threshold_value = 128; //0-255
        int threshold_type = 0; //5 options
     /* 0: Binary
     1: Binary Inverted
     2: Threshold Truncated
     3: Threshold to Zero
     4: Threshold to Zero Inverted
    */
        final int max_BINARY_value = 255;

        Imgproc.threshold(src, moo2, threshold_value, max_BINARY_value,threshold_type );

        //Imshow im = new Imshow("Post");
        //im.showImage(moo2);

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat canny_output = new Mat();
        Mat hierarchy = new Mat();

        Imgproc.Canny(moo2, canny_output, 100.0, 200.0);
        Imgproc.findContours(canny_output, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        /*
        //https://stackoverflow.com/questions/3915959/java-using-an-array-of-points
        class Point {
            double x;
            double y;
        }


        Point[] approx = new Point[6];
        for(int i = 0; i < approx.length; i++) {
            approx[i] = new Point();
        }

        // now you can set the values, since the point's aren't null
        approx[0].x = 10;
        approx[0].y = 10;

        for (int i = 0; i < contours.size(); i++) {
            Imgproc.approxPolyDP(Mat(contours[i]), approx, );
        }
        */

        Scalar color = new Scalar( rng.nextInt(255), rng.nextInt(255), rng.nextInt(255) );

        Imgproc.drawContours(moo2, contours, -1, color, 3);

        //Highgui.imwrite("C:\\Users\\Will\\Google Drive\\seizurePost.JPG", moo2);
        //Highgui.imwrite("C:\\Users\\Will\\Desktop\\seizureContour.JPG", moo3);

        Imshow im = new Imshow("Post");
        im.showImage(moo2);

/*
        Mat moo2 = src;
        src.copyTo(moo2);

//        Mat new_image = Mat.zeros(src.size(), src.type());
        new LoadImage("C:\\Users\\Will\\Desktop\\seizure.JPG", src, "To Be Processed");
        double thresh = 3.0;
        double maxval = 115.0;
        int type = 0;

        Imgproc.threshold(src, moo2, thresh, maxval, type);

        Imshow im = new Imshow("Threshed");
        im.showImage(moo2);
*/
/*
        System.out.println("Basic Linear Transforms");
        System.out.println("------------------------");

        //Un-hardcode this
        double alpha = 1.0;
        double beta = 100.0;

        Console console = System.console();

        "System.console() returns null in an IDE, so if you really need to use System.console(), read this solution from McDowell http://illegalargumentexception.blogspot.com/2010/09/java-systemconsole-ides-and-testing.html "
        String alphaString = console.readLine("Alpha Value: [1.0-3.0]:");
        String betaString = console.readLine("Enter the beta value [0-100]:");

        double alpha = Double.parseDouble(alphaString);
        double beta = Double.parseDouble(betaString);

        System.out.println("Alpha: " + alpha);
        System.out.println("Beta:" + beta);

        src.convertTo(new_image, -1, alpha, beta);

        Imshow im = new Imshow("Post");
        im.showImage(new_image);
*/

/*
        namedWindow("OG Image", 1);
        namedWindow("Processed Image", 1);

        //Draw matrice src

        Mat img_tmp = src;
        MatOfByte matOfByte = new MatOfByte();

        Highgui.imencode(".jpg", img_tmp, matOfByte);

        byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;

        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/


/*
        try {
            Graphics g = null;
            g.drawImage(bufImage, 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

}

/*
public class MyFirstOpenCVApp {

    public static void main(String[] args) {

        // Read an image.
        final IplImage image = cvLoadImage("boldt.jpg");

        // Create image window named "My Image".
        //
        // Note that you need to indicate to CanvasFrame not to apply gamma correction,
        // by setting gamma to 1, otherwise the image will not look correct.
        final CanvasFrame canvas = new CanvasFrame("My Image", 1);

        // Request closing of the application when the image window is closed.
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        // Show image on window.
        canvas.showImage(image);
    }
}
*/