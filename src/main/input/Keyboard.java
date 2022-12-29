package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import main.signal.Signal;
import main.signal.SignalListener;

public class Keyboard implements KeyListener {

	// Fields //
	private boolean[] keys;
	private HashMap<Integer, String> specialkeyMapping;
	private HashMap<Integer, Boolean> activeKeys;

	public Signal onInputBegin;
	public Signal onInputEnded;
	
	// Constructors //
	public Keyboard() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.keys = new boolean[66568];
		this.activeKeys = new HashMap<Integer, Boolean>();
		
		this.onInputBegin = new Signal();
		this.onInputEnded = new Signal();
		
		this.specialkeyMapping = new HashMap<Integer, String>();
		this.specialkeyMapping.put(27, "ESCAPE");
		this.specialkeyMapping.put(16, "SHIFT");
		this.specialkeyMapping.put(17, "CONTROL");
		this.specialkeyMapping.put(18, "ALT");
		this.specialkeyMapping.put(20, "CAPS_LOCK");
		this.specialkeyMapping.put(524, "WINDOWS_KEY");
	}
	
	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {
		
		// prevents fast-repeat-triggers in events
		if (activeKeys.get((Object) e.getKeyCode()) != null) {
			return;
		}
		activeKeys.put(e.getKeyCode(), true);
		
//		System.out.println(e.getKeyCode());
		
		keys[ e.getKeyCode() ] = true;
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("KeyCode", e.getKeyCode());
		if (specialkeyMapping.get( e.getKeyCode() ) != null) {
			args.put("KeyChar", specialkeyMapping.get( e.getKeyCode() ));
		} else {
			args.put("KeyChar", e.getKeyChar());
		}
		
		this.onInputBegin.Fire(args);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		activeKeys.remove((Object) e.getKeyCode());
		keys[ e.getKeyCode() ] = false;
		
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("KeyCode", e.getKeyCode());
		if (specialkeyMapping.get( e.getKeyCode() ) != null) {
			args.put("KeyChar", specialkeyMapping.get( e.getKeyCode() ));
		} else {
			args.put("KeyChar", e.getKeyChar());
		}
		
		this.onInputEnded.Fire(args);
	}
	
	public void onInputBegin(SignalListener listener) {
		this.onInputBegin.OnEvent(listener);
	}
	
	public void onInputEnded(SignalListener listener) {
		this.onInputEnded.OnEvent(listener);
	}
	
}
