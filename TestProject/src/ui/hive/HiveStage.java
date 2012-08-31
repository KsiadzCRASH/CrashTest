package ui.hive;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HiveStage extends Application {

	public static void main(String[] args) {
        launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Group root = new Group();
		Scene scene = new Scene(root, 1024, 768, Color.BLACK);

		stage.setScene(scene);

		stage.show();
	}

}
