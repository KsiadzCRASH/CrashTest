package ui.game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameStage {
	
	//Params
	private int width;
	private int height;
	private Color background;
	//
	private GameStageRenderer gameRenderer;
	private Stage stage;
	
	public GameStage(int width, int height, Color background)
	{
		this.width = width;
		this.height = height;
		this.background = background;
		
		gameRenderer = new GameStageRenderer();
	}
	
	public void initialize(Stage stage) {

		this.stage = stage;
		
		Group root = new Group();
		Scene scene = new Scene(root, width, height, background);

		this.stage.setScene(scene);
	}
	public void show()
	{
		stage.show();
	}
}
