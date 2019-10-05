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
		HotelUICommon.menuListStart("Location City 관리");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showLocationCityMenu();
			selectLocationCityMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showLocationCityMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        Location City        │");
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
		
		HotelUICommon.menuStart("정보등록");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            정보등록                         │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. 도시가 속한 나라 번호 입력 : ");
		countryNo = sc.nextInt();
		System.out.print("   2. 도시 이름 입력 : ");
		cityName = sc.nextLine();
		System.out.print("   3. 도시 코드 입력 : ");
		cityCode = sc.nextInt();
		
		if (HotelAdminBasicUI.getLocationCityDAO().insertLocationCity(countryNo, cityName, cityCode) > 0) {
			System.out.println("\n   정상적으로 등록되었습니다. (^^)");
		} else {
			System.out.println("\n   등록에 실패하였습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteLocationCity() {
		int cityCode;
		
		HotelUICommon.menuStart("정보삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            정보삭제                         │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   삭제하고자 하는 도시의 코드 입력 : ");
		cityCode = sc.nextInt();
		
		if (HotelAdminBasicUI.getLocationCityDAO().deleteLocationCity(cityCode) > 0) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyLocationCity() {
		String cityName;
		int cityNo, countryNo, cityCode;
		
		HotelUICommon.menuStart("정보수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            정보수정                         │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. 도시 번호 입력 : ");
		cityNo = sc.nextInt();
		System.out.print("   2. 변경하고자 하는 나라 번호 입력 : ");
		countryNo = sc.nextInt();
		System.out.print("   3. 변경하고자 하는 도시 이름 입력 : ");
		cityName = sc.nextLine();
		System.out.print("   4. 변경하고자 하는 도시 코드 입력 : ");
		cityCode = sc.nextInt();
		
		LocationCityInfo cityInfo = new LocationCityInfo(countryNo, cityName, cityCode);
		cityInfo.setCountryNo(cityNo);
		
		if (HotelAdminBasicUI.getLocationCityDAO().updateLocationCity(cityInfo) > 0) {
			System.out.println("\n   정상적으로 수정되었습니다. (^^)");
		} else {
			System.out.println("\n   수정에 실패하였습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchLocationCity() {
		int cityNo;
		
		HotelUICommon.menuStart("정보조회(id)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          정보조회(id)          │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   찾고자 하는 도시 번호 입력 : ");
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
			System.out.println("\n   데이터가 존재하지 않습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchAllLocationCity() {
		HotelUICommon.menuStart("정보조회(전체)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          정보조회(전체)         │");
		System.out.println("└─────────────────────────────┘\n");
		
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
			System.out.println("\n   데이터가 존재하지 않습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
