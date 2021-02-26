import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



/**
 * Represents the gamelogic.
 * 
 * @author Nikolas Wach
 * @author www.LoweWriter.com
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
			characters.add(new Trapdoor());
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
					gap = gameCharacter.getClass().getName() == "Trapdoor"
							|| gameCharacter2.getClass().getName() == "Trapdoor" ? 0 : 2;

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
			if(generationAttempts > 10000000){//Stop Game if no valid Level is found after 10 Mio Attempts
				System.out.println("Level generation failed! To many Attempts failed.");
				System.exit(1);
			}
			generationAttempts++;
			for (GameCharacter gameCharacter : characters) {
				gameCharacter.setLocation((1 + random.nextInt(PLAYFIELD_HEIGHT - 2)), (1 + random.nextInt(PLAYFIELD_WIDTH - 2)));
			}

		}
	}

	// print the hole game into console
	public void printField() {
		Tools.clearScreen();
		System.out.print("\nLevel: " + level);
		for (int i = 0; i < PLAYFIELD_HEIGHT; i++) {
			System.out.print("\n");
			for (int j = 0; j < PLAYFIELD_WIDTH; j++) {
				char symbol = '.';
				for (GameCharacter gameCharacter : characters) {
					if (i == gameCharacter.getLocationX() && j == gameCharacter.getLocationY() && gameCharacter.getClass().getName() != "Trapdoor") {
						symbol = gameCharacter.GetSymbol();
						continue;
					}
				}
				if (i == 0 || j == 0 || i == PLAYFIELD_HEIGHT - 1 || j == PLAYFIELD_WIDTH - 1) {
					symbol = '#';
				}
				System.out.print(symbol);
			}
		}
		System.out.print("\n");
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
					player.moveUP();
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
				if (character.getClass().getName() == "Trapdoor") {					
					Trapdoor trap = (Trapdoor) character;
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

// Creating Game characters ##### Player Snake And Door
//	Door door = new Door((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//	Snake snake = new Snake((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//	Player player = new Player((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//
//	// Creating Traps here to increase difficulty
//	Trapdoor trap1 = new Trapdoor((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//	Trapdoor trap2 = new Trapdoor((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//	Trapdoor trap3 = new Trapdoor((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));

//	GameCharacter[] characters = { door, snake, player, trap1, trap2, trap3 };

//	private boolean winGame() {
//		if (door.getLocation()[0] == player.getLocation()[0] && door.getLocation()[1] == player.getLocation()[1]) {
//			pf.clearLastPosition(player.getLocation());
//			pf.setCharAt(player.getLocation());
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	private boolean lostGame() {
//		if (snake.getLocation()[0] == player.getLocation()[0] && snake.getLocation()[1] == player.getLocation()[1]
//				|| player.getLocation()[0] == trap1.getLocation()[0]
//						&& player.getLocation()[1] == trap1.getLocation()[1]
//				|| player.getLocation()[0] == trap2.getLocation()[0]
//						&& player.getLocation()[1] == trap2.getLocation()[1]
//				|| player.getLocation()[0] == trap3.getLocation()[0]
//						&& player.getLocation()[1] == trap3.getLocation()[1]) {
//			return true;
//		} else {
//			return false;
//		}
//	}

//	public void InitalizeGame() {
//		pf.InitalizeField();
//		setCharacters();
//		pf.PrintField();
//
//	}
//	public void setTraps() {
//		trap1.setLocation((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//		trap2.setLocation((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//		trap3.setLocation((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//	}
//
//	private void setDoor() {
//
//		door.setLocation((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//	}
//
//	private void setSnake() {
//
//		snake.setLocation((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//
//	}
//
//	private void setPlayer() {
//
//		player.setLocation((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
//	}

	/*
	 * Checks the gap between characters and replace coordinates of all characters
	 * until gap which is define in "checkGap" is reached
	 */
//	private boolean validateTrapPositions() {
//		if (trap1.getLocationX() == door.getLocationX() && trap1.getLocationY() == door.getLocationY()
//				|| trap1.getLocationX() == snake.getLocationX() && trap1.getLocationY() == snake.getLocationY()
//				|| trap1.getLocationX() == player.getLocationX() && trap1.getLocationY() == player.getLocationY()
//				|| trap2.getLocationX() == door.getLocationX() && trap2.getLocationY() == door.getLocationY()
//				|| trap2.getLocationX() == snake.getLocationX() && trap2.getLocationY() == snake.getLocationY()
//				|| trap2.getLocationX() == player.getLocationX() && trap2.getLocationY() == player.getLocationY()
//				|| trap3.getLocationX() == door.getLocationX() && trap3.getLocationY() == door.getLocationY()
//				|| trap3.getLocationX() == snake.getLocationX() && trap3.getLocationY() == snake.getLocationY()
//				|| trap3.getLocationX() == player.getLocationX() && trap3.getLocationY() == player.getLocationY()) {
//			return false;
//		} else {
//			return true;
//		}
//	}

//	private boolean validatePositions() {
//
//		if (Tools.checkGap(door.getLocationX(), snake.getLocationX(), door.getLocationY(), snake.getLocationY())
//				&& Tools.checkGap(player.getLocationX(), door.getLocationX(), player.getLocationY(),
//						door.getLocationY())
//				&& Tools.checkGap(player.getLocationX(), snake.getLocationX(), player.getLocationY(),
//						snake.getLocationY())) {
//			return true;
//		} else {
//			return false;
//		}
//
//	}
	// Instruction Text for how the game should be played
//	private String welcomeText = "\nWilkommen bei Snake. Um ihre Spielfigur zu bewegen benutzen Sie die Tasten 'W' f�r hoch, 'S' f�r runter, 'A' f�r links und 'D' f�r rechts.\n"
//			+ "Ziel des Spiels ist es die T�r '#' zu erreichen, bevor die Schlange 'S' Sie als Spieler 'P' erwischt!\n"
//			+ "Wenn Sie nach einer Eingabe gefragt werden, Benutzen Sie die o.g Richtungstasten und best�tigen Sie mit enter um Ihren Zug abzuschlie�en.";

//	public void printField() {
//
//		for (int i = 0; i < row; i++) {
//			System.out.print("\n");
//			for (int j = 0; j < col; j++) {
//				boolean isCharacter = false;
//				for (GameCharacter gameCharacter : characters) {
//					if (i == gameCharacter.getLocationX() && j == gameCharacter.getLocationY()) {
//						System.out.print(gameCharacter.GetSymbol());
//						isCharacter = true;
//						continue;
//					}
//				}
//				if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
//					System.out.print('#');
//				}
//
//				else if (!isCharacter) {
//					System.out.print('.');
//				}
//			}
//		}
//
//	}
	// public void printChars() {
	// 	for (GameCharacter gameCharacter : characters) {
	// 		for (GameCharacter gameCharacter2 : characters) {
	// 			if (gameCharacter != gameCharacter2) {
	// 				System.out
	// 						.println(gameCharacter.getClass().getName() + " : " + gameCharacter2.getClass().getName());
	// 			}
	// 		}
	// 	}
	// }
}