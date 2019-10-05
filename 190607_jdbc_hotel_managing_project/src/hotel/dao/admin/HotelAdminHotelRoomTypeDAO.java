package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.RoomTypeInfo;

public class HotelAdminHotelRoomTypeDAO extends HotelAdminBasicDAO {
	private static int TypeID = 0;
	
	public HotelAdminHotelRoomTypeDAO() {
		
	}

	public static int getTypeID() {
		return TypeID;
	}

	public static void setTypeID(int typeID) {
		TypeID = typeID;
	}
	
	public boolean insertHotelRoomTypeInfo(int typeNo, String typeName) {
		boolean isInserted = false;
		int rowCnt = 0;
		
		//TypeID = getNextTypeID();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into room_type_info values(?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, typeNo);
			pstmt.setString(2, typeName);

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
	
	public boolean modifyHotelRoomTypeInfoByID(int typeID, String typeName) {
		boolean isModified = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "update room_type_info "
					+ "set type=? where type_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, typeName);
			pstmt.setInt(2, typeID);
			
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
	
	public boolean deleteHotelRoomTypeInfoByID(int typeID) {
		boolean isDeleted = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete room_type_info where type_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, typeID);
			
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
	
	public RoomTypeInfo getHotelRoomTypeByID(int typeID) {
		RoomTypeInfo resInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from room_type_info where type_no=" + typeID;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int typeNo = rs.getInt("type_no");
				String typeName = rs.getString("type");

				resInfo = new RoomTypeInfo(typeNo, typeName);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}
	
	public List<RoomTypeInfo> getAllHotelRoomType() {
		List<RoomTypeInfo> resList = new ArrayList<RoomTypeInfo>();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from room_type_info order by type_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int typeNo = rs.getInt("type_no");
				String typeName = rs.getString("type");

				RoomTypeInfo typeInfo = new RoomTypeInfo(typeNo, typeName);
				
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
