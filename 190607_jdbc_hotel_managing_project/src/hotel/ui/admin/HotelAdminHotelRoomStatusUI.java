package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.RoomStatusInfo;

public class HotelAdminHotelRoomStatusUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelRoomStatusUI() {
		
	}
	
	public void startHotelRoomStatusMenu() {
		HotelUICommon.menuListStart("ȣ�� �� ���¸�� ����");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelRoomStatusMenu();
			selectHotelRoomStatusMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelRoomStatusMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �� ���¸�� ����                ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. ȣ�� �� ���¸�� �߰�");
		System.out.println("   2. ȣ�� �� ���¸�� ����");
		System.out.println("  10. ȣ�� �� ���¸�� ����");
		System.out.println("  20. ȣ�� �� ���¸�� ��ü���");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelRoomStatusMenu() {
		switch (choice) {
		case 1:
			// ȣ�� �� ���¸�� �߰�
			insertHotelRoomStatusType();
			break;
		case 2:
			// ȣ�� �� ���¸�� ����
			modifyHotelRoomStatusType();
			break;
		case 10:
			// ȣ�� �� ���¸�� ����
			deleteHotelRoomStatusType();
			break;
		case 20:
			// ȣ�� �� ���¸�� ��ü���
			printAllHotelRoomStatusType();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelRoomStatusType() {
		int statusNo;
		String statusName;
		HotelUICommon.menuStart("ȣ�� �� ���¸�� �߰�");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �� ���¸�� �߰�                ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   1. ȣ�� �� ���� ��ȣ �Է� : ");
		statusNo = sc.nextInt();
		sc.nextLine();
		System.out.print("   2. ȣ�� �� ���� �� �Է� : ");
		statusName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelRoomStatusDAO().insertHotelRoomStatusInfo(statusNo, statusName)) {
			System.out.println("\n   ���������� �߰��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �߰��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelRoomStatusType() {
		int statusNo;
		String statusName;
		
		HotelUICommon.menuStart("ȣ�� �� ���¸�� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �� ���¸�� ����                ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ȣ�� �� ���� ��ȣ �Է� : ");
		statusNo = sc.nextInt();
		sc.nextLine();
		System.out.print("   �����ϰ��� �ϴ� ȣ�� �� ���� �� �Է� : ");
		statusName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelRoomStatusDAO().modifyHotelRoomStatusInfoByID(statusNo, statusName)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelRoomStatusType() {
		int statusNo;
		
		HotelUICommon.menuStart("ȣ�� �� ���¸�� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �� ���¸�� ����                ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ȣ�� �� ���� ��ȣ �Է� : ");
		statusNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomStatusDAO().deleteHotelRoomStatusInfoByID(statusNo)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelRoomStatusType() {
		HotelUICommon.menuStart("ȣ�� �� ���¸�� ��ü���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��       ȣ�� �� ���¸�� ��ü���             ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		
		List<RoomStatusInfo> typeList = HotelAdminBasicUI.getHotelRoomStatusDAO().getAllHotelRoomStatus();
		if (typeList.size() > 0) {
			for (int i=0; i<typeList.size(); i++) {
				System.out.println("��ȣ : " + typeList.get(i).getStatusNo()
							+ ", ȣ�� �� ���� �� : " + typeList.get(i).getStatusStr());
			}
			
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� ���� ���ų� ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
