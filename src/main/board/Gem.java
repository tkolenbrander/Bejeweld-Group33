package main.board;

import java.util.List;

import javafx.scene.image.Image;

/**
 * Gem class to represent the gems in Bejeweled.
 * 
 * @author Bart van Oort
 */
public abstract class Gem {
	
	protected GemType type;
	protected DestroyBehaviour onDestroy;
	
	/**
	 * Creates a Gem object with GemType t.
	 * @param t GemType of the Gem.
	 */
	public Gem(GemType t) {
		type = t;
	}
	
	/**
	 * Returns the type of gem.
	 * @return type of gem.
	 */
	public GemType getType() {
		return type;
	}
	
	/**
	 * Return the image of a gem.
	 * @return The image of a gem
	 */
	public abstract Image getImage();
	
	/**
	 * Return the image of a gem when it's clicked.
	 * @return The image of a gem when it's clicked
	 */
	public abstract Image getImageClicked();
	
	/**
	 * Destroys this gem.
	 * Gives a list of the positions of all the other gems that got destroyed when this gem got destroyed.
	 * @param cells	Array of the cells containing all the gems
	 * @param pos	The position of this gem on the board
	 * @return		A list of the positions of all the other gems that got destroyed when this gem gets destroyed.
	 */
	public List<Position> destroy(Cell[][] cells, Position pos){
		return onDestroy.destroy(cells, pos);
	};
	
	/**
   * Checks if two gems are equal.
   * @param other gem to check
   * @return true iff the two gems are equal.
   */
	@Override
	public boolean equals(Object other) {
	  if (other instanceof Gem) {
	    return this.type == ((Gem) other).type;
	  }
	  return false;
	}
}
