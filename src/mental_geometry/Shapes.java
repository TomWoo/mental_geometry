package mental_geometry;

import java.awt.Image;

public class Shapes {
	int cannonX, cannonY, targetX, targetY;
	double angle;
	Image shapeIm;
	
	public Shapes(int cX, int cY, int tX, int tY, double a, Image sIm){
		this.cannonX = cX;
		this.cannonY = cY;
		this.targetX = tX;
		this.targetY = tY;
		this.angle = a;
		this.shapeIm = sIm;
	}
	
	private double solve(double offset){
		return offset + angle;
	}
}
