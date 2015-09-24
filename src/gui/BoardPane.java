package gui;

import board.Board;
import board.Cell;
import board.Gem;
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

	private GridPane gridPane;
	
	private ImageView[][] imageviews;
	
	private ImageView selectedView;
	
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
				Image icon = cells[x][y].getGem().getImage(); 
				
				ImageView image = new ImageView(icon);
				
				image.setOnMousePressed(new EventHandler<MouseEvent>() {
				    public void handle(MouseEvent me) {	
				    	int x = gridPane.getRowIndex((Node) me.getSource());
				    	int y = gridPane.getColumnIndex((Node) me.getSource());
				        clicked(x, y);
				    }
				});
				imageviews[x][y] = image;
				gridPane.add(image, y, x);
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
	 * @param x The x coordinate of the gem.
	 * @param y The y coordinate of the gem.
	 */
	public void clicked(int x, int y) {
		Board board = GUI.game.getBoard();
		Cell[][] cells = board.getCells();
		Gem gem = cells[x][y].getGem();
		
		ImageView selected = imageviews[x][y];

		selected.setImage(new Image("file:assets/textures/gems/gemMissing.png"));
	}

}
