package hotel.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HotelDBCommon {
	public static void Load_JDBC_Driver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver 로드 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection GetConnection(String db_url, String db_user_id, String db_user_pw) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DriverManager.getConnection(db_url, db_user_id, db_user_pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void Disconnect(Connection con) {
		// TODO Auto-generated method stub
		try {
			if (con != null)	con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}
