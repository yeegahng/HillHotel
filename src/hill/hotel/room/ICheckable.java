package hill.hotel.room;

import hill.hotel.guest.Guest;

public interface ICheckable {
	public void checkIn(Guest guest);
	public void checkOut(Key key);
	public boolean isOccupied();
}
