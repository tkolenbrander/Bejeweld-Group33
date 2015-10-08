package board;

/**
 * Represents a change of an object, with its begin state and its
 * end state.
 * @author Bart
 *
 * @param <K>
 */
public class Change<K> {

  private K from;
  private K to;
  
  /**
   * Creates a Change object.
   * @param f State of the object before the change.
   * @param t State of the object after the change.
   */
  public Change(K f, K t) {
    from = f;
    to = t;
  }
  
  /**
   * Returns the state of the object before the change.
   * @return the state of the object before the change.
   */
  public K getFrom() {
    return from;
  }
  
  /**
   * Returns the state of the object after the change.
   * @return the state of the object after the change.
   */
  public K getTo() {
    return to;
  }
  
  /**
   * Sets the state of the object before the change.
   * @param f the state of the object before the change.
   */
  public void setFrom(K f) {
    from = f;
  }
  
  /**
   * Sets the state of the object after the change.
   * @param t the state of the object after the change.
   */
  public void setTo(K t) {
    to = t;
  }
}
