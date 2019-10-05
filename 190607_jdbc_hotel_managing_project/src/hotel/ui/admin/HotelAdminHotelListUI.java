package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.HotelInfo;

public class HotelAdminHotelListUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelListUI() {
		
	}
	
	public void startHotelListMenu() {
		HotelUICommon.menuListStart("호텔 리스트 관리");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelListMenu();
			selectHotelListMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelListMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 리스트 관리                   │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 호텔 정보 등록");
		System.out.println("   2. 호텔 기본정보 수정");
		System.out.println("   3. 호텔 기타정보 수정");
		System.out.println("  10. 호텔 정보 검색(id)");
		System.out.println("  11. 호텔 정보 검색(이름)");
		System.out.println("  12. 호텔 정보 검색(나라)");
		System.out.println("  13. 호텔 정보 검색(도시)");
		System.out.println("  20. 호텔 정보 삭제");
		System.out.println("  50. 호텔 전체 출력");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelListMenu() {
		switch (choice) {
		case 1:
			// 호텔 정보 등록 (not null)
			insertHotelList();
			break;
		case 2:
			// 호텔 기본정보 수정
			modifyHotelListBasicInfo();
			break;
		case 3:
			// 호텔 기타정보 수정
			modifyHotelListEtcInfo();
			break;
		case 10:
			// 호텔 정보 검색(id)
			searchHotelListByID();
			break;
		case 11:
			// 호텔 정보 검색(이름)
			searchHotelListByName();
			break;
		case 12:
			// 호텔 정보 검색(나라)
			searchHotelListByCountry();
			break;
		case 13:
			// 호텔 정보 검색(지역)
			searchHotelListByCity();
			break;
		case 20:
			// 호텔 정보 삭제
			deleteHotelListByID();
			break;
		case 50:
			// 호텔 전체 출력
			printAllHotelList();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelList() {
		String hotelName;
		int hotelBrandNo, hotelTypeNo, hotelLocNo;
		int hotelStar, hotelRoomCnt;
		
		HotelUICommon.menuStart("호텔 정보 등록");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│           호텔 정보 등록                    │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   1. 호텔 이름 입력 : ");
		hotelName= sc.nextLine();
		System.out.print("   2. 호텔 브랜드 번호 입력 : ");
		hotelBrandNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelBrandDAO().getHotelBrandByID(hotelBrandNo) == null) {
			System.out.println("\n   호텔 브랜드 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   3. 호텔 타입 번호 입력 : ");
		hotelTypeNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelTypeDAO().getHotelTypeByID(hotelTypeNo) == null) {
			System.out.println("\n   호텔 타입 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 호텔 위치 번호 입력 : ");
		hotelLocNo = sc.nextInt();
		if (HotelAdminBasicUI.getLocationCityDAO().getLocationCity(hotelLocNo) == null) {
			System.out.println("\n   호텔 위치 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   5. 호텔 Star 입력 : ");
		hotelStar = sc.nextInt();
		System.out.print("   6. 호텔 방 개수 입력 : ");
		hotelRoomCnt = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelListDAO().insertHotelListInfo(hotelName, hotelBrandNo, hotelTypeNo, hotelLocNo, hotelStar, hotelRoomCnt)) {
			System.out.println("\n   정상적으로 추가되었습니다. (^^)\n");
		} else {
			System.out.println("\n   추가에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelListBasicInfo() {
		int hotelNo;
		
		String hotelName;
		int hotelBrandNo, hotelTypeNo, hotelLocNo;
		int hotelStar, hotelRoomCnt;
		
		HotelUICommon.menuStart("호텔 기본정보 수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 기본정보 수정                  │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   수정하고자 하는 호텔 번호 입력 : ");
		hotelNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   검색하신 호텔을 찾을수 없습니다.\n");
		}  else {
			System.out.println("   호텔 기본정보 수정을 시작합니다.\n");
			System.out.print("   1. 호텔 이름 입력 : ");
			hotelName= sc.nextLine();
			System.out.print("   2. 호텔 브랜드 번호 입력 : ");
			hotelBrandNo = sc.nextInt();
			if (HotelAdminBasicUI.getHotelBrandDAO().getHotelBrandByID(hotelBrandNo) == null) {
				System.out.println("\n   호텔 브랜드 번호가 존재하지 않습니다.\n");
				sc = new Scanner(System.in);
				HotelUICommon.menuEnd(sc);
				return;
			}
			
			System.out.print("   3. 호텔 타입 번호 입력 : ");
			hotelTypeNo = sc.nextInt();
			if (HotelAdminBasicUI.getHotelTypeDAO().getHotelTypeByID(hotelTypeNo) == null) {
				System.out.println("\n   호텔 타입 번호가 존재하지 않습니다.\n");
				sc = new Scanner(System.in);
				HotelUICommon.menuEnd(sc);
				return;
			}
			
			System.out.print("   4. 호텔 위치 번호 입력 : ");
			hotelLocNo = sc.nextInt();
			if (HotelAdminBasicUI.getLocationCityDAO().getLocationCity(hotelLocNo) == null) {
				System.out.println("\n   호텔 위치 번호가 존재하지 않습니다.\n");
				sc = new Scanner(System.in);
				HotelUICommon.menuEnd(sc);
				return;
			}
			
			System.out.print("   5. 호텔 Star 입력 : ");
			hotelStar = sc.nextInt();
			System.out.print("   6. 호텔 방 개수 입력 : ");
			hotelRoomCnt = sc.nextInt();
			
			if (HotelAdminBasicUI.getHotelListDAO().updateHotelListBasicInfo(hotelNo, hotelName, hotelBrandNo, hotelTypeNo, hotelLocNo, hotelStar, hotelRoomCnt)) {
				System.out.println("\n   정상적으로 수정되었습니다. (^^)\n");
			} else {
				System.out.println("\n   수정에 실패하였습니다.\n");
			}
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelListEtcInfo() {
		int hotelNo;
		
		String hotelAddress;
		int hotelWorkerCnt, bossHotelNo;
		int can_breakfast, can_swimming, can_wifi, can_parking, can_pet, can_smoke;
		
		HotelUICommon.menuStart("호텔 기타정보 수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 기타정보 수정                  │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   수정하고자 하는 호텔 번호 입력 : ");
		hotelNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   검색하신 호텔을 찾을수 없습니다.\n");
		}  else {
			System.out.println("   호텔 기타정보 수정을 시작합니다.\n");
			sc.nextLine();
			System.out.print("   1. 호텔 상세주소 입력 : ");
			hotelAddress = sc.nextLine();
			System.out.print("   2. 호텔 직원 수 입력 : ");
			hotelWorkerCnt = sc.nextInt();
			System.out.print("   3. 본사 호텔 번호 입력(없을 경우 0) : ");
			bossHotelNo = sc.nextInt();
			System.out.print("   4. 아침 식사 가능 유무 입력(0/1) : ");
			can_breakfast = sc.nextInt();
			System.out.print("   5. 수영장 이용 가능 유무 입력(0/1) : ");
			can_swimming = sc.nextInt();
			System.out.print("   6. Wifi 이용가능 유무 입력(0/1) : ");
			can_wifi = sc.nextInt();
			System.out.print("   7. 주차 가능 유무 입력(0/1) : ");
			can_parking = sc.nextInt();
			System.out.print("   8. 애완동물 동행 가능 유무 입력(0/1) : ");
			can_pet = sc.nextInt();
			System.out.print("   9. 흡연 가능 유무 입력(0/1) : ");
			can_smoke = sc.nextInt();
			
			if (HotelAdminBasicUI.getHotelListDAO().updateHotelListEtcInfo(hotelNo, hotelAddress, hotelWorkerCnt, bossHotelNo, can_breakfast, can_swimming, can_wifi, can_parking, can_pet, can_smoke)) {
				System.out.println("\n   정상적으로 수정되었습니다. (^^)\n");
			} else {
				System.out.println("\n   수정에 실패하였습니다.\n");
			}
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelListByID() {
		int hotelNo;
		HotelUICommon.menuStart("호텔 정보 검색(id)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 정보 검색(id)       │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   검색하고자 하는 호텔 번호 입력 : ");
		hotelNo = sc.nextInt();
		
		HotelInfo resInfo = HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo);
		
		if (resInfo != null) {
			System.out.println("번호 : " + resInfo.getHotelNo()
					+ ", 호텔이름 : " + resInfo.getHotelName()
					+ ", 호텔브랜드 : " + resInfo.getBossHotelNo()
					+ ", 호텔타입 : " + resInfo.getTypeNo() + "\n"
					+ ", 호텔지역 : " + resInfo.getCityNo()
					+ ", 호텔주소 : " + resInfo.getHotelAddress() + "\n"
					+ ", 호텔Star : " + resInfo.getStar()
					+ ", 호텔 방 개수 : " + resInfo.getRoomCnt()
					+ ", 호텔 직원 수 : " + resInfo.getWorkerCnt() + "\n"
					+ ", 아침식사 유무 : " + resInfo.getCanBreakfast()
					+ ", 수영가능 유무 : " + resInfo.getCanSwimming()
					+ ", Wifi 유무 : " + resInfo.getCanSwimming()
					+ ", 주차가능 유무 : " + resInfo.getCanParking()
					+ ", 애완동물 동행가능 유무 : " + resInfo.getCanPet()
					+ ", 흡연 가능 유무 : " + resInfo.getCanSmoke() + "\n"
					+ ", 본사 호텔 번호 : " + resInfo.getBossHotelNo());
			
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelListByName() {
		String hotelName;
		HotelUICommon.menuStart("호텔 정보 검색(이름)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 정보 검색(이름)       │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   검색하고자 하는 호텔 이름 입력 : ");
		hotelName = sc.nextLine();
		
		HotelInfo resInfo = HotelAdminBasicUI.getHotelListDAO().getHotelListByName(hotelName);
		
		if (resInfo != null) {
			System.out.println("번호 : " + resInfo.getHotelNo()
					+ ", 호텔이름 : " + resInfo.getHotelName()
					+ ", 호텔브랜드 : " + resInfo.getBossHotelNo()
					+ ", 호텔타입 : " + resInfo.getTypeNo() + "\n"
					+ ", 호텔지역 : " + resInfo.getCityNo()
					+ ", 호텔주소 : " + resInfo.getHotelAddress() + "\n"
					+ ", 호텔Star : " + resInfo.getStar()
					+ ", 호텔 방 개수 : " + resInfo.getRoomCnt()
					+ ", 호텔 직원 수 : " + resInfo.getWorkerCnt() + "\n"
					+ ", 아침식사 유무 : " + resInfo.getCanBreakfast()
					+ ", 수영가능 유무 : " + resInfo.getCanSwimming()
					+ ", Wifi 유무 : " + resInfo.getCanSwimming()
					+ ", 주차가능 유무 : " + resInfo.getCanParking()
					+ ", 애완동물 동행가능 유무 : " + resInfo.getCanPet()
					+ ", 흡연 가능 유무 : " + resInfo.getCanSmoke() + "\n"
					+ ", 본사 호텔 번호 : " + resInfo.getBossHotelNo());
			
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelListByCountry() {
		int countryID;
		HotelUICommon.menuStart("호텔 정보 검색(나라)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 정보 검색(나라)       │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   검색하고자 하는 호텔의 나라번호 입력 : ");
		countryID = sc.nextInt();
		
		List<HotelInfo> resList = HotelAdminBasicUI.getHotelListDAO().getHotelListByCountry(countryID);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
			System.out.println("번호 : " + resList.get(i).getHotelNo()
					+ ", 호텔이름 : " + resList.get(i).getHotelName()
					+ ", 호텔브랜드 : " + resList.get(i).getBossHotelNo()
					+ ", 호텔타입 : " + resList.get(i).getTypeNo() + "\n"
					+ ", 호텔지역 : " + resList.get(i).getCityNo()
					+ ", 호텔주소 : " + resList.get(i).getHotelAddress() + "\n"
					+ ", 호텔Star : " + resList.get(i).getStar()
					+ ", 호텔 방 개수 : " + resList.get(i).getRoomCnt()
					+ ", 호텔 직원 수 : " + resList.get(i).getWorkerCnt() + "\n"
					+ ", 아침식사 유무 : " + resList.get(i).getCanBreakfast()
					+ ", 수영가능 유무 : " + resList.get(i).getCanSwimming()
					+ ", Wifi 유무 : " + resList.get(i).getCanSwimming()
					+ ", 주차가능 유무 : " + resList.get(i).getCanParking()
					+ ", 애완동물 동행가능 유무 : " + resList.get(i).getCanPet()
					+ ", 흡연 가능 유무 : " + resList.get(i).getCanSmoke() + "\n"
					+ ", 본사 호텔 번호 : " + resList.get(i).getBossHotelNo());
			}
			
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelListByCity() {
		int cityID;
		HotelUICommon.menuStart("호텔 정보 검색(도시)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 정보 검색(도시)       │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   검색하고자 하는 호텔의 도시번호 입력 : ");
		cityID = sc.nextInt();
		
		List<HotelInfo> resList = HotelAdminBasicUI.getHotelListDAO().getHotelListByCity(cityID);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("번호 : " + resList.get(i).getHotelNo()
						+ ", 호텔이름 : " + resList.get(i).getHotelName()
						+ ", 호텔브랜드 : " + resList.get(i).getBossHotelNo()
						+ ", 호텔타입 : " + resList.get(i).getTypeNo() + "\n"
						+ ", 호텔지역 : " + resList.get(i).getCityNo()
						+ ", 호텔주소 : " + resList.get(i).getHotelAddress() + "\n"
						+ ", 호텔Star : " + resList.get(i).getStar()
						+ ", 호텔 방 개수 : " + resList.get(i).getRoomCnt()
						+ ", 호텔 직원 수 : " + resList.get(i).getWorkerCnt() + "\n"
						+ ", 아침식사 유무 : " + resList.get(i).getCanBreakfast()
						+ ", 수영가능 유무 : " + resList.get(i).getCanSwimming()
						+ ", Wifi 유무 : " + resList.get(i).getCanSwimming()
						+ ", 주차가능 유무 : " + resList.get(i).getCanParking()
						+ ", 애완동물 동행가능 유무 : " + resList.get(i).getCanPet()
						+ ", 흡연 가능 유무 : " + resList.get(i).getCanSmoke() + "\n"
						+ ", 본사 호텔 번호 : " + resList.get(i).getBossHotelNo());
			}
			
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelListByID() {
		int hotelNo;
		HotelUICommon.menuStart("호텔 정보 삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 정보 삭제                      │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   삭제하고자 하는 호텔 번호 입력 : ");
		hotelNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelListDAO().deleteHotelListByID(hotelNo)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelList() {
		HotelUICommon.menuStart("호텔 전체 출력");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 전체 출력                      │");
		System.out.println("└─────────────────────────────┘\n");
		
		List<HotelInfo> resList = HotelAdminBasicUI.getHotelListDAO().getAllHotelList();
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("번호 : " + resList.get(i).getHotelNo()
						+ ", 호텔이름 : " + resList.get(i).getHotelName()
						+ ", 호텔브랜드 : " + resList.get(i).getBrandNo()
						+ ", 호텔타입 : " + resList.get(i).getTypeNo() + "\n"
						+ "\t, 호텔지역 : " + resList.get(i).getCityNo()
						+ ", 호텔주소 : " + resList.get(i).getHotelAddress() + "\n"
						+ "\t, 호텔Star : " + resList.get(i).getStar()
						+ ", 호텔 방 개수 : " + resList.get(i).getRoomCnt()
						+ ", 호텔 직원 수 : " + resList.get(i).getWorkerCnt() + "\n"
						+ "\t, 아침식사 유무 : " + resList.get(i).getCanBreakfast()
						+ ", 수영가능 유무 : " + resList.get(i).getCanSwimming()
						+ ", Wifi 유무 : " + resList.get(i).getCanSwimming()
						+ ", 주차가능 유무 : " + resList.get(i).getCanParking()
						+ ", 애완동물 동행가능 유무 : " + resList.get(i).getCanPet()
						+ ", 흡연 가능 유무 : " + resList.get(i).getCanSmoke() + "\n"
						+ "\t, 본사 호텔 번호 : " + resList.get(i).getBossHotelNo());
			}
			System.out.println("\n   정상적으로 출력되었습니다. (^^)\n");
		} else {
			System.out.println("\n   리스트가 존재하지 않습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
