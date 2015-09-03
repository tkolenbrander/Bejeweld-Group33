package gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The panel which will contain the score can be generated in this class.
 * @author Thomas
 *
 */
public class ScorePanel extends JPanel {

	/**
	 * This label will display the score of the current player.
	 */
	private JLabel scoreLabel;
	
	public ScorePanel() {
		super();
		setLayout(new FlowLayout());
		JLabel textScoreLabel = new JLabel("Score: ");
		scoreLabel = new JLabel("0");
		add(textScoreLabel);
		add(scoreLabel);
	}
	
	/**
	 * When this is called the player's score will be updated.
	 */
	public void refresh() {
		
	}

}
