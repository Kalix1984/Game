package application.entities;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class OSD {

	private String fontType;
	private int fontSize;
	private int posX;
	private int posY;
	private int maxWidth;
	
	

	public OSD() {
		maxWidth = 80;
		fontSize = 20;
		fontType = "Verdana";
		posX = 50;
		posY = 50;
	}

	public void setFontType(String fontType) {
		this.fontType = fontType;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void draw(GraphicsContext gc, String text, Color color) {
		gc.setFill(color);
		gc.setFont(new Font(fontType, fontSize));
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.fillText(text, posX, posY, maxWidth);
	}
	
	public void draw(GraphicsContext gc, int number, Color color) {
		gc.setFill(color);
		
		int elementXPos = this.posX;
		
		for (int i = 0; i < number; i++) {
			gc.fillRoundRect(elementXPos, posY, 20, 30, 5, 5);
			elementXPos += 20 + 5;
		}
		
	}
	
	
}
