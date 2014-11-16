package mental_geometry;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
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
	Image image;
	private BufferedImage cannonballIm, targetIm, cannonIm;
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
        cannon = new Cannon("images/cannon-new.png", 120, 320);
        cannon.setAngle(0);
        cannon.setTargetAngle(180);
        shapes = new ArrayList<Shapes>();
        try{
        	background1 = ImageIO.read(new File("images/b1.png"));
            cannonIm = ImageIO.read(new File("images/cannon-new.png")); 
            cannonballIm = ImageIO.read(new File("images/ball.png"));
            targetIm = ImageIO.read(new File("images/target.png"));
            shapes = new ArrayList<Shapes>();
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
	}
	
	@Override
	public void start(){
		inputLine = new TextField(15);
        add(inputLine);
        inputLine.addActionListener(this);
        angleInput = -1;
		cannonball = new Ball(160,360,0,0, false, cannonballIm); //CHANGE THIS!!!
		target = new Ball(0,0,0,0, true, targetIm); //CHANGE THIS!!!
		levelWon = false;
		//System.out.println("yay");
		
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
				if(angleInput > -1){
					//System.out.println(Math.cos(Math.toRadians(angleInput))*100);
					cannonball.setVeloX(((int) Math.round(Math.cos(Math.toRadians(angleInput))*5)));
			        cannonball.setVeloY(((int) Math.round(-Math.sin(Math.sin(angleInput))*5)));
			        cannonball.setVisible(true);
				}
				cannonball.update();
				//System.out.println(cannonball.getX());
				//System.out.println(angleInput);
				repaint();
				if (levelWon 
						&& (angleInput < shapes.get(level).soluAngle + .5)
						&& (angleInput > shapes.get(level).soluAngle - .5)){
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
			if(cannonball.isVisible()){
				g.drawImage(cannonballIm, cannonball.getX()-5, cannonball.getY()-5, this);
			}
			
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
        //System.out.println(cannonball.isVisible());
	}

}
