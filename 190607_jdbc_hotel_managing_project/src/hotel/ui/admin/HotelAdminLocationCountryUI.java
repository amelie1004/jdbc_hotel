package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.LocationCountryInfo;

public class HotelAdminLocationCountryUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminLocationCountryUI() {
		
	}
	
	public void startLocationCountryMenu() {
		HotelUICommon.menuListStart("Location Country ����");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showLocationCountryMenu();
			selectLocationCountryMenu();
		}

		HotelUICommon.menuListEnd();
	}

	public void showLocationCountryMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��      Location Country       ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. �������");
		System.out.println("   2. ��������");
		System.out.println("   3. ��������");
		System.out.println("  10. ������ȸ(id)");
		System.out.println("  20. ������ȸ(��ü)");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}

	public void selectLocationCountryMenu() {
		switch (choice) {
		case 1:
			insertLocationCountry();
			break;
		case 2:
			deleteLocationCountry();
			break;
		case 3:
			modifyLocationCountry();
			break;
		case 10:
			searchLocationCountry();
			break;
		case 20:
			searchAllLocationCountry();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}

	public void insertLocationCountry() {
		String countryName;
		int countryCode;
		
		HotelUICommon.menuStart("�������");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            �������                         ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. ���� �̸� �Է� : ");
		countryName = sc.nextLine();
		System.out.print("   2. ���� �ڵ� �Է� : ");
		countryCode = sc.nextInt();
		
		if (HotelAdminBasicUI.getLocationCountryDAO().insertLocationCountry(countryName, countryCode) > 0) {
			System.out.println("\n   ���������� ��ϵǾ����ϴ�. (^^)");
		} else {
			System.out.println("\n   ��Ͽ� �����Ͽ����ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}

	public void deleteLocationCountry() {
		int code;
		
		HotelUICommon.menuStart("��������");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            ��������                         ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ������ �ڵ� �Է� : ");
		code = sc.nextInt();
		
		if (HotelAdminBasicUI.getLocationCountryDAO().deleteLocationCountry(code) > 0) {
			System.out.println("\n   ������ �����Ͽ����ϴ�.");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}

	public void modifyLocationCountry() {
		String countryName;
		int countryNo, countryCode;
		
		HotelUICommon.menuStart("��������");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            ��������                         ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. ���� ��ȣ �Է� : ");
		countryNo = sc.nextInt();
		System.out.print("   2. �����ϰ��� �ϴ� ���� �̸� �Է� : ");
		countryName = sc.nextLine();
		System.out.print("   3. �����ϰ��� �ϴ� ���� �ڵ� �Է� : ");
		countryCode = sc.nextInt();
		
		LocationCountryInfo countryInfo = new LocationCountryInfo(countryName, countryCode);
		countryInfo.setCountryNo(countryNo);
		
		if (HotelAdminBasicUI.getLocationCountryDAO().updateLocationCountry(countryInfo) > 0) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}

	public void searchLocationCountry() {
		int countryNo;
		
		HotelUICommon.menuStart("������ȸ(id)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ������ȸ(id)          ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ã���� �ϴ� ���� ��ȣ �Է� : ");
		countryNo = sc.nextInt();
		
		LocationCountryInfo info = HotelAdminBasicUI.getLocationCountryDAO().getLocationCountry(countryNo);
		
		if (info != null) {
			System.out.printf("\nNo : %d\t, Name : %s\t\t, Code : %d\n",
					info.getCountryNo(),
					info.getCountryName(),
					info.getCountryCode());
			System.out.println();
		} else {
			System.out.println("\n   �����Ͱ� �������� �ʽ��ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}

	public void searchAllLocationCountry() {
		HotelUICommon.menuStart("������ȸ(��ü)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ������ȸ(��ü)          ��");
		System.out.println("��������������������������������������������������������������\n");
		
		List<LocationCountryInfo> resList = HotelAdminBasicUI.getLocationCountryDAO().getAllData();
		
		if (resList != null) {
			for (int i=0; i<resList.size(); i++) {
				System.out.printf("No : %d\t, Name : %s\t\t, Code : %d\n",
						resList.get(i).getCountryNo(),
						resList.get(i).getCountryName(),
						resList.get(i).getCountryCode());
			}
			System.out.println();
		} else {
			System.out.println("\n   �����Ͱ� �������� �ʽ��ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
