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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GameStart extends Applet implements Runnable, KeyListener, ActionListener, MouseListener{
	
	enum GameState {
		Running, Win, GameOver, Menu;
	}
	
	static GameState state = GameState.Menu;
	
	public static void setState(GameState s){
		state = s;
	}
	
	private Image background1;
	private URL base;
	Image image;
	private BufferedImage cannonballIm, targetIm, cannonIm, gameOverIm, titleIm, playButIm, winIm;
	private Cannon cannon;
	private Graphics gr;
	private Ball cannonball;
	public static Ball target;
	public static boolean levelWon, rotating = true;
	private ArrayList<Shapes> shapes;
	private TextField inputLine;
	private double angleInput;
	private int level;
	private Animation cannonFire;
	
	@Override
	public void init(){
		setSize(800,480);
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
	    addMouseListener(this);
		//Frame frame = (Frame) this.getParent();
		//frame.setTitle("Mental Geometry");
		try {
			base = getCodeBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
        cannon = new Cannon("images/cannon-new.png", 120, 320);
        cannon.setAngle(0);
        //cannon.setTargetAngle(180);
        shapes = new ArrayList<Shapes>();
        cannonFire = new Animation();
        try{
        	for (int i=1; i<=15; i++ ){
        		cannonFire.addFrame(ImageIO.read(new File("animate/" + i + ".png")), 10);
        	}
        	winIm = ImageIO.read(new File("images/win.png"));
        	titleIm = ImageIO.read(new File("images/title.png"));
        	playButIm = ImageIO.read(new File("images/playbutton.png"));
        	gameOverIm = ImageIO.read(new File("images/gameover.png"));
        	background1 = ImageIO.read(new File("images/b1.png"));
            cannonIm = ImageIO.read(new File("images/cannon-new.png")); 
            cannonballIm = ImageIO.read(new File("images/ball.png"));
            targetIm = ImageIO.read(new File("images/target.png"));
            shapes = new ArrayList<Shapes>();
        	shapes.add(new Shapes(160,360,240,221,30,0,ImageIO.read(new File("images/lvl1.png"))));
        	shapes.add(new Shapes(160,360,320,360,30,60,ImageIO.read(new File("images/lvl1.png"))));
        	shapes.add(new Shapes(160,360,160,200,0,0,ImageIO.read(new File("images/lvl2.png"))));
        	shapes.add(new Shapes(160,360,320,200,0,45,ImageIO.read(new File("images/lvl2.png"))));
        	shapes.add(new Shapes(160,360,320,360,0,90,ImageIO.read(new File("images/lvl2.png"))));
        	shapes.add(new Shapes(160,360,111,208,72-90,342,ImageIO.read(new File("images/lvl3.png"))));
        	shapes.add(new Shapes(160,360,240,114,72-90,36,ImageIO.read(new File("images/lvl3.png"))));
        	shapes.add(new Shapes(160,360,369-3,208,72-90,54,ImageIO.read(new File("images/lvl3.png"))));
        	shapes.add(new Shapes(160,360,320,360,72-90,108,ImageIO.read(new File("images/lvl3.png"))));
        	shapes.add(new Shapes(160,360,80,222,60-90,0,ImageIO.read(new File("images/lvl4.png"))));
        	shapes.add(new Shapes(160,360,160,85,60-90,0,ImageIO.read(new File("images/lvl4.png"))));
        	shapes.add(new Shapes(160,360,320,85,60-90,0,ImageIO.read(new File("images/lvl4.png"))));
        	shapes.add(new Shapes(160,360,400,222,60-90,0,ImageIO.read(new File("images/lvl4.png"))));
        	shapes.add(new Shapes(160,360,320,360,60-90,0,ImageIO.read(new File("images/lvl4.png"))));
        }
        catch(IOException e){
        	
        }
	}
	
	@Override
	public void start(){
        angleInput = -2;
		cannonball = new Ball(160,360,0,0, false, cannonballIm);
		level = 0;
		Shapes curLvl = shapes.get(level);
		target = new Ball(curLvl.getTargetX()-1,curLvl.getTargetY()-1,0,0, true, targetIm);
		levelWon = false;
		//System.out.println("yay");
		inputLine = new TextField(15);
        add(inputLine);
        inputLine.addActionListener(this);
        
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
		while(true){
			if(state == GameState.Running){
				if((angleInput > -2) && (cannon.getTargetAngle() == 0)){
					cannon.setTargetAngle(angleInput);
				}
				if(Math.abs(cannon.getAngle() - cannon.getTargetAngle()) < 1.5){
					cannon.setReadyToFire(true);
				}
				if(angleInput > -1 && cannon.isReadyToFire()){
					rotating = false;
					//System.out.println(Math.cos(Math.toRadians(angleInput))*100);
					cannonball.setVeloX(3*Math.cos(Math.toRadians(90-angleInput)));
					cannonball.setVeloY(-3*Math.sin(Math.toRadians(90-angleInput)));
					cannonball.setVisible(true);
				}
				cannon.setReadyToFire(false);
				cannonball.update();
				//System.out.println(cannonball.getX() +" "+ cannonball.getY());
				//System.out.println(cannonball.getX());
				//System.out.println(angleInput);
				repaint();
				//System.out.println(levelWon);
				//System.out.println(shapes.get(level).getSoluAngle());
				if (levelWon ){//&& Math.abs(angleInput - shapes.get(level).solve(shapes.get(level).getCannonAngle())) < 1){ 
					//&& (Math.abs(angleInput - shapes.get(level).soluAngle) < 1.5)){
					//System.out.println("Yay!");
					state = GameState.Win;
				}
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(state == GameState.Menu){
				repaint();
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
			else if(level < 9){
				g.drawImage(tShape,111,360-tShape.getHeight(this),this);
			}
			else{
				g.drawImage(tShape,80,360-tShape.getHeight(this),this);
			}
//			g.drawImage(cannonIm,120,320,this);
//			if(rotating){
				AffineTransformOp op = cannon.update();
				//System.out.println(cannon.getAngle()+" "+cannon.getX()+" "+cannon.getY());
				g.drawImage(op.filter(cannon.getImage(), null), cannon.getX(), cannon.getY(), null);
//			}else{
//				BufferedImage temp = cannonFire.getImage();
//				AffineTransform tx = new AffineTransform();
//				int width = temp.getWidth();
//				int height = temp.getHeight();
//				tx.rotate(angleInput, width/2, height/2); 
//				AffineTransformOp op = new AffineTransformOp(tx,
//						AffineTransformOp.TYPE_BILINEAR);
//				g.drawImage(op.filter(temp, null),cannonball.getX(),cannonball.getY(),this);
//			}
			
			//g.drawImage(targetIm, target.getX(), target.getY(), this);
			if(cannonball.isVisible()){
				g.drawImage(cannonballIm, (int) cannonball.getX()-5, (int) cannonball.getY()-5, this);
				//if(cannonFire.getCurrentFrame() != 15){
				//	g.drawImage(cannonFire.getImage(),cannonball.getX()-5,cannonball.getY()-5,this);
				//}
			}
			
			g.drawImage(targetIm, (int) target.getX()-5, (int) target.getY()-5, this);
		}
		else if(state == GameState.Menu){
			g.drawImage(titleIm,108,100,this);
			g.drawImage(playButIm, 280, 300, this);
		}
		else if(state == GameState.GameOver){
			g.drawImage(gameOverIm, 0, 0, this);
		}
		else if(state == GameState.Win){
			g.drawImage(winIm, 0, 0, this);
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(state == GameState.Menu && Math.abs(arg0.getX() - 400) < 150
				&& Math.abs(arg0.getY() - 325) < 25){
			level = 0;
			angleInput = -2;
			cannonball = new Ball(160,360,0,0, false, cannonballIm); //CHANGE THIS!!!
			Shapes curLvl = shapes.get(level);
			target = new Ball(curLvl.getTargetX()-1,curLvl.getTargetY()-1,0,0, true, targetIm); //CHANGE THIS!!!
			System.out.println(curLvl.getTargetX());
			System.out.println(curLvl.getTargetY());
			levelWon = false;
			cannon = new Cannon("images/cannon-new.png", 120, 320);
	        cannon.setAngle(0);
	        state = GameState.Running;
			//System.out.println(arg0.getX());
			//System.out.println(arg0.getY());
		}
		if(state == GameState.Win){
			if(level < 14){
				level++;
				angleInput = -2;
				cannonball = new Ball(160,360,0,0, false, cannonballIm); //CHANGE THIS!!!
				Shapes curLvl = shapes.get(level);
				target = new Ball(curLvl.getTargetX()-1,curLvl.getTargetY()-1,0,0, true, targetIm); //CHANGE THIS!!!
				levelWon = false;
				//cannon.setAngle(-angleInput);
				cannon = new Cannon("images/cannon-new.png", 120, 320);
		        cannon.setAngle(0);
		        state = GameState.Running;
			}
			else{
				state = GameState.Menu;
			}
		}
		if(state == GameState.GameOver){
			state = GameState.Menu;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}