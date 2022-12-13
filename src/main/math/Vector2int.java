package main.math;

public class Vector2int {

	// Fields //
	public int x, y;

	// Constructors //
	public Vector2int(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2int( float x, float y ) {
		this.x = (int) x;
		this.y = (int) y;
	}
	
	public Vector2int( double x, double y ) {
		this.x = (int) x;
		this.y = (int) y;
	}
	
	public Vector2int() {
		this.x = this.y = 0;
	}
	
	public Vector2int(Vector2 fVec2) {
		this.x = (int) fVec2.x;
		this.y = (int) fVec2.y;
	}
	
	// Class Methods //
	public Vector2int add( Vector2int other ) {
		return new Vector2int(this.x + other.x, this.y + other.y);
	}
	
	public Vector2int sub( Vector2int other ) {
		return new Vector2int(this.x - other.x, this.y - other.y);
	}
	
	public Vector2int mult( Vector2int other ) {
		return new Vector2int(this.x * other.x, this.y * other.y);
	}
	
	public Vector2int mult( float n ) {
		return new Vector2int(this.x * n, this.y * n);
	}
	
	public Vector2int mult( int n ) {
		return new Vector2int(this.x * n, this.y * n);
	}
	
	
	public Vector2int div( Vector2int other ) {
		return new Vector2int( (this.x / other.x), (this.y / other.y) );
	}
	
	public Vector2int div( float n ) {
		return new Vector2int( (this.x / n), (this.y / n) );
	}
	
	public float dist( Vector2int other ) {
		return this.sub(other).magnitude();
	}
	
	public float magnitude() {
		if (this.x == this.y && this.y == 0) {
			return 0;
		}
		return (float) Math.sqrt(this.dot(this));
	}
	
	public Vector2int unit() {
		float mag = this.magnitude();
		return new Vector2int( this.x / mag, this.y / mag);
	}
	
	public float dot( Vector2int v2 ) {
		return (this.x * v2.x) + (this.y * v2.y);
	}

	@Override
	public String toString() {
		return "Vector2 [x=" + x + ", y=" + y + "]";
	}
	
}
