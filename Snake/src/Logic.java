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

	public static boolean snakebite = false;
	// random object for automate Game character setting
	Random random = new Random();
	private int count = 0;

	// set the size of the play field
	private int row = 15;
	private int col = 25;
	// Creating play field here ##### Change size if you want

	ArrayList<GameCharacter> characters = new ArrayList<>();
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

	private void addCharsToList() {
		characters.add(new Door());
		characters.add(new Snake());
		characters.add(new Player());

		int traps = random.nextInt(5) + 3;
//		System.out.println(traps);
		for (int i = 0; i < traps; i++) {

			characters.add(new Trapdoor());

		}

	}

	public void printChars() {
		for (GameCharacter gameCharacter : characters) {
			for (GameCharacter gameCharacter2 : characters) {
				if (gameCharacter != gameCharacter2) {
					System.out
							.println(gameCharacter.getClass().getName() + " : " + gameCharacter2.getClass().getName());
				}
			}
		}
	}

	public boolean checkPositions() {
		int gap;

		for (GameCharacter gameCharacter : characters) {
			for (GameCharacter gameCharacter2 : characters) {
				if (gameCharacter != gameCharacter2) {
					gap = gameCharacter.getClass().getName() == "Trapdoor"
							|| gameCharacter2.getClass().getName() == "Trapdoor" ? 0 : 2;
//					System.out
//							.println(gameCharacter.getClass().getName() + " : " + gameCharacter2.getClass().getName());
					if (!Tools.checkGap(gameCharacter, gameCharacter2, gap)) {
						count++;
						return false;
					}
				}
			}
		}
		System.out.print(count);
		return true;

	}
	// Instruction Text for how the game should be played
//	private String welcomeText = "\nWilkommen bei Snake. Um ihre Spielfigur zu bewegen benutzen Sie die Tasten 'W' für hoch, 'S' für runter, 'A' für links und 'D' für rechts.\n"
//			+ "Ziel des Spiels ist es die Tür '#' zu erreichen, bevor die Schlange 'S' Sie als Spieler 'P' erwischt!\n"
//			+ "Wenn Sie nach einer Eingabe gefragt werden, Benutzen Sie die o.g Richtungstasten und bestätigen Sie mit enter um Ihren Zug abzuschließen.";

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

	public void printField() {
		for (int i = 0; i < row; i++) {
			System.out.print("\n");
			for (int j = 0; j < col; j++) {
				char symb = '.';
				for (GameCharacter gameCharacter : characters) {
					if (i == gameCharacter.getLocationX() && j == gameCharacter.getLocationY()) {
						symb = gameCharacter.GetSymbol();
						continue;
					}
				}
				if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
					symb = '#';
				}
				System.out.print(symb);
			}
		}
		System.out.print("\n");
	}

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

	public void setPositions() {

		while (!checkPositions()) {

			for (GameCharacter gameCharacter : characters) {
				gameCharacter.setLocation((1 + random.nextInt(row - 2)), (1 + random.nextInt(col - 2)));
			}

		}

	}

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

	public void createSnake() {
		characters.add(new Snake());
	}

	public void runGame() {
//		Scanner sc = new Scanner(System.in);
		
		int inputKey = 0;
		addCharsToList();
		setPositions();

		printField();
		while (!snakebite) {
			try {
			inputKey =RawConsoleInput.read(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Player p = (Player) characters.get(2);
			p.move(inputKey);
		}
		
	

//		sc.close();
	}

	public static void clearConsole() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033\143");
			}
		} catch (IOException | InterruptedException ex) {
		}
	}
}