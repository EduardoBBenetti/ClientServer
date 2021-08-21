/**
 * 
 * @author Eduardo Barros Benetti
 * @version 1
 * @since February 2, 2020
 *
 */

	/**
	 * Represented by the Board game, with the constants used to run 
	 * the program
	 */	
public class Board implements Constants {
	
	/**
	 * theBoard is the board, where the X and O are being stored
	 */	
	private char theBoard[][];
	/**
	 * Represents the amount of plays in the games
	 */	
	private int markCount;

	/**
	 * Default constructor for the Board, setting markCount to 0 
	 * and stating the board
	 */	
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * Getter of the amount of marks in the board
	 * @return markCount
	 */
	public int getMarkCount() {
		return this.markCount;
	}
	
	/**
	 * Used to get the current values of the board
	 * @return the array of char, the theBoard
	 * @param row , the "x axis" in the theBoard 
	 * @param col , the "y axis" in the board
	 */	
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Checks if the board is full, where there should not be any other play
	 * @return true if marKCount == 9, if not false
	 */	
	public boolean isFull() {
		if(markCount == 9)
			return true;
		else
			return false;
	}

	/**
	 * Checks if the player with LETTER_X wins
	 * @return true if LETTER_X wins, 
	 * else false
	 */	
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Checks if the player with LETTER_O wins
	 * @return true if LETTER_O wins, 
	 * else false
	 */	
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Displays the board game, with the plays from both players 
	 * Puts together the returns from other methods, together forming the information
	 * in the board
	 * @return board as a String
	 */
	public String display() {
		String all = displayColumnHeaders();
		all = all + addHyphens();
		for (int row = 0; row < 3; row++) {
			all= all + addSpaces();
			all = all + "    row " + row + ' ';
			for (int col = 0; col < 3; col++)
				all = all + "|  " + getMark(row, col) + "  ";
			all = all + "|\n";
			all = all + addSpaces();
			all = all + addHyphens();
		}
		return all;
	}

	/**
	 * Records the values into the board and increment markCount, in order to
	 * check if the games continues or not
	 * @param row , the "x axis" in the theBoard 
	 * @param col , the "y axis" in the board
	 * @param mark , is the char representation of the player in the board
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Clear all the board game with SPACE_CHAR and sets the markCount to 0
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Checks all possible ways for a player to win, including column, row and 
	 * diagonals
	 * @return 1 if yes, meaning the player won
	 * else 0 (as assigned at the beginning of the program)
	 * @param mark it can either be a X or a O, both are of char type
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	 * Prints the display of the board game, only the columns
	 * @return String with the content into Strings
	 */
	String displayColumnHeaders() {
		String all = "          ";
		for (int j = 0; j < 3; j++)
			all = all  + "|col " + j;
		all = all + "\n";
		return all;
	}

	/**
	 * Prints the display of the board game, only the hyphens 
	 * to have the division of the game
	 * @return String with the content into Strings
	 */
	String addHyphens() {
		String all = "          ";
		for (int j = 0; j < 3; j++)
			all = all + "+-----";
		all = all +"+\n";
		return all;
	}

	/**
	 * Delimits the end of the board game, with lines to end the rows
	 * @return String with the content into Strings
	 */
	String addSpaces() {
		String all = "          ";
		for (int j = 0; j < 3; j++)
			all = all + "|     ";
		all = all + "|\n";
		return all;
	}
}
