package board;

/**
 * Gem class to represent the gems in Bejeweled.
 * 
 * @author Bart van Oort
 */
public class Gem {
	
	private GemType type;
	
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
}
