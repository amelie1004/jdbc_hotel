package hotel.vo;

import java.util.Date;

public class CustomerInfo {
	private int customerNo;
	private String loginId;
	private String loginPasswd;
	private int isPasswdExpired;
	private String nickName;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String address;
	private String phoneNumber;
	private String email;
	private int residenceLocationNo;
	private String zipCode;
	private int classNo;
	private int recommendCustomerNo;
	private int rewardCount;
	private Date accountRegisterDate;
	private Date accountDropDate;
	private Date accountModifiedDate;
	private Date lastLoginDate;
	private int bookmarkCityNo;
	private int isLogin;
	private int isBlocked;
	private int loginFailedCount;

	public CustomerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerInfo(String loginId, String loginPasswd, int isPasswdExpired, String nickName, String firstName,
			String lastName, int age, String phoneNumber, String email, Date accountRegisterDate, int isLogin,
			int isBlocked, int loginFailedCount) {
		super();
		this.loginId = loginId;
		this.loginPasswd = loginPasswd;
		this.isPasswdExpired = isPasswdExpired;
		this.nickName = nickName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.accountRegisterDate = accountRegisterDate;
		this.isLogin = isLogin;
		this.isBlocked = isBlocked;
		this.loginFailedCount = loginFailedCount;
	}

	public CustomerInfo(int customerNo, String loginId, String loginPasswd, int isPasswdExpired, String nickName,
			String firstName, String lastName, int age, String phoneNumber, String email, Date accountRegisterDate,
			int isLogin, int isBlocked, int loginFailedCount) {
		super();
		this.customerNo = customerNo;
		this.loginId = loginId;
		this.loginPasswd = loginPasswd;
		this.isPasswdExpired = isPasswdExpired;
		this.nickName = nickName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.accountRegisterDate = accountRegisterDate;
		this.isLogin = isLogin;
		this.isBlocked = isBlocked;
		this.loginFailedCount = loginFailedCount;
	}

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPasswd() {
		return loginPasswd;
	}

	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}

	public int getIsPasswdExpired() {
		return isPasswdExpired;
	}

	public void setIsPasswdExpired(int isPasswdExpired) {
		this.isPasswdExpired = isPasswdExpired;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getResidenceLocationNo() {
		return residenceLocationNo;
	}

	public void setResidenceLocationNo(int residenceLocationNo) {
		this.residenceLocationNo = residenceLocationNo;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	public int getRecommendCustomerNo() {
		return recommendCustomerNo;
	}

	public void setRecommendCustomerNo(int recommendCustomerNo) {
		this.recommendCustomerNo = recommendCustomerNo;
	}

	public int getRewardCount() {
		return rewardCount;
	}

	public void setRewardCount(int rewardCount) {
		this.rewardCount = rewardCount;
	}

	public Date getAccountRegisterDate() {
		return accountRegisterDate;
	}

	public void setAccountRegisterDate(Date accountRegisterDate) {
		this.accountRegisterDate = accountRegisterDate;
	}

	public Date getAccountDropDate() {
		return accountDropDate;
	}

	public void setAccountDropDate(Date accountDropDate) {
		this.accountDropDate = accountDropDate;
	}

	public Date getAccountModifiedDate() {
		return accountModifiedDate;
	}

	public void setAccountModifiedDate(Date accountModifiedDate) {
		this.accountModifiedDate = accountModifiedDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public int getBookmarkCityNo() {
		return bookmarkCityNo;
	}

	public void setBookmarkCityNo(int bookmarkCityNo) {
		this.bookmarkCityNo = bookmarkCityNo;
	}

	public int getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}

	public int getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(int isBlocked) {
		this.isBlocked = isBlocked;
	}

	public int getLoginFailedCount() {
		return loginFailedCount;
	}

	public void setLoginFailedCount(int loginFailedCount) {
		this.loginFailedCount = loginFailedCount;
	}

}
