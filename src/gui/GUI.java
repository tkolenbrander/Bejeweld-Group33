package gui;

import game.Game;
import game.Logger;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * The base class of the GUI.
 * @author Thomas Kolenbrander
 *
 */
public class GUI extends JFrame implements WindowListener {

	private static final int WINDOW_X = 520;
	private static final int WINDOW_Y = 600;

	/**
	 * The mainframe of the GUI. All panels will be added to this frame
	 */
	protected static JFrame mainFrame;

	/**
	 * This panel will display the score of the player.
	 */
	protected static ScorePanel scorePanel;

	/**
	 * This panel will display the board of the game.
	 */
	private static BoardPanel boardPanel;

	/**
	 * This is our main game with the player and the board.
	 */
	protected static Game game;

	/**
	 * The GUI.
	 */
	protected static GUI gui;

	/**
	 * This is the main method which makes sure the GUI starts.
	 * @param args args
	 */
	public static void main(String[] args) {
		//Init game
		game = new Game();
		game.start();
		gui = new GUI();
	}

	public GUI() {
		createGUI();
	}

	/**
	 * This method will build the GUI.
	 */
	public static void createGUI() {
		Logger.logInfo("Game started");

		mainFrame = new JFrame("Bejeweled");
		mainFrame.setSize(WINDOW_X, WINDOW_Y);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setResizable(false);

		//Add the scorepanel
		scorePanel = new ScorePanel();
		mainFrame.add(scorePanel, BorderLayout.NORTH);

		//Add the boardpanel
		boardPanel = new BoardPanel();
		mainFrame.add(boardPanel, BorderLayout.SOUTH);
		
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 Logger.logInfo("Game closed");
	        	 Logger.close();
	         } 
		}); 

		mainFrame.setVisible(true);

		Logger.logInfo("Game succesfully initialised");
	}

	@Override
	public void windowOpened(WindowEvent e) {		
	}

	@Override
	public void windowClosing(WindowEvent e) {		
	}

	@Override
	public void windowClosed(WindowEvent e) {		
	}

	@Override
	public void windowIconified(WindowEvent e) {		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {		
	}

	@Override
	public void windowActivated(WindowEvent e) {		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {		
	}

}
