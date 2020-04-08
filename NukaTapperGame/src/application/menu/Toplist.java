package application.menu;

import application.Panel;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Toplist {
	private final AnchorPane rootPane;

	public Toplist(Stage mainStage) {
		this.rootPane = new AnchorPane();
		
		MenuButton backButton = new MenuButton("Back");
		backButton.setOnAction(e -> {
			GameMenu mainMenu = new GameMenu(mainStage);
			
			mainStage.getScene().setRoot(mainMenu.getRootPane());
		});

		Panel panel = new Panel("Toplista", 600, 400, backButton);


		rootPane.getChildren().add(panel);
	}

	public Pane getRootPane() {
		return rootPane;
	}

}
