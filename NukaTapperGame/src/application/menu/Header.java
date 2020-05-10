package application.menu;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Header extends Node {
	private Pane bg;

	public Header(String text) {
		bg = new Pane();

		bg.setPadding(new Insets(10));
	}
}
