package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.HotelTypeInfo;

public class HotelAdminHotelTypeDAO extends HotelAdminBasicDAO {
	private static int TypeID = 0;

	public HotelAdminHotelTypeDAO() {
		
	}

	public static int getTypeID() {
		return TypeID;
	}

	public static void setTypeID(int typeID) {
		TypeID = typeID;
	}
	
	// sequence name : hotel_type_seq
	public boolean insertHotelTypeInfo(int typeNo, String typeName) {
		boolean isInserted = false;
		int rowCnt = 0;
		
		//TypeID = getNextTypeID();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into hotel_type_info values(?, ?)";
			
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
	
	public boolean modifyHotelTypeInfoByID(int typeID, String typeName) {
		boolean isModified = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "update hotel_type_info "
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
	
	public boolean deleteHotelTypeInfoByID(int typeID) {
		boolean isDeleted = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete hotel_type_info where type_no=?";
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
	
	public HotelTypeInfo getHotelTypeByID(int targetID) {
		HotelTypeInfo typeInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_type_info where type_no=" + targetID;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int typeNo = rs.getInt("type_no");
				String typeName = rs.getString("type");

				typeInfo = new HotelTypeInfo(typeName);
				typeInfo.setTypeNo(typeNo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		
		return typeInfo;
	}
	
	public List<HotelTypeInfo> getAllHotelType() {
		List<HotelTypeInfo> resList = new ArrayList<HotelTypeInfo>();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_type_info order by type_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int typeNo = rs.getInt("type_no");
				String typeName = rs.getString("type");

				HotelTypeInfo typeInfo = new HotelTypeInfo(typeName);
				typeInfo.setTypeNo(typeNo);
				
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
	
	public int getNextTypeID() {
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select hotel_type_no from number_info where id=7";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("hotel_type_no");
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
