package application.menu;

import application.Panel;
import application.Header;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Toplist {
	private final AnchorPane rootPane;

	public Toplist(Stage mainStage) {
		this.rootPane = new AnchorPane();
		
//		MenuButton backButton = new MenuButton("Back");
//		backButton.setOnAction(e -> {
//			GameMenu mainMenu = new GameMenu(mainStage);
//			
//			mainStage.getScene().setRoot(mainMenu.getRootPane());
//		});

		
		
		Panel toplistPanel = new Panel(400, 400, 200, 80);
		toplistPanel.addHeader();
		
		
//		titlePane.setStyle("-fx-background-color: black; -fx-background-radius: 10 10 0 0;");
//		titlePane.setPadding(new Insets(5));
//		toplistPanel.getTitlePane().setTop(titlePane);
//		
//		
//		Label panelTitle = new Label();
//		panelTitle.setFont(new Font(titleFontType, titleFontSize));
//		panelTitle.setText(title);
//		panelTitle.setTextFill(Color.web(titleFontColor));
//
//		titlePane.getChildren().add(panelTitle);


		rootPane.getChildren().add(toplistPanel);
		
	}

	public Pane getRootPane() {
		return rootPane;
	}

}
