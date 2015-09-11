package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import board.Cell;
import board.Gem;
import board.GemType;

/**
 * Tests the Cell class. Not really much to test there though...
 * @author Bart van Oort
 *
 */
public class CellTest {

  /**
   * Tests the isEmpty method of Cell.
   */
  @Test
  public void testCell() {
    assertTrue((new Cell()).isEmpty());
  }
  
  /**
   * Tests the constructor for a filled cell.
   */
  @Test
  public void testCellFilled() {
    Cell cell = new Cell(new Gem(GemType.BLUE));
    assertTrue(cell.getGem().equals(new Gem(GemType.BLUE)));
  }

}
