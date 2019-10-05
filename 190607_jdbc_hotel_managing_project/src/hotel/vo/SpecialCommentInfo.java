package hotel.vo;

public class SpecialCommentInfo {
	private int commentNo;
	private int customerNo;
	private int previewerNo;
	private int hotelNo;
	private int roomNo;
	private String roomCommentStr;
	private int roomScore;

	public SpecialCommentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SpecialCommentInfo(String roomCommentStr, int roomScore) {
		super();
		this.roomCommentStr = roomCommentStr;
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

	public int getPreviewerNo() {
		return previewerNo;
	}

	public void setPreviewerNo(int previewerNo) {
		this.previewerNo = previewerNo;
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

	public String getRoomCommentStr() {
		return roomCommentStr;
	}

	public void setRoomCommentStr(String roomCommentStr) {
		this.roomCommentStr = roomCommentStr;
	}

	public int getRoomScore() {
		return roomScore;
	}

	public void setRoomScore(int roomScore) {
		this.roomScore = roomScore;
	}

}
