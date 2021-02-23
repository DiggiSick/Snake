public class PlayingField {

	private char[][] field;
	private int row;
	private int col;

	public PlayingField(int x, int y) {
		row = y;
		col = x;
	}

	public void InitalizeField() {
		SetField(row, col);

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[1].length; j++) {
				if (i == 0 || j == 0 || i == field.length - 1 || j == field[1].length - 1) {
					field[i][j] = '#';
				} else {
					field[i][j] = '.';
				}

			}
		}

	}

	public void PrintField() {

		for (int i = 0; i < field.length; i++) {
			System.out.print("\n");
			for (int j = 0; j < field[1].length; j++) {
				System.out.print(field[i][j]);
			}
		}
		System.out.print("\n");
	}

	public void SetFieldPosition(GameCharacter gc) {

		field[gc.getLocation()[0]][gc.getLocation()[1]] = gc.GetSymbol();
	}

	public int GetRows() {
		return row;
	}

	public int GetCols() {
		return col;
	}

	public void SetField(int x, int y) {
		field = new char[x][y];
	}

	public char[][] getField() {
		return field;
	}

	public void clearLastPosition(int[] lastlocation) {
		field[lastlocation[0]][lastlocation[1]] = '.';
	}

	public char getCharacterAt(int[] location) {
		char symbol = field[location[0]][location[1]];
		return symbol;

	}

	public void setCharAt(int[] location) {
		field[location[0]][location[1]] = 'X';

	}

}
