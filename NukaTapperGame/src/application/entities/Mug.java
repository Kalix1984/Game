package application.entities;

public class Mug extends Entity {
	private final double startPosX;
	private final double endPosX;
	private OnBar pos;
	private MugStatus status;
	private boolean isAlive;
	private Entity owner; // Player / Guest

	
	//actualBar már nem kell mivel le tudom kérni az owner pozícióját
	public Mug(Bar actualBar, Entity owner) {
		super(20, 30, 0, 0);

		if (owner.getClass() == Player.class) {
			status = MugStatus.FORWARD;
			setPositionX(actualBar.getPositionX());
			
		}else if (owner.getClass() == Guest.class) {
			status = MugStatus.BACKWARD;
		}

		setPositionY(actualBar.getPositionY() - 20);

		startPosX = getPositionX();
		endPosX = actualBar.getEndPointInX() - getWidth();

		setVelocity(150);

	}

	public void setOwner(Entity owner) {
		this.owner = owner;
	}

	public boolean isMugInTheEndOfBar() {

		return getPositionX() >= endPosX;
	}


}
