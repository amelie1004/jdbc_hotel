package hotel.vo;

import java.util.Date;

public class HotelReservationInfo {
	private int reservationNo;
	private int customerNo;
	private int roomNo;
	private int hotelNo;
	private Date startDate;
	private Date endDate;
	private Date signDate;
	private Date signCancelDate;
	private int adultCnt;
	private int childrenCnt;
	private String requestStr;
	private String isHotdeal;

	public HotelReservationInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelReservationInfo(Date startDate, Date endDate, Date signDate, Date signCancelDate, int adultCnt,
			int childrenCnt, String requestStr, String isHotdeal) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.signDate = signDate;
		this.signCancelDate = signCancelDate;
		this.adultCnt = adultCnt;
		this.childrenCnt = childrenCnt;
		this.requestStr = requestStr;
		this.isHotdeal = isHotdeal;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getHotelNo() {
		return hotelNo;
	}

	public void setHotelNo(int hotelNo) {
		this.hotelNo = hotelNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getSignCancelDate() {
		return signCancelDate;
	}

	public void setSignCancelDate(Date signCancelDate) {
		this.signCancelDate = signCancelDate;
	}

	public int getAdultCnt() {
		return adultCnt;
	}

	public void setAdultCnt(int adultCnt) {
		this.adultCnt = adultCnt;
	}

	public int getChildrenCnt() {
		return childrenCnt;
	}

	public void setChildrenCnt(int childrenCnt) {
		this.childrenCnt = childrenCnt;
	}

	public String getRequestStr() {
		return requestStr;
	}

	public void setRequestStr(String requestStr) {
		this.requestStr = requestStr;
	}

	public String getIsHotdeal() {
		return isHotdeal;
	}

	public void setIsHotdeal(String isHotdeal) {
		this.isHotdeal = isHotdeal;
	}

}
