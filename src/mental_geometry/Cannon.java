package mental_geometry;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cannon {
	private double angle = 0, targetAngle = 0;
	private double vRot = 1;
	private int x, y;
	private boolean readyToFire = false, visible = false;
	BufferedImage image = null;

	public Cannon(String filename, int x0, int y0, int r, int g, int b) {
		try {
			image = ImageIO.read(new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = x0;
		y = y0;
//		image.setRGB(r, g, b);
//		Graphics2D graphics = image.createGraphics();
//		graphics.setPaint(new Color( r, g, b ) );
//		graphics.fillRect( 0, 0, image.getWidth(), image.getHeight() );
	}
	
	public void setTargetAngle(double targetAngle) {
		
			this.targetAngle = targetAngle;
		
	}

	public double getTargetAngle() {
		return targetAngle;
	}

	double rotationRequired = 0;
	public AffineTransformOp update() {
		// Moves Character or Scrolls Background accordingly.
		if(angle < targetAngle) {
			angle += vRot;
			rotationRequired += Math.toRadians(vRot);
		} else if(angle > targetAngle) {
			angle -= vRot;
			rotationRequired -= Math.toRadians(vRot);
		}
//		System.out.println(angle+" "+targetAngle+" "+rotationRequired);
//		AffineTransform tx = AffineTransform.getRotateInstance(
//				rotationRequired, x, y);
		AffineTransform tx = new AffineTransform();
		int width = image.getWidth();
		int height = image.getHeight();
		tx.rotate(rotationRequired, width/2, height/2); 
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		return op;
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.drawImage(op.filter(image, null), x, y, null);
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void shoot() {
		if (readyToFire) {
//			Ball ball = new Ball(x, y, vx, vy); //TODO offset
			visible = true;
		}
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}
}
