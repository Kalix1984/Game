package application.entities;

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

}
