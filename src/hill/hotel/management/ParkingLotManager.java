package hill.hotel.management;

import java.util.ArrayList;

public class ParkingLotManager {
	
	private ArrayList<String> mVehicleList;
	
	public ParkingLotManager() {
		mVehicleList = new ArrayList<String>();
	}
	
	public void valletParkIn(String vehicleInfo) {
		mVehicleList.add(vehicleInfo);
	}

	public void valletParkOut(String vehicleInfo) {
		// TODO prepare vehicle, confirm guest info and hand it over.
		mVehicleList.remove(vehicleInfo);
	}

	public boolean isVehicleParked(String vehicleInfo) {
		return mVehicleList.contains(vehicleInfo);
	}


}
