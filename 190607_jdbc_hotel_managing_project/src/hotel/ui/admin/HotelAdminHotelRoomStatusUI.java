package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.RoomStatusInfo;

public class HotelAdminHotelRoomStatusUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelRoomStatusUI() {
		
	}
	
	public void startHotelRoomStatusMenu() {
		HotelUICommon.menuListStart("호텔 방 상태목록 관리");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelRoomStatusMenu();
			selectHotelRoomStatusMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelRoomStatusMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 방 상태목록 관리                │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 호텔 방 상태목록 추가");
		System.out.println("   2. 호텔 방 상태목록 수정");
		System.out.println("  10. 호텔 방 상태목록 삭제");
		System.out.println("  20. 호텔 방 상태목록 전체출력");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelRoomStatusMenu() {
		switch (choice) {
		case 1:
			// 호텔 방 상태목록 추가
			insertHotelRoomStatusType();
			break;
		case 2:
			// 호텔 방 상태목록 수정
			modifyHotelRoomStatusType();
			break;
		case 10:
			// 호텔 방 상태목록 삭제
			deleteHotelRoomStatusType();
			break;
		case 20:
			// 호텔 방 상태목록 전체출력
			printAllHotelRoomStatusType();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelRoomStatusType() {
		int statusNo;
		String statusName;
		HotelUICommon.menuStart("호텔 방 상태목록 추가");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 방 상태목록 추가                │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   1. 호텔 방 상태 번호 입력 : ");
		statusNo = sc.nextInt();
		sc.nextLine();
		System.out.print("   2. 호텔 방 상태 명 입력 : ");
		statusName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelRoomStatusDAO().insertHotelRoomStatusInfo(statusNo, statusName)) {
			System.out.println("\n   정상적으로 추가되었습니다. (^^)\n");
		} else {
			System.out.println("\n   추가에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelRoomStatusType() {
		int statusNo;
		String statusName;
		
		HotelUICommon.menuStart("호텔 방 상태목록 수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 방 상태목록 수정                │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   수정하고자 하는 호텔 방 상태 번호 입력 : ");
		statusNo = sc.nextInt();
		sc.nextLine();
		System.out.print("   수정하고자 하는 호텔 방 상태 명 입력 : ");
		statusName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelRoomStatusDAO().modifyHotelRoomStatusInfoByID(statusNo, statusName)) {
			System.out.println("\n   정상적으로 수정되었습니다. (^^)\n");
		} else {
			System.out.println("\n   수정에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelRoomStatusType() {
		int statusNo;
		
		HotelUICommon.menuStart("호텔 방 상태목록 삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 방 상태목록 삭제                │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   삭제하고자 하는 호텔 방 상태 번호 입력 : ");
		statusNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomStatusDAO().deleteHotelRoomStatusInfoByID(statusNo)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelRoomStatusType() {
		HotelUICommon.menuStart("호텔 방 상태목록 전체출력");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│       호텔 방 상태목록 전체출력             │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		
		List<RoomStatusInfo> typeList = HotelAdminBasicUI.getHotelRoomStatusDAO().getAllHotelRoomStatus();
		if (typeList.size() > 0) {
			for (int i=0; i<typeList.size(); i++) {
				System.out.println("번호 : " + typeList.get(i).getStatusNo()
							+ ", 호텔 방 상태 명 : " + typeList.get(i).getStatusStr());
			}
			
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회된 값이 없거나 조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
