package main.display;

import java.awt.Graphics;

import javax.swing.JFrame;

import main.math.Vector2;

public interface IDisplay {
	void init( );
	void update( );
	
	void setSize( Vector2 size );
	void setTitle( String title );
	void setFPS( int FPS );
	void setVisible( boolean visible );
	void setResizable( boolean resizable );
	
	JFrame getFrame();
	int getWidth( );
	int getHeight( );
	Vector2 getWindowSize();
	String getTitle();
	int getFPS();
	boolean isVisible( );
	boolean isResizable();
	String toString( );
	Graphics getGraphics();
	Graphics2D getGraphics2D();
}
