import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the gamelogic.
 * 
 * @author Nikolas Wach
 * @author www.LoweWriter.com
 * @version 1.0
 */
public class Logic {

	// random object for automate Game character setting
	Random random = new Random();

	// Creating play field here ##### Change size if you want
	PlayingField pf = new PlayingField(20, 10);

	// Creating Game characters ##### Player Snake And Door
	Door door = new Door((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));
	Snake snake = new Snake((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));
	Player player = new Player((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));

	// Creating Traps here to increase difficulty
	Trapdoor trap1 = new Trapdoor((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));
	Trapdoor trap2 = new Trapdoor((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));
	Trapdoor trap3 = new Trapdoor((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));

	GameCharacter[] list = new GameCharacter[];

	// Instruction Text for how the game should be played
//	private String welcomeText = "\nWilkommen bei Snake. Um ihre Spielfigur zu bewegen benutzen Sie die Tasten 'W' für hoch, 'S' für runter, 'A' für links und 'D' für rechts.\n"
//			+ "Ziel des Spiels ist es die Tür '#' zu erreichen, bevor die Schlange 'S' Sie als Spieler 'P' erwischt!\n"
//			+ "Wenn Sie nach einer Eingabe gefragt werden, Benutzen Sie die o.g Richtungstasten und bestätigen Sie mit enter um Ihren Zug abzuschließen.";

	public void setTraps() {
		trap1.setLocation((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));
		trap2.setLocation((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));
		trap3.setLocation((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));
	}

	private void setDoor() {

		door.setLocation((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));
	}

	private void setSnake() {

		snake.setLocation((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));

	}

	private void setPlayer() {

		player.setLocation((1 + random.nextInt(pf.GetRows() - 2)), (1 + random.nextInt(pf.GetCols() - 2)));
	}

	/*
	 * Checks the gap between characters and replace coordinates of all characters
	 * until gap which is define in "checkGap" is reached
	 */
	private boolean validateTrapPositions() {
		if (pf.getCharAt(trap1.getLocation()) != '.' && pf.getCharAt(trap2.getLocation()) != '.'
				&& pf.getCharAt(trap3.getLocation()) != '.') {
			return false;
		} else {
			return true;
		}
	}

	private boolean validatePositions() {

		if (Tools.checkGap(door.getLocation(), snake.getLocation())
				&& Tools.checkGap(player.getLocation(), door.getLocation())
				&& Tools.checkGap(player.getLocation(), snake.getLocation())) {
			return true;
		} else {
			return false;
		}

	}

	private void setCharacters() {

		if (validatePositions() && validateTrapPositions()) {

			pf.SetFieldPosition(door);
			pf.SetFieldPosition(snake);
			pf.SetFieldPosition(player);

		} else {
			setDoor();
			setSnake();
			setPlayer();
			setTraps();
			setCharacters(); /// recursive
		}

	}

	private boolean winGame() {
		if (door.getLocation()[0] == player.getLocation()[0] && door.getLocation()[1] == player.getLocation()[1]) {
			pf.clearLastPosition(player.getLocation());
			pf.setCharAt(player.getLocation());
			return true;
		} else {
			return false;
		}
	}

	private boolean lostGame() {
		if (snake.getLocation()[0] == player.getLocation()[0] && snake.getLocation()[1] == player.getLocation()[1]
				|| player.getLocation()[0] == trap1.getLocation()[0]
						&& player.getLocation()[1] == trap1.getLocation()[1]
				|| player.getLocation()[0] == trap2.getLocation()[0]
						&& player.getLocation()[1] == trap2.getLocation()[1]
				|| player.getLocation()[0] == trap3.getLocation()[0]
						&& player.getLocation()[1] == trap3.getLocation()[1]) {
			return true;
		} else {
			return false;
		}
	}

	public void InitalizeGame() {
		pf.InitalizeField();
		setCharacters();
		pf.PrintField();

	}

	public void runGame() {
		Scanner sc = new Scanner(System.in);

		player.move(sc.next().charAt(0), pf);
		pf.SetFieldPosition(player);

		pf.PrintField();

		if (winGame()) {
			pf.clearLastPosition(player.getLocation());
			pf.setCharAt(player.getLocation());

		} else if (lostGame()) {
			pf.clearLastPosition(player.getLocation());
			pf.setCharAt(player.getLocation());
			bo = true;
			sc.close();
		} else {
			runGame(bo);
		}

		if (lost) {
			System.out.println("Game Over");
		} else {
			System.out.println("Gewonnen");
		}
		sc.close();
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