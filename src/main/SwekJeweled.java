package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import main.game.Logger;
import main.gui.MenuViewController;

/**
 * The main class for the SwekJeweld game.
 * This class makes sure the GUI is loaded and the game is started.
 * 
 * @author The SwekJeweled Team
 * @version 20/10/2015
 */
public class SwekJeweled extends Application {

	/**
	 * X and Y dimensions of the board.
	 */
	private static final int WINDOW_X = 520;
	private static final int WINDOW_Y = 620;

	private static Stage stage;

	/**
	 * Main class to launch the application.
	 * 
	 * @param args Launch arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		SwekJeweled.stage = primaryStage;
		SwekJeweled.stage.setTitle("SwekJeweled");

		//Properties of the stage
		SwekJeweled.stage.setResizable(false);
		SwekJeweled.stage.centerOnScreen();
		SwekJeweled.stage.setWidth(WINDOW_X);
		SwekJeweled.stage.setHeight(WINDOW_Y);
		SwekJeweled.stage.show();
		
		Logger.logInfo("Game succesfully initialized");
		
		//Show the main menu
		MenuViewController.show();
	}

	/**
	 * @return The primary stage.
	 */
	public static Stage getStage() {
		return stage;
	}

}
