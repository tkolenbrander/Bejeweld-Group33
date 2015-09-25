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
	 * Returns y-coordinate of this Position.
	 * @return y-coordinate of this Position.
	 */
	public int getY() {
		return y;
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
	
}
