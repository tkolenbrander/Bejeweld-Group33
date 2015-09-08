package board;

/**
 * Gem class to represent the gems in Bejeweled.
 * 
 * @author Bart van Oort
 */
public class Gem {
	
	private GemType type;
	
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
