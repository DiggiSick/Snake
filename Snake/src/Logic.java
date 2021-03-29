import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;


/**
 * Represents the gamelogic.
 * 
 * @author Nikolas Wach
 * @author Mirco Umbach
 * @version 1.0
 */
public class Logic {

	// random object to automate Game character setting
	Random random = new Random();

	// set the size of the play field
	private final int PLAYFIELD_HEIGHT = 10;
	private final int PLAYFIELD_WIDTH = 25;
	private final int LEVEL_CAP = 10;
	// level variable which increase the number of Trapdoors
	private int level = 1;

	// stores all Gamecharacters
	ArrayList<GameCharacter> characters = new ArrayList<>();

	private void generateCharacters() {
		characters.clear();
		characters.add(new Door());
		characters.add(new Snake());
		characters.add(new Player());

		int traps = level * 2;
		for (int i = 0; i < traps; i++) {
			characters.add(new TrapDoor());
		}
	}

	private void createSnake() {
		Snake snake = new Snake();
		characters.add(snake);
		snake.setLocation((1 + random.nextInt(PLAYFIELD_HEIGHT - 2)), (1 + random.nextInt(PLAYFIELD_WIDTH - 2)));
	}

	public boolean checkPositions() {
		int gap;

		for (GameCharacter gameCharacter : characters) {
			for (GameCharacter gameCharacter2 : characters) {
				if (gameCharacter != gameCharacter2) {
					gap = gameCharacter.getClass().getName() == "TrapDoor"
							|| gameCharacter2.getClass().getName() == "TrapDoor" ? 0 : 3;

					if (!Tools.checkGap(gameCharacter, gameCharacter2, gap)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// autoset characters
	public void setPositions() {
		int generationAttempts = 0;
		while (!checkPositions()) {
			if (generationAttempts > 10000000) {// Stop Game if no valid Level is found after 10 Mio Attempts
				System.out.println("Level generation failed! To many Attempts failed.");
				System.exit(1);
			}
			generationAttempts++;
			for (GameCharacter gameCharacter : characters) {
				gameCharacter.setLocation((1 + random.nextInt(PLAYFIELD_HEIGHT - 2)),
						(1 + random.nextInt(PLAYFIELD_WIDTH - 2)));
			}

		}
	}

	// print the hole game into console
	public void printField() {
		PrintStream ps;
		StringBuilder field = new StringBuilder();
		Tools.clearScreen();
		field.append("\nLevel: " + level);
		// System.out.print("\nLevel: " + level);
		for (int i = 0; i < PLAYFIELD_HEIGHT; i++) {
			field.append("\n");
			for (int j = 0; j < PLAYFIELD_WIDTH; j++) {
				String symbol = Tools.colourBackgroundToBlack(" ");
				for (GameCharacter gameCharacter : characters) {
					if (i == gameCharacter.getLocationX() && j == gameCharacter.getLocationY()
							&& gameCharacter.getClass().getName() != "TrapDoor") {
						symbol = Tools.colourBackgroundToBlack(gameCharacter.GetSymbol());
						continue;
					}
				}
				if (i == 0 || j == 0 || i == PLAYFIELD_HEIGHT - 1 || j == PLAYFIELD_WIDTH - 1) {
					symbol = Tools.colourBackgroundToGrey(" ");
				}
				field.append(symbol);
			}
		}
		field.append("\n");
		
		try {
			ps = new PrintStream(System.out, true, "UTF-8");
			ps.print(field.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	private void initGame() {
		generateCharacters();
		setPositions();
	}
	
	public void runGame() {

		int snakeSkipRound = 0; // store how much moves the snake skipped
		int inputKey = 0;	// stores userinput as ASCII-Code
	    initGame();

		while (level <= LEVEL_CAP) {
			Player player = (Player)characters.get(2);
			Door door = (Door)characters.get(0);
			printField();

			try {
				inputKey = RawConsoleInput.read(true);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
			switch (inputKey){

				case 119: /// ASCII CODE w
				if(player.getLocationX() > 1){
					player.moveUp();
				}else{
					// do nothing
				}								
				break;

			case 115: /// ASCII CODE s
				if(player.getLocationX() < PLAYFIELD_HEIGHT - 2){
					player.moveDown();	
				}else{
					// do nothing
				}						
				break;

			case 97: /// ASCII CODE a
			if(player.getLocationY() > 1){
				player.moveLeft();	
			}else{
				// do nothing
			}						
				break;

			case 100: /// ASCII CODE d
			if(player.getLocationY() < PLAYFIELD_WIDTH - 2){
				player.moveRight();	
			}else{
				//do nothing
			}						
				break;

			case 27: /// ASCII CODE ESC
				System.exit(0);
			default:
				System.out.println("Unzulässige Eingabe! Bitte verwenden Sie nur W A S D um die Spielfigur zu bewegen oder ESC um das Spiel zu verlassen");
				break;
			}
							
            if (snakeSkipRound == 0) {
                for (GameCharacter snakes : characters) {
                    if (snakes.getClass().getName() == "Snake") {
                        Snake snake = (Snake)snakes;
                        snake.move(player);                       
                        if(Tools.checkSnakebite(player, snake)){
							level = 1;
							initGame();
							break;
						}
                    }
                }
                snakeSkipRound = 1;
            } else {
                snakeSkipRound--;
            }
									
			int i = 0; // count index for ArrayList
			// check whether player hits a trap to create a new snke and remove the trap
			for (GameCharacter character : characters) {
				if (character.getClass().getName() == "TrapDoor") {					
					TrapDoor trap = (TrapDoor) character;
					if( trap.getLocationX() == player.getLocationX() && trap.getLocationY() == player.getLocationY()) {
						createSnake();
						characters.remove(i); // remove trapdoor
						break;
					}
				}
				i++;
			}
			//check wether Player hits the door
			if( door.getLocationX() == player.getLocationX() && door.getLocationY() == player.getLocationY()) {
				level++;
				initGame();
			}else{
				//do nothing
			}									
		}

		System.out.println("Game Over!");
		System.exit(0);
	}


}
