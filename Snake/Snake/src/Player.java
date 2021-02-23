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

	public void move(char input, PlayingField pf) {

		switch (input) {
		case 'w':
			pf.clearLastPosition(getLocation());
			setLocationX(--getLocation()[0]);
			break;
		case 's':
			pf.clearLastPosition(getLocation());
			setLocationX(++getLocation()[0]);
			break;
		case 'a':
			pf.clearLastPosition(getLocation());
			setLocationY(--getLocation()[1]);
			break;
		case 'd':
			pf.clearLastPosition(getLocation());
			setLocationY(++getLocation()[1]);
			break;

		default:
			break;
		}
	}
}
