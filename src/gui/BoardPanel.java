package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.color.*;

import board.Board;
import board.Cell;
import board.Gem;
import board.GemType;

/**
 * This class will make sure the board is visible.
 * @author Thomas
 *
 */
public class BoardPanel extends JPanel{
	
	/**
	 * List of all the labels for the gems
	 */
	private JLabel[] labels;

	public BoardPanel() {
		super();
		setLayout(new GridLayout(8,8));
		refresh();	
		// TODO
	}
	
	/**
	 * Will refresh the board after a move.
	 */
	public void refresh() {
		Board board = GUI.game.getBoard();
		Cell[][] cells = board.getCells();
		
		labels = new JLabel[64];
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				Gem gem = cells[i][j].getGem();
				GemType type = gem.getType();
				//Default icon is blue, for the time being
				ImageIcon icon = new ImageIcon("assets/textures/gems/gemBlue.jpg");
				
				switch(type) {
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
				add(label);				
			}
		}
	}

}
