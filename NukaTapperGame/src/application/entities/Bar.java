package application.entities;

import javafx.scene.canvas.GraphicsContext;

public class Bar extends Entity {
	private double endX;

	public Bar(double width, double height, double posX, double posY) {
		setPosition(posX, posY);
		endX = calculateEndX(posX, width);

		setImage("bar.png");

		setWidth(width);
		setHeight(height);
	}

	public double getEndX() {
		return endX;
	}

	public double getStarX() {
		return getPositionX();
	}

	private double calculateEndX(double posX, double width) {
		return posX + width;
	}

	@Override
	public void render(GraphicsContext gameSpace) {
		gameSpace.drawImage(getImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
	}
}
