package application.entities;

public class Timer {
	private long startTime;
	private long finishTime;
	
	
	public Timer(long startTime, int delayInSec) {
		this.startTime = startTime;
		this.finishTime = startTime + delayInSec * 1_000_000_000L;
				
		
	}

	public boolean isDelayReached(long currentTime) {
		
		return finishTime >= currentTime;
	}
}
