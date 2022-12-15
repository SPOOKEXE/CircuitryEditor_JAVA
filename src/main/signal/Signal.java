package main.signal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Signal {
	
	// Fields //
	protected ArrayList<SignalListener> listeners;
	protected boolean disconnected;
	
	// Constructors //
	public Signal() { }
	public Signal(SignalListener...listeners) { this.OnEvent(listeners); }
	public Signal(ArrayList<SignalListener> listeners) { this.OnEvent(listeners); }
	
	// Class Methods //
	public void Fire(HashMap<String, Object> args) {
		if (disconnected) {
			return;
		}
		for (SignalListener listener : this.listeners) {
			try {
				listener.handle(args);
			} catch (Exception e1) {
				try {
					listener.onError(e1);
				} catch (Exception e2) {
					System.out.println("SignalListener onError errored.");
					e2.printStackTrace();
				}
				
			}
		}
	}
	
	public void OnEvent(SignalListener...listeners) {
		if (this.disconnected) {
			return;
		}
		this.listeners.addAll( Arrays.asList(listeners) );
	}
	
	public void OnEvent(ArrayList<SignalListener> listeners) {
		if (this.disconnected) {
			return;
		}
		this.listeners.addAll( listeners );
	}
	
	public void Disconnect() {
		if (this.disconnected) {
			return;
		}
		this.disconnected = true;
		this.listeners = null;
	}
	
}
