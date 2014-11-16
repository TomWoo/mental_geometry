package mental_geometry;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Ball {
	private int x, y, veloX, veloY;
	private boolean visible, isTarget;
	private BufferedImage ballImage;
	private Rectangle r;
	
	public Ball(int xStart, int yStart, int vX, int vY, boolean isT, BufferedImage ballIm){
		this.x = xStart;
		this.y = yStart;
		this.veloX = vX;
		this.veloY = vY;
		this.ballImage = ballIm;
		this.visible = false;
		this.isTarget = isT;
		r = new Rectangle(x-5,y-5,x+5,y+5);
	}
	
	public void update(){
		x += veloX;
		y += veloY;
		r.setBounds(x-5, y-5, 10, 10);
		
		if (x < 800 && x >= 0 && y < 480 && y >= 0 ){
			checkcollision();
		}
		else{
			visible = false;
			r = null;
		}
	}

	public int getVeloX() {
		return veloX;
	}

	public void setVeloX(int veloX) {
		this.veloX = veloX;
	}

	public int getVeloY() {
		return veloY;
	}

	public void setVeloY(int veloY) {
		this.veloY = veloY;
	}

	public boolean isTarget() {
		return isTarget;
	}

	public void setTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}

	private void checkcollision() {
		// TODO Auto-generated method stub
		if(r.intersects(GameStart.getTarget().getR()) && !this.isTarget){
			GameStart.setLevelWon(true);
			visible = false;
		}
		
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

	public void setBallImage(BufferedImage ballImage) {
		this.ballImage = ballImage;
	}

	public Rectangle getR() {
		return r;
	}

	public void setR(Rectangle r) {
		this.r = r;
	}
	
	
	
	
	
}
