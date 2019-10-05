package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.HotelManagerInfo;

public class HotelAdminHotelManagerDAO extends HotelAdminBasicDAO {
	private static int HotelManagerID = 0;
	
	public HotelAdminHotelManagerDAO() {
		
	}

	// sequence name : hotel_manager_seq
	public static int getHotelManagerID() {
		return HotelManagerID;
	}

	public static void setHotelManagerID(int hotelManagerID) {
		HotelManagerID = hotelManagerID;
	}
	
	public boolean insertHotelManagerInfo(int hotelNo, String loginID, String loginPasswd,
			String firstName, String lastName, String telephone, String email, int bossManagerNo) {
		boolean isInserted = false;
		int rowCnt = 0, argCnt = 1;
		
		HotelManagerID = getNextHotelManagerID();
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into hotel_manager_list values(hotel_manager_seq.nextval,?,?,?,?,?,?,?"
					+ ( (bossManagerNo > 0) ? ",?" : ", null" ) + ")";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(argCnt++, hotelNo);
			pstmt.setString(argCnt++, loginID);
			pstmt.setString(argCnt++, loginPasswd);
			pstmt.setString(argCnt++, firstName);
			pstmt.setString(argCnt++, lastName);
			pstmt.setString(argCnt++, telephone);
			pstmt.setString(argCnt++, email);
			if (bossManagerNo > 0) {
				pstmt.setInt(argCnt++, bossManagerNo);
			}

			rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
				sql = "update number_info set hotel_manager_no=? where id=11";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, HotelManagerID);
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
	
	public boolean updateHotelManagerInfo(int managerNo, int hotelNo, String loginID, String loginPasswd,
			String firstName, String lastName, String telephone, String email, int bossManagerNo) {
		boolean isModified = false;
		int rowCnt = 0, argCnt = 1;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "update hotel_manager_list "
					+ "set hotel_no=?, login_id=?, login_passwd=?, first_name=?, "
					+ "last_name=?, telephone=?, email=?"
					+ ((bossManagerNo > 0) ? ", boss_manager_no=? " : " ") 
					+ "where manager_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(argCnt++, hotelNo);
			pstmt.setString(argCnt++, loginID);
			pstmt.setString(argCnt++, loginPasswd);
			pstmt.setString(argCnt++, firstName);
			pstmt.setString(argCnt++, lastName);
			pstmt.setString(argCnt++, telephone);
			pstmt.setString(argCnt++, email);
			if (bossManagerNo > 0) {
				pstmt.setInt(argCnt++, bossManagerNo);
			}
			pstmt.setInt(argCnt++, managerNo);
			
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
	
	public boolean deleteHotelManagerByID(int targetManagerID) {
		boolean isDeleted = false;
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete hotel_manager_list where manager_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, targetManagerID);
			
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
	
	public HotelManagerInfo getHotelManagerByID(int targetManagerID) {
		HotelManagerInfo managerInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_manager_list where manager_no=\'" + targetManagerID + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				int hotelNo = rs.getInt("hotel_no");
				String managerId = rs.getString("login_id");
				String managerPasswd = rs.getString("login_passwd");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String telephoneNum = rs.getString("telephone");
				String email = rs.getString("email");
				int bossManagerNo = rs.getInt("boss_manager_no");
				
				managerInfo = new HotelManagerInfo(hotelNo, managerId, managerPasswd, firstName, lastName, telephoneNum, email, bossManagerNo);
				managerInfo.setManagerNo(targetManagerID);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return managerInfo;
	}
	
	public HotelManagerInfo getHotelManagerByLoginID(String targetLoginID) {
		HotelManagerInfo managerInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_manager_list where login_id=\'" + targetLoginID + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				int managerNo = rs.getInt("manager_no");
				int hotelNo = rs.getInt("hotel_no");
				String managerId = rs.getString("login_id");
				String managerPasswd = rs.getString("login_passwd");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String telephoneNum = rs.getString("telephone");
				String email = rs.getString("email");
				int bossManagerNo = rs.getInt("boss_manager_no");
				
				managerInfo = new HotelManagerInfo(hotelNo, managerId, managerPasswd, firstName, lastName, telephoneNum, email, bossManagerNo);
				managerInfo.setManagerNo(managerNo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return managerInfo;
	}
	
	public List<HotelManagerInfo> getHotelManagerByName(String targetName) {
		List<HotelManagerInfo> resList = new ArrayList<HotelManagerInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_manager_list "
					+ "where first_name like \'%" + targetName + "%\' or last_name like \'%" + targetName + "%\' order by manager_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int managerNo = rs.getInt("manager_no");
				int hotelNo = rs.getInt("hotel_no");
				String managerId = rs.getString("login_id");
				String managerPasswd = rs.getString("login_passwd");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String telephoneNum = rs.getString("telephone");
				String email = rs.getString("email");
				int bossManagerNo = rs.getInt("boss_manager_no");
				
				HotelManagerInfo managerInfo = new HotelManagerInfo(hotelNo, managerId, managerPasswd, firstName, lastName, telephoneNum, email, bossManagerNo);
				managerInfo.setManagerNo(managerNo);
				
				resList.add(managerInfo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resList;
	}
	
	public HotelManagerInfo getHotelManagerByTelephone(String targetTelephone) {
		HotelManagerInfo managerInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_manager_list where telephone=\'" + targetTelephone + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				int managerNo = rs.getInt("manager_no");
				int hotelNo = rs.getInt("hotel_no");
				String managerId = rs.getString("login_id");
				String managerPasswd = rs.getString("login_passwd");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String telephoneNum = rs.getString("telephone");
				String email = rs.getString("email");
				int bossManagerNo = rs.getInt("boss_manager_no");
				
				managerInfo = new HotelManagerInfo(hotelNo, managerId, managerPasswd, firstName, lastName, telephoneNum, email, bossManagerNo);
				managerInfo.setManagerNo(managerNo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return managerInfo;
	}
	
	public HotelManagerInfo getHotelManagerByEmail(String targetEmail) {
		HotelManagerInfo managerInfo = null;
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_manager_list where email=\'" + targetEmail + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				int managerNo = rs.getInt("manager_no");
				int hotelNo = rs.getInt("hotel_no");
				String managerId = rs.getString("login_id");
				String managerPasswd = rs.getString("login_passwd");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String telephoneNum = rs.getString("telephone");
				String email = rs.getString("email");
				int bossManagerNo = rs.getInt("boss_manager_no");
				
				managerInfo = new HotelManagerInfo(hotelNo, managerId, managerPasswd, firstName, lastName, telephoneNum, email, bossManagerNo);
				managerInfo.setManagerNo(managerNo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return managerInfo;
	}
	
	public List<HotelManagerInfo> getAllHotelManager() {
		List<HotelManagerInfo> resList = new ArrayList<HotelManagerInfo>();
		
		Connection con = null;
		
		try {
			con = getConnection();

			String sql = "select * from hotel_manager_list order by manager_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int managerNo = rs.getInt("manager_no");
				int hotelNo = rs.getInt("hotel_no");
				String managerId = rs.getString("login_id");
				String managerPasswd = rs.getString("login_passwd");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String telephoneNum = rs.getString("telephone");
				String email = rs.getString("email");
				int bossManagerNo = rs.getInt("boss_manager_no");
				
				HotelManagerInfo managerInfo = new HotelManagerInfo(hotelNo, managerId, managerPasswd, firstName, lastName, telephoneNum, email, bossManagerNo);
				managerInfo.setManagerNo(managerNo);
				
				resList.add(managerInfo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}
		
		return resList;
	}
	
	public int getNextHotelManagerID() {
		int result = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "select hotel_manager_no from number_info where id=11";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("hotel_manager_no");
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
