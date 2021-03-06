package main.board.gems;

import java.util.ArrayList;
import java.util.List;

import main.board.Cell;
import main.board.Position;

/**
 * A class to represent the behaviour of destroying a regular gem.
 * 
 * @author Ruben Vrolijk
 */
public class RegularDestroyBehaviour implements DestroyBehaviour {

	/**
	 * Destroys a given gem.
	 * Gives a list of the positions of all the other gems that got destroyed 
	 * 	when a given gem got destroyed.
	 * 
	 * With this behavior, no extra gems are destroyed.
	 * 
	 * @param cells	Array of the cells containing all the gems
	 * @param pos	The position of the given gem on the board
	 * @return		An empty list.
	 */
	public List<Position> destroy(Cell[][] cells, Position pos) {
		cells[pos.getY()][pos.getX()] = null;

		return new ArrayList<Position>();
	}
}
