package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import main.board.Position;

import org.junit.Test;

public class PositionTest {

  /**
   * Tests the isAdjacentTo method with a true outcome.
   */
  @Test
  public void isAdjacentTest_True() {
    assertTrue((new Position(0, 0)).isAdjacentTo(new Position(0, 1)));

  }

  /**
   * Tests the isAdjacentTo method with a false outcome, where the points are on the same height.
   */
  @Test
  public void isAdjacentTest_False_SameHeight() {
    assertFalse((new Position(0, 0)).isAdjacentTo(new Position(0, 2)));

  }

  /**
   * Tests the isAdjacentTo method with a false outcome, where the points are diagonally positioned.
   */
  @Test
  public void isAdjacentTest_False_Diagonal() {
    assertFalse((new Position(0, 0)).isAdjacentTo(new Position(1, 1)));

  }
}
