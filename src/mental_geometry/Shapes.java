package mental_geometry;

import java.awt.Image;

public class Shapes {
	int cannonX, cannonY, targetX, targetY;
	double soluAngle, cannonAngle;
	Image shapeIm;
	
	public Shapes(int cX, int cY, int tX, int tY, double ca, double sa, Image sIm){
		this.cannonX = cX;
		this.cannonY = cY;
		this.targetX = tX;
		this.targetY = tY;
		this.cannonAngle = ca;
		this.soluAngle = sa;
		this.shapeIm = sIm;
	}
	
	private double solve(double offset){
		return offset + soluAngle - cannonAngle;
	}

	public int getCannonX() {
		return cannonX;
	}

	public void setCannonX(int cannonX) {
		this.cannonX = cannonX;
	}

	public int getCannonY() {
		return cannonY;
	}

	public void setCannonY(int cannonY) {
		this.cannonY = cannonY;
	}

	public int getTargetX() {
		return targetX;
	}

	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}

	public int getTargetY() {
		return targetY;
	}

	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}

	public double getSoluAngle() {
		return soluAngle;
	}

	public void setSoluAngle(double soluAngle) {
		this.soluAngle = soluAngle;
	}

	public double getCannonAngle() {
		return cannonAngle;
	}

	public void setCannonAngle(double cannonAngle) {
		this.cannonAngle = cannonAngle;
	}

	public Image getShapeIm() {
		return shapeIm;
	}

	public void setShapeIm(Image shapeIm) {
		this.shapeIm = shapeIm;
	}
}
