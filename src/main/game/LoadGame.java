package main.game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.board.Board;
import main.board.Cell;
import main.board.Position;
import main.board.gems.GemType;
import main.board.gems.PowerGem;
import main.board.gems.RegularGem;

/**
 * Utility class for loading the game from a saved game.
 * 
 * @author Ruben
 */
public final class LoadGame {

	private static final Object SYNC = new Object();
	private static final String PATH = "savefile.bjw";

	/**
	 * Since this is a utility class, we don't want this instantiated.
	 */
	private LoadGame() {
		throw new AssertionError("Instantiating utility class...");
	}

	/**
	 * Loads the game from the last saved game.
	 * @throws FileNotFoundException Which is handled in the GUI.
	 * @return a new Game with the saved score and Board.
	 */
	public static Game load() throws FileNotFoundException {
		synchronized (SYNC) {
			Cell[][] cells = new Cell[Board.BOARDSIZE][Board.BOARDSIZE];
			Scanner in = null;
			ClassicGame game = new ClassicGame();
			try {
				in = new Scanner(new FileReader(PATH));
				Player player = new Player(in.nextInt());
				int numOfPowerGems = in.nextInt();
				List<Position> powerPos = new ArrayList<Position>();
				
				for (int i = 0; i < numOfPowerGems; i++) {
					powerPos.add(new Position(in.nextInt(), in.nextInt()));
				}

				for (int y = 0; y < Board.BOARDSIZE; y++) {
					for (int x = 0; x < Board.BOARDSIZE; x++) {
						GemType type = GemType.valueOf(in.next());
						Position currPos = new Position(x, y);
						
						if (powerPos.contains(currPos)) {
							cells[y][x] = new Cell(new PowerGem(type));
						} else {
							cells[y][x] = new Cell(new RegularGem(type));
						}
					}
				}
				game = new ClassicGame(game.getBoardFactory().generateBoard(cells), player);
				return game;
			}
			
			finally {
				in.close();
			}
		}
	}
}
