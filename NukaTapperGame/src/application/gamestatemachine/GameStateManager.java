package application.gamestatemachine;

import java.util.Iterator;
import java.util.List;

import application.GameStats;
import application.entities.guest.Guest;
import application.entities.guest.GuestState;
import application.entities.mug.Mug;
import application.entities.mug.MugState;
import application.entities.player.Player;

public class GameStateManager {
	private Player player;
	private List<Mug> mugs;
	private List<Guest> guests;
	private GameStats gameStats;

	private GameState gameState;

	public GameStateManager(Player player, List<Mug> mugs, List<Guest> guests, GameStats gameStats) {
		this.player = player;
		this.mugs = mugs;
		this.guests = guests;
		this.gameStats = gameStats;
		this.gameState = GameState.INIT_LEVEL;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void changeGameState(GameState newState) {
		this.gameState = newState;
	}

	public GameState checkGameState() {
		if (isBrokenMugInTheList() || isAngryGuestInTheList() && !isGameOver()) {
			return GameState.LOSE_LIFE;
		} else if (isGameOver()) {
			return GameState.GAME_OVER;
		} else if (isLevelCompleted()) {
			return GameState.INIT_LEVEL;
		}

		return GameState.RUNNING;
	}

	private boolean isLevelCompleted() {
		return guests.isEmpty();
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

	public void removeGuests() {
		Iterator<Guest> guestListIterator = guests.iterator();
		while (guestListIterator.hasNext()) {
			Guest guest = guestListIterator.next();
			if (guest.isRemovable()) {
				guestListIterator.remove();
			}
		}
	}

	public void removeMugs() {
		Iterator<Mug> mugListIterator = mugs.iterator();
		while (mugListIterator.hasNext()) {
			Mug mug = mugListIterator.next();
			if (mug.isRemovable()) {
				mugListIterator.remove();
			}
		}
	}

	public void checkCatches() {
		for (Guest guest : guests) {
			switch (guest.getState()) {
			case IN_COME_MOTION:
				if (isMugCatchedByGuest(guest)) {
					gameStats.addScore(100);
				}
				break;
			case IN_COME_IDLE:
				if (isMugCatchedByGuest(guest)) {
					gameStats.addScore(100);
				}
				break;
			default:
				break;
			}

		}

		if (isMugCatchedByPlayer(player)) {
			gameStats.addScore(200);
		}
	}

	private boolean isMugCatchedByGuest(Guest guest) {
		for (Mug mug : mugs) {
			if (mug.getState() == MugState.FORWARD && guest.intersects(mug)) {
				guest.setState(GuestState.ENTER_LEAVE_IN_MOTION);
				mug.setState(MugState.IN_GUEST_HANDS);

				return true;
			}
		}
		return false;
	}

	private boolean isMugCatchedByPlayer(Player player) {
		for (Mug mug : mugs) {

			if (mug.getState() == MugState.BACKWARD && player.intersects(mug)) {
				mug.setState(MugState.IN_PLAYER_HANDS);

				System.out.println("belépett ide");

				return true;
			}
		}
		return false;
	}
}
