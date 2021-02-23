/**
 * 
 */

/**
 * @author Nikolas
 *
 */
public class Player extends GameCharacter {

	public Player() {
		super(0, 0, 'P');
	}

	private int tmp;

	public void move(char input) {

		switch (input) {

		case 'w':
			tmp = getLocationX();
			setLocationX(--tmp);
			break;
		case 's':
			tmp = getLocationX();
			setLocationX(++tmp);
			break;
		case 'a':
			tmp = getLocationY();
			setLocationY(--tmp);
			break;
		case 'd':
			tmp = getLocationY();
			setLocationY(++tmp);
			break;

		default:
			break;
		}
	}
}
