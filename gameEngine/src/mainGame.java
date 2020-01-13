import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class mainGame {
	private Player player;
	private int spawnX, spawnY;
	private keyManager keyM;
	private Handler handler;
	private ArrayList<Enemy> enemies = new ArrayList();
	private float lvl = 1;
    private int deaths = 0;
    private int highScore;
	private Font font16;
	
public mainGame(keyManager KeyManager, Handler handler,int score) {
	this.keyM = KeyManager;
	this.handler = handler;
	font16 = FontLoader.loadFont("pixel.ttf", 16);
	spawnX = handler.getRandomNumber(0, 800);
	spawnY = handler.getRandomNumber(0,600);
	player = new Player(spawnX, spawnY,handler);
	//enemies.add(new Enemy(handler,handler.getRandomNumber(0, 780),handler.getRandomNumber(0, 580),1.5f));
	addEnemy(1);
	highScore = score; 
}
//Tick and Render

public void reset() {
	enemies.clear();
	spawnX = handler.getRandomNumber(0, 780);
	spawnY = handler.getRandomNumber(0,580);
	player.x = spawnX;
	player.y = spawnY;
	player.getGun().setWeaponType(0);
	player.setMaxVel(2);
	if(deaths>highScore) {
		highScore = deaths;
		try {
			PrintWriter out = new PrintWriter("highScore.txt");
			out.println(highScore);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	lvl = 1;
	deaths = 0;
	enemies.add(new Enemy(handler,handler.getRandomNumber(0, 800),handler.getRandomNumber(0, 600),1f));
	
}
public void tick() {
	
	player.tick();
	checkCollisions();
	
	if(deaths>= 10 && deaths<20) {
		player.getGun().setWeaponType(1);
		
	}else if(deaths>= 20){
		player.getGun().setWeaponType(2);
		
	}
	for(int i = 0; i<enemies.size();i++) {
		enemies.get(i).tick(); //tick every enemy
		}
	}


public void checkCollisions() {
	for(int i = 0; i<enemies.size();i++) {
		for(int j = 0; j<player.getGun().getBullets().size(); j++) {
		if(enemies.get(i).getBounds().intersects(player.getGun().getBullets().get(j).bounds)) {
			player.getGun().getBullets().remove(j);
			enemies.remove(i); 
			//lvl += 0.1f;
			deaths += 1;
			player.setMaxVel(player.getMaxVel()+1);
			if(deaths < 10) {
				addEnemy(1);
			}else if(deaths<20) {
				addEnemy(2-enemies.size());
			}else if(deaths<30) {
				addEnemy(3-enemies.size());
			}else if(deaths>30) {
				addEnemy(4-enemies.size());
			}

			break;
		}
		}
	}
	
}
public void addEnemy(int Amount) {
	for (int i = 0; i< Amount; i++) {
	enemies.add(new Enemy(handler, handler.getRandomNumber(0, 800), handler.getRandomNumber(0, 600),lvl));
	}
	
}

public void render(Graphics g) {
	
	player.render(g);
	for(int i = 0; i<enemies.size();i++) {
		enemies.get(i).render(g);
		
	}
	Text.drawString(g,"SCORE:" + Integer.toString(deaths), 200, 30, true, Color.GREEN, font16);
	Text.drawString(g,"HIGHSCORE:" + Integer.toString(highScore), 600, 30, true, Color.GREEN, font16);
}

//Getters and Setters
public Player getPlayer() {
	return player;
}
public void setPlayer(Player player) {
	this.player = player;
}
}
