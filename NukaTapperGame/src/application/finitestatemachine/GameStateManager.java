package application.finitestatemachine;

import java.util.Iterator;
import java.util.List;

import application.GameStats;
import application.entities.Bar;
import application.entities.guest.Guest;
import application.entities.guest.GuestState;
import application.entities.mug.Mug;
import application.entities.mug.MugState;
import application.entities.player.Player;
import application.entities.properties.OnBar;
import application.input.Keyboard;

public class GameStateManager {
	
	private List<Mug> mugs;
	private List<Guest> guests;
	private List<Bar> bars;
	private GameStats gameStats;

	private GameState gameState;

	public GameStateManager(List<Mug> mugs, List<Guest> guests, List<Bar> bars, GameStats gameStats) {
		this.mugs = mugs;
		this.guests = guests;
		this.bars = bars;
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

	public void addGuests() {
		guests.add(new Guest(OnBar.BAR1, bars, mugs, 30));
		guests.add(new Guest(OnBar.BAR2, bars, mugs, 30));
		guests.add(new Guest(OnBar.BAR3, bars, mugs, 30));
		guests.add(new Guest(OnBar.BAR4, bars, mugs, 30));
		
		

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
		addGuests();
	}

	public void removeAllRemainingMugs() {
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

//	public void respawnPlayer() {
//		
//		player = new Player(40, 460, keyListener, bars, mugs);
//		player.setWidth(40);
//		player.setHeight(80);
//	}

	public void checkCatches(Player player) {
		for (Guest guest : guests) {
			switch (guest.getState()) {
			case IN_COME:
				if (isMugCatchedByGuest(guest)) {
					gameStats.addScore(100);
				}
				break;
			case IN_WAIT:
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
				guest.setState(GuestState.ENTER_LEAVE);
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

				return true;
			}
		}
		return false;
	}
}
