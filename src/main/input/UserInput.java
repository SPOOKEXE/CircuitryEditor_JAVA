package main.input;

public class UserInput {

	public static Mouse mouse;
	public static Keyboard keyboard;
	
	public UserInput() {
		mouse = new Mouse();
		keyboard = new Keyboard();
	}
	
	public UserInput( Mouse nmouse, Keyboard nkeyboard ) {
		mouse = nmouse;
		keyboard = nkeyboard;
	}
	
	/*
	 * TODO: event handling for
	 * - on mouse changed
	 * - on mouse left click
	 * - on mouse right click
	 * - on keyboard key press
	 * - on keyboard key release
	 */
	
}
