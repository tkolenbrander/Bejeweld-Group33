package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.board.Board;
import main.board.BoardFactory;
import main.board.Cell;
import main.board.Direction;
import main.board.Position;
import main.board.gems.Gem;
import main.board.gems.GemType;
import main.board.gems.RegularGem;

/**
 * Test class that tests the Board class.
 * 
 * @author Wytze Elhorst
 */
@SuppressWarnings("magicnumber") //Acceptable for test classes
public class BoardTest {

	private Board board;
	private Board board2;
	private BoardFactory boardFactory;
	private Cell[][] cells;
	private Cell[][] cells2;
	private Cell b;
	private Cell w;
	private Cell g;
	private Cell p;
	private Cell o;

	/**
	 * Sets up a random board for testing purposes.
	 */
	@Before
	public void setUp() {
		boardFactory = new BoardFactory();
		Gem bl = new RegularGem(GemType.BLUE);
		Gem wh = new RegularGem(GemType.WHITE);
		Gem gr = new RegularGem(GemType.GREEN);
		Gem pu = new RegularGem(GemType.PURPLE);
		Gem or = new RegularGem(GemType.ORANGE);
		b = new Cell(bl);
		w = new Cell(wh);
		g = new Cell(gr);
		p = new Cell(pu);
		o = new Cell(or);
		cells = new Cell[][] {{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, o}};
			board = boardFactory.generateBoard(cells);
			cells2 = new Cell[][] {{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
				{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, o}};
				board2 = boardFactory.generateBoard(cells2);
	}

	/**
	 * Tests if the equal method for boards works correctly for true.
	 */
	@Test
	public void equalsTest_True() {
		assertTrue(board.equals(board2));
	}

	/**
	 * Tests if the equal method for boards works correctly for false.
	 */
	@Test
	public void equalsTest_False() {
		cells = new Cell[][] {{o, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, o}};
			board = boardFactory.generateBoard(cells);
			assertFalse(board.equals(board2));
	}

	/**
	 * Tests the getCells method.
	 */
	@Test
	public void getCellsTest()  {
		Cell[][] cells2 = board.getCells();
		assertTrue(Arrays.deepEquals(cells, cells2)); 
	}

	/**
	 * Tests the setCell method.
	 */
	@Test
	public void setCellTest() {
		Gem gem = new RegularGem(GemType.GREEN);
		Cell cell = new Cell(gem);
		board.setCell(cell, 0, 0);
		assertTrue(cell.equals(board.getCells()[0][0]));
	}

	/**
	 * Tests the isTripletInDir method with a true outcome horizontally.
	 */
	@Test
	public void isTripletInDirTest_True_Horizontal() {
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
			Board board = boardFactory.generateBoard(cells);
			assertTrue(board.isTripletInDir(0, 0, Direction.EAST));
	}

	/**
	 * Tests the isTripletInDir method with a true outcome vertically.
	 */
	@Test
	public void isTripletInDirTest_True_Vertical() {
		Cell[][] cells = new Cell[][] {{b, w, b, w, b, w, b, w}, {b, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
			Board board = boardFactory.generateBoard(cells);
			assertTrue(board.isTripletInDir(0, 0, Direction.SOUTH));
	}

	/**
	 * Tests the isTripletInDir method with a false outcome.
	 */
	@Test
	public void isTripleInDirTest_False() {
		assertFalse(board.isTripletInDir(0, 0, Direction.EAST));
	}

	/**
	 * Tests the isTripletInDir method with a false outcome where the check goes out of bounds.
	 */
	@Test
	public void isTripleInDirTest_False_OOB() {
		assertFalse(board.isTripletInDir(0, 0, Direction.NORTH));
	}

	/**
	 * Ttests the GetChainAtTest method with a chain of 3 gems.
	 */
	@Test
	public void getChainAtTest_3() {
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
			Board board = boardFactory.generateBoard(cells);
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
	 * Tests the GetChainAtTest method with a chain of 0 gems.
	 */
	@Test
	public void getChainAtTest_Empty() {
		List<Position> list = board.getChainAt(0, 0, Direction.EAST);
		List<Position> list2 = new ArrayList<Position>();
		assertEquals(list, list2);
	}

	/**
	 * Tests the GetChainAtTest method with a chain of 4 gems.
	 */
	@Test
	public void getChainAtTest_4() {
		Cell[][] cells = new Cell[][] {{b, b, b, b, o, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
			Board board = boardFactory.generateBoard(cells);
			List<Position> list = board.getChainAt(0, 0, Direction.EAST);
			List<Position> list2 = new ArrayList<Position>();
			list2.add(new Position(0, 0));
			list2.add(new Position(1, 0));
			list2.add(new Position(2, 0));
			list2.add(new Position(3, 0));
			assertEquals(list, list2);
	}

	/**
	 * Tests the GetChainAtTest method with a chain of 5 gems.
	 */
	@Test
	public void getChainAtTest_5() {
		Cell[][] cells = new Cell[][] {{b, b, b, b, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
			Board board = boardFactory.generateBoard(cells);
			List<Position> list = board.getChainAt(0, 0, Direction.EAST);
			List<Position> list2 = new ArrayList<Position>();
			list2.add(new Position(0, 0));
			list2.add(new Position(1, 0));
			list2.add(new Position(2, 0));
			list2.add(new Position(3, 0));
			list2.add(new Position(4, 0));
			assertEquals(list, list2);
	}

	/**
	 * Tests the chainedCells method.
	 */
	@Test
	public void chainedCellsTest() {
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{g, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			List<List<Position>> chains = board.chainedCells();
			List<List<Position>> chains2 = new ArrayList<List<Position>>();
			chains2.add(board.getChainAt(0, 0, Direction.EAST));
			chains2.add(board.getChainAt(0, 1, Direction.SOUTH));
			assertEquals(chains, chains2);
	}

	/**
	 * Tests the chainedCells method when there are no chains.
	 */
	@Test
	public void chainedCellsTest_Empty() {
		List<List<Position>> chains = board.chainedCells();
		List<List<Position>> chains2 = new ArrayList<List<Position>>();
		assertEquals(chains, chains2);
	}

	/**
	 * Tests the removeChains method.
	 */
	@Test
	public void removeChains() {
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, g, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			board.removeChains();
			Cell[][] cells2 = new Cell[][] {{null, null, null, w, b, w, b, w}, 
				{null, null, null, p, g, p, g, p}, {b, w, b, w, b, w, b, w}, 
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
				{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
				Board board2 = boardFactory.generateBoard(cells2);
				assertEquals(board2, board);
	}

	/**
	 * Tests the fillEmptyCells method.
	 */
	@Test
	public void fillEmptyCells() {
		Cell[][] cells = new Cell[][] {{null, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			board.fillEmptyCells();
			assertFalse(board.getCells()[0][0] == null);
	}

	/**
	 * Tests the hasChain method when the board has a chain.
	 */
	@Test
	public void hasChainTest_True() {
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			assertTrue(board.hasChain());
	}

	/**
	 * Tests the hasChain method when the board does not have a chain.
	 */
	@Test
	public void hasChainTest_False() {
		assertFalse(board.hasChain());
	}

	/**
	 * Tests the checkMoves method when there is a possible move.
	 */
	@Test
	public void checkmovesTest_True() {
		Cell[][] cells = new Cell[][] {{b, w, b, w, b, w, b, w}, {g, b, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			assertTrue(board.checkMoves());
	}

	/**
	 * Tests the checkMoves method when there is no possible move.
	 */
	@Test
	public void checkmovesTest_False() {
		assertFalse(board.checkMoves());
	}

	/**
	 * Tests the getNeightbourAt method.
	 */
	@Test
	public void getNeighbourAtTest() {
		Cell cell = board.getNeighbourAt(0, 0, Direction.EAST);
		assertTrue(cell.equals(w));
	}

	/**
	 * Tests the getNeightbourAt method when returning a neighbor out of bounds.
	 */
	@Test
	public void getNeighbourAtTest_Null() {
		Cell cell = board.getNeighbourAt(0, 0, Direction.NORTH);
		assertTrue(cell == null);
	}

	/**
	 * Tests the getNeightboursOf method.
	 */
	@Test
	public void getNeighboursOfTest() {
		Cell[][] cells = new Cell[][] {{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			List<Cell> cell = board.getNeighboursOf(1, 1);
			List<Cell> cell2 = new ArrayList<Cell>();
			cell2.add(board.getNeighbourAt(1, 1, Direction.NORTH));
			cell2.add(board.getNeighbourAt(1, 1, Direction.EAST));
			cell2.add(board.getNeighbourAt(1, 1, Direction.SOUTH));
			cell2.add(board.getNeighbourAt(1, 1, Direction.WEST));
			assertEquals(cell2, cell);
	}

	/**
	 * Tests the swap method.
	 */
	@Test
	public void swapTest() {
		board.swap(0, 0, 1, 0);
		Cell[][] cells2 = new Cell[][] {{w, b, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, o}};
			Board board2 = boardFactory.generateBoard(cells2);
			assertEquals(board2, board);
	}

	/**
	 * Tests the calculateScore method with a chain of 3 cells.
	 */
	@Test
	public void calculateScoreTest_3() {
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, b, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			assertEquals(50, board.calculateScore(0));
	}

	/**
	 * Tests the calculateScore method with a chain of 4 cells.
	 */
	@Test
	public void calculateScoreTest_4() {
		Cell[][] cells = new Cell[][] {{b, b, b, b, o, w, b, w}, {g, b, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			assertEquals(100, board.calculateScore(0));
	}

	/**
	 * Tests the calculateScore method with a chain of 5 cells.
	 */
	@Test
	public void calculateScoreTest_5() {
		Cell[][] cells = new Cell[][] {{b, b, b, b, b, w, b, w}, {g, b, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			assertEquals(500, board.calculateScore(0));
	}

	/**
	 * Tests the calculateScore method with a chain of 3 cells and a bonus.
	 */
	@Test
	public void calculateScoreTest_3_bonus() {
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, b, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			assertEquals(100, board.calculateScore(1));
	}

	/**
	 * Tests the calculateScore method with two chains.
	 */
	@Test
	public void calculateScoreTest_twoChains() {
		Cell[][] cells = new Cell[][] {{b, b, b, w, b, w, b, w}, {g, g, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, p}};
			Board board = boardFactory.generateBoard(cells);
			assertEquals(100, board.calculateScore(0));
	}

	/**
	 * Tests the calculateScore method with no chains.
	 */
	@Test
	public void calculateScoreTest_0() {
		assertEquals(0, board.calculateScore(0));
	}

	/**
	 * Tests the falldown method with a single cell missing.
	 */
	@Test
	public void falldownTest_Single() {
		Cell[][] cells = new Cell[][] {{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, o}, {g, p, g, p, g, p, g, null}};
			Board board = boardFactory.generateBoard(cells);
			board.falldown();

			Cell[][] cells2 = new Cell[][] {{b, w, b, w, b, w, b, null}, {g, p, g, p, g, p, g, w},
				{b, w, b, w, b, w, b, p}, {g, p, g, p, g, p, g, w}, {b, w, b, w, b, w, b, p},
				{g, p, g, p, g, p, g, w}, {b, w, b, w, b, w, b, p}, {g, p, g, p, g, p, g, o}};
				Board board2 = boardFactory.generateBoard(cells2);
				assertEquals(board2, board);
	}

	/**
	 * Tests the falldown method with a vertical row of three cells missing.
	 */
	@Test
	public void falldownTest_Vertical() {
		Cell[][] cells = new Cell[][] {{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p},
			{b, w, b, w, b, w, b, w}, {g, p, g, p, g, p, g, p}, {b, w, b, w, b, w, b, w},
			{g, p, g, p, g, p, g, null}, {b, w, b, w, b, w, b, null},
			{g, p, g, p, g, p, g, null}};
			Board board = boardFactory.generateBoard(cells);
			board.falldown();

			Cell[][] cells2 = new Cell[][] {{b, w, b, w, b, w, b, null}, {g, p, g, p, g, p, g, null},
				{b, w, b, w, b, w, b, null}, {g, p, g, p, g, p, g, w}, {b, w, b, w, b, w, b, p},
				{g, p, g, p, g, p, g, w}, {b, w, b, w, b, w, b, p}, {g, p, g, p, g, p, g, w}};
				Board board2 = boardFactory.generateBoard(cells2);
				assertEquals(board2, board);
	}
}
