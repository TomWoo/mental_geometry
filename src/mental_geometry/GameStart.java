package mental_geometry;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class GameStart extends Applet implements Runnable, KeyListener{
	
	enum GameState {
		Running, Dead;
	}
	
	GameState state = GameState.Running;
	private Image background1;
	private URL base;
	private Image image;
	private Graphics gr;
	
	
	@Override
	public void init(){
		setSize(800,480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent();
		frame.setTitle("Mental Geometry");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
        background1 = getImage(base, "images/bg1.png");
        
	}
	
	@Override
	public void start(){
		
		
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


}
