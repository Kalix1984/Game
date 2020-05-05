package application.entities;

public class Boundary {
	private double left;
	private double right;

	public Boundary(double left, double right) {
		this.left = left;
		this.right = right;
	}

	public double getLeft() {
		return left;
	}

	public double getRight() {
		return right;
	}

	public void setBounds(double left, double right) {
		this.left = left;
		this.right = right;
	}
}
