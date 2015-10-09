package board;

import java.util.List;

/**
 * An interface for how gems get destroyed.
 * @author Ruben
 *
 */
public interface DestroyBehaviour {
	
	/**
	 * Destroys a given gem.
	 * Gives a list of the positions of all the other gems that got destroyed when a given gem got destroyed.
	 * @param cells	Array of the cells containing all the gems
	 * @param pos	The position of the destroyed gem on the board
	 * @return		A list of the positions of all the other gems that got destroyed when the given gem gets destroyed.
	 */
	public List<Position> destroy(Cell[][] cells, Position pos);;
	
}
