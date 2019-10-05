package hotel.ui.admin;

import java.util.Scanner;

import hotel.common.HotelDBCommon;
import hotel.common.HotelUICommon;
import hotel.dao.admin.HotelAdminCustomerClassDAO;
import hotel.dao.admin.HotelAdminCustomerDAO;
import hotel.dao.admin.HotelAdminHotelBrandDAO;
import hotel.dao.admin.HotelAdminHotelListDAO;
import hotel.dao.admin.HotelAdminHotelManagerDAO;
import hotel.dao.admin.HotelAdminHotelReservationDAO;
import hotel.dao.admin.HotelAdminHotelRoomDAO;
import hotel.dao.admin.HotelAdminHotelRoomDisplayDAO;
import hotel.dao.admin.HotelAdminHotelRoomStatusDAO;
import hotel.dao.admin.HotelAdminHotelRoomTypeDAO;
import hotel.dao.admin.HotelAdminHotelTypeDAO;
import hotel.dao.admin.HotelAdminLocationCityDAO;
import hotel.dao.admin.HotelAdminLocationCountryDAO;
import hotel.dao.admin.HotelAdminMemoDAO;

public class HotelAdminBasicUI {
	private static HotelAdminLocationCountryDAO locationCountryDAO = null;
	private static HotelAdminLocationCityDAO LocationCityDAO = null;
	private static HotelAdminCustomerClassDAO CustomerClassDAO = null;
	private static HotelAdminCustomerDAO CustomerDAO = null;
	private static HotelAdminMemoDAO MemoDAO = null;
	private static HotelAdminHotelListDAO HotelListDAO = null;
	private static HotelAdminHotelRoomDAO HotelRoomDAO = null;
	private static HotelAdminHotelManagerDAO HotelManagerDAO = null;
	private static HotelAdminHotelBrandDAO HotelBrandDAO = null;
	private static HotelAdminHotelTypeDAO HotelTypeDAO = null;
	private static HotelAdminHotelRoomTypeDAO HotelRoomTypeDAO = null;
	private static HotelAdminHotelRoomStatusDAO HotelRoomStatusDAO = null;
	private static HotelAdminHotelRoomDisplayDAO HotelRoomDisplayDAO = null;
	private static HotelAdminHotelReservationDAO HotelReservationDAO = null;

	private HotelAdminLocationCountryUI locationCountryUI = new HotelAdminLocationCountryUI();
	private HotelAdminLocationCityUI locationCityUI = new HotelAdminLocationCityUI();
	private HotelAdminCustomerClassUI customerClassUI = new HotelAdminCustomerClassUI();
	private HotelAdminCustomerUI customerUI = new HotelAdminCustomerUI();
	private HotelAdminMemoUI memoUI = new HotelAdminMemoUI();
	private HotelAdminHotelUI hotelUI = new HotelAdminHotelUI();
	private HotelAdminHotelRoomDisplayUI roomDisplayUI = new HotelAdminHotelRoomDisplayUI();
	private HotelAdminHotelReservationUI reservationUI = new HotelAdminHotelReservationUI();

	private Scanner sc = new Scanner(System.in);
	private int choice = 0;

	public HotelAdminBasicUI() {
		HotelDBCommon.Load_JDBC_Driver();

		locationCountryDAO = new HotelAdminLocationCountryDAO();
		LocationCityDAO = new HotelAdminLocationCityDAO();
		CustomerClassDAO = new HotelAdminCustomerClassDAO();
		CustomerDAO = new HotelAdminCustomerDAO();
		MemoDAO = new HotelAdminMemoDAO();
		HotelListDAO = new HotelAdminHotelListDAO();
		HotelRoomDAO = new HotelAdminHotelRoomDAO();
		HotelManagerDAO = new HotelAdminHotelManagerDAO();
		HotelBrandDAO = new HotelAdminHotelBrandDAO();
		HotelTypeDAO = new HotelAdminHotelTypeDAO();
		HotelRoomTypeDAO = new HotelAdminHotelRoomTypeDAO();
		HotelRoomStatusDAO = new HotelAdminHotelRoomStatusDAO();
		HotelRoomDisplayDAO = new HotelAdminHotelRoomDisplayDAO();
		HotelReservationDAO = new HotelAdminHotelReservationDAO();

		HotelUICommon.menuListStart("메인 메뉴");

		while (HotelUICommon.isMenuRun(0)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();

			showMainMenu();

			switch (choice) {
			case 1:
				// 고객 계정 관리
				customerUI.startCustomerListMenu();
				break;
			case 2:
				// 고객 메모 관리
				memoUI.startMemoMenu();
				break;
			case 10:
				// 호텔 정보 관리
				hotelUI.startHotelMenu();
				break;
			case 20:
				// 숙박 리스트 관리
				roomDisplayUI.startHotelRoomDisplayMenu();
				break;
			case 30:
				// 예약 리스트 관리
				reservationUI.startHotelReservationMenu();
				break;
			case 50:
				// 기타 정보 관리
				startEtcMenu();
				break;
			case 100:
				// 프로그램 종료
				exitProgram();
				break;
			default:
				break;
			}
		}
	}

	public void showMainMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│   SC Hotel Managing System  │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 고객 계정 관리");
		System.out.println("   2. 고객 메모 관리");
		System.out.println("  10. 호텔 정보 관리");
		System.out.println("  20. 숙박 리스트 관리");
		System.out.println("  30. 예약 리스트 관리");
		System.out.println("  50. 기타 정보 관리");
		System.out.println(" 100. 프로그램 종료");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}

	public static HotelAdminLocationCountryDAO getLocationCountryDAO() {
		return locationCountryDAO;
	}

	public static HotelAdminLocationCityDAO getLocationCityDAO() {
		return LocationCityDAO;
	}

	public static HotelAdminCustomerClassDAO getCustomerClassDAO() {
		return CustomerClassDAO;
	}

	public static HotelAdminCustomerDAO getCustomerDAO() {
		return CustomerDAO;
	}

	public static HotelAdminMemoDAO getMemoDAO() {
		return MemoDAO;
	}

	public static HotelAdminHotelListDAO getHotelListDAO() {
		return HotelListDAO;
	}

	public static HotelAdminHotelRoomDAO getHotelRoomDAO() {
		return HotelRoomDAO;
	}

	public static HotelAdminHotelManagerDAO getHotelManagerDAO() {
		return HotelManagerDAO;
	}

	public static HotelAdminHotelBrandDAO getHotelBrandDAO() {
		return HotelBrandDAO;
	}

	public static HotelAdminHotelTypeDAO getHotelTypeDAO() {
		return HotelTypeDAO;
	}

	public static HotelAdminHotelRoomTypeDAO getHotelRoomTypeDAO() {
		return HotelRoomTypeDAO;
	}

	public static HotelAdminHotelRoomStatusDAO getHotelRoomStatusDAO() {
		return HotelRoomStatusDAO;
	}

	public static HotelAdminHotelRoomDisplayDAO getHotelRoomDisplayDAO() {
		return HotelRoomDisplayDAO;
	}

	public static HotelAdminHotelReservationDAO getHotelReservationDAO() {
		return HotelReservationDAO;
	}

	public void startEtcMenu() {
		HotelUICommon.menuListStart("기타 정보 관리");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();

			showEtcMenu();
			selectEtcMenu();
		}

		HotelUICommon.menuListEnd();
	}

	public void showEtcMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│           기타 정보 관리                   │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. Location Country 관리");
		System.out.println("   2. Location City 관리");
		System.out.println("   3. Customer Class 관리");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}

	public void selectEtcMenu() {
		switch (choice) {
		case 1:
			locationCountryUI.startLocationCountryMenu();
			break;
		case 2:
			locationCityUI.startLocationCityMenu();
			break;
		case 3:
			customerClassUI.startCustomerClassMenu();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}

	public void exitProgram() {
		HotelUICommon.menuListStop();
		System.out.println("\n프로그램을 종료합니다. 감사합니다.\n");
	}
}
