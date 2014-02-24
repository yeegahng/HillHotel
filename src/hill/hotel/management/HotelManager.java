package hill.hotel.management;

import government.service.validity.GovernmentProxy;
import hill.hotel.executive.HotelOwner;
import hill.hotel.guest.Guest;
import hill.hotel.room.RoomManager;

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
			System.out.println("HotelOwner is null: Cannot open hotel.");
			return null;
		}
		int licenseNumber = hotelOwner.getLicenseNumber();
		if(!GovernmentProxy.getProxy().verifyLicenseValidity(licenseNumber)) {
			System.out.println("HotelOwner info is invalid: Cannot open hotel.");
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
			System.out.println("Failed to hire staffs: Cannot open hotel.");
		}
		mLicenseNumber = licenseNumber;
		mIsHotelReady = true;
		System.out.println("Hotel " + mLicenseNumber + " is ready to open");
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
		if(!parkingLotManager.isVehicleParked(guest.getVehicleInfo())) {
			System.out.println("No such vehicle parked: vehicle info " + guest.getVehicleInfo());
		}		
		//TODO queue로 처리할 것
		roomManager.requestCheckIn(guest);
	}
	public void checkOut(Guest guest) {		
		String vehicleInfo = guest.getVehicleInfo();
		if(vehicleInfo != null) {
			parkingLotManager.valletParkOut(vehicleInfo);
		}
		//TODO queue로 처리할 것
		roomManager.requestCheckOut(guest);
	}
}
