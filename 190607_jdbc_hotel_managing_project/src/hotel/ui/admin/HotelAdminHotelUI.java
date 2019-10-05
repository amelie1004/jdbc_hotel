package hotel.ui.admin;

import java.util.Scanner;

import hotel.common.HotelUICommon;

public class HotelAdminHotelUI {
	private HotelAdminHotelListUI hotelListUI = new HotelAdminHotelListUI();
	private HotelAdminHotelRoomUI hotelRoomUI = new HotelAdminHotelRoomUI();
	private HotelAdminHotelManagerUI hotelManagerUI = new HotelAdminHotelManagerUI();
	private HotelAdminHotelBrandUI hotelBrandUI = new HotelAdminHotelBrandUI();
	private HotelAdminHotelTypeUI hotelTypeUI = new HotelAdminHotelTypeUI();
	private HotelAdminHotelRoomTypeUI hotelRoomTypeUI = new HotelAdminHotelRoomTypeUI();
	private HotelAdminHotelRoomStatusUI hotelRoomStatusUI = new HotelAdminHotelRoomStatusUI();
	
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelUI() {
		
	}
	
	public void startHotelMenu() {
		HotelUICommon.menuListStart("호텔 정보 관리");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelMenu();
			selectHotelMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│           호텔 정보 관리                    │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 호텔 리스트 관리");
		System.out.println("   2. 호텔 방 관리");
		System.out.println("   3. 호텔 매니저 관리");
		System.out.println("  11. 호텔 브랜드목록 관리");
		System.out.println("  12. 호텔 타입목록 관리");
		System.out.println("  21. 호텔 방 타입목록 관리");
		System.out.println("  22. 호텔 방 상태목록 관리");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelMenu() {
		switch (choice) {
		case 1:
			// 호텔 리스트 관리
			hotelListUI.startHotelListMenu();
			break;
		case 2:
			// 호텔 방 관리
			hotelRoomUI.startHotelRoomMenu();
			break;
		case 3:
			// 호텔 매니저 관리
			hotelManagerUI.startHotelManagerMenu();
			break;
		case 11:
			// 호텔 브랜드목록 관리
			hotelBrandUI.startHotelBrandMenu();
			break;
		case 12:
			// 호텔 타입목록 관리
			hotelTypeUI.startHotelTypeMenu();
			break;
		case 21:
			// 호텔 방 타입목록 관리
			hotelRoomTypeUI.startHotelRoomTypeMenu();
			break;
		case 22:
			// 호텔 방 상태목록 관리
			hotelRoomStatusUI.startHotelRoomStatusMenu();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
}
