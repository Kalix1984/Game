package application.guest;

import application.RandomGenerator;

public class DistanceGenerator {
	
	private final RandomGenerator random;
	private int minDistance;
	private int maxDistance;
	
	
	public int distance;
	public double actual;

	public DistanceGenerator(RandomGenerator random, int minDistance, int maxDistance) {
		this.random = random;
		this.minDistance = minDistance;
		this.maxDistance = maxDistance;
		this.distance = generateRandomDistance();
		this.actual = distance;
	}

	private int generateRandomDistance() {
		return random.generateInt(minDistance, maxDistance);
	}
	
//	public void setDistance() {
//		distance = generateRandomDistance();
//	}
	
	public void decrease(double distanceTraveled) {
		actual += distanceTraveled;  
	}
	
	public boolean isDestinationReached() {
		return actual <= 0;
	}
	
	
}
