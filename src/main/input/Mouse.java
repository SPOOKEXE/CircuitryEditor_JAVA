package main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

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
	public Signal onMouse1Down;
	public Signal onMouse1Up;
	public Signal onMouse2Down;
	public Signal onMouse2Up;
	public Signal onMouseScroll;
	public Signal onMouseScrollDown;
	public Signal onMouseScrollUp;
	
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
		this.onMouse1Down = new Signal();
		this.onMouse1Up = new Signal();
		this.onMouse2Down = new Signal();
		this.onMouse2Up = new Signal();
		this.onMouseScroll = new Signal();
		this.onMouseScrollDown = new Signal();
		this.onMouseScrollUp = new Signal();
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
		this.onMouseScroll.Fire();
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
		System.out.println(event.getButton());
		
		this.active_keys_ints.add(event.getButton());
		if (event.getButton() == 1) {
			this.button1Down = true;
			this.onMouse1Down.Fire();
		} else if (event.getButton() == 2) {
			this.button2Down = true;
			this.onMouse2Down.Fire();
		} else if (event.getButton() == 3) {
			this.buttonScrollDown = true;
			this.onMouseScrollDown.Fire();
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		System.out.println(event.getButton());
		
		this.active_keys_ints.remove((Object) event.getButton());
		if (event.getButton() == 1) {
			this.button1Down = false;
			this.onMouse1Up.Fire();
		} else if (event.getButton() == 2) {
			this.button2Down = false;
			this.onMouse2Up.Fire();
		} else if (event.getButton() == 3) {
			this.buttonScrollDown = false;
			this.onMouseScrollUp.Fire();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		System.out.println("click");
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		System.out.println("enter");
	}

	@Override
	public void mouseExited(MouseEvent event) {
		System.out.println("exit");
	}
	
	public void onMouseMove(SignalListener listener) {
		this.onMouseMove.OnEvent(listener);
	}
	
	public void onMouse1Down(SignalListener listener) {
		System.out.println("mouse 1 down");
		this.onMouse1Down.OnEvent(listener);
	}
	
	public void onMouse1Up(SignalListener listener) {
		System.out.println("mouse 1 up");
		this.onMouse1Up.OnEvent(listener);
	}
	
	public void onMouse2Down(SignalListener listener) {
		this.onMouse1Down.OnEvent(listener);
	}
	
	public void onMouse2Up(SignalListener listener) {
		this.onMouse1Up.OnEvent(listener);
	}
	
	public void onMouse2Scroll(SignalListener listener) {
		this.onMouseScroll.OnEvent(listener);
	}
}
