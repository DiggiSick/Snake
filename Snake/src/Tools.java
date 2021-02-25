/* 
 * 
 * Helper class for some functions 
 * */

import java.io.IOException;


public class Tools {

	private static int getDifference(int pointA, int pointB) {
		int difference;
		if (pointA > pointB) {
			difference = pointA - pointB;
		} else {
			difference = pointB - pointA;
		}
		return difference;

	}

	public static boolean checkGap(GameCharacter gc1, GameCharacter gc2, int gap) {

		int gapX = getDifference(gc1.getLocationX(), gc2.getLocationX());
//		System.out.println(gapX);
		int gapY = getDifference(gc1.getLocationY(), gc2.getLocationY());
//		System.out.println(gapY);
		if (gapX > gap && gapY > gap) {
			return true;
		} else {
			return false;
		}
	}
	

	public static void clearScreen() {
		// Clears Screen in java
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033\143");
			}
		} catch (IOException | InterruptedException ex) {
		}
	}
}
