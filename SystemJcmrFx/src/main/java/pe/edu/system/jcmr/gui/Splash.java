package pe.edu.system.jcmr.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Splash extends Application{
	private final String LOGUIN_FXML = "Splash.fxml";
	
	

	@Override
	public void start(Stage primaryStage) throws IOException {

		StackPane root = FXMLLoader.load(getClass().getResource(LOGUIN_FXML));

		Scene scena = new Scene(root);
		scena.setFill(null);
		primaryStage.setScene(scena);
		primaryStage.setWidth(406);
		primaryStage.setHeight(367);

		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setTitle("SystemJCMR");
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
