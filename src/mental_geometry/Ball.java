package mental_geometry;

import java.awt.Image;
import java.awt.Rectangle;


public class Ball {
	private int x, y, veloX, veloY;
	private boolean visible;
	private Image ballImage;
	private Rectangle r;
	
	public Ball(int xStart, int yStart, int vX, int vY, Image ballIm){
		this.x = xStart;
		this.y = yStart;
		this.veloX = vX;
		this.veloY = vY;
		this.ballImage = ballIm;
		visible = false;
		r = new Rectangle(x-5,y-5,x+5,y+5);
	}
	
	public void update(){
		x += veloX;
		y += veloY;
		r.setBounds(x, y, 10, 10);
		
		if (x < 800 && x >= 0 && y < 480 && y >= 0 ){
			checkcollision();
		}
		else{
			visible = false;
			r = null;
		}
	}

	private void checkcollision() {
		// TODO Auto-generated method stub
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Image getBallImage() {
		return ballImage;
	}

	public void setBallImage(Image ballImage) {
		this.ballImage = ballImage;
	}

	public Rectangle getR() {
		return r;
	}

	public void setR(Rectangle r) {
		this.r = r;
	}
	
	
	
	
	
}
