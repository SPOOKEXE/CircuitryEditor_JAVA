package main.math;

public class UDim {

	// Fields //
	public float scale;
	public int offset;
	
	// Constructor //
	public UDim() {
		this.scale = 0;
		this.offset = 0;
	}
	
	public UDim(float scale, int offset) {
		this.scale = 0;
		this.offset = 0;
	}
	
	// Class Methods //
	public UDim sub(UDim other) {
		return new UDim( this.scale - other.scale, this.offset - other.offset );
	}
	
	public UDim add(UDim other) {
		return new UDim( this.scale + other.scale, this.offset + other.offset );
	}

	@Override
	public String toString() {
		return "UDim [scale=" + scale + ", offset=" + offset + "]";
	}
}
