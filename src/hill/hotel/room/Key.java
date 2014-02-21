package hill.hotel.room;

public class Key {
	
	private Room mRoom;
	
	public Key() {
	}
	
	public int getRoomNumber() {
		if(mRoom != null) {
			return mRoom.getRoomNumber();
		}
		else {
			System.out.println("This key hasn't been registered: No room info available now.");
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
