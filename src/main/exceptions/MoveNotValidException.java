package main.exceptions;

/**
 * Custom exception for when a move is not valid.
 * 
 * @author Bart van Oort
 */
public class MoveNotValidException extends RuntimeException {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -2552398648393159002L;

	/**
	 * Default constructor.
	 */
	public MoveNotValidException() { 
		super();
	}

	/**
	 * Constructor with error message.
	 * 
	 * @param s The error message
	 */
	public MoveNotValidException(String s) { 
		super(s);
	}

	/**
	 * Constructor with error message and throwable.
	 * 
	 * @param s The error message
	 * @param throwable Throwable
	 */
	public MoveNotValidException(String s, Throwable throwable) {
		super(s, throwable);
	}

	/**
	 * Constructor with throwable.
	 * 
	 * @param throwable Throwable
	 */
	public MoveNotValidException(Throwable throwable) { 
		super(throwable); 
	}
}
