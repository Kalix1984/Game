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

	public void setDistance(int minDistance, int maxDistance) {
		distance = generateRandomDistance(minDistance, maxDistance);
		this.actual = distance;
	}

	public void setDistance(int newDistance) {
		distance = newDistance;
		this.actual = distance;
	}

	public void decrease(double distanceTraveled) {
		actual += distanceTraveled;
	}

	public boolean isDestinationReached() {
		return actual <= 0;
	}

}
