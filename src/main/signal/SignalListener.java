package main.signal;

import java.io.InvalidClassException;
import java.util.HashMap;

public class SignalListener {
	public static final String ANSI_RED = "\u001B[31m";
	
	// Fields //
	
	// Constructors //
	public SignalListener() { }
	
	// Class Methods //
	
	// OVERRIDE //
	public void handle(HashMap<String, Object> args) throws InvalidClassException {
		throw new InvalidClassException("You must override this method to use SignalListeners"); 
	}
	
	// OVERRIDE //
	public void onError(Exception e) {
		System.out.println(ANSI_RED + "Signal Listener Errored;");
		for (StackTraceElement element : e.getStackTrace()) {
			System.out.println(ANSI_RED + element);
		}
	}
	
}
