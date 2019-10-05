package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.HotelManagerInfo;

public class HotelAdminHotelManagerUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelManagerUI() {
		
	}
	
	public void startHotelManagerMenu() {
		HotelUICommon.menuListStart("호텔 매니저 관리");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelManagerMenu();
			selectHotelManagerMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelManagerMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 매니저 관리                   │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 호텔 매니저 추가");
		System.out.println("  10. 호텔 매니저 수정");
		System.out.println("  20. 호텔 매니저 삭제");
		System.out.println("  30. 호텔 매니저 조회(ID)");
		System.out.println("  31. 호텔 매니저 조회(Login_id)");
		System.out.println("  32. 호텔 매니저 조회(이름)");
		System.out.println("  33. 호텔 매니저 조회(전화번호)");
		System.out.println("  34. 호텔 매니저 조회(E-mail)");
		System.out.println("  40. 호텔 매니저 전체 출력");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelManagerMenu() {
		switch (choice) {
		case 1:
			// 호텔 매니저 추가
			insertHotelManager();
			break;
		case 10:
			// 호텔 매니저 수정
			modifyHotelManager();
			break;
		case 20:
			// 호텔 매니저 삭제
			deleteHotelManager();
			break;
		case 30:
			// 호텔 매니저 조회(ID)
			searchHotelManagerByID();
			break;
		case 31:
			// 호텔 매니저 조회(Login_id)
			searchHotelManagerByLoginID();
			break;
		case 32:
			// 호텔 매니저 조회(이름)
			searchHotelManagerByName();
			break;
		case 33:
			// 호텔 매니저 조회(전화번호)
			searchHotelManagerByTelephone();
			break;
		case 34:
			// 호텔 매니저 조회(E-mail)
			searchHotelManagerByEmail();
			break;
		case 40:
			// 호텔 매니저 전체 출력
			printAllHotelManager();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelManager() {
		int hotelNo, bossManagerNo;
		String loginID, loginPasswd;
		String firstName, lastName;
		String telephone, email;
		
		HotelUICommon.menuStart("호텔 매니저 추가");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 매니저 추가                   │");
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
		
		System.out.print("   2. 로그인 아이디 입력 : ");
		loginID = sc.next();
		if (HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByLoginID(loginID) != null) {
			System.out.println("\n   로그인 아이디가 이미 존재합니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   3. 로그인 패스워드 입력 : ");
		loginPasswd = sc.next();
		sc.nextLine();
		System.out.print("   4. 이름(First name) 입력 : ");
		firstName = sc.nextLine();
		System.out.print("   5. 성(Last name) 입력 : ");
		lastName = sc.nextLine();
		System.out.print("   6. 전화번호 입력 : ");
		telephone = sc.next();
		System.out.print("   7. 이메일 입력 : ");
		email = sc.next();
		System.out.print("   8. 관리자 번호 입력(없으면 0) : ");
		bossManagerNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelManagerDAO().insertHotelManagerInfo(hotelNo, loginID, loginPasswd, firstName, lastName, telephone, email, bossManagerNo)) {
			System.out.println("\n   정상적으로 추가되었습니다. (^^)\n");
		} else {
			System.out.println("\n   추가에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelManager() {
		int managerNo, hotelNo, bossManagerNo;
		String loginID, loginPasswd;
		String firstName, lastName;
		String telephone, email;
		
		HotelUICommon.menuStart("호텔 매니저 수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 매니저 수정                   │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. 매니저 번호 입력 : ");
		managerNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByID(managerNo) == null) {
			System.out.println("\n   매니저 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. 호텔 번호 입력 : ");
		hotelNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   호텔 번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   3. 로그인 아이디 입력 : ");
		loginID = sc.next();
		if (HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByLoginID(loginID) != null) {
			System.out.println("\n   로그인 아이디가 이미 존재합니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 로그인 패스워드 입력 : ");
		loginPasswd = sc.next();
		System.out.print("   5. 이름(First name) 입력 : ");
		firstName = sc.next();
		System.out.print("   6. 성(Last name) 입력 : ");
		lastName = sc.next();
		System.out.print("   7. 전화번호 입력 : ");
		telephone = sc.next();
		System.out.print("   8. 이메일 입력 : ");
		email = sc.next();
		System.out.print("   9. 관리자 번호 입력(없으면 0) : ");
		bossManagerNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelManagerDAO().updateHotelManagerInfo(managerNo, hotelNo, loginID, loginPasswd, firstName, lastName, telephone, email, bossManagerNo)) {
			System.out.println("\n   정상적으로 추가되었습니다. (^^)\n");
		} else {
			System.out.println("\n   추가에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelManager() {
		int managerNo;
		
		HotelUICommon.menuStart("호텔 매니저 삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          호텔 매니저 삭제                   │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   삭제 하고자 하는 매니저 번호 입력 : ");
		managerNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelManagerDAO().deleteHotelManagerByID(managerNo)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelManagerByID() {
		int managerNo;
		
		HotelUICommon.menuStart("호텔 매니저 조회(ID)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        호텔 매니저 조회(ID)       │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   조회 하고자 하는 매니저 번호 입력 : ");
		managerNo = sc.nextInt();
		
		HotelManagerInfo resInfo = HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByID(managerNo);
		if (resInfo != null) {
			System.out.println();
			System.out.println("매니저 번호 : " + resInfo.getManagerNo()
					+ ", 아이디 : " + resInfo.getManagerId() + ", 소속호텔번호 : " + resInfo.getHotelNo()
					+ ", 이름 : " + resInfo.getFirstName() + ", 성 : " + resInfo.getLastName()
					+ ", 전화번호 : " + resInfo.getTelephoneNum() + ", 이메일 : " + resInfo.getEmail()
					+ ", 관리매니저 번호 : " + resInfo.getBossManagerNo() + "\n");
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelManagerByLoginID() {
		String loginID;
		
		HotelUICommon.menuStart("호텔 매니저 조회(Login_id)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│     호텔 매니저 조회(Login_id)    │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   조회 하고자 하는 매니저 아이디 입력 : ");
		loginID = sc.next();
		
		HotelManagerInfo resInfo = HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByLoginID(loginID);
		if (resInfo != null) {
			System.out.println();
			System.out.println("매니저 번호 : " + resInfo.getManagerNo()
					+ ", 아이디 : " + resInfo.getManagerId() + ", 소속호텔번호 : " + resInfo.getHotelNo()
					+ ", 이름 : " + resInfo.getFirstName() + ", 성 : " + resInfo.getLastName()
					+ ", 전화번호 : " + resInfo.getTelephoneNum() + ", 이메일 : " + resInfo.getEmail()
					+ ", 관리매니저 번호 : " + resInfo.getBossManagerNo() + "\n");
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelManagerByName() {
		String nameStr;
		
		HotelUICommon.menuStart("호텔 매니저 조회(이름)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│       호텔 매니저 조회(이름)       │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   조회 하고자 하는 매니저 이름 입력 : ");
		nameStr = sc.nextLine();
		
		List<HotelManagerInfo> resList = HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByName(nameStr);
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("매니저 번호 : " + resList.get(i).getManagerNo()
						+ ", 아이디 : " + resList.get(i).getManagerId() + ", 소속호텔번호 : " + resList.get(i).getHotelNo()
						+ ", 이름 : " + resList.get(i).getFirstName() + ", 성 : " + resList.get(i).getLastName()
						+ ", 전화번호 : " + resList.get(i).getTelephoneNum() + ", 이메일 : " + resList.get(i).getEmail()
						+ ", 관리매니저 번호 : " + resList.get(i).getBossManagerNo());
			}
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelManagerByTelephone() {
		String telephone;
		
		HotelUICommon.menuStart("호텔 매니저 조회(전화번호)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│      호텔 매니저 조회(전화번호)     │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   조회 하고자 하는 전화번호 입력 : ");
		telephone = sc.next();
		
		HotelManagerInfo resInfo = HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByTelephone(telephone);
		if (resInfo != null) {
			System.out.println();
			System.out.println("매니저 번호 : " + resInfo.getManagerNo()
					+ ", 아이디 : " + resInfo.getManagerId() + ", 소속호텔번호 : " + resInfo.getHotelNo()
					+ ", 이름 : " + resInfo.getFirstName() + ", 성 : " + resInfo.getLastName()
					+ ", 전화번호 : " + resInfo.getTelephoneNum() + ", 이메일 : " + resInfo.getEmail()
					+ ", 관리매니저 번호 : " + resInfo.getBossManagerNo() + "\n");
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelManagerByEmail() {
		String email;
		
		HotelUICommon.menuStart("호텔 매니저 조회(E-mail)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│      호텔 매니저 조회(E-mail)     │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   조회 하고자 하는 이메일 입력 : ");
		email = sc.next();
		
		HotelManagerInfo resInfo = HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByEmail(email);
		if (resInfo != null) {
			System.out.println();
			System.out.println("매니저 번호 : " + resInfo.getManagerNo()
					+ ", 아이디 : " + resInfo.getManagerId() + ", 소속호텔번호 : " + resInfo.getHotelNo()
					+ ", 이름 : " + resInfo.getFirstName() + ", 성 : " + resInfo.getLastName()
					+ ", 전화번호 : " + resInfo.getTelephoneNum() + ", 이메일 : " + resInfo.getEmail()
					+ ", 관리매니저 번호 : " + resInfo.getBossManagerNo() + "\n");
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelManager() {
		HotelUICommon.menuStart("호텔 매니저 전체 출력");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         호텔 매니저 전체 출력              │");
		System.out.println("└─────────────────────────────┘\n");
		
		List<HotelManagerInfo> resList = HotelAdminBasicUI.getHotelManagerDAO().getAllHotelManager();
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("매니저 번호 : " + resList.get(i).getManagerNo()
						+ ", 아이디 : " + resList.get(i).getManagerId() + ", 소속호텔번호 : " + resList.get(i).getHotelNo()
						+ ", 이름 : " + resList.get(i).getFirstName() + ", 성 : " + resList.get(i).getLastName()
						+ ", 전화번호 : " + resList.get(i).getTelephoneNum() + ", 이메일 : " + resList.get(i).getEmail()
						+ ", 관리매니저 번호 : " + resList.get(i).getBossManagerNo());
			}
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
