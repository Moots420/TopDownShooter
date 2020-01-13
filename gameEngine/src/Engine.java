

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class Engine implements Runnable{
private MouseHandler mouseMngr;
private int width, height; // resoloution
private int Fps;
private String title;
private BufferStrategy bs;
private Graphics g;
private Display disp;
private boolean running = false;
private Thread thread;
private Entity entity = new Entity();
private mainGame game;
private keyManager KeyManager;
private Handler handler;
private int score;
	
	public Engine(String title, int resX, int resY ) {
		System.setProperty("sun.java2d.opengl", "true"); //set to opengl
		Fps = 0;
		score = 0;
		loadScore();
		this.width = resX;
		this.height = resY;
		this.title = title;
		handler = new Handler();
		KeyManager = new keyManager();
		mouseMngr = new MouseHandler();
		game = new mainGame(KeyManager, handler,score);
		handler.setKeyMngr(KeyManager);
		handler.setMouseMngr(mouseMngr);
		handler.setEngine(this);
		handler.setGame(game);

		
	}
	
	private void loadScore() { 
		try {
			BufferedReader checkScores = new BufferedReader(new FileReader("highScore.txt"));
			score = Integer.parseInt(checkScores.readLine());
			checkScores.close();
			System.out.println("score loaded");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void tick() {
		game.tick();
		KeyManager.tick();
	
	}
	private void render() {
		
		bs = disp.getCanvas().getBufferStrategy();
		if(bs == null) {
			disp.getCanvas().createBufferStrategy(3);
		return;
		}
		g = bs.getDrawGraphics();
		//clear screen
		g.clearRect(0,0,width,height);
		//draw here
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		game.render(g);
		
		
		//end draw
		
		bs.show();
		g.dispose();
	}
	
	private void init() {
		disp = new Display(title, width, height,handler); 
		handler.setDisp(disp);
		disp.getFrame().addKeyListener(KeyManager);
		
		
		
	}
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/ fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta>=1) {
		tick();
		render();
		ticks++; 
		delta--;
			}
			if(timer>= 1000000000) {
				//System.out.println("ticks and frames:" + ticks);
				Fps = ticks;
				ticks = 0;
				timer = 0;
			}
			
		}
		stop();
		
	}
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		
		thread.start();
	}
public synchronized void stop() {
	if(!running)
		return;
	running = false;
	try {
	
		thread.join();
		
	}catch (InterruptedException e) {
		e.printStackTrace();
	}
}

}
