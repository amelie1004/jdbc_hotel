package hotel.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.vo.CustomerClassInfo;

public class HotelAdminCustomerClassDAO extends HotelAdminBasicDAO {
	private static int ClassID = 0;

	public HotelAdminCustomerClassDAO() {
		
	}
	
	public int insertClass(int classNo, String className, int discountRate) {
		int rowCnt = 0;
		
		Connection con = null;

		try {
			con = getConnection();

			String sql = "insert into customer_class_info values (?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, classNo);
			pstmt.setString(2, className);
			pstmt.setInt(3, discountRate);

			rowCnt = pstmt.executeUpdate();

			sql = "update number_info set class_no=? where id=5";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ClassID);
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
	
	public int deleteCustomerClass(int classNo) {
		// TODO Auto-generated method stub
		int rowcnt = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "delete customer_class_info where class_no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setInt(1, classNo);

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
	
	public int updateCustomerClass(CustomerClassInfo vo) {
		// TODO Auto-generated method stub
		int rowcnt = 0;

		Connection con = null;

		try {
			con = getConnection();

			String sql = "update customer_class_info set class_name=?, class_discount_rate=? where class_no=?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);

			pstmt.setString(1, vo.getClassName());
			pstmt.setInt(2, vo.getDiscountRate());
			pstmt.setInt(3, vo.getClassNo());

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
	
	public List<CustomerClassInfo> getAllData() {
		// TODO Auto-generated method stub
		List<CustomerClassInfo> resDataList = new ArrayList<CustomerClassInfo> ();
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			String sql = "select * from customer_class_info order by class_no";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {				
				int classNo = rs.getInt("class_no");
				String className = rs.getString("class_name");
				int discountRate = rs.getInt("class_discount_rate");

				CustomerClassInfo res = new CustomerClassInfo(className, discountRate);
				res.setClassNo(classNo);
				
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

	public CustomerClassInfo getCustomerClass(int classNo) {
		// TODO Auto-generated method stub
		CustomerClassInfo res = null;
		Connection con = null;

		try {
			con = getConnection();

			String sql = "select * from customer_class_info where class_no=\'" + classNo + "\'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String className = rs.getString("class_name");
				int discountRate = rs.getInt("class_discount_rate");

				res = new CustomerClassInfo(className, discountRate);
				res.setClassNo(classNo);
			}

		} catch (SQLException e) {
			System.out.println("Data 읽기 실패..");
			e.printStackTrace();
		} finally {
			disconnect(con);
		}

		return res;
	}
}
