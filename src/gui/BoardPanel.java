package gui;

import exceptions.MoveNotValidException;
import game.Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.color.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import board.Board;
import board.Cell;
import board.Gem;
import board.GemType;
import board.Position;

/**
 * This class will make sure the board is visible.
 * 
 * @author Thomas
 *
 */
public class BoardPanel extends JPanel implements MouseListener {

	/**
	 * List of all the labels for the gems.
	 */
	private JLabel[][] labels;

	/**
	 * This boolean is true if a gem has been selected.
	 */
	private boolean selected = false;

	/**
	 * The current selected gem, and its position.
	 */
	private Gem selectedGem;
	private Position selectedPosition;
	
	/**
	 * The current selected label.
	 */
	private JLabel selectedLabel;

	public BoardPanel() {
		super();
		setLayout(new GridLayout(Board.BOARDSIZE, Board.BOARDSIZE));
		initBoard();
		// TODO
	}

	/**
	 * Initializes the board.
	 */
	public void initBoard() {
		Board board = GUI.game.getBoard();
		Cell[][] cells = board.getCells();

		labels = new JLabel[Board.BOARDSIZE][Board.BOARDSIZE];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				Gem gem = cells[i][j].getGem();
				GemType type = gem.getType();
				// Default icon is blue, for the time being
				ImageIcon icon = new ImageIcon(
						"assets/textures/gems/gemBlue.jpg");

				switch (type) {
				case BLUE:
					icon = new ImageIcon("assets/textures/gems/gemBlue.jpg");
					break;
				case RED:
					icon = new ImageIcon("assets/textures/gems/gemRed.jpg");
					break;
				case GREEN:
					icon = new ImageIcon("assets/textures/gems/gemGreen.jpg");
					break;
				case YELLOW:
					icon = new ImageIcon("assets/textures/gems/gemYellow.jpg");
					break;
				case WHITE:
					icon = new ImageIcon("assets/textures/gems/gemWhite.jpg");
					break;
				case PURPLE:
					icon = new ImageIcon("assets/textures/gems/gemPurple.jpg");
					break;
				}
				JLabel label = new JLabel(icon);
				label.setOpaque(true);
				label.addMouseListener(this);
				labels[i][j] = label;
				add(label);
			}
		}
	}
	
	/**
	 * Refreshes the board after a move.
	 */
	public void refresh() {
		removeAll();
		System.out.println("Refreshing");
		initBoard();
		repaint();
	}

	/**
	 * This is what happens when a JLabel (aka gem) is clicked.
	 * @param clicked The label that is clicked.
	 */
	public void clicked(JLabel clicked) {
		// Find the gem that is clicked
		int x = 0;
		int y = 0;
		boolean stop = false;
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				if (clicked == labels[i][j]) {
					x = i;
					y = j;
					stop = true;
					break;
				}
			}
			if (stop) {
				break;
			}
		}

		Board board = GUI.game.getBoard();
		Cell[][] cells = board.getCells();
		Cell cell = cells[x][y];
		Gem gem = cell.getGem();
		GemType type = gem.getType();
		System.out.println("You clicked: " + type);
		if (!selected) {
			GUI.scorePanel.noError();
			displayClicked(clicked, gem);
			selected = true;
			selectedGem = gem;
			selectedPosition = new Position(x, y);
			selectedLabel = clicked;
		} else {
		    makeMove(new Position(x, y));
			displayUnClicked(selectedLabel, selectedGem);
			selected = false;
		}
	}

	/**
	 * Will display a red rectangle on a gem.
	 * @param clicked The JLabel that is clicked.
	 * @param gem The gem the JLabel displays.
	 */
	public void displayClicked(JLabel clicked, Gem gem) {
		GemType type = gem.getType();
		ImageIcon icon = null;

		switch (type) {
		case BLUE:
			icon = new ImageIcon("assets/textures/gems/gemBlueClicked.png");
			break;
		case RED:
			icon = new ImageIcon("assets/textures/gems/gemRedClicked.png");
			break;
		case GREEN:
			icon = new ImageIcon("assets/textures/gems/gemGreenClicked.png");
			break;
		case YELLOW:
			icon = new ImageIcon("assets/textures/gems/gemYellowClicked.png");
			break;
		case WHITE:
			icon = new ImageIcon("assets/textures/gems/gemWhiteClicked.png");
			break;
		case PURPLE:
			icon = new ImageIcon("assets/textures/gems/gemPurpleClicked.png");
			break;
		default:   // Default blue clicked
		  icon = new ImageIcon("assets/textures/gems/gemBlueClicked.png");
		  break;
		}
		clicked.setIcon(icon);
	}
	
	/**
	 * Removes the red rectangle on a gem
	 * @param clicked The JLabel that is clicked.
	 * @param gem The gem the JLabel displays.
	 */
	public void displayUnClicked(JLabel clicked, Gem gem) {
		GemType type = gem.getType();
		ImageIcon icon = null;

		switch (type) {
		case BLUE:
			icon = new ImageIcon("assets/textures/gems/gemBlue.jpg");
			break;
		case RED:
			icon = new ImageIcon("assets/textures/gems/gemRed.jpg");
			break;
		case GREEN:
			icon = new ImageIcon("assets/textures/gems/gemGreen.jpg");
			break;
		case YELLOW:
			icon = new ImageIcon("assets/textures/gems/gemYellow.jpg");
			break;
		case WHITE:
			icon = new ImageIcon("assets/textures/gems/gemWhite.jpg");
			break;
		case PURPLE:
			icon = new ImageIcon("assets/textures/gems/gemPurple.jpg");
			break;
		default: // Default icon is blue, for the time being
		  icon = new ImageIcon("assets/textures/gems/gemBlue.jpg");
		  break;
		}
		clicked.setIcon(icon);
	}
	
	public void makeMove(Position p2) {
	  try {
	    GUI.game.makeMove(p2.getX(), p2.getY(), selectedPosition.getX(), selectedPosition.getY());
	    //TODO Refresh the board after a move
	    //GUI.gui.refreshBoard();
	  }
	  catch (MoveNotValidException e) {
		GUI.scorePanel.setError(e.getMessage());
	  }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JLabel source = (JLabel) e.getSource();
		clicked(source);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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

}
