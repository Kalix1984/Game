package application.entities.guest;

import java.util.List;

import application.RandomGenerator;
import application.entities.Bar;
import application.entities.Boundary;
import application.entities.Mob;
import application.entities.OnBar;
import application.entities.motionmodifiers.CountdownTimer;
import application.entities.motionmodifiers.Route;
import application.entities.mug.Mug;
import application.gamestatemachine.GameState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Guest extends Mob {

	private Boundary boundary;
	private GuestState state;
	private final OnBar actualBar;
	private double speed;
	private Route route;
	private CountdownTimer timer;
	private List<Mug> mugs;
	private List<Bar> bars;

	public Guest(OnBar actualBar, List<Bar> bars, List<Mug> mugs, double speed) {
		this.speed = speed;
		state = GuestState.ENTER_COME_IDLE;
		this.actualBar = actualBar;
		this.mugs = mugs;
		this.bars = bars;

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

	private void returnEmptyMug() {
		mugs.add(new Mug(this, bars, actualBar));
		
	}

	@Override
	public void move(double deltaTime) {

		switch (state) {
		case ENTER_COME_IDLE:
			timer = new CountdownTimer(2);
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
			route = new Route(30, 70);
			state = GuestState.IN_COME_MOTION;
			break;

		case IN_COME_MOTION:
			if (!route.isDestinationReached() && getPositionX() > boundary.getLeft()) {
				setVelocityX(-1 * speed);
				route.decrease(getVelocityX() * deltaTime);
			} else if (route.isDestinationReached() && getPositionX() > boundary.getLeft()) {
				state = GuestState.EXIT_COME_MOTION;
			} else if (getPositionX() <= boundary.getLeft()) {
				state = GuestState.ANGRY;
			}
			break;

		case EXIT_COME_MOTION:
			route = null;
			state = GuestState.ENTER_COME_IDLE;
			break;

		case ENTER_LEAVE_IN_MOTION:
			route = new Route(150);
			state = GuestState.IN_LEAVE_IN_MOTION;
			break;

		case IN_LEAVE_IN_MOTION:
			if (!route.isDestinationReached() && getPositionX() < boundary.getRight()) {
				setVelocityX(200);
				route.decrease(getVelocityX() * deltaTime * -1);
			} else {
				state = GuestState.EXIT_LEAVE_IN_MOTION;
			}
			break;

		case EXIT_LEAVE_IN_MOTION:
			route = null;

			if (getPositionX() >= boundary.getRight()) {
				setVelocityX(0);
				setRemovable(true);
			} else {
				state = GuestState.ASK_MORE_AND_COME;
//				state = GuestState.ENTER_COME_IDLE;
			}
			break;

		case ASK_MORE_AND_COME:
//			setVelocityX(0);
			returnEmptyMug();
			state = GuestState.ENTER_COME_IDLE;
			break;
		default:
			break;
		}

		setPositionX(getPositionX() + getVelocityX() * deltaTime);
	}

	@Override
	public void update(double deltaTime) {
		move(deltaTime);
	}

	@Override
	public void render(GraphicsContext gameSpace) {
		gameSpace.setFill(Color.GREEN);
		gameSpace.setImageSmoothing(true);
		gameSpace.fillRect(getPositionX(), getPositionY(), getWidth(), getHeight());
		
	}

}
