package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import board.Cell;
import board.Gem;
import board.GemType;
import board.RegularGem;

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
    Cell cell = new Cell(new RegularGem(GemType.BLUE));
    assertTrue(cell.getGem().equals(new RegularGem(GemType.BLUE)));
  }

}
