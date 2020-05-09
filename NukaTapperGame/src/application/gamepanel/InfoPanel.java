package application.gamepanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import application.entities.Entity;
import application.input.Keyboard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InfoPanel extends Entity implements GamePanel {

	private Keyboard input;
	private String label;

	public InfoPanel(int posX, int posY, int width, int height, Keyboard input) {
		label = "No Info";
		setPosition(posX, posY);
		this.input = input;

		setImage("metalCenter.png");

		setWidth(width);
		setHeight(height);
	}

	@Override
	public boolean isExitKeyPressed() {
		return input.isStart();
	}

	@Override
	public void render(GraphicsContext gameSpace) {
		for (int x = 0; x < getWidth(); x += getImage().getWidth()) {

			for (int y = (int) getPositionY(); y < getPositionY() + getHeight(); y += getImage().getHeight()) {
				gameSpace.drawImage(getImage(), x, y, getImage().getWidth(), getImage().getHeight());
			}
		}

		try {
			gameSpace.setFont(Font.loadFont(new FileInputStream(new File(FONT_NAME)), 25));
		} catch (FileNotFoundException e) {
			gameSpace.setFont(Font.font("Verdana", 25));
		}

		gameSpace.setFill(Color.WHITE);
		gameSpace.setStroke(Color.BLACK);
		gameSpace.setLineWidth(2);

		gameSpace.fillText(label, getPositionX() + 100, getPositionY() + 80, 600);
		gameSpace.strokeText(label, getPositionX() + 100, getPositionY() + 80, 600);

	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}
}
