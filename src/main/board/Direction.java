package main.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Enumeration of possible 2D directions in a matrix.
 * 
 * @author Bart van Oort
 */
public enum Direction {
  NORTH (0, -1), 
  EAST (1, 0), 
  SOUTH (0, 1), 
  WEST (-1, 0);
  
  /**
   * List of all directions.
   */
  public static final List<Direction> DIRECTIONS = 
      Collections.unmodifiableList(Arrays.asList(values()));
  
  /**
   * Delta X for the direction.
   */
  private final int dx;
  
  /**
   * Delta Y for the direction.
   */
  private final int dy;
  
  /**
   * Directions to send you in the right direction. The coordinate system has (0,0) at the top left
   * and (BOARDSIZE, BOARDSIZE) at the bottom right.
   * @param x How many steps this direction takes you to the right.
   * @param y How many steps this direction takes you to the bottom.
   */
  private Direction(int x, int y) {
    dx = x;
    dy = y;
  }
  
  /**
   * Returns the delta x for this direction.
   * @return delta x for this direction.
   */
  public int getDX() {
    return dx;
  }
  
  /**
   * Returns the delta y for this direction.
   * @return delta y for this direction.
   */
  public int getDY() {
    return dy;
  }
}
