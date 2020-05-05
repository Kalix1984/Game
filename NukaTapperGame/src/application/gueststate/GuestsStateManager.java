package application.gueststate;

import java.util.List;
import application.entities.Mug;
import application.entities.MugState;
import application.gamestate.GameStateManager;

public class GuestsStateManager {

	private List<Guest> guests;
	private List<Mug> mugs;
//	private GameStateManager gameState;

	public GuestsStateManager(List<Mug> mugs, List<Guest> guests) {
		this.mugs = mugs;
		this.guests = guests;
	}

	public void check() {
		for (Guest guest : guests) {
			switch (guest.getState()) {
			case COME:
				if (isGuestInLeftBound(guest)) {
					guest.setState(GuestState.ANGRY);
				} else {
					for (Mug mug : mugs) {
						if (guest.intersects(mug)) {
							guest.setState(GuestState.LEAVE);
							mug.setState(MugState.IN_HAND);
						}
					}
				}

				break;
			case LEAVE:

				break;
			default:
				break;
			}
		}
	}

	private boolean isGuestInLeftBound(Guest guest) {
		return guest.getBoundary().getLeft() == guest.getPositionX();
	}

//	private boolean isAngry() {
//		for (Guest guest : guests) {
//			if (guest.getStatus() == GuestState.ANGRY) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	private boolean isServed() {
//
//	}

}
