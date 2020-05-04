package application.gamepanel;

import application.entities.Entity;
import application.input.Keyboard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MessageGamePanel extends Entity implements GamePanel {
	
	private Keyboard input;

	private String fontType;
	private int fontSize;
	private Color fontColor;

	public MessageGamePanel(int posX, int posY, int width, int height, Keyboard input) {
		setPosition(posX, posY);
		setWidth(width);
		setHeight(height);
		this.input = input;
		setDefaults();
	}

	private void setDefaults() {
		
		fontType = "Verdana";
		fontSize = 20;
		fontColor = Color.BLACK;
	}

	@Override
	public void render(GraphicsContext gameSpace, String text) {
		gameSpace.setFill(Color.AQUAMARINE);
		gameSpace.fillRect(getPositionX(), getPositionY(), getWidth(), getHeight());
		
		gameSpace.setFont(new Font(fontType, fontSize));
		gameSpace.setFill(fontColor);
		
		gameSpace.fillText(text, 250, 300);
	}

	@Override
	public boolean isExitKeyPressed() {
		return input.isStart();
	}

}
