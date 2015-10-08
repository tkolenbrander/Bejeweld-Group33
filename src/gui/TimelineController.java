package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import board.Change;
import board.Position;

public class TimelineController {

	/**
	 * Contains all changes.
	 */
	private static ArrayList<ArrayList<Change<Position>>> list;

	/**
	 * Simple constructor.
	 */
	public TimelineController() {
		list = new ArrayList<ArrayList<Change<Position>>>();
	}

	/**
	 * Generates animations from the changes.
	 * @return The timeline with animations.
	 */
	public Timeline getTimeline() {
		Timeline t = new Timeline();
		KeyFrame[] keyFrames = new KeyFrame[list.size()];
		for (int i = 0; i < list.size(); i++) {
			List<Change<Position>> changes = list.get(i);
			KeyValue[] keyValues = generateKeyValues(changes);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValues);
			keyFrames[i] = keyFrame;
		}
		t.getKeyFrames().addAll(keyFrames);
		return t;
	}

	/**
	 * Generates key values from the changes.
	 * @param changes The list with all the changes that should occur parallel.
	 * @return The generated key values.
	 */
	public KeyValue[] generateKeyValues(List<Change<Position>> changes) {
		KeyValue[] keyValues = new KeyValue[changes.size()];
		for (int i = 0; i < changes.size(); i++) {
			// TODO: Convert changes to keyvalues and add to array
		}
		return keyValues;
	}

	/**
	 * Sets the list of current changes.
	 * @param l A list of changes.
	 */
	public void setList(ArrayList<ArrayList<Change<Position>>> l) {
		list = l;
	}

}
