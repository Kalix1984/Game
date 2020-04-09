package application;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Panel extends BorderPane {
	private int width;
	private int height;
	private int posX;
	private int posY;

	Header title;
	
	Pane content;
	StackPane footer;

	public Panel(int width, int height, int posX, int posY) {
		this.width = width;
		this.height = height;
		
		this.posX = posX;
		this.posY = posY;

		this.setPrefWidth(width);
		this.setPrefHeight(height);

		this.setLayoutX(posX);
		this.setLayoutY(posY);
		
		this.setTop(title);
		this.setCenter(content);
		this.setBottom(footer);
		
	}
	
	public void addHeader() {
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: white;");
		this.getChildren().add(pane);
	}

	
}
