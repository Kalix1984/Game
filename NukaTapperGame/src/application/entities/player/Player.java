package application.entities.player;

import java.util.List;

import application.entities.Bar;
import application.entities.Boundary;
import application.entities.Direction;
import application.entities.Mob;
import application.entities.OnBar;
import application.entities.mug.Mug;
import application.input.Keyboard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Mob {

	private OnBar actualBar;
	private Boundary boundary;
	private Keyboard input;
	private List<Bar> bars;
	private List<Mug> mugs;

	public Player(double posX, double posY, Keyboard input, List<Bar> bars, List<Mug> mugs) {

		setPosition(posX, posY);
		this.input = input;

		actualBar = OnBar.BAR4;
		this.bars = bars;
		this.mugs = mugs;
		boundary = new Boundary(bars.get(actualBar.getIndex()).getStarX() - 60,
				bars.get(actualBar.getIndex()).getEndX() - 40);
	}

	private void tapBeer() {
		if (input.isTap() && canTapBeer()) {
			mugs.add(new Mug(this, bars, actualBar));
		}
		input.resetTap();
	}

	private boolean canTapBeer() {

		return boundary.getLeft() == getPositionX();
	}

	@Override
	public void move(double deltaTime) {
		moveHorizontal();
		if (changeActualBar()) {
			moveVertical();
			setBounds();
		}
		
		setPositionX(getPositionX() + getVelocityX() * deltaTime);
	}

	private void moveVertical() {
		setPosition(bars.get(actualBar.getIndex()).getStarX() - 60, bars.get(actualBar.getIndex()).getPositionY() - 40);
	}

	private boolean changeActualBar() {
		if (input.isUp()) {
			switch (actualBar) {
			case BAR4:
				actualBar = OnBar.BAR3;
				break;
			case BAR3:
				actualBar = OnBar.BAR2;
				break;
			case BAR2:
				actualBar = OnBar.BAR1;
				break;
			case BAR1:
				actualBar = OnBar.BAR4;
				break;
			}
			input.resetUp();

			return true;
		} else if (input.isDown()) {
			switch (actualBar) {
			case BAR1:
				actualBar = OnBar.BAR2;
				break;
			case BAR2:
				actualBar = OnBar.BAR3;
				break;
			case BAR3:
				actualBar = OnBar.BAR4;
				break;
			case BAR4:
				actualBar = OnBar.BAR1;
				break;
			}
			input.resetDown();

			return true;
		}
		return false;
	}

	private void moveHorizontal() {
		if (input.isRight() && canMoveRight()) {
			this.setVelocityX(200);
			this.setDir(Direction.RIGHT);
			this.setMoving(true);
		} else if (input.isLeft() && canMoveLeft()) {
			this.setVelocityX(-200);
			this.setDir(Direction.LEFT);
			this.setMoving(true);
		}

		else if (!input.isLeft() || !input.isRight()) {
			this.setVelocityX(0);
			this.setMoving(false);
		}
	}

	@Override
	public void update(double deltaTime) {
		tapBeer();
		move(deltaTime);
		
	}


	public void setBounds() {
		boundary.setBounds(bars.get(actualBar.getIndex()).getStarX() - 60,
				bars.get(actualBar.getIndex()).getEndX() - 40);
	}

	private boolean canMoveLeft() {
		return getPositionX() >= boundary.getLeft();
	}

	private boolean canMoveRight() {
		return getPositionX() <= boundary.getRight();
	}

	@Override
	public void render(GraphicsContext gameSpace) {
		gameSpace.setFill(Color.BLUE);
		gameSpace.setImageSmoothing(true);
		gameSpace.fillRect(getPositionX(), getPositionY(), getWidth(), getHeight());
		
		
//		gameSpace.drawImage(getImage(), getPositionX(), getPositionY());
		
	}

}
