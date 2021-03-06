package application.entities;

import javafx.scene.canvas.GraphicsContext;

public class Background extends Entity{
	
	public Background() {
		setImage("background.png");
	}

	@Override
	public void render(GraphicsContext gameSpace) {
		gameSpace.drawImage(getImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
	}
}
