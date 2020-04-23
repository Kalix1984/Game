package application.entities;


public class Guest extends Entity {
	
	private GuestStatus status;
	
	public Guest(Bar bar) {
		super(40, 80, 0, 0);
	
		setPositionY(bar.getPositionY() - 50);
		setPositionX(bar.getPositionX() + bar.getWidth());
		
		this.status = GuestStatus.COME;
		
	}
	
	public GuestStatus getStatus() {
		return status;
	}

	public void setStatus(GuestStatus status) {
		this.status = status;
	}
	
	public double getDistanceFromDoor(Bar bar) {
		return bar.getEndPointInX() - getPositionX();
	}
	
	

}
