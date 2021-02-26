/**
 * @author Nikolas
 * @author Mirco Umbach
 *
 * construct for gamecharacters
 */
public abstract class GameCharacter {

//	private HashMap<Integer, Integer> location = new HashMap<Integer, Integer>();
	private String symbol;
	private int locationX, locationY;

	public GameCharacter(int x, int y, String c) {
		setLocation(x, y);
		SetSymbol(c);

	}

	public void setLocation(int x, int y) {
		locationX = x;
		locationY = y;
//		location.put(x, y);
	}

	public void SetSymbol(String c) {
		symbol = c;
	}

	public String GetSymbol() {
		return symbol;
	}

	public void setLocationX(int x) {
		locationX = x;
	}

	public void setLocationY(int y) {
		locationY = y;
	}

	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}
}
