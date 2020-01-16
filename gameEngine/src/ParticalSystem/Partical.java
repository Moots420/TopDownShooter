package ParticalSystem;

import java.awt.Graphics;

public class Partical {
	public int xDir,yDir, x, y,rotation,speed;
	public Partical(int xDir, int yDir, int x, int y,int rotation, int speed) {
		this.xDir = xDir;
		this.yDir = yDir;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.speed = speed;
	}
	public void tick() {
		
	}
	public void render(Graphics g) {
		g.fillRect(x, y, 5, 5);
		
	}

}
