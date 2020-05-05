package application.alternator;

public class Alternator {
	private boolean value;

	public Alternator(boolean startCase) {
		this.value = startCase;
	}

	public void alternate() {
		if (value) {
			value = false;
		}
		value = true;
	}

	public boolean getValue() {
		return value;
	}
	
	

}
