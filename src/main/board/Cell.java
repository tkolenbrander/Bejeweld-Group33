package main.board;

import main.board.gems.Gem;

/**
 * Creates a cell object.
 * 
 * @author Bart van Oort
 */
public class Cell {

	/**
	 * The gem that inhabits this cell.
	 */
	private Gem occupant;

	/**
	 * Creates an empty cell.
	 */
	public Cell() {

	}

	/**
	 * Creates a cell with Gem g inside.
	 * 
	 * @param g The Gem inside the cell.
	 */
	public Cell(Gem g) {
		occupant = g;
	}

	/**
	 * Returns the gem that is currently occupying this cell
	 * 	or returns null if it is empty.
	 * 
	 * @return The gem that is currently occupying this cell
	 * 			or null if it is empty.
	 */
	public Gem getGem() {
		return occupant;
	}

	/**
	 * @return True iff the cell is empty.
	 */
	public boolean isEmpty() {
		return occupant == null;
	}

	/**
	 * Checks if two cells are equal.
	 * 
	 * @param other The cell to check.
	 * @return True iff the two cells are equal.
	 */
	public boolean equals(Object other) {
		if (other instanceof Cell) {
			Cell that = (Cell) other;

			if (this.occupant == null && that.occupant == null) {
				return true;
			}

			if (this.occupant == null || that.occupant == null) {
				return false;
			}

			return this.occupant.equals(that.occupant);  
		}
		return false;
	}
}
