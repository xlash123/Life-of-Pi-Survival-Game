package xlash.pi;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import xlash.pi.graphics.Window;
import xlash.pi.graphics.gui.Gui;
import xlash.pi.graphics.gui.MainMenuGui;

public class Game {
	
	public static Window window;
	public Gui currentGui;
	
	public Game(){
		window = new Window(){
			@Override
			public void draw(Graphics2D g2d){
				Game.this.draw(g2d);
			}
		};
		Thread gameLoop = new Thread("Game Loop"){
			@Override
			public void run(){
				initialize();
			}
		};
		gameLoop.start();
		Thread renderLoop = new Thread("Render Loop"){
			@Override
			public void run(){
				renderLoop();
			}
		};
		renderLoop.start();
	}
	
	public void initialize(){
		try{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			InputStream fontStream = getClass().getClassLoader().getResourceAsStream("xlash/pi/resources/font/DisposableDroidBB.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
			System.out.println("Registered font of name " + font.getName());
		}catch (IOException|FontFormatException e){
			e.printStackTrace();
		}
		this.currentGui = new MainMenuGui();
	}
	
	public void gameLoop(){
		
	}
	
	public void renderLoop(){
		while(true){
			window.repaint();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Draw method for the panel.
	 * @param g2d
	 */
	public void draw(Graphics2D g2d){
		if(currentGui != null) this.currentGui.draw(g2d);
	}

}
