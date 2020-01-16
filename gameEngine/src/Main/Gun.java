package Main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Gun {
	private Player player;
	private int x, y, width, height;
	private float mouseX, mouseY;
	private float angle;
	private Handler handler;
	private Rectangle mouseRect;
	private int plrX, plrY;
	private int counter;
	private ArrayList<Bullet> bullets = new ArrayList();
	private int weaponType;
	

public Gun(int x, int y, int width, int height,Player player,Handler handler) {
	this.player = player;
	this.handler = handler;
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	mouseRect = new Rectangle();
	counter = 0 ;
	weaponType = 0;
	
}
    public void tick() { 
   
    for (int i = 0 ; i<bullets.size(); i++) {
    	bullets.get(i).tick();
        if(bullets.get(i).active == false) {
        	bullets.remove(i);
        	//System.out.println("removed");
        	
        }
    }
    Rotate();
	plrX = player.x/2;
	plrY = player.y/2;
    mouseX =  MouseInfo.getPointerInfo().getLocation().x - handler.getDisp().getFrame().getX();
    mouseY =  MouseInfo.getPointerInfo().getLocation().y - handler.getDisp().getFrame().getY();

}
    
    public void shoot() {
    	player.x = (int) (player.x - Math.cos(Math.toRadians(angle))*15);
    	player.y = (int) (player.y - Math.sin(Math.toRadians(angle))*15);
    	if(weaponType ==0) {
    	bullets.add(new Bullet(x,y,handler,angle));
    	}else if(weaponType == 1) {
    	bullets.add(new Bullet(x,y,handler,angle));
    	bullets.add(new Bullet(x,y,handler,angle+5));
    	bullets.add(new Bullet(x,y,handler,angle-5));
    	}else if(weaponType == 2) {
    		bullets.add(new Bullet(x,y,handler,angle-10));
    		bullets.add(new Bullet(x,y,handler,angle-5));
    		bullets.add(new Bullet(x,y,handler,angle));
        	bullets.add(new Bullet(x,y,handler,angle+5));
        	bullets.add(new Bullet(x,y,handler,angle+10));
    		
    	}
    }

	public void Rotate(){

    	float xDist, yDist;

    	xDist =  mouseX - plrX*2 ;
    	yDist = mouseY - plrY*2 ;

    	 angle = (float) (Math.atan2(yDist,xDist) * (180 / Math.PI));
    	if(angle<0) {
    		angle = angle+360;
    	}
    	//System.out.println(angle);
    
        double distance=15; //distance from center point
        x=(int) Math.round(plrX*2+9 + (Math.cos(Math.toRadians(angle))*distance));
        y=(int) Math.round(plrY*2+9 + (Math.sin(Math.toRadians(angle))*distance));
        
    }

public void render(Graphics g) {
	Color c = new Color(150,150,0,255);
	g.setColor(c);
   //	g.translate(x,y);
   
	g.fillOval(x, y, width, height);
    for (int i = 0 ; i<bullets.size(); i++) {
    	bullets.get(i).render(g);
    	
    }
}


public int getWeaponType() {
	return weaponType;
}
public void setWeaponType(int weaponType) {
	this.weaponType = weaponType;
}
public ArrayList<Bullet> getBullets() {
	return bullets;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
}

