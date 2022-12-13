package main.math;

public class UDim2 {

	// Fields //
	public float scale_x;
	public int offset_x;
	public float scale_y;
	public int offset_y;
	
	// Constructor //
	public UDim2() { }
	
	public UDim2(double scale_x, int offset_x, double scale_y, int offset_y) {
		this.scale_x = (float) scale_x;
		this.offset_x = offset_x;
		this.scale_y = (float) scale_y;
		this.offset_y = offset_y;
	}
	
	public UDim2(float scale_x, int offset_x, float scale_y, int offset_y) {
		this.scale_x = scale_x;
		this.offset_x = offset_x;
		this.scale_y = scale_y;
		this.offset_y = offset_y;
	}
	
	// Class Methods // 
	public UDim2 sub(UDim2 other) {
		return new UDim2(
			this.scale_x - other.scale_x,
			this.offset_x - other.offset_x,
			this.scale_y - other.scale_y,
			this.offset_y - other.offset_y
		);
	}
	
	public UDim2 add(UDim2 other) {
		return new UDim2(
			this.scale_x + other.scale_x,
			this.offset_x + other.offset_x,
			this.scale_y + other.scale_y,
			this.offset_y + other.offset_y
		);
	}
	
	// Static Methods //
	static UDim2 fromScale(float sX, float sY) {
		return new UDim2(sX, 0, sY, 0);
	}

	static UDim2 fromOffset(int oX, int oY) {
		return new UDim2(0, oX, 0, oY);
	}

	@Override
	public String toString() {
		return "UDim2 [scale_x=" + scale_x + ", offset_x=" + offset_x + ", scale_y=" + scale_y + ", offset_y="
				+ offset_y + "]";
	}
}
