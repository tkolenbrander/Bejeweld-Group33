package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import board.Cell;
import board.Direction;
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
	private Cell[][] cells;
	/**
	 * Sets up a random board for testing purposes.
	 */
	@Before
	public void setUp() {
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
		cells = new Cell[][] {{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
		board = new Board(cells);
	}

	/**
	 * tests the getCells method.
	 */
	@Test
	public void getCellsTest()  {
		Cell[][] cells2 = board.getCells();
		assertTrue(Arrays.deepEquals(cells, cells2)); 
	}

	/**
	 * tests the reset method.
	 */
	@Test
	public void resetTest() {
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
		Cell[][] cells2 = new Cell[][] {{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
		Board board2 = new Board(cells2);
		board2.reset();
		assertFalse(board.equals(board2));

	}

	/**
	 * tests the setCell method.
	 */
	@Test
	public void setCellTest() {
		Gem gem = new Gem(GemType.GREEN);
		Cell cell = new Cell(gem);
		board.setCell(cell, 0, 0);
		assertEquals(cell, board.getCells()[0][0]);
	}

	/**
	 * tests the isAdjacent method with a good outcome.
	 */
	@Test
	public void isAdjacentTest() {
		assertTrue(board.isAdjacent(0, 0, 0, 1));

	}

	/**
	 * tests the isAdjacent method with a bad outcome.
	 */
	@Test
	public void badisAdjacentTest() {
		assertFalse(board.isAdjacent(0, 0, 0, 2));

	}
	
	/**
	 * Tests the istripleat method with a good outcome.
	 */
	@Test
	public void badisTripleAtTest() {
		assertFalse(board.isTripletAt(0, 0));
	}
	
	/**
	 * Tests the istripleat method with a bad outcome.
	 */
	@Test
	public void isTripleAtTest() {
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
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
		Board board = new Board(cells);
		assertTrue(board.isTripletAt(0, 0));
	}
	
	/**
	 * tests the isTripleInDir method with a good outcome.
	 */
	@Test
	public void isTripleInDirTest() {
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
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
		Board board = new Board(cells);
		assertTrue(board.isTripletInDir(0, 0, Direction.EAST));
	}
	
	/**
	 * tests the isTripleInDir method with a bad outcome.
	 */
	@Test
	public void badisTripleInDirTest() {
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
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
		Board board = new Board(cells);
		assertFalse(board.isTripletInDir(0, 0, Direction.NORTH));
	}
	/**
	 * tests the swap method.
	 */
	@Test
	public void swapTest() {
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
		board.swap(0, 0, 0, 1);
		Cell[][] cells2 = new Cell[][] {{g, w, b, w, b, w, b, w}, {b, p, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
		Board board2 = new Board(cells2);
		assertEquals(board2, board);
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