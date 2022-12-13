package main.display;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import main.math.Vector2int;

public interface IDisplay {
	void init( );
	void update( );
	
	void setSize( Vector2int size );
	void setTitle( String title );
	void setFPS( int FPS );
	void setVisible( boolean visible );
	void setResizable( boolean resizable );
	
	JFrame getFrame();
	int getWidth( );
	int getHeight( );
	Vector2int getWindowSize();
	String getTitle();
	int getFPS();
	boolean isVisible( );
	boolean isResizable();
	String toString( );
	Graphics getJFrameGraphics();
	Graphics2D getJFrameGraphics2D();
}
