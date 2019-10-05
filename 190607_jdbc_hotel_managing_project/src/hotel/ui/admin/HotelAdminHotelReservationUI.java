package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.HotelReservationInfo;

public class HotelAdminHotelReservationUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelReservationUI() {
		
	}
	
	public void startHotelReservationMenu() {
		HotelUICommon.menuListStart("예약 리스트 관리");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelReservationMenu();
			selectHotelReservationMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelReservationMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          예약 리스트 관리                   │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("  10. 예약 목록 확인(예약 번호)");
		System.out.println("  11. 예약 목록 확인(회원 번호)");
		System.out.println("  12. 예약 목록 확인(방 번호)");
		System.out.println("  30. 예약 목록 삭제");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelReservationMenu() {
		switch (choice) {
		case 10:
			// 예약 목록 확인(예약 번호)
			searchReserveListByID();
			break;
		case 11:
			// 예약 목록 확인(회원 번호)
			searchReserveListByCustomer();
			break;
		case 12:
			// 예약 목록 확인(방 번호)
			searchReserveListByRoom();
			break;
		case 20:
			// 예약 목록 확인(전체)
			printAllReserveList();
			break;
		case 30:
			// 예약 목록 삭제
			deleteReserveList();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void searchReserveListByID() {
		int reservationID;
		HotelUICommon.menuStart("예약 목록 확인(예약 번호)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│       예약 목록 확인(예약 번호)     │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   예약 번호 입력 : ");
		reservationID = sc.nextInt();
		
		HotelReservationInfo resInfo = HotelAdminBasicUI.getHotelReservationDAO().getReservationInfoByID(reservationID);
		if (resInfo != null) {
			System.out.println();
			System.out.println("예약 번호 : " + resInfo.getReservationNo() + ", 고객 번호 : " + resInfo.getCustomerNo()
						+ ", 방 번호 : " + resInfo.getRoomNo() + ", 시작 일자 : " + resInfo.getStartDate() + ", 종료 일자 : " + resInfo.getEndDate()
						+ ", 결제 일자 : " + resInfo.getSignDate() + ", 결제취소 일자 : " + resInfo.getSignCancelDate() + "\n"
						+ ", 성인 수 : " + resInfo.getAdultCnt() + ", 청소년 수 : " + resInfo.getChildrenCnt() + ", 핫딜 유무 : " + resInfo.getIsHotdeal() + "\n"
						+ ", 고객 요청 사항 : " + resInfo.getRequestStr());
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchReserveListByCustomer() {
		int customerId;
		HotelUICommon.menuStart("예약 목록 확인(회원 번호)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│      예약 목록 확인(회원 번호)     │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   회원 번호 입력 : ");
		customerId = sc.nextInt();
		
		List<HotelReservationInfo> resList = HotelAdminBasicUI.getHotelReservationDAO().getReservationInfoByCustomer(customerId);
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("예약 번호 : " + resList.get(i).getReservationNo() + ", 고객 번호 : " + resList.get(i).getCustomerNo()
							+ ", 방 번호 : " + resList.get(i).getRoomNo() + ", 시작 일자 : " + resList.get(i).getStartDate() + ", 종료 일자 : " + resList.get(i).getEndDate()
							+ ", 결제 일자 : " + resList.get(i).getSignDate() + ", 결제취소 일자 : " + resList.get(i).getSignCancelDate() + "\n"
							+ ", 성인 수 : " + resList.get(i).getAdultCnt() + ", 청소년 수 : " + resList.get(i).getChildrenCnt() + ", 핫딜 유무 : " + resList.get(i).getIsHotdeal() + "\n"
							+ ", 고객 요청 사항 : " + resList.get(i).getRequestStr());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchReserveListByRoom() {
		int roomNo;
		HotelUICommon.menuStart("예약 목록 확인(방 번호)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        예약 목록 확인(방 번호)     │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   방 번호 입력 : ");
		roomNo = sc.nextInt();
		
		List<HotelReservationInfo> resList = HotelAdminBasicUI.getHotelReservationDAO().getReservationInfoByRoom(roomNo);
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("예약 번호 : " + resList.get(i).getReservationNo() + ", 고객 번호 : " + resList.get(i).getCustomerNo()
							+ ", 방 번호 : " + resList.get(i).getRoomNo() + ", 시작 일자 : " + resList.get(i).getStartDate() + ", 종료 일자 : " + resList.get(i).getEndDate()
							+ ", 결제 일자 : " + resList.get(i).getSignDate() + ", 결제취소 일자 : " + resList.get(i).getSignCancelDate() + "\n"
							+ ", 성인 수 : " + resList.get(i).getAdultCnt() + ", 청소년 수 : " + resList.get(i).getChildrenCnt() + ", 핫딜 유무 : " + resList.get(i).getIsHotdeal() + "\n"
							+ ", 고객 요청 사항 : " + resList.get(i).getRequestStr());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllReserveList() {
		HotelUICommon.menuStart("예약 목록 확인(전체)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        예약 목록 확인(전체)       │");
		System.out.println("└─────────────────────────────┘\n");
		
		// 여기서부터 다시 구현
		
		
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteReserveList() {
		int reservationID;
		HotelUICommon.menuStart("예약 목록 삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          예약 목록 삭제                      │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   예약 번호 입력 : ");
		reservationID = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelReservationDAO().deleteReserveListByID(reservationID)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
