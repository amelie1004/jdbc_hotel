package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.HotelTypeInfo;

public class HotelAdminHotelTypeUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelTypeUI() {
		
	}
	
	public void startHotelTypeMenu() {
		HotelUICommon.menuListStart("호텔 타입목록 관리");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelTypeMenu();
			selectHotelTypeMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelTypeMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 타입목록 관리                │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 호텔 타입 추가");
		System.out.println("   2. 호텔 타입 수정");
		System.out.println("  10. 호텔 타입 삭제");
		System.out.println("  20. 호텔 타입 전체출력");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelTypeMenu() {
		switch (choice) {
		case 1:
			// 호텔 타입 추가
			insertHotelType();
			break;
		case 2:
			// 호텔 타입 수정
			modifyHotelType();
			break;
		case 10:
			// 호텔 타입 삭제
			deleteHotelType();
			break;
		case 20:
			// 호텔 타입 전체출력
			printAllHotelType();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelType() {
		int typeNo;
		String typeName;
		//String address, ceoName;
		//int workerCnt;
		HotelUICommon.menuStart("호텔 타입 추가");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│           호텔 타입 추가                    │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   1. 호텔 타입 번호 입력 : ");
		typeNo = sc.nextInt();
		sc.nextLine();
		System.out.print("   2. 호텔 타입 명 입력 : ");
		typeName = sc.nextLine();
		//System.out.print("   2. 주소 입력 : ");
		//address = sc.nextLine();
		//System.out.print("   3. CEO 이름 입력 : ");
		//ceoName = sc.nextLine();
		//System.out.print("   4. 직원 수 입력 : ");
		//workerCnt = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelTypeDAO().insertHotelTypeInfo(typeNo, typeName)) {
			System.out.println("\n   정상적으로 추가되었습니다. (^^)\n");
		} else {
			System.out.println("\n   추가에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelType() {
		int typeID;
		String typeName;
		
		HotelUICommon.menuStart("호텔 타입 수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│           호텔 타입 수정                    │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   수정하고자 하는 호텔타입 번호 입력 : ");
		typeID = sc.nextInt();
		sc.nextLine();
		System.out.print("   수정하고자 하는 호텔타입 명 입력 : ");
		typeName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelTypeDAO().modifyHotelTypeInfoByID(typeID, typeName)) {
			System.out.println("\n   정상적으로 수정되었습니다. (^^)\n");
		} else {
			System.out.println("\n   수정에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelType() {
		int typeID;
		
		HotelUICommon.menuStart("호텔 타입 삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│           호텔 타입 삭제                    │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   삭제하고자 하는 호텔타입 번호 입력 : ");
		typeID = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelTypeDAO().deleteHotelTypeInfoByID(typeID)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelType() {
		HotelUICommon.menuStart("호텔 브랜드 전체출력");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 타입 전체출력                  │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		
		List<HotelTypeInfo> typeList = HotelAdminBasicUI.getHotelTypeDAO().getAllHotelType();
		if (typeList.size() > 0) {
			for (int i=0; i<typeList.size(); i++) {
				System.out.println("번호 : " + typeList.get(i).getTypeNo()
								+ ", 호텔 타입 명 : " + typeList.get(i).getTypeStr());
			}
			
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회된 값이 없거나 조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
