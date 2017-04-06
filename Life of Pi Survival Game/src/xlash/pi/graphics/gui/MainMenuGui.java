package xlash.pi.graphics.gui;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import xlash.pi.Game;
import xlash.pi.util.GuiUtil;

public class MainMenuGui extends Gui{
	
	public ButtonGui sampleButton;

	public MainMenuGui() {
		sampleButton = new ButtonGui(100,100,100,40,"Hi mom"){
			@Override
			public void onActivate() {
				Game.window.toggleFullscreen();
			}
		};
	}
	
	public void onWindowChange(int width, int height){
		this.setPosition(getX(), getY(), width, height);
	}
	
	@Override
	public void onClick(Point point, int button, boolean pressed){
		this.sampleButton.onClick(point, button, pressed);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setFont(new Font("DisposableDroid BB", Font.TRUETYPE_FONT, 50));
		GuiUtil.drawStringCenteredWidth(g2d, "Life of Pi - Survival Game", bounds, 90);
		g2d.setFont(new Font("DisposableDroid BB", Font.TRUETYPE_FONT, 32));
		sampleButton.draw(g2d);
	}

}
