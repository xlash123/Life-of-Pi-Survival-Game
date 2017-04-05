package xlash.pi.graphics.gui;

import java.awt.Font;
import java.awt.Graphics2D;

public class MainMenuGui extends Gui{

	public MainMenuGui() {
	}
	
	public void onWindowChange(int width, int height){
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setFont(new Font("DisposableDroid BB", Font.TRUETYPE_FONT, 50));
		g2d.drawString("Life of Pi - Survival Game", 20, 100);
	}

}
