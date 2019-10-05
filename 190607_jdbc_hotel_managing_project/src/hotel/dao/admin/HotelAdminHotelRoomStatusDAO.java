package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.RoomStatusInfo;

public class HotelAdminHotelRoomStatusDAO extends HotelAdminBasicDAO {
	private static int TypeID = 0;
	
	public HotelAdminHotelRoomStatusDAO() {
		
	}

	public static int getTypeID() {
		return TypeID;
	}

	public static void setTypeID(int typeID) {
		TypeID = typeID;
	}
	
	public boolean insertHotelRoomStatusInfo(int statusNo, String statusName) {
		boolean isInserted = false;
		int rowCnt = 0;
		
		//TypeID = getNextTypeID();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into room_status_info values(?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, statusNo);
			pstmt.setString(2, statusName);

			rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
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
	
	public boolean modifyHotelRoomStatusInfoByID(int statusNo, String statusName) {
		boolean isModified = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "update room_status_info "
					+ "set status=? where status_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, statusName);
			pstmt.setInt(2, statusNo);
			
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
	
	public boolean deleteHotelRoomStatusInfoByID(int statusNo) {
		boolean isDeleted = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete room_status_info where status_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, statusNo);
			
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
	
	public RoomStatusInfo getHotelRoomStatusInfoByID(int statusNo) {
		RoomStatusInfo resInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from room_status_info where status_no=\'" + statusNo + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				String statusName = rs.getString("status");
				
				resInfo = new RoomStatusInfo(statusNo, statusName);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}
	
	public List<RoomStatusInfo> getAllHotelRoomStatus() {
		List<RoomStatusInfo> resList = new ArrayList<RoomStatusInfo>();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from room_status_info order by status_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int statusNo = rs.getInt("status_no");
				String statusName = rs.getString("status");

				RoomStatusInfo typeInfo = new RoomStatusInfo(statusNo, statusName);
				
				resList.add(typeInfo);
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
