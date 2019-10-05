package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.HotelRoomInfo;

public class HotelAdminHotelRoomUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelRoomUI() {
		
	}
	
	public void startHotelRoomMenu() {
		HotelUICommon.menuListStart("ȣ�� �� ����");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelRoomMenu();
			selectHotelRoomMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelRoomMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            ȣ�� �� ����                    ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. ȣ�� �� �߰�");
		System.out.println("  10. ȣ�� �� ����");
		System.out.println("  20. ȣ�� �� ����");
		System.out.println("  40. ȣ�� �� �˻�(id)");
		System.out.println("  41. ȣ�� �� �˻�(���� ȣ��)");
		System.out.println("  42. ȣ�� �� �˻�(ȣ�ں�)");
		System.out.println("  43. ȣ�� �� �˻�(Ÿ�Ժ�)");
		System.out.println("  44. ȣ�� �� �˻�(���º�)");
		System.out.println("  50. ȣ�� �� ��ü ���");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelRoomMenu() {
		switch (choice) {
		case 1:
			// ȣ�� �� �߰�
			insertHotelRoomInfo();
			break;
		case 10:
			// ȣ�� �� ����
			updateHotelRoomInfo();
			break;
		case 20:
			// ȣ�� �� ����
			deleteHotelRoomInfo();
			break;
		case 40:
			// ȣ�� �� �˻�(id)
			searchHotelRoomInfoByID();
			break;
		case 41:
			// ȣ�� �� �˻�(���� ȣ��)
			searchHotelRoomInfoByRealPos();
			break;
		case 42:
			// ȣ�� �� �˻�(ȣ�ں�)
			searchHotelRoomInfoByHotelID();
			break;
		case 43:
			// ȣ�� �� �˻�(Ÿ�Ժ�)
			searchHotelRoomInfoByType();
			break;
		case 44:
			// ȣ�� �� �˻�(���º�)
			searchHotelRoomInfoByStatus();
			break;
		case 50:
			// ȣ�� �� ��ü ���
			printAllHotelRoom();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelRoomInfo() {
		int hotelNo, managerNo, typeNo, statusNo, roomCount, roomRealPos;
		String roomSize;
		
		HotelUICommon.menuStart("ȣ�� �� �߰�");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            ȣ�� �� �߰�                    ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   1. ȣ�� ��ȣ �Է� : ");
		hotelNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   ȣ�� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. ��� �Ŵ��� ��ȣ �Է� : ");
		managerNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByID(managerNo) == null) {
			System.out.println("\n   �Ŵ��� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   3. �� Ÿ�� ��ȣ �Է� : ");
		typeNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomTypeDAO().getHotelRoomTypeByID(typeNo) == null) {
			System.out.println("\n   Ÿ�� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. �� ���� ��ȣ �Է� : ");
		statusNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomStatusDAO().getHotelRoomStatusInfoByID(statusNo) == null) {
			System.out.println("\n   ���� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		sc.nextLine();
		System.out.print("   5. �� ũ�� �Է� : ");
		roomSize = sc.nextLine();
		
		System.out.print("   6. �� ���� �Է� : ");
		roomCount = sc.nextInt();
		System.out.print("   7. �� ���� ȣ�� �Է� : ");
		roomRealPos = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDAO().insertHotelRoomInfo(hotelNo, managerNo, typeNo, statusNo, roomSize, roomCount, roomRealPos)) {
			System.out.println("\n   ���������� �߰��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �߰��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void updateHotelRoomInfo() {
		int roomNo;
		int hotelNo, managerNo, typeNo, statusNo, roomCount, roomRealPos;
		String roomSize;
		
		HotelUICommon.menuStart("ȣ�� �� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            ȣ�� �� ����                    ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �����ϰ��� �ϴ� �� ��ȣ �Է� : ");
		roomNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByID(roomNo) == null) {
			System.out.println("\n   ȣ�� ���� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		} else {
			System.out.println("\n   �� ���� ������ �����մϴ�.\n");
		}
		
		System.out.print("   1. ȣ�� ��ȣ �Է� : ");
		hotelNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   ȣ�� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. ��� �Ŵ��� ��ȣ �Է� : ");
		managerNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByID(managerNo) == null) {
			System.out.println("\n   �Ŵ��� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   3. �� Ÿ�� ��ȣ �Է� : ");
		typeNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelTypeDAO().getHotelTypeByID(typeNo) == null) {
			System.out.println("\n   Ÿ�� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. �� ���� ��ȣ �Է� : ");
		statusNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomStatusDAO().getHotelRoomStatusInfoByID(statusNo) == null) {
			System.out.println("\n   ���� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		sc.nextLine();
		System.out.print("   5. �� ũ�� �Է� : ");
		roomSize = sc.nextLine();
		
		System.out.print("   6. �� ���� �Է� : ");
		roomCount = sc.nextInt();
		System.out.print("   7. �� ���� ȣ�� �Է� : ");
		roomRealPos = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDAO().updateHotelRoomInfo(roomNo, hotelNo, managerNo, typeNo, statusNo, roomSize, roomCount, roomRealPos)) {
			System.out.println("\n   ���������� �߰��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �߰��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelRoomInfo() {
		int roomNo;
		HotelUICommon.menuStart("ȣ�� �� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            ȣ�� �� ����                    ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �����ϰ��� �ϴ� �� ��ȣ �Է� : ");
		roomNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDAO().deleteHotelRoomInfoByID(roomNo)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelRoomInfoByID() {
		int roomNo;
		HotelUICommon.menuStart("ȣ�� �� �˻�(id)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� �� �˻�(id)        ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �˻��ϰ��� �ϴ� �� ��ȣ �Է� : ");
		roomNo = sc.nextInt();
		
		HotelRoomInfo resInfo = HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByID(roomNo);
		if (resInfo != null) {
			System.out.println();
			System.out.println("�� ��ȣ : " + resInfo.getRoomNo() + ", ȣ�� ��ȣ : " + resInfo.getHotelNo()
				+ ", ��� �Ŵ��� ��ȣ : " + resInfo.getManagerNo() + ", �� Ÿ�� ��ȣ : " + resInfo.getRoomTypeNo()
				+ ", �� ���� ��ȣ : " + resInfo.getStatusNo() + ", �� ũ�� : " + resInfo.getRoomSize()
				+ ", �� ���� : " + resInfo.getRoomCnt() + ", �� ���� ȣ�� : " + resInfo.getRoomRealNo());
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelRoomInfoByRealPos() {
		int posNum;
		HotelUICommon.menuStart("ȣ�� �� �˻�(���� ȣ��)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �� �˻�(���� ȣ��)     ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �˻��ϰ��� �ϴ� ���� ȣ�� ��ȣ �Է� : ");
		posNum = sc.nextInt();
		
		List<HotelRoomInfo> resList = HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByRealPos(posNum);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("�� ��ȣ : " + resList.get(i).getRoomNo() + ", ȣ�� ��ȣ : " + resList.get(i).getHotelNo()
					+ ", ��� �Ŵ��� ��ȣ : " + resList.get(i).getManagerNo() + ", �� Ÿ�� ��ȣ : " + resList.get(i).getRoomTypeNo()
					+ ", �� ���� ��ȣ : " + resList.get(i).getStatusNo() + ", �� ũ�� : " + resList.get(i).getRoomSize()
					+ ", �� ���� : " + resList.get(i).getRoomCnt() + ", �� ���� ȣ�� : " + resList.get(i).getRoomRealNo());
			}
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelRoomInfoByHotelID() {
		int hotelID;
		HotelUICommon.menuStart("ȣ�� �� �˻�(ȣ�ں�)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� �� �˻�(ȣ�ں�)      ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �˻��ϰ��� �ϴ� ȣ�� ��ȣ �Է� : ");
		hotelID = sc.nextInt();
		
		List<HotelRoomInfo> resList = HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByHotelID(hotelID);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("�� ��ȣ : " + resList.get(i).getRoomNo() + ", ȣ�� ��ȣ : " + resList.get(i).getHotelNo()
					+ ", ��� �Ŵ��� ��ȣ : " + resList.get(i).getManagerNo() + ", �� Ÿ�� ��ȣ : " + resList.get(i).getRoomTypeNo()
					+ ", �� ���� ��ȣ : " + resList.get(i).getStatusNo() + ", �� ũ�� : " + resList.get(i).getRoomSize()
					+ ", �� ���� : " + resList.get(i).getRoomCnt() + ", �� ���� ȣ�� : " + resList.get(i).getRoomRealNo());
			}
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelRoomInfoByType() {
		int typeNo;
		HotelUICommon.menuStart("ȣ�� �� �˻�(Ÿ�Ժ�)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� �� �˻�(Ÿ�Ժ�)      ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �˻��ϰ��� �ϴ� �� Ÿ�� ��ȣ �Է� : ");
		typeNo = sc.nextInt();
		
		List<HotelRoomInfo> resList = HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByType(typeNo);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("�� ��ȣ : " + resList.get(i).getRoomNo() + ", ȣ�� ��ȣ : " + resList.get(i).getHotelNo()
					+ ", ��� �Ŵ��� ��ȣ : " + resList.get(i).getManagerNo() + ", �� Ÿ�� ��ȣ : " + resList.get(i).getRoomTypeNo()
					+ ", �� ���� ��ȣ : " + resList.get(i).getStatusNo() + ", �� ũ�� : " + resList.get(i).getRoomSize()
					+ ", �� ���� : " + resList.get(i).getRoomCnt() + ", �� ���� ȣ�� : " + resList.get(i).getRoomRealNo());
			}
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelRoomInfoByStatus() {
		int statusId;
		HotelUICommon.menuStart("ȣ�� �� �˻�(���º�)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� �� �˻�(���º�)      ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   �˻��ϰ��� �ϴ� �� ���� ��ȣ �Է� : ");
		statusId = sc.nextInt();
		
		List<HotelRoomInfo> resList = HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByStatus(statusId);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("�� ��ȣ : " + resList.get(i).getRoomNo() + ", ȣ�� ��ȣ : " + resList.get(i).getHotelNo()
					+ ", ��� �Ŵ��� ��ȣ : " + resList.get(i).getManagerNo() + ", �� Ÿ�� ��ȣ : " + resList.get(i).getRoomTypeNo()
					+ ", �� ���� ��ȣ : " + resList.get(i).getStatusNo() + ", �� ũ�� : " + resList.get(i).getRoomSize()
					+ ", �� ���� : " + resList.get(i).getRoomCnt() + ", �� ���� ȣ�� : " + resList.get(i).getRoomRealNo());
			}
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelRoom() {
		HotelUICommon.menuStart("ȣ�� �� ��ü ���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� �� ��ü ���                  ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		List<HotelRoomInfo> resList = HotelAdminBasicUI.getHotelRoomDAO().getAllHotelRoomInfo();
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("�� ��ȣ : " + resList.get(i).getRoomNo() + ", ȣ�� ��ȣ : " + resList.get(i).getHotelNo()
					+ ", ��� �Ŵ��� ��ȣ : " + resList.get(i).getManagerNo() + ", �� Ÿ�� ��ȣ : " + resList.get(i).getRoomTypeNo()
					+ ", �� ���� ��ȣ : " + resList.get(i).getStatusNo() + ", �� ũ�� : " + resList.get(i).getRoomSize()
					+ ", �� ���� : " + resList.get(i).getRoomCnt() + ", �� ���� ȣ�� : " + resList.get(i).getRoomRealNo());
			}
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ���� ���� �������� �ʽ��ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
