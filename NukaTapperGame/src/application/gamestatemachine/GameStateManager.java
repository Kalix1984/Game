package application.gamestatemachine;

import java.util.Iterator;
import java.util.List;

import application.GameStats;
import application.entities.guest.Guest;
import application.entities.guest.GuestState;
import application.entities.mug.Mug;
import application.entities.mug.MugState;

public class GameStateManager {
	private List<Mug> mugs;
	private List<Guest> guests;
	private GameStats gameStats;
	
	private GameState gameState;

	public GameStateManager(List<Mug> mugs, List<Guest> guests, GameStats gameStats) {
		this.mugs = mugs;
		this.guests = guests;
		this.gameStats = gameStats;
		this.gameState = GameState.INIT_LEVEL;
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
		} else if (isGameOver()) {
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
		for (Guest guest : guests) {
			if (guest.getState() == GuestState.ANGRY) {
				return true;
			}
		}
		return false;
	}

	public void removeServedGuestsAndGiveScore() {
		Iterator<Guest> guestListIterator = guests.iterator();
		while (guestListIterator.hasNext()) {
			Guest guest = guestListIterator.next();
			if (guest.isRemovable()) {
				guestListIterator.remove();
				gameStats.addScore(100);
			}
		}
	}

	public void removeEmptyMugs() {
		Iterator<Mug> mugListIterator = mugs.iterator();
		while (mugListIterator.hasNext()) {
			Mug mug = mugListIterator.next();
			if (mug.isRemovable()) {
				mugListIterator.remove();
			}
		}
	}

	public void checkGuestsAreServed() {
		for (Guest guest : guests) {
			switch (guest.getState()) {
			case IN_COME_MOTION:
				checkGuestsAndMugsCollusion(guest);
				break;
			case IN_COME_IDLE:
				checkGuestsAndMugsCollusion(guest);
				break;
			default:
				break;
			}
		}
	}

	private void checkGuestsAndMugsCollusion(Guest guest) {
		for (Mug mug : mugs) {
			if (guest.intersects(mug)) {
				guest.setState(GuestState.ENTER_LEAVE_IN_MOTION);
				mug.setState(MugState.IN_HAND);
			}
		}
	}

}
