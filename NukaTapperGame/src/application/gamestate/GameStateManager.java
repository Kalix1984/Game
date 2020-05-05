package application.gamestate;

import java.util.List;

import application.GameStats;
import application.entities.Mug;
import application.gueststate.Guest;
import application.gueststate.GuestState;

public class GameStateManager {
	private List<Mug> mugs;
	private List<Guest> guests;
	private GameState gameState;
	private GameStats gameStats; 
	
	public GameStateManager(List<Mug> mugs, List<Guest> guests) {
		this.mugs = mugs;
		this.guests = guests;
		this.gameStats = new GameStats();
		this.gameState = GameState.START_LEVEL;
	}

	public GameState getGameState() {
		return gameState;
	}

	public GameStats getGameStats() {
		return gameStats;
	}
	
	public void changeGameState(GameState newState) {
		this.gameState = newState;
	}
	
	public GameState check() {
		if (isBrokenMugInTheList() || isAngryGuestInTheList() && !isGameOver()) {
			return GameState.LOSE_LIFE;
		}else if (isGameOver()) {
			return GameState.GAME_OVER;
		}
		
		return GameState.RUNNING;
	}
	
	private boolean isGameOver() {
		return gameStats.getLife() == 0;
	}
	
	public void restartLevel() {
		guests.clear();
		mugs.clear();
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
			if (guest.getState() == GuestState.ANGRY) {
				return true;
			}
		}
		return false;
	}
	
	
}
