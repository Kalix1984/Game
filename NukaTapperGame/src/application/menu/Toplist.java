package application.menu;

import application.Panel;
import application.Header;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Toplist {
	private final Parent rootPane;
	private final Stage mainStage;

	public Toplist(Stage mainStage) {
		this.rootPane = generateToplist();
		this.mainStage = mainStage;
		
		
		
	}

	private Parent generateToplist() {
		StackPane layout = new StackPane();
		
		MenuButton backButton = new MenuButton("Vissza");
		backButton.setAlignment(Pos.CENTER);
		backButton.setOnAction(e -> {
			GameMenu mainMenu = new GameMenu(mainStage);
			
			mainStage.getScene().setRoot(mainMenu.getRootPane());
		});
		
		Panel toplistPanel = new Panel(250, 200, 260, 150);
		
		StackPane header = new StackPane();
		header.setPadding(new Insets(5));
		header.setStyle("-fx-background-color: #162455; -fx-background-radius: 10 10 0 0;");
		
		Text caption = new Text("Toplista");
		caption.setFont(new Font("Impact", 20));
		caption.setFill(Color.web("#AFD8E2"));
		header.getChildren().add(caption);
		
		//tesztadatok
		Text content1 = new Text("1. Jani");
		Text content2 = new Text("2. Szilvi");
		Text content3 = new Text("3. András");
		Text content4 = new Text("4. Attila");
		Text content5 = new Text("5. Barabás");
		
		StackPane footer = new StackPane();
		footer.setPadding(new Insets(10));
		footer.getChildren().add(backButton);
		
		VBox content = new VBox();
		content.setPadding(new Insets(20, 50, 20, 50));
		content.getChildren().addAll(content1, content2, content3, content4, content5);
		
		toplistPanel.setTop(header);
		toplistPanel.setCenter(content);
		toplistPanel.setBottom(footer);

		layout.getChildren().add(toplistPanel);
		
		return layout;
	}

	public Parent getRootPane() {
		return rootPane;
	}

}
