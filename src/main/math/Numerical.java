package main.math;

public class Numerical {

	public static float safeDiv0(float a, float b) {
		if (a == 0 || b == 0) {
			return 0;
		}
		return a / b;
	}
	
	public static float LerpN(float a, float b, float t) {
		return (float) (a + (b - a) * t);
	}
	
	
	
}
