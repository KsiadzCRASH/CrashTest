package main;

import main.alg.concurrency.game.managers.WorldManager;
import main.alg.concurrency.game.params.InitParams;
import ui.game.GameStage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameMain extends Application {

	private GameStage gStage;
	private WorldManager wManager; 
	
	public static void main(String[] args) {
        
		
		launch(args);
	}

	private InitParams loadResources()
	{
		return null;
	}
	private void initialize()
	{
		InitParams params = loadResources();
		 
		wManager = new WorldManager();
		gStage = new GameStage(1024, 768, Color.BLACK);
		
	}
	@Override
	public void start(Stage stage) throws Exception 
	{
		initialize();
		
		gStage.initialize(stage);
		gStage.show();
	}

}
