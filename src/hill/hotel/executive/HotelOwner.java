package hill.hotel.executive;

import government.service.validity.GovernmentProxy;
import hill.hotel.config.Config;
import hill.hotel.management.HotelManager;
import hill.hotel.util.Log;

public class HotelOwner implements IBusinessRunnable {
	
	private int mLicenseNumber;
	private String mPhoneNumber = Config.PHONE_NUMBER;
	
	public final void acquireLicense() {
		GovernmentProxy govProxy = GovernmentProxy.getProxy();
		govProxy.registerBusiness(this);
		Log.i("Hotel license acquired: " + mLicenseNumber);
		Log.i("Hotel phone number: " + mPhoneNumber);
		
	}

	@Override
	public final int getLicenseNumber() {
		return mLicenseNumber;
	}

	@Override
	public void setLicenseNumber(int licenseNumber) {
		mLicenseNumber = licenseNumber;
		
	}

	public void startBusiness() {
		HotelManager hotelManager = HotelManager.callHotelManager(this);
		if(hotelManager == null) {
			Log.e("Without hotel manager, owner is unable to start business. Application quits.");
		}
	}


}
