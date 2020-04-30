package application.entities;

public class Mug extends Mob {
	private final double startPosX;
	private final double endPosX;
	private OnBar pos;
	private MugStatus status;
	private boolean isAlive;
	private Entity owner; // Player / Guest

	
	//actualBar már nem kell mivel le tudom kérni az owner pozícióját
	public Mug(Bar actualBar, Entity owner) {
		

		if (owner.getClass() == Player.class) {
			status = MugStatus.FORWARD;
			setPositionX(actualBar.getPositionX());
			
		}else if (owner.getClass() == Guest.class) {
			status = MugStatus.BACKWARD;
		}

		setPositionY(actualBar.getPositionY() - 20);

		startPosX = getPositionX();
		endPosX = actualBar.getEndX() - getWidth();

	

	}

	public void setOwner(Entity owner) {
		this.owner = owner;
	}

	public boolean isMugInTheEndOfBar() {

		return getPositionX() >= endPosX;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}




}
