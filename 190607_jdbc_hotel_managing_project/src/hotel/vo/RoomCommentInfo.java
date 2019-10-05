package hotel.vo;

public class RoomCommentInfo {
	private int commentNo;
	private int customerNo;
	private int reservationNo;
	private int hotelNo;
	private int roomNo;
	private String commentStr;
	private int roomScore;

	public RoomCommentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomCommentInfo(String commentStr, int roomScore) {
		super();
		this.commentStr = commentStr;
		this.roomScore = roomScore;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	public int getHotelNo() {
		return hotelNo;
	}

	public void setHotelNo(int hotelNo) {
		this.hotelNo = hotelNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getCommentStr() {
		return commentStr;
	}

	public void setCommentStr(String commentStr) {
		this.commentStr = commentStr;
	}

	public int getRoomScore() {
		return roomScore;
	}

	public void setRoomScore(int roomScore) {
		this.roomScore = roomScore;
	}

}
