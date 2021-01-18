import java.util.*;
/**
 * 
 * @author Wendy Li
 *Student number: 251026390
 *CS 2211 Assignment 2
 */

public class nk_TicTacToe {
	int board_size;
	int inline;
	int max_levels;
	char[][] gameBoard;
	
	/**
	 * The constructor for nk_TicTacToe and initializes variables
	 * found in this class
	 * 
	 * @param board_size: the size of the board
	 * @param inline: number of adjacent symbols in a row to win
	 * @param max_levels: number of levels the computer stores
	 */
	public nk_TicTacToe(int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		this.gameBoard = new char[board_size][board_size]; //creates a new game board
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				this.gameBoard[i][j] = ' '; //initializes each square with an empty space
			}
		}
	}
	
	/**
	 * This method creates an empty Dictionary 
	 * @return: dictionary
	 */
	public Dictionary createDictionary() {
		Dictionary dictionary = new Dictionary(6967); //creating a dictionary of size 6967
		return dictionary; 
	}
	
	/**
	 * This method checks if the string representation of
	 * gameBoard is already in the dictionary or not
	 * 
	 * @param configurations: the dictionary
	 * @return: the corresponding score of config if it is in 
	 * dictionary, and -1 if it is not
	 */
	public int repeatedConfig(Dictionary configurations) {
		String config = "";
		for (int i = 0; i < gameBoard.length; i++) { //iterate through game board
			for (int j = 0; j < gameBoard.length; j++) {
				config += gameBoard[i][j]; //concatenating gameBoard value to String config
			}
		}
		int score = configurations.get(config);
		return score;
	}
	
	/**
	 * This method inserts the string representation of the gameBoard
	 * and corresponding score into dictionary. It catches DictionaryException
	 * if insert is not completed. 
	 * 
	 * @param configurations: the dictionary
	 * @param score: score associated with config 
	 */
	public void insertConfig(Dictionary configurations, int score) {
		String config = "";
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard.length; j++) {
				config += gameBoard[i][j]; //concatenating gameBoard value to String config 
			}
		}
		try {
			configurations.insert(new Record(config, score));
		}catch (DictionaryException e) { //catches DictionaryException if the insert fails 
			System.out.println("Insert failed.");
		}
	}
	
	/**
	 * This function stores the play
	 * 
	 * @param row: row of gameBoard
	 * @param col: column of gameBoard
	 * @param symbol: symbol being stored in gameBoard
	 */
	public void storePlay(int row, int col, char symbol) {
		this.gameBoard[row][col] = symbol; 
	}
	
	/**
	 * This method checks if a specific square in gameBoard is 
	 * empty
	 * 
	 * @param row: row of gameBoard
	 * @param col" column of gameBoard
	 * @return: true if square is empty and false otherwise
	 */
	public boolean squareIsEmpty(int row, int col) {
		char symbol = this.gameBoard[row][col];
		if (Character.compare(symbol, ' ') == 0) { 
			return true;
		}else {
			return false; 
		}
	}
	
	/**
	 * This method checks if there are inline adjacent occurrences
	 * of the same symbol in gameBoard in the same row, column or 
	 * diagonal
	 * 
	 * @param symbol: X or O
	 * @return true if computer or human wins, false otherwise
	 */
	public boolean wins(char symbol) {
		if (checkRow(symbol) || checkCol(symbol) || leftRightDiag(symbol) || rightLeftDiag(symbol)) {
			return true;
		}else {
			return false;
		}
		
	}

	/**
	 * This function checks for inline adjacent occurrences of the same symbol
	 * in the same row in gameBoard
	 * @param symbol: X or O
	 * @return true if the requirement is met, false otherwise
	 */
	private boolean checkRow(char symbol) {
		int count = 0;
		for (int i = 0; i < gameBoard.length; i++) { //variable i iterates through each row
			for(int j = 0; j < gameBoard.length; j++) { //variable j iterates through each column
				if(Character.compare(gameBoard[i][j], symbol) == 0) {
					count += 1; //increase count to 1 if the board matches the symbol
					if (count == inline) { // checking if the requirement of inline adjacent symbols are met 
						return true;
					}
				}else {
					count = 0;
				}
			}
			count = 0; //resetting count through each new row
		}
		return false; //return false if requirement isn't met
	}
	
	/**
	 * This function checks for inline adjacent occurrence of the same symbol 
	 * in the same column in gameBoard
	 * @param symbol: X or O
	 * @return true if the requirement is met, false otherwise
	 */
	private boolean checkCol(char symbol) {
		int count = 0;
		for (int i = 0; i < gameBoard.length; i++) { //variable i iterates through each column
			for (int j = 0; j < gameBoard.length; j++) { //variable j iterates through each row
				if(Character.compare(gameBoard[j][i], symbol) == 0) {
					count += 1; //increase count to 1 if the board matches the symbol
					if (count == inline) { // checking if the requirement of inline adjacent symbols are met 
						return true;
					}
				}else {
					count = 0;//resetting count if the adjacent streak is broken
				}
			}
			count = 0; //resetting count through each new column
		}
		return false; //return false if requirement isn't met 
	}
	
	/**
	 * This function checks for inline adjacent occurrence of the same symbol 
	 * in a diagonal line from top right to bottom left in gameBoard
	 * @param symbol: X or O
	 * @return true if the requirement is met, false otherwise
	 */
	private boolean rightLeftDiag(char symbol) {
		
		int count = 0;
		
		//iterating through the diagonal lines from top right to bottom left and going
		//up through the board
		for (int i = 0; i < gameBoard.length; i++) { // i is used to bound the second for loop as well as adjust 
													// the decreasing start point of column for each diagonal line
			for (int j = 0; j < gameBoard.length-i; j++) { 
				if (Character.compare(gameBoard[j][board_size-j-i-1], symbol) == 0) {
					count += 1;
					if (count == inline) { //checking if the requirement of inline adjacent symbols are met
						return true;
					}
				}else {
					count = 0;//resetting count if the adjacent streak is broken
				}
			}
			count = 0; //resetting count through each diagonal line
		}
		count = 0; //resetting count
		
		//iterating through the diagonal lines from top right to bottom left and going
		//down the board
		for (int i = 0; i < gameBoard.length; i ++) {
			for (int j = gameBoard.length - 1; j > i; j--) { 
				if (Character.compare(gameBoard[board_size - j + i][j], symbol) == 0) {
					count += 1;
					if (count == inline) { //checking if the requirement of inline adjacent symbols are met
						return true;
					}
				}else {
					count = 0;//resetting count if the adjacent streak is broken
				}
			}
			count = 0; //resetting count through each new diagonal line
		}
		return false; //return false if none of the requirements are met
	}
	
	/**
	 * This function checks for inline adjacent occurrence of the same symbol 
	 * in a diagonal line from top left to bottom right in gameBoard
	 * @param symbol: X or O
	 * @return return true if there are inline adjacent occurrence of the same symbol,
	 * false otherwise
	 */
	private boolean leftRightDiag(char symbol) {
		int count = 0;
		
		//iterating through diagonal lines from top left to bottom right and going down 
		//the game board
		for (int i = 0; i < gameBoard.length; i++) {
			for(int j = 0; j < gameBoard.length - i; j++) { //variable i ensures j does not go out of bounds
				if (Character.compare(gameBoard[j+i][j], symbol) == 0) {
					count += 1;
					if (count == inline) { //checking if the requirement of inline adjacent symbols are met
						return true;
					}
				}else {
					count = 0;//resetting count if the adjacent streak is broken
				}
			}
			count = 0; //resetting count through each new diagonal line
		}
		count = 0; //resetting count 
		
		//iterating through diagonal lines from top left to bottom right and going
		//up the gameboard
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard.length - i; j++) {
				if (Character.compare(gameBoard[j][j+i], symbol) == 0) {
					count += 1;
					if (count == inline) {
						return true;

					}
				}else {
					count = 0;//resetting count if the adjacent streak is broken
				}
			}
			count = 0; //resetting count through each new diagonal line
		}
				
		return false; //returns false if there are no inline adjacent symbols in a diagonal line on game board
	}
	/**
	 * This method analyzes a full gameBoard and determines if it 
	 * is a draw or not
	 * @return: true if it is a draw and false otherwise
	 */
	public boolean isDraw() {
		if (!isEmpty() && !wins('X') && !wins('O')) { //checks if there are no wins and if the board is not empty
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Checks if the board game is empty or not
	 * @return: true if board game is empty, false otherwise
	 */
	private boolean isEmpty() {
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (Character.compare(gameBoard[i][j], ' ') == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This method evaluates the gameBoard
	 * @return: 3 if computer wins, 2 if it is a draw, 
	 * 0 if human wins and 1 otherwise
	 */
	public int evalBoard() {
		if(wins('O')) { //computer wins
			return 3;
		}else if(wins('X')) { //human wins
			return 0;
		}else if(isDraw()) { //is a draw
			return 2;
		}else { //undecided
			return 1;
		}
	}
}
