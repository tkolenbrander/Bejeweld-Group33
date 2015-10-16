package main.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class to represent a Board.
 * 
 * @author Bart van Oort
 *
 */
public class Board {

	public static final int BOARDSIZE = 8;
	private Cell[][] board;

	/**
	 * Constructor for a board. Also generates and places all cells and their content.
	 */
	public Board() {
		board = new Cell[BOARDSIZE][BOARDSIZE];
		initBoard();
	}

	/**
	 * Constructor for a board, allowing the board to be set to a predefined map.
	 * Used in testing and loading a saved game
	 * @param cells board
	 */
	public Board(Cell[][] cells) {
		board = cells;
	}

	/**
	 * Returns all the cells of the board.
	 * @return two-dimensional array with all the cells on the board.
	 */
	public Cell[][] getCells() {
		return board;
	}

	/**
	 * Resets the board to how it came out of the constructor. Basically, the board is
	 * reinitialised, that's it.
	 */
	public void reset() {
		initBoard();
	}

	/**
	 * Fills up the initially empty board with cells.
	 */
	private void initBoard() {
		for (int y = 0; y < BOARDSIZE; y++) {
			for (int x = 0; x < BOARDSIZE; x++) {
				board[y][x] = new Cell(new RegularGem(GemType.randomGem()));
				while (isTripletAt(x, y)) {
					board[y][x] = new Cell(new RegularGem(GemType.randomGem()));
				}
			}
		}
	}

	/**
	 * Sets a cell at location (x, y).
	 * @param cell cell to be set
	 * @param x x-coordinate of the cell
	 * @param y y-coordinate of the cell
	 */
	public void setCell(Cell cell, int x, int y) {
		board[y][x] = cell;
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
			if (isTripletInDir(x, y, dir)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the origin cell, at location (x, y), is part of a chain of three 
	 * of the same gems in the given direction.
	 * @param x x-coordinate of the origin cell
	 * @param y y-coordinate of the origin cell
	 * @param dir the direction of the chain, relative to the origin cell
	 * @return true iff the origin cell, at location (x, y), is part of a chain of
	 *         three of the same gems in the given direction.
	 */
	public boolean isTripletInDir(int x, int y, Direction dir) {
		GemType currGemType = board[y][x].getGem().getType();
		Cell neighbour = getNeighbourAt(x, y, dir);
		if (neighbour != null && currGemType == neighbour.getGem().getType()) {
			Cell tripletEnd = getNeighbourAt(x + dir.getDX(), y + dir.getDY(), dir);
			if (tripletEnd != null && currGemType == tripletEnd.getGem().getType()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the chain starting at the origin cell, at location (x, y), in the given direction.
	 * @param x x-coordinate of the origin cell
	 * @param y	y-coordinate of the origin cell
	 * @param dir the direction of the chain, relative to the origin cell
	 * @return a list of the positions of cells in the chain.
	 * 		   the list will be empty iff there is no chain 
	 *       starting at location (x, y) in the given direction.
	 */
	public List<Position> getChainAt(int x, int y, Direction dir) {
		List<Position> chain = new ArrayList<Position>();
		if (isTripletInDir(x, y, dir)) {
			GemType currGemType = board[y][x].getGem().getType();
			while (x >= 0 && x < BOARDSIZE && y >= 0 && y < BOARDSIZE) {
				if (currGemType == board[y][x].getGem().getType()) {
					chain.add(new Position(x, y));
				} else {
					break;
				}
				x = x + dir.getDX();
				y = y + dir.getDY();
			}
		}
		return chain;
	}

	/**
	 * Returns a list of all the chains of three or more of the same gems on the board.
	 * @return a list of the positions of all the cells on the board that are in a chain.
	 *		   the list will be empty iff there are no chains on the board.
	 */
	@SuppressWarnings("magicnumber")
	public List<List<Position>> chainedCells() {
		List<List<Position>> chains = new ArrayList<List<Position>>();
		for (int y = 0; y < BOARDSIZE; y++) {
			for (int x = 0; x < BOARDSIZE; x++) {
				for (int i = 1; i < 3; i++) {
					Direction dir = Direction.DIRECTIONS.get(i);
					List<Position> chain = getChainAt(x, y, dir);
					boolean toAdd = true;
					for (List<Position> existingChain : chains) {
						if (existingChain.containsAll(chain)) {
							toAdd = false;
							break;
						}
					}
					if (toAdd && chain.size() != 0) {
						chains.add(chain);
					}
				}
			}
		}
		return chains;
	}

	/**
	 * Destroys all the gems that are in a chain and returns a list of the cells changed.
	 * Creates a PowerGem when a chain of 4 or more is destroyed
	 * 
	 * @return A list of removed Positions
	 */
	public List<Change<Position>> removeChains() {
		List<List<Position>> chains = chainedCells();
		List<Change<Position>> changes = new ArrayList<Change<Position>>();
		for (List<Position> chain : chains) {
			GemType type = null;
			for (Position pos : chain) {
				Cell cell = board[pos.getY()][pos.getX()];
				if (cell != null){
					type = cell.getGem().getType();
					List<Position> removed = cell.getGem().destroy(board, pos); // <--------------------------------------------------------------------------------- Gotta check this shit man
					for (Position destroyed : removed) {
					  changes.add(new Remove<Position>(destroyed));
					}
					changes.add(new Remove<Position>(pos));
				}
			}
			if (chain.size() >= 4) {
				Position powerPos = chain.get((int) Math.random() * chain.size());
				Gem powerGem = new PowerGem(type);
				board[powerPos.getY()][powerPos.getX()] = new Cell(powerGem); //<--------------------------------------------------------------- This too
				changes.add(new Create<Position>(powerPos, powerGem));
			}
		}
		return changes;
	}

	/**
	 * When an empty cell is detected, the cells above them will fall down into the empty cell.
	 * 
	 * @return a list of Change-objects, where the From object is the first filled cell
	 * above the empty cell.
	 */
	public List<Change<Position>> falldown() {
	  List<Change<Position>> changes = new ArrayList<Change<Position>>();
		for (int y = BOARDSIZE - 1; y >= 0; y--) {
			for (int x = BOARDSIZE - 1; x >= 0; x--) {
				if (board[y][x] == null) {
					for (int d = 1; d <= y; d++) {
						if (y != 0 && board[y - d][x] != null) {
							board[y][x] = board[y - d][x];
							board[y - d][x] = null;
							changes.add(
							    new Change<Position>(new Position(x, y - d), new Position(x, y)));
							break;
						}
					}
				}  
			}
		}
		return changes;
	}

	/**
	 * Fills empty cells with new gems.
	 * 
	 * A new gem is represented in the returned list as a Change-object with the
	 * From-position being (-1, -1)
	 * 
	 * @return a list of Change-objects where the From position is (-1, -1).
	 */
	public List<Change<Position>> fillEmptyCells() {
	  List<Change<Position>> changes = new ArrayList<Change<Position>>();
		for (int y = 0; y < BOARDSIZE; y++) {
			for (int x = 0; x < BOARDSIZE; x++) {
				if (board[y][x] == null) {
					Gem g = new RegularGem(GemType.randomGem());
					board[y][x] = new Cell(g);
					changes.add(new Create<Position>(new Position(x, y), g));
				}
			}
		}
		return changes;
	}
	
	/**
	 * Checks if the current board has any chains.
	 * @return true iff the current board has at least one chain.
	 */
	@SuppressWarnings("magicnumber")
	public boolean hasChain() {
		for (int y = 0; y < BOARDSIZE; y++) {
			for (int x = 0; x < BOARDSIZE; x++) {
				for (int i = 1; i < 3; i++) {
					Direction dir = Direction.DIRECTIONS.get(i);
					if (isTripletInDir(x, y, dir)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Checks if the current board allows for a move that will create a chain.
	 * @return true iff any gem on the board can be swapped with its neighbour to create a chain.
	 */
	@SuppressWarnings("magicnumber")
	public boolean checkMoves() {
		for (int y = 0; y < BOARDSIZE; y++) {
			for (int x = 0; x < BOARDSIZE; x++) {
				for (int i = 1; i < 3; i++) {
					Direction dir = Direction.DIRECTIONS.get(i);
					int newX = x + dir.getDX();
					int newY = y + dir.getDY();
					if (newX == BOARDSIZE || newY == BOARDSIZE) {
						continue;
					}
					swap(x, y, newX, newY);
					if (hasChain()) {
						swap(x, y, newX, newY);
						return true;    //possibility to return positions for hint system.
					}
					swap(x, y, newX, newY);
				}
			}
		}
		return false;
	}
	
	/**
	 * Gets the positions of all the Power Gems on the board.
	 * @return A list containing all the positions of Power Gems on the board.
	 */
	public List<Position> getPowerGems(){
		List<Position> positions = new ArrayList<Position>();
		for (int y = 0; y < BOARDSIZE; y++) {
			for (int x = 0; x < BOARDSIZE; x++) {
				if (board[y][x].getGem() instanceof PowerGem){
					positions.add(new Position(x, y));
				}
			}
		}
		return positions;
	}

	/**
	 * Gets the neighbouring cell in the direction dir from the origin cell at location (x,y)
	 * or returns null iff the origin cell does not have a neighbour in that direction.
	 * @param x x-coordinate of the origin cell
	 * @param y y-coordinate of the origin cell
	 * @param dir Direction from the origin cell to the returned neighbouring cell
	 * @return neighbouring cell in the direction dir from the origin cell at (x,y) or 
	 *       null iff the origin cell does not have a neighbour in that direction.
	 */
	public Cell getNeighbourAt(int x, int y, Direction dir) {
		int newX = x + dir.getDX();
		int newY = y + dir.getDY();
		if (newX >= 0 && newX < BOARDSIZE && newY >= 0 && newY < BOARDSIZE) {
			return board[newY][newX];
		}
		else {
			return null;
		}
	}

	/**
	 * Gets a list of all neighbours of the cell at (x,y).
	 * This list is sorted NORTH, EAST, SOUTH, WEST. If a neighbour is non-existent
	 * in a direction, then it will be null.
	 * @param x x-coordinate of the cell to get all neighbours of
	 * @param y y-coordinate of the cell to get all neighbours of
	 * @return List of all neighbours of the cell at (x,y).
	 */
	public List<Cell> getNeighboursOf(int x, int y) {
		ArrayList<Cell> result = new ArrayList<Cell>();
		result.add(getNeighbourAt(x, y, Direction.NORTH));
		result.add(getNeighbourAt(x, y, Direction.EAST));
		result.add(getNeighbourAt(x, y, Direction.SOUTH));
		result.add(getNeighbourAt(x, y, Direction.WEST));
		return result;
	}

	/**
	 * Swaps the cells at locations (x1, y1) and (x2, y2).
	 * @param x1 x-coordinate of cell 1
	 * @param y1 y-coordinate of cell 1
	 * @param x2 x-coordinate of cell 2
	 * @param y2 y-coordinate of cell 2
	 */
	public void swap(int x1, int y1, int x2, int y2) {
		List<Change<Position>> changes = new ArrayList<Change<Position>>();
		Cell temp = board[y1][x1];
		board[y1][x1] = board[y2][x2];
		board[y2][x2] = temp;
		changes.add(new Change<Position>(new Position(x2, y2), new Position(x1, y1)));
		changes.add(new Change<Position>(new Position(x1, y1), new Position(x2, y2)));
	}
	
	/**
	 * Calculates the score the player is supposed to receive from the current board.
	 * @param bonus Initial bonus.
	 * @return score the player should receive.
	 */
	@SuppressWarnings("magicnumber")
	public int calculateScore(int bonus) {
		int score = 0;
		List<List<Position>> chains = chainedCells();
		for (int i = 0; i < chains.size(); i++) {
			if (chains.get(i).size() == 3) {
				score = score + 50 + bonus * 50;	
			}
			if (chains.get(i).size() == 4) {
				score = score + 100 + bonus * 50;
			}
			if (chains.get(i).size() == 5) {
				score = score + 500 + bonus * 50;		
			}		
		}
		return score;
	}

	/**
	 * Returns a String representing the board in the format for a saved game.
	 * @return returns a string with the saved board.
	 */
	public String toString() {
	  final String eol = System.getProperty("line.separator");
	  String result = "";
		for (int y = 0; y < BOARDSIZE; y++) {
			for (int x = 0; x < BOARDSIZE; x++) {
				result += board[y][x].getGem().getType() + " ";
			}
			result += eol;
		}
		return result;
	}

	/**
	 * Checks if two boards are equal.
	 * @return if the two boards are equal or not.
	 */
	@Override
	public boolean equals(Object board) {
		if (board instanceof Board) {
			Board that = (Board) board;
			return Arrays.deepEquals(this.getCells(), that.getCells());
		}
		return false;
	}
}
