package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import main.signal.Signal;
import main.signal.SignalListener;

public class Keyboard implements KeyListener {

	// Fields //
	private boolean[] keys = new boolean[66568];

	public Signal onInputBegin;
	public Signal onInputEnded;
	
	// Constructors //
	public Keyboard() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.onInputBegin = new Signal();
		this.onInputEnded = new Signal();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[ e.getKeyCode() ] = true;
		
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("KeyCode", e.getKeyCode());
		this.onInputBegin.Fire(args);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[ e.getKeyCode() ] = false;
		
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("KeyCode", e.getKeyCode());
		this.onInputEnded.Fire(args);
	}
	
	public void onInputBegin(SignalListener listener) {
		this.onInputBegin.OnEvent(listener);
	}
	
	public void onInputEnded(SignalListener listener) {
		this.onInputEnded.OnEvent(listener);
	}
	
}
