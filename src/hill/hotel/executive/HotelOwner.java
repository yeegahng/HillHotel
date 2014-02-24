package hill.hotel.executive;

import government.service.validity.GovernmentProxy;
import hill.hotel.config.Config;
import hill.hotel.management.HotelManager;

public class HotelOwner implements IBusinessRunnable {
	
	private int mLicenseNumber;
	private String mPhoneNumber = Config.PHONE_NUMBER;
	
	public final void acquireLicense() {
		GovernmentProxy govProxy = GovernmentProxy.getProxy();
		govProxy.registerBusiness(this);
		System.out.println("Hotel license acquired: " + mLicenseNumber);
		System.out.println("Hotel phone number: " + mPhoneNumber);
		
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
	}


}
