package hill.hotel.management;

import government.service.validity.GovernmentProxy;
import hill.hotel.executive.HotelOwner;
import hill.hotel.guest.Guest;
import hill.hotel.room.RoomManager;
import hill.hotel.util.Log;

public class HotelManager {
	
	private static HotelManager mHotelManager = null;
	private boolean mIsHotelReady = false;
	private final int mLicenseNumber;
	
	//Staffs
	private RoomManager roomManager;
	private ParkingLotManager parkingLotManager;
	private CleanServiceManager cleanServiceManager;
	private PoliceWatcher policeWatcher;
	
	//HotelManager works as a singleton
	public static HotelManager callHotelManager(HotelOwner hotelOwner) {
		if(hotelOwner == null) {
			Log.e("HotelOwner is null: Cannot call hotel manager.");
			return null;
		}
		int licenseNumber = hotelOwner.getLicenseNumber();
		if(!GovernmentProxy.getProxy().verifyLicenseValidity(licenseNumber)) {
			Log.e("Failed to verify HotelOwner license: Cannot call hotel manager.");
			return null;
		}
		if(mHotelManager == null) {
			mHotelManager = new HotelManager(licenseNumber);
		}
		return mHotelManager;
	}
	
	private HotelManager(int licenseNumber) {
		try {
			hireStaffs();
		} catch(Exception e) {
			Log.e("Failed to hire staffs: Cannot open hotel.");
		}
		mLicenseNumber = licenseNumber;
		mIsHotelReady = true;
		Log.i("Hotel " + mLicenseNumber + " is ready to open");
	}

	private void hireStaffs() {
		//TODO 각각의 service thread로 수행할 것
		roomManager = new RoomManager();
		parkingLotManager = new ParkingLotManager();
		cleanServiceManager = new CleanServiceManager();
		policeWatcher = new PoliceWatcher();
	}
	
	public boolean isHotelReady() {
		return mIsHotelReady;
	}

	public void checkIn(Guest guest) {
		//TODO queue로 처리할 것
		roomManager.requestCheckIn(guest);
	}
	public void checkOut(Guest guest) {		
		String vehicleInfo = guest.getVehicleInfo();
		if(vehicleInfo != null && parkingLotManager.isVehicleParked(vehicleInfo)) {
			parkingLotManager.valletParkOut(vehicleInfo);
		}
		//TODO queue로 처리할 것
		roomManager.requestCheckOut(guest);
	}
}
