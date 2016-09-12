package pe.edu.system.jcmr.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Julio Cesar Meza Rios
 */

public class Index extends Application {

	private final String LOGUIN_FXML = "LoguinFXML.fxml";

	@Override
	public void start(Stage primaryStage) throws IOException {

		StackPane root = FXMLLoader.load(getClass().getResource(LOGUIN_FXML));

		Scene scena = new Scene(root);
		primaryStage.setScene(scena);
		primaryStage.setWidth(406);
		primaryStage.setHeight(367);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("SystemJCMR");
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
