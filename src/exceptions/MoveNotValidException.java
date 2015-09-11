package exceptions;

/**
 * Custom exception for when a move is not valid.
 * @author Bart van Oort
 *
 */
public class MoveNotValidException extends RuntimeException {
  
  /**
   * Default constructor.
   */
  public MoveNotValidException() { 
    super();
  }
  
  /**
   * Constructor with error message.
   * @param s error message
   */
  public MoveNotValidException(String s) { 
    super(s);
    System.out.println(s);
  }
  
  /**
   * Constructor with error message and throwable.
   * @param s error message
   * @param throwable throwable
   */
  public MoveNotValidException(String s, Throwable throwable) {
    super(s, throwable);
  }
  
  /**
   * Constructor with throwable.
   * @param throwable throwable
   */
  public MoveNotValidException(Throwable throwable) { 
    super(throwable); 
  }
}
