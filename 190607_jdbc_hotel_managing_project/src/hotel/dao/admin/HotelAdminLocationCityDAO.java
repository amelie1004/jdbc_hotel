package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.LocationCityInfo;

public class HotelAdminLocationCityDAO extends HotelAdminBasicDAO {
	private static int CityID = 0;

	public HotelAdminLocationCityDAO() {
		CityID = getNextCityNo();
	}

	public static int getCityID() {
		return CityID;
	}

	public static void setCityID(int cityID) {
		CityID = cityID;
	}
	
	public int insertLocationCity(int countryNo, String cityName, int code) {
		int rowCnt = 0;
		
		CityID = getNextCityNo();

		LocationCityInfo cityInfo = new LocationCityInfo(countryNo, cityName, code);
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into location_city_info values (loc_city_seq.nextval, ?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, cityInfo.getCountryNo());
			pstmt.setString(2, cityInfo.getCityName());
			pstmt.setInt(3, cityInfo.getCityCode());

			rowCnt = pstmt.executeUpdate();

			sql = "update number_info set location_city_no=? where id=3";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, CityID);
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
	
	public int deleteLocationCity(int code) {
		// TODO Auto-generated method stub
		int rowcnt = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete location_city_info where city_code=?";
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
	
	public int updateLocationCity(LocationCityInfo vo) {
		// TODO Auto-generated method stub
		int rowcnt = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "update location_city_info set country_no=?, city_name=?, city_code=? where city_no=?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, vo.getCountryNo());
			pstmt.setString(2, vo.getCityName());
			pstmt.setInt(3, vo.getCityCode());
			pstmt.setInt(4, vo.getCityNo());

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
	
	public List<LocationCityInfo> getAllData() {
		// TODO Auto-generated method stub
		List<LocationCityInfo> resDataList = new ArrayList<LocationCityInfo> ();
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			//String sql = "select * from location_city_info order by city_no";
			String sql = "select city.city_no as city_no, ctry.country_name as country_name, city.city_name as city_name, city.city_code as city_code " + 
					"from location_city_info city, location_country_info ctry " + 
					"where city.country_no = ctry.country_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int cityNo = rs.getInt("city_no");
				String countryName = rs.getString("country_name");
				String cityName = rs.getString("city_name");
				int code = rs.getInt("city_code");

				LocationCityInfo res = new LocationCityInfo(countryName, cityName, code);
				res.setCityNo(cityNo);
				
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
	
	public LocationCityInfo getLocationCity(int cityNo) {
		// TODO Auto-generated method stub
		LocationCityInfo res = null;
		Connection con = null;

		try {
			con = getConnection();

			//String sql = "select * from location_city_info where city_no=\'" + cityNo + "\'";
			String sql = "select city.city_no as city_no, ctry.country_name as country_name, city.city_name as city_name, city.city_code as city_code " + 
					"from location_city_info city, location_country_info ctry " + 
					"where city.country_no = ctry.country_no and city_no=" + cityNo;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int no = rs.getInt("city_no");
				String countryName = rs.getString("country_name");
				String cityName = rs.getString("city_name");
				int code = rs.getInt("city_code");

				res = new LocationCityInfo(countryName, cityName, code);
				res.setCityNo(no);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}

		return res;
	}

	public int getNextCityNo() {
		// TODO Auto-generated method stub
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select location_city_no from number_info where id=3";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("location_city_no");
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
