package hotel.vo;

public class RoomStatusInfo {
	private int statusNo;
	private String statusStr;

	public RoomStatusInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomStatusInfo(int statusNo, String statusStr) {
		super();
		this.statusNo = statusNo;
		this.statusStr = statusStr;
	}

	public RoomStatusInfo(String statusStr) {
		super();
		this.statusStr = statusStr;
	}

	public int getStatusNo() {
		return statusNo;
	}

	public void setStatusNo(int statusNo) {
		this.statusNo = statusNo;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

}
