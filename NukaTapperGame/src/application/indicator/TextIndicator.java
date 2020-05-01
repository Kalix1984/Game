package application.indicator;

import application.entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TextIndicator extends Entity implements Indicator{
	
	private String text;
	private String fontType;
	private int fontSize;
	private Color fontColor;
	private TextAlignment align;
	
	
	public TextIndicator(double posX, double posY) {
		setPosition(posX, posY);
		setDefaults();
	}

	private void setDefaults() {
		text = "nincs";
		fontType = "Verdana";
		fontSize = 20;
		fontColor = Color.BLACK;
		align = TextAlignment.RIGHT;
	}
	
	public TextIndicator(double posX, double posY, String fontType, int fontSize, Color fontColor, TextAlignment align) {
		this(posX, posY);
		
		this.fontType = fontType;
		this.fontSize = fontSize;
		this.fontColor = fontColor;
		this.align = align;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFont(new Font(fontType, fontSize));
		gc.setFill(fontColor);
		gc.fillText(text, getPositionX(), getPositionY(),90);
		gc.setTextAlign(align);
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
