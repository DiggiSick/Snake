/**
 * Represents snakecharacter.
 * 
 * @author Nikolas Wach
 * @author Mirco Umbach
 * @version 1.0
 */
public class Snake extends GameCharacter {

	public Snake() {
		super(0, 0, Tools.colourToRed("∫"));
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