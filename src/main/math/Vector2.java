package main.math;

public class Vector2 {

	// Fields //
	public float x, y;

	// Constructors //
	public Vector2( int x, int y ) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2( float x, float y ) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2( double x, double y ) {
		this.x = (float) x;
		this.y = (float) y;
	}
	
	public Vector2() {
		this.x = this.y = 0;
	}
	
	public Vector2(Vector2int intVec2) {
		this.x = intVec2.x;
		this.y = intVec2.y;
	}
	
	// Class Methods //
	public Vector2 add( Vector2 other ) {
		return new Vector2(this.x + other.x, this.y + other.y);
	}
	
	public Vector2 sub( Vector2 other ) {
		return new Vector2(this.x - other.x, this.y - other.y);
	}
	
	public Vector2 mult( Vector2 other ) {
		return new Vector2(this.x * other.x, this.y * other.y);
	}
	
	public Vector2 mult( float n ) {
		return new Vector2(this.x * n, this.y * n);
	}
	
	public Vector2 div( Vector2 other ) {
		return new Vector2(this.x / other.x, this.y / other.y);
	}
	
	public Vector2 div( float n ) {
		return new Vector2(this.x / n, this.y / n);
	}
	
	public float dist( Vector2 other ) {
		return this.sub(other).magnitude();
	}
	
	public float magnitude() {
		if (this.x == this.y && this.y == 0) {
			return 0;
		}
		return (float) Math.sqrt(this.dot(this));
	}
	
	public Vector2 unit() {
		float mag = this.magnitude();
		return new Vector2(this.x / mag, this.y / mag);
	}
	
	public float dot( Vector2 v2 ) {
		return (this.x * v2.x) + (this.y * v2.y); 
	}

	@Override
	public String toString() {
		return "Vector2 [x=" + x + ", y=" + y + "]";
	}
	
}
