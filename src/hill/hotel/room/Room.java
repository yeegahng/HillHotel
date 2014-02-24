package hill.hotel.room;

import hill.hotel.config.Config;
import hill.hotel.guest.Guest;
import hill.hotel.util.Log;

public class Room implements ICheckable, IReservable {
	
	private enum State {
		AVAILABLE,
		OCCUPIED,
		RESERVED,
		NEED_CLEAN, CLEANING,
		NEED_FIX,
		NOT_USABLE
	}
	private enum DoorState {
		OPEN,
		CLOSED
	}
	private State mRoomState;
	private DoorState mDoorState;
	
	private final int ROOM_CHARGE; //this may vary upon room type (that will be defined by extending Room class)
	private final int mRoomNumber;

	private Guest mCurrentGuest;
	private Key mKey;

	public Room(int roomNumber) {
		mRoomNumber = roomNumber;
		mCurrentGuest = null;
		mKey = null;
		mRoomState = State.AVAILABLE;
		mDoorState = DoorState.CLOSED;
		ROOM_CHARGE = Config.ROOM_CHARGE;
	}

	@Override
	public void checkIn(Guest guest) {
		mCurrentGuest = guest;
		mRoomState = State.OCCUPIED;
	}

	@Override
	public void checkOut(Key key) {
		mCurrentGuest = null;
		mRoomState = State.AVAILABLE;
	}

	@Override
	public boolean isOccupied() {
		return (mRoomState == State.OCCUPIED);
	}

	@Override
	public void doReservation() {
		if(mRoomState == State.AVAILABLE) {
			mRoomState = State.RESERVED;
		}
		else {
			Log.w("Cannot reserve room #" + mRoomNumber + ": Room state is " + mRoomState);
		}
	}

	@Override
	public void cancelReservation() {
	}

	@Override
	public boolean isReserved() {
		return (mRoomState == State.RESERVED);
	}

	public int getRoomNumber() {
		return mRoomNumber;
	}
	
	public void registerKey(Key key) {
		mKey = key;
	}

}
