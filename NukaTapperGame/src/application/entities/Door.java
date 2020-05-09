package application.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Door extends Entity {

	public Door(Bar bar) {
		setPositionY(bar.getPositionY() - 50);
		setPositionX(bar.getPositionX() + bar.getWidth() + 3);

		setImage("door.png");

		setWidth(150);
		setHeight(90);
	}

	@Override
	public void render(GraphicsContext gameSpace) {
		gameSpace.drawImage(getImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
	}
}
