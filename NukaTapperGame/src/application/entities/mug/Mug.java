package application.entities.mug;

import java.util.List;

import application.entities.Bar;
import application.entities.Mob;
import application.entities.guest.Guest;
import application.entities.player.Player;
import application.entities.properties.Boundary;
import application.entities.properties.OnBar;
import javafx.scene.canvas.GraphicsContext;

public class Mug extends Mob {
	private MugState state;
	private Boundary bounds;
	private int speed;

	public Mug(Mob owner, List<Bar> bars, OnBar actualBar) {
		this.bounds = new Boundary(bars.get(actualBar.getIndex()).getStarX() - 30,
				bars.get(actualBar.getIndex()).getEndX() - 30);

		setWidth(30);
		setHeight(50);
		
		if (owner instanceof Player) {
			setPosition(owner.getPositionX(), owner.getPositionY());
			speed = 200; 
			state = MugState.FORWARD;
			setImage("beer.png");
			
		} else if (owner instanceof Guest) {
			setPosition(owner.getPositionX()- getWidth() - 5, owner.getPositionY() + 10);
			speed = -50;
			state = MugState.BACKWARD;
			setImage("empty.png");
		}
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
		switch (state) {
		case FORWARD:
			if (getPositionX() < bounds.getRight()) {
				setVelocityX(speed);
				setMoving(true);
			}else {
				state = MugState.BREAK_ONWALL;
			}
			break;
			
		case BACKWARD:
			if (getPositionX() > bounds.getLeft()) {
				setVelocityX(speed);
				setMoving(true);
			}else {
				state = MugState.BREAK_ONFLOOR;
			}
			break;
			
		case IN_GUEST_HANDS:
			setVelocityX(0);
			setRemovable(true);
			break;
			
		case IN_PLAYER_HANDS:
			setVelocityX(0);
			setRemovable(true);
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
		gameSpace.drawImage(getImage(), getPositionX(), getPositionY());
		
	}
}
