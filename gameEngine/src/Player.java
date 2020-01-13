
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Player {
    private int yDir,xDir;
    private float xvel,yvel,maxVel, acceleration, friction;
	public int x, y,width, height;
	private keyManager KeyManager;
	private Gun gun;
	private Handler handler;
	private Rectangle bounds,spawnBounds;

	public Player(int x, int y,Handler handler) {
		this.handler = handler;
		gun = new Gun(x-5,y-5,5,5,this,handler);
		this.x = x;
		this.y = y;
		width = 20;
		height = 20;
//		this.KeyManager = handler.getKeyMngr();
		xvel = 0;
		yvel = 0;
		maxVel = 2;
		acceleration = 0.6f;
		friction = 0.8f;
		bounds = new Rectangle(x,y,width,height);
		spawnBounds = new Rectangle(x-150,y-150,300,300);
		
	}
	
	//Tick and Render;
	
	public void tick() {
		checkMove();
		move();
		checkAttack();
		spawnBounds.x = x-150;
		spawnBounds.y = y-150;
		gun.tick();
		
		
	
	}
	public Rectangle getSpawnBounds() {
		return spawnBounds;
	}

	public void setSpawnBounds(Rectangle spawnBounds) {
		this.spawnBounds = spawnBounds;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void render(Graphics g) {
		g.setColor(new Color(0,150,150,255));
		g.fillOval(x,y,width,height);
		gun.render(g);
		
	}
	//shoot
	
	public void checkAttack() {
	if(handler.getMouseMngr().leftClick) {
		
	gun.shoot();
		handler.getMouseMngr().leftClick = false;
}
		
		
	}
	//Movement



private void checkMove() {
	if(!handler.getKeyMngr().left && !handler.getKeyMngr().right) {
		if(xvel>0) {
			xvel-= friction;
		}
		
	}else if(xvel<maxVel) {
			xvel += acceleration;
		}

	
	if(!handler.getKeyMngr().up &&!handler.getKeyMngr().down) {
		if(yvel>0) {
			yvel-= friction;
		}
	}else if(yvel<maxVel) {
			yvel += acceleration;
		}

	if(handler.getKeyMngr().left) {
		
		xDir = -1;		
	}else if(handler.getKeyMngr().right) {
		xDir = 1;
	}else if(!(xvel>0)) {xDir=0;}
	
	if(handler.getKeyMngr().down) {
		yDir = 1;
		
	}else if(handler.getKeyMngr().up) {
		yDir = -1;
		
	}else if(!(yvel>0)) {yDir=0;}
	
	
}
public float getMaxVel() {
	return maxVel;
}

public void setMaxVel(float maxVel) {
	this.maxVel = maxVel;
}

private void move() {
    if(x<=10) {
    	x+= xvel;
    }else if (x>=780) {
    	x-= xvel;
    }
    if(y<=10) {
    	y+= yvel;
    }else if (y>=580) {
    	y-= yvel;
    }
	x += xvel*xDir;
	
	y += yvel*yDir;
	bounds.x = x;
	bounds.y = y;
	
}
	



//Getters and Setters

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
public Gun getGun() {
	return gun;
}

}




