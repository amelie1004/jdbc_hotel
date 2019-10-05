package hotel.ui.admin;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.RoomDisplayInfo;

public class HotelAdminHotelRoomDisplayUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelRoomDisplayUI() {
		
	}
	
	public void startHotelRoomDisplayMenu() {
		HotelUICommon.menuListStart("숙박 리스트 관리");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelRoomDisplayMenu();
			selectHotelRoomDisplayMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelRoomDisplayMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          숙박 리스트 관리                   │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 방 추가");
		System.out.println("  10. 현재 등록된 방 수정");
		System.out.println("  20. 현재 등록된 방 삭제");
		System.out.println("  30. 현재 등록된 방 조회(ID)");
		System.out.println("  31. 현재 등록된 방 조회(RoomID)");
		System.out.println("  40. 현재 거래중인 방 전체 출력");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelRoomDisplayMenu() {
		switch (choice) {
		case 1:
			// 방 추가
			insertRoom();
			break;
		case 10:
			// 현재 거래중인 방 수정
			modifyRoom();
			break;
		case 20:
			// 현재 거래중인 방 삭제
			deleteRoomByID();
			break;
		case 30:
			// 현재 거래중인 방 조회(ID)
			searchRoomByID();
			break;
		case 31:
			// 현재 거래중인 방 조회(RoomID)
			searchRoomByRoomID();
			break;
		case 40:
			// 현재 거래중인 방 전체 출력
			printAllRoomByDealingState();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertRoom() {
		int roomNo, pricePerDay, isDealed; 
		Date dealStartDate, dealEndDate;
		
		HotelUICommon.menuStart("방 추가");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│             방 추가                         │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   1. 등록된 방 번호 입력 : ");
		roomNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByID(roomNo) == null) {
			System.out.println("\n   방 번호 가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. 거래 시작 일자 입력(YYYY-MM-DD) : ");
		String dealStartStr = sc.next();
		dealStartDate = java.sql.Date.valueOf(dealStartStr);
		
		System.out.print("   3. 거래 종료 일자 입력(YYYY-MM-DD) : ");
		String dealEndStr = sc.next();
		dealEndDate = java.sql.Date.valueOf(dealEndStr);
		
		if (dealStartDate.after(dealEndDate)) {
			System.out.println("\n   날짜를 잘못 입력하셨습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 1일당 대여 가격 : ");
		pricePerDay = sc.nextInt();
		
		System.out.print("   5. 거래 가능 유무(0/1) : ");
		isDealed = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().insertRoomDisplayInfo(roomNo, dealStartDate, dealEndDate, pricePerDay, isDealed)) {
			System.out.println("\n   정상적으로 추가되었습니다. (^^)\n");
		} else {
			System.out.println("\n   추가에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyRoom() {
		int roomDisplayNo;
		int roomNo, pricePerDay, isDealed; 
		Date dealStartDate, dealEndDate, hotdealDate;
		
		HotelUICommon.menuStart("현재 등록된 방 수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         현재 등록된 방 수정                 │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   수정하고자 하는 거래등록번호 입력 : ");
		roomDisplayNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().getRoomDisplayInfoByID(roomDisplayNo) != null) {
			System.out.println("\n   거래등록번호 가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   1. 등록된 방 번호 입력 : ");
		roomNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByID(roomNo) == null) {
			System.out.println("\n   방 번호 가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. 거래 시작 일자 입력(YYYY-MM-DD) : ");
		String dealStartStr = sc.next();
		dealStartDate = java.sql.Date.valueOf(dealStartStr);
		
		System.out.print("   3. 거래 종료 일자 입력(YYYY-MM-DD) : ");
		String dealEndStr = sc.next();
		dealEndDate = java.sql.Date.valueOf(dealEndStr);
		
		if (dealStartDate.after(dealEndDate)) {
			System.out.println("\n   날짜를 잘못 입력하셨습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 1일당 대여 가격 : ");
		pricePerDay = sc.nextInt();
		
		System.out.print("   5. 핫딜 시작 일자 입력(0/YYYY-MM-DD) : ");
		String hotdealStr = sc.next();
		hotdealDate = java.sql.Date.valueOf(hotdealStr);
		
		if (hotdealDate.after(dealStartDate)) {
			System.out.println("\n   날짜를 잘못 입력하셨습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   6. 거래 가능 유무(0/1) : ");
		isDealed = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().updateRoomDisplayInfo(roomDisplayNo, roomNo, dealStartDate, dealEndDate, pricePerDay, hotdealDate, isDealed)) {
			System.out.println("\n   정상적으로 수정되었습니다. (^^)\n");
		} else {
			System.out.println("\n   수정에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteRoomByID() {
		int roomDisplayNo;
		HotelUICommon.menuStart("현재 등록된 방 삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         현재 등록된 방 삭제                 │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   삭제하고자 하는 거래등록번호 입력 : ");
		roomDisplayNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().deleteRoomDisplayInfo(roomDisplayNo)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchRoomByID() {
		int roomDisplayNo;
		HotelUICommon.menuStart("현재 등록된 방 조회(ID)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│       현재 등록된 방 조회(ID)      │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   검색하고자 하는 거래등록번호 입력 : ");
		roomDisplayNo = sc.nextInt();
		
		RoomDisplayInfo resInfo = HotelAdminBasicUI.getHotelRoomDisplayDAO().getRoomDisplayInfoByID(roomDisplayNo);
		if (resInfo != null) {
			System.out.println();
			System.out.println("거래등록번호 : " + resInfo.getDisplayNo()
					+ ", 방 번호 : " + resInfo.getRoomNo() + "\n"
					+ ", 거래시작일자 : " + resInfo.getStartDate().toString() + ", 거래종료일자 : " + resInfo.getEndDate().toString() + "\n"
					+ ", 1일당 가격 : " + resInfo.getPricePerDay() + ", 핫딜 시작 일자 : " + resInfo.getHotdealStartDate());
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchRoomByRoomID() {
		int roomDisplayNo;
		HotelUICommon.menuStart("현재 등록된 방 조회(RoomID)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│      현재 등록된 방 조회(RoomID)   │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   검색하고자 하는 방 번호 입력 : ");
		roomDisplayNo = sc.nextInt();
		
		List<RoomDisplayInfo> resList = HotelAdminBasicUI.getHotelRoomDisplayDAO().getRoomDisplayInfoByRoomID(roomDisplayNo);
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("거래등록번호 : " + resList.get(i).getDisplayNo()
						+ ", 방 번호 : " + resList.get(i).getRoomNo() + "\n"
						+ ", 거래시작일자 : " + resList.get(i).getStartDate().toString() + ", 거래종료일자 : " + resList.get(i).getEndDate().toString() + "\n"
						+ ", 1일당 가격 : " + resList.get(i).getPricePerDay() + ", 핫딜 시작 일자 : " + resList.get(i).getHotdealStartDate());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllRoomByDealingState() {
		HotelUICommon.menuStart("현재 거래중인 방 전체 출력");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│      현재 거래중인 방 전체 출력              │");
		System.out.println("└─────────────────────────────┘\n");
		
		List<RoomDisplayInfo> resList = HotelAdminBasicUI.getHotelRoomDisplayDAO().getAllRoomDisplayInfoByDealed();
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("거래등록번호 : " + resList.get(i).getDisplayNo()
						+ ", 방 번호 : " + resList.get(i).getRoomNo() + "\n"
						+ ", 거래시작일자 : " + resList.get(i).getStartDate().toString() + ", 거래종료일자 : " + resList.get(i).getEndDate().toString() + "\n"
						+ ", 1일당 가격 : " + resList.get(i).getPricePerDay() + ", 핫딜 시작 일자 : " + resList.get(i).getHotdealStartDate());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
