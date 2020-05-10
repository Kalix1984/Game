package application.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MenuButton extends Button {
	public final static String FONT_NAME = "res/Heroes Legend.ttf";
	
	
	public MenuButton(String title) {
		setPrefWidth(200);
		setText(title);
		
		try {
			setFont(Font.loadFont(new FileInputStream(new File(FONT_NAME)), 15));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 25));
		}
	}
	
	
	
	
}
