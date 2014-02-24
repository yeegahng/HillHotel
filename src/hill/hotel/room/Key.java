package hill.hotel.room;

import hill.hotel.util.Log;

public class Key {
	
	private Room mRoom;
	
	public Key() {
	}
	
	public int getRoomNumber() {
		if(mRoom != null) {
			return mRoom.getRoomNumber();
		}
		else {
			Log.w("This key hasn't been registered: No room info available now.");
			return -1;
		}
	}

	public void registerRoom(Room room) {
		mRoom = room;
	}
	
	public boolean isCheckedIn() {
		if(mRoom != null) {
			return mRoom.isOccupied();
		}
		else {
			//Wouldn't reach here if Hotel is ready
			return false;
		}
	}

}
