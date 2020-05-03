package application.entities;

import java.util.List;
import application.input.Keyboard;

public class Player extends Mob {

	private OnBar actualBar;
	private Boundary boundary;
	private Keyboard input;
	private List<Bar> bars;
	private List<Mug> mugs;

	public Player(double posX, double posY, Keyboard input, List<Bar> bars, List<Mug> mugs) {

		this.setPosition(posX, posY);
		this.input = input;

		actualBar = OnBar.BAR4;
		this.bars = bars;
		this.mugs = mugs;
		boundary = new Boundary();

		boundary.setBounds(bars.get(3).getStarX() - 60, bars.get(3).getEndX() - 40);
	}
	
	public void tapBeer() {
		if (input.isTap() && canTapBeer()) {
			mugs.add(new Mug(this, bars, actualBar));
			System.out.println(mugs.size());
		}
		input.resetTap();
	}

	private boolean canTapBeer() {
		System.out.println("start point");
		return boundary.getMinPos() == getPositionX();
	}

	@Override
	public void move() {
		moveHorizontal();
		if (changeActualBar()) {
			moveVertical();
			setBounds();
		}
	}

	private void moveVertical() {
		switch (actualBar) {
		case BAR1:

			setPosition(bars.get(0).getStarX() - 60, bars.get(0).getPositionY() - 40);
			break;
		case BAR2:
			setPosition(bars.get(1).getStarX() - 60, bars.get(1).getPositionY() - 40);
			break;
		case BAR3:
			setPosition(bars.get(2).getStarX() - 60, bars.get(2).getPositionY() - 40);
			break;
		case BAR4:
			setPosition(bars.get(3).getStarX() - 60, bars.get(3).getPositionY() - 40);
			break;
		}

	}

	private boolean changeActualBar() {
		if (input.isUp()) {
			switch (actualBar) {
			case BAR4:
				actualBar = OnBar.BAR3;
				break;
			case BAR3:
				actualBar = OnBar.BAR2;
				break;
			case BAR2:
				actualBar = OnBar.BAR1;
				break;
			case BAR1:
				actualBar = OnBar.BAR4;
				break;
			}
			input.resetUp();

			return true;
		} else if (input.isDown()) {
			switch (actualBar) {
			case BAR1:
				actualBar = OnBar.BAR2;
				break;
			case BAR2:
				actualBar = OnBar.BAR3;
				break;
			case BAR3:
				actualBar = OnBar.BAR4;
				break;
			case BAR4:
				actualBar = OnBar.BAR1;
				break;
			}
			input.resetDown();

			return true;
		}
		return false;
	}

	private void moveHorizontal() {
		if (input.isRight() && canMoveRight()) {
			this.setVelocityX(200);
			this.setDir(Direction.RIGHT);
			this.setMoving(true);
		} else if (input.isLeft() && canMoveLeft()) {
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


	@Override
	public void setBounds() {
		int index = 0;
		switch (actualBar) {
		case BAR1:
			index = 0;
			break;
		case BAR2:
			index = 1;
			break;
		case BAR3:
			index = 2;
			break;
		case BAR4:
			index = 3;
			break;

		}
		boundary.setBounds(bars.get(index).getStarX() - 60, bars.get(index).getEndX() - 40);
	}

	private boolean canMoveLeft() {
		return getPositionX() > boundary.getMinPos();
	}

	private boolean canMoveRight() {
		return getPositionX() < boundary.getMaxPos();
	}

}
