package mental_geometry;

import java.awt.Image;
import java.awt.Rectangle;

import mental_geometry.GameStart.GameState;


public class Ball {
	private double x, y;
	private double veloX, veloY;
	private boolean visible, isTarget;
	private Image ballImage;
	private Rectangle r;
	
	public double getVeloX() {
		return veloX;
	}

	public void setVeloX(double veloX) {
		this.veloX = veloX;
	}

	public double getVeloY() {
		return veloY;
	}

	public void setVeloY(double veloY) {
		this.veloY = veloY;
	}

	public boolean isTarget() {
		return isTarget;
	}

	public void setTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}

	public Ball(double xStart, double yStart, double vX, double vY, boolean isT, Image ballIm){
		this.x = xStart;
		this.y = yStart;
		this.veloX = vX;
		this.veloY = vY;
		this.ballImage = ballIm;
		this.visible = false;
		this.isTarget = isT;
		r = new Rectangle((int) x, (int) y,10,10);
	}
	
	public void update(){
		x += veloX;
		y += veloY;
		r.setBounds((int) x,(int) y, 10, 10);
		
		if (x < 800 && x >= 0 && y < 480 && y >= 0 ){
			checkcollision();
		}
		else{
			visible = false;
			r = null;
			GameStart.setState(GameState.GameOver);
		}
	}

	private void checkcollision() {
		// TODO Auto-generated method stub
		if(r.intersects(GameStart.getTarget().getR()) && !this.isTarget){
			GameStart.setLevelWon(true);
			visible = false;
			System.out.println("collision");
		}
		
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
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
