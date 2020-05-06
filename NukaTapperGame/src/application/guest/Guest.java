package application.guest;

import java.util.List;

import application.RandomGenerator;
import application.entities.Bar;
import application.entities.Boundary;
import application.entities.Mob;
import application.entities.OnBar;
import application.gamestate.GameState;

public class Guest extends Mob {

	private Boundary boundary;
	private GuestState state;
	private final OnBar actualBar;
	private final RandomGenerator random;
	private double speed;
	private DistanceGenerator distance;
	private CountdownTimer timer;

	public Guest(OnBar actualBar, List<Bar> bars, double speed, RandomGenerator random) {
		this.random = random;
		this.speed = speed;
		state = GuestState.ENTER_COME_IDLE;
		this.actualBar = actualBar;

		setWidth(40);
		setHeight(80);

		setPositionX(bars.get(actualBar.getIndex()).getEndX() + 5);
		setPositionY(bars.get(actualBar.getIndex()).getPositionY() - 50);

		boundary = new Boundary(bars.get(actualBar.getIndex()).getStarX(), bars.get(actualBar.getIndex()).getEndX());

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

		switch (state) {
		case ENTER_COME_IDLE:
			timer = new CountdownTimer(3);
			setVelocityX(0);
			state = GuestState.IN_COME_IDLE;
			break;

		case IN_COME_IDLE:
			if (!timer.hasTimeExpired()) {
				timer.decreaseTime(deltaTime);
			} else {
				state = GuestState.EXIT_COME_IDLE;
			}
			break;

		case EXIT_COME_IDLE:
			timer = null;
			state = GuestState.ENTER_COME_MOTION;
			break;

		case ENTER_COME_MOTION:
			distance = new DistanceGenerator(random);
			distance.setDistance(30, 70);
			state = GuestState.IN_COME_MOTION;
			break;

		case IN_COME_MOTION:
			if (!distance.isDestinationReached() && getPositionX() > boundary.getLeft()) {
				setVelocityX(-1 * speed);
				distance.decrease(getVelocityX() * deltaTime);
			} else if (distance.isDestinationReached() && getPositionX() > boundary.getLeft()) {
				state = GuestState.EXIT_COME_MOTION;
			} else if  (getPositionX() <= boundary.getLeft()) {
				state = GuestState.ANGRY;
			}
			break;

		case EXIT_COME_MOTION:
			distance = null;
			state = GuestState.ENTER_COME_IDLE;
			break;

		case ENTER_LEAVE_IN_MOTION:

			distance = new DistanceGenerator();
			distance.setDistance(150);
			state = GuestState.IN_LEAVE_IN_MOTION;
			break;

		case IN_LEAVE_IN_MOTION:
			if (!distance.isDestinationReached() && getPositionX() < boundary.getRight()) {
				setVelocityX(200);
				distance.decrease(getVelocityX() * deltaTime * -1);
			} else {
				state = GuestState.EXIT_LEAVE_IN_MOTION;
			}
			break;

		case EXIT_LEAVE_IN_MOTION:
			distance = null;
			
			if (getPositionX() >= boundary.getRight()) {

				setVelocityX(0);
				setRemovable(true);
			} else {
				state = GuestState.ENTER_COME_IDLE;
			}

			break;
		}

		setPositionX(getPositionX() + getVelocityX() * deltaTime);
	}

	@Override
	public void update(double deltaTime) {

		move(deltaTime);

	}

}
