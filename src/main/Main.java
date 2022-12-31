package main;

import java.util.ArrayList;

import main.widgets.BaseWidget;
import main.widgets.EditorWidget;
import main.widgets.TestWidget;

public class Main {
	
	protected static ArrayList<BaseWidget> widgets;

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
		
		// Update the widgets and draw continuously //
		// TODO: change to FPS-based not timer-based
		while (true) {
			Main.update();
			Main.draw();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
