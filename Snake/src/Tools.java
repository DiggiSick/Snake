/**
 * Helperclass with some funktionalstuff
 * 
 * @author Nikolas Wach
 * @author Mirco Umbach
 * @version 1.0
 */
import java.io.IOException;

public class Tools {

	private static int getDifference(int pointA, int pointB) {

		int difference = pointA > pointB ? pointA - pointB : pointB - pointA;			
		return difference;

	}

	public static boolean checkGap(GameCharacter gc1, GameCharacter gc2, int gap) {

		int gapX = getDifference(gc1.getLocationX(), gc2.getLocationX());
		int gapY = getDifference(gc1.getLocationY(), gc2.getLocationY());

		boolean checkGap = gapX >= gap && gapY >= gap ? true : false;		
		return checkGap;
	}

		// define loose condition if the Snake reach player
		public static boolean checkSnakebite(Player player, Snake snake) {

			boolean snakeBite = player.getLocationX() == snake.getLocationX() && player.getLocationY() == snake.getLocationY() ? true : false;		
			return snakeBite;
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

	/*
	set color of input and after that set color back to default
	https://stackoverflow.com/questions/1448858/how-to-color-system-out-println-output
	https://en.wikipedia.org/wiki/ANSI_escape_code#Escape_sequences
	*/

	public static String colourToRed( String input){
		return (char)27 + "[31m" + input + (char)27 + "[0m";
	}
	public static String colourToCyan( String input){
		return (char)27 + "[36m" + input + (char)27 + "[0m";
	}
	public static String colourToGreen( String input){
		return (char)27 + "[32m" + input + (char)27 + "[0m";
	}
	public static String colourToGrey( String input){
		return (char)27 + "[90m" + input + (char)27 + "[0m";
	}
	public static String colourToYellow( String input){
		return (char)27 + "[93m" + input + (char)27 + "[0m";
	}
	
}
