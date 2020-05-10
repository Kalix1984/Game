package application;



import application.menu.Menu;
import application.sql.DataBase;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoreSaverViewManager {

	static final int WIDTH = 400;
	static final int HEIGHT = 400;

	private int score;
	
	private final Parent rootPane;
	private final Stage mainStage;

	private Scene mainScene;
	
	private DataBase db;

	public ScoreSaverViewManager(Stage mainStage) {
		this.mainStage = mainStage;
		rootPane = generateScoreSaver();
		db = new DataBase();
		
	}

	public Parent getRootPane() {
		return rootPane;
	}

	public void setScore(int score) {
		this.score = score;
	}
		

	private Parent generateScoreSaver() {
		
		VBox layout = new VBox(10);

		TextField playerName = new TextField("Name");
		playerName.setMaxWidth(200);

		Button send = new Button("Send");
		layout.getChildren().addAll(playerName, send);
		

		send.setOnAction(e -> {
			db.addScore(playerName.getText(), score);
			
			Menu menu = new Menu(mainStage);
			mainStage.getScene().setRoot(menu.getRootPane());
		
		});

		return layout;

	}

}
