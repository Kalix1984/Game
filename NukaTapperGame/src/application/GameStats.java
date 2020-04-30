package application;

public class GameStats {
	private String playerName;
	private int life;
	private int score;
	private int level;
	
	
	public GameStats() {
		playerName = "ismeretlen";
		life = 4;
		level = 1;

	}
	
	public int getLife() {
		return life;
	}

	public void looseLife() {
		this.life -= 1;
	}
	
	public int getScore() {
		return score;
	}

	public void addScore(int plusScore) {
		this.score += plusScore;
	}
	
	public int getLevel() {
		return level;
	}

	public void reachNewLevel() {
		this.level += 1;
	}
	
}
