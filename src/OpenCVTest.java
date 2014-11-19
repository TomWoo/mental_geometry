import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class OpenCVTest {
	BufferedImage bufImage = null;

	public static void main(String[] args) {
		new OpenCVTest();
	}

	public OpenCVTest() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat img = Highgui
				.imread("C:/Users/User/SkyDrive/Duke/3_Fall_2014/HackDuke/workspace/mental_geometry/src/images/win.png");
		// Imshow imshow = new Imshow("Original");
		// imshow.showImage(img);
		displayMatImage(img, "original");
		ArrayList<Point> vertices = findVertices(img);

		// EventQueue.invokeLater(new Runnable() {
		// @Override
		// public void run() {
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (ClassNotFoundException ex) {
		// } catch (InstantiationException ex) {
		// } catch (IllegalAccessException ex) {
		// } catch (UnsupportedLookAndFeelException ex) {
		// }
		//
		// JFrame frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setLayout(new BorderLayout());
		// frame.add(new ImagePane());
		// frame.pack();
		// frame.setLocationRelativeTo(null);
		// frame.setVisible(true);
		// }
		// });
	}

	public class ImagePane extends JPanel {
		public ImagePane(BufferedImage image) {
			setLayout(new BorderLayout());
			ImageIcon icon = null;
			try {
				icon = new ImageIcon(image);
			} catch (Exception e) {
				e.printStackTrace();
			}
			add(new JLabel(icon));
		}
	}

	public void displayMatImage(Mat img, String title) {
		bufImage = toBufferedImage(img);
		// setSize(800, 480);
		// setBackground(Color.WHITE);
		// setFocusable(true);
		// repaint();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(new ImagePane(bufImage));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setTitle(title);
		frame.setVisible(true);
	}

	public ArrayList<Point> findVertices(Mat m) {
		Imgproc.cvtColor(m, m, Imgproc.COLOR_RGB2GRAY);
		Imgproc.threshold(m, m, 100, 255, Imgproc.THRESH_BINARY);
		Imgproc.cornerHarris(m, m, 4, 3, Imgproc.BORDER_DEFAULT);
		displayMatImage(m, "vertices");
		// Imshow imshow = new Imshow("Vertices");
		// imshow.showImage(m);

		ArrayList<Point> vertices = new ArrayList<Point>();
		return vertices;
	}

	public BufferedImage toBufferedImage(Mat m) {
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (m.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = m.channels() * m.cols() * m.rows();
		byte[] b = new byte[bufferSize];
		m.get(0, 0, b); // get all the pixels
		BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster()
				.getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);
		return image;
	}

	public class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
