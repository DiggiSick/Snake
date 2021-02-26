public class Snake extends GameCharacter {
	/*
	 * Maybe function: stores location history if we want that the snake expand
	 */
//	public static HashMap<Integer, Integer> history = new HashMap<Integer, Integer>();

	public Snake() {
		super(0, 0, 'S');
	}

	public void move(Player player) {

		if (player.getLocationX() == getLocationX() && player.getLocationY() == getLocationY()) {
			//Logic.snakebite = true;
		} else if (player.getLocationX() > getLocationX()) {
			setLocationX(getLocationX() + 1);
		} else if (player.getLocationX() < getLocationX()) {
			setLocationX(getLocationX() - 1);
		} else if (player.getLocationY() > getLocationY()) {
			setLocationY(getLocationY() + 1);
		} else if (player.getLocationY() < getLocationY()) {
			setLocationY(getLocationY() - 1);
		}
	}
}