package application.entities;

public class Boundary {
	private double minPos;
	private double maxPos;

	public Boundary(double minPos, double maxPos) {
		this.minPos = minPos;
		this.maxPos = maxPos;
	}

	public double getMinPos() {
		return minPos;
	}

	public double getMaxPos() {
		return maxPos;
	}

	public void setBounds(double min, double max) {
		this.minPos = min;
		this.maxPos = max;
	}
}
