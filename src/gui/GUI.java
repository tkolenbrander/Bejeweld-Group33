package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * The base class of the GUI.
 * @author Thomas Kolenbrander
 *
 */
public class GUI extends JFrame  {
  
  private static final int WINDOW_X = 800;
  private static final int WINDOW_Y = 600;
	
	/**
	 * The mainframe of the GUI. All panels will be added to this frame
	 */
	private static JFrame mainFrame;
	
	/**
	 * This panel will display the score of the player.
	 */
	private static ScorePanel scorePanel;

	/**
	 * This is the main method which makes sure the GUI starts.
	 * @param args args
	 */
	public static void main(String[] args) {
		createGUI();
	}
	
	/**
	 * This method will build the GUI.
	 */
	public static void createGUI() {
		mainFrame = new JFrame("Bejeweled");
		mainFrame.setSize(WINDOW_X, WINDOW_Y);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		mainFrame.setLayout(new BorderLayout());
		
		//Add the scorepanel
		scorePanel = new ScorePanel();
		mainFrame.add(scorePanel, BorderLayout.NORTH);
		
		//Add the boardpanel
		//TODO
		
		mainFrame.setVisible(true);
	}

}
