package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.RoomTypeInfo;

public class HotelAdminHotelRoomTypeUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelRoomTypeUI() {
		
	}
	
	public void startHotelRoomTypeMenu() {
		HotelUICommon.menuListStart("호텔 방 타입목록 관리");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelRoomTypeMenu();
			selectHotelRoomTypeMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelRoomTypeMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 방 타입목록 관리              │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 호텔 방 타입 추가");
		System.out.println("   2. 호텔 방 타입 수정");
		System.out.println("  10. 호텔 방 타입 삭제");
		System.out.println("  20. 호텔 방 타입 전체출력");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelRoomTypeMenu() {
		switch (choice) {
		case 1:
			// 호텔 방 타입 추가
			insertHotelRoomType();
			break;
		case 2:
			// 호텔 방 타입 수정
			modifyHotelRoomType();
			break;
		case 10:
			// 호텔 방 타입 삭제
			deleteHotelRoomType();
			break;
		case 20:
			// 호텔 방 타입 전체출력
			printAllHotelRoomType();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelRoomType() {
		int typeNo;
		String typeName;
		HotelUICommon.menuStart("호텔 방 타입 추가");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 방 타입 추가                  │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   1. 호텔 방 타입 번호 입력 : ");
		typeNo = sc.nextInt();
		sc.nextLine();
		System.out.print("   2. 호텔 방 타입 명 입력 : ");
		typeName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelRoomTypeDAO().insertHotelRoomTypeInfo(typeNo, typeName)) {
			System.out.println("\n   정상적으로 추가되었습니다. (^^)\n");
		} else {
			System.out.println("\n   추가에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelRoomType() {
		int typeID;
		String typeName;
		
		HotelUICommon.menuStart("호텔 방 타입 수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 방 타입 수정                  │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   수정하고자 하는 호텔 방 타입 번호 입력 : ");
		typeID = sc.nextInt();
		sc.nextLine();
		System.out.print("   수정하고자 하는 호텔 방 타입 명 입력 : ");
		typeName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelRoomTypeDAO().modifyHotelRoomTypeInfoByID(typeID, typeName)) {
			System.out.println("\n   정상적으로 수정되었습니다. (^^)\n");
		} else {
			System.out.println("\n   수정에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelRoomType() {
		int typeID;
		
		HotelUICommon.menuStart("호텔 방 타입 삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 방 타입 삭제                  │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   삭제하고자 하는 호텔타입 번호 입력 : ");
		typeID = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomTypeDAO().deleteHotelRoomTypeInfoByID(typeID)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelRoomType() {
		HotelUICommon.menuStart("호텔 방 타입 전체출력");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 방 타입 전체출력                │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		
		List<RoomTypeInfo> typeList = HotelAdminBasicUI.getHotelRoomTypeDAO().getAllHotelRoomType();
		if (typeList.size() > 0) {
			for (int i=0; i<typeList.size(); i++) {
				System.out.println("번호 : " + typeList.get(i).getTypeNo()
							+ ", 호텔 방 타입 명 : " + typeList.get(i).getTypeStr());
			}
			
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회된 값이 없거나 조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
