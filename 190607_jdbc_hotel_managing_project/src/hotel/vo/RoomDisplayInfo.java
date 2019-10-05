package hotel.vo;

import java.util.Date;

public class RoomDisplayInfo {
	private int displayNo;
	private int roomNo;
	private int hotelNo;
	private Date startDate;
	private Date endDate;
	private int pricePerDay;
	private Date hotdealStartDate;
	private int isDealed;

	public RoomDisplayInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomDisplayInfo(int roomNo, Date startDate, Date endDate, int pricePerDay) {
		super();
		this.roomNo = roomNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pricePerDay = pricePerDay;
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

	public int getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public Date getHotdealStartDate() {
		return hotdealStartDate;
	}

	public void setHotdealStartDate(Date hotdealStartDate) {
		this.hotdealStartDate = hotdealStartDate;
	}

	public int getIsDealed() {
		return isDealed;
	}

	public void setIsDealed(int isDealed) {
		this.isDealed = isDealed;
	}

}
