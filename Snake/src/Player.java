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

	public void move(int input) {

		
		switch (input) {

		case 119: /// ASCII CODE w
			tmp = getLocationX();
			if(tmp >= Logic.PLAYFIELD_HEIGHT +1){
				setLocationX(--tmp);
			}else{
				//do nothing	
			}							
			break;
		case 115: /// ASCII CODE s
			tmp = getLocationX();
			if( tmp <= Logic.PLAYFIELD_HEIGHT -1){
				setLocationX(++tmp);
			}else{
				//do nothing
			}			
			break;
		case 97: /// ASCII CODE a
			tmp = getLocationY();
			if(tmp >= Logic.PLAYFIELD_WIDTH +1){
				setLocationY(--tmp);
			}else{
				//do nothing
			}			
			break;
		case 100: /// ASCII CODE d
			tmp = getLocationY();
			if(tmp <= Logic.PLAYFIELD_WIDTH -1){
				setLocationY(++tmp);
			}else{
				//do nothing
			}			
			break;
		case 27: /// ASCII CODE ESC
			System.exit(0);
		default:
			break;
		}
	}


}
