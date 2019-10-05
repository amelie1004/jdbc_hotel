package hotel.vo;

public class HotelCommentInfo {
	private int commentNo;
	private int hotelNo;
	private int customerNo;
	private String commentStr;
	private int hotelScore;

	public HotelCommentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelCommentInfo(String commentStr, int hotelScore) {
		super();
		this.commentStr = commentStr;
		this.hotelScore = hotelScore;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getHotelNo() {
		return hotelNo;
	}

	public void setHotelNo(int hotelNo) {
		this.hotelNo = hotelNo;
	}

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public String getCommentStr() {
		return commentStr;
	}

	public void setCommentStr(String commentStr) {
		this.commentStr = commentStr;
	}

	public int getHotelScore() {
		return hotelScore;
	}

	public void setHotelScore(int hotelScore) {
		this.hotelScore = hotelScore;
	}

}
