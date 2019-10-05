package hotel.ui.customer;

import java.util.Scanner;

import hotel.common.HotelDBCommon;
import hotel.common.HotelUICommon;
import hotel.dao.customer.HotelCustomerDAO;
import hotel.dao.customer.HotelMemoDAO;
import hotel.dao.customer.HotelReservationDAO;
import hotel.dao.customer.HotelRoomDisplayDAO;

public class HotelBasicUI {
	private static HotelCustomerDAO CustomerDAO = null;
	private static HotelMemoDAO MemoDAO = null;
	private static HotelRoomDisplayDAO RoomDisplayDAO = null;
	private static HotelReservationDAO ReservationDAO = null;

	private HotelCustomerUI customerUI = new HotelCustomerUI();
	private HotelMemoUI memoUI = new HotelMemoUI();
	private HotelRoomDisplayUI roomDisplayUI = new HotelRoomDisplayUI();
	private HotelReservationUI reservationUI = new HotelReservationUI();

	private Scanner sc = new Scanner(System.in);
	private int choice = 0;

	public HotelBasicUI() {
		HotelDBCommon.Load_JDBC_Driver();

		CustomerDAO = new HotelCustomerDAO();
		MemoDAO = new HotelMemoDAO();
		RoomDisplayDAO = new HotelRoomDisplayDAO();
		ReservationDAO = new HotelReservationDAO();

		HotelUICommon.menuListStart("메인 메뉴");

		while (HotelUICommon.isMenuRun(0)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();

			showMainMenu();

			switch (choice) {
			case 1:
				if (!HotelCustomerDAO.isLogin()) {
					// 로그아웃 상태 -- 회원가입
					customerUI.insertCustomerList();
				} else {
					// 로그인 상태 -- 예약 가능한 방 출력
					roomDisplayUI.printAllAccesibleRoomList();
				}
				break;
			case 2:
				if (!HotelCustomerDAO.isLogin()) {
					// 로그아웃 상태 -- 로그인 메뉴
					customerUI.loginCustomer();
				} else {
					// 로그인 상태 -- 현재 예약한 방 출력
					reservationUI.printAllReservedInfoByCustomerID();
				}
				break;
			case 10:
				if (HotelCustomerDAO.isLogin()) {
					// 로그인 상태 -- 방 예약하기
					reservationUI.insertReservationInfoByRoomID();
				}
				break;
			case 20:
				if (HotelCustomerDAO.isLogin()) {
					// 로그인 상태 -- 방 예약 취소하기
					reservationUI.cancelReservationInfoByRoomID();
				}
				break;
			case 30:
				if (HotelCustomerDAO.isLogin()) {
					// 로그인 상태 -- 개인 메모 관리
					memoUI.startCustomerMemoMenu();
				}
				break;
			case 40:
				if (HotelCustomerDAO.isLogin()) {
					// 로그인 상태 -- 로그아웃 메뉴
					customerUI.logoutCustomer();
				}
				break;
			case 50:
				if (!HotelCustomerDAO.isLogin()) {
					// 로그아웃 상태
					customerUI.deleteCustomerInLogOff();
				} else {
					// 로그인 상태 -- 회원탈퇴
					customerUI.deleteCustomerInLogOn();
				}
				break;
			case 100:
				// 프로그램 종료
				if (HotelCustomerDAO.isLogin()) {
					if (HotelBasicUI.getCustomerDAO().logout(HotelCustomerDAO.getLoginID())) {
						System.out.println("\n   정상적으로 로그아웃되었습니다. (^^)");
					} else {
						System.out.println("\n   로그아웃에 실패하였습니다.");
					}
				}
				exitProgram();
				break;
			default:
				break;
			}
		}
	}

	public static HotelCustomerDAO getCustomerDAO() {
		return CustomerDAO;
	}

	public static HotelMemoDAO getMemoDAO() {
		return MemoDAO;
	}

	public static HotelRoomDisplayDAO getRoomDisplayDAO() {
		return RoomDisplayDAO;
	}

	public static HotelReservationDAO getReservationDAO() {
		return ReservationDAO;
	}

	public void showMainMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│   SC Hotel Customer System  │");
		System.out.println("└─────────────────────────────┘\n");
		if (!HotelCustomerDAO.isLogin()) {
			// 로그아웃 상태
			System.out.println("   1. 회원가입");
			System.out.println("   2. 로그인");
			System.out.println("  50. 회원탈퇴");
		} else {
			// 로그인 상태
			System.out.println("   1. 현재 예약 가능한 방 출력");
			System.out.println("   2. 현재 예약한 방 출력");
			System.out.println("  10. 방 예약하기");
			System.out.println("  20. 방 예약 취소하기");
			System.out.println("  30. 개인 메모 관리");
			System.out.println("  40. 로그아웃");
			System.out.println("  50. 회원탈퇴");
		}
		System.out.println(" 100. 프로그램 종료");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}

	public void exitProgram() {
		HotelUICommon.menuListStop();
		System.out.println("\n프로그램을 종료합니다. 감사합니다.\n");
	}
}
