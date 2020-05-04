package application.indicator;

import application.entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextIndicator extends Entity implements Indicator{
	
	private String text;
	private String fontType;
	private int fontSize;
	private Color fontColor;
	
	
	public TextIndicator(double posX, double posY) {
		setPosition(posX, posY);
		setDefaults();
	}
	
	private void setDefaults() {
		text = "nincs";
		fontType = "Verdana";
		fontSize = 20;
		fontColor = Color.BLACK;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFont(new Font(fontType, fontSize));
		gc.setFill(fontColor);
		gc.fillText(text, getPositionX(), getPositionY());
	}

	@Override
	public void update(int newValue) {
		text = "" + newValue; 
		
	}

	@Override
	public void update(String newValue) {
		text = newValue;
		
	}

}
