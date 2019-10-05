package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.HotelRoomInfo;

public class HotelAdminHotelRoomDAO extends HotelAdminBasicDAO {
	private static int HotelRoomID = 0;
	
	public HotelAdminHotelRoomDAO() {
		
	}

	public static int getHotelRoomID() {
		return HotelRoomID;
	}

	public static void setHotelRoomID(int hotelRoomID) {
		HotelRoomID = hotelRoomID;
	}
	
	// sequence name : hotel_room_seq
	public boolean insertHotelRoomInfo(int hotelNo, int managerNo, int typeNo, int statusNo,
			String roomSize, int roomCount, int roomRealPos) {
		boolean isInserted = false;
		int rowCnt = 0, argCnt = 1;
		
		HotelRoomID = getNextHotelRoomID();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into hotel_room_list values(hotel_room_seq.nextval,?" 
					+ ( (managerNo > 0) ? ",?" : ", null" )
					+ ",?,?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(argCnt++, hotelNo);
			if (managerNo > 0) {
				pstmt.setInt(argCnt++, managerNo);
			}
			pstmt.setInt(argCnt++, typeNo);
			pstmt.setInt(argCnt++, statusNo);
			pstmt.setString(argCnt++, roomSize);
			pstmt.setInt(argCnt++, roomCount);
			pstmt.setInt(argCnt++, roomRealPos);

			rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
				sql = "update number_info set hotel_room_no=? where id=12";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, HotelRoomID);
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
	
	public boolean updateHotelRoomInfo(int roomNo, int hotelNo, int managerNo, int typeNo, int statusNo,
			String roomSize, int roomCount, int roomRealPos) {
		boolean isModified = false;
		int rowCnt = 0, argCnt = 1;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "update hotel_room_list "
					+ "set hotel_no=?, "
					+ ( (managerNo > 0) ? "manager_no=?, " : "")
					+ "room_type_no=?, room_status_no=?, room_size=?, room_count=?, room_real_no=?"
					+ "where room_no=?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(argCnt++, hotelNo);
			if (managerNo > 0) {
				pstmt.setInt(argCnt++, managerNo);
			}
			pstmt.setInt(argCnt++, typeNo);
			pstmt.setInt(argCnt++, statusNo);
			pstmt.setString(argCnt++, roomSize);
			pstmt.setInt(argCnt++, roomCount);
			pstmt.setInt(argCnt++, roomRealPos);
			pstmt.setInt(argCnt++, roomNo);

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
	
	public boolean deleteHotelRoomInfoByID(int roomId) {
		boolean isDeleted = false;
		
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete hotel_room_list where room_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, roomId);
			
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
	
	public HotelRoomInfo getHotelRoomInfoByID(int roomId) {
		HotelRoomInfo resInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_room_list where room_no=\'" + roomId + "\'";
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
	
	public List<HotelRoomInfo> getHotelRoomInfoByRealPos(int posNum) {
		List<HotelRoomInfo> resList = new ArrayList<HotelRoomInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_room_list where room_real_no=\'" + posNum + "\' order by room_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int roomId = rs.getInt("room_no");
				int hotelNo = rs.getInt("hotel_no");
				int managerNo = rs.getInt("manager_no");
				int roomTypeNo = rs.getInt("room_type_no");
				int statusNo = rs.getInt("room_status_no");
				String roomSize = rs.getString("room_size");
				int roomCnt = rs.getInt("room_count");
				int roomRealNo = rs.getInt("room_real_no");
				
				HotelRoomInfo resInfo = new HotelRoomInfo(hotelNo, managerNo, roomTypeNo, statusNo, roomSize, roomCnt, roomRealNo);
				resInfo.setRoomNo(roomId);
				
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
	
	public List<HotelRoomInfo> getHotelRoomInfoByHotelID(int hotelID) {
		List<HotelRoomInfo> resList = new ArrayList<HotelRoomInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_room_list where hotel_no=\'" + hotelID + "\' order by room_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int roomId = rs.getInt("room_no");
				int hotelNo = rs.getInt("hotel_no");
				int managerNo = rs.getInt("manager_no");
				int roomTypeNo = rs.getInt("room_type_no");
				int statusNo = rs.getInt("room_status_no");
				String roomSize = rs.getString("room_size");
				int roomCnt = rs.getInt("room_count");
				int roomRealNo = rs.getInt("room_real_no");
				
				HotelRoomInfo resInfo = new HotelRoomInfo(hotelNo, managerNo, roomTypeNo, statusNo, roomSize, roomCnt, roomRealNo);
				resInfo.setRoomNo(roomId);
				
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
	
	public List<HotelRoomInfo> getHotelRoomInfoByType(int typeNo) {
		List<HotelRoomInfo> resList = new ArrayList<HotelRoomInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_room_list where room_type_no=\'" + typeNo + "\' order by room_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int roomId = rs.getInt("room_no");
				int hotelNo = rs.getInt("hotel_no");
				int managerNo = rs.getInt("manager_no");
				int roomTypeNo = rs.getInt("room_type_no");
				int statusNo = rs.getInt("room_status_no");
				String roomSize = rs.getString("room_size");
				int roomCnt = rs.getInt("room_count");
				int roomRealNo = rs.getInt("room_real_no");
				
				HotelRoomInfo resInfo = new HotelRoomInfo(hotelNo, managerNo, roomTypeNo, statusNo, roomSize, roomCnt, roomRealNo);
				resInfo.setRoomNo(roomId);
				
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
	
	public List<HotelRoomInfo> getHotelRoomInfoByStatus(int statusId) {
		List<HotelRoomInfo> resList = new ArrayList<HotelRoomInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_room_list where room_status_no=\'" + statusId + "\' order by room_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int roomId = rs.getInt("room_no");
				int hotelNo = rs.getInt("hotel_no");
				int managerNo = rs.getInt("manager_no");
				int roomTypeNo = rs.getInt("room_type_no");
				int statusNo = rs.getInt("room_status_no");
				String roomSize = rs.getString("room_size");
				int roomCnt = rs.getInt("room_count");
				int roomRealNo = rs.getInt("room_real_no");
				
				HotelRoomInfo resInfo = new HotelRoomInfo(hotelNo, managerNo, roomTypeNo, statusNo, roomSize, roomCnt, roomRealNo);
				resInfo.setRoomNo(roomId);
				
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
	
	public List<HotelRoomInfo> getAllHotelRoomInfo() {
		List<HotelRoomInfo> resList = new ArrayList<HotelRoomInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_room_list order by room_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int roomId = rs.getInt("room_no");
				int hotelNo = rs.getInt("hotel_no");
				int managerNo = rs.getInt("manager_no");
				int roomTypeNo = rs.getInt("room_type_no");
				int statusNo = rs.getInt("room_status_no");
				String roomSize = rs.getString("room_size");
				int roomCnt = rs.getInt("room_count");
				int roomRealNo = rs.getInt("room_real_no");
				
				HotelRoomInfo resInfo = new HotelRoomInfo(hotelNo, managerNo, roomTypeNo, statusNo, roomSize, roomCnt, roomRealNo);
				resInfo.setRoomNo(roomId);
				
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
	
	public int getNextHotelRoomID() {
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select hotel_room_no from number_info where id=12";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("hotel_room_no");
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
