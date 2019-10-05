package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.CustomerClassInfo;

public class HotelAdminCustomerClassUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminCustomerClassUI() {
		
	}
	
	public void startCustomerClassMenu() {
		HotelUICommon.menuListStart("CustomerClass 관리");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showCustomerClassMenu();
			selectCustomerClassMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showCustomerClassMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│       Customer Class        │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 정보등록");
		System.out.println("   2. 정보삭제");
		System.out.println("   3. 정보수정");
		System.out.println("  20. 정보조회(전체)");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectCustomerClassMenu() {
		switch (choice) {
		case 1:
			insertCustomerClass();
			break;
		case 2:
			deleteCustomerClass();
			break;
		case 3:
			modifyCustomerClass();
			break;
		case 20:
			searchAllCustomerClass();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertCustomerClass() {
		String className;
		int classNo, discountRate;
		
		HotelUICommon.menuStart("정보등록");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│             정보등록                       │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. Class 번호 입력 : ");
		classNo = sc.nextInt();
		System.out.print("   2. Class 이름 입력 : ");
		className = sc.nextLine();
		System.out.print("   3. Class 할인율 입력 : ");
		discountRate = sc.nextInt();
		
		if (HotelAdminBasicUI.getCustomerClassDAO().insertClass(classNo, className, discountRate) > 0) {
			System.out.println("\n   정상적으로 등록되었습니다. (^^)");
		} else {
			System.out.println("\n   등록에 실패하였습니다.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteCustomerClass() {
		int classNo;
		
		HotelUICommon.menuStart("정보삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│             정보삭제                       │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   삭제하고자 하는 Class의 번호 입력 : ");
		classNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getCustomerClassDAO().deleteCustomerClass(classNo) > 0) {
			System.out.println("\n   삭제에 성공하였습니다.");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyCustomerClass() {
		String className;
		int classNo, discountRate;
		
		HotelUICommon.menuStart("정보수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│             정보수정                       │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. Class 번호 입력 : ");
		classNo = sc.nextInt();
		System.out.print("   2. 변경하고자 하는 Class 이름 입력 : ");
		className = sc.nextLine();
		System.out.print("   3. 변경하고자 하는 Class 할인율 입력 : ");
		discountRate = sc.nextInt();
		
		CustomerClassInfo countryInfo = new CustomerClassInfo(classNo, className, discountRate);
		
		if (HotelAdminBasicUI.getCustomerClassDAO().updateCustomerClass(countryInfo) > 0) {
			System.out.println("\n   정상적으로 수정되었습니다. (^^)");
		} else {
			System.out.println("\n   수정에 실패하였습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchAllCustomerClass() {
		HotelUICommon.menuStart("정보조회(전체)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          정보조회(전체)         │");
		System.out.println("└─────────────────────────────┘\n");
		
		List<CustomerClassInfo> resList = HotelAdminBasicUI.getCustomerClassDAO().getAllData();
		
		if (resList != null) {
			for (int i=0; i<resList.size(); i++) {
				System.out.printf("No : %d\t, Name : %s\t\t, Discount Rate : %d\n",
						resList.get(i).getClassNo(),
						resList.get(i).getClassName(),
						resList.get(i).getDiscountRate());
			}
			System.out.println();
		} else {
			System.out.println("\n   데이터가 존재하지 않습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
