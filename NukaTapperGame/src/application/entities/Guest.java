package application.entities;


public class Guest extends Entity {
	
	private GuestStatus status;
	private final Bar barOfGuest;
	private Timer timer;
	
	public Guest(Bar bar) {
		super(40, 80, 0, 0);
	
		setPositionY(bar.getPositionY() - 50);
		setPositionX(bar.getPositionX() + bar.getWidth());
		setVelocity(-15, 0);
		status = GuestStatus.COME;
		barOfGuest = bar;
		
//		timer = new Timer(startTime, delayInSec)
		
	}
	
	public GuestStatus getStatus() {
		return status;
	}

	public void setStatus(GuestStatus status) {
		this.status = status;
	}
	
	public double getDistanceFromDoor() {
		return barOfGuest.getEndPointInX() - getPositionX();
	}
	
	public double getDistanceFromBarFront() {
		return barOfGuest.getPositionX() - this.getPositionX();
	}
	
	public void come() {
		
	}
	

}
