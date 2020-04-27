package application.entities;


public class Player extends Entity {

	private OnBar actualBar;
	private Direction direction;
	private LevelName level;
	
	private String playerName;
	private int life;
	private int score;
	
	public Player(double width, double height, double posX, double posY) {
		super(width, height, posX, posY);
		life = 4;
		actualBar = OnBar.BAR4;
		level = LevelName.LEVEL1;
		score = 0;
	}
	
	public int getLife() {
		return life;
	}

	public void looseLife() {
		this.life -= 1;
	}

	public OnBar getActualBar() {
		return this.actualBar;
	}

	private void setActualBar(OnBar playerPos) {
		this.actualBar = playerPos;
	}
	
	public boolean changeBarUp() {
		
		switch (actualBar) {
		case BAR4:
			setActualBar(actualBar.BAR3);
			break;
		case BAR3:
			setActualBar(actualBar.BAR2);
			break;
		case BAR2:
			setActualBar(actualBar.BAR1);
			break;
		case BAR1:
			setActualBar(actualBar.BAR4);
			break;
		}
		
		return true;
	}
	
	public boolean changeBarDown() {
		
		switch (actualBar) {
		case BAR1:
			setActualBar(actualBar.BAR2);
			break;
		case BAR2:
			setActualBar(actualBar.BAR3);
			break;
		case BAR3:
			setActualBar(actualBar.BAR4);
			break;
		case BAR4:
			setActualBar(actualBar.BAR1);
			break;
		}
		
		return true;
	}


	public LevelName getLevel() {
		return level;
	}


	public void setLevel(LevelName level) {
		this.level = level;
	}


	public int getScore() {
		return score;
	}


	public void addScore(int plusScore) {
		this.score += plusScore;
	}




}
