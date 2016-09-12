package pe.edu.system.jcmr.util;

import java.util.HashMap;
import java.util.Map;

import javafx.stage.Stage;

public class SessionJCMR {

	private static SessionJCMR instance;
	private static Stage currentStage;

	public static SessionJCMR getInstance() {

		return instance == null ? new SessionJCMR() : instance;

	}

	private static Map<String, Object> contextObjects = new HashMap<String, Object>();

	public Map<String, Object> getContextObjects() {
		return contextObjects;
	}

	public Object getContextObject(String key) {
		return contextObjects.get(key);
	}

	public Object removeContextObject(String key) {
		return contextObjects.remove(key);
	}

	public void addContextObject(String key, Object value) {
		contextObjects.put(key, value);
	}

	public void clearContextObjects() {
		contextObjects.clear();
	}

	public Stage getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(Stage stage) {
		currentStage = stage;
	}
}
