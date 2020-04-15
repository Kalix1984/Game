package application.menu;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameMenu{
	private final Parent rootPane;
	private final Stage mainStage;
	
	
    public GameMenu(Stage mainStage) {
    	this.mainStage = mainStage;
        rootPane = generateMenu();
        
    }
    
    public Parent getRootPane() {
    	return rootPane ;
    }

	private Parent generateMenu() {
		StackPane layout = new StackPane();
		
		VBox box = new VBox(10);
		
        MenuButton startButton = new MenuButton("Játék indítása");
        MenuButton toplistButton = new MenuButton("Toplista");
        MenuButton settingsButton = new MenuButton("Beállítások");
        MenuButton exitButton = new MenuButton("Kilépés");
        
        startButton.setOnAction(e -> {
        	Start start = new Start(mainStage);
			mainStage.getScene().setRoot(start.getRootPane());
        });
        toplistButton.setOnAction(e -> {
        	Toplist toplist = new Toplist(mainStage);
        	mainStage.getScene().setRoot(toplist.getRootPane());
        });
        settingsButton.setOnAction(e -> {
        	Settings settings = new Settings(mainStage);
        	mainStage.getScene().setRoot(settings.getRootPane());
        });
        exitButton.setOnAction(e -> {
        	mainStage.close();
        	System.out.println("kilépett(Kilépés gombbal)...menteni kell!");
        });
        
        box.getChildren().addAll(startButton, toplistButton, settingsButton, exitButton);
        box.setAlignment(Pos.CENTER);
        
        layout.getChildren().add(box);
        
        return layout;
	}
}
