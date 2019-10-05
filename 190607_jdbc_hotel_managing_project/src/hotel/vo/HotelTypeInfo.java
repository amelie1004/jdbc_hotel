package hotel.vo;

public class HotelTypeInfo {
	private int typeNo;
	private String typeStr;

	public HotelTypeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelTypeInfo(String typeStr) {
		super();
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
