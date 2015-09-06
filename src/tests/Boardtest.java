package tests;

import static org.junit.Assert.assertEquals;

import board.Cell;
import board.Gem;

import org.junit.Test;

import board.Board;
/**
 * testclass that tests the board class.
 * @author Gebruiker
 *
 */
public class Boardtest {
/**
 * tests the falldown method with a single cell missing.
 */
	@Test
	@SuppressWarnings("magicnumber")
	public void falldownsinglechangetest() {
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
	Gem gem1 = null;
	if (cells[6][7] != null) {
	gem1 = cells[6][7].getGem();
	}
	assertEquals(gem2, gem1);
	gem2 = cells[6][6].getGem();
	assertEquals(gem3, gem2);
	gem3 = cells[6][5].getGem();
	assertEquals(gem4, gem3);
	gem4 = cells[6][4].getGem();
	assertEquals(gem5, gem4);
	gem5 = cells[6][3].getGem();
	assertEquals(gem6, gem5);
	gem6 = cells[6][2].getGem();
	assertEquals(gem7, gem6);
	gem7 = cells[6][1].getGem();
	assertEquals(gem8, gem7);
	}
	
	/**
	 * tests the falldown method with a vertical row of 3 cells missing.
	 */
	@Test
	@SuppressWarnings("magicnumber")
	public void falldown3verticaltest() {
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
	Gem gem1 = null;
	Gem gem2 = null;
	Gem gem3 = null;
	if (cells[7][7] != null) {
		gem1 = cells[7][7].getGem(); }
	if (cells[7][6] != null) {
		gem2 = cells[7][6].getGem(); }
	if (cells[7][5] != null) {
		gem3 = cells[7][5].getGem(); }
	assertEquals(gem4, gem1);
	assertEquals(gem5, gem2);
	assertEquals(gem6, gem3);
	gem4 = cells[7][4].getGem();
	gem5 = cells[7][3].getGem();
	assertEquals(gem7, gem4);
	assertEquals(gem8, gem5);
	}
}
