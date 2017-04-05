package xlash.pi.graphics.gui;

import java.awt.Graphics2D;
import java.awt.Point;

import xlash.pi.Game;

public abstract class Gui {
	
	public int x,y,width,height;
	
	public Gui(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	public void onClick(Point point){}

}
