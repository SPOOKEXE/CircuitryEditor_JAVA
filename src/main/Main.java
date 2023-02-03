package main;

import java.util.ArrayList;

import main.widgets.BaseWidget;
import main.widgets.EditorWidget;
import main.widgets.TestWidget;

public class Main {
	protected static ArrayList<BaseWidget> widgets;
	
	protected static int FPS = 60;
	
	private static double ns = 10e8 / FPS;
	private static double second = 1e3;
	
	public static void setFPS(int fps) {
		FPS = fps;
		ns = 10e8 / fps;
	}
	
	public static void update() {
		for (BaseWidget widget : widgets) {
			widget.Update();
		}
	}
	
	public static void draw() {
		for (BaseWidget widget : widgets) {
			widget.Draw();
		}
	}
	
	public static void initializeAndStartAll() {
		System.out.println("Initialize and Starting all widgets.");
		for (BaseWidget widget : widgets) {
			widget.Init();
		}
		for (BaseWidget widget : widgets) {
			widget.Start();
		}
	}
	
	public static void main(String[] args) {
		// Create all the widgets //
		TestWidget testWidget = new TestWidget();
		testWidget.getViewportCanvas().setVisible(true);
		testWidget.setWindowSize(1280, 720);
		
		EditorWidget editorWidget = new EditorWidget();
		editorWidget.getViewportCanvas().setVisible(true);
		editorWidget.setWindowSize(1280, 720);
		
		// Widget ArrayList //
		widgets = new ArrayList<BaseWidget>();
		widgets.add(testWidget);
		widgets.add(editorWidget);
		
		// Initialize and start all widgets //
		Main.initializeAndStartAll();
		
		// Immediately update and draw once //
		Main.update();
		Main.draw();
		
		// Speed-Based Update //
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		int frames = 0;
		double delta = 0;
		
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {
				delta--;
				Main.update();
				Main.draw();
				frames++;
			}
			
			if (System.currentTimeMillis() - timer > second) {
				timer += second;
				// show FPS counter on title
				for (BaseWidget widget : widgets) {
					widget.getViewportCanvas().getFrame().setTitle(frames + " fps");
				}
				frames = 0;
			}
		}
		
		
		// Update the widgets and draw on a timer //
		/*
		while (true) {
			Main.update();
			Main.draw();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				break;
			}
		}*/
	}

}
