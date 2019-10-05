package hotel.vo;

public class CustomerClassInfo {
	private int classNo;
	private String className;
	private int discountRate;

	public CustomerClassInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerClassInfo(int classNo, String className, int discountRate) {
		super();
		this.classNo = classNo;
		this.className = className;
		this.discountRate = discountRate;
	}

	public CustomerClassInfo(String className, int discountRate) {
		super();
		this.className = className;
		this.discountRate = discountRate;
	}

	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

}
