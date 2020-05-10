package application.sql;

public class Winner {
	private String name;
	private int score;
	
	public Winner(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
}
