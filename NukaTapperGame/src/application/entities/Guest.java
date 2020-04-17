package application.entities;


public class Guest extends Entity {
	
	private GuestStatus status;
	private boolean isVisible;
	
	public Guest(Bar bar) {
		super(40, 80, 0, 0);
	
		setPositionY(bar.getPositionY() - 50);
		setPositionX(bar.getPositionX() + bar.getWidth());
		
		this.status = GuestStatus.COME;
		this.isVisible = false;
		
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	
	public GuestStatus getStatus() {
		return status;
	}

	public void setStatus(GuestStatus status) {
		this.status = status;
	}
	
	

}
