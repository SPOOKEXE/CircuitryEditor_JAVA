package main.widgets.events;

import main.signal.Signal;
import main.signal.SignalListener;

public class GuiEvents {

	// Fields //
	
	// TODO: implement in BaseWidget
	public Signal onMouseEnter;
	public Signal onMouseMove;
	public Signal onMouseLeave;
	
	// TODO: implement in BaseWidget
	public Signal onMouse1Down;
	public Signal onMouse1Up;
	public Signal onMouse2Down;
	public Signal onMouse2Up;
	
	// TODO: implement in BaseWidget
	public Signal onInputDown;
	public Signal onInputChanged;
	public Signal onInputUp;
	
	// Constructors //
	public GuiEvents() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.onMouseEnter = new Signal();
		this.onMouseMove = new Signal();
		this.onMouseLeave = new Signal();
	}
	
	public void onMouseEnter(SignalListener listener) {
		this.onMouseEnter.OnEvent(listener);
	}
	
	public void onMouseMove(SignalListener listener) {
		this.onMouseMove.OnEvent(listener);
	}
	
	public void onMouseLeave(SignalListener listener) {
		this.onMouseLeave.OnEvent(listener);
	}
}
