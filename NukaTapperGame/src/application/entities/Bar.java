package application.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bar extends Entity {
	private double endX;

	public Bar(double width, double height, double posX, double posY) {
		setWidth(width);
		setHeight(height);
		setPosition(posX, posY);
		endX = calculateEndX(posX, width);
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
		gameSpace.setFill(Color.BROWN);
		gameSpace.setImageSmoothing(true);
		gameSpace.fillRect(getPositionX(), getPositionY(), getWidth(), getHeight());
		
		
//		gameSpace.drawImage(getImage(), getPositionX(), getPositionY());
		
	}

}
