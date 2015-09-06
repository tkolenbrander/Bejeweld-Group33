package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import board.Cell;
import board.Gem;

import org.junit.Before;
import org.junit.Test;

import board.Board;
/**
 * Test class that tests the Board class.
 * @author Wytze Elhorst
 *
 */
public class BoardTest {
  
  private Board board;
  
  @Before
  public void setUp() {
    board = new Board();
  }
  
  /**
   * Tests the falldown method with a single cell missing.
   */
	@Test
	@SuppressWarnings("magicnumber")
	public void falldownSingleChangeTest() {
	  Cell[][] cells = board.getCells();
	  Gem gem2 = cells[6][6].getGem();
	  Gem gem3 = cells[5][6].getGem();
	  Gem gem4 = cells[4][6].getGem();
	  Gem gem5 = cells[3][6].getGem();
	  Gem gem6 = cells[2][6].getGem();
	  Gem gem7 = cells[1][6].getGem();
	  Gem gem8 = cells[0][6].getGem();
	  
	  board.setCell(null, 6, 7);
	  board.falldown();
	  
	  assertNotNull(cells[7][6]);
	  assertEquals(gem2, cells[7][6].getGem());
	  assertEquals(gem3, cells[6][6].getGem());
	  assertEquals(gem4, cells[5][6].getGem());
	  assertEquals(gem5, cells[4][6].getGem());
	  assertEquals(gem6, cells[3][6].getGem());
	  assertEquals(gem7, cells[2][6].getGem());
	  assertEquals(gem8, cells[1][6].getGem());
	}
	
	/**
	 * Tests the falldown method with a vertical row of three cells missing.
	 */
	@Test
	@SuppressWarnings("magicnumber")
	public void falldownThreeVerticalTest() {
	  Cell[][] cells = board.getCells();
	  Gem gem4 = cells[4][7].getGem();
	  Gem gem5 = cells[3][7].getGem();
	  Gem gem6 = cells[2][7].getGem();
	  Gem gem7 = cells[1][7].getGem();
	  Gem gem8 = cells[0][7].getGem();
	  
	  board.setCell(null, 7, 7);
	  board.setCell(null, 7, 6);
	  board.setCell(null, 7, 5);
	  board.falldown();
	  
	  assertNotNull(cells[7][7]);
	  assertNotNull(cells[6][7]);
	  assertNotNull(cells[5][7]);
	  assertEquals(gem4, cells[7][7].getGem());
	  assertEquals(gem5, cells[6][7].getGem());
	  assertEquals(gem6, cells[5][7].getGem());
	  assertEquals(gem7, cells[4][7].getGem());
	  assertEquals(gem8, cells[3][7].getGem());
	}
}
