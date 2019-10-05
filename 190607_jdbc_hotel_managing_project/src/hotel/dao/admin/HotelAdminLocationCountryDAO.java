package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.LocationCountryInfo;

public class HotelAdminLocationCountryDAO extends HotelAdminBasicDAO {
	private static int CountryID = 0;

	public HotelAdminLocationCountryDAO() {
		CountryID = getNextCountryNo();
	}

	public static int getCountryID() {
		return CountryID;
	}

	public static void setCountryID(int countryID) {
		CountryID = countryID;
	}

	public int insertLocationCountry(String countryName, int code) {
		int rowCnt = 0;
		
		CountryID = getNextCountryNo();

		LocationCountryInfo cityInfo = new LocationCountryInfo(countryName, code);
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into location_country_info values (loc_country_seq.nextval, ?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, cityInfo.getCountryName());
			pstmt.setInt(2, cityInfo.getCountryCode());

			rowCnt = pstmt.executeUpdate();

			sql = "update number_info set location_country_no=? where id=2";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, CountryID);
			pstmt.executeUpdate();

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

		return rowCnt;
	}

	public int deleteLocationCountry(int code) {
		// TODO Auto-generated method stub
		int rowcnt = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete location_country_info where country_code=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, code);

			rowcnt = pstmt.executeUpdate();

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

		return rowcnt;
	}

	public int updateLocationCountry(LocationCountryInfo vo) {
		// TODO Auto-generated method stub
		int rowcnt = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "update location_country_info set country_name=?, country_code=? where country_no=?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, vo.getCountryName());
			pstmt.setInt(2, vo.getCountryCode());
			pstmt.setInt(3, vo.getCountryNo());

			rowcnt = pstmt.executeUpdate();

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

		return rowcnt;
	}
	
	public List<LocationCountryInfo> getAllData() {
		// TODO Auto-generated method stub
		List<LocationCountryInfo> resDataList = new ArrayList<LocationCountryInfo> ();
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			String sql = "select * from location_country_info order by country_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {				
				int no = rs.getInt("country_no");
				String name = rs.getString("country_name");
				int code = rs.getInt("country_code");

				LocationCountryInfo res = new LocationCountryInfo(name, code);
				res.setCountryNo(no);
				
				resDataList.add(res);
			}
			
		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resDataList;
	}

	public LocationCountryInfo getLocationCountry(int countryNo) {
		// TODO Auto-generated method stub
		LocationCountryInfo res = null;
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from location_country_info where country_no=\'" + countryNo + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int no = rs.getInt("country_no");
				String name = rs.getString("country_name");
				int code = rs.getInt("country_code");

				res = new LocationCountryInfo(name, code);
				res.setCountryNo(no);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}

		return res;
	}

	public int getNextCountryNo() {
		// TODO Auto-generated method stub
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select location_country_no from number_info where id=2";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("location_country_no");
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
