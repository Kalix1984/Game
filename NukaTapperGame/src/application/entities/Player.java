package application.entities;


public class Player extends Entity {

	private PlayerPosY playerOnBar;
	private Level level;
	private int life;
	private int score;
	
	public Player() {
		super();
		life = 4;
		playerOnBar = PlayerPosY.BAR4;
		level = Level.LEVEL1;
		score = 0;
	}
	
	public Player(int width, int height, int posX, int posY) {
		super(width, height, posX, posY);
		life = 4;
		playerOnBar = PlayerPosY.BAR4;
		level = Level.LEVEL1;
		score = 0;
	}
	
	public int getLife() {
		return life;
	}

	public void looseLife() {
		this.life -= 1;
	}

	public PlayerPosY getPlayerOnBar() {
		return this.playerOnBar;
	}

	private void setPlayerPosY(PlayerPosY playerPos) {
		this.playerOnBar = playerPos;
	}
	
	public boolean changePlayerPosUp() {
		
		switch (playerOnBar) {
		case BAR4:
			setPlayerPosY(playerOnBar.BAR3);
			break;
		case BAR3:
			setPlayerPosY(playerOnBar.BAR2);
			break;
		case BAR2:
			setPlayerPosY(playerOnBar.BAR1);
			break;
		case BAR1:
			return false;
		}
		
		return true;
	}
	
	public boolean changePlayerPosDown() {
		
		switch (playerOnBar) {
		case BAR1:
			setPlayerPosY(playerOnBar.BAR2);
			break;
		case BAR2:
			setPlayerPosY(playerOnBar.BAR3);
			break;
		case BAR3:
			setPlayerPosY(playerOnBar.BAR4);
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
