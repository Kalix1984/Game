package application.guest;

import java.util.Iterator;
import java.util.List;

import application.GameStats;
import application.entities.Mug;
import application.entities.MugState;

public class GuestsStateManager {

	private List<Guest> guests;
	private List<Mug> mugs;
	private GameStats gameStats;

	public GuestsStateManager(List<Mug> mugs, List<Guest> guests, GameStats gameStats) {
		this.mugs = mugs;
		this.guests = guests;
		this.gameStats = gameStats;
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
