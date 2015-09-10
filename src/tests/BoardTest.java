package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import board.Cell;
import board.Direction;
import board.Gem;
import board.GemType;
import board.Position;

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
	 * tests the GetChainAtTest method.
	 */
	@Test
	public void getChainAtTest() {
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
		List<Position> list = board.getChainAt(0, 0, Direction.EAST);
		List<Position> list2 = new ArrayList<Position>();
		Position pos1 = new Position(0, 0);
		Position pos2 = new Position(1, 0);
		Position pos3 = new Position(2, 0);
		list2.add(pos1);
		list2.add(pos2);
		list2.add(pos3);
		assertEquals(list, list2);
	}
	/**
	 * tests the chainedCells method.
	 */
	@Test
	public void chainedCellsTest() {
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
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, g, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
		Board board = new Board(cells);
		List<List<Position>> chains = board.chainedCells();
		List<List<Position>> chains2 = new ArrayList<List<Position>>();
		chains2.add(board.getChainAt(0, 0, Direction.EAST));
		chains2.add(board.getChainAt(0, 1, Direction.EAST));
		assertEquals(chains, chains2);
			
	}
	/**
	 * tests the removeChains method.
	 */
	@Test
	public void removeChains() {
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
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, g, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
		Board board = new Board(cells);
		board.removeChains();
		Cell[][] cells2 = new Cell[][] {{null, null, null, w, b, w, b, w}, 
				{null, null, null, p, g, p, g, p}, {b, w, b, w, b, w, b, w}, 
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
		Board board2 = new Board(cells2);
		assertEquals(board2, board);
	}
	
	/**
	 * tests the fillEmptyCells method.
	 */
	@Test
	public void fillEmptyCells() {
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
		Cell[][] cells = new Cell[][] {{b, null, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
		Board board = new Board(cells);
		board.fillEmptyCells();
		assertFalse(board.getCells()[0][1] == null);
	}
	/**
	 * tests the hasChain method.
	 */
	@Test
	public void hasChainTest() {
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
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
		Board board = new Board(cells);
		assertTrue(board.hasChain());
	}
	/**
	 * tests the checkMoves method with a positive outcome.
	 */
	@Test
	public void checkmovesTest() {
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
		Cell[][] cells = new Cell[][] {{b, w, b, w, b, w, b, w}, {g, b, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
		Board board = new Board(cells);
		assertTrue(board.checkMoves());
	}
	/**
	 * tests the checkMoves method with a negative outcome.
	 */
	@Test
	public void badCheckmovesTest() {
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
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
		Board board = new Board(cells);
		assertFalse(board.checkMoves());
	}
	/**
	 * tests the getNeightbourAt method.
	 */
	@Test
	public void getNeighbourAtTest() {
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
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
		Board board = new Board(cells);
		Cell cell = board.getNeighbourAt(0, 0, Direction.EAST);
		assertEquals(board.getCells()[0][1], cell);
	}
	/**
	 * tests the getNeightboursOf method.
	 */
	@Test
	public void getNeighboursOfTest() {
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
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
		Board board = new Board(cells);
		List<Cell> cell = board.getNeighboursOf(1, 1);
		List<Cell> cell2 = new ArrayList<Cell>();
		cell2.add(board.getNeighbourAt(1, 1, Direction.NORTH));
		cell2.add(board.getNeighbourAt(1, 1, Direction.EAST));
		cell2.add(board.getNeighbourAt(1, 1, Direction.SOUTH));
		cell2.add(board.getNeighbourAt(1, 1, Direction.WEST));
		assertEquals(cell2, cell);
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
	 * tests the calculateScore method.
	 */
	@Test
	@SuppressWarnings("magicnumber")
	public void calculateScoreTest() {
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
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, b, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
		Board board = new Board(cells);
		assertEquals(50, board.calculateScore(0));
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