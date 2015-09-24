package gui;

import game.Game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The panel which will contain the score can be generated in this class.
 * 
 * @author Thomas
 *
 */
@Deprecated
public class ScorePanel extends JPanel {

	/**
	 * This label will display the score of the current player.
	 */
	private JLabel scoreLabel;

	/**
	 * This label will display an error, for example when a move is not
	 * possible.
	 */
	private JLabel errorLabel;

	/**
	 * This button will restart the game.
	 */
	private JButton restartButton;

	public ScorePanel() {
		super();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		errorLabel = new JLabel();
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		errorLabel.setHorizontalAlignment(JLabel.CENTER);
		restartButton = new JButton("Restart!");

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		add(scoreLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		add(restartButton, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		add(errorLabel, c);

		// Add action listener to button
		restartButton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
            	GUI.game.stop();
        		GUI.game = new Game();
        		GUI.game.start();
        		GUI.mainFrame.dispose();
        		GUI.gui = new GUI();
            }
        });
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
	 * 
	 * @param error
	 *            The text of this error.
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
