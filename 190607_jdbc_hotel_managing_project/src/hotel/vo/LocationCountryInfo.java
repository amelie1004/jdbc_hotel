package hotel.vo;

public class LocationCountryInfo {
	private int countryNo;
	private String countryName;
	private int countryCode;

	public LocationCountryInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationCountryInfo(String countryName, int countryCode) {
		super();
		this.countryName = countryName;
		this.countryCode = countryCode;
	}

	public int getCountryNo() {
		return countryNo;
	}

	public void setCountryNo(int countryNo) {
		this.countryNo = countryNo;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
}
