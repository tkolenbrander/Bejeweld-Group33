package main.board;

/**
 * Represents a change of an object, with its begin state and its
 * 	end state.
 * 
 * @author Bart van Oort
 * @param <K> The state.
 */
public class Change<K> {

	/**
	 * Begin state of the change.
	 */
	private K from;

	/**
	 * End state of the change.
	 */
	private K to;

	/**
	 * Creates a Change object.
	 * 
	 * @param f State of the object before the change.
	 * @param t State of the object after the change.
	 */
	public Change(K f, K t) {
		from = f;
		to = t;
	}

	/**
	 * @return The state of the object before the change.
	 */
	public K getFrom() {
		return from;
	}

	/**
	 * @return The state of the object after the change.
	 */
	public K getTo() {
		return to;
	}

	/**
	 * Sets the state of the object before the change.
	 * 
	 * @param f The state of the object before the change.
	 */
	public void setFrom(K f) {
		from = f;
	}

	/**
	 * Sets the state of the object after the change.
	 * 
	 * @param t The state of the object after the change.
	 */
	public void setTo(K t) {
		to = t;
	}
}
