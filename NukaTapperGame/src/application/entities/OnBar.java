package application.entities;

public enum OnBar {
	BAR1(0), BAR2(1), BAR3(2), BAR4(3);

	private final int index;

	private OnBar(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
