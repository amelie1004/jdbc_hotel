package hotel.vo;

public class RoomTypeInfo {
	private int typeNo;
	private String typeStr;

	public RoomTypeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomTypeInfo(int typeNo, String typeStr) {
		super();
		this.typeNo = typeNo;
		this.typeStr = typeStr;
	}

	public int getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
}
