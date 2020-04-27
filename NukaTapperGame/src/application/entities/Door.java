package application.entities;

public class Door extends Entity {

	public Door(Bar bar) {
		super(40, 80, 0, 0);

		setPositionY(bar.getPositionY() - 50);
		setPositionX(bar.getPositionX() + bar.getWidth() + 5);
	}



}
