package main.math;

public class Color3 {

	// Static Private //
	private static float safeDiv0(float a, float b) {
		if (a == 0 || b == 0) {
			return 0;
		}
		return (float) (a / b);
	}
	
	// Fields //
	protected float r, g, b; // 0->1, dark->bright

	// Constructors //
	public Color3() { }
	
	public Color3(float red, float green, float blue) {
		this.r = red;
		this.g = green;
		this.b = blue;
	}
	
	// Class Methods //
	public Color3 add(Color3 other) {
		return new Color3(this.r + other.r, this.g + other.g, this.b + other.b);
	}
	
	public Color3 sub(Color3 other) {
		return new Color3(this.r - other.r, this.g - other.g, this.b - other.b);
	}
	
	public Color3 mult(Color3 other) {
		return new Color3(this.r * other.r, this.g * other.g, this.b * other.b);
	}
	
	public Color3 mult(float mult) {
		return new Color3(this.r * mult, this.g * mult, this.b * mult);
	}
	
	public Color3 div(Color3 other) {
		return new Color3(
			safeDiv0(this.r, other.r),
			safeDiv0(this.g, other.g),
			safeDiv0(this.b, other.b)
		);
	}
	
	public Color3 div(float div) {
		return new Color3(
			safeDiv0(this.r, div),
			safeDiv0(this.g, div),
			safeDiv0(this.b, div)
		);
	}
	
	public float[] getRGB() {
		return new float[] { this.r, this.g, this.b };
	}
	
	@Override
	public String toString() {
		return "Color3 [r=" + r + ", g=" + g + ", b=" + b + "]";
	}
	
	// Static Class Methods //
	public static Color3 fromRGB(int r, int g, int b) {
		return new Color3(safeDiv0(r, 255), safeDiv0(g, 255), safeDiv0(b, 255) );
	}
	
}
