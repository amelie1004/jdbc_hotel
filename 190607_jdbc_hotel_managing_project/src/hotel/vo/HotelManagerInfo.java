package hotel.vo;

public class HotelManagerInfo {
	private int managerNo;
	private int hotelNo;
	private String managerId;
	private String managerPasswd;
	private String firstName;
	private String lastName;
	private String telephoneNum;
	private String email;
	private int bossManagerNo;

	public HotelManagerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelManagerInfo(int hotelNo, String managerId, String managerPasswd, String firstName, String lastName,
			String telephoneNum, String email, int bossManagerNo) {
		super();
		this.hotelNo = hotelNo;
		this.managerId = managerId;
		this.managerPasswd = managerPasswd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephoneNum = telephoneNum;
		this.email = email;
		this.bossManagerNo = bossManagerNo;
	}

	public int getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}

	public int getHotelNo() {
		return hotelNo;
	}

	public void setHotelNo(int hotelNo) {
		this.hotelNo = hotelNo;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerPasswd() {
		return managerPasswd;
	}

	public void setManagerPasswd(String managerPasswd) {
		this.managerPasswd = managerPasswd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephoneNum() {
		return telephoneNum;
	}

	public void setTelephoneNum(String telephoneNum) {
		this.telephoneNum = telephoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBossManagerNo() {
		return bossManagerNo;
	}

	public void setBossManagerNo(int bossManagerNo) {
		this.bossManagerNo = bossManagerNo;
	}

}
