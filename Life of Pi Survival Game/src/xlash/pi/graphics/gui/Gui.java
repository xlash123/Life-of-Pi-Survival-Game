package xlash.pi.graphics.gui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import xlash.pi.Game;

public abstract class Gui {
	
	public Rectangle bounds;
	
	public Gui(int x, int y, int width, int height){
		this.bounds = new Rectangle(x,y,width,height);
	}
	
	public Gui(){
		this(0,0,Game.window.getPanelWidth(), Game.window.getPanelHeight());
	}
	
	/**
	 * Draw method for the gui relative to panel. Be sure to account for relative gui position.
	 * @param g2d Graphics component
	 */
	public abstract void draw(Graphics2D g2d);
	
	/**
	 * Called when the window changes size.
	 * @param width
	 * @param height
	 */
	public void onWindowChange(int width, int height){}
	
	/**
	 * Runs every click.
	 * @param point Relative to the gui's position.
	 */
	public void onClick(Point point, int button, boolean pressed){}
	
	public int getX(){
		return bounds.x;
	}
	
	public int getY(){
		return bounds.y;
	}
	
	public int getWidth(){
		return bounds.width;
	}
	
	public int getHeight(){
		return bounds.height;
	}
	
	public void setPosition(int x, int y, int width, int height){
		bounds.setBounds(x, y, width, height);
	}
	
	public void setPosition(int x, int y){
		this.setPosition(x, y, getWidth(), getHeight());
	}

}
