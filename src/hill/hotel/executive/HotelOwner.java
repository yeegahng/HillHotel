package hill.hotel.executive;

import government.service.validity.GovernmentProxy;
import hill.hotel.config.Config;
import hill.hotel.management.HotelManager;
import hill.hotel.util.Log;

public class HotelOwner implements IBusinessRunnable {

	private int mLicenseNumber;
	private String mPhoneNumber = Config.PHONE_NUMBER;
	private HotelManager mHotelManager = null;
	
	public HotelOwner() {
		//every owner should have license from its born.
		acquireLicense();
	}
	
	private final boolean acquireLicense() {
		GovernmentProxy govProxy = GovernmentProxy.getProxy();
		boolean result = govProxy.registerBusiness(this);
		if(result) {
			Log.i("Hotel license acquired: " + mLicenseNumber);
			Log.i("Hotel phone number: " + mPhoneNumber);
		}
		return result;		
	}

	@Override
	public final int getLicenseNumber() {
		return mLicenseNumber;
	}

	@Override
	public void setLicenseNumber(int licenseNumber) {
		mLicenseNumber = licenseNumber;
		
	}

	@Override
	public boolean startBusiness() {
		mHotelManager = HotelManager.callHotelManager(this);
		if(mHotelManager == null) {
			Log.e("Without hotel manager, owner is unable to start business. Application quits.");
			return false;
		}
		mHotelManager.run();
		return true;
	}

	public boolean isRunningBusiness() {
		return mHotelManager.isHotelRunning();
	}


}
