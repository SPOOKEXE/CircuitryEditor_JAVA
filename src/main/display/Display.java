package main.display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import main.math.Vector2;

public class Display implements IDisplay {

	// Fields //
	protected JFrame frame;
	protected Vector2 displaySize;
	protected String type;
	protected String title;
	protected int FPS;
	protected boolean visible;

	// Constructors //
	public Display() {
		this.setSize(new Vector2(1280, 720));
		this.setTitle("Display");
		this.setVisible(true);
		this.setupFrame();
	}

	public Display(Vector2 size) {
		this.setSize(size);
		this.setTitle("Display");
		this.setVisible(true);
		this.setupFrame();
	}

	// Class Methods //
	private void setupFrame() {
		this.frame = new JFrame();
		this.frame.setTitle(title);
		this.frame.setPreferredSize(new Dimension((int) this.displaySize.x, (int) this.displaySize.y));
		this.frame.pack();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop the program when the window is closed
		this.frame.setLocationRelativeTo(null); // Center to the middle of the screen
		//this.frame.setResizable(false); // Disallow resizing
		this.frame.setVisible(this.visible); // Make it visible
	}
	
	@Override
	public void setSize(Vector2 size) {
		this.displaySize = size;
		if (this.frame != null) {
			Dimension dimSize = new Dimension((int) size.x, (int) size.y);
			this.frame.setPreferredSize(dimSize);
			this.frame.setSize(dimSize);
		}
	}
	
	@Override
	public void setTitle(String title) {
		this.title = title;
		if (this.frame != null) {
			this.frame.setTitle(this.title);
		}
	}
	
	@Override
	public String getTitle() {
		return this.frame.getTitle();
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
		if (this.frame != null) {
			this.frame.setVisible(this.visible);
		}
	}

	@Override
	public int getFPS() {
		return this.FPS;
	}

	@Override
	public void setFPS(int FPS) {
		this.FPS = FPS;
	}

	@Override
	public JFrame getFrame() {
		return this.frame;
	}

	@Override
	public int getWidth() {
		return (int) this.displaySize.x;
	}

	@Override
	public int getHeight() {
		return (int) this.displaySize.y;
	}

	@Override
	public boolean isVisible() {
		return this.visible;
	}

	@Override
	public Vector2 getWindowSize() {
		Dimension wdSize = this.frame.getSize();
		return new Vector2( wdSize.width, wdSize.height );
	}

	@Override
	public void setResizable(boolean resizable) {
		this.frame.setResizable(resizable);
	}

	@Override
	public boolean isResizable() {
		return this.frame.isResizable();
	}
	
	@Override
	public Graphics getJFrameGraphics() {
		return this.frame.getGraphics();
	}
	
	@Override
	public Graphics2D getJFrameGraphics2D() {
		Graphics g = this.getJFrameGraphics();
		if (g != null) {
			return (Graphics2D) g;
		}
		return null;
	}
	
	@Override
	public void init() {
		this.frame.setTitle(this.title + " | " + this.FPS + " fps");
	}

	@Override
	public void update() {
		this.frame.setTitle(this.title + " | " + this.FPS + " fps");
	}
	
	@Override
	public String toString() {
		String hashString = Integer.toHexString(this.hashCode());
		return String.format("%s | %s" + " | " + "Type: %s", this.type, hashString, this.type);
	}
}
