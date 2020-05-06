package application.entities;

import java.util.List;

import application.guest.Guest;

public class Mug extends Mob {
	private OnBar actualBar;
	private MugState state;
	private Mob owner; // Player / Guest
	private List<Bar> bars;
	private Boundary bounds;

	public Mug(Mob owner, List<Bar> bars, OnBar actualBar) {
		this.owner = owner;
		this.bars = bars;
		this.actualBar = actualBar;
		this.bounds = new Boundary(bars.get(actualBar.getIndex()).getStarX(), bars.get(actualBar.getIndex()).getEndX() - 30);

		if (owner instanceof Player) {
			state = MugState.FORWARD;
		} else if (owner instanceof Guest) {
			state = MugState.BACKWARD;
		}

		setImage("beer.png");
		setPosition(owner.getPositionX(), owner.getPositionY() + 0);
	}
	
	public MugState getState() {
		return state;
	}

	public void setState(MugState state) {
		this.state = state;
	}

	public boolean isMugBroken() {
		return state == MugState.BREAK_ONWALL || state == MugState.BREAK_ONFLOOR;
	}

	@Override
	public void move(double deltaTime) {
		if (state == MugState.FORWARD && bounds.getRight() > getPositionX()) {
			setVelocityX(200);
			setMoving(true);
		}else if(state == MugState.BACKWARD && bounds.getLeft() < getPositionX()) {
			setVelocityX(-200);
			setMoving(true);
		}else if(state == MugState.FORWARD && bounds.getRight() <= getPositionX()) {
			setVelocityX(0);
			setMoving(false);
			state = MugState.BREAK_ONWALL;
		}
		
		setPositionX(getPositionX() + getVelocityX() * deltaTime);
	}

	@Override
	public void update(double deltaTime) {
		move(deltaTime);
	}
}
