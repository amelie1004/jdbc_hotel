package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.RoomTypeInfo;

public class HotelAdminHotelRoomTypeUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelRoomTypeUI() {
		
	}
	
	public void startHotelRoomTypeMenu() {
		HotelUICommon.menuListStart("ȣ�� �� Ÿ�Ը�� ����");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelRoomTypeMenu();
			selectHotelRoomTypeMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelRoomTypeMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� �� Ÿ�Ը�� ����              ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. ȣ�� �� Ÿ�� �߰�");
		System.out.println("   2. ȣ�� �� Ÿ�� ����");
		System.out.println("  10. ȣ�� �� Ÿ�� ����");
		System.out.println("  20. ȣ�� �� Ÿ�� ��ü���");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelRoomTypeMenu() {
		switch (choice) {
		case 1:
			// ȣ�� �� Ÿ�� �߰�
			insertHotelRoomType();
			break;
		case 2:
			// ȣ�� �� Ÿ�� ����
			modifyHotelRoomType();
			break;
		case 10:
			// ȣ�� �� Ÿ�� ����
			deleteHotelRoomType();
			break;
		case 20:
			// ȣ�� �� Ÿ�� ��ü���
			printAllHotelRoomType();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelRoomType() {
		int typeNo;
		String typeName;
		HotelUICommon.menuStart("ȣ�� �� Ÿ�� �߰�");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� �� Ÿ�� �߰�                  ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   1. ȣ�� �� Ÿ�� ��ȣ �Է� : ");
		typeNo = sc.nextInt();
		sc.nextLine();
		System.out.print("   2. ȣ�� �� Ÿ�� �� �Է� : ");
		typeName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelRoomTypeDAO().insertHotelRoomTypeInfo(typeNo, typeName)) {
			System.out.println("\n   ���������� �߰��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �߰��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelRoomType() {
		int typeID;
		String typeName;
		
		HotelUICommon.menuStart("ȣ�� �� Ÿ�� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� �� Ÿ�� ����                  ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ȣ�� �� Ÿ�� ��ȣ �Է� : ");
		typeID = sc.nextInt();
		sc.nextLine();
		System.out.print("   �����ϰ��� �ϴ� ȣ�� �� Ÿ�� �� �Է� : ");
		typeName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelRoomTypeDAO().modifyHotelRoomTypeInfoByID(typeID, typeName)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelRoomType() {
		int typeID;
		
		HotelUICommon.menuStart("ȣ�� �� Ÿ�� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� �� Ÿ�� ����                  ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ȣ��Ÿ�� ��ȣ �Է� : ");
		typeID = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomTypeDAO().deleteHotelRoomTypeInfoByID(typeID)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelRoomType() {
		HotelUICommon.menuStart("ȣ�� �� Ÿ�� ��ü���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �� Ÿ�� ��ü���                ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		
		List<RoomTypeInfo> typeList = HotelAdminBasicUI.getHotelRoomTypeDAO().getAllHotelRoomType();
		if (typeList.size() > 0) {
			for (int i=0; i<typeList.size(); i++) {
				System.out.println("��ȣ : " + typeList.get(i).getTypeNo()
							+ ", ȣ�� �� Ÿ�� �� : " + typeList.get(i).getTypeStr());
			}
			
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� ���� ���ų� ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
