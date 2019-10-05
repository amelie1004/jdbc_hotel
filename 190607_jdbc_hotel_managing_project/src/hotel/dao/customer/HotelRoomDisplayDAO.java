package hotel.dao.customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.HotelRoomInfo;
import hotel.vo.RoomDisplayInfo;

public class HotelRoomDisplayDAO extends HotelBasicDAO {
	
	public HotelRoomDisplayDAO() {
		
	}
	
	public RoomDisplayInfo getRoomDisplayInfoByID(int roomDisplayID) {
		RoomDisplayInfo resInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_admin.room_display_list where room_display_no=\'" + roomDisplayID + "\'";
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
	
	public HotelRoomInfo getHotelRoomInfoByID(int roomId) {
		HotelRoomInfo resInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_admin.hotel_room_list where room_no=\'" + roomId + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				int hotelNo = rs.getInt("hotel_no");
				int managerNo = rs.getInt("manager_no");
				int roomTypeNo = rs.getInt("room_type_no");
				int statusNo = rs.getInt("room_status_no");
				String roomSize = rs.getString("room_size");
				int roomCnt = rs.getInt("room_count");
				int roomRealNo = rs.getInt("room_real_no");
				
				resInfo = new HotelRoomInfo(hotelNo, managerNo, roomTypeNo, statusNo, roomSize, roomCnt, roomRealNo);
				resInfo.setRoomNo(roomId);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}
	
	public List<RoomDisplayInfo> getAllAccessibleRoomList() {
		List<RoomDisplayInfo> resList = new ArrayList<RoomDisplayInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_admin.room_display_list where is_dealed=1";
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
}
