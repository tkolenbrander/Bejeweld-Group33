package board;

public class Board {

  private static final int BOARDSIZE = 8;
	private Cell[][] board;

	public Board() {
		board = new Cell[BOARDSIZE][BOARDSIZE];
	}
}
