package hill.hotel.executive;


public class ApplicationRunner {

	public static void main(String[] args) {
		
		IBusinessRunnable owner = new HotelOwner();
		owner.startBusiness();
	}

}
