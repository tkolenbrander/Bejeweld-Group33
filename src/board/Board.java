package board;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a Board.
 * 
 * @author Bart van Oort
 *
 */
public class Board {

  private static final int BOARDSIZE = 8;
	private Cell[][] board;

	/**
	 * Constructor for a board. Also generates and places all cells and their content.
	 */
	public Board() {
		board = new Cell[BOARDSIZE][BOARDSIZE];
		initBoard();
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
	  for (int x = 0; x < BOARDSIZE; x++) {
	    for (int y = 0; x < BOARDSIZE; x++) {
	      board[x][y] = new Cell(new Gem(GemType.randomGem()));
	      while (isTripletAt(x, y)) {
	        board[x][y] = new Cell(new Gem(GemType.randomGem()));
	      }
	    }
	  }
	}
	
	/**
	 * Checks if the origin cell, at location (x, y), is part of a chain of three 
	 * of the same gems.
	 * @param x x-coordinate of the origin cell
	 * @param y y-coordinate of the origin cell
	 * @return true iff the origin cell, at location (x, y), is part of a chain of
	 *         three of the same gems.
	 */
	public boolean isTripletAt(int x, int y) {
	  GemType currGemType = board[x][y].getGem().getType();
	  List<Cell> neighbours = getNeighboursOf(x, y);
	  for (int i = 0; i < neighbours.size(); i++) {
	    Cell neighbour = neighbours.get(i);
	    GemType neighbourGemType = neighbour.getGem().getType();
	    if (neighbourGemType == currGemType) {
	      Direction dir = Direction.DIRECTIONS.get(i);
	      if (neighbourGemType 
	          == getNeighbourAt(x + dir.getDX(), y + dir.getDY(), dir).getGem().getType()) {
	        return true;
	      }
	    }
	    
	  }
	  return false;
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
	    return board[newX][newY];
	  }
	  else {
	    return null;
	  }
	}
	
	/**
	 * Gets a list of all neighbours of the cell at (x,y).
	 * This list is sorted north, east, south, west. If a neighbour is non-existant
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
	  Cell temp = board[x1][y1];
	  board[x1][y1] = board[x2][y2];
	  board[x2][y2] = temp;
	}
}
