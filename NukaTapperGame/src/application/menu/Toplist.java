package application.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import application.sql.DataBase;
import application.sql.Winner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Toplist {
	public final static String FONT_NAME = "res/Heroes Legend.ttf";
	private final Parent rootPane;
	private final Stage mainStage;
	public DataBase db = new DataBase(); 

	public Toplist(Stage mainStage) {
		this.rootPane = generateToplist();
		this.mainStage = mainStage;
	}

	private Parent generateToplist() {
		AnchorPane layout = new AnchorPane();
		
		MenuButton backButton = new MenuButton("Back");
		backButton.setAlignment(Pos.CENTER);
		backButton.setOnAction(e -> {
			Menu mainMenu = new Menu(mainStage);
			
			mainStage.getScene().setRoot(mainMenu.getRootPane());
		});
		
		Panel toplistPanel = new Panel(250, 200, 260, 150);
		
		StackPane header = new StackPane();
		header.setPadding(new Insets(15));
		header.setStyle("-fx-background-color: #162455; -fx-background-radius: 10 10 0 0;");
		
		Text caption = new Text("Toplist");
//		caption.setFont(new Font("Impact", 20));
		caption.setFill(Color.web("#AFD8E2"));
		
		try {
			caption.setFont(Font.loadFont(new FileInputStream(new File(FONT_NAME)), 20));
		} catch (FileNotFoundException e) {
			caption.setFont(Font.font("Verdana", 25));
		}
		
		header.getChildren().add(caption);
		
		List<Winner> winners = db.getToplist(); 
		
		Text content1 = new Text("1. " + winners.get(0).getName() + " " + winners.get(0).getScore());
		Text content2 = new Text("2. " + winners.get(1).getName() + " " + winners.get(1).getScore());
		Text content3 = new Text("3. " + winners.get(2).getName() + " " + winners.get(2).getScore());
		Text content4 = new Text("4. " + winners.get(3).getName() + " " + winners.get(3).getScore());
		Text content5 = new Text("5. " + winners.get(4).getName() + " " + winners.get(4).getScore());
		
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
