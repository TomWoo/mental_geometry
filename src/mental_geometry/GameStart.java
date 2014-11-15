package mental_geometry;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GameStart extends Applet implements Runnable, KeyListener, ActionListener{
	
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
	private TextField inputLine;
	private double angleInput;
	private int level = 0;
	
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
        // 360 down y, 160 accross x
        try{
        	shapes.add(new Shapes(160,360,240,221,120,0,ImageIO.read(new File("images/lvl1.png"))));
        	shapes.add(new Shapes(160,360,320,360,120,60,ImageIO.read(new File("images/lvl1.png"))));
        	shapes.add(new Shapes(160,360,160,200,90,0,ImageIO.read(new File("images/lvl2.png"))));
        	shapes.add(new Shapes(160,360,320,200,90,45,ImageIO.read(new File("images/lvl2.png"))));
        	shapes.add(new Shapes(160,360,320,360,90,90,ImageIO.read(new File("images/lvl2.png"))));
        	shapes.add(new Shapes(160,360,111,152,72,0,ImageIO.read(new File("images/lvl3.png"))));
        	shapes.add(new Shapes(160,360,240,114,72,36,ImageIO.read(new File("images/lvl3.png"))));
        	shapes.add(new Shapes(160,360,369,152,72,72,ImageIO.read(new File("images/lvl3.png"))));
        	shapes.add(new Shapes(160,360,320,360,72,108,ImageIO.read(new File("images/lvl3.png"))));
        	shapes.add(new Shapes(160,360,80,222,60,0,ImageIO.read(new File("images/lvl4.png"))));
        	shapes.add(new Shapes(160,360,160,85,60,0,ImageIO.read(new File("images/lvl4.png"))));
        	shapes.add(new Shapes(160,360,320,85,60,0,ImageIO.read(new File("images/lvl4.png"))));
        	shapes.add(new Shapes(160,360,400,222,60,0,ImageIO.read(new File("images/lvl4.png"))));
        	shapes.add(new Shapes(160,360,320,360,60,0,ImageIO.read(new File("images/lvl4.png"))));
        }
        catch(IOException e){
        	
        }
        inputLine = new TextField(15);
        add(inputLine);
        inputLine.addActionListener(this);
        angleInput = 0;
        
	}
	
	@Override
	public void start(){
		cannonball = new Ball(159,359,0,0, false, cannonballIm); //CHANGE THIS!!!
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
		if(state == GameState.Running){
			while(true){
				cannonball.update();
				repaint();
				if (levelWon){
					System.out.println("Yay!");
				}
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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
		if(state == GameState.Running){
			g.drawImage(background1,0,0,this);
			if(cannonball.isVisible()){
				g.drawImage(cannonballIm, cannonball.getX(), cannonball.getY(), this);
			}
			Shapes t = shapes.get(level);
			Image tShape = t.getShapeIm();
			if(level < 5){
				g.drawImage(tShape,160,360-tShape.getHeight(this),this);
			}
			else if(level < 8){
				g.drawImage(tShape,111,360-tShape.getHeight(this),this);
			}
			else if(level < 14){
				g.drawImage(tShape,80,360-tShape.getHeight(this),this);
			}
			g.drawImage(cannonIm,120,320,this);
			g.drawImage(targetIm, target.getX(), target.getY(), this);
			
		}
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String s = inputLine.getText();
        angleInput = Double.parseDouble(s);
        //System.out.println(angleInput);
	}

}
