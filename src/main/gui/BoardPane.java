package main.gui;

import javafx.animation.SequentialTransition;
import main.exceptions.MoveNotValidException;
import main.game.Logger;
import main.board.Board;
import main.board.Cell;
import main.board.Gem;
import main.board.Position;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;

/**
 * Class that creates the board and passes it to the GUI.
 * 
 * @author Steven Meijer & Thomas Kolenbrander
 */
public class BoardPane {

	/**
	 * The main pane of this class.
	 */
	private BorderPane borderPane;

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

	/**
	 * Contains the animations.
	 */
	private TimelineController controller;

	/**
	 * Width and height of a pixel.
	 */
	private static final int SPRITE_SIZE = 65;

	/**
	 * True if an animation is playing, otherwise false.
	 */
	private boolean animating = false;

	/**
	 * Creates a new BoardPane.
	 */
	public BoardPane() {
		borderPane = new BorderPane();
		imageviews = new ImageView[Board.BOARDSIZE][Board.BOARDSIZE];
		controller = new TimelineController();
		initBoard();		
	}

	/**
	 * Returns borderPane.
	 * @return borderPane from the BoardPane.
	 */
	public BorderPane getPane() {
		return borderPane;
	}

	/**
	 * Initializes the board.
	 */
	public void initBoard() {
		Board board = GameViewController.getGame().getBoard();
		Cell[][] cells = board.getCells();

		for (int y = 0; y < cells.length; y++) {
			for (int x = 0; x < cells[y].length; x++) {
				Gem gem = cells[y][x].getGem();

				ImageView image = new ImageView();
				displayNormal(image, gem);
				image.setX(x * SPRITE_SIZE);
				image.setY(y * SPRITE_SIZE);
				image.setOnMousePressed(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						System.out.println(me.getSceneX() + ", " + (me.getSceneY() - 55));
						int x = (int) me.getSceneX() / SPRITE_SIZE;
						int y = (int) (me.getSceneY() - 55) / SPRITE_SIZE;
						if (!animating) {
							clicked(x, y, image);
						}
					}
				});
				imageviews[x][y] = image;
				borderPane.getChildren().add(image);
			}
		}
	}

	/**
	 * @return The boardPane
	 */
	public BorderPane getBoardPane() {
		return borderPane;
	}

	/**
	 * Returns the imageviews.
	 * @return the imageviews.
	 */
	public ImageView[][] getImageViews() {
		return imageviews;
	}

	/**
	 * Sets the imageviews.
	 * @param views The views
	 */
	public void setImageViews(ImageView[][] views) {
		imageviews = views;
	}

	/**
	 * Handles the event when a gem is clicked.
	 * 
	 * @param x
	 *            The x coordinate of the gem.
	 * @param y
	 *            The y coordinate of the gem.
	 * @param view
	 * 			  The imageview
	 */
	public void clicked(int x, int y, ImageView view) {
		Board board = GameViewController.getGame().getBoard();
		Cell[][] cells = board.getCells();
		Gem gem = cells[y][x].getGem();

		//ImageView view = imageviews[x][y];
		System.out.println("Selected: " + gem.getType());

		if (selected) {			
			makeMove(new Position(x, y), selectedPosition);
			SequentialTransition t = controller.getTimeline();
			t.setOnFinished(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					animating = false;
				}
			});
			
			animating = true;
			t.play();
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
	 * Tries to make a move between two positions. True if successful.
	 * 
	 * @param p1
	 *            The first position.
	 * @param p2
	 *            The second position.
	 * @return returns whether a move is possible
	 */
	public boolean makeMove(Position p1, Position p2) {
		boolean result = false;
		
		try {
			GameViewController.getGame().makeMove(p1, p2);			
			result = true;
		} catch (MoveNotValidException e) {
			//GameViewController.getGameViewController().setError(e.getMessage());
		}
		if (!GameViewController.getGame().inProgress()) {
			// TODO: Game over
			//GameViewController.getGameViewController().setError("Game over!");
			Logger.close();
		}
		return result;
	}

	/**
	 * Plays the swap animation.
	 * @param swapAnim The animation
	 */
	public void playSwapAnim(SequentialTransition swapAnim) {
		swapAnim.play();
	}

	/**
	 * Updates the displayed board with the current board.
	 */
	public void refresh() {
		borderPane.getChildren().removeAll(borderPane.getChildren());
		//GameViewController.getGameViewController().setScore(GameViewController.getGame().getPlayer().getScore());
		initBoard();
	}
}
