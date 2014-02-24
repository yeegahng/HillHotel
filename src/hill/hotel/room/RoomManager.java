package hill.hotel.room;

import hill.hotel.config.Config;
import hill.hotel.guest.Guest;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomManager {

	private HashMap<Key,Room> mRoomKeyMap = new HashMap<Key,Room>();
	private HashMap<Key,Room> mRoomGuestMap = new HashMap<Key,Room>();
	private ArrayList<Integer> mCheckInList = new ArrayList<Integer>();
	
	public RoomManager() {
		initRoomMap();
	}

	private void initRoomMap() {
		
		int floorIndex = Config.BOTTOM_FLOOR;
		int roomIndex = 1;
		for(; floorIndex <= Config.TOP_FLOOR; floorIndex++) {
			for(; roomIndex <= Config.ROOM_PER_FLOOR; roomIndex++) {
				int roomNumber = floorIndex * Config.FLOOR_NUMBER_OFFSET + roomIndex;
				Room room = new Room(roomNumber);
				Key key = new Key();
				room.registerKey(key);
				key.registerRoom(room);
				mRoomKeyMap.put(key, room);
			}
		}		
	}

	public void requestCheckIn(Guest guest) {
		Key key = getAvailableRoomKey(null);
		mRoomKeyMap.get(key).checkIn(guest);
		guest.assignRoom(key);
	}

	private Key getAvailableRoomKey(RoomRequestInfo reqInfo) {
		if(reqInfo != null) {
			//TODO: find room with request info (such as favorite floor, room direction, double/twin setup, etc.)
		}
		else {
			for(Key key : mRoomKeyMap.keySet()) {
				//TODO: find any available room (search from the lower floor, room index)
			}
		}
		return null;
	}

	public void requestCheckOut(Guest guest) {
		Key key = guest.getKey();
		Room room = mRoomKeyMap.get(key);
		if(room != null) {
			room.checkOut(key);
			mCheckInList.remove(room);
		}
		else {
			System.out.println("Cannot checkOut: Room #" + key.getRoomNumber() + " was not in status of checkIn");
		}
	}
	
	
	
	
	public void findRoomFor(int roomNumber) {
		
	}
	public void findRoomFor(String guestName) {
		
	}

}
