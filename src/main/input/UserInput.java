package main.input;

public class UserInput {

	// Fields //
	public Mouse mouse;
	public Keyboard keyboard;
	
	// Constructors //
	public UserInput() {
		this.mouse = new Mouse();
		this.keyboard = new Keyboard();
	}
	
	public UserInput( Mouse nmouse, Keyboard nkeyboard ) {
		this.mouse = nmouse;
		this.keyboard = nkeyboard;
	}
	
	// Class Methods //
	public Mouse getMouse() {
		return this.mouse;
	}
	
	public Keyboard getKeyboard() {
		return this.keyboard;
	}
	
}
