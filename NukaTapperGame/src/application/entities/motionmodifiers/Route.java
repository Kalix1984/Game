package application.entities.motionmodifiers;

import application.RandomGenerator;

public class Route {
	private int length;
	private double actual;

	public Route(int length) {
		this.length = length;
		this.actual = length;
	}

	public Route(int minDistance, int maxDistance) {
		length = generateRandomLength(minDistance, maxDistance);
		this.actual = length;
	}

	private int generateRandomLength(int minDistance, int maxDistance) {

		return new RandomGenerator().generateInt(minDistance, maxDistance);
	}

	public void decrease(double distanceTraveled) {
		actual += distanceTraveled;
	}

	public boolean isDestinationReached() {
		return actual <= 0;
	}

}
