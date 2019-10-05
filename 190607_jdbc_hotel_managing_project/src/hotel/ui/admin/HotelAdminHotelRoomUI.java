package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.HotelRoomInfo;

public class HotelAdminHotelRoomUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelRoomUI() {
		
	}
	
	public void startHotelRoomMenu() {
		HotelUICommon.menuListStart("호텔 방 관리");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelRoomMenu();
			selectHotelRoomMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelRoomMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            호텔 방 관리                    │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 호텔 방 추가");
		System.out.println("  10. 호텔 방 수정");
		System.out.println("  20. 호텔 방 삭제");
		System.out.println("  40. 호텔 방 검색(id)");
		System.out.println("  41. 호텔 방 검색(실제 호실)");
		System.out.println("  42. 호텔 방 검색(호텔별)");
		System.out.println("  43. 호텔 방 검색(타입별)");
		System.out.println("  44. 호텔 방 검색(상태별)");
		System.out.println("  50. 호텔 방 전체 출력");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelRoomMenu() {
		switch (choice) {
		case 1:
			// 호텔 방 추가
			insertHotelRoomInfo();
			break;
		case 10:
			// 호텔 방 수정
			updateHotelRoomInfo();
			break;
		case 20:
			// 호텔 방 삭제
			deleteHotelRoomInfo();
			break;
		case 40:
			// 호텔 방 검색(id)
			searchHotelRoomInfoByID();
			break;
		case 41:
			// 호텔 방 검색(실제 호실)
			searchHotelRoomInfoByRealPos();
			break;
		case 42:
			// 호텔 방 검색(호텔별)
			searchHotelRoomInfoByHotelID();
			break;
		case 43:
			// 호텔 방 검색(타입별)
			searchHotelRoomInfoByType();
			break;
		case 44:
			// 호텔 방 검색(상태별)
			searchHotelRoomInfoByStatus();
			break;
		case 50:
			// 호텔 방 전체 출력
			printAllHotelRoom();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelRoomInfo() {
		int hotelNo, managerNo, typeNo, statusNo, roomCount, roomRealPos;
		String roomSize;
		
		HotelUICommon.menuStart("호텔 방 추가");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            호텔 방 추가                    │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   1. 호텔 번호 입력 : ");
		hotelNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   호텔 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. 담당 매니저 번호 입력 : ");
		managerNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByID(managerNo) == null) {
			System.out.println("\n   매니저 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   3. 방 타입 번호 입력 : ");
		typeNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomTypeDAO().getHotelRoomTypeByID(typeNo) == null) {
			System.out.println("\n   타입 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 방 상태 번호 입력 : ");
		statusNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomStatusDAO().getHotelRoomStatusInfoByID(statusNo) == null) {
			System.out.println("\n   상태 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		sc.nextLine();
		System.out.print("   5. 방 크기 입력 : ");
		roomSize = sc.nextLine();
		
		System.out.print("   6. 방 개수 입력 : ");
		roomCount = sc.nextInt();
		System.out.print("   7. 방 실제 호실 입력 : ");
		roomRealPos = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDAO().insertHotelRoomInfo(hotelNo, managerNo, typeNo, statusNo, roomSize, roomCount, roomRealPos)) {
			System.out.println("\n   정상적으로 추가되었습니다. (^^)\n");
		} else {
			System.out.println("\n   추가에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void updateHotelRoomInfo() {
		int roomNo;
		int hotelNo, managerNo, typeNo, statusNo, roomCount, roomRealPos;
		String roomSize;
		
		HotelUICommon.menuStart("호텔 방 수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            호텔 방 수정                    │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   수정하고자 하는 방 번호 입력 : ");
		roomNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByID(roomNo) == null) {
			System.out.println("\n   호텔 방이 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		} else {
			System.out.println("\n   방 정보 수정을 시작합니다.\n");
		}
		
		System.out.print("   1. 호텔 번호 입력 : ");
		hotelNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   호텔 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. 담당 매니저 번호 입력 : ");
		managerNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByID(managerNo) == null) {
			System.out.println("\n   매니저 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   3. 방 타입 번호 입력 : ");
		typeNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelTypeDAO().getHotelTypeByID(typeNo) == null) {
			System.out.println("\n   타입 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 방 상태 번호 입력 : ");
		statusNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomStatusDAO().getHotelRoomStatusInfoByID(statusNo) == null) {
			System.out.println("\n   상태 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		sc.nextLine();
		System.out.print("   5. 방 크기 입력 : ");
		roomSize = sc.nextLine();
		
		System.out.print("   6. 방 개수 입력 : ");
		roomCount = sc.nextInt();
		System.out.print("   7. 방 실제 호실 입력 : ");
		roomRealPos = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDAO().updateHotelRoomInfo(roomNo, hotelNo, managerNo, typeNo, statusNo, roomSize, roomCount, roomRealPos)) {
			System.out.println("\n   정상적으로 추가되었습니다. (^^)\n");
		} else {
			System.out.println("\n   추가에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelRoomInfo() {
		int roomNo;
		HotelUICommon.menuStart("호텔 방 삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            호텔 방 삭제                    │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   삭제하고자 하는 방 번호 입력 : ");
		roomNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDAO().deleteHotelRoomInfoByID(roomNo)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelRoomInfoByID() {
		int roomNo;
		HotelUICommon.menuStart("호텔 방 검색(id)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 방 검색(id)        │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   검색하고자 하는 방 번호 입력 : ");
		roomNo = sc.nextInt();
		
		HotelRoomInfo resInfo = HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByID(roomNo);
		if (resInfo != null) {
			System.out.println();
			System.out.println("방 번호 : " + resInfo.getRoomNo() + ", 호텔 번호 : " + resInfo.getHotelNo()
				+ ", 담당 매니저 번호 : " + resInfo.getManagerNo() + ", 방 타입 번호 : " + resInfo.getRoomTypeNo()
				+ ", 방 상태 번호 : " + resInfo.getStatusNo() + ", 방 크기 : " + resInfo.getRoomSize()
				+ ", 방 개수 : " + resInfo.getRoomCnt() + ", 방 실제 호실 : " + resInfo.getRoomRealNo());
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelRoomInfoByRealPos() {
		int posNum;
		HotelUICommon.menuStart("호텔 방 검색(실제 호실)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 방 검색(실제 호실)     │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   검색하고자 하는 실제 호실 번호 입력 : ");
		posNum = sc.nextInt();
		
		List<HotelRoomInfo> resList = HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByRealPos(posNum);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("방 번호 : " + resList.get(i).getRoomNo() + ", 호텔 번호 : " + resList.get(i).getHotelNo()
					+ ", 담당 매니저 번호 : " + resList.get(i).getManagerNo() + ", 방 타입 번호 : " + resList.get(i).getRoomTypeNo()
					+ ", 방 상태 번호 : " + resList.get(i).getStatusNo() + ", 방 크기 : " + resList.get(i).getRoomSize()
					+ ", 방 개수 : " + resList.get(i).getRoomCnt() + ", 방 실제 호실 : " + resList.get(i).getRoomRealNo());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelRoomInfoByHotelID() {
		int hotelID;
		HotelUICommon.menuStart("호텔 방 검색(호텔별)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 방 검색(호텔별)      │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   검색하고자 하는 호텔 번호 입력 : ");
		hotelID = sc.nextInt();
		
		List<HotelRoomInfo> resList = HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByHotelID(hotelID);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("방 번호 : " + resList.get(i).getRoomNo() + ", 호텔 번호 : " + resList.get(i).getHotelNo()
					+ ", 담당 매니저 번호 : " + resList.get(i).getManagerNo() + ", 방 타입 번호 : " + resList.get(i).getRoomTypeNo()
					+ ", 방 상태 번호 : " + resList.get(i).getStatusNo() + ", 방 크기 : " + resList.get(i).getRoomSize()
					+ ", 방 개수 : " + resList.get(i).getRoomCnt() + ", 방 실제 호실 : " + resList.get(i).getRoomRealNo());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelRoomInfoByType() {
		int typeNo;
		HotelUICommon.menuStart("호텔 방 검색(타입별)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 방 검색(타입별)      │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   검색하고자 하는 방 타입 번호 입력 : ");
		typeNo = sc.nextInt();
		
		List<HotelRoomInfo> resList = HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByType(typeNo);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("방 번호 : " + resList.get(i).getRoomNo() + ", 호텔 번호 : " + resList.get(i).getHotelNo()
					+ ", 담당 매니저 번호 : " + resList.get(i).getManagerNo() + ", 방 타입 번호 : " + resList.get(i).getRoomTypeNo()
					+ ", 방 상태 번호 : " + resList.get(i).getStatusNo() + ", 방 크기 : " + resList.get(i).getRoomSize()
					+ ", 방 개수 : " + resList.get(i).getRoomCnt() + ", 방 실제 호실 : " + resList.get(i).getRoomRealNo());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelRoomInfoByStatus() {
		int statusId;
		HotelUICommon.menuStart("호텔 방 검색(상태별)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 방 검색(상태별)      │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   검색하고자 하는 방 상태 번호 입력 : ");
		statusId = sc.nextInt();
		
		List<HotelRoomInfo> resList = HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByStatus(statusId);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("방 번호 : " + resList.get(i).getRoomNo() + ", 호텔 번호 : " + resList.get(i).getHotelNo()
					+ ", 담당 매니저 번호 : " + resList.get(i).getManagerNo() + ", 방 타입 번호 : " + resList.get(i).getRoomTypeNo()
					+ ", 방 상태 번호 : " + resList.get(i).getStatusNo() + ", 방 크기 : " + resList.get(i).getRoomSize()
					+ ", 방 개수 : " + resList.get(i).getRoomCnt() + ", 방 실제 호실 : " + resList.get(i).getRoomRealNo());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelRoom() {
		HotelUICommon.menuStart("호텔 방 전체 출력");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 방 전체 출력                  │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		List<HotelRoomInfo> resList = HotelAdminBasicUI.getHotelRoomDAO().getAllHotelRoomInfo();
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("방 번호 : " + resList.get(i).getRoomNo() + ", 호텔 번호 : " + resList.get(i).getHotelNo()
					+ ", 담당 매니저 번호 : " + resList.get(i).getManagerNo() + ", 방 타입 번호 : " + resList.get(i).getRoomTypeNo()
					+ ", 방 상태 번호 : " + resList.get(i).getStatusNo() + ", 방 크기 : " + resList.get(i).getRoomSize()
					+ ", 방 개수 : " + resList.get(i).getRoomCnt() + ", 방 실제 호실 : " + resList.get(i).getRoomRealNo());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   현재 방이 존재하지 않습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
