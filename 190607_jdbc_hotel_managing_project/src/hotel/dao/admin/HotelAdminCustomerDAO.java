package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hotel.vo.CustomerInfo;

public class HotelAdminCustomerDAO extends HotelAdminBasicDAO {
	
	public HotelAdminCustomerDAO() {
		
	}
	
	public CustomerInfo getCustomerByID(String loginID) {
		CustomerInfo resInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from customer_list where login_id=\'" + loginID + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				resInfo = new CustomerInfo();
				resInfo.setCustomerNo(rs.getInt("customer_no"));
				resInfo.setLoginId(rs.getString("login_id"));
				resInfo.setLoginPasswd(rs.getString("login_passwd"));
				resInfo.setIsPasswdExpired(rs.getInt("is_passwd_expired"));
				resInfo.setNickName(rs.getString("nick_name"));
				resInfo.setFirstName(rs.getString("first_name"));
				resInfo.setLastName(rs.getString("last_name"));
				resInfo.setAge(rs.getInt("age"));
				resInfo.setGender(rs.getString("gender"));
				resInfo.setAddress(rs.getString("address"));
				resInfo.setPhoneNumber(rs.getString("phone_number"));
				resInfo.setEmail(rs.getString("email"));
				resInfo.setResidenceLocationNo(rs.getInt("residence_location_no"));
				resInfo.setZipCode(rs.getString("zip_code"));
				resInfo.setClassNo(rs.getInt("class_no"));
				resInfo.setRecommendCustomerNo(rs.getInt("recommend_customer_no"));
				resInfo.setRewardCount(rs.getInt("reward_cnt"));
				resInfo.setLastLoginDate(rs.getDate("last_login_date"));
				resInfo.setBookmarkCityNo(rs.getInt("bookmark_location_city_no"));
				resInfo.setIsLogin(rs.getInt("is_login"));
				resInfo.setIsBlocked(rs.getInt("is_blocked"));
				resInfo.setLoginFailedCount(rs.getInt("login_failed_count"));
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}
	
	public CustomerInfo getCustomerByNickName(String nickName) {
		CustomerInfo resInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from customer_list where nick_name=\'" + nickName + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				resInfo = new CustomerInfo();
				resInfo.setCustomerNo(rs.getInt("customer_no"));
				resInfo.setLoginId(rs.getString("login_id"));
				resInfo.setLoginPasswd(rs.getString("login_passwd"));
				resInfo.setIsPasswdExpired(rs.getInt("is_passwd_expired"));
				resInfo.setNickName(rs.getString("nick_name"));
				resInfo.setFirstName(rs.getString("first_name"));
				resInfo.setLastName(rs.getString("last_name"));
				resInfo.setAge(rs.getInt("age"));
				resInfo.setGender(rs.getString("gender"));
				resInfo.setAddress(rs.getString("address"));
				resInfo.setPhoneNumber(rs.getString("phone_number"));
				resInfo.setEmail(rs.getString("email"));
				resInfo.setResidenceLocationNo(rs.getInt("residence_location_no"));
				resInfo.setZipCode(rs.getString("zip_code"));
				resInfo.setClassNo(rs.getInt("class_no"));
				resInfo.setRecommendCustomerNo(rs.getInt("recommend_customer_no"));
				resInfo.setRewardCount(rs.getInt("reward_cnt"));
				resInfo.setLastLoginDate(rs.getDate("last_login_date"));
				resInfo.setBookmarkCityNo(rs.getInt("bookmark_location_city_no"));
				resInfo.setIsLogin(rs.getInt("is_login"));
				resInfo.setIsBlocked(rs.getInt("is_blocked"));
				resInfo.setLoginFailedCount(rs.getInt("login_failed_count"));
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}
	
	public CustomerInfo getCustomerByPhoneNum(String phoneNum) {
		CustomerInfo resInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from customer_list where phone_number=\'" + phoneNum + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				resInfo = new CustomerInfo();
				resInfo.setCustomerNo(rs.getInt("customer_no"));
				resInfo.setLoginId(rs.getString("login_id"));
				resInfo.setLoginPasswd(rs.getString("login_passwd"));
				resInfo.setIsPasswdExpired(rs.getInt("is_passwd_expired"));
				resInfo.setNickName(rs.getString("nick_name"));
				resInfo.setFirstName(rs.getString("first_name"));
				resInfo.setLastName(rs.getString("last_name"));
				resInfo.setAge(rs.getInt("age"));
				resInfo.setGender(rs.getString("gender"));
				resInfo.setAddress(rs.getString("address"));
				resInfo.setPhoneNumber(rs.getString("phone_number"));
				resInfo.setEmail(rs.getString("email"));
				resInfo.setResidenceLocationNo(rs.getInt("residence_location_no"));
				resInfo.setZipCode(rs.getString("zip_code"));
				resInfo.setClassNo(rs.getInt("class_no"));
				resInfo.setRecommendCustomerNo(rs.getInt("recommend_customer_no"));
				resInfo.setRewardCount(rs.getInt("reward_cnt"));
				resInfo.setLastLoginDate(rs.getDate("last_login_date"));
				resInfo.setBookmarkCityNo(rs.getInt("bookmark_location_city_no"));
				resInfo.setIsLogin(rs.getInt("is_login"));
				resInfo.setIsBlocked(rs.getInt("is_blocked"));
				resInfo.setLoginFailedCount(rs.getInt("login_failed_count"));
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}
	
	public CustomerInfo getCustomerByEmail(String email) {
		CustomerInfo resInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from customer_list where email=\'" + email + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				resInfo = new CustomerInfo();
				resInfo.setCustomerNo(rs.getInt("customer_no"));
				resInfo.setLoginId(rs.getString("login_id"));
				resInfo.setLoginPasswd(rs.getString("login_passwd"));
				resInfo.setIsPasswdExpired(rs.getInt("is_passwd_expired"));
				resInfo.setNickName(rs.getString("nick_name"));
				resInfo.setFirstName(rs.getString("first_name"));
				resInfo.setLastName(rs.getString("last_name"));
				resInfo.setAge(rs.getInt("age"));
				resInfo.setGender(rs.getString("gender"));
				resInfo.setAddress(rs.getString("address"));
				resInfo.setPhoneNumber(rs.getString("phone_number"));
				resInfo.setEmail(rs.getString("email"));
				resInfo.setResidenceLocationNo(rs.getInt("residence_location_no"));
				resInfo.setZipCode(rs.getString("zip_code"));
				resInfo.setClassNo(rs.getInt("class_no"));
				resInfo.setRecommendCustomerNo(rs.getInt("recommend_customer_no"));
				resInfo.setRewardCount(rs.getInt("reward_cnt"));
				resInfo.setLastLoginDate(rs.getDate("last_login_date"));
				resInfo.setBookmarkCityNo(rs.getInt("bookmark_location_city_no"));
				resInfo.setIsLogin(rs.getInt("is_login"));
				resInfo.setIsBlocked(rs.getInt("is_blocked"));
				resInfo.setLoginFailedCount(rs.getInt("login_failed_count"));
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}
	
	public boolean modifyCustomerById(String loginId, String loginPasswd,
			String nickName, String firstName, String lastName, int age,
			String phoneNumber, String email) {
		boolean isModified = false;
		
		if (getCustomerByID(loginId) == null) {
			return false;
		}
		
		int rowCnt = 0;
		Connection con = null;

		try {
			con = getConnection();
			
			String sql = "update customer_list set login_passwd=?, nick_name=?, "
					+ "first_name=?, last_name=?, age=?, phone_number=?, email=? "
					+ "where login_id=?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, loginPasswd);
			pstmt.setString(2, nickName);
			pstmt.setString(3, firstName);
			pstmt.setString(4, lastName);
			pstmt.setInt(5, age);
			pstmt.setString(6, phoneNumber);
			pstmt.setString(7, email);
			pstmt.setString(8, loginId);
			
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
	
	public boolean blockCustomerByLoginID(String loginID, boolean isBlock) {
		boolean isBlocked = false;
		
		if (getCustomerByID(loginID) == null) {
			return false;
		}
		
		Connection con = null;

		try {
			con = getConnection();
			
			String sql;
			if (isBlock) {
				sql = "update customer_list set is_blocked=1 where login_id=\'" + loginID + "\'";
			} else {
				sql = "update customer_list set is_blocked=0 where login_id=\'" + loginID + "\'";
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				isBlocked = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return isBlocked;
	}
	
	public boolean deleteCustomerByLoginID(String loginID) {
		boolean isDeleted = false;
		
		int rowCnt = 0;
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "delete customer_list where login_id=\'" + loginID + "\'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

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
	
	public int getNextCustomerID() {
		// TODO Auto-generated method stub
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select customer_no from number_info where id=1";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("customer_no");
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}

		return result + 1;
	}
}
