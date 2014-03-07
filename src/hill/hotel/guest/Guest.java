package hill.hotel.guest;

import hill.hotel.room.Key;

public class Guest {

	private int mGuestCount;
	private String mVehicleInfo; //vehicle number
	private Key mKey;
	
	public Guest(int guestCount) {
		mGuestCount = guestCount;
		mVehicleInfo = null;
		mKey = null;
	}
	public void setVehicleInfo(String vehicleInfo) {
		mVehicleInfo = vehicleInfo;		
	}
	public String getVehicleInfo() {
		return mVehicleInfo;
	}
	public Key getKey() {
		return mKey;
	}
	public void assignRoom(Key key) {
		mKey = key;
	}

}
