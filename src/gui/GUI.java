package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Base class of the GUI.
 * 
 * @author Steven Meijer & Thomas Kolenbrander
 */
public class GUI extends Application {

	/**
	 * X and Y dimensions of the board.
	 */
	private static final int WINDOW_X = 520;
	private static final int WINDOW_Y = 600;

	private Pane boardPane;

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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUI.class.getResource("/assets/config/gui.fxml"));

		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, WINDOW_X, WINDOW_Y);

		HBox hbox = addScorePanel();
		borderPane.setTop(hbox);
		borderPane.setCenter(boardPane);

		// Properties of the stage
		stage.setTitle("SwekJeweld");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * Adds a score panel to the top of the screen.
	 * @return The HBox containing all that is needed for the score panel.
	 */
	public HBox addScorePanel() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #3366ff;");

		Button buttonPause = new Button("Pause Game");
		buttonPause.setPrefSize(100, 20);

		Button buttonRestart = new Button("Restart Game");
		buttonRestart.setPrefSize(100, 20);

		hbox.getChildren().addAll(buttonPause, buttonRestart);

		return hbox;
	}
	
	/**
	 * @return The boardPane
	 */
	public Pane getBoardPane() {
		return boardPane;
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