package hill.hotel.management;

import hill.hotel.room.RoomManager;

public class HotelManager {
	
	private HotelManager mHotelManager = new HotelManager();
	private boolean mIsHotelReady = false;
	
	//Staffs
	private RoomManager roomManager;
	private ParkingLotManager parkingLotManager;
	private CleanServiceManager cleanServiceManager;
	private PoliceWatcher policeWatcher;
	
	//HotelManager works as a singleton
	public HotelManager callHotelManager() {
		if(mHotelManager == null) {
			mHotelManager = new HotelManager();
		}
		return mHotelManager;
	}
	
	private HotelManager() {
		try {
			hireStaffs();
		} catch(Exception e) {
			System.out.println("Failed to hire staffs: Cannot open hotel now.");
		}
		mIsHotelReady = true;
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
		roomManager.checkIn(guest);
	}
	public void checkOut(Guest guest) {		
		String vehicleInfo = guest.getVehicleInfo();
		if(vehicleInfo != null) {
			parkingLotManager.valletParkOut(vehicleInfo);
		}
		//TODO queue로 처리할 것
		roomManager.checkOut(guest);
	}
}
