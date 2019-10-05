package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;

import hotel.vo.HotelBrandInfo;

public class HotelAdminHotelBrandUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelBrandUI() {
		
	}
	
	public void startHotelBrandMenu() {
		HotelUICommon.menuListStart("호텔 브랜드목록 관리");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelBrandMenu();
			selectHotelBrandMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelBrandMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 브랜드목록 관리               │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 호텔 브랜드 추가");
		System.out.println("   2. 호텔 브랜드 검색(이름)");
		System.out.println("   3. 호텔 브랜드 수정(ID)");
		System.out.println("  10. 호텔 브랜드 삭제(ID)");
		System.out.println("  11. 호텔 브랜드 삭제(이름)");
		System.out.println("  20. 호텔 브랜드 전체출력");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelBrandMenu() {
		switch (choice) {
		case 1:
			insertHotelBrand();
			break;
		case 2:
			searchHotelBrandByName();
			break;
		case 3:
			modifyHotelBrandByID();
			break;
		case 10:
			deleteHotelBrandByID();
			break;
		case 11:
			deleteHotelBrandByName();
			break;
		case 20:
			printAllHotelBrand();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelBrand() {
		String brandName;
		//String address, ceoName;
		//int workerCnt;
		HotelUICommon.menuStart("호텔 브랜드 추가");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 브랜드 추가                   │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   추가하고자 하는 브랜드 명 입력 : ");
		brandName = sc.nextLine();
		//System.out.print("   2. 주소 입력 : ");
		//address = sc.nextLine();
		//System.out.print("   3. CEO 이름 입력 : ");
		//ceoName = sc.nextLine();
		//System.out.print("   4. 직원 수 입력 : ");
		//workerCnt = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelBrandDAO().insertBrandInfo(brandName, "", "", 0)) {
			System.out.println("\n   정상적으로 추가되었습니다. (^^)\n");
		} else {
			System.out.println("\n   추가에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelBrandByName() {
		String brandName;
		
		HotelUICommon.menuStart("호텔 브랜드 검색(이름)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 브랜드 검색(이름)      │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   조회하고자 하는 브랜드 명 입력 : ");
		brandName = sc.nextLine();
		
		HotelBrandInfo brandInfo = HotelAdminBasicUI.getHotelBrandDAO().getHotelBrandByName(brandName);
		if (brandInfo != null) {
			System.out.println();
			System.out.println("번호 : " + brandInfo.getBrandNo()
							+ ", 브랜드 명 : " + brandInfo.getBrandName()
							+ ", 브랜드 주소 : " + brandInfo.getAddress()
							+ ", CEO 이름 : " + brandInfo.getCeoName()
							+ ", 직원 수 : " + brandInfo.getWorkerCnt());
			
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelBrandByID() {
		int brandID;
		String brandName;
		
		HotelUICommon.menuStart("호텔 브랜드 수정(ID)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 브랜드 수정(ID)       │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   수정하고자 하는 브랜드 번호 입력 : ");
		brandID = sc.nextInt();
		sc.nextLine();
		System.out.print("   수정하고자 하는 브랜드 명 입력 : ");
		brandName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelBrandDAO().modifyHotelBrandByID(brandID, brandName, "", "", 0)) {
			System.out.println("\n   정상적으로 수정되었습니다. (^^)\n");
		} else {
			System.out.println("\n   수정에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelBrandByID() {
		int brandID;
		
		HotelUICommon.menuStart("호텔 브랜드 삭제(ID)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 브랜드 삭제(ID)       │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   삭제하고자 하는 브랜드 번호 입력 : ");
		brandID = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelBrandDAO().deleteHotelBrandByID(brandID)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelBrandByName() {
		String brandName;
		
		HotelUICommon.menuStart("호텔 브랜드 삭제(이름)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 브랜드 삭제(이름)      │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   삭제하고자 하는 브랜드 명 입력 : ");
		brandName = sc.nextLine();
		
		if (HotelAdminBasicUI.getHotelBrandDAO().deleteHotelBrandByName(brandName)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelBrand() {
		HotelUICommon.menuStart("호텔 브랜드 전체출력");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 브랜드 전체출력               │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		
		List<HotelBrandInfo> brandList = HotelAdminBasicUI.getHotelBrandDAO().getAllHotelBrand();
		if (brandList.size() > 0) {
			for (int i=0; i<brandList.size(); i++) {
				System.out.println("번호 : " + brandList.get(i).getBrandNo()
								+ ", 브랜드 명 : " + brandList.get(i).getBrandName()
								+ ", 브랜드 주소 : " + brandList.get(i).getAddress()
								+ ", CEO 이름 : " + brandList.get(i).getCeoName()
								+ ", 직원 수 : " + brandList.get(i).getWorkerCnt());
			}
			
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회된 값이 없거나 조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
