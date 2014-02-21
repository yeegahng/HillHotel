package hill.hotel.room;

public class CheckInInfo {
	
	public int mGuestCount;
	private int mGuestInfo;
	//mGuestInfo bit map (low to high)
	//CAR_PARKING
	
	public CheckInInfo(int guestCount) {
		mGuestCount = guestCount;
		mGuestInfo = 0x00000000;
	}
	
	public void setInfoFlag(int flag) {
		
	}
}
