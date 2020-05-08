package application.finitestatemachine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import application.GameStats;
import application.RandomGenerator;
import application.entities.Bar;
import application.entities.guest.Guest;
import application.entities.guest.GuestState;
import application.entities.mug.Mug;
import application.entities.mug.MugState;
import application.entities.player.Player;
import application.entities.properties.OnBar;

public class GameStateManager {

	private List<Mug> mugs;
	private List<List<Guest>> allGuests;
	private List<Bar> bars;
	private GameStats gameStats;

	private GameState gameState;

	private RandomGenerator random;

	public GameStateManager(List<Mug> mugs, List<List<Guest>> allGuests, List<Bar> bars, GameStats gameStats) {
		this.mugs = mugs;
		this.allGuests = allGuests;
		this.bars = bars;
		this.gameStats = gameStats;
		this.gameState = GameState.INIT_LEVEL;
		random = new RandomGenerator();
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

	public void initGuests() {
		List<Guest> bar1 = new ArrayList<>();
		List<Guest> bar2 = new ArrayList<>();
		List<Guest> bar3 = new ArrayList<>();
		List<Guest> bar4 = new ArrayList<>();

		int actualLevel = gameStats.getLevel();
		int speedMutator = 30 * actualLevel;

		for (int i = 0; i < random.generateInt(1, actualLevel); i++) {
			bar1.add(new Guest(bars.get(0).getEndX() + 5 + i * 70, OnBar.BAR1, bars, mugs, speedMutator));
		}

		for (int i = 0; i < random.generateInt(1, actualLevel); i++) {
			bar2.add(new Guest(bars.get(1).getEndX() + 5 + i * 70, OnBar.BAR2, bars, mugs, speedMutator));
		}

		for (int i = 0; i < random.generateInt(1, actualLevel); i++) {
			bar3.add(new Guest(bars.get(2).getEndX() + 5 + i * 70, OnBar.BAR3, bars, mugs, speedMutator));
		}

		for (int i = 0; i < random.generateInt(1, actualLevel); i++) {
			bar4.add(new Guest(bars.get(3).getEndX() + 5 + i * 70, OnBar.BAR4, bars, mugs, speedMutator));
		}

		allGuests.add(bar1);
		allGuests.add(bar2);
		allGuests.add(bar3);
		allGuests.add(bar4);
	}

	private boolean isLevelCompleted() {
		for (List<Guest> list : allGuests) {
			if (!list.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	private boolean isGameOver() {
		return gameStats.getLife() == 0;
	}

	public void restartLevel() {
		for (List<Guest> list : allGuests) {
			list.clear();
		}

		mugs.clear();
		initGuests();
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
		for (List<Guest> list : allGuests) {
			for (Guest guest : list) {
				if (guest.getState() == GuestState.ANGRY) {
					return true;
				}
			}
		}

		return false;
	}

	public void removeGuests() {
		for (List<Guest> list : allGuests) {
			Iterator<Guest> guestListIterator = list.iterator();
			while (guestListIterator.hasNext()) {
				Guest guest = guestListIterator.next();
				if (guest.isRemovable()) {
					guestListIterator.remove();
				}
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

	public void checkCatchesByGuestAndPlayer(Player player) {
		for (List<Guest> list : allGuests) {
			for (Guest guest : list) {
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
