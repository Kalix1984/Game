package application.indicatorsview;

import application.GameStats;
import application.entities.Entity;

public abstract class Indicator extends Entity {

	private GameStats gameStats;

	public Indicator(double posX, double posY, GameStats gameStats) {
		setPosition(posX, posY);
		this.gameStats = gameStats;
	}
}
