package application.menu;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Settings {
	private final StackPane rootPane;

    public Settings() {

        rootPane = new StackPane();
        Label label1= new Label("Ez az almenü3");
        
        rootPane.getChildren().add(label1);
    }

    public Pane getRootPane() {
        return rootPane ;
    }
}
