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
	public Signal onInputBegin;
	public Signal onInputChanged;
	public Signal onInputEnded;
	
	// Constructors //
	public GuiEvents() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.onMouseEnter = new Signal();
		this.onMouseMove = new Signal();
		this.onMouseLeave = new Signal();
		
		this.onMouse1Down = new Signal();
		this.onMouse1Up = new Signal();
		this.onMouse2Down = new Signal();
		this.onMouse2Up = new Signal();
		
		this.onInputBegin = new Signal();
		this.onInputChanged = new Signal();
		this.onInputEnded = new Signal();
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
	
	public void onMouse1Down(SignalListener listener) {
		this.onMouse1Down.OnEvent(listener);
	}
	
	public void onMouse1Up(SignalListener listener) {
		this.onMouse1Up.OnEvent(listener);
	}
	
	public void onMouse2Down(SignalListener listener) {
		this.onMouse2Down.OnEvent(listener);
	}
	
	public void onMouse2Up(SignalListener listener) {
		this.onMouse2Up.OnEvent(listener);
	}
	
	public void onInputBegin(SignalListener listener) {
		this.onInputBegin.OnEvent(listener);
	}
	
	public void onInputChanged(SignalListener listener) {
		this.onInputChanged.OnEvent(listener);
	}
	
	public void onInputEnded(SignalListener listener) {
		this.onInputEnded.OnEvent(listener);
	}
}
