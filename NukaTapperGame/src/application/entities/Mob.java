package application.entities;

public abstract class Mob extends Entity{
	private Direction dir;
	private boolean isMoving = false;
	private double velocityX;

	public abstract void setBounds();
	public abstract void move();	
	public abstract void update(double time);		
//	public abstract void render();
		
	public boolean collision() {
		return false;
	}
	
	public void setVelocityX(double x) {
		velocityX = x;
	}
	
	public double getVelocityX() {
		return velocityX;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	
	
	

}
