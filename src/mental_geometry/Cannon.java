package mental_geometry;

import java.awt.image.BufferedImage;

public class Cannon {
	private int angle, targetAngle;
	private int x, y;
	private int vRot = 1;
	private boolean readyToFire = false, visible = false;
	BufferedImage image = null;

	public void update() {
		// Moves Character or Scrolls Background accordingly.
		if(this.angle < targetAngle) {
			this.angle += vRot;
		} else if(this.angle > targetAngle) {
			this.angle -= vRot;
		}
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
	
	public int getAngle() {
		return angle;
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
