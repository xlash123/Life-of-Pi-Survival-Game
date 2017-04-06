package xlash.pi.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class InputHandler implements KeyListener, MouseListener{
	
	private static boolean[] prevMouse = new boolean[4];
	private static boolean[] prevKey = new boolean[256];
	
	private static boolean[] mouse = new boolean[4];
	private static boolean[] key = new boolean[256];
	
	private static ArrayList<Integer> mouseStorage = new ArrayList<Integer>();
	private static ArrayList<Integer> keyStorage = new ArrayList<Integer>();
	
	public static boolean isMouseButtonPressed(int button){
		return mouse[button];
	}
	
	public static boolean isKeyPressed(int key){
		return InputHandler.key[key];
	}
	
	public static boolean didMouseClick(int button){
		return mouse[button] && !prevMouse[button];
	}
	
	public static boolean didMouseUnclick(int button){
		return prevMouse[button] && !mouse[button];
	}
	
	public static void tick(){
		for(int button : mouseStorage){
			if(button > 0){
				mouse[button] = true;
			}else mouse[-button] = false;
		}
		for(int button : keyStorage){
			if(button > 0){
				key[button] = true;
			}else key[-button] = false;
		}
		mouseStorage.clear();
		keyStorage.clear();
	}
	
	public static void untick(){
		prevMouse = mouse.clone();
		prevKey = key.clone();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int lastIndex = mouseStorage.lastIndexOf(arg0.getButton());
		if(lastIndex==-1){
			mouseStorage.add(arg0.getButton());
			return;
		}
		if(mouseStorage.get(mouseStorage.lastIndexOf(arg0.getButton())) < 0){
			mouseStorage.add(arg0.getButton());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		int lastIndex = mouseStorage.lastIndexOf(arg0.getButton());
		if(lastIndex==-1){
			mouseStorage.add(-arg0.getButton());
			return;
		}
		if(lastIndex > -1 && mouseStorage.get(mouseStorage.lastIndexOf(arg0.getButton())) > 0){
			mouseStorage.add(-arg0.getButton());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int lastIndex = keyStorage.lastIndexOf(e.getKeyCode());
		if(lastIndex==-1){
			keyStorage.add(e.getKeyCode());
			return;
		}
		if(lastIndex > -1 && keyStorage.get(keyStorage.lastIndexOf(e.getKeyCode())) < 0){
			keyStorage.add(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int lastIndex = keyStorage.lastIndexOf(e.getKeyCode());
		if(lastIndex==-1){
			keyStorage.add(-e.getKeyCode());
			return;
		}
		if(lastIndex > -1 && keyStorage.get(keyStorage.lastIndexOf(e.getKeyCode())) > 0){
			keyStorage.add(-e.getKeyCode());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
