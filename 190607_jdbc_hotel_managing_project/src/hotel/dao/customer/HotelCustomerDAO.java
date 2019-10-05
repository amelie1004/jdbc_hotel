package hotel.dao.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hotel.vo.CustomerInfo;

public class HotelCustomerDAO extends HotelBasicDAO {
	private static boolean isLogin = false;
	private static String LoginID = null;

	public HotelCustomerDAO() {
		
	}

	public static boolean isLogin() {
		return isLogin;
	}

	public static void setLogin(boolean isLogin) {
		HotelCustomerDAO.isLogin = isLogin;
	}
	
	public static String getLoginID() {
		return LoginID;
	}

	public static void setLoginID(String loginID) {
		LoginID = loginID;
	}
	
	// sequence name : customer_seq
	public int insertCustomer(String loginId, String loginPasswd,
			String nickName, String firstName, String lastName, int age,
			String phoneNumber, String email) {
		
		CustomerInfo tmpInfo = getCustomerByID(loginId);
		if (tmpInfo != null) {
			return 0;
		}
		
		int rowCnt = 0;
		int customerID = getNextCustomerID();

		CustomerInfo customerInfo = new CustomerInfo(loginId, loginPasswd, 0,
				nickName, firstName, lastName, age, phoneNumber, email,
				null, 0, 0, 0);	
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into hotel_admin.customer_list(customer_no, login_id, login_passwd, is_passwd_expired, "
					+ "nick_name, first_name, last_name, age, phone_number, email, customer_register_date, "
					+ "is_login, is_blocked, login_failed_count) "
					+ "values(hotel_admin.customer_seq.nextval, ?, ?, 0,"
					+ "?, ?, ?, ?, ?, ?, sysdate,"
					+ "0, 0, 0)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, customerInfo.getLoginId());
			pstmt.setString(2, customerInfo.getLoginPasswd());
			pstmt.setString(3, customerInfo.getNickName());
			pstmt.setString(4, customerInfo.getFirstName());
			pstmt.setString(5, customerInfo.getLastName());
			pstmt.setInt(6, customerInfo.getAge());
			pstmt.setString(7, customerInfo.getPhoneNumber());
			pstmt.setString(8, customerInfo.getEmail());

			rowCnt = pstmt.executeUpdate();

			sql = "update hotel_admin.number_info set customer_no=? where id=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerID);
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
	
	public int deleteCustomerByID(String loginID) {
		int rowCnt = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete hotel_admin.customer_list where login_id=\'" + loginID + "\'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			rowCnt = pstmt.executeUpdate();

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
		
		if (rowCnt > 0) {
			isLogin = false;
			LoginID = null;
		}

		return rowCnt;
	}
	
	public int deleteCustomerByIDAndPasswd(String loginID, String passwd) {
		int rowCnt = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete hotel_admin.customer_list where login_id=\'" + loginID + "\'"
						+ " and login_passwd=\'" + passwd + "\'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			rowCnt = pstmt.executeUpdate();

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
		
		if (rowCnt > 0) {
			isLogin = false;
			LoginID = null;
		}

		return rowCnt;
	}
	
	public CustomerInfo getCustomerByID(String loginID) {
		CustomerInfo resInfo = null;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_admin.customer_list where login_id=\'" + loginID + "\'";
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
			// System.out.println("result : " + result);

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resInfo;
	}

	public int getNextCustomerID() {
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select customer_no from hotel_admin.number_info where id=1";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("customer_no");
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

	public boolean login(String loginId, String passwd) {
		int result = 0;
		boolean isExisted = false;
		boolean isBlocked = false;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from hotel_admin.customer_list "
					+ "where login_id=\'" + loginId + "\'" + " and login_passwd=\'" + passwd + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				isExisted = true;
				result = rs.getInt("is_login");
				if (rs.getInt("is_blocked") == 1) {
					isBlocked = true;
				} else {
					boolean isUpdateLastLoginDate = false;
					
					sql = "update hotel_admin.customer_list set last_login_date=sysdate where login_id=\'" + loginId+ "\'";
					stmt = con.createStatement();
					ResultSet lastDateRS = stmt.executeQuery(sql);
					if (lastDateRS.next()) {
						isUpdateLastLoginDate = true;
					}
					
					if (!isUpdateLastLoginDate) {
						isExisted = false;
					}
				}
			}
			// System.out.println("result : " + result);

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}

		// logout여부 확인 이지만.. 나중에 필요할 때 살리는 걸로 하자..
//		if (result > 0) {
//			return false;
//		}

		if (isExisted && !isBlocked) {
			isLogin = switchLoginState(loginId, true);
			if (isLogin) {
				LoginID = new String(loginId);
				return true;
			}
		}

		return false;
	}
	
	public boolean logout(String loginId) {
		boolean isLogout = switchLoginState(loginId, false);
		if (isLogout) {
			isLogin = false;
			LoginID = null;
		}
		
		return isLogout;
	}

	public boolean switchLoginState(String loginId, boolean isOn) {
		int result = 0;
		boolean isOK = false;

		Connection con = null;

		try {
			con = getConnection();

			String sql;
			if (isOn)
				sql = "update hotel_admin.customer_list set is_login=1 where login_id=\'" + loginId + "\'";
			else
				sql = "update hotel_admin.customer_list set is_login=0 where login_id=\'" + loginId + "\'";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				isOK = true;
			}
			
			if (isOK) {
				sql = "select is_login from hotel_admin.customer_list where login_id=\'" + loginId + "\'";
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					result = rs.getInt("is_login");
				}
			}
			
			// System.out.println("result : " + result);

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		if (isOn && isOK && result == 1) {
			return true;
		}

		if (!isOn && isOK && result == 0) {
			return true;
		}

		return false;
	}

	public boolean isLoginState(String loginId) {
		int result = 0;
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select is_login from hotel_admin.customer_list where login_id=" + loginId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("is_login");
			}
			// System.out.println("result : " + result);

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}

		if (result > 0) {
			return true;
		}

		return false;
	}
}
