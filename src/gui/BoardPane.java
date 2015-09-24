package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import board.Board;
import board.Cell;
import board.GemType;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Class that creates the board and passes it to the GUI.
 * 
 * @author Steven Meijer & Thomas Kolenbrander
 */
public class BoardPane implements MouseListener {

	private GridPane gridPane;

	public BoardPane() {
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
				GemType type = cells[y][x].getGem().getType();
				Image icon = new Image("file:assets/textures/gems/gemMissing.png"); //TODO: Fancy class for getting image icon

				switch (type) {
				case BLUE:
					icon = new Image("file:assets/textures/gems/gemBlue.png");
					break;
				case RED:
					icon = new Image("file:assets/textures/gems/gemRed.png");
					break;
				case GREEN:
					icon = new Image("file:assets/textures/gems/gemGreen.png");
					break;
				case YELLOW:
					icon = new Image("file:assets/textures/gems/gemYellow.png");
					break;
				case WHITE:
					icon = new Image("file:assets/textures/gems/gemWhite.png");
					break;
				case PURPLE:
					icon = new Image("file:assets/textures/gems/gemPurple.png");
					break;
				case ORANGE:
					icon = new Image("file:assets/textures/gems/gemOrange.png");
					break;
				}
				
				ImageView image = new ImageView(icon);
				//gridPane.add(image, y, x);
			}
		}
	}
	
	/**
	 * @return The boardPane
	 */
	public GridPane getBoardPane() {
		return gridPane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
