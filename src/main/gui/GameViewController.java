package main.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.SwekJeweled;
import main.game.ClassicGame;
import main.game.Game;
import main.game.LoadGame;
import main.game.Logger;
import main.game.Observer;
import main.game.SaveGame;

/**
 * Controller class for the game.
 * This class takes care of correctly displaying the game and 
 * 	the elements that belong in the in-game view.
 * 
 * @author Steven Meijer
 */
public class GameViewController implements Observer {

	private static final String FILENAME = "game.fxml";

	private static Game game;
	private static BoardPane boardPane;

	@FXML private BorderPane borderPane;

	@FXML private Button saveGameButton;
	@FXML private Button loadGameButton;
	@FXML private Button restartGameButton;
	@FXML private Button exitGameButton;

	@FXML private Label scoreLabel;
	@FXML private Label errorLabel;

	@FXML private Pane topPane;
	@FXML private Pane bottomPane;

	@FXML
	private void initialize() {

		game = new ClassicGame();
		game.start();
		game.getPlayer().register(this);

		boardPane = new BoardPane();
		borderPane.setCenter(boardPane.getBoardPane());

		topPane.setStyle("-fx-background-color: #C0C0C0;");
		borderPane.getChildren().remove(topPane);
		borderPane.setTop(topPane);
		bottomPane.setStyle("-fx-background-color: #C0C0C0;");

		scoreLabel.setText("Score: 0");

		initializeButtons();

		Logger.logInfo("New Normal game started");
	}

	/**
	 * Initializes the on-screen buttons.
	 */
	private void initializeButtons() {
		saveGameButton.setOnAction((event) -> {
			SaveGame.save(game);
			setError("Game saved!");
			Logger.logInfo("Game saved");
		});

		loadGameButton.setOnAction((event) -> {
			try {
				game = LoadGame.load();
				game.start();
				boardPane.refresh();
				setScore(GameViewController.game.getPlayer().getScore());
				game.getPlayer().register(this);
				setError("Game loaded!");
				Logger.logInfo("Loaded game from file");
			} catch (Exception e) {
				setError("Cannot load game!");
				Logger.logWarning("Failed to load game from file with error: " + e);
			} 
		});

		restartGameButton.setOnAction((event) -> {
			game.reset();
			game.start();
			setScore(0);
			boardPane.refresh();
		});

		exitGameButton.setOnAction((event) -> {
			game.getPlayer().unregister(this);
			MenuViewController.show();
		});
	}

	/**
	 * Sets a message for the error label.
	 * @param s the message to be displayed
	 */
	protected void setError(String s) {
		errorLabel.setText(s);
	}

	/**
	 * Sets a number for the score label.
	 * @param score number to be displayed
	 */
	protected void setScore(int score) {
		String s = "Score: " + score;
		scoreLabel.setText(s);
	}

	/**
	 * @return The game.
	 */
	protected static Game getGame() {
		return game;
	}

	/**
	 * @return The GameViewController.
	 */
	//	protected static GameViewController getGameViewController() {
	//		return this;
	//		TODO Alternative that works
	//	}

	/**
	 * @return The boardpane.
	 */
	protected static BoardPane getBoardPane() {
		return boardPane;
	}

	/**
	 * Show this view.
	 */
	public static void show() {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(SwekJeweled.class.getClassLoader().getResource(FILENAME));

		try {
			AnchorPane game = l.load();
			SwekJeweled.getStage().setScene(new Scene(game));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update method for observer.
	 */
	@Override
	public void update() {
		int newScore = (int) game.getPlayer().getUpdate(this);
		setScore(newScore);
	}

}
