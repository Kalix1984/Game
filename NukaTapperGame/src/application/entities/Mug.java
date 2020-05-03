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
		this.bounds = new Boundary();

		if (owner instanceof Player) {
			status = MugStatus.FORWARD;
		} else if (owner instanceof Guest) {
			status = MugStatus.BACKWARD;
		}

		setImage("beer.png");
		setPosition(owner.getPositionX(), owner.getPositionY() + 0);
		setBounds(); 
	}
	
	public MugStatus getStatus() {
		return status;
	}

	public boolean isMugBroken() {
		
		return status == MugStatus.BREAK_ONWALL || status == MugStatus.BREAK_ONFLOOR;
	}

	@Override
	public void move() {
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
	}

	@Override
	public void setBounds() {
		
		int index = getBarIndex();
	
		bounds.setBounds(bars.get(index).getStarX(), bars.get(index).getEndX() - 30);
		
	}

	private int getBarIndex() {
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
		return index;
	}

	@Override
	public void update(double time) {
		
		move();
		setPositionX(getPositionX() + getVelocityX() * time);
	}

}
