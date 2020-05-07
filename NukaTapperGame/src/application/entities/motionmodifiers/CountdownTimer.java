package application.entities.motionmodifiers;

public class CountdownTimer {
	private double actualValue;
	
	public CountdownTimer(double startValueInSec) {
		this.actualValue = startValueInSec;
	}

	public void	decreaseTime(double elapsedTime) {
		this.actualValue -= elapsedTime;
	}
	
	public boolean hasTimeExpired() {
		return actualValue <= 0;
	}
	
}
