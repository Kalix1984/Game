package application;

import application.menu.MenuButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewManager2 {

	private Stage mainStage;
	
	private Scene firstScene;
	private Scene menuScene;
	
	private Scene gameScene;
	private Scene toplistScene;
	private Scene setupScene;
	
	private StackPane firstSceneLayout;
	private VBox menuSceneLayout;

	static final int WIDTH = 800;
	static final int HEIGHT = 600;

	public ViewManager2() {
		this.mainStage = new Stage();
		mainStage.setTitle("Nuka Tapper");
		mainStage.setResizable(false);
		
	}
	
	public void initWindow() {
		
		firstSceneLayout = new StackPane();
		
		MenuButton btn = new MenuButton("Tovább");

		btn.setOnAction(e -> {
			mainStage.setScene(menuScene);
		});

		firstSceneLayout.getChildren().add(btn);

		firstScene = new Scene(firstSceneLayout, WIDTH, HEIGHT);
		
		mainStage.setScene(firstScene);
		
		
	}
	
	public void initMenu() {
		menuSceneLayout = new VBox(10);
		
		MenuButton startButton = new MenuButton("Játék indítása");
		MenuButton toplistButton = new MenuButton("Toplista");
		MenuButton settingsButton = new MenuButton("Beállítások");
		MenuButton exitButton = new MenuButton("Kilépés");

		startButton.setOnAction(e -> {
			mainStage.setScene(gameScene);
		});
		toplistButton.setOnAction(e -> {
			mainStage.setScene(toplistScene);
		});
		settingsButton.setOnAction(e -> {
			mainStage.setScene(setupScene);
		});
		exitButton.setOnAction(e -> {
			mainStage.close();
		});

		menuSceneLayout.getChildren().addAll(startButton, toplistButton, settingsButton, exitButton);
		menuSceneLayout.setAlignment(Pos.CENTER);

		menuScene = new Scene(menuSceneLayout, WIDTH, HEIGHT);
	}
	
//	public void createNewGame(Stage mainStage) {
//		this.mainStage = mainStage;
//		this.menuStage.hide();
//		createBackground();
//		createShip(choosenShip);
//		createGameElements(choosenShip);
//		createGameLoop();
//		gameStage.show();
//	}

	public Stage getMainStage() {
		return mainStage;
	}
}
