package gui;

import exceptions.MoveNotValidException;
import board.Board;
import board.Cell;
import board.Gem;
import board.Position;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

/**
 * Class that creates the board and passes it to the GUI.
 * 
 * @author Steven Meijer & Thomas Kolenbrander
 */
public class BoardPane {

	/**
	 * The main pane of this class
	 */
	private GridPane gridPane;

	/**
	 * Contains all the imageviews of the sprites.
	 */
	private ImageView[][] imageviews;

	/**
	 * The current selected view.
	 */
	private ImageView selectedView;

	/**
	 * The current selected gem.
	 */
	private Gem selectedGem;

	/**
	 * The current selected position.
	 */
	private Position selectedPosition;

	/**
	 * True if a gem is selected.
	 */
	private boolean selected = false;

	public BoardPane() {
		gridPane = new GridPane();
		imageviews = new ImageView[Board.BOARDSIZE][Board.BOARDSIZE];
		initBoard();		
	}

	/**
	 * Initializes the board.
	 */
	public void initBoard() {
		Board board = GUI.game.getBoard();
		Cell[][] cells = board.getCells();

		for (int y = 0; y < cells.length; y++) {
			for (int x = 0; x < cells[y].length; x++) {
				Gem gem = cells[y][x].getGem();

				ImageView image = new ImageView();
				displayNormal(image, gem);

				image.setOnMousePressed(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						int y = gridPane.getRowIndex((Node) me.getSource());
						int x = gridPane.getColumnIndex((Node) me.getSource());
						clicked(x, y);
					}
				});
				imageviews[x][y] = image;
				gridPane.add(image, x, y);
			}
		}
	}

	/**
	 * @return The boardPane
	 */
	public GridPane getBoardPane() {
		return gridPane;
	}

	/**
	 * Handles the event when a gem is clicked.
	 * 
	 * @param x
	 *            The x coordinate of the gem.
	 * @param y
	 *            The y coordinate of the gem.
	 */
	public void clicked(int x, int y) {
		Board board = GUI.game.getBoard();
		Cell[][] cells = board.getCells();
		Gem gem = cells[y][x].getGem();

		ImageView view = imageviews[x][y];
		System.out.println("Selected: " + gem.getType());

		if (selected) {			
			makeMove(new Position(x, y), selectedPosition);
			displayNormal(selectedView, selectedGem);
			selected = false;			
		} else {
			selectedView = view;
			selectedGem = gem;
			selectedPosition = new Position(x, y);
			displayClicked(view, gem);
			selected = true;
		}
	}

	/**
	 * Changes the sprite of the gem to the 'clicked' sprite.
	 * 
	 * @param view
	 *            The ImageView to add the sprite to.
	 * @param gem
	 *            The gem whose 'clicked' sprite shall be added.
	 */
	public void displayClicked(ImageView view, Gem gem) {
		view.setImage(gem.getImageClicked());
	}

	/**
	 * Adds the sprite of a gem to an ImageView.
	 * 
	 * @param view
	 *            The ImageView to add the sprite to.
	 * @param gem
	 *            The gem whose sprite shall be added.
	 */
	public void displayNormal(ImageView view, Gem gem) {
		view.setImage(gem.getImage());
	}

	/**
	 * Tries to make a move between two positions. True if succesful.
	 * 
	 * @param p1
	 *            The first position.
	 * @param p2
	 *            The second position.
	 */
	public boolean makeMove(Position p1, Position p2) {
		boolean result = false;
		
		try {
			GUI.game.makeMove(p1.getX(), p1.getY(), p2.getX(), p2.getY());			
			result = true;
			System.out.println("move made");
			refresh();
		} catch (MoveNotValidException e) {
			// TODO: Display error
		}
		if (!GUI.game.inProgress()) {
			// TODO: Game over
		}
		return result;
	}

	/**
	 * Updates the displayed board with the current board.
	 */
	public void refresh() {
		gridPane.getChildren().removeAll(gridPane.getChildren());
		initBoard();
	}
}
