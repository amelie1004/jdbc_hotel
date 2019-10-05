package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.MemoInfo;

public class HotelAdminMemoDAO extends HotelAdminBasicDAO {
	
	public HotelAdminMemoDAO() {
		
	}
	
	public boolean deleteMemo(int memoNo) {
		boolean isDeleted = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete customer_memo_list where memo_no = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, memoNo);
			
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
	
	public List<MemoInfo> searchMemo(String loginID) {
		List<MemoInfo> memoList = new ArrayList<MemoInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			String sql = "select m.memo_no as memo_no, c.login_id as login_id, m.memo_title as memo_title, m.memo_detail_str as memo_detail_str, to_char(m.written_date, 'YYYY-MM-DD HH:MI:SS') as written_date "
					+ "from customer_memo_list m join customer_list c "
					+ "on m.customer_no = c.customer_no "
					+ "where login_id=\'" + loginID + "\' order by memo_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {				
				int memoNo = rs.getInt("memo_no");
				String login_ID = rs.getString("login_id");
				String memoTitle = rs.getString("memo_title");
				String memoContents = rs.getString("memo_detail_str");
				String memoDate = rs.getString("written_date");
				
				MemoInfo res = new MemoInfo(login_ID, memoTitle, memoContents);
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
	
	public List<MemoInfo> searchAllMemo() {
		List<MemoInfo> memoList = new ArrayList<MemoInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			String sql = "select m.memo_no as memo_no, c.login_id as login_id, m.memo_title as memo_title, m.memo_detail_str as memo_detail_str, to_char(m.written_date, 'YYYY-MM-DD HH:MI:SS') as written_date "
					+ "from customer_memo_list m join customer_list c "
					+ "on m.customer_no = c.customer_no order by memo_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {				
				int memoNo = rs.getInt("memo_no");
				String login_ID = rs.getString("login_id");
				String memoTitle = rs.getString("memo_title");
				String memoContents = rs.getString("memo_detail_str");
				String memoDate = rs.getString("written_date");
				
				MemoInfo res = new MemoInfo(login_ID, memoTitle, memoContents);
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
}
