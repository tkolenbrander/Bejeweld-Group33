package main.gui;

import main.game.Game;
import main.game.LoadGame;
import main.game.Logger;
import main.game.SaveGame;
import main.game.Observer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Base class of the GUI.
 * 
 * @author Steven Meijer & Thomas Kolenbrander
 * @deprecated
 */
public class GUI extends Application implements Observer {

	/**
	 * X and Y dimensions of the board.
	 */
	private static final int WINDOW_X = 520;
	private static final int WINDOW_Y = 620;
	
	private final double fontSize = 22;

	private static Game game;
	private static BoardPane boardPane;
	private static Label scoreLabel;
	private static Label errorLabel;
	private static GUI gui;
	private static HBox hbox;
	private static BorderPane borderPane;

	/**
	 * Main class to launch the application.
	 * 
	 * @param args Launch arguments.
	 */
	public static void main(String[] args) {
		game = new Game();
		game.start();
		gui = new GUI();

		game.getPlayer().register(gui);
		launch(args);
	}

	/**
	 * JavaFX start class, that sets the properties of the GUI.
	 * 
	 * @param stage The stage.
	 */
	public void start(Stage stage) {
		Logger.logInfo("Game started");	

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUI.class.getResource("/assets/config/gui.fxml"));

		borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, WINDOW_X, WINDOW_Y);	

		scoreLabel = new Label("Score: ");
		scoreLabel.setFont(new Font("Arial", fontSize));
		errorLabel = new Label("");
		errorLabel.setFont(new Font("Arial", fontSize));
		boardPane = new BoardPane();
		hbox = addScorePanel();
		HBox errorPanel = addErrorPanel();
		borderPane.setTop(hbox);
		borderPane.setCenter(boardPane.getBoardPane());		
		borderPane.setBottom(errorPanel);

		// Properties of the stage
		stage.setTitle("SwekJeweled");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.setScene(scene);
		stage.show();

		Logger.logInfo("Game succesfully initialized");
	}

	/**
	 * Sets the hbox to the front of the view.
	 */
	public void boxToFront() {
		borderPane.getChildren().remove(hbox);
		borderPane.setTop(hbox);
	}

	/**
	 * Adds a score panel to the top of the screen.
	 * @return The HBox containing all that is needed for the score panel.
	 */
	private HBox addScorePanel() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 150));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #C0C0C0;");

		Button buttonSave = addSaveButton();
		Button buttonLoad = addLoadButton();

		setScore(0);
		hbox.getChildren().addAll(scoreLabel, buttonSave, buttonLoad);

		return hbox;  
	}

	/**
	 * Adds a save button.
	 */
	private Button addSaveButton() {
		Button buttonSave = new Button("Save Game");
		buttonSave.setPrefSize(100, 20);

		buttonSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SaveGame.save(game);
				setError("Game saved!"); 
			} 
		});

		return buttonSave;
	}

	/**
	 * Adds a load button.
	 */
	private Button addLoadButton() {
		Button buttonLoad = new Button("Load Game");
		buttonLoad.setPrefSize(100, 20);

		buttonLoad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					game = LoadGame.load();
					game.start();
					boardPane.refresh();
					setScore(GUI.game.getPlayer().getScore());
					game.getPlayer().register(gui);
					setError("Game loaded!"); }
				catch (Exception e) {
					setError("Cannot load game!"); 
				} 
			} 
		});

		return buttonLoad;
	}

	/**
	 * Adds the error panel at the bottom of the screen.
	 * @return The HBox containing all that is needed for the error panel
	 */
	private HBox addErrorPanel() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 150));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #C0C0C0;");

		Button buttonRestart = new Button("Restart Game");
		buttonRestart.setPrefSize(100, 20);

		buttonRestart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				game.reset();
				game.start();
				setScore(0);
				boardPane.refresh();
			}
		});

		hbox.getChildren().addAll(errorLabel, buttonRestart);

		return hbox;
	}
////////////////////////////////////////////////////////////////////
	/**
	 * Sets a message for the error label.
	 * @param s the message to be displayed
	 */
	protected final void setError(final String s) {
		errorLabel.setText(s);
	}

	/**
	 * Sets a number for the score label.
	 * @param score number to be displayed
	 */
	protected void setScore(final int score) {
		String s = "Score: " + score;
		scoreLabel.setText(s);
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns the game.
	 * @return Game
	 */
	protected static Game getGame() {
		return game;
	}

	/**
	 * Returns the GUI.
	 * @return gui
	 */
	protected static GUI getgui() {
		return gui;
	}

	/**
	 * Returns the boardpane.
	 * @return Boardpane.
	 */
	protected static BoardPane getBoardPane() {
		return boardPane;
	}

	/**
	 * Sets a number for the score label.
	 * @param o The observable object
	 * @param arg The argument.
	 */
	@Override
	public final void update() {
		int newScore = (int) game.getPlayer().getUpdate(gui);
		System.out.println(newScore);
		setScore(newScore);
	}

}
