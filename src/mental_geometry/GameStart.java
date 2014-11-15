package mental_geometry;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameStart extends Applet implements Runnable, KeyListener{
	
	enum GameState {
		Running, Dead;
	}
	
	GameState state = GameState.Running;
	private Image background1;
	private URL base;
	private Image image, cannonballIm, targetIm;
	private Graphics gr;
	private Ball cannonball;
	public static Ball target;
	public static boolean levelWon;
	
	private Cannon cannon;
	
	@Override
	public void init(){
		setSize(800,480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		//Frame frame = (Frame) this.getParent();
		//frame.setTitle("Mental Geometry");
		try {
			base = getCodeBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
        try {
			background1 = ImageIO.read(new File("images/b1.png"));
	        cannonballIm = ImageIO.read(new File("images/ball.png")); 
	        targetIm = ImageIO.read(new File("images/target.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(){
		cannonball = new Ball(0,0,1,1, false, cannonballIm); //CHANGE THIS!!!
		target = new Ball(0,0,1,1, true, targetIm); //CHANGE THIS!!!
		levelWon = false;
        
        cannon = new Cannon("images/cannon-new.png", 200, 200); //change this
        cannon.setTargetAngle(180);
		
		Thread thread = new Thread(this);
	    thread.start();
	}
	
	@Override
	public void stop(){
		super.stop();
	}
	
	@Override
	public void destroy(){
		super.destroy();
	}

	int counter = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		repaint();
		try {
			Thread.sleep(17);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("run"+counter+" ");
	}
	@Override
	public void update(Graphics g){
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			gr = image.getGraphics();
		}

		gr.setColor(getBackground());
		gr.fillRect(0, 0, getWidth(), getHeight());
		gr.setColor(getForeground());
		paint(gr);

		g.drawImage(image, 0, 0, this);
		System.out.print("update"+counter+" ");
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(background1,0,0,this);
		
//		g.drawImage(cannon.getImage(), cannon.getX(), cannon.getY(), this);
//		AffineTransformOp op = cannon.update();
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.drawImage(op.filter(cannon.getImage(), null), cannon.getX(), cannon.getY(), null);
		System.out.println("paint"+counter+" ");
		counter++;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static Ball getTarget(){
		return target;
	}
	
	public static void setLevelWon(boolean lvlWon){
		levelWon = lvlWon;
	}

}
