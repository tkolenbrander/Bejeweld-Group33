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
	
	private static ArrayList<ArrayList<Change<Position>>> list;

	public TimelineController() {
		list = new ArrayList<ArrayList<Change<Position>>>();
	}
	
	public Timeline getTimeline() {
		Timeline t = new Timeline();
		KeyFrame[] keyFrames = new KeyFrame[list.size()];
		for(int i = 0; i < list.size(); i++) {
			List<Change<Position>> changes = list.get(i);
			KeyValue[] keyValues = generateKeyValues(changes);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValues);
			keyFrames[i] = keyFrame;
		}
		t.getKeyFrames().addAll(keyFrames);
		return t;
	}
	
	public KeyValue[] generateKeyValues(List<Change<Position>> changes) {
		KeyValue[] keyValues = new KeyValue[changes.size()];
		for(int i = 0; i < changes.size(); i++) {
			//TODO: Convert changes to keyvalues and add to array
		}
		return keyValues;
	}
	
	public void setList(ArrayList<ArrayList<Change<Position>>> l) {
		list = l;
	}

}
