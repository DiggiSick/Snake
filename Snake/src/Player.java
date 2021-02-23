/**
 * 
 */

/**
 * @author Nikolas
 *
 */
public class Player extends GameCharacter {

	public Player(int x, int y) {
		super(x, y, 'P');
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
			setLocationX(--tmp);
			break;
		case 'd':
			tmp = getLocationY();
			setLocationX(++tmp);
			break;

		default:
			break;
		}
	}
}
