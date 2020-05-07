package application.entities.mug;

import java.util.List;

import application.entities.Bar;
import application.entities.Boundary;
import application.entities.Mob;
import application.entities.OnBar;
import application.entities.guest.Guest;
import application.entities.player.Player;
import javafx.scene.canvas.GraphicsContext;

public class Mug extends Mob {
	private MugState state;
	private Mob owner; // Player / Guest
	private Boundary bounds;

	public Mug(Mob owner, List<Bar> bars, OnBar actualBar) {
		this.owner = owner;
		this.bounds = new Boundary(bars.get(actualBar.getIndex()).getStarX(),
				bars.get(actualBar.getIndex()).getEndX() - 30);

		if (owner instanceof Player) {
			state = MugState.FORWARD;
		} else if (owner instanceof Guest) {
			state = MugState.BACKWARD;
		}

		setImage("beer.png");
		setPosition(owner.getPositionX(), owner.getPositionY() + 0);
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
				setVelocityX(200);
				setMoving(true);
			}else {
				state = MugState.BREAK_ONWALL;
			}

			break;
			
		case BACKWARD:
			if (getPositionX() > bounds.getLeft()) {
				setVelocityX(-200);
				setMoving(true);
			}else {
				state = MugState.BREAK_ONFLOOR;
			}
			break;
		case IN_HAND:
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