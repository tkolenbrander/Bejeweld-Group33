package main.board;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent the behaviour of destroying a power gem
 * @author Ruben
 *
 */
public class PowerDestroyBehaviour implements DestroyBehaviour{

	/**
	 * Destroys a given gem.
	 * Gives a list of the positions of all the other gems that got destroyed when a given gem got destroyed.
	 * 
	 * With this behaviour, all 8 surrounding gems are destroyed.
	 * These gems can also recursively destroy more gems, which are all added to the list of destroyed gems.
	 * @param cells	Array of the cells containing all the gems
	 * @param pos	The position of the given gem on the board
	 * @return		A list of the positions of all the other gems that got destroyed when the given gem gets destroyed.
	 */
	public List<Position> destroy(Cell[][] cells, Position pos) {
		cells[pos.getY()][pos.getX()] = null;
		List<Position> destroyed = new ArrayList<Position>();
		List<Position> surrounding = pos.getSurrounding();
		for (Position surPos : surrounding){
			Cell cell = cells[surPos.getY()][surPos.getX()];
			List<Position> combo = null;
			if (cell != null){
				combo = cell.getGem().destroy(cells, surPos);
			}
			destroyed.add(surPos);
			if (combo != null){
				destroyed.addAll(combo);
			}
		}
		return destroyed;
	}

}
