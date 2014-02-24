package government.service.validity;

import hill.hotel.executive.IBusinessRunnable;

import java.util.ArrayList;

public final class GovernmentProxy {
	
	private static GovernmentProxy mProxyEntity = new GovernmentProxy();
	private ArrayList<IBusinessRunnable> mBusinessEnrollmentList;
	
	private GovernmentProxy() {
		mBusinessEnrollmentList = new ArrayList<IBusinessRunnable>();
	}
	
	public static GovernmentProxy getProxy() {
		return mProxyEntity;
	}
	
	public boolean verifyLicenseValidity(int licenseNumber) {
		IBusinessRunnable bizOwner = mBusinessEnrollmentList.get(licenseNumber);
		return (bizOwner.getLicenseNumber() == licenseNumber);
	}

	public boolean registerBusiness(IBusinessRunnable bizOwner) {
		mBusinessEnrollmentList.add(bizOwner);
		bizOwner.setLicenseNumber(mBusinessEnrollmentList.indexOf(bizOwner) + 10000); //10000 is offset
		return true;
		
	}

}
