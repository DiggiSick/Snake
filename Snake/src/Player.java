/**
 * Represents the playercharacter.
 * 
 * @author Nikolas Wach
 * @author Mirco Umbach
 * @version 1.0
 */


public class Player extends GameCharacter {

	public Player() {
		super(0, 0, Tools.colourToCyan("♿"));
	}

	public void moveUP(){
		setLocationX(getLocationX() - 1);
	}

	public void moveDown(){
		setLocationX(getLocationX() + 1);
	}

	public void moveLeft(){
		setLocationY(getLocationY() - 1);
	}

	public void moveRight(){
		setLocationY(getLocationY() +1 );
	}


}
