package application.entities;


public class Player extends Entity {

	private OnBar actualBar;
	private Level level;
	private int life;
	private int score;
	
	public Player() {
		super();
		life = 4;
		actualBar = OnBar.BAR4;
		level = Level.LEVEL1;
		score = 0;
	}
	
	public Player(double width, double height, double posX, double posY) {
		super(width, height, posX, posY);
		life = 4;
		actualBar = OnBar.BAR4;
		level = Level.LEVEL1;
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

	private void setPlayerPosY(OnBar playerPos) {
		this.actualBar = playerPos;
	}
	
	public boolean changePlayerPosUp() {
		
		switch (actualBar) {
		case BAR4:
			setPlayerPosY(actualBar.BAR3);
			break;
		case BAR3:
			setPlayerPosY(actualBar.BAR2);
			break;
		case BAR2:
			setPlayerPosY(actualBar.BAR1);
			break;
		case BAR1:
			return false;
		}
		
		return true;
	}
	
	public boolean changePlayerPosDown() {
		
		switch (actualBar) {
		case BAR1:
			setPlayerPosY(actualBar.BAR2);
			break;
		case BAR2:
			setPlayerPosY(actualBar.BAR3);
			break;
		case BAR3:
			setPlayerPosY(actualBar.BAR4);
			break;
		case BAR4:
			return false;
		}
		
		return true;
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
