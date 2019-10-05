package hotel.vo;

import java.util.Date;

public class HotdealDisplayInfo {
	private int displayNo;
	private int roomNo;
	private int hotelNo;
	private Date startDate;
	private Date endDate;
	private int discountRate;
	private int pricePerDay;
	private int isShowHotdeal;

	public HotdealDisplayInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotdealDisplayInfo(Date startDate, Date endDate, int discountRate, int pricePerDay, int isShowHotdeal) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.discountRate = discountRate;
		this.pricePerDay = pricePerDay;
		this.isShowHotdeal = isShowHotdeal;
	}

	public int getDisplayNo() {
		return displayNo;
	}

	public void setDisplayNo(int displayNo) {
		this.displayNo = displayNo;
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

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

	public int getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public int getIsShowHotdeal() {
		return isShowHotdeal;
	}

	public void setIsShowHotdeal(int isShowHotdeal) {
		this.isShowHotdeal = isShowHotdeal;
	}
}
