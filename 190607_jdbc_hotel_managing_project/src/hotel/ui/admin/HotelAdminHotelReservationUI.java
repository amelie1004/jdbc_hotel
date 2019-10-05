package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.HotelReservationInfo;

public class HotelAdminHotelReservationUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelReservationUI() {
		
	}
	
	public void startHotelReservationMenu() {
		HotelUICommon.menuListStart("���� ����Ʈ ����");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelReservationMenu();
			selectHotelReservationMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelReservationMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ���� ����Ʈ ����                   ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("  10. ���� ��� Ȯ��(���� ��ȣ)");
		System.out.println("  11. ���� ��� Ȯ��(ȸ�� ��ȣ)");
		System.out.println("  12. ���� ��� Ȯ��(�� ��ȣ)");
		System.out.println("  30. ���� ��� ����");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelReservationMenu() {
		switch (choice) {
		case 10:
			// ���� ��� Ȯ��(���� ��ȣ)
			searchReserveListByID();
			break;
		case 11:
			// ���� ��� Ȯ��(ȸ�� ��ȣ)
			searchReserveListByCustomer();
			break;
		case 12:
			// ���� ��� Ȯ��(�� ��ȣ)
			searchReserveListByRoom();
			break;
		case 20:
			// ���� ��� Ȯ��(��ü)
			printAllReserveList();
			break;
		case 30:
			// ���� ��� ����
			deleteReserveList();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void searchReserveListByID() {
		int reservationID;
		HotelUICommon.menuStart("���� ��� Ȯ��(���� ��ȣ)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��       ���� ��� Ȯ��(���� ��ȣ)     ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ���� ��ȣ �Է� : ");
		reservationID = sc.nextInt();
		
		HotelReservationInfo resInfo = HotelAdminBasicUI.getHotelReservationDAO().getReservationInfoByID(reservationID);
		if (resInfo != null) {
			System.out.println();
			System.out.println("���� ��ȣ : " + resInfo.getReservationNo() + ", �� ��ȣ : " + resInfo.getCustomerNo()
						+ ", �� ��ȣ : " + resInfo.getRoomNo() + ", ���� ���� : " + resInfo.getStartDate() + ", ���� ���� : " + resInfo.getEndDate()
						+ ", ���� ���� : " + resInfo.getSignDate() + ", ������� ���� : " + resInfo.getSignCancelDate() + "\n"
						+ ", ���� �� : " + resInfo.getAdultCnt() + ", û�ҳ� �� : " + resInfo.getChildrenCnt() + ", �ֵ� ���� : " + resInfo.getIsHotdeal() + "\n"
						+ ", �� ��û ���� : " + resInfo.getRequestStr());
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchReserveListByCustomer() {
		int customerId;
		HotelUICommon.menuStart("���� ��� Ȯ��(ȸ�� ��ȣ)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��      ���� ��� Ȯ��(ȸ�� ��ȣ)     ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ȸ�� ��ȣ �Է� : ");
		customerId = sc.nextInt();
		
		List<HotelReservationInfo> resList = HotelAdminBasicUI.getHotelReservationDAO().getReservationInfoByCustomer(customerId);
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
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchReserveListByRoom() {
		int roomNo;
		HotelUICommon.menuStart("���� ��� Ȯ��(�� ��ȣ)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ���� ��� Ȯ��(�� ��ȣ)     ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   �� ��ȣ �Է� : ");
		roomNo = sc.nextInt();
		
		List<HotelReservationInfo> resList = HotelAdminBasicUI.getHotelReservationDAO().getReservationInfoByRoom(roomNo);
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
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllReserveList() {
		HotelUICommon.menuStart("���� ��� Ȯ��(��ü)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ���� ��� Ȯ��(��ü)       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		// ���⼭���� �ٽ� ����
		
		
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteReserveList() {
		int reservationID;
		HotelUICommon.menuStart("���� ��� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ���� ��� ����                      ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ���� ��ȣ �Է� : ");
		reservationID = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelReservationDAO().deleteReserveListByID(reservationID)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
