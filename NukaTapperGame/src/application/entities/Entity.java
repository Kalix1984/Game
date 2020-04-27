package application.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Entity {
	private Image image;
	
	private boolean isAlive;

	private double positionX;
	private double positionY;
	private double velocityX;
	private double width;
	private double height;

	//render with Image
	public Entity(double posX, double posY) {
		this.positionX = posX;
		this.positionY = posY;
	}
	
	//render with rect
	public Entity(double width, double height, double posX, double posY) {
		this.width = width;
		this.height = height;
		this.positionX = posX;
		this.positionY = posY;
	}
	
	public void setAlive() {
		this.isAlive = true;
	}
	
	public void setDead() {
		this.isAlive = false;
	}
	
	public boolean isAlive() {
		return this.isAlive;
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

	public void setPosition(int x, int y) {
		positionX = x;
		positionY = y;
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

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public void setVelocity(double x) {
		velocityX = x;
	}

	public void addVelocity(double x) {
		velocityX += x;
	}
	
	public void update(double time) {
		positionX += velocityX * time;
		
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
