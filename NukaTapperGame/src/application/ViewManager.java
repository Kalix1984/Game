package application;

import application.menu.GameMenu;
import application.menu.MenuButton;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewManager {

	private Stage mainStage;
	private Scene mainScene;
	private Pane mainPane;

	static final int WIDTH = 800;
	static final int HEIGHT = 600;

	public ViewManager() {

		this.mainStage = new Stage();
		this.mainPane = new Pane();
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

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public Scene getMainScene() {
		return mainScene;
	}

	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}

	public Pane getMainPane() {
		return mainPane;
	}

	public void setMainPane(Pane mainPane) {
		this.mainPane = mainPane;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public void initSplashScreen() {

		MenuButton btn = new MenuButton("Tovább");
		
		btn.setLayoutX(WIDTH / 2 - (btn.getPrefWidth() / 2));
		btn.setLayoutY(HEIGHT - 100);
		
		mainPane.getChildren().add(btn);

		btn.setOnAction(e -> {
			GameMenu mainMenu = new GameMenu(mainStage);
			
			getMainScene().setRoot(mainMenu.getRootPane());
		});

	}

}
