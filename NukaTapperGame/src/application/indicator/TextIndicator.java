package application.indicator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TextIndicator extends Indicator{
	
	private String text;
	private String fontType;
	private int fontSize;
	private Color fontColor;
	private TextAlignment align;
	
	
	public TextIndicator(double posX, double posY) {
		setPosition(posX, posY);
		//default values
		text = "nincs";
		fontType = "Verdana";
		fontSize = 20;
		fontColor = Color.BLACK;
		align = TextAlignment.RIGHT;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setFontType(String fontType) {
		this.fontType = fontType;
	}
	
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public void setAlign(TextAlignment align) {
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
	public void update() {
	
	}
	
	
	
	

}
