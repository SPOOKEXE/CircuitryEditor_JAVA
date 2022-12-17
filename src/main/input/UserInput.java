package main.input;

import javax.swing.JFrame;

public class UserInput {

	// Fields //
	protected Mouse mouse;
	protected Keyboard keyboard;
	
	// Constructors //
	public UserInput() {
		this.mouse = new Mouse();
		this.keyboard = new Keyboard();
	}
	
	public UserInput( Mouse mouse, Keyboard keyboard ) {
		this.mouse = mouse;
		this.keyboard = keyboard;
	}
	
	// Class Methods //
	public Mouse getMouse() {
		return this.mouse;
	}
	
	public Keyboard getKeyboard() {
		return this.keyboard;
	}
	
	public void setupListeners(JFrame frame) {
		frame.addMouseListener(this.getMouse());
		frame.addMouseMotionListener(this.getMouse());
		frame.addMouseWheelListener(this.getMouse());
		frame.addKeyListener(this.getKeyboard());
	}
	
}
