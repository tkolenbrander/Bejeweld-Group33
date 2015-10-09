package board;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a position on a board.
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
	 * Gets the surrounding 8 positions of this position.
	 * Positions off-board and this position itself are excluded.
	 * The positions are ordered as follows:
	 * (x-1, y-1), (x-1, y), (x-1, y+1),
	 * (x, y-1), (x, y+1),
	 * (x+1, y-1), (x+1, y), (x+1, y+1)
	 * @return A list of the 8 positions surrounding this position.
	 */
	public List<Position> getSurrounding(){
		ArrayList<Position> positions = new ArrayList<Position>();
		for (int x = -1; x <= 1; x++){
			for (int y = -1; y <= 1; y++){
				if ((this.x != x || this.y != y) &&
						x >= 0 && x < Board.BOARDSIZE &&
						y >= 0 && y < Board.BOARDSIZE){
					Position pos = new Position(this.x+x, this.y+y);
					positions.add(pos);
				}
			}
		}
		return positions;
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
