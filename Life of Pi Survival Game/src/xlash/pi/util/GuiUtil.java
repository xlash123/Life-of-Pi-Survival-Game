package xlash.pi.util;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class GuiUtil {
	
	public static void drawStringCentered(Graphics2D g2d, String text, Rectangle guiBounds){
		Rectangle2D rect = g2d.getFontMetrics().getStringBounds(text, g2d);
		int y = (int) (guiBounds.y + guiBounds.getHeight()/2 + rect.getHeight()/4);
		int x = (int) (guiBounds.x + guiBounds.getWidth()/2 - rect.getWidth()/2);
		g2d.drawString(text, x, y);
	}
	
	public static void drawStringCenteredWidth(Graphics2D g2d, String text, Rectangle guiBounds, int y){
		Rectangle2D rect = g2d.getFontMetrics().getStringBounds(text, g2d);
		int x = (int) (guiBounds.x + guiBounds.getWidth()/2 - rect.getWidth()/2);
		g2d.drawString(text, x, y);
	}

}
