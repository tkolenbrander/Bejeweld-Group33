package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

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
	
	/**
	 * This label will display an error, for example when a move is not possible.
	 */
	private JLabel errorLabel;
	
	public ScorePanel() {
		super();
		setLayout(new GridLayout(2, 1));
		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		errorLabel = new JLabel();
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		errorLabel.setHorizontalAlignment(JLabel.CENTER);
		add(scoreLabel);
		add(errorLabel);
	}
	
	/**
	 * This function updates the score of a player.
	 */
	public void setScore(int score) {
		String text = "Score: " + score;
		scoreLabel.setText(text);
	}

	/**
	 * This function sets the text to display an error.
	 * @param error The text of this error.
	 */
	public void setError(String error) {
		errorLabel.setText(error);
	}
	
	/**
	 * The error that is displayed will be removed.
	 */
	public void noError() {
		errorLabel.setText("");
	}
}
