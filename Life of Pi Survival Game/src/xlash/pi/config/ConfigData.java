package xlash.pi.config;

import java.awt.Dimension;
import java.awt.Point;

/**
 * A class used to store configuration data neatly.
 * @author Noah
 *
 */
public class ConfigData {
	
	public Dimension windowSize;
	public boolean isFullscreen;
	public Point screenLocation;
	
	public ConfigData(Dimension windowSize, boolean isFullscreen, Point screenLocation){
		this.windowSize = windowSize;
		this.isFullscreen = isFullscreen;
		this.screenLocation = screenLocation;
	}
	
	protected ConfigData(){}
	
}
