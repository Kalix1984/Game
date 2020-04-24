package application;

import java.util.Random;

public class RandomGenerator {
	Random random = new Random();
	
	public int generate(int upperBound) {
		return random.nextInt(upperBound);
	}
}
