import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	private Handler handler;
	private int x,y,width,height;
	private Rectangle bounds;
	private int xDir,yDir;
	private float vel;
public Enemy(Handler handler, int x, int y, float vel) {
	this.handler = handler;
	this.x = x;
	this.y = y;
	this.width = 10;
	this.height = 10;
	bounds = new Rectangle(x,y,width,height);
	xDir  = 0;
	yDir = 0;
	this.vel = vel;
//	if(bounds.intersects(handler.getGame().getPlayer().getSpawnBounds())) {
//		this.x -= 200;
//		this.y += 200;
//		
//	}

}
public void tick() {
	bounds.x = x;
	bounds.y = y;
	checkMove();
	if(bounds.intersects(handler.getGame().getPlayer().getBounds()) == false) {
	move();
	}else {
		handler.getGame().reset();
		
	}
	
}
private void checkMove() {
	
		if(this.x < handler.getGame().getPlayer().getX()-5) {
			xDir = 1;
			
			
		}else if(this.x > handler.getGame().getPlayer().getX()+5) {
			xDir = -1;
			
		}else {xDir = 0;}
		
        if(this.y < handler.getGame().getPlayer().getY()-5) {
			yDir = 1;
			
			
		}else if(this.y > handler.getGame().getPlayer().getY()+5) {
			yDir = -1;
			
		}else {yDir = 0;}
        
	
}
public void move() {
	this.x += vel * xDir;
	this.y += vel * yDir;
	
}
public Rectangle getBounds() {
	return bounds;
}
public void render(Graphics g) {
	g.setColor(Color.BLUE);
	g.fillRect(x, y, width, height);
	
}
}
