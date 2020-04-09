package application;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Header extends Node{
	
	private Pane bg;
	private String text;
	private String fontType = "Verdana";
	private int fontSize = 25;
	private String fontColor = "white";
	
	
	public Header(String text) {
		Text label = new Text(text);
		bg = new Pane();
		
		bg.setPadding(new Insets(5));
		bg.setStyle("-fx-background-color: green;");
		
	}
	
	
	
	
	
}
