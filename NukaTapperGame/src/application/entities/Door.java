package application.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Door extends Entity {

	public Door(Bar bar) {
		setPositionY(bar.getPositionY() - 50);
		setPositionX(bar.getPositionX() + bar.getWidth() + 5);
		setWidth(40);
		setHeight(90);
	}

	@Override
	public void render(GraphicsContext gameSpace) {
//		gameSpace.setFill(Color.WHITE);
		gameSpace.setFill(Color.BLACK);
		gameSpace.setImageSmoothing(true);
		gameSpace.fillRect(getPositionX(), getPositionY(), getWidth(), getHeight());
		
	}
}
