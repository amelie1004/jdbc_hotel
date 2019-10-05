package hotel.dao.customer;

import java.sql.Connection;

import hotel.common.*;

public class HotelBasicDAO {
	private static String DB_USER_ID = "hotel_customer"; 
	private static String DB_USER_PW = "oracle";
	private static String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public HotelBasicDAO() {
		
	}
	
	public Connection getConnection() {
		return HotelDBCommon.GetConnection(DB_URL, DB_USER_ID, DB_USER_PW);
	}
	
	public void disconnect(Connection con) {
		HotelDBCommon.Disconnect(con);
	}
}
