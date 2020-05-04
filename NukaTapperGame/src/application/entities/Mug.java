package application.entities;

import java.util.List;

public class Mug extends Mob {
	private OnBar actualBar;
	private MugStatus status;
	private Mob owner; // Player / Guest
	private List<Bar> bars;
	private Boundary bounds;

	public Mug(Mob owner, List<Bar> bars, OnBar actualBar) {
		this.owner = owner;
		this.bars = bars;
		this.actualBar = actualBar;
		this.bounds = new Boundary(bars.get(actualBar.getIndex()).getStarX(), bars.get(actualBar.getIndex()).getEndX() - 30);

		if (owner instanceof Player) {
			status = MugStatus.FORWARD;
		} else if (owner instanceof Guest) {
			status = MugStatus.BACKWARD;
		}

		setImage("beer.png");
		setPosition(owner.getPositionX(), owner.getPositionY() + 0);
	}
	
	public MugStatus getStatus() {
		return status;
	}

	public boolean isMugBroken() {
		return status == MugStatus.BREAK_ONWALL || status == MugStatus.BREAK_ONFLOOR;
	}

	@Override
	public void move(double deltaTime) {
		if (status == MugStatus.FORWARD && bounds.getMaxPos() > getPositionX()) {
			setVelocityX(200);
			setMoving(true);
		}else if(status == MugStatus.BACKWARD && bounds.getMinPos() < getPositionX()) {
			setVelocityX(-200);
			setMoving(true);
		}else if(status == MugStatus.FORWARD && bounds.getMaxPos() <= getPositionX()) {
			setVelocityX(0);
			setMoving(false);
			status = MugStatus.BREAK_ONWALL;
		}
		
		setPositionX(getPositionX() + getVelocityX() * deltaTime);
	}

	@Override
	public void update(double deltaTime) {
		move(deltaTime);
	}
}
