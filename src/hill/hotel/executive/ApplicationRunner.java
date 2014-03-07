package hill.hotel.executive;

import hill.hotel.util.Log;


public class ApplicationRunner {

	public static void main(String[] args) {
		
		Log.i("HillHotel Application Started");
		
		IBusinessRunnable owner = new HotelOwner();
		owner.startBusiness(); //this will start thread waiting for
								//1. user event
								//2. sensor event
								//3. system event

		Log.i("HillHotel Application Finished");
	}

}
