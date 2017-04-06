package xlash.pi.graphics.gui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import xlash.pi.util.GuiUtil;

public abstract class ButtonGui extends Gui{
	
	private boolean beingPressed;
	private boolean prevPressed;
	private Point initialPoint;
	
	public String text;
	
	public ButtonGui(int x, int y, int width, int height, String text){
		super(x, y, width, height);
		this.text = text;
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(beingPressed){
			g2d.fillRect(getX(), getY(), getWidth(), getHeight());
		}else{
			g2d.drawRect(getX(), getY(), getWidth(), getHeight());
			GuiUtil.drawStringCentered(g2d, text, bounds);
		}
	}
	
	@Override
	public void onClick(Point point, int button, boolean pressed){
		if(!prevPressed) this.initialPoint = point;
		if(point!=null && button==MouseEvent.BUTTON1 && bounds.contains(point) && bounds.contains(initialPoint) && pressed) beingPressed = true;
		if(point == null || !bounds.contains(point)) beingPressed = false;
		if(button==MouseEvent.BUTTON1 && beingPressed && !pressed){
			beingPressed = false;
			onActivate();
		}
		prevPressed = pressed;
	}
	
	public abstract void onActivate();

}
