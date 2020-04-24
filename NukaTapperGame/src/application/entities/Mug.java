package application.entities;

public class Mug extends Entity {
	private final double startPosX;
	private final double endPosX;
	private OnBar pos;
	private MugStatus status;
	
	public Mug(Bar actualBar) {
		super(20, 30, 0, 0);
		status = MugStatus.FORWARD;
		
		setPositionX(actualBar.getPositionX());
		setPositionY(actualBar.getPositionY() - 20);
		
		startPosX = getPositionX();
		endPosX = actualBar.getEndPointInX() - getWidth();
		
		setVelocity(150, 0);
		
	}
	
	public boolean isMugInTheEndOfBar() {
		
		return getPositionX() >= endPosX;
	}



}
