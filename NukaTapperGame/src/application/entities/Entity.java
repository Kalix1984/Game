package application.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Entity {
	private Image image;
	
	private boolean isRemoved;

	private double positionX;
	private double positionY;
	private double width;
	private double height;
	
	public void Remove() {
		this.isRemoved = true;
	}
	
	public void Alive() {
		this.isRemoved = false;
	}
	
	public boolean isRemoved() {
		return this.isRemoved;
	}

	public void setImage(Image image) {
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

	public void renderWithImage(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY);
	}

	public void renderWithRect(GraphicsContext gc, Color color) {
		gc.setFill(color);
		gc.setImageSmoothing(true);
		gc.fillRect(positionX, positionY, width, height);

	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}

	public boolean intersects(Entity s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
	
}
