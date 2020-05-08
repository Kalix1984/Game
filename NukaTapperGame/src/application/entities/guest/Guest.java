package application.entities.guest;

import java.util.List;

import application.entities.Bar;
import application.entities.Mob;
import application.entities.motionmodifiers.CountdownTimer;
import application.entities.motionmodifiers.Route;
import application.entities.mug.Mug;
import application.entities.properties.Boundary;
import application.entities.properties.OnBar;
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
	

	public Guest(double posX, OnBar actualBar, List<Bar> bars, List<Mug> mugs, double speed) {
		this.speed = speed;
		state = GuestState.ENTER_WAIT;
		this.actualBar = actualBar;
		this.mugs = mugs;
		this.bars = bars;

		setWidth(40);
		setHeight(80);
		
		setPositionX(posX);
		setPositionY(bars.get(actualBar.getIndex()).getPositionY() - 50);

		boundary = new Boundary(bars.get(actualBar.getIndex()).getStarX(), bars.get(actualBar.getIndex()).getEndX());

	}

	public OnBar getActualBar() {
		return actualBar;
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
		case ENTER_WAIT:
			timer = new CountdownTimer(2);
			setVelocityX(0);
			state = GuestState.IN_WAIT;
			break;

		case IN_WAIT:
			if (!timer.hasTimeExpired()) {
				timer.decreaseTime(deltaTime);
			} else {
				state = GuestState.EXIT_WAIT;
			}
			break;

		case EXIT_WAIT:
			timer = null;
			state = GuestState.ENTER_COME;
			break;

		case ENTER_COME:
			route = new Route(30, 70);
			state = GuestState.IN_COME;
			break;

		case IN_COME:
			if (!route.isDestinationReached() && getPositionX() > boundary.getLeft()) {
				setVelocityX(-1 * speed);
				route.decrease(getVelocityX() * deltaTime);
			} else if (route.isDestinationReached() && getPositionX() > boundary.getLeft()) {
				state = GuestState.EXIT_COME;
			} else if (getPositionX() <= boundary.getLeft()) {
				state = GuestState.ANGRY;
			}
			break;

		case EXIT_COME:
			route = null;
			state = GuestState.ENTER_WAIT;
			break;

		case ENTER_LEAVE:
			route = new Route(150);
			state = GuestState.IN_LEAVE;
			break;

		case IN_LEAVE:
			if (!route.isDestinationReached() && getPositionX() < boundary.getRight()) {
				setVelocityX(200);
				route.decrease(getVelocityX() * deltaTime * -1);
			} else {
				state = GuestState.EXIT_LEAVE;
			}
			break;

		case EXIT_LEAVE:
			route = null;

			if (getPositionX() >= boundary.getRight()) {
				setVelocityX(0);
				setRemovable(true);
			} else {
				state = GuestState.ASK_MORE_AND_COME;
			}
			break;

		case ASK_MORE_AND_COME:
			returnEmptyMug();
			state = GuestState.ENTER_WAIT;
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
