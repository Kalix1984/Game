package application.entities;

public class Door extends Entity {

	public Door(Bar bar) {
		

		setPositionY(bar.getPositionY() - 50);
		setPositionX(bar.getPositionX() + bar.getWidth() + 5);
	}




}
