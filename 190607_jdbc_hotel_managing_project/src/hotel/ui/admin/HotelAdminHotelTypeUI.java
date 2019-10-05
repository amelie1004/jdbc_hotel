package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.HotelTypeInfo;

public class HotelAdminHotelTypeUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelTypeUI() {
		
	}
	
	public void startHotelTypeMenu() {
		HotelUICommon.menuListStart("ȣ�� Ÿ�Ը�� ����");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelTypeMenu();
			selectHotelTypeMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelTypeMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� Ÿ�Ը�� ����                ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. ȣ�� Ÿ�� �߰�");
		System.out.println("   2. ȣ�� Ÿ�� ����");
		System.out.println("  10. ȣ�� Ÿ�� ����");
		System.out.println("  20. ȣ�� Ÿ�� ��ü���");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelTypeMenu() {
		switch (choice) {
		case 1:
			// ȣ�� Ÿ�� �߰�
			insertHotelType();
			break;
		case 2:
			// ȣ�� Ÿ�� ����
			modifyHotelType();
			break;
		case 10:
			// ȣ�� Ÿ�� ����
			deleteHotelType();
			break;
		case 20:
			// ȣ�� Ÿ�� ��ü���
			printAllHotelType();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelType() {
		int typeNo;
		String typeName;
		//String address, ceoName;
		//int workerCnt;
		HotelUICommon.menuStart("ȣ�� Ÿ�� �߰�");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��           ȣ�� Ÿ�� �߰�                    ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   1. ȣ�� Ÿ�� ��ȣ �Է� : ");
		typeNo = sc.nextInt();
		sc.nextLine();
		System.out.print("   2. ȣ�� Ÿ�� �� �Է� : ");
		typeName = sc.nextLine();
		//System.out.print("   2. �ּ� �Է� : ");
		//address = sc.nextLine();
		//System.out.print("   3. CEO �̸� �Է� : ");
		//ceoName = sc.nextLine();
		//System.out.print("   4. ���� �� �Է� : ");
		//workerCnt = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelTypeDAO().insertHotelTypeInfo(typeNo, typeName)) {
			System.out.println("\n   ���������� �߰��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �߰��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelType() {
		int typeID;
		String typeName;
		
		HotelUICommon.menuStart("ȣ�� Ÿ�� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��           ȣ�� Ÿ�� ����                    ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ȣ��Ÿ�� ��ȣ �Է� : ");
		typeID = sc.nextInt();
		sc.nextLine();
		System.out.print("   �����ϰ��� �ϴ� ȣ��Ÿ�� �� �Է� : ");
		typeName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelTypeDAO().modifyHotelTypeInfoByID(typeID, typeName)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelType() {
		int typeID;
		
		HotelUICommon.menuStart("ȣ�� Ÿ�� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��           ȣ�� Ÿ�� ����                    ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ȣ��Ÿ�� ��ȣ �Է� : ");
		typeID = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelTypeDAO().deleteHotelTypeInfoByID(typeID)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelType() {
		HotelUICommon.menuStart("ȣ�� �귣�� ��ü���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� Ÿ�� ��ü���                  ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		
		List<HotelTypeInfo> typeList = HotelAdminBasicUI.getHotelTypeDAO().getAllHotelType();
		if (typeList.size() > 0) {
			for (int i=0; i<typeList.size(); i++) {
				System.out.println("��ȣ : " + typeList.get(i).getTypeNo()
								+ ", ȣ�� Ÿ�� �� : " + typeList.get(i).getTypeStr());
			}
			
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� ���� ���ų� ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
