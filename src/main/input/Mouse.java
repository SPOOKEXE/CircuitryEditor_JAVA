package main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;

import main.signal.Signal;
import main.signal.SignalListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	// Fields //
	protected int mouseX; // x position
	protected int mouseY; // y position
	protected int scrollZ; // scroll-direction
	
	protected boolean button1Down;
	protected boolean button2Down;
	protected boolean buttonScrollDown;
	
	protected ArrayList<Integer> active_keys_ints;

	public Signal onMouseMove;
	public Signal onMouse1Clicked;
	public Signal onMouse1Down;
	public Signal onMouse1Up;
	public Signal onMouse2Down;
	public Signal onMouse2Up;
	public Signal onMouseScrolled;
	public Signal onMouseScrollDown;
	public Signal onMouseScrollUp;
	
	public Signal onMouseEnterComponent;
	public Signal onMouseLeaveComponent;
	
	// Constructor //
	public Mouse() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.mouseX = 0;
		this.mouseY = 0;
		this.scrollZ = 0;
		
		this.button1Down = false;
		this.button2Down = false;
		this.buttonScrollDown = false;
		
		this.active_keys_ints = new ArrayList<Integer>();
		
		this.onMouseMove = new Signal();
		this.onMouse1Clicked = new Signal();
		this.onMouse1Down = new Signal();
		this.onMouse1Up = new Signal();
		this.onMouse2Down = new Signal();
		this.onMouse2Up = new Signal();
		this.onMouseScrolled = new Signal();
		this.onMouseScrollDown = new Signal();
		this.onMouseScrollUp = new Signal();
		
		this.onMouseEnterComponent = new Signal();
		this.onMouseLeaveComponent = new Signal();
	}
	
	public int getMouseX() {
		return this.mouseX;
	}
	
	public int getMouseY() {
		return this.mouseY;
	}
	
	public int getScrollZ() {
		return this.scrollZ;
	}
	
	public boolean isKeyIntDown(int keyInt) {
		return this.active_keys_ints.contains(keyInt);
	}
	
	public boolean isKeyIntUp(int keyInt) {
		return !this.isKeyIntDown(keyInt);
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		this.scrollZ = event.getWheelRotation();
		
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("Delta", this.scrollZ);
		this.onMouseScrolled.Fire(args);
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		this.mouseX = event.getX();
		this.mouseY = event.getY();
		this.onMouseMove.Fire();
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		this.mouseX = event.getX();
		this.mouseY = event.getY();
		this.onMouseMove.Fire();
	}

	@Override
	public void mousePressed(MouseEvent event) {
//		System.out.println(event.getButton());
		
		this.active_keys_ints.add(event.getButton());
		if (event.getButton() == 1) {
			this.button1Down = true;
			this.onMouse1Down.Fire();
		} else if (event.getButton() == 3) {
			this.button2Down = true;
			this.onMouse2Down.Fire();
		} else if (event.getButton() == 2) {
			this.buttonScrollDown = true;
			this.onMouseScrollDown.Fire();
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
//		System.out.println(event.getButton());
		
		this.active_keys_ints.remove((Object) event.getButton());
		if (event.getButton() == 1) {
			this.button1Down = false;
			this.onMouse1Up.Fire();
		} else if (event.getButton() == 3) {
			this.button2Down = false;
			this.onMouse2Up.Fire();
		} else if (event.getButton() == 2) {
			this.buttonScrollDown = false;
			this.onMouseScrollUp.Fire();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		this.onMouse1Clicked.Fire();
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		this.onMouseEnterComponent.Fire();
	}

	@Override
	public void mouseExited(MouseEvent event) {
		this.onMouseLeaveComponent.Fire();
	}
	
	public void onMouseMove(SignalListener listener) {
		this.onMouseMove.OnEvent(listener);
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
	
	public void onMouseScrollDown(SignalListener listener) {
		this.onMouseScrollDown.OnEvent(listener);
	}
	
	public void onMouseScrollUp(SignalListener listener) {
		this.onMouseScrollUp.OnEvent(listener);
	}
	
	public void onMouseScrolled(SignalListener listener) {
		this.onMouseScrolled.OnEvent(listener);
	}
	
}
