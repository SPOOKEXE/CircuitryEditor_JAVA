package main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import main.enumerations.KeyTypes;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private int mouseX = -1; // x position
	private int mouseY = -1; // y position
	private int mouseB = -1; // mouse button pressed (varies)
	private int scroll = 0; // scroll
	
	public int getX() { return this.mouseX; }
	public int getY() { return this.mouseY; }
	public boolean isScrollingUp() { return this.scroll == -1; }
	public boolean isScrollingDown() { return this.scroll == 1; }
	
	public void resetScroll() { this.scroll = 0; }
	
	public KeyTypes getActiveButton() {
		switch(this.mouseB) {
			case 1:
				return KeyTypes.LeftClick;
			case 2:
				return KeyTypes.ScrollClick;
			case 3:
				return KeyTypes.RightClick;
			case 4:
				return KeyTypes.ForwardPage;
			case 5:
				return KeyTypes.BackPage;
			default:
				return KeyTypes.Unknown;
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent event) { this.scroll = event.getWheelRotation(); }

	@Override
	public void mouseDragged(MouseEvent event) { this.mouseX = event.getX(); this.mouseY = event.getY(); }

	@Override
	public void mouseMoved(MouseEvent event) { this.mouseX = event.getX(); this.mouseY = event.getY(); }

	@Override
	public void mousePressed(MouseEvent event) { this.mouseB = event.getButton(); }

	@Override
	public void mouseReleased(MouseEvent event) { this.mouseB = -1; }
	
	@Override
	public void mouseClicked(MouseEvent event) { }

	@Override
	public void mouseEntered(MouseEvent event) { }

	@Override
	public void mouseExited(MouseEvent event) { }
	
}
