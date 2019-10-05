package hotel.dao.admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.HotelReservationInfo;

public class HotelAdminHotelReservationDAO extends HotelAdminBasicDAO {
	private static int HotelRoomReservationID = 0;
	
	public HotelAdminHotelReservationDAO() {
		
	}

	public static int getHotelRoomReservationID() {
		return HotelRoomReservationID;
	}

	public static void setHotelRoomReservationID(int hotelRoomReservationID) {
		HotelRoomReservationID = hotelRoomReservationID;
	}
	
	// sequence name : hotel_reserve_seq
	public HotelReservationInfo getReservationInfoByID(int reservationID) {
		HotelReservationInfo resInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_reservation_list where hotel_reservation_no=" + reservationID;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
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
				
				resInfo = new HotelReservationInfo(startDate, endDate, signDate, signCancelDate, adultCnt, childrenCnt, requestStr, isHotdeal);
				resInfo.setReservationNo(reservationNo);
				resInfo.setCustomerNo(customerNo);
				resInfo.setRoomNo(roomNo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}
	
	public List<HotelReservationInfo> getReservationInfoByCustomer(int customerId) {
		List<HotelReservationInfo> resList = new ArrayList<HotelReservationInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_reservation_list where customer_no=" + customerId;
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
	
	public List<HotelReservationInfo> getReservationInfoByRoom(int targetRoomNo) {
		List<HotelReservationInfo> resList = new ArrayList<HotelReservationInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_reservation_list where room_no=" + targetRoomNo;
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
	
	public boolean deleteReserveListByID(int reservationId) {
		boolean isDeleted = false;
		
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete hotel_reservation_list where hotel_reservation_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, reservationId);
			
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
	
	public int getNextHotelReservationID() {
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select reservation_no from number_info where id=14";
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
