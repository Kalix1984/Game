package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.menu.Menu;
import application.menu.MenuButton;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewManager {

	private Stage mainStage;
	private Scene mainScene;
	private Parent mainPane;

	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	
	public final static String FONT_NAME = "res/Heroes Legend.ttf";

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
		mainStage.setTitle("Nuka Tapper Game");
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
		
		VBox box = new VBox(200);
		box.setAlignment(Pos.CENTER);
		
		Text gameTitle = new Text("Nuka Tappper");
		gameTitle.setFill(Color.ORANGE);
		gameTitle.setStroke(Color.WHITE);
		gameTitle.setStrokeWidth(5);
		
		try {
			gameTitle.setFont(Font.loadFont(new FileInputStream(new File(FONT_NAME)), 60));
		} catch (FileNotFoundException e) {
			gameTitle.setFont(Font.font("Verdana", 25));
		}
		
		MenuButton btn = new MenuButton("Start");
		
		box.getChildren().addAll(gameTitle, btn);
		layout.getChildren().add(box);

		btn.setOnAction(e -> {
			Menu mainMenu = new Menu(mainStage);
			
			mainStage.getScene().setRoot(mainMenu.getRootPane());
		});

		
		return layout;
	}

}
