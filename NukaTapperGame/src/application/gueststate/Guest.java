package application.gueststate;

import java.util.List;

import application.RandomGenerator;
import application.alternator.Alternator;
import application.entities.Bar;
import application.entities.Boundary;
import application.entities.Mob;
import application.entities.OnBar;

public class Guest extends Mob {

	private Boundary boundary;
	private GuestState state;
	private final OnBar actualBar;
	private final RandomGenerator random;
	private double speed;
	private double distanceToMove;
	private long timeToWait = 1_000_000_000 * 10;
	private long timeWhenMoveAgain;
	private boolean wait;

	public Guest(OnBar actualBar, List<Bar> bars, double speed) {
		random = new RandomGenerator();
		wait = true;
		this.speed = speed;
		state = GuestState.COME;
		this.actualBar = actualBar;

		setWidth(40);
		setHeight(80);

		setPositionX(bars.get(actualBar.getIndex()).getEndX() + 5);
		setPositionY(bars.get(actualBar.getIndex()).getPositionY() - 50);

		boundary = new Boundary(bars.get(actualBar.getIndex()).getStarX(),
				bars.get(actualBar.getIndex()).getEndX() + 55);
	}

	private long now() {
		return System.nanoTime();
	}

	private void generateTimeWhenMoveAgain() {
		timeWhenMoveAgain = calculateTimeWhenMoveAgain();
	}

	private void generateRandomDistance(int min, int max) {
		distanceToMove = random.generateInt(min, max);
	}

	private long calculateTimeWhenMoveAgain() {
		return System.nanoTime() + timeToWait;
	}

	public GuestState getState() {
		return state;
	}

	public void setState(GuestState state) {
		this.state = state;
	}

	public Boundary getBoundary() {
		return boundary;
	}

	@Override
	public void move(double deltaTime) {
		if (state == GuestState.COME) {
			if (wait) {
				if (timeWhenMoveAgain < now()) {
					generateTimeWhenMoveAgain();
					setVelocityX(0);
					System.out.println("W");
				} else if (timeWhenMoveAgain >= now()) {
					wait = false;
				}
			} else {
				if (distanceToMove < 1.0) {
					generateRandomDistance(20, 50);
				} else if (distanceToMove > 1.0) {
					setVelocityX(-1 * speed);
					distanceToMove += getVelocityX() * deltaTime;
				}
			}
		}
	setPositionX(getPositionX() + getVelocityX() * deltaTime);
	}

//		} else if (state == GuestState.COME && getPositionX() <= boundary.getMinPos()) {
//			setVelocityX(0 * speed);
//			state = GuestState.ANGRY;
//		}

//	}

	@Override
	public void update(double deltaTime) {
//		if (distanceToMove < 1.0) {
//			generateRandomDistance(30, 80);
//
//			setTimeUntilWait();
//			state = GuestState.WAIT;
//		}
//
		move(deltaTime);

	}

}
