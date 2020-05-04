package application;

import java.util.List;

import application.entities.Guest;
import application.entities.GuestStatus;
import application.entities.Mug;

public class GameStateManager {
	private List<Mug> mugs;
	private List<Guest> guests;
	private GameState gameState;
	private GameStats gameStats; 
//	RUNNING,
//	WIN,
//	GAMEOVER,
//	LOOSELIFE	
	public GameStateManager(List<Mug> mugs, List<Guest> guests) {
		this.mugs = mugs;
		this.guests = guests;
		this.gameStats = new GameStats();
		this.gameState = GameState.STARTLEVEL;
		
	}

	public GameState getGameState() {
		return gameState;
	}

	public GameStats getGameStats() {
		return gameStats;
	}
	
	public void checkGameState() {
		
	}
	
	private boolean isBrokenMugInTheList() {
		for (Mug mug : mugs) {
			if (mug.isMugBroken()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isAngryGuestInTheList() {
		for (Guest guest: guests) {
			if (guest.getStatus() == GuestStatus.ANGRY) {
				return true;
			}
		}
		return false;
	}
	
	
}
