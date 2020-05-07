package application.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Settings {
	private final Parent rootPane;
	private final Stage mainStage;

	
    public Settings(Stage mainStage) {
		this.rootPane = generateSettings();
		this.mainStage = mainStage;
    }

    public Parent getRootPane() {
        return rootPane ;
    }
    
    private Parent generateSettings() {
		AnchorPane layout = new AnchorPane();
		
		MenuButton backButton = new MenuButton("Vissza");
		backButton.setAlignment(Pos.CENTER);
		backButton.setOnAction(e -> {
			Menu mainMenu = new Menu(mainStage);
			
			mainStage.getScene().setRoot(mainMenu.getRootPane());
		});
		
		MenuButton saveButton = new MenuButton("Mentés");
		saveButton.setAlignment(Pos.CENTER);
		saveButton.setOnAction(e -> {
			System.out.println("Mentés");
		});
		
		Panel toplistPanel = new Panel(450, 400, 160, 50);
		
		StackPane header = new StackPane();
		header.setPadding(new Insets(5));
		header.setStyle("-fx-background-color: #162455; -fx-background-radius: 10 10 0 0;");
		
		Text caption = new Text("Beállítások");
		caption.setFont(new Font("Impact", 20));
		caption.setFill(Color.web("#AFD8E2"));
		header.getChildren().add(caption);
		
		//tesztadatok
		Text content1 = new Text("Vezérlés...");
		Text content2 = new Text("Nehézségi szint...");
		Text content3 = new Text("Zene hangerő....");
		Text content4 = new Text("Effekt hangerő...");
		
		HBox footer = new HBox(10);
		footer.setPadding(new Insets(10));
		footer.getChildren().addAll(saveButton, backButton);
		
		VBox content = new VBox();
		content.setPadding(new Insets(20, 50, 20, 50));
		content.getChildren().addAll(content1, content2, content3, content4);
		
		toplistPanel.setTop(header);
		toplistPanel.setCenter(content);
		toplistPanel.setBottom(footer);

		layout.getChildren().add(toplistPanel);
		
		return layout;
	}
}
