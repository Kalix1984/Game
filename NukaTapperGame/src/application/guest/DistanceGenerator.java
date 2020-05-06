package application.guest;

import application.RandomGenerator;

public class DistanceGenerator {

	private final RandomGenerator random;

	public int distance;
	public double actual;

	public DistanceGenerator() {
		random = null;
	}

	public DistanceGenerator(RandomGenerator random) {
		this.random = random;
	}

	private int generateRandomDistance(int minDistance, int maxDistance) {
		return random.generateInt(minDistance, maxDistance);
	}

	public void setDistanceOnlyOnce(int minDistance, int maxDistance) {

		if (distance == 0) {
			distance = generateRandomDistance(minDistance, maxDistance);
			this.actual = distance;
		}
	}
	
	public void setDistanceOnlyOnce(int newDistance) {
		
		if (distance == 0) {
			distance = newDistance;
			this.actual = distance;
			
			
			System.out.println("only once");
		}
	}

	public void decrease(double distanceTraveled) {
		actual += distanceTraveled;
	}

	public boolean isDestinationReached() {
		return actual <= 0;
	}

}
