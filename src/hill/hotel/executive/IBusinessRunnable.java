package hill.hotel.executive;

public interface IBusinessRunnable {

	void setLicenseNumber(int licenseNumber);

	int getLicenseNumber();

	boolean startBusiness();

	boolean isRunningBusiness();

}
