package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;

import hotel.vo.HotelBrandInfo;

public class HotelAdminHotelBrandUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelBrandUI() {
		
	}
	
	public void startHotelBrandMenu() {
		HotelUICommon.menuListStart("ȣ�� �귣���� ����");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelBrandMenu();
			selectHotelBrandMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelBrandMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� �귣���� ����               ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. ȣ�� �귣�� �߰�");
		System.out.println("   2. ȣ�� �귣�� �˻�(�̸�)");
		System.out.println("   3. ȣ�� �귣�� ����(ID)");
		System.out.println("  10. ȣ�� �귣�� ����(ID)");
		System.out.println("  11. ȣ�� �귣�� ����(�̸�)");
		System.out.println("  20. ȣ�� �귣�� ��ü���");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelBrandMenu() {
		switch (choice) {
		case 1:
			insertHotelBrand();
			break;
		case 2:
			searchHotelBrandByName();
			break;
		case 3:
			modifyHotelBrandByID();
			break;
		case 10:
			deleteHotelBrandByID();
			break;
		case 11:
			deleteHotelBrandByName();
			break;
		case 20:
			printAllHotelBrand();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelBrand() {
		String brandName;
		//String address, ceoName;
		//int workerCnt;
		HotelUICommon.menuStart("ȣ�� �귣�� �߰�");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� �귣�� �߰�                   ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �߰��ϰ��� �ϴ� �귣�� �� �Է� : ");
		brandName = sc.nextLine();
		//System.out.print("   2. �ּ� �Է� : ");
		//address = sc.nextLine();
		//System.out.print("   3. CEO �̸� �Է� : ");
		//ceoName = sc.nextLine();
		//System.out.print("   4. ���� �� �Է� : ");
		//workerCnt = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelBrandDAO().insertBrandInfo(brandName, "", "", 0)) {
			System.out.println("\n   ���������� �߰��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �߰��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelBrandByName() {
		String brandName;
		
		HotelUICommon.menuStart("ȣ�� �귣�� �˻�(�̸�)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �귣�� �˻�(�̸�)      ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   ��ȸ�ϰ��� �ϴ� �귣�� �� �Է� : ");
		brandName = sc.nextLine();
		
		HotelBrandInfo brandInfo = HotelAdminBasicUI.getHotelBrandDAO().getHotelBrandByName(brandName);
		if (brandInfo != null) {
			System.out.println();
			System.out.println("��ȣ : " + brandInfo.getBrandNo()
							+ ", �귣�� �� : " + brandInfo.getBrandName()
							+ ", �귣�� �ּ� : " + brandInfo.getAddress()
							+ ", CEO �̸� : " + brandInfo.getCeoName()
							+ ", ���� �� : " + brandInfo.getWorkerCnt());
			
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelBrandByID() {
		int brandID;
		String brandName;
		
		HotelUICommon.menuStart("ȣ�� �귣�� ����(ID)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �귣�� ����(ID)       ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� �귣�� ��ȣ �Է� : ");
		brandID = sc.nextInt();
		sc.nextLine();
		System.out.print("   �����ϰ��� �ϴ� �귣�� �� �Է� : ");
		brandName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelBrandDAO().modifyHotelBrandByID(brandID, brandName, "", "", 0)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelBrandByID() {
		int brandID;
		
		HotelUICommon.menuStart("ȣ�� �귣�� ����(ID)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �귣�� ����(ID)       ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� �귣�� ��ȣ �Է� : ");
		brandID = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelBrandDAO().deleteHotelBrandByID(brandID)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelBrandByName() {
		String brandName;
		
		HotelUICommon.menuStart("ȣ�� �귣�� ����(�̸�)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �귣�� ����(�̸�)      ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� �귣�� �� �Է� : ");
		brandName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelBrandDAO().deleteHotelBrandByName(brandName)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelBrand() {
		HotelUICommon.menuStart("ȣ�� �귣�� ��ü���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� �귣�� ��ü���               ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		
		List<HotelBrandInfo> brandList = HotelAdminBasicUI.getHotelBrandDAO().getAllHotelBrand();
		if (brandList.size() > 0) {
			for (int i=0; i<brandList.size(); i++) {
				System.out.println("��ȣ : " + brandList.get(i).getBrandNo()
								+ ", �귣�� �� : " + brandList.get(i).getBrandName()
								+ ", �귣�� �ּ� : " + brandList.get(i).getAddress()
								+ ", CEO �̸� : " + brandList.get(i).getCeoName()
								+ ", ���� �� : " + brandList.get(i).getWorkerCnt());
			}
			
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� ���� ���ų� ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
