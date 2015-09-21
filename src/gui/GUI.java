package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Base class of the GUI.
 * 
 * @author Steven Meijer & Thomas Kolenbrander
 *
 */

public class GUI extends Application {

	/**
	 * X and Y dimensions of the board.
	 */
	private static final int WINDOW_X = 520;
	private static final int WINDOW_Y = 600;
	
	private Pane boardPane;
    private Label scoreLabel;

	/**
	 * Main class to launch the application.
	 * 
	 * @param args Launch arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * JavaFX start class, that sets the properties of the GUI.
	 * 
	 * @param primaryStage The stage.
	 */
	public void start(Stage stage) {
		Group root = new Group();
		Scene scene = new Scene(root, WINDOW_X, WINDOW_Y);
		
		// Properties of the stage
		stage.setTitle("SwekJeweld");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.setScene(scene);
		stage.show();

	}
}

//OLD Swing GUI below, use for reference

//package gui;
//
//import game.Game;
//
//import java.awt.BorderLayout;
//
//import javax.swing.JFrame;
//
///**
// * The base class of the GUI.
// * @author Thomas Kolenbrander
// *
// */
//public class GUI extends JFrame  {
//  
//  private static final int WINDOW_X = 520;
//  private static final int WINDOW_Y = 600;
//	
//	/**
//	 * The mainframe of the GUI. All panels will be added to this frame
//	 */
//	private static JFrame mainFrame;
//	
//	/**
//	 * This panel will display the score of the player.
//	 */
//	protected static ScorePanel scorePanel;
//	
//	/**
//	 * This panel will display the board of the game.
//	 */
//	private static BoardPanel boardPanel;
//	
//	/**
//	 * This is our main game with the player and the board.
//	 */
//	protected static Game game;
//	
//	/**
//	 * The GUI.
//	 */
//	protected static GUI gui;
//	
//	/**
//	 * This is the main method which makes sure the GUI starts.
//	 * @param args args
//	 */
//	public static void main(String[] args) {
//		//Init game
//		game = new Game();
//		game.start();
//		gui = new GUI();
//	}
//	
//	public GUI() {
//		createGUI();
//	}
//	
//	/**
//	 * This method will build the GUI.
//	 */
//	public static void createGUI() {
//		mainFrame = new JFrame("Bejeweled");
//		mainFrame.setSize(WINDOW_X, WINDOW_Y);
//		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
//		mainFrame.setLayout(new BorderLayout());
//		mainFrame.setResizable(false);
//		
//		//Add the scorepanel
//		scorePanel = new ScorePanel();
//		mainFrame.add(scorePanel, BorderLayout.NORTH);
//		
//		//Add the boardpanel
//		boardPanel = new BoardPanel();
//		mainFrame.add(boardPanel, BorderLayout.SOUTH);
//		
//		mainFrame.setVisible(true);
//	}
//
//}