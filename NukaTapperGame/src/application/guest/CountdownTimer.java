package application.guest;

public class CountdownTimer {
	private double startValue;
	private double actualValue;
	
	public CountdownTimer(double startValueInSec) {
		this.startValue = startValueInSec;
		this.actualValue = startValueInSec;
	}

	public void restart() {
		this.actualValue = startValue;
	}

	public void	decreaseTime(double elapsedTime) {
		this.actualValue -= elapsedTime;
	}
	
	public boolean hasTimeExpired() {
		return actualValue <= 0;
	}
	
}
