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

//		if (Tools.checkGap(this.getLocationX(), x, 0)) {
//
//		} else if (x > getLocationX()) {
//			setLocationX(getLocationX() + 1);
//		} else if (x < getLocationX()) {
//			setLocationX(getLocationX() - 1);
//		} else if (y > getLocationY()) {
//			setLocationY(getLocationY() + 1);
//		} else if (y <= getLocationY()) {
//			setLocationY(getLocationY() - 1);
//		}

	}
}