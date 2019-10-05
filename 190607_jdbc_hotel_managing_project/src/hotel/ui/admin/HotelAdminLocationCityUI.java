package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.LocationCityInfo;

public class HotelAdminLocationCityUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminLocationCityUI() {
		
	}
	
	public void startLocationCityMenu() {
		HotelUICommon.menuListStart("Location City ����");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showLocationCityMenu();
			selectLocationCityMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showLocationCityMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        Location City        ��");
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
	
	public void selectLocationCityMenu() {
		switch (choice) {
		case 1:
			insertLocationCity();
			break;
		case 2:
			deleteLocationCity();
			break;
		case 3:
			modifyLocationCity();
			break;
		case 10:
			searchLocationCity();
			break;
		case 20:
			searchAllLocationCity();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertLocationCity() {
		String cityName;
		int cityCode, countryNo;
		
		HotelUICommon.menuStart("�������");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            �������                         ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. ���ð� ���� ���� ��ȣ �Է� : ");
		countryNo = sc.nextInt();
		System.out.print("   2. ���� �̸� �Է� : ");
		cityName = sc.nextLine();
		System.out.print("   3. ���� �ڵ� �Է� : ");
		cityCode = sc.nextInt();
		
		if (HotelAdminBasicUI.getLocationCityDAO().insertLocationCity(countryNo, cityName, cityCode) > 0) {
			System.out.println("\n   ���������� ��ϵǾ����ϴ�. (^^)");
		} else {
			System.out.println("\n   ��Ͽ� �����Ͽ����ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteLocationCity() {
		int cityCode;
		
		HotelUICommon.menuStart("��������");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            ��������                         ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ������ �ڵ� �Է� : ");
		cityCode = sc.nextInt();
		
		if (HotelAdminBasicUI.getLocationCityDAO().deleteLocationCity(cityCode) > 0) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyLocationCity() {
		String cityName;
		int cityNo, countryNo, cityCode;
		
		HotelUICommon.menuStart("��������");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            ��������                         ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. ���� ��ȣ �Է� : ");
		cityNo = sc.nextInt();
		System.out.print("   2. �����ϰ��� �ϴ� ���� ��ȣ �Է� : ");
		countryNo = sc.nextInt();
		System.out.print("   3. �����ϰ��� �ϴ� ���� �̸� �Է� : ");
		cityName = sc.nextLine();
		System.out.print("   4. �����ϰ��� �ϴ� ���� �ڵ� �Է� : ");
		cityCode = sc.nextInt();
		
		LocationCityInfo cityInfo = new LocationCityInfo(countryNo, cityName, cityCode);
		cityInfo.setCountryNo(cityNo);
		
		if (HotelAdminBasicUI.getLocationCityDAO().updateLocationCity(cityInfo) > 0) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchLocationCity() {
		int cityNo;
		
		HotelUICommon.menuStart("������ȸ(id)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ������ȸ(id)          ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ã���� �ϴ� ���� ��ȣ �Է� : ");
		cityNo = sc.nextInt();
		
		LocationCityInfo info = HotelAdminBasicUI.getLocationCityDAO().getLocationCity(cityNo);
		
		if (info != null) {
			System.out.printf("\nNo : %d\t, Country Name : %s\t\t, Name : %s\t\t, Code : %d\n",
					info.getCityNo(),
					info.getCountryName(),
					info.getCityName(),
					info.getCityCode());
			System.out.println();
		} else {
			System.out.println("\n   �����Ͱ� �������� �ʽ��ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchAllLocationCity() {
		HotelUICommon.menuStart("������ȸ(��ü)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ������ȸ(��ü)         ��");
		System.out.println("��������������������������������������������������������������\n");
		
		List<LocationCityInfo> resList = HotelAdminBasicUI.getLocationCityDAO().getAllData();
		
		if (resList != null) {
			for (int i=0; i<resList.size(); i++) {
				System.out.printf("No : %d\t, Country Name : %s\t\t, Name : %s\t\t, Code : %d\n",
						resList.get(i).getCityNo(),
						resList.get(i).getCountryName(),
						resList.get(i).getCityName(),
						resList.get(i).getCityCode());
			}
			System.out.println();
		} else {
			System.out.println("\n   �����Ͱ� �������� �ʽ��ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
