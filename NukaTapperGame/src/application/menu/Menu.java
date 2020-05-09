package application.menu;

import application.GameViewManager;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {
	private final Parent rootPane;
	private final Stage mainStage;

	public Menu(Stage mainStage) {
		this.mainStage = mainStage;
		rootPane = generateMenu();

	}

	public Parent getRootPane() {
		return rootPane;
	}

	private Parent generateMenu() {
		StackPane layout = new StackPane();

		VBox box = new VBox(10);

		MenuButton startButton = new MenuButton("New Game");
		MenuButton toplistButton = new MenuButton("Toplist");
		MenuButton exitButton = new MenuButton("Exit");

		startButton.setOnAction(e -> {
			GameViewManager game = new GameViewManager();
			game.changeToGameStage(mainStage);
		});

		toplistButton.setOnAction(e -> {
			Toplist toplist = new Toplist(mainStage);
			mainStage.getScene().setRoot(toplist.getRootPane());
		});

		exitButton.setOnAction(e -> {
			mainStage.close();
			
		});

		box.getChildren().addAll(startButton, toplistButton, exitButton);
		box.setAlignment(Pos.CENTER);

		layout.getChildren().add(box);

		return layout;
	}
}
