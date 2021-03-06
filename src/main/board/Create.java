package main.board;

import main.board.gems.Gem;


/**
 * Represents the creation of a new Gem.
 * 
 * @author Ruben Vrolijk
 * @param <K> The state.
 */
public class Create<K> extends Change<K> {
	
	/**
	 * The gem to create.
	 */
	private Gem g;

	/**
	 * Creates a Create object.
	 * 
	 * @param t State of the created Gem after creation.
	 * @param g The created Gem.
	 */
	public Create(K t, Gem g) {
		super(null, t);
		this.g = g;
	}
	
	/**
	 * Sets the created Gem.
	 * 
	 * @param g The created Gem.
	 */
	public void setG(Gem g) {
		this.g = g;
	}
	
	/**
	 * Gets the created Gem.
	 * 
	 * @return The created Gem.
	 */
	public Gem getGem() {
		return g;
	}
}
