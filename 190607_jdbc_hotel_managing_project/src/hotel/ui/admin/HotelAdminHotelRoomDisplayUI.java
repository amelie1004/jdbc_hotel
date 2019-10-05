package hotel.ui.admin;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.RoomDisplayInfo;

public class HotelAdminHotelRoomDisplayUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelRoomDisplayUI() {
		
	}
	
	public void startHotelRoomDisplayMenu() {
		HotelUICommon.menuListStart("���� ����Ʈ ����");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelRoomDisplayMenu();
			selectHotelRoomDisplayMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelRoomDisplayMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ���� ����Ʈ ����                   ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. �� �߰�");
		System.out.println("  10. ���� ��ϵ� �� ����");
		System.out.println("  20. ���� ��ϵ� �� ����");
		System.out.println("  30. ���� ��ϵ� �� ��ȸ(ID)");
		System.out.println("  31. ���� ��ϵ� �� ��ȸ(RoomID)");
		System.out.println("  40. ���� �ŷ����� �� ��ü ���");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelRoomDisplayMenu() {
		switch (choice) {
		case 1:
			// �� �߰�
			insertRoom();
			break;
		case 10:
			// ���� �ŷ����� �� ����
			modifyRoom();
			break;
		case 20:
			// ���� �ŷ����� �� ����
			deleteRoomByID();
			break;
		case 30:
			// ���� �ŷ����� �� ��ȸ(ID)
			searchRoomByID();
			break;
		case 31:
			// ���� �ŷ����� �� ��ȸ(RoomID)
			searchRoomByRoomID();
			break;
		case 40:
			// ���� �ŷ����� �� ��ü ���
			printAllRoomByDealingState();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertRoom() {
		int roomNo, pricePerDay, isDealed; 
		Date dealStartDate, dealEndDate;
		
		HotelUICommon.menuStart("�� �߰�");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��             �� �߰�                         ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   1. ��ϵ� �� ��ȣ �Է� : ");
		roomNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByID(roomNo) == null) {
			System.out.println("\n   �� ��ȣ �� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. �ŷ� ���� ���� �Է�(YYYY-MM-DD) : ");
		String dealStartStr = sc.next();
		dealStartDate = java.sql.Date.valueOf(dealStartStr);
		
		System.out.print("   3. �ŷ� ���� ���� �Է�(YYYY-MM-DD) : ");
		String dealEndStr = sc.next();
		dealEndDate = java.sql.Date.valueOf(dealEndStr);
		
		if (dealStartDate.after(dealEndDate)) {
			System.out.println("\n   ��¥�� �߸� �Է��ϼ̽��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 1�ϴ� �뿩 ���� : ");
		pricePerDay = sc.nextInt();
		
		System.out.print("   5. �ŷ� ���� ����(0/1) : ");
		isDealed = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().insertRoomDisplayInfo(roomNo, dealStartDate, dealEndDate, pricePerDay, isDealed)) {
			System.out.println("\n   ���������� �߰��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �߰��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyRoom() {
		int roomDisplayNo;
		int roomNo, pricePerDay, isDealed; 
		Date dealStartDate, dealEndDate, hotdealDate;
		
		HotelUICommon.menuStart("���� ��ϵ� �� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ���� ��ϵ� �� ����                 ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �����ϰ��� �ϴ� �ŷ���Ϲ�ȣ �Է� : ");
		roomDisplayNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().getRoomDisplayInfoByID(roomDisplayNo) != null) {
			System.out.println("\n   �ŷ���Ϲ�ȣ �� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   1. ��ϵ� �� ��ȣ �Է� : ");
		roomNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByID(roomNo) == null) {
			System.out.println("\n   �� ��ȣ �� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. �ŷ� ���� ���� �Է�(YYYY-MM-DD) : ");
		String dealStartStr = sc.next();
		dealStartDate = java.sql.Date.valueOf(dealStartStr);
		
		System.out.print("   3. �ŷ� ���� ���� �Է�(YYYY-MM-DD) : ");
		String dealEndStr = sc.next();
		dealEndDate = java.sql.Date.valueOf(dealEndStr);
		
		if (dealStartDate.after(dealEndDate)) {
			System.out.println("\n   ��¥�� �߸� �Է��ϼ̽��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 1�ϴ� �뿩 ���� : ");
		pricePerDay = sc.nextInt();
		
		System.out.print("   5. �ֵ� ���� ���� �Է�(0/YYYY-MM-DD) : ");
		String hotdealStr = sc.next();
		hotdealDate = java.sql.Date.valueOf(hotdealStr);
		
		if (hotdealDate.after(dealStartDate)) {
			System.out.println("\n   ��¥�� �߸� �Է��ϼ̽��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   6. �ŷ� ���� ����(0/1) : ");
		isDealed = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().updateRoomDisplayInfo(roomDisplayNo, roomNo, dealStartDate, dealEndDate, pricePerDay, hotdealDate, isDealed)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteRoomByID() {
		int roomDisplayNo;
		HotelUICommon.menuStart("���� ��ϵ� �� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ���� ��ϵ� �� ����                 ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �����ϰ��� �ϴ� �ŷ���Ϲ�ȣ �Է� : ");
		roomDisplayNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().deleteRoomDisplayInfo(roomDisplayNo)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchRoomByID() {
		int roomDisplayNo;
		HotelUICommon.menuStart("���� ��ϵ� �� ��ȸ(ID)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��       ���� ��ϵ� �� ��ȸ(ID)      ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �˻��ϰ��� �ϴ� �ŷ���Ϲ�ȣ �Է� : ");
		roomDisplayNo = sc.nextInt();
		
		RoomDisplayInfo resInfo = HotelAdminBasicUI.getHotelRoomDisplayDAO().getRoomDisplayInfoByID(roomDisplayNo);
		if (resInfo != null) {
			System.out.println();
			System.out.println("�ŷ���Ϲ�ȣ : " + resInfo.getDisplayNo()
					+ ", �� ��ȣ : " + resInfo.getRoomNo() + "\n"
					+ ", �ŷ��������� : " + resInfo.getStartDate().toString() + ", �ŷ��������� : " + resInfo.getEndDate().toString() + "\n"
					+ ", 1�ϴ� ���� : " + resInfo.getPricePerDay() + ", �ֵ� ���� ���� : " + resInfo.getHotdealStartDate());
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchRoomByRoomID() {
		int roomDisplayNo;
		HotelUICommon.menuStart("���� ��ϵ� �� ��ȸ(RoomID)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��      ���� ��ϵ� �� ��ȸ(RoomID)   ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �˻��ϰ��� �ϴ� �� ��ȣ �Է� : ");
		roomDisplayNo = sc.nextInt();
		
		List<RoomDisplayInfo> resList = HotelAdminBasicUI.getHotelRoomDisplayDAO().getRoomDisplayInfoByRoomID(roomDisplayNo);
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("�ŷ���Ϲ�ȣ : " + resList.get(i).getDisplayNo()
						+ ", �� ��ȣ : " + resList.get(i).getRoomNo() + "\n"
						+ ", �ŷ��������� : " + resList.get(i).getStartDate().toString() + ", �ŷ��������� : " + resList.get(i).getEndDate().toString() + "\n"
						+ ", 1�ϴ� ���� : " + resList.get(i).getPricePerDay() + ", �ֵ� ���� ���� : " + resList.get(i).getHotdealStartDate());
			}
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllRoomByDealingState() {
		HotelUICommon.menuStart("���� �ŷ����� �� ��ü ���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��      ���� �ŷ����� �� ��ü ���              ��");
		System.out.println("��������������������������������������������������������������\n");
		
		List<RoomDisplayInfo> resList = HotelAdminBasicUI.getHotelRoomDisplayDAO().getAllRoomDisplayInfoByDealed();
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("�ŷ���Ϲ�ȣ : " + resList.get(i).getDisplayNo()
						+ ", �� ��ȣ : " + resList.get(i).getRoomNo() + "\n"
						+ ", �ŷ��������� : " + resList.get(i).getStartDate().toString() + ", �ŷ��������� : " + resList.get(i).getEndDate().toString() + "\n"
						+ ", 1�ϴ� ���� : " + resList.get(i).getPricePerDay() + ", �ֵ� ���� ���� : " + resList.get(i).getHotdealStartDate());
			}
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
