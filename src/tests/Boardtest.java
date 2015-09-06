package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import board.Cell;
import board.Gem;

import org.junit.Test;

import board.Board;
/**
 * Test class that tests the Board class.
 * @author Wytze Elhorst
 *
 */
public class BoardTest {
  
  /**
   * Tests the falldown method with a single cell missing.
   */
	@Test
	@SuppressWarnings("magicnumber")
	public void falldownSingleChangeTest() {
	  Board board = new Board();
	  Cell[][] cells = board.getCells();
	  Gem gem2 = cells[6][6].getGem();
	  Gem gem3 = cells[6][5].getGem();
	  Gem gem4 = cells[6][4].getGem();
	  Gem gem5 = cells[6][3].getGem();
	  Gem gem6 = cells[6][2].getGem();
	  Gem gem7 = cells[6][1].getGem();
	  Gem gem8 = cells[6][0].getGem();
	  
	  board.setCell(null, 6, 7);
	  board.falldown();
	  
	  assertNotNull(cells[6][7]);
	  assertEquals(gem2, cells[6][7].getGem());
	  assertEquals(gem3, cells[6][6].getGem());
	  assertEquals(gem4, cells[6][5].getGem());
	  assertEquals(gem5, cells[6][4].getGem());
	  assertEquals(gem6, cells[6][3].getGem());
	  assertEquals(gem7, cells[6][2].getGem());
	  assertEquals(gem8, cells[6][1].getGem());
	}
	
	/**
	 * Tests the falldown method with a vertical row of three cells missing.
	 */
	@Test
	@SuppressWarnings("magicnumber")
	public void falldownThreeVerticalTest() {
	  Board board = new Board();
	  Cell[][] cells = board.getCells();
	  Gem gem4 = cells[7][4].getGem();
	  Gem gem5 = cells[7][3].getGem();
	  Gem gem6 = cells[7][2].getGem();
	  Gem gem7 = cells[7][1].getGem();
	  Gem gem8 = cells[7][0].getGem();
	  
	  board.setCell(null, 7, 7);
	  board.setCell(null, 7, 6);
	  board.setCell(null, 7, 5);
	  board.falldown();
	  
	  assertNotNull(cells[7][7]);
	  assertNotNull(cells[7][6]);
	  assertNotNull(cells[7][5]);
	  assertEquals(gem4, cells[7][7].getGem());
	  assertEquals(gem5, cells[7][6].getGem());
	  assertEquals(gem6, cells[7][5].getGem());
	  assertEquals(gem7, cells[7][4].getGem());
	  assertEquals(gem8, cells[7][3].getGem());
	}
}
