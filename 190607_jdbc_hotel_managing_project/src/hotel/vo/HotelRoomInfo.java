package hotel.vo;

public class HotelRoomInfo {
	private int roomNo;
	private int hotelNo;
	private int managerNo;
	private int roomTypeNo;
	private int statusNo;
	private String roomSize;
	private int roomCnt;
	private int roomRealNo;

	public HotelRoomInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelRoomInfo(int hotelNo, int managerNo, int roomTypeNo, int statusNo, String roomSize, int roomCnt,
			int roomRealNo) {
		super();
		this.hotelNo = hotelNo;
		this.managerNo = managerNo;
		this.roomTypeNo = roomTypeNo;
		this.statusNo = statusNo;
		this.roomSize = roomSize;
		this.roomCnt = roomCnt;
		this.roomRealNo = roomRealNo;
	}

	public int getRoomRealNo() {
		return roomRealNo;
	}

	public void setRoomRealNo(int roomRealNo) {
		this.roomRealNo = roomRealNo;
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

	public int getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}

	public int getRoomTypeNo() {
		return roomTypeNo;
	}

	public void setRoomTypeNo(int roomTypeNo) {
		this.roomTypeNo = roomTypeNo;
	}

	public int getStatusNo() {
		return statusNo;
	}

	public void setStatusNo(int statusNo) {
		this.statusNo = statusNo;
	}

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public int getRoomCnt() {
		return roomCnt;
	}

	public void setRoomCnt(int roomCnt) {
		this.roomCnt = roomCnt;
	}

}
