package main.signal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Signal {
	
	// Fields //
	protected ArrayList<SignalListener> listeners;
	protected boolean disconnected;
	
	// Constructors //
	public Signal() {
		this.setDefault();
	}
	
	// Class Methods //
	private void setDefault() {
		this.listeners = new ArrayList<SignalListener>();
		this.disconnected = false;
	}
	
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
	
	public void Fire() {
		this.Fire(null);
	}
	
	public void OnEvent(SignalListener...listeners) {
		if (this.disconnected) {
			return;
		}
		this.listeners.addAll( Arrays.asList(listeners) );
	}
	
	public void Disconnect() {
		if (this.disconnected) {
			return;
		}
		this.disconnected = true;
		this.listeners = null;
	}
	
}
