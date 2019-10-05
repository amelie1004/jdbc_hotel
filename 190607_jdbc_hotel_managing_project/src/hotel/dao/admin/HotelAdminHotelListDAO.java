package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.HotelInfo;

public class HotelAdminHotelListDAO extends HotelAdminBasicDAO {
	private static int HotelListID = 0;

	public HotelAdminHotelListDAO() {

	}

	// sequence name : hotel_list_seq
	public static int getHotelListID() {
		return HotelListID;
	}

	public static void setHotelListID(int hotelListID) {
		HotelListID = hotelListID;
	}

	public boolean insertHotelListInfo(String hotelName, int hotelBrandNo,
			int hotelTypeNo, int hotelLocNo, int hotelStar, int hotelRoomCnt) {
		boolean isInserted = false;
		int rowCnt = 0;

		HotelListID = getNextHotelListID();

		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into hotel_list "
					+ "(hotel_no, hotel_name, hotel_brand_no, hotel_type_no, hotel_location_city_no, hotel_star, hotel_room_count)"
					+ "values(hotel_list_seq.nextval, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, hotelName);
			pstmt.setInt(2, hotelBrandNo);
			pstmt.setInt(3, hotelTypeNo);
			pstmt.setInt(4, hotelLocNo);
			pstmt.setInt(5, hotelStar);
			pstmt.setInt(6, hotelRoomCnt);

			rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
				sql = "update number_info set hotel_list_no=? where id=10";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, HotelListID);
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
	
	public boolean updateHotelListBasicInfo(int hotelListNo, String hotelName, int hotelBrandNo,
			int hotelTypeNo, int hotelLocNo, int hotelStar, int hotelRoomCnt) {
		boolean isModified = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "update hotel_list "
					+ "set hotel_name=?, hotel_brand_no=?, hotel_type_no=?, hotel_location_city_no=? "
					+ "hotel_star=?, hotel_room_count=? " 
					+ "where hotel_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			// basic : hotel_name, hotel_brand_no, hotel_type_no, hotel_location_city_no, hotel_star, hotel_room_count
			pstmt.setString(1, hotelName);
			pstmt.setInt(2, hotelBrandNo);
			pstmt.setInt(3, hotelTypeNo);
			pstmt.setInt(4, hotelLocNo);
			pstmt.setInt(5, hotelStar);
			pstmt.setInt(6, hotelRoomCnt);
			
			pstmt.setInt(7, hotelListNo);
			
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
	
	public boolean updateHotelListEtcInfo(int hotelListNo, String hotelAddress, int hotelWorkerCnt, int bossHotelNo,
			int can_breakfast, int can_swimming, int can_wifi, int can_parking, int can_pet, int can_smoke) {
		boolean isModified = false;
		int rowCnt = 0, argCnt = 1;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "update hotel_list "
					+ "set hotel_address=?, hotel_worker_count=?, can_breakfast=?, "
					+ "can_swimming=?, can_wifi=?, can_parking=?, can_pet=?, can_smoke=?"
					+ ( (bossHotelNo > 0) ? ", boss_hotel_no=?" : " " )
					+ "where hotel_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			// etc : hotel_address (varchar2(200)), hotel_worker_count(number(5)), boss_hotel_no (number(6)),
			//		 can_breakfast (number(2)), can_swimming (number(2)), can_wifi(number(2)), can_parking(number(2)),
			//		 can_pet (number(2)), can_smoke(number(2))
			pstmt.setString(argCnt++, hotelAddress);
			pstmt.setInt(argCnt++, hotelWorkerCnt);
			if (bossHotelNo > 0) {
				pstmt.setInt(argCnt++, bossHotelNo);
			}
			pstmt.setInt(argCnt++, can_breakfast);
			pstmt.setInt(argCnt++, can_swimming);
			pstmt.setInt(argCnt++, can_wifi);
			pstmt.setInt(argCnt++, can_parking);
			pstmt.setInt(argCnt++, can_pet);
			pstmt.setInt(argCnt++, can_smoke);
			
			pstmt.setInt(argCnt++, hotelListNo);
			
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
	
	public HotelInfo getHotelListByID(int targetHotelID) {
		HotelInfo hotelInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_list where hotel_no=\'" + targetHotelID + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int hotelNo = rs.getInt("hotel_no");
				int hotelBrandNo = rs.getInt("hotel_brand_no");
				int hotelTypeNo = rs.getInt("hotel_type_no");
				String hotelName = rs.getString("hotel_name");
				int hotelLocNo = rs.getInt("hotel_location_city_no");
				String hotelAddr = rs.getString("hotel_address");
				int hotelStar = rs.getInt("hotel_star");
				int hotelRoomCnt = rs.getInt("hotel_room_count");
				int hotelWorkerCnt = rs.getInt("hotel_worker_count");
				int hotelCanBreakfast = rs.getInt("can_breakfast");
				int hotelCanSwimming = rs.getInt("can_swimming");
				int hotelCanWifi = rs.getInt("can_wifi");
				int hotelCanParking = rs.getInt("can_parking");
				int hotelCanPet = rs.getInt("can_pet");
				int hotelCanSmoke = rs.getInt("can_smoke");
				int bossHotelNo = rs.getInt("boss_hotel_no");
				

				hotelInfo = new HotelInfo(hotelBrandNo, hotelTypeNo,
						hotelName, hotelLocNo, hotelAddr, hotelStar,
						hotelRoomCnt, hotelWorkerCnt,
						hotelCanBreakfast, hotelCanSwimming, hotelCanWifi,
						hotelCanParking, hotelCanPet, hotelCanSmoke, bossHotelNo);
				hotelInfo.setHotelNo(hotelNo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return hotelInfo;
	}
	
	public HotelInfo getHotelListByName(String targetHotelName) {
		HotelInfo hotelInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_list where hotel_name=\'" + targetHotelName + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int hotelNo = rs.getInt("hotel_no");
				int hotelBrandNo = rs.getInt("hotel_brand_no");
				int hotelTypeNo = rs.getInt("hotel_type_no");
				String hotelName = rs.getString("hotel_name");
				int hotelLocNo = rs.getInt("hotel_location_city_no");
				String hotelAddr = rs.getString("hotel_address");
				int hotelStar = rs.getInt("hotel_star");
				int hotelRoomCnt = rs.getInt("hotel_room_count");
				int hotelWorkerCnt = rs.getInt("hotel_worker_count");
				int hotelCanBreakfast = rs.getInt("can_breakfast");
				int hotelCanSwimming = rs.getInt("can_swimming");
				int hotelCanWifi = rs.getInt("can_wifi");
				int hotelCanParking = rs.getInt("can_parking");
				int hotelCanPet = rs.getInt("can_pet");
				int hotelCanSmoke = rs.getInt("can_smoke");
				int bossHotelNo = rs.getInt("boss_hotel_no");
				

				hotelInfo = new HotelInfo(hotelBrandNo, hotelTypeNo,
						hotelName, hotelLocNo, hotelAddr, hotelStar,
						hotelRoomCnt, hotelWorkerCnt,
						hotelCanBreakfast, hotelCanSwimming, hotelCanWifi,
						hotelCanParking, hotelCanPet, hotelCanSmoke, bossHotelNo);
				hotelInfo.setHotelNo(hotelNo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return hotelInfo;
	}
	
	public List<HotelInfo> getHotelListByCountry(int countryID) {
		List<HotelInfo> resList = new ArrayList<HotelInfo>();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_list where hotel_location_city_no in "
					+ "(select city_no from location_city_info where country_no=" + countryID + ")";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int hotelNo = rs.getInt("hotel_no");
				int hotelBrandNo = rs.getInt("hotel_brand_no");
				int hotelTypeNo = rs.getInt("hotel_type_no");
				String hotelName = rs.getString("hotel_name");
				int hotelLocNo = rs.getInt("hotel_location_city_no");
				String hotelAddr = rs.getString("hotel_address");
				int hotelStar = rs.getInt("hotel_star");
				int hotelRoomCnt = rs.getInt("hotel_room_count");
				int hotelWorkerCnt = rs.getInt("hotel_worker_count");
				int hotelCanBreakfast = rs.getInt("can_breakfast");
				int hotelCanSwimming = rs.getInt("can_swimming");
				int hotelCanWifi = rs.getInt("can_wifi");
				int hotelCanParking = rs.getInt("can_parking");
				int hotelCanPet = rs.getInt("can_pet");
				int hotelCanSmoke = rs.getInt("can_smoke");
				int bossHotelNo = rs.getInt("boss_hotel_no");
				

				HotelInfo hotelInfo = new HotelInfo(hotelBrandNo, hotelTypeNo,
						hotelName, hotelLocNo, hotelAddr, hotelStar,
						hotelRoomCnt, hotelWorkerCnt,
						hotelCanBreakfast, hotelCanSwimming, hotelCanWifi,
						hotelCanParking, hotelCanPet, hotelCanSmoke, bossHotelNo);
				hotelInfo.setHotelNo(hotelNo);
				
				resList.add(hotelInfo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resList;
	}
	
	public List<HotelInfo> getHotelListByCity(int cityID) {
		List<HotelInfo> resList = new ArrayList<HotelInfo>();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_list where hotel_location_city_no=" + cityID;
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int hotelNo = rs.getInt("hotel_no");
				int hotelBrandNo = rs.getInt("hotel_brand_no");
				int hotelTypeNo = rs.getInt("hotel_type_no");
				String hotelName = rs.getString("hotel_name");
				int hotelLocNo = rs.getInt("hotel_location_city_no");
				String hotelAddr = rs.getString("hotel_address");
				int hotelStar = rs.getInt("hotel_star");
				int hotelRoomCnt = rs.getInt("hotel_room_count");
				int hotelWorkerCnt = rs.getInt("hotel_worker_count");
				int hotelCanBreakfast = rs.getInt("can_breakfast");
				int hotelCanSwimming = rs.getInt("can_swimming");
				int hotelCanWifi = rs.getInt("can_wifi");
				int hotelCanParking = rs.getInt("can_parking");
				int hotelCanPet = rs.getInt("can_pet");
				int hotelCanSmoke = rs.getInt("can_smoke");
				int bossHotelNo = rs.getInt("boss_hotel_no");
				

				HotelInfo hotelInfo = new HotelInfo(hotelBrandNo, hotelTypeNo,
						hotelName, hotelLocNo, hotelAddr, hotelStar,
						hotelRoomCnt, hotelWorkerCnt,
						hotelCanBreakfast, hotelCanSwimming, hotelCanWifi,
						hotelCanParking, hotelCanPet, hotelCanSmoke, bossHotelNo);
				hotelInfo.setHotelNo(hotelNo);
				
				resList.add(hotelInfo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resList;
	}
	
	public List<HotelInfo> getAllHotelList() {
		List<HotelInfo> resList = new ArrayList<HotelInfo>();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_list";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int hotelNo = rs.getInt("hotel_no");
				int hotelBrandNo = rs.getInt("hotel_brand_no");
				int hotelTypeNo = rs.getInt("hotel_type_no");
				String hotelName = rs.getString("hotel_name");
				int hotelLocNo = rs.getInt("hotel_location_city_no");
				String hotelAddr = rs.getString("hotel_address");
				int hotelStar = rs.getInt("hotel_star");
				int hotelRoomCnt = rs.getInt("hotel_room_count");
				int hotelWorkerCnt = rs.getInt("hotel_worker_count");
				int hotelCanBreakfast = rs.getInt("can_breakfast");
				int hotelCanSwimming = rs.getInt("can_swimming");
				int hotelCanWifi = rs.getInt("can_wifi");
				int hotelCanParking = rs.getInt("can_parking");
				int hotelCanPet = rs.getInt("can_pet");
				int hotelCanSmoke = rs.getInt("can_smoke");
				int bossHotelNo = rs.getInt("boss_hotel_no");
				

				HotelInfo hotelInfo = new HotelInfo(hotelBrandNo, hotelTypeNo,
						hotelName, hotelLocNo, hotelAddr, hotelStar,
						hotelRoomCnt, hotelWorkerCnt,
						hotelCanBreakfast, hotelCanSwimming, hotelCanWifi,
						hotelCanParking, hotelCanPet, hotelCanSmoke, bossHotelNo);
				hotelInfo.setHotelNo(hotelNo);
				
				resList.add(hotelInfo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resList;
	}
	
	public boolean deleteHotelListByID(int hotelID) {
		boolean isDeleted = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete hotel_list where hotel_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, hotelID);
			
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

	public int getNextHotelListID() {
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select hotel_list_no from number_info where id=10";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("hotel_list_no");
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
