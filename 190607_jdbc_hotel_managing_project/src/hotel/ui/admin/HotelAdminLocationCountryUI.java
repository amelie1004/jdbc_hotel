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
		HotelUICommon.menuListStart("Location Country 관리");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showLocationCountryMenu();
			selectLocationCountryMenu();
		}

		HotelUICommon.menuListEnd();
	}

	public void showLocationCountryMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│      Location Country       │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 정보등록");
		System.out.println("   2. 정보삭제");
		System.out.println("   3. 정보수정");
		System.out.println("  10. 정보조회(id)");
		System.out.println("  20. 정보조회(전체)");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
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
		
		HotelUICommon.menuStart("정보등록");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            정보등록                         │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. 나라 이름 입력 : ");
		countryName = sc.nextLine();
		System.out.print("   2. 나라 코드 입력 : ");
		countryCode = sc.nextInt();
		
		if (HotelAdminBasicUI.getLocationCountryDAO().insertLocationCountry(countryName, countryCode) > 0) {
			System.out.println("\n   정상적으로 등록되었습니다. (^^)");
		} else {
			System.out.println("\n   등록에 실패하였습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}

	public void deleteLocationCountry() {
		int code;
		
		HotelUICommon.menuStart("정보삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            정보삭제                         │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   삭제하고자 하는 도시의 코드 입력 : ");
		code = sc.nextInt();
		
		if (HotelAdminBasicUI.getLocationCountryDAO().deleteLocationCountry(code) > 0) {
			System.out.println("\n   삭제에 성공하였습니다.");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}

	public void modifyLocationCountry() {
		String countryName;
		int countryNo, countryCode;
		
		HotelUICommon.menuStart("정보수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            정보수정                         │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. 나라 번호 입력 : ");
		countryNo = sc.nextInt();
		System.out.print("   2. 변경하고자 하는 나라 이름 입력 : ");
		countryName = sc.nextLine();
		System.out.print("   3. 변경하고자 하는 나라 코드 입력 : ");
		countryCode = sc.nextInt();
		
		LocationCountryInfo countryInfo = new LocationCountryInfo(countryName, countryCode);
		countryInfo.setCountryNo(countryNo);
		
		if (HotelAdminBasicUI.getLocationCountryDAO().updateLocationCountry(countryInfo) > 0) {
			System.out.println("\n   정상적으로 수정되었습니다. (^^)");
		} else {
			System.out.println("\n   수정에 실패하였습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}

	public void searchLocationCountry() {
		int countryNo;
		
		HotelUICommon.menuStart("정보조회(id)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         정보조회(id)          │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   찾고자 하는 나라 번호 입력 : ");
		countryNo = sc.nextInt();
		
		LocationCountryInfo info = HotelAdminBasicUI.getLocationCountryDAO().getLocationCountry(countryNo);
		
		if (info != null) {
			System.out.printf("\nNo : %d\t, Name : %s\t\t, Code : %d\n",
					info.getCountryNo(),
					info.getCountryName(),
					info.getCountryCode());
			System.out.println();
		} else {
			System.out.println("\n   데이터가 존재하지 않습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}

	public void searchAllLocationCountry() {
		HotelUICommon.menuStart("정보조회(전체)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         정보조회(전체)          │");
		System.out.println("└─────────────────────────────┘\n");
		
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
			System.out.println("\n   데이터가 존재하지 않습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
