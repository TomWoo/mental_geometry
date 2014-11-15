package mental_geometry;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class GameStart extends Applet implements Runnable, KeyListener{
	
	enum GameState {
		Running, Dead;
	}
	
	GameState state = GameState.Running;
	private Image background1;
	private URL base;
	private Image image, cannonballIm, targetIm, cannonIm;
	private Cannon cannon;
	private Graphics gr;
	private Ball cannonball;
	public static Ball target;
	public static boolean levelWon;
	private ArrayList<Shapes> shapes;
	
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
        background1 = getImage(base, "images/b1.png");
        cannonIm = getImage(base, "images/cannon-new.png"); 
        cannonballIm = getImage(base, "ball.png");
        targetIm = getImage(base,"images/target.png");
        shapes = new ArrayList<Shapes>();
        
        TextField inputLine = new TextField(15);
        add(inputLine);
        
	}
	
	@Override
	public void start(){
		cannonball = new Ball(0,0,1,1, false, cannonballIm); //CHANGE THIS!!!
		target = new Ball(0,0,1,1, true, targetIm); //CHANGE THIS!!!
		levelWon = false;
		
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
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		repaint();
		try {
			Thread.sleep(17);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(background1,0,0,this);
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
