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
	  for (int x = 0; x < BOARDSIZE; x++) {
	    for (int y = 0; y < BOARDSIZE; y++) {
	      board[x][y] = new Cell(new Gem(GemType.randomGem()));
	      while (isTripletAt(x, y)) {
	        board[x][y] = new Cell(new Gem(GemType.randomGem()));
	      }
	    }
	  }
	}
	public void newBoard() {
	  for (int x = 0; x < BOARDSIZE; x++) {
	    for (int y = 0; y < BOARDSIZE; y++) {
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
		  GemType currGemType = board[x][y].getGem().getType();
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
			GemType currGemType = board[x][y].getGem().getType();
			while (x >= 0 && x < BOARDSIZE && y >= 0 && y < BOARDSIZE) {
				if (currGemType == board[x][y].getGem().getType()) {
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
		for (int x = 0; x < BOARDSIZE; x++) {
			for (int y = 0; y < BOARDSIZE; y++) {
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
					if (toAdd) {
					  chains.add(chain);
					}
				}
			}
		}
		return chains;
	}
	/**
	 * Removes all the gems that are in a chain
	 */
	public void removeChains() {
		List<List<Position>> chains = chainedCells();
		for (int i = 0; i < chains.size(); i++){
			List<Position> chain = chains.get(i); 
			for(int v = 0; v < chain.size(); v++){
				Position pos = chain.get(v);
				board[pos.getX()][pos.getY()] = null;
			}
		}
	}
	/**
	 * when an empty cell is detected, the rows above them will fall down into the empty cell
	 */
	public void falldown(){
		for (int y = BOARDSIZE -1; y >= 0; y--) {
		    for (int x = BOARDSIZE -1; x >= 0; x--) {
		    if (board[x][y] == null){
		    	for (int d = 1; d < BOARDSIZE - y; d++){
		    		if (board[x][y-d] != null){
			    	board[x][y] = board[x][y-d];
			    	board[x][y-d] = null;
			    	break;
		    		}
		    	}
		    }  
		   }
		}
	}
	/**
	 * fills empty cells with new gems.
	 */
	public void fillEmptyCells() {
		 for (int x = 0; x < BOARDSIZE; x++) {
			 for (int y = 0; y < BOARDSIZE; y++) {
			   if (board[x][y] == null) {
			     board[x][y] = new Cell(new Gem(GemType.randomGem()));
			     while (isTripletAt(x, y)) {
			       board[x][y] = new Cell(new Gem(GemType.randomGem()));
			     }
			   }
			 }
		 }
	}
	/**
	 * Checks if the current board has any chains.
	 * @return true iff the current board has at least one chain.
	 */
	@SuppressWarnings("magicnumber")
	public boolean hasChain() {
		for (int x = 0; x < BOARDSIZE; x++) {
			for (int y = 0; y < BOARDSIZE; y++) {
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
		for (int x = 0; x < BOARDSIZE; x++) {
			for (int y = 0; y < BOARDSIZE; y++) {
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

	/*
	public void printBoard() {
	  System.out.println("Board: ");
	  for (int y = 0; y < BOARDSIZE; y++) {
	    for (int x = 0; x < BOARDSIZE; x++) {
	      System.out.print(board[x][y].getGem().getType() + ", " + x + "," + y + "! ");
	    }
	    System.out.println();
	  }
	}
	*/
}
