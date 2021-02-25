public class Snake extends GameCharacter {
	/*
	 * Maybe function: stores location history if we want that the snake expand
	 */
//	public static HashMap<Integer, Integer> history = new HashMap<Integer, Integer>();

	public Snake() {
		super(0, 0, 'S');
	}

	public void move(Player playerLocation) {

		int x = playerLocation.getLocationX();
		int y = playerLocation.getLocationY();

		if (x == getLocationX() && y == getLocationY()) {
			//Logic.snakebite = true;
		} else if (x > getLocationX()) {
			setLocationX(getLocationX() + 1);
		} else if (x < getLocationX()) {
			setLocationX(getLocationX() - 1);
		} else if (y > getLocationY()) {
			setLocationY(getLocationY() + 1);
		} else if (y < getLocationY()) {
			setLocationY(getLocationY() - 1);
		}

	}
	public boolean checkSnakebite(Player playerLocation) {
		int x = playerLocation.getLocationX();
		int y = playerLocation.getLocationY();
		if (x == getLocationX() && y == getLocationY()) {
			return true;
		}else{
			return false;
		}
	}
}