/**
 * 
 */

/**
 * @author Nikolas
 *
 */
public abstract class GameCharacter {

//	private HashMap<Integer, Integer> location = new HashMap<Integer, Integer>();
	private char symbol;
	private int locationX, locationY;

	public GameCharacter(int x, int y, char c) {
		setLocation(x, y);
		SetSymbol(c);

	}

	public void setLocation(int x, int y) {
		locationX = x;
		locationY = y;
//		location.put(x, y);
	}

	public void SetSymbol(char c) {
		symbol = c;
	}

	public int[] getLocation() {
		int[] coordinates = { locationX, locationY };
		return coordinates;
	}

	public char GetSymbol() {
		return symbol;
	}

	public void setLocationX(int x) {
		locationX = x;
	}

	public void setLocationY(int y) {
		locationY = y;
	}

}
