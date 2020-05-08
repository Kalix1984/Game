package application.menu;


import javafx.scene.layout.BorderPane;

public class Panel extends BorderPane {

	public Panel(int width, int height, int posX, int posY) {

		setPrefWidth(width);
		setPrefHeight(height);

		setLayoutX(posX);
		setLayoutY(posY);
		
		
	}
}
