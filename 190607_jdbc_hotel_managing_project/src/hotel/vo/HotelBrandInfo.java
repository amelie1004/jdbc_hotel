package hotel.vo;

public class HotelBrandInfo {
	private int brandNo;
	private String brandName;
	private String address;
	private String ceoName;
	private int workerCnt;

	public HotelBrandInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HotelBrandInfo(String brandName, String address, String ceoName, int workerCnt) {
		super();
		this.brandName = brandName;
		this.address = address;
		this.ceoName = ceoName;
		this.workerCnt = workerCnt;
	}

	public int getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(int brandNo) {
		this.brandNo = brandNo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	public int getWorkerCnt() {
		return workerCnt;
	}

	public void setWorkerCnt(int workerCnt) {
		this.workerCnt = workerCnt;
	}

}
