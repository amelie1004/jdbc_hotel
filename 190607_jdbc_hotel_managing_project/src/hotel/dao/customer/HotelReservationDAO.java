package hotel.dao.customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.HotelReservationInfo;

public class HotelReservationDAO extends HotelBasicDAO {
	private static int HotelRoomReservationID = 0;
	
	public HotelReservationDAO () {
		
	}
	
	/*
	private int reservationNo;
	private int customerNo;
	private int roomNo;
	private int hotelNo;
	private Date startDate;
	private Date endDate;
	private Date signDate;
	private Date signCancelDate;
	private int adultCnt;
	private int childrenCnt;
	private String requestStr;
	private String isHotdeal;
	*/
	
	public static int getHotelRoomReservationID() {
		return HotelRoomReservationID;
	}

	public static void setHotelRoomReservationID(int hotelRoomReservationID) {
		HotelRoomReservationID = hotelRoomReservationID;
	}

	// sequence name : hotel_reserve_seq
	public boolean insertReservationInfoByRoomID(int customerNo, int roomNo, Date startDate, Date endDate,
			int adultCnt, int childrenCnt, String requestStr) {
		boolean isInserted = false;
		int rowCnt = 0;
		
		Connection con = null;

		HotelRoomReservationID = getNextHotelReservationID();
		
		try {
			con = getConnection();

			String sql = "insert into hotel_admin.hotel_reservation_list"
					+ "(hotel_reservation_no, customer_no, room_no, reservation_start_date, reservation_end_date,"
					+ "sign_date, customer_adult_count, customer_children_count, customer_request) "
					+ "values (hotel_admin.hotel_reserve_seq.nextval, ?, ?, ?, ?,"
					+ "sysdate, ?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, customerNo);
			pstmt.setInt(2, roomNo);
			pstmt.setDate(3, startDate);
			pstmt.setDate(4, endDate);
			pstmt.setInt(5, adultCnt);
			pstmt.setInt(6, childrenCnt);
			pstmt.setString(7, requestStr);

			rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
				sql = "update hotel_admin.number_info set reservation_no=? where id=14";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, HotelRoomReservationID);
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
	
	public boolean cancelSignReservationInfo(int customerNo, int reservationNo) {
		boolean isCanceled = false;
		int rowCnt = 0;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "update hotel_admin.hotel_reservation_list set sign_cancel_date=sysdate "
					+ "where customer_no=? and hotel_reservation_no=?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, customerNo);
			pstmt.setInt(2, reservationNo);

			rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
				isCanceled = true;
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
		
		return isCanceled;
	}
	
	public List<HotelReservationInfo> getAllReservedInfoByCustomerNo(int targetCustomerNo) {
		List<HotelReservationInfo> resList = new ArrayList<HotelReservationInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_admin.hotel_reservation_list where customer_no=" + targetCustomerNo
					+ " and sign_cancel_date is null";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int reservationNo = rs.getInt("hotel_reservation_no");
				int customerNo = rs.getInt("customer_no");
				int roomNo = rs.getInt("room_no");
				Date startDate = rs.getDate("reservation_start_date");
				Date endDate = rs.getDate("reservation_end_date");
				Date signDate = rs.getDate("sign_date");
				Date signCancelDate = rs.getDate("sign_cancel_date");
				int adultCnt = rs.getInt("customer_adult_count");
				int childrenCnt = rs.getInt("customer_children_count");
				String isHotdeal = rs.getString("is_hotdeal");
				String requestStr = rs.getString("customer_request");
				
				HotelReservationInfo resInfo = new HotelReservationInfo(startDate, endDate, signDate, signCancelDate, adultCnt, childrenCnt, requestStr, isHotdeal);
				resInfo.setReservationNo(reservationNo);
				resInfo.setCustomerNo(customerNo);
				resInfo.setRoomNo(roomNo);
				
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
	
	
	
	public int getNextHotelReservationID() {
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select reservation_no from hotel_admin.number_info where id=14";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("reservation_no");
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
