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

public class Toplist {
	private final AnchorPane rootPane;

    public Toplist() {
    	this.rootPane = new AnchorPane();
//    	Panel toplistPanel = new Panel("Toplista", 400, 400);
//    	rootPane.getChildren().add(toplistPanel);
		
    	
    	
    	
    	Panel panel = new Panel("Toplista" ,600, 400);
		rootPane.getChildren().add(panel);
		

        
        
       
    }

    public Pane getRootPane() {
        return rootPane ;
    }
    
    
    
}
