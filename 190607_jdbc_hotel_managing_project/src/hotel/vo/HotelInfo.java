package hotel.vo;

public class HotelInfo {
	private int hotelNo;
	private int brandNo;
	private int typeNo;
	private String hotelName;
	private int cityNo;
	private String hotelAddress;
	private int star;
	private int roomCnt;
	private int workerCnt;
	private int canBreakfast;
	private int canSwimming;
	private int canWifi;
	private int canParking;
	private int canPet;
	private int canSmoke;
	private int bossHotelNo;

	public HotelInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelInfo(int brandNo, int typeNo, String hotelName, int cityNo, int star, int roomCnt) {
		super();
		this.brandNo = brandNo;
		this.typeNo = typeNo;
		this.hotelName = hotelName;
		this.cityNo = cityNo;
		this.star = star;
		this.roomCnt = roomCnt;
	}

	public HotelInfo(String hotelAddress, int workerCnt, int canBreakfast, int canSwimming, int canWifi, int canParking,
			int canPet, int canSmoke, int bossHotelNo) {
		super();
		this.hotelAddress = hotelAddress;
		this.workerCnt = workerCnt;
		this.canBreakfast = canBreakfast;
		this.canSwimming = canSwimming;
		this.canWifi = canWifi;
		this.canParking = canParking;
		this.canPet = canPet;
		this.canSmoke = canSmoke;
		this.bossHotelNo = bossHotelNo;
	}
	
	public HotelInfo(int brandNo, int typeNo, String hotelName, int cityNo, String hotelAddress, int star, int roomCnt,
			int workerCnt, int canBreakfast, int canSwimming, int canWifi, int canParking, int canPet, int canSmoke,
			int bossHotelNo) {
		super();
		this.brandNo = brandNo;
		this.typeNo = typeNo;
		this.hotelName = hotelName;
		this.cityNo = cityNo;
		this.hotelAddress = hotelAddress;
		this.star = star;
		this.roomCnt = roomCnt;
		this.workerCnt = workerCnt;
		this.canBreakfast = canBreakfast;
		this.canSwimming = canSwimming;
		this.canWifi = canWifi;
		this.canParking = canParking;
		this.canPet = canPet;
		this.canSmoke = canSmoke;
		this.bossHotelNo = bossHotelNo;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getHotelNo() {
		return hotelNo;
	}

	public void setHotelNo(int hotelNo) {
		this.hotelNo = hotelNo;
	}

	public int getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(int brandNo) {
		this.brandNo = brandNo;
	}

	public int getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getCityNo() {
		return cityNo;
	}

	public void setCityNo(int cityNo) {
		this.cityNo = cityNo;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public int getRoomCnt() {
		return roomCnt;
	}

	public void setRoomCnt(int roomCnt) {
		this.roomCnt = roomCnt;
	}

	public int getWorkerCnt() {
		return workerCnt;
	}

	public void setWorkerCnt(int workerCnt) {
		this.workerCnt = workerCnt;
	}

	public int getCanBreakfast() {
		return canBreakfast;
	}

	public void setCanBreakfast(int canBreakfast) {
		this.canBreakfast = canBreakfast;
	}

	public int getCanSwimming() {
		return canSwimming;
	}

	public void setCanSwimming(int canSwimming) {
		this.canSwimming = canSwimming;
	}

	public int getCanWifi() {
		return canWifi;
	}

	public void setCanWifi(int canWifi) {
		this.canWifi = canWifi;
	}

	public int getCanParking() {
		return canParking;
	}

	public void setCanParking(int canParking) {
		this.canParking = canParking;
	}

	public int getCanPet() {
		return canPet;
	}

	public void setCanPet(int canPet) {
		this.canPet = canPet;
	}

	public int getCanSmoke() {
		return canSmoke;
	}

	public void setCanSmoke(int canSmoke) {
		this.canSmoke = canSmoke;
	}

	public int getBossHotelNo() {
		return bossHotelNo;
	}

	public void setBossHotelNo(int bossHotelNo) {
		this.bossHotelNo = bossHotelNo;
	}

}
