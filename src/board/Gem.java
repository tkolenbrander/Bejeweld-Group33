package board;

import java.util.List;

import javafx.scene.image.Image;

/**
 * Gem interface to represent the gems in Bejeweled.
 * 
 * @author Bart van Oort
 */
public abstract class Gem {
	
	protected GemType type;
	
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
