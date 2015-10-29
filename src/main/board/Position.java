package main.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a position in a 2D grid.
 * 
 * @author Ruben Vrolijk
 */
public class Position {

	/**
	 * X coordinate of the position.
	 */
	private int x;

	/**
	 * Y coordinate of the position.
	 */
	private int y;

	/**
	 * Creates a Position object with coordinates x and y.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return x-coordinate of this Position.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the difference in x-value of two Positions.
	 * Can be positive or negative.
	 * 
	 * Usage: <code>from.deltaX(to);</code>
	 * 
	 * @param to The position
	 * @return The difference in x-value between this and to.
	 */
	public int deltaX(Position to) {
		return to.x - this.x;
	}

	/**
	 * @return y-coordinate of this Position.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the difference in y-value of two Positions.
	 * Can be positive or negative.
	 * 
	 * Usage: <code>from.deltaY(to);</code>
	 * 
	 * @param to The position
	 * @return The difference in y-value between this and to.
	 */
	public int deltaY(Position to) {
		return to.y - this.y;
	}

	/**
	 * @returns True iff they are.
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
	 * Checks if two Positions are adjacent.
	 * 
	 * @param other The position to be checked against.
	 * @return True iff the two Positions are adjacent.
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

	/**
	 * @return True is the position is on the board.
	 */
	public boolean isInBoard() {
		return this.x >= 0 && this.x < Board.BOARDSIZE && this.y >= 0 && this.y < Board.BOARDSIZE;
	}

	/**
	 * Gets the surrounding 8 positions of this position.
	 * Positions off-board and this position itself are excluded.
	 * 
	 * The positions are ordered as follows:
	 * (x-1, y-1), (x-1, y), (x-1, y+1),
	 * (x, y-1), (x, y+1),
	 * (x+1, y-1), (x+1, y), (x+1, y+1)
	 * 
	 * @return A list of the 8 positions surrounding this position.
	 */
	public List<Position> getSurrounding() {
		ArrayList<Position> positions = new ArrayList<Position>();

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if ((x != 0 || y != 0) 
						&& this.x + x >= 0 
						&& this.x + x < Board.BOARDSIZE 
						&& this.y + y >= 0 
						&& this.y + y < Board.BOARDSIZE) {
					Position pos = new Position(this.x + x, this.y + y);
					positions.add(pos);
				}
			}
		}
		return positions;
	}

	/**
	 * @return A string with the x and y coordinate, separated with a single space.
	 */
	@Override
	public String toString() {
		return this.x + " " + this.y;
	}
}
