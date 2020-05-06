package application.guest;

import java.util.Iterator;
import java.util.List;
import application.entities.Mug;
import application.entities.MugState;

public class GuestsStateManager {

	private List<Guest> guests;
	private List<Mug> mugs;

	public GuestsStateManager(List<Mug> mugs, List<Guest> guests) {
		this.mugs = mugs;
		this.guests = guests;
	}

	public void remove() {
		Iterator<Mug> mugListIterator = mugs.iterator();
		while (mugListIterator.hasNext()) {
			Mug mug = mugListIterator.next();
			if (mug.isRemovable()) {
				mugListIterator.remove();
			}
		}
		
		Iterator<Guest> guestListIterator = guests.iterator();
		while (guestListIterator.hasNext()) {
			Guest guest = guestListIterator.next();
			if (guest.isRemovable()) {
				guestListIterator.remove();
			}
		}
	}

	

	public void check() {
		for (Guest guest : guests) {
			switch (guest.getState()) {
			case IN_COME_MOTION: // case: ANGRY / LEAVE
				if (isGuestOnLeftBound(guest)) {
					guest.setState(GuestState.ANGRY);
				} else {
					for (Mug mug : mugs) {
						if (guest.intersects(mug)) {
							guest.setState(GuestState.ENTER_LEAVE_IN_MOTION);
							mug.setState(MugState.IN_HAND);
						}
					}
				}

				break;
			case IN_COME_IDLE:
				for (Mug mug : mugs) {
					if (guest.intersects(mug)) {
						guest.setState(GuestState.ENTER_LEAVE_IN_MOTION);
						mug.setState(MugState.IN_HAND);
					}
				}
				break;

//			case LEAVE: //case: EXIT / ASK_MORE
//				
//				break;
			default:
				break;
			}
		}
	}

	private boolean isGuestOnLeftBound(Guest guest) {
		return guest.getPositionX() <= guest.getBoundary().getLeft();
	}

}
