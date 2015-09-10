package tests;

import static org.junit.Assert.assertEquals;
import board.Cell;
import board.Gem;
import board.GemType;
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
  /**
   * Sets up a random board for testing purposes.
   */
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
	  Gem bl = new Gem(GemType.BLUE);
	  Gem wh = new Gem(GemType.WHITE);
	  Gem gr = new Gem(GemType.GREEN);
	  Gem pu = new Gem(GemType.PURPLE);
	  Gem or = new Gem(GemType.ORANGE);
	  Cell b = new Cell(bl);
	  Cell w = new Cell(wh);
	  Cell g = new Cell(gr);
	  Cell p = new Cell(pu);
	  Cell o = new Cell(or);
	  Cell[][] cells = new Cell[][] {{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			  {b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			  {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
	  Board board = new Board(cells);
	  board.falldown();
	  cells = board.getCells();
	  Cell[][] cells2 = new Cell[][] {{b, w, b, w, b, w, b, null}, {g, p, g, p, g, p, g, w},
			  {b, w, b, w, b, w, b, p}, {g, p, g, p, g, p, g, w}, {b, w, b, w, b, w, b, p},
			  {g, p, g, p, g, p, g, w}, {b, w, b, w, b, w, b, p}, {g, p, g, p, g, p, g, o}};
	  Board board2 = new Board(cells2);
	  assertEquals(board2, board);
  }
	
	/**
	 * Tests the falldown method with a vertical row of three cells missing.
	 */
	@Test
	@SuppressWarnings("magicnumber")
	public void falldownThreeVerticalTest() {
		  Gem bl = new Gem(GemType.BLUE);
		  Gem wh = new Gem(GemType.WHITE);
		  Gem gr = new Gem(GemType.GREEN);
		  Gem pu = new Gem(GemType.PURPLE);
		  Cell b = new Cell(bl);
		  Cell w = new Cell(wh);
		  Cell g = new Cell(gr);
		  Cell p = new Cell(pu);
		  Cell[][] cells = new Cell[][] { {b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
				  {b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				  {g, p, g, p, g, p, g, null}, {b, w, b, w, b, w, b, null},
				  {g, p, g, p, g, p, g, null}};
		  Board board = new Board(cells);
		  board.falldown();
		  cells = board.getCells();
		  Cell[][] cells2 = new Cell[][] {{b, w, b, w, b, w, b, null}, {g, p, g, p, g, p, g, null},
				  {b, w, b, w, b, w, b, null}, {g, p, g, p, g, p, g, w}, {b, w, b, w, b, w, b, p},
				  {g, p, g, p, g, p, g, w}, {b, w, b, w, b, w, b, p}, {g, p, g, p, g, p, g, w}};
		  Board board2 = new Board(cells2);
		  assertEquals(board2, board);
	  }
}