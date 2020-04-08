package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Panel extends Pane {

	public Panel(String title, double width, double height, Node node) {
		setPrefWidth(width);
		setPrefHeight(height);
		setPadding(new Insets(10, 10, 10, 10));
		
		VBox vbox = new VBox();
		getChildren().add(vbox);
		
		Pane header = new Pane();
		header.setStyle("-fx-background-color: #223986; -fx-background-radius: 20 20 0 0;");
		header.setPrefWidth(width);
		header.setPrefHeight(50);
		
		
		vbox.getChildren().addAll(header, node);
		
		
		
		

		Label panelName = new Label();
		panelName.setFont(new Font("Verdana", 30));
		panelName.setText(title);
		panelName.setTextFill(Color.web("white"));
		panelName.setLayoutX(200);

		
		header.getChildren().add(panelName);
		
//		
//		List<Label> elements = new ArrayList<>();
//		elements.add(createLabel("1. alma"));
//		elements.add(createLabel("2. körte"));
//		elements.add(createLabel("3. szilva"));
//		elements.add(createLabel("4. cseresznye"));
//		elements.add(createLabel("5. kivi"));
//		
//		MenuButton backButton = new MenuButton("Vissza");
//		backButton.setOnAction(e->{
//			System.out.println("back");
//		});
//		
//		
//		
//		vbox.setTranslateX(300);
//		vbox.setTranslateY(150);
//		
//		for (Label label : elements) {
//			vbox.getChildren().add(label);
//		}
//		vbox.getChildren().add(backButton);
//		
//		
//		
//		
//		super.getChildren().addAll(bg, vbox);

	}
//	//content
//	private Label createLabel(String name) {
//		Label label = new Label();
//		label.setFont(new Font("Verdana", 20));
//		label.setText(name);
//		
//		return label;
//	}

}
