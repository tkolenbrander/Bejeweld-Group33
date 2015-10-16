package board;

/**
 * Represents removing an object from its given state
 * @author Ruben
 *
 * @param <K>
 */
public class Remove<K> extends Change<K> {

	/**
	 * Creates a Remove object from a given state
	 * @param f the state to remove an object from
	 */
	public Remove(K f) {
		super(f, null);
	}

}
