package application.entities;


public class Player extends Entity {

	private PlayerPos playerOnBar;
	private Level level;
	private int life;
	private int score;
	
	public Player() {
		life = 4;
		playerOnBar = PlayerPos.BAR4;
		level = Level.LEVEL1;
		score = 0;
	}
	
	
	public int getLife() {
		return life;
	}

	public void looseLife() {
		this.life -= 1;
	}

	public PlayerPos getPlayerOnBar() {
		return this.playerOnBar;
	}

	public void setPlayerOnBar(PlayerPos playerPos) {
		this.playerOnBar = playerPos;
	}


	public Level getLevel() {
		return level;
	}


	public void setLevel(Level level) {
		this.level = level;
	}


	public int getScore() {
		return score;
	}


	public void addScore(int plusScore) {
		this.score += plusScore;
	}

	

}
