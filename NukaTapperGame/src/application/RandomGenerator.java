package application;

import java.util.Random;

public class RandomGenerator {
	Random random = new Random();
	
	public int generateInt(int lowerBound, int upperBound) {
		return random.nextInt(upperBound) + lowerBound;
	}
	
	
}
