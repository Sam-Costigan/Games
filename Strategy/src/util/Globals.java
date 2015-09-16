package util;

public class Globals {
	
	// Size of each block on the map
	public static final int squareSize = 10;
	
	// The default min and max map size
	public static final int minMapSize = 600;
	public static final int maxMapSize = 1000;
	
	// The min and max for strength and speed
	public static final int minStr = 3;
	public static final int maxStr = 6;
	public static final int minSpeed = 1;
	public static final int maxSpeed = 3;
	
	public static int floor(double num, int multipleOf) {
		return (int) Math.floor((num + multipleOf/2) / multipleOf) * multipleOf;
	}
	
	public static int round(double num, int multipleOf) {
		return (int) Math.round((num + multipleOf/2) / multipleOf) * multipleOf;
	}
	
	public static int generateRandomRange(int min, int max) {
		int range = Math.abs(max - min);
		int rand = (int)((Math.random() * range) + min);
		
		return rand;
	}
	
}
