package application.guest;

import java.util.List;

import application.RandomGenerator;
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
	private DistanceGenerator distance;
	private CountdownTimer timer;
	private boolean wait;

	public Guest(OnBar actualBar, List<Bar> bars, double speed, RandomGenerator random) {
		this.random = random;
		timer = new CountdownTimer(3);
		distance = new DistanceGenerator(random);
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
				setVelocityX(0);

				if (!timer.hasTimeExpired()) {
					timer.decreaseTime(deltaTime);
				} else {
					wait = false;
					timer.restart();
				}
			} else if (!wait) {
				distance.setDistanceOnlyOnce(30, 70);
				
				if (!distance.isDestinationReached()) {
					setVelocityX(-1 * speed);
					distance.decrease(getVelocityX() * deltaTime);
				} else {
					wait = true;
					
					distance = new DistanceGenerator(random);
				}
			}
		} else if (state == GuestState.LEAVE) {
			//Ezt írom éppen még nem működik
			wait = false;
			distance = new DistanceGenerator();
			distance.setDistanceOnlyOnce(200);
			if (!distance.isDestinationReached()) {
				setVelocityX(200);
				distance.decrease( - (getVelocityX() * deltaTime));
				
				System.out.println(distance.actual);
				
			}else {
				setVelocityX(0);
			}
			
			
			
			
			
		}
		
		

		setPositionX(getPositionX() + getVelocityX() * deltaTime);
	}

	@Override
	public void update(double deltaTime) {

		move(deltaTime);

	}

}
