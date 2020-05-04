package application.entities;

import java.util.List;

import application.RandomGenerator;

public class Guest extends Mob {

	private Boundary boundary;
	private GuestStatus status;
	private final OnBar actualBar;
	private final RandomGenerator random;
	private double speed;
	private double distanceToMove;
	private long delay = 1_000_000_000 * 10;
	private long timeToReach;

	public Guest(OnBar actualBar, List<Bar> bars, double speed) {
		random = new RandomGenerator();
		this.speed = speed;		
		status = GuestStatus.COME;
		this.actualBar = actualBar;

		setWidth(40);
		setHeight(80);

		setPositionX(bars.get(actualBar.getIndex()).getEndX() + 5);
		setPositionY(bars.get(actualBar.getIndex()).getPositionY() - 50);

		boundary = new Boundary(bars.get(actualBar.getIndex()).getStarX(),
				bars.get(actualBar.getIndex()).getEndX() + 55);
	}

	public GuestStatus getStatus() {
		return status;
	}

	public void setStatus(GuestStatus status) {
		this.status = status;
	}

	@Override
	public void move(double deltaTime) {
		if (status == GuestStatus.WAIT) {
			setVelocityX(0 * speed);
			if (System.nanoTime() >= timeToReach) {
				status = GuestStatus.COME;
			}
		}

		if (status == GuestStatus.COME && distanceToMove > 0 && getPositionX() > boundary.getMinPos()) {
			setVelocityX(-1 * speed);
			distanceToMove += getVelocityX() * deltaTime;

		} else if (status == GuestStatus.COME && getPositionX() <= boundary.getMinPos()) {
			setVelocityX(0 * speed);
			status = GuestStatus.ANGRY;
		}

		setPositionX(getPositionX() + getVelocityX() * deltaTime);
	}

	@Override
	public void update(double deltaTime) {
		if (distanceToMove < 1.0) {
			generateRandomDistance(30, 80);

			setTimeUntilWait();
			status = GuestStatus.WAIT;
		}

		move(deltaTime);

	}

	private void setTimeUntilWait() {
		timeToReach = System.nanoTime() + delay;
	}

	private void generateRandomDistance(int min, int max) {
		distanceToMove = random.generateInt(min, max);
	}

}
