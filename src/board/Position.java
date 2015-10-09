package board;

/**
 * Class to represent a position in a 2D grid.
 * 
 * @author Ruben
 *
 */
public class Position {
	
	private int x;
	private int y;
	
	/**
	 * Creates a Position object with coordinates x and y.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns x-coordinate of this Position.
	 * @return x-coordinate of this Position.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the difference in x-value of two Positions.
	 * Can be positive or negative.
	 * 
	 * Usage: from.deltaX(to);
	 * 
	 * @param to Position
	 * @return difference in x-value between this and to.
	 */
	public int deltaX(Position to) {
	  return to.x - this.x;
	}
	
	/**
	 * Returns y-coordinate of this Position.
	 * @return y-coordinate of this Position.
	 */
	public int getY() {
		return y;
	}
	
	/**
   * Returns the difference in y-value of two Positions.
   * Can be positive or negative.
   * 
   * Usage: from.deltaY(to);
   * 
   * @param to Position
   * @return difference in y-value between this and to.
   */
  public int deltaY(Position to) {
    return to.y - this.y;
  }
  
	/**
	 * Checks if two Positions are equal.
	 * @returns true iff they are.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Position) {
			Position that = (Position) other;
			return this.x == that.x && this.y == that.y;
		}
		return false;
	}
	
	 /**
   * Checks if two Positions, are adjacent.
   * @param other The position to be checked against.
   * @return returns true iff the two Positions are adjacent
   */
  public boolean isAdjacentTo(Position other) {
    if ((this.x - other.x == 1 || this.x - other.x == -1) && this.y == other.y) {
      return true;
    }
    if ((this.y - other.y == 1 || this.y - other.y == -1) && this.x == other.x) {
      return true;
    }
    return false;
  }
  
  public boolean isInBoard() {
    return this.x >= 0 && this.x < Board.BOARDSIZE && this.y >= 0 && this.y < Board.BOARDSIZE;
  }
	
}
