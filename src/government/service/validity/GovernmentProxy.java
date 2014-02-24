package government.service.validity;

import hill.hotel.executive.IBusinessRunnable;
import hill.hotel.util.Log;

import java.util.ArrayList;

public final class GovernmentProxy {
	
	private static final int OFFSET = 10000;
	private static GovernmentProxy mProxyEntity = new GovernmentProxy();
	private ArrayList<IBusinessRunnable> mBusinessEnrollmentList;
	
	private GovernmentProxy() {
		mBusinessEnrollmentList = new ArrayList<IBusinessRunnable>();
	}
	
	public static GovernmentProxy getProxy() {
		return mProxyEntity;
	}
	
	public boolean verifyLicenseValidity(int licenseNumber, IBusinessRunnable bizOwner) {
		if(bizOwner == null) {
			Log.w("input bizOwner to verify is null");
			return false;
		}
		if(bizOwner.getLicenseNumber() != licenseNumber) {
			Log.w("input bizOwner has different licenseNumber to verify");
			return false;
		}
		if(mBusinessEnrollmentList.isEmpty()) {
			Log.w("No business owner registered yet");
			return false;
		}
		IBusinessRunnable enrolledBizOwner = mBusinessEnrollmentList.get(extractLicenseNumber(licenseNumber));
		if(bizOwner.equals(enrolledBizOwner)
				&& (licenseNumber == enrolledBizOwner.getLicenseNumber())) {
			Log.i("License Number has been verified: " + licenseNumber);
			return true;
		}
		else {
			return false;	
		}
	}

	public boolean registerBusiness(IBusinessRunnable bizOwner) {
		mBusinessEnrollmentList.add(bizOwner);
		int licenseNumber = synthesizeLicenseNumber(mBusinessEnrollmentList.indexOf(bizOwner));
		bizOwner.setLicenseNumber(licenseNumber);
		return true;
	}
	
	/* mimic hash generation and reversing */
	private int synthesizeLicenseNumber(int listIndex) {
		return listIndex + OFFSET;
	}
	private int extractLicenseNumber(int licenseNumber) {
		return licenseNumber - OFFSET;
	}

}
