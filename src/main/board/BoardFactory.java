package main.board;

import main.board.gems.GemType;
import main.board.gems.RegularGem;

public class BoardFactory {

	private int BOARDSIZE;
	private Cell[][] board;

	public BoardFactory() {
		BOARDSIZE = Board.BOARDSIZE;
		board = new Cell[BOARDSIZE][BOARDSIZE];
	}

	/**
	 * Fills up the initially empty board with cells.
	 * @return The generated board.
	 */
	public Board generateBoard() {
		Board boardObject = new Board();
		for (int y = 0; y < BOARDSIZE; y++) {
			for (int x = 0; x < BOARDSIZE; x++) {
				board[y][x] = new Cell(new RegularGem(GemType.randomGem()));
				boardObject.setCells(board);
				while (isTripletAt(x, y)) {
					board[y][x] = new Cell(new RegularGem(GemType.randomGem()));
				}				
			}
		}
		
		return boardObject;
	}
	
	/**
	 * Generates a board and allowing the board to be set to a predefined map.
	 * Used in testing and loading a saved game
	 * @return The generated board.
	 */
	public Board generateBoard(Cell[][] cells) {
		Board boardObject = new Board();
		boardObject.setCells(cells);
		return boardObject;
	}
	
	/**
	 * Checks if the origin cell, at location (x, y), is part of a chain of three 
	 * of the same gems.
	 * @param x x-coordinate of the origin cell
	 * @param y y-coordinate of the origin cell
	 * @return true iff the origin cell, at location (x, y), is part of a chain of
	 *         three of the same gems.
	 */
	@SuppressWarnings("magicnumber") //4 is the amount of directions there are.
	public boolean isTripletAt(int x, int y) {
		for (int i = 0; i < 4; i++) {
			Direction dir = Direction.DIRECTIONS.get(i);
			if (Board.isTripletInDir(x, y, dir)) {
				return true;
			}
		}
		return false;
	}
}
