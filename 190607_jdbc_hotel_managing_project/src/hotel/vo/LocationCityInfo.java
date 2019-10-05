package hotel.vo;

public class LocationCityInfo {
	private int cityNo;
	private int countryNo;
	private String countryName;
	private String cityName;
	private int cityCode;

	public LocationCityInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationCityInfo(int countryNo, String cityName, int cityCode) {
		super();
		this.countryNo = countryNo;
		this.cityName = cityName;
		this.cityCode = cityCode;
	}
	
	public LocationCityInfo(String countryName, String cityName, int cityCode) {
		super();
		this.countryName = countryName;
		this.cityName = cityName;
		this.cityCode = cityCode;
	}

	public int getCityNo() {
		return cityNo;
	}

	public void setCityNo(int cityNo) {
		this.cityNo = cityNo;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
}
