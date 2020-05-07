package application.indicatorsview;

import application.entities.Entity;
import javafx.scene.canvas.GraphicsContext;

public class LifeSymbol extends Entity{

	public LifeSymbol(double posX, double posY) {
		setPosition(posX, posY);
		setImage("life.png");
	}

	@Override
	public void render(GraphicsContext gameSpace) {
		gameSpace.drawImage(getImage(), getPositionX(), getPositionY());
	}
}
