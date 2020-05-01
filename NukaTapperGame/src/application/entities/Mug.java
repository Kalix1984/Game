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

		if (owner instanceof Player) {
			status = MugStatus.FORWARD;
		} else if (owner instanceof Guest) {
			status = MugStatus.BACKWARD;
		}

		setImage("beer.png");
		setPosition(owner.getPositionX(), owner.getPositionY() + 0);
		setBounds(); //Ez nullpointer hibát okoz
	}

	@Override
	public void move() {
		if (status == MugStatus.FORWARD /*&& bounds.getMaxPos() > getPositionX()*/) {
			setVelocityX(200);
		}else {
			setVelocityX(0);
			
		}
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
		bounds.setBounds(bars.get(index).getStarX(), bars.get(index).getEndX() - 30);
		
	}

	@Override
	public void update(double time) {
		move();
		setPositionX(getPositionX() + getVelocityX() * time);
	}

}
