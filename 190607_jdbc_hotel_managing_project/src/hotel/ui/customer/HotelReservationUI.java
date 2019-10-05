package hotel.ui.customer;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.dao.customer.HotelCustomerDAO;
import hotel.vo.CustomerInfo;
import hotel.vo.HotelReservationInfo;
import hotel.vo.RoomDisplayInfo;

public class HotelReservationUI {
	private Scanner sc = new Scanner(System.in);
	
	public HotelReservationUI() {
		
	}
	
	public void printAllReservedInfoByCustomerID() {
		HotelUICommon.menuStart("���� ������ �� ���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ���� ������ �� ���                 ��");
		System.out.println("��������������������������������������������������������������\n");
		
		CustomerInfo customerInfo = HotelBasicUI.getCustomerDAO().getCustomerByID(HotelCustomerDAO.getLoginID());
		int customerNo = customerInfo.getCustomerNo();
		List<HotelReservationInfo> resList = HotelBasicUI.getReservationDAO().getAllReservedInfoByCustomerNo(customerNo);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("���� ��ȣ : " + resList.get(i).getReservationNo() + ", �� ��ȣ : " + resList.get(i).getCustomerNo()
							+ ", �� ��ȣ : " + resList.get(i).getRoomNo() + ", ���� ���� : " + resList.get(i).getStartDate() + ", ���� ���� : " + resList.get(i).getEndDate()
							+ ", ���� ���� : " + resList.get(i).getSignDate() + ", ������� ���� : " + resList.get(i).getSignCancelDate() + "\n"
							+ ", ���� �� : " + resList.get(i).getAdultCnt() + ", û�ҳ� �� : " + resList.get(i).getChildrenCnt() + ", �ֵ� ���� : " + resList.get(i).getIsHotdeal() + "\n"
							+ ", �� ��û ���� : " + resList.get(i).getRequestStr());
			}
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		HotelUICommon.menuEnd(sc);
	}
	
	public void insertReservationInfoByRoomID() {
		int roomNo, adultCnt, childrenCnt;
		Date startDate, endDate, signDate;
		String requestStr;
		
		HotelUICommon.menuStart("�� �����ϱ�");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            �� �����ϱ�                     ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		CustomerInfo customerInfo = HotelBasicUI.getCustomerDAO().getCustomerByID(HotelCustomerDAO.getLoginID());
		int customerNo = customerInfo.getCustomerNo();
		
		System.out.print("   1. �����ϰ��� �ϴ� �ŷ���Ϲ�ȣ �Է� : ");
		int roomDisplayNo = sc.nextInt();
		RoomDisplayInfo displayInfo = HotelBasicUI.getRoomDisplayDAO().getRoomDisplayInfoByID(roomDisplayNo);
		if (displayInfo == null) {
			System.out.println("\n   �ŷ���Ϲ�ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		} else {
			roomNo = displayInfo.getRoomNo();
		}
		
		System.out.print("   2. ���ڽ������� �Է�(YYYY-MM-DD) : ");
		String startDateStr = sc.next();
		startDate = java.sql.Date.valueOf(startDateStr);
		
		System.out.print("   3. ������������ �Է�(YYYY-MM-DD) : ");
		String endDateStr = sc.next();
		endDate = java.sql.Date.valueOf(endDateStr);
		
		if (startDate.after(endDate)) {
			System.out.println("\n   �������ڰ� �߸� �ԷµǾ����ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. ���� �ο� �� �Է� : ");
		adultCnt = sc.nextInt();
		System.out.print("   5. û�ҳ� �ο� �� �Է� : ");
		childrenCnt = sc.nextInt();
		
		sc.nextLine();
		System.out.print("   6. �߰� �䱸���� �Է� : ");
		requestStr = sc.nextLine();
		
		if (HotelBasicUI.getReservationDAO().insertReservationInfoByRoomID(customerNo, roomNo, startDate, endDate, adultCnt, childrenCnt, requestStr)) {
			System.out.println("\n   ���������� ����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ���࿡ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void cancelReservationInfoByRoomID() {
		int reservationNo;
		
		HotelUICommon.menuStart("�� ���� ����ϱ�");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          �� ���� ����ϱ�                   ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ����ϰ��� �ϴ� �����ȣ �Է� : ");
		reservationNo = sc.nextInt();
		
		CustomerInfo customerInfo = HotelBasicUI.getCustomerDAO().getCustomerByID(HotelCustomerDAO.getLoginID());
		int customerNo = customerInfo.getCustomerNo();
		
		if (HotelBasicUI.getReservationDAO().cancelSignReservationInfo(customerNo, reservationNo)) {
			System.out.println("\n   ���������� ��ҵǾ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ҿ� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		
		HotelUICommon.menuEnd(sc);
	}
}
