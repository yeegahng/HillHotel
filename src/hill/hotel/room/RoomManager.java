package hill.hotel.room;

import hill.hotel.config.Config;
import hill.hotel.management.Guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

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

	public void checkIn(Guest guest) {
		Key key = getAvailableRoomKey(null);
		mRoomKeyMap.get(key).checkIn(guest);
		guest.assignRoom(key);
	}

	private Key getAvailableRoomKey(RoomRequestInfo reqInfo) {
		if(reqInfo != null) {
			
		}
		else {
			for(Key key : mRoomKeyMap.keySet()) {
				
			}
		}
		return null;
	}

	public void checkOut(Guest guest) {
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
