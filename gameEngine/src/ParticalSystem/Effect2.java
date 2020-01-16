package ParticalSystem;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Main.Handler;

public class Effect2 extends Effects {
private int x,y,maxParticals,speed,alpha;
private Color c;
private Handler handler;
private ArrayList<Partical> particals = new ArrayList<Partical>();
	public Effect2(Handler handler, int x, int y, int maxParticals, int speed) {
		super(handler, x, y, maxParticals, speed);
	 this.maxParticals = maxParticals;
	 this.x = x;
	 this.y = y;
	 this.speed = speed;
	 this.handler = handler;
	 alpha = 255;
	 
	addParticals();
	}

	public void addParticals() {
		for(int i = 0; i < maxParticals; i++) {
			particals.add(new Partical(handler.getRandomDirection(),handler.getRandomDirection(),x,y,handler.getRandomNumber(0, 360),handler.getRandomNumber(1, speed)));
		}
		
		
	}
	public void tick() {
		c = new Color(handler.getRandomNumber(1, 255),handler.getRandomNumber(1, 255),handler.getRandomNumber(1, 255),alpha);
		alpha -= 8;
		if(alpha<=5) {
			isActive = false;
		}
		for (int i =0; i<particals.size(); i++) {
			particals.get(i).x += particals.get(i).speed*Math.cos(Math.toRadians(particals.get(i).rotation));
			particals.get(i).y += particals.get(i).speed*Math.sin(Math.toRadians(particals.get(i).rotation));
		        
		}
		
		
	}

	@Override
	public void render(Graphics g) {
	for (int i =0; i<particals.size(); i++) {
		    g.setColor(c);
		 
		    	particals.get(i).render(g);
		  
			
			
		}
		
	}

}
