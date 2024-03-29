package hotel.ui.admin;

import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.ui.customer.HotelBasicUI;
import hotel.vo.CustomerInfo;

public class HotelAdminCustomerUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminCustomerUI() {
		
	}
	
	public void startCustomerListMenu() {
		HotelUICommon.menuListStart("堅偌 啗薑 婦葬");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showCustomerListMenu();
			selectCustomerListMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showCustomerListMenu() {
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛           堅偌 啗薑 婦葬                    弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		System.out.println("   1. 啗薑 匐儀(Login_ID)");
		System.out.println("   2. 啗薑 匐儀(NickName)");
		System.out.println("   3. 啗薑 匐儀(Phone_Number)");
		System.out.println("   4. 啗薑 匐儀(E-mail)");
		System.out.println("  10. 啗薑 熱薑(Login_ID)");
		System.out.println("  20. 啗薑 綰煙撲薑(Login_ID)");
		System.out.println("  21. 啗薑 綰煙п薯(Login_ID)");
		System.out.println("  30. 啗薑 餉薯(Login_ID)");
		System.out.println(" 100. 菴煎陛晦");
		System.out.print("\n  詭景蒂 摹鷗п輿撮蹂 : ");
		choice = sc.nextInt();
	}
	
	public void selectCustomerListMenu() {
		switch (choice) {
		case 1:
			// 啗薑 匐儀(Login_ID) 
			searchCustomerByLoginID();
			break;
		case 2:
			// 啗薑 匐儀(NickName)
			searchCustomerByNickName();
			break;
		case 3:
			// 啗薑 匐儀(Phone_Number)
			searchCustomerByPhoneNum();
			break;
		case 4:
			// 啗薑 匐儀(E-mail)
			searchCustomerByEmail();
			break;
		case 10:
			// 啗薑 熱薑(Login_ID)
			modifyCustomerByID();
			break;
		case 20:
			// 啗薑 綰煙撲薑(Login_ID)
			blockCustomerByLoginID();
			break;
		case 21:
			// 啗薑 綰煙п薯(Login_ID)
			releaseCustomerByLoginID();
			break;
		case 30:
			// 啗薑 餉薯(Login_ID)
			deleteCustomerByLoginID();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void searchCustomerByLoginID() {
		String loginID = null;
		
		HotelUICommon.menuStart("啗薑 匐儀(id)");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛          啗薑 匐儀 (id)         弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		System.out.print("   瓊堅濠ж朝 啗薑曖 煎斜檣 嬴檜蛤 殮溘 : ");
		loginID = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByID(loginID);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", 嬴檜蛤 : " + resInfo.getLoginId()
					+ ", 綠塵廓�� 虜猿 : " + resInfo.getIsPasswdExpired()
					+ ", 棣啻歜 : " + resInfo.getNickName()
					+ ", 檜葷 : " + resInfo.getFirstName()
					+ ", 撩 : " + resInfo.getLastName()
					+ ", 釭檜 : " + resInfo.getAge() + "\n"
					+ "\t瞪�食醽� : " + resInfo.getPhoneNumber()
					+ ", 葆雖虞 煎斜檣 橾濠 : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   薑鼻瞳戲煎 匐儀腎歷蝗棲棻. (^^)");
		} else {
			System.out.println("\n   п渡ж朝 嬴檜蛤陛 襄營ж雖 彊蝗棲棻.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerByNickName() {
		String nickName = null;
		
		HotelUICommon.menuStart("啗薑 匐儀(NickName)");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛       啗薑 匐儀 (NickName)      弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		System.out.print("   瓊堅濠ж朝 啗薑曖 棣啻歜 殮溘 : ");
		nickName = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByNickName(nickName);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", 嬴檜蛤 : " + resInfo.getLoginId()
					+ ", 綠塵廓�� 虜猿 : " + resInfo.getIsPasswdExpired()
					+ ", 棣啻歜 : " + resInfo.getNickName()
					+ ", 檜葷 : " + resInfo.getFirstName()
					+ ", 撩 : " + resInfo.getLastName()
					+ ", 釭檜 : " + resInfo.getAge() + "\n"
					+ "\t瞪�食醽� : " + resInfo.getPhoneNumber()
					+ ", 葆雖虞 煎斜檣 橾濠 : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   薑鼻瞳戲煎 匐儀腎歷蝗棲棻. (^^)");
		} else {
			System.out.println("\n   п渡ж朝 棣啻歜檜 襄營ж雖 彊蝗棲棻.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerByPhoneNum() {
		String phoneNum = null;
		
		HotelUICommon.menuStart("啗薑 匐儀(Phone_Number)");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛     啗薑 匐儀 (Phone_Number)    弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		System.out.print("   瓊堅濠ж朝 啗薑曖 瞪�食醽� 殮溘 : ");
		phoneNum = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByPhoneNum(phoneNum);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", 嬴檜蛤 : " + resInfo.getLoginId()
					+ ", 綠塵廓�� 虜猿 : " + resInfo.getIsPasswdExpired()
					+ ", 棣啻歜 : " + resInfo.getNickName()
					+ ", 檜葷 : " + resInfo.getFirstName()
					+ ", 撩 : " + resInfo.getLastName()
					+ ", 釭檜 : " + resInfo.getAge() + "\n"
					+ "\t瞪�食醽� : " + resInfo.getPhoneNumber()
					+ ", 葆雖虞 煎斜檣 橾濠 : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   薑鼻瞳戲煎 匐儀腎歷蝗棲棻. (^^)");
		} else {
			System.out.println("\n   п渡ж朝 瞪�食醽ㄟ� 襄營ж雖 彊蝗棲棻.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerByEmail() {
		String email = null;
		
		HotelUICommon.menuStart("啗薑 匐儀(E-mail)");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛        啗薑 匐儀 (E-mail)       弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		System.out.print("   瓊堅濠ж朝 啗薑曖 檜詭橾 殮溘 : ");
		email = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByEmail(email);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", 嬴檜蛤 : " + resInfo.getLoginId()
					+ ", 綠塵廓�� 虜猿 : " + resInfo.getIsPasswdExpired()
					+ ", 棣啻歜 : " + resInfo.getNickName()
					+ ", 檜葷 : " + resInfo.getFirstName()
					+ ", 撩 : " + resInfo.getLastName()
					+ ", 釭檜 : " + resInfo.getAge() + "\n"
					+ "\t瞪�食醽� : " + resInfo.getPhoneNumber()
					+ ", 葆雖虞 煎斜檣 橾濠 : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   薑鼻瞳戲煎 匐儀腎歷蝗棲棻. (^^)");
		} else {
			System.out.println("\n   п渡ж朝 瞪�食醽ㄟ� 襄營ж雖 彊蝗棲棻.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyCustomerByID() {
		String loginId, loginPasswd;
		String nickName, firstName, lastName;
		int age;
		String phoneNumber, email;
		
		HotelUICommon.menuStart("啗薑 熱薑(Login_ID)");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛       啗薑 熱薑(Login_ID)      弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		System.out.print("   熱薑ж堅濠 ж朝 啗薑曖 嬴檜蛤 殮溘 : ");
		loginId = sc.next();
		
		if (HotelBasicUI.getCustomerDAO().getCustomerByID(loginId) == null) {
			System.out.println("\n   熱薑ж堅濠 ж朝 嬴檜蛤陛 襄營ж雖 彊蝗棲棻.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}

		System.out.print("   1. ぬ蝶錶萄 殮溘 : ");
		loginPasswd = sc.next();
		sc.nextLine();
		System.out.print("   2. 棣啻歜 殮溘 : ");
		nickName = sc.nextLine();
		System.out.print("   3. 檜葷(First name) 殮溘 : ");
		firstName = sc.nextLine();
		System.out.print("   4. 撩(Last name) 殮溘 : ");
		lastName = sc.nextLine();
		System.out.print("   5. 釭檜 殮溘 : ");
		age = sc.nextInt();
		System.out.print("   6. 瞪�食醽� 殮溘 : ");
		phoneNumber = sc.next();
		System.out.print("   7. 檜詭橾 殮溘 : ");
		email = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().modifyCustomerById(loginId, loginPasswd, nickName,
				firstName, lastName, age, phoneNumber, email)) {
			System.out.println("\n   薑鼻瞳戲煎 蛔煙腎歷蝗棲棻. (^^)\n");
		} else {
			System.out.println("\n   蛔煙縑 褒ぬж艘蝗棲棻.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	
	public void blockCustomerByLoginID() {
		String loginID = null;
		
		HotelUICommon.menuStart("啗薑 綰煙撲薑(Login_ID)");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛      啗薑 綰煙撲薑(Login_ID)    弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		System.out.print("   綰煙ж堅濠 ж朝 啗薑曖 嬴檜蛤 殮溘 : ");
		loginID = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().blockCustomerByLoginID(loginID, true)) {
			System.out.println("\n   薑鼻瞳戲煎 綰煙籀葬 腎歷蝗棲棻.");
		} else {
			System.out.println("\n   п渡ж朝 嬴檜蛤陛 襄營ж雖 彊蝗棲棻.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void releaseCustomerByLoginID() {
		String loginID = null;
		
		HotelUICommon.menuStart("啗薑 綰煙п薯(Login_ID)");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛      啗薑 綰煙п薯(Login_ID)    弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		System.out.print("   綰煙п薯ж堅濠 ж朝 啗薑曖 嬴檜蛤 殮溘 : ");
		loginID = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().blockCustomerByLoginID(loginID, false)) {
			System.out.println("\n   薑鼻瞳戲煎 綰煙п薯籀葬 腎歷蝗棲棻.");
		} else {
			System.out.println("\n   п渡ж朝 嬴檜蛤陛 襄營ж雖 彊蝗棲棻.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteCustomerByLoginID() {
		String loginID = null;
		
		HotelUICommon.menuStart("啗薑 餉薯(Login_ID)");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛       啗薑 餉薯(Login_ID)      弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		System.out.print("   餉薯ж堅濠 ж朝 啗薑曖 嬴檜蛤 殮溘 : ");
		loginID = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().deleteCustomerByLoginID(loginID)) {
			System.out.println("\n   薑鼻瞳戲煎 餉薯籀葬 腎歷蝗棲棻.");
		} else {
			System.out.println("\n   п渡ж朝 嬴檜蛤陛 襄營ж雖 彊蝗棲棻.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
