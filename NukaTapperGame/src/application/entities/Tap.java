package application.entities;

import javafx.scene.canvas.GraphicsContext;

public class Tap extends Entity{

	public Tap(Bar bar) {
		setPositionY(bar.getPositionY() - 20);
		setPositionX(bar.getStarX() - 95);

		setImage("tap.png");
	}
	
	@Override
	public void render(GraphicsContext gameSpace) {
		gameSpace.drawImage(getImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
	}
}
