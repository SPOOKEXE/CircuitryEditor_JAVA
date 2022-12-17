package main.widgets.events;

import java.util.HashMap;

import main.input.UserInput;
import main.signal.Signal;
import main.signal.SignalListener;

public class GuiEvents {

	// Fields //
	
	public Signal onMouseEnter;
	public Signal onMouseMove;
	public Signal onMouseLeave;
	
	public Signal onMouse1Down;
	public Signal onMouse1Up;
	public Signal onMouse2Down;
	public Signal onMouse2Up;
	
	public Signal onMouseScrolled;
	public Signal onMouseScrollDown;
	public Signal onMouseScrollUp;
	
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
		
		this.onMouseScrolled = new Signal();
		this.onMouseScrollDown = new Signal();
		this.onMouseScrollUp = new Signal();
		
		this.onInputBegin = new Signal();
		this.onInputChanged = new Signal();
		this.onInputEnded = new Signal();
	}
	
	public void setupUserInputSignals(UserInput userInput) {
		
		GuiEvents self = this;
		
		userInput.getMouse().onMouse1Down.OnEvent(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				System.out.println("button 1 down");
				self.onMouse1Down.Fire(args);
			}
		});
		
		userInput.getMouse().onMouse1Up.OnEvent(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				self.onMouse1Up.Fire(args);
			}
		});
		
		userInput.getMouse().onMouse2Down.OnEvent(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				self.onMouse2Down.Fire(args);
			}
		});
		
		userInput.getMouse().onMouse2Up.OnEvent(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				self.onMouse2Up.Fire(args);
			}
		});
		
		userInput.getMouse().onMouseScrollDown.OnEvent(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				self.onMouseScrollDown.Fire(args);
			}
		});
		
		userInput.getMouse().onMouseScrollUp.OnEvent(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				self.onMouseScrollUp.Fire(args);
			}
		});
		
		userInput.getMouse().onMouseScrolled.OnEvent(new SignalListener() {
			@Override
			public void handle(HashMap<String, Object> args) {
				self.onMouseScrolled.Fire(args);
			}
		});
		
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
	
	public void onMouseScrolled(SignalListener listener) {
		this.onMouseScrolled.OnEvent(listener);
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
