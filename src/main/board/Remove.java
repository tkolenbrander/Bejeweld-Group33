package main.board;

/**
 * Represents removing an object from its given state.
 * 
 * @author Ruben Vrolijk
 * @param <K> The state.
 */
public class Remove<K> extends Change<K> {

	/**
	 * Creates a Remove object from a given state.
	 * 
	 * @param f Te state to remove an object from
	 */
	public Remove(K f) {
		super(f, null);
	}
}
