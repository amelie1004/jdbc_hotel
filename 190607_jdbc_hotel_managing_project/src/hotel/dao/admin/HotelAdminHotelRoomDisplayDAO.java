package hotel.dao.admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import hotel.vo.RoomDisplayInfo;

public class HotelAdminHotelRoomDisplayDAO extends HotelAdminBasicDAO {
	private static int HotelRoomDisplayID = 0;
	
	public HotelAdminHotelRoomDisplayDAO() {
		
	}

	public static int getHotelRoomDisplayID() {
		return HotelRoomDisplayID;
	}

	public static void setHotelRoomDisplayID(int hotelRoomDisplayID) {
		HotelRoomDisplayID = hotelRoomDisplayID;
	}
	
	/*
	private int displayNo;
	private int roomNo;
	private int hotelNo;
	private Date startDate;
	private Date endDate;
	private int pricePerDay;
	private Date hotdealStartDate;
	*/
	
	// sequence name : room_display_seq
	public boolean insertRoomDisplayInfo(int roomNo, Date dealStartDate, Date dealEndDate, int pricePerDay, int isDealed) {
		boolean isInserted = false;
		int rowCnt = 0;
		HotelRoomDisplayID = getNextHotelRoomDisplayID();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into room_display_list(room_display_no, room_no, deal_start_date, deal_end_date, price_per_day, is_dealed) "
					+ "values(room_display_seq.nextval,?,?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, roomNo);
			pstmt.setDate(2, dealStartDate);
			pstmt.setDate(3, dealEndDate);
			pstmt.setInt(4, pricePerDay);
			pstmt.setInt(5, isDealed);
			
			rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
				sql = "update number_info set room_display_no=? where id=13";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, HotelRoomDisplayID);
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
	
	public boolean updateRoomDisplayInfo(int roomDisplayNo, int roomNo, Date dealStartDate, Date dealEndDate, int pricePerDay,
				Date hotdealDate, int isDealed) {
		boolean isModified = false;
		int rowCnt = 0, argCnt = 1;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "update room_display_list "
					+ "set room_no=?, "
					+ ( (dealStartDate != null) ? "deal_start_date=?, " : "")
					+ ( (dealEndDate != null) ? "deal_end_date=?, " : "")
					+ "price_per_day=? "
					+ ( (hotdealDate != null) ? ", hotdeal_changing_date=?" : "")
					+ ", is_dealed=? "
					+ "where room_display_no=?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(argCnt++, roomNo);
			if (dealStartDate != null) {
				pstmt.setDate(argCnt++, dealStartDate);
			}
			if (dealEndDate != null) {
				pstmt.setDate(argCnt++, dealEndDate);
			}
			pstmt.setInt(argCnt++, pricePerDay);
			if (hotdealDate != null) {
				pstmt.setDate(argCnt++, hotdealDate);
			}
			
			pstmt.setInt(argCnt++, isDealed);
			pstmt.setInt(argCnt++, roomDisplayNo);

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
	
	public boolean deleteRoomDisplayInfo(int roomDisplayNo) {
		boolean isDeleted = false;
		
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete room_display_list where room_display_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, roomDisplayNo);
			
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
	
	public RoomDisplayInfo getRoomDisplayInfoByID(int roomDisplayID) {
		RoomDisplayInfo resInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from room_display_list where room_display_no=\'" + roomDisplayID + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				int displayNo = rs.getInt("room_display_no");
				int roomNo = rs.getInt("room_no");
				Date startDate = rs.getDate("deal_start_date");
				Date endDate = rs.getDate("deal_end_date");
				int pricePerDay = rs.getInt("price_per_day");
				Date hotdealStartDate = rs.getDate("hotdeal_changing_date");
				int isDealed = rs.getInt("is_dealed");
				
				resInfo = new RoomDisplayInfo(roomNo, startDate, endDate, pricePerDay);
				resInfo.setDisplayNo(displayNo);
				resInfo.setHotdealStartDate(hotdealStartDate);
				resInfo.setIsDealed(isDealed);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}
	
	public List<RoomDisplayInfo> getRoomDisplayInfoByRoomID(int roomID) {
		List<RoomDisplayInfo> resList = new ArrayList<RoomDisplayInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from room_display_list where room_no=" + roomID;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int displayNo = rs.getInt("room_display_no");
				int roomNo = rs.getInt("room_no");
				Date startDate = rs.getDate("deal_start_date");
				Date endDate = rs.getDate("deal_end_date");
				int pricePerDay = rs.getInt("price_per_day");
				Date hotdealStartDate = rs.getDate("hotdeal_changing_date");
				int isDealed = rs.getInt("is_dealed");
				
				RoomDisplayInfo resInfo = new RoomDisplayInfo(roomNo, startDate, endDate, pricePerDay);
				resInfo.setDisplayNo(displayNo);
				resInfo.setHotdealStartDate(hotdealStartDate);
				resInfo.setIsDealed(isDealed);
				
				resList.add(resInfo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resList;
	}
	
	public List<RoomDisplayInfo> getAllRoomDisplayInfoByDealed() {
		List<RoomDisplayInfo> resList = new ArrayList<RoomDisplayInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from room_display_list where is_dealed=1";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int displayNo = rs.getInt("room_display_no");
				int roomNo = rs.getInt("room_no");
				Date startDate = rs.getDate("deal_start_date");
				Date endDate = rs.getDate("deal_end_date");
				int pricePerDay = rs.getInt("price_per_day");
				Date hotdealStartDate = rs.getDate("hotdeal_changing_date");
				int isDealed = rs.getInt("is_dealed");
				
				RoomDisplayInfo resInfo = new RoomDisplayInfo(roomNo, startDate, endDate, pricePerDay);
				resInfo.setDisplayNo(displayNo);
				resInfo.setHotdealStartDate(hotdealStartDate);
				resInfo.setIsDealed(isDealed);
				
				resList.add(resInfo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resList;
	}
	
	public int getNextHotelRoomDisplayID() {
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select room_display_no from number_info where id=13";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("room_display_no");
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
