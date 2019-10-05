package hotel.dao.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.MemoInfo;

public class HotelMemoDAO extends HotelBasicDAO {
	private static int MemoID = 0;
	
	public HotelMemoDAO() {
		
	}
	
	// sequence : memo_seq
	public boolean insertMemo(String loginID, String title, String contents) {
		boolean isInserted = false;
		int rowCnt = 0;
		
		MemoID = getNextMemoID();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into hotel_admin.customer_memo_list(memo_no, customer_no, memo_title, memo_detail_str, written_date) values("
					+ "hotel_admin.memo_seq.nextval, "
					+ "(select customer_no from hotel_admin.customer_list where login_id=?), "
					+ "?, ?, sysdate)";
					//+ "?, ?, to_char(sysdate, 'YYYY-MM-DD HH:MI:SS'))";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, loginID);
			pstmt.setString(2, title);
			pstmt.setString(3, contents);

			rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
				sql = "update hotel_admin.number_info set memo_no=? where id=4";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, MemoID);
				pstmt.executeUpdate();
				
				isInserted = true;
			}

			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("Data 입력 실패..");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			disconnect(con);
		}

		return isInserted;
	}
	
	public boolean deleteMemo(String loginID, int memoNo) {
		boolean isDeleted = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete hotel_admin.customer_memo_list "
						+ "where memo_no = ?"
						+ "and customer_no = "
						+ "(select customer_no from hotel_admin.customer_list "
						+ "where login_id = ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, memoNo);
			pstmt.setString(2, loginID);
			
			rowCnt = pstmt.executeUpdate();
			if (rowCnt > 0) {
				isDeleted = true;
			}

			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("Data 삭제 실패..");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return isDeleted;
	}
	
	public boolean modifyMemo(String loginID, int memoNo, String title, String contents) {
		boolean isModified = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "update hotel_admin.customer_memo_list "
						+ "set memo_title=?, memo_detail_str=?, written_date=sysdate "
						+ "where memo_no=? "
						+ "and customer_no=(select customer_no from hotel_admin.customer_list "
						+ "where login_id=?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setInt(3, memoNo);
			pstmt.setString(4, loginID);
			
			rowCnt = pstmt.executeUpdate();
			if (rowCnt > 0) {
				isModified = true;
			}

			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("Data 수정 실패..");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return isModified;
	}
	
	public List<MemoInfo> searchAllMemo(String loginID) {
		List<MemoInfo> memoList = new ArrayList<MemoInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			String sql = "select memo_no, customer_no, memo_title, memo_detail_str, to_char(written_date, 'YYYY-MM-DD HH:MI:SS') as written_date from hotel_admin.customer_memo_list "
					+ "where customer_no = (select customer_no from hotel_admin.customer_list "
					+ "where login_id = \'" + loginID + "\') order by memo_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {				
				int memoNo = rs.getInt("memo_no");
				int customerNo = rs.getInt("customer_no");
				String memoTitle = rs.getString("memo_title");
				String memoContents = rs.getString("memo_detail_str");
				String memoDate = rs.getString("written_date");
				
				MemoInfo res = new MemoInfo(customerNo, memoTitle, memoContents);
				res.setMemoNo(memoNo);
				res.setWrittenDate(memoDate);
				
				memoList.add(res);
			}
			
		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return memoList;
	}
	
	public List<MemoInfo> searchMemoByTitleAndContents(String loginID, String title, String contents) {
		List<MemoInfo> memoList = new ArrayList<MemoInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			String sql = "select memo_no, customer_no, memo_title, memo_detail_str, to_char(written_date, 'YYYY-MM-DD HH:MI:SS') as written_date from hotel_admin.customer_memo_list "
					+ "where customer_no = (select customer_no from hotel_admin.customer_list "
					+ "where login_id = \'" + loginID + "\') "
					+ "and (memo_title like \'%" + title + "%\' or memo_detail_str like \'%" + contents + "%\') order by memo_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {				
				int memoNo = rs.getInt("memo_no");
				int customerNo = rs.getInt("customer_no");
				String memoTitle = rs.getString("memo_title");
				String memoContents = rs.getString("memo_detail_str");
				String memoDate = rs.getString("written_date");

				MemoInfo res = new MemoInfo(customerNo, memoTitle, memoContents);
				res.setMemoNo(memoNo);
				res.setWrittenDate(memoDate);
				
				memoList.add(res);
			}
			
		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return memoList;
	}
	
	public MemoInfo searchDetailCustomerMemo(String loginID, int targetMemoNo) {
		MemoInfo resInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			String sql = "select memo_no, customer_no, memo_title, memo_detail_str, to_char(written_date, 'YYYY-MM-DD HH:MI:SS') as written_date from hotel_admin.customer_memo_list "
					+ "where customer_no = (select customer_no from hotel_admin.customer_list "
					+ "where login_id = \'" + loginID + "\') "
					+ "and memo_no = " + targetMemoNo;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {				
				int memoNo = rs.getInt("memo_no");
				int customerNo = rs.getInt("customer_no");
				String memoTitle = rs.getString("memo_title");
				String memoContents = rs.getString("memo_detail_str");
				String memoDate = rs.getString("written_date");

				resInfo = new MemoInfo(customerNo, memoTitle, memoContents);
				resInfo.setMemoNo(memoNo);
				resInfo.setWrittenDate(memoDate);
			}
			
		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}
	
	public int getNextMemoID() {
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select memo_no from hotel_admin.number_info where id=4";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("memo_no");
			}
			// System.out.println("result : " + result);

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}

		return result + 1;
	}
}
