package application;

import application.menu.GameMenu;
import application.menu.MenuButton;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ViewManager {

	private Stage mainStage;
	private Scene mainScene;
	private Parent mainPane;

	static final int WIDTH = 800;
	static final int HEIGHT = 600;

	public ViewManager() {

		this.mainStage = new Stage();
		this.mainPane = generateSplashScreen();
		this.mainScene = new Scene(mainPane, WIDTH, HEIGHT);

		setupWindow();
	}
	
	public ViewManager(Stage mainStage) {
		this.mainStage = mainStage;
	}

	private void setupWindow() {
		mainStage.setScene(mainScene);
		mainStage.setTitle("Nuka Tapper");
		mainStage.setResizable(false);

		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		mainStage.setOnCloseRequest(e -> {
			System.out.println("kilépett(X-el)...menteni kell!");
		});
	}

	
	public Parent getMainPane() {
		return mainPane;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public Parent generateSplashScreen() {
		StackPane layout = new StackPane();
		
		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		
		Rectangle rect = new Rectangle(400, 400, Color.BLUE);
		MenuButton btn = new MenuButton("Tovább");
		
		box.getChildren().addAll(rect, btn);
		layout.getChildren().add(box);

		btn.setOnAction(e -> {
			GameMenu mainMenu = new GameMenu(mainStage);
			
			mainStage.getScene().setRoot(mainMenu.getRootPane());
		});

		
		return layout;
	}

}
