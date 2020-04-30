package application.entities;

import application.input.Keyboard;

public class Player extends Mob {

	private OnBar actualBar;
	
	private Keyboard input;

	public Player(double posX, double posY, Keyboard input) {

		this.setPosition(posX, posY);
		this.input = input;
	
		actualBar = OnBar.BAR4;
	}

	@Override
	public void move() {
		moveHorizontal();
		moveVertical();
	}

	private void moveVertical() {
		// TODO Auto-generated method stub
		
	}

	private void moveHorizontal() {
		if (input.isRight()) {
			this.setVelocityX(200);
			this.setDir(Direction.RIGHT);
			this.setMoving(true);
		} 

		else if (input.isLeft()) {
			this.setVelocityX(-200);
			this.setDir(Direction.LEFT);
			this.setMoving(true);
		}
		
		else if (!input.isLeft() || !input.isRight()) {
			this.setVelocityX(0);
			this.setMoving(false);
		}
	}

	@Override
	public void update(double time) {
		move();
		setPositionX(getPositionX() + getVelocityX() * time);
	}

	public void render() {

	}

	public OnBar getActualBar() {
		return this.actualBar;
	}

	private void setActualBar(OnBar playerPos) {
		this.actualBar = playerPos;
	}

	public boolean changeBarUp() {

		switch (actualBar) {
		case BAR4:
			setActualBar(actualBar.BAR3);
			break;
		case BAR3:
			setActualBar(actualBar.BAR2);
			break;
		case BAR2:
			setActualBar(actualBar.BAR1);
			break;
		case BAR1:
			setActualBar(actualBar.BAR4);
			break;
		}

		return true;
	}

	public boolean changeBarDown() {

		switch (actualBar) {
		case BAR1:
			setActualBar(actualBar.BAR2);
			break;
		case BAR2:
			setActualBar(actualBar.BAR3);
			break;
		case BAR3:
			setActualBar(actualBar.BAR4);
			break;
		case BAR4:
			setActualBar(actualBar.BAR1);
			break;
		}

		return true;
	}

}
