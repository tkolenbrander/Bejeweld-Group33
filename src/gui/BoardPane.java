package gui;

import java.util.ArrayList;

import exceptions.MoveNotValidException;
import game.Logger;
import board.Board;
import board.Cell;
import board.Gem;
import board.Position;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * Class that creates the board and passes it to the GUI.
 * 
 * @author Steven Meijer & Thomas Kolenbrander
 */
public class BoardPane {

	/**
	 * The main pane of this class.
	 */
	protected BorderPane borderPane;

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
	 * Initializes the board.
	 */
	public void initBoard() {
		Board board = GUI.getGame().getBoard();
		Cell[][] cells = board.getCells();

		for (int y = 0; y < cells.length; y++) {
			for (int x = 0; x < cells[y].length; x++) {
				Gem gem = cells[y][x].getGem();

				ImageView image = new ImageView();
				displayNormal(image, gem);
				image.setX(x * 65);
				image.setY(y * 65);
				image.setOnMousePressed(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						System.out.println(me.getSceneX() + ", " + (me.getSceneY() - 55));
						int x = (int) me.getSceneX() / 65;
						int y = (int) (me.getSceneY() - 55) / 65;
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
	 */
	public void clicked(int x, int y, ImageView view) {
		Board board = GUI.getGame().getBoard();
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
		  GUI.getGame().makeMove(p1, p2);			
		  result = true;
		  GUI.getgui().setError("");
		  GUI.getgui().setScore(GUI.getGame().getPlayer().getScore());
		  //refresh();
		} catch (MoveNotValidException e) {
		  GUI.getgui().setError(e.getMessage());
		}
		if (!GUI.getGame().inProgress()) {
		  // TODO: Game over
		  GUI.getgui().setError("Game over!");
		  Logger.close();
		}
		return result;
	}
	
	public void playSwapAnim(SequentialTransition swapAnim) {
	  swapAnim.play();
	}

	/**
	 * Updates the displayed board with the current board.
	 */
	public void refresh() {
		borderPane.getChildren().removeAll(borderPane.getChildren());
		GUI.getgui().setScore(GUI.getGame().getPlayer().getScore());
		initBoard();
	}
}
