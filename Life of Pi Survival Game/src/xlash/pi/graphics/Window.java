package xlash.pi.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import xlash.pi.config.ConfigUtil;

public abstract class Window {
	
	public JFrame frame;
	public JPanel panel;
	
	private boolean isFullscreen;
	
	public Window(){
		ConfigUtil.readConfigFile();
		isFullscreen = ConfigUtil.loadedConfig.isFullscreen;
		frame = new JFrame("Life of Pi - Survival Game");
		frame.setPreferredSize(ConfigUtil.loadedConfig.windowSize);
		panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				Graphics2D g2d = (Graphics2D) g;
				super.paintComponent(g2d);
				draw(g2d);
			}
		};
		panel.setDoubleBuffered(true);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				onClose();
			}
		});
		this.toggleFullscreen(isFullscreen);
		frame.pack();
		frame.setLocation(ConfigUtil.loadedConfig.screenLocation);
		frame.setVisible(true);
	}
	
	public void repaint(){
		frame.repaint();
	}
	
	public void onClose(){
		if(!isFullscreen) ConfigUtil.loadedConfig.windowSize = frame.getSize();
		ConfigUtil.loadedConfig.isFullscreen = isFullscreen;
		if(!isFullscreen) ConfigUtil.loadedConfig.screenLocation = frame.getLocation();
		ConfigUtil.saveConfigFile();
	}
	
	public void toggleFullscreen(){
		isFullscreen = !isFullscreen;
		this.toggleFullscreen(isFullscreen);
	}
	
	public void toggleFullscreen(boolean set){
		if(set){
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}else frame.setExtendedState(JFrame.NORMAL);
		frame.setUndecorated(isFullscreen);
	}
	
	public int getPanelWidth(){
		return panel.getWidth();
	}
	
	public int getPanelHeight(){
		return panel.getHeight();
	}
	
	public abstract void draw(Graphics2D g2d);

}
