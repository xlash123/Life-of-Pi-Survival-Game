package xlash.pi.config;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles the logic of the configuration file and used to save and read from it.
 * @author Noah
 *
 */
public class ConfigUtil {
	
	public static final String CONFIG_DIRECTORY = System.getProperty("user.home") + "\\Life of Pi Config\\";
	public static final String CONFIG_NAME = "config.cfg";
	public static final ConfigData DEFAULT = new ConfigData(new Dimension(800, 600), false, new Point(0,0));
	
	public static ConfigData loadedConfig = DEFAULT;
	
	/**
	 * Saves the passed configuration data to the config file. Done on close.
	 * @param data Configuration data
	 */
	public static void saveConfigFile(ConfigData data){
		System.out.println("Saving configuration data...");
		File file = getConfigFile();
		try {
			PrintStream print = new PrintStream(new FileOutputStream(file));
			
			print.println("Window Size=" + data.windowSize.width + "," + data.windowSize.height);
			print.println("Fullscreen=" + data.isFullscreen);
			print.println("Screen Location="+data.screenLocation.x+","+data.screenLocation.y);
			
			print.close();
			System.out.println("Configuration save complete!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Saves the currently loaded configuration data to the config file. Done on close.
	 */
	public static void saveConfigFile(){
		saveConfigFile(loadedConfig);
	}
	
	/**
	 * Reads the config file and returns the configuration data, as well as
	 * storing it in ConfigData.loadedConfig. Done on startup.
	 * @return
	 */
	public static ConfigData readConfigFile(){
		File file = getConfigFile();
		ConfigData data = new ConfigData();
		try {
			Scanner scanner = new Scanner(file);
			
			String[] windowSizeValues = getConfigValue(scanner.nextLine()).split(",");
			data.windowSize = new Dimension(Integer.parseInt(windowSizeValues[0]),Integer.parseInt(windowSizeValues[1]));
			
			data.isFullscreen = Boolean.parseBoolean(getConfigValue(scanner.nextLine()));
			
			String[] screenLocationValues = getConfigValue(scanner.nextLine()).split(",");
			data.screenLocation = new Point(Integer.parseInt(screenLocationValues[0]),Integer.parseInt(screenLocationValues[1]));
			
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			data = null;
		} catch (NumberFormatException e){
			System.err.println("User entered non-numerical values. Using default config.");
			data = null;
		}
		loadedConfig = data == null ? DEFAULT : data;
		return loadedConfig;
	}
	
	private static String getConfigValue(String line){
		return line.substring(line.indexOf("=") + 1);
	}
	
	public static File getConfigFile(){
		File file = new File(CONFIG_DIRECTORY+CONFIG_NAME);
		if(!file.exists()){
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
				saveConfigFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
}
