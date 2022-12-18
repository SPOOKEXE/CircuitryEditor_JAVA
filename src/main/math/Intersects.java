package main.math;

public class Intersects {

	public static boolean PointInSquare(int pX, int pY, int TLX, int TLY, int BRX, int BRY) {
		return pX > TLX && pX < BRX && pY > TLY && pY < BRY;
	}
	
	public static boolean PointInSquare(Vector2int point, Vector2int topLeft, Vector2int bottomRight) {
		return PointInSquare(point.x, point.y, topLeft.x, topLeft.y, bottomRight.x, bottomRight.y);
	}
	
}
