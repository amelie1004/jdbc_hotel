package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.HotelBrandInfo;

public class HotelAdminHotelBrandDAO extends HotelAdminBasicDAO {
	private static int BrandID = 0;

	public HotelAdminHotelBrandDAO() {

	}

	public static int getBrandID() {
		return BrandID;
	}

	public static void setBrandID(int brandID) {
		BrandID = brandID;
	}

	// sequence name : hotel_brand_seq
	public boolean insertBrandInfo(String brandName, String address, String ceoName, int workerCnt) {
		boolean isInserted = false;
		int rowCnt = 0;
		
		BrandID = getNextBrandID();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into hotel_brand_info "
					+ "values(hotel_brand_seq.nextval, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, brandName);
			pstmt.setString(2, address);
			pstmt.setString(3, ceoName);
			pstmt.setInt(4, workerCnt);

			rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
				sql = "update number_info set hotel_brand_no=? where id=6";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, BrandID);
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
	
	public HotelBrandInfo getHotelBrandByID(int targetID) {
		HotelBrandInfo brandInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_brand_info where brand_no=" + targetID;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int brandNo = rs.getInt("brand_no");
				String brandName = rs.getString("brand_name");
				String address = rs.getString("address");
				String ceoName = rs.getString("ceo_name");
				int workerCnt = rs.getInt("worker_count");

				brandInfo = new HotelBrandInfo(brandName, address, ceoName, workerCnt);
				brandInfo.setBrandNo(brandNo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return brandInfo;
	}
	
	public HotelBrandInfo getHotelBrandByName(String targetName) {
		HotelBrandInfo brandInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_brand_info where brand_name=\'" + targetName + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int brandNo = rs.getInt("brand_no");
				String brandName = rs.getString("brand_name");
				String address = rs.getString("address");
				String ceoName = rs.getString("ceo_name");
				int workerCnt = rs.getInt("worker_count");

				brandInfo = new HotelBrandInfo(brandName, address, ceoName, workerCnt);
				brandInfo.setBrandNo(brandNo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return brandInfo;
	}
	
	public boolean modifyHotelBrandByID(int brandID, String brandName, String address, String ceoName, int workerCnt) {
		boolean isModified = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "update hotel_brand_info "
					+ "set brand_name=?, address=?, ceo_name=?, worker_count=? "
					+ "where brand_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, brandName);
			pstmt.setString(2, address);
			pstmt.setString(3, ceoName);
			pstmt.setInt(4, workerCnt);
			pstmt.setInt(5, brandID);
			
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
	
	public boolean deleteHotelBrandByID(int brandID) {
		boolean isDeleted = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete hotel_brand_info where brand_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, brandID);
			
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
	
	public boolean deleteHotelBrandByName(String brandName) {
		boolean isDeleted = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete hotel_brand_info where brand_name=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, brandName);
			
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
	
	public List<HotelBrandInfo> getAllHotelBrand() {
		List<HotelBrandInfo> resList = new ArrayList<HotelBrandInfo>();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_brand_info order by brand_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int brandNo = rs.getInt("brand_no");
				String brandName = rs.getString("brand_name");
				String address = rs.getString("address");
				String ceoName = rs.getString("ceo_name");
				int workerCnt = rs.getInt("worker_count");

				HotelBrandInfo brandInfo = new HotelBrandInfo(brandName, address, ceoName, workerCnt);
				brandInfo.setBrandNo(brandNo);
				
				resList.add(brandInfo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resList;
	}
	
	public int getNextBrandID() {
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select hotel_brand_no from number_info where id=6";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("hotel_brand_no");
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
