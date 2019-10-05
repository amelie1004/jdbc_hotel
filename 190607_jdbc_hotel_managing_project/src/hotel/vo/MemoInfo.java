package hotel.vo;

public class MemoInfo {
	private int memoNo;
	private int customerNo;
	private String loginID;
	private String memoTitle;
	private String memoDetailStr;
	private String writtenDate;

	public MemoInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemoInfo(int customerNo, String memoTitle, String memoDetailStr) {
		super();
		this.customerNo = customerNo;
		this.memoTitle = memoTitle;
		this.memoDetailStr = memoDetailStr;
	}

	public MemoInfo(String loginID, String memoTitle, String memoDetailStr) {
		super();
		this.loginID = loginID;
		this.memoTitle = memoTitle;
		this.memoDetailStr = memoDetailStr;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public int getMemoNo() {
		return memoNo;
	}

	public void setMemoNo(int memoNo) {
		this.memoNo = memoNo;
	}

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public String getMemoTitle() {
		return memoTitle;
	}

	public void setMemoTitle(String memoTitle) {
		this.memoTitle = memoTitle;
	}

	public String getMemoDetailStr() {
		return memoDetailStr;
	}

	public void setMemoDetailStr(String memoDetailStr) {
		this.memoDetailStr = memoDetailStr;
	}
	
	public String getWrittenDate() {
		return writtenDate;
	}

	public void setWrittenDate(String writtenDate) {
		this.writtenDate = writtenDate;
	}
}
