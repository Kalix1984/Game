package application.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Entity {
	private Image image;

	private double positionX;
	private double positionY;
	private double width;
	private double height;

	private boolean isRemovable;

	public boolean isRemovable() {
		return isRemovable;
	}

	public void setRemovable(boolean isRemovable) {
		this.isRemovable = isRemovable;
	}

	public Image getImage() {
		return image;
	}

	private void setImage(Image image) {
		this.image = image;
		width = (int) image.getWidth();
		height = (int) image.getHeight();
	}

	public void setImage(String url) {
		Image i = new Image(url);
		setImage(i);
	}

	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	
	public abstract void render(GraphicsContext gameSpace);
	
//	public void renderWithImage(GraphicsContext gameSpace) {
//		gameSpace.drawImage(image, positionX, positionY);
//	}

	public void renderWithRect(GraphicsContext gameSpace, Color color) {
		gameSpace.setFill(color);
		gameSpace.setImageSmoothing(true);
		gameSpace.fillRect(positionX, positionY, width, height);

	}

	public Rectangle2D getEntityBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}

	public boolean intersects(Entity s) {
		return s.getEntityBoundary().intersects(this.getEntityBoundary());
	}

}
