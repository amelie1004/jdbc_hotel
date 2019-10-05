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
		HotelUICommon.menuListStart("°í°´ °èÁ¤ °ü¸®");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showCustomerListMenu();
			selectCustomerListMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showCustomerListMenu() {
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢           °í°´ °èÁ¤ °ü¸®                    ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥\n");
		System.out.println("   1. °èÁ¤ °Ë»ö(Login_ID)");
		System.out.println("   2. °èÁ¤ °Ë»ö(NickName)");
		System.out.println("   3. °èÁ¤ °Ë»ö(Phone_Number)");
		System.out.println("   4. °èÁ¤ °Ë»ö(E-mail)");
		System.out.println("  10. °èÁ¤ ¼öÁ¤(Login_ID)");
		System.out.println("  20. °èÁ¤ ºí·Ï¼³Á¤(Login_ID)");
		System.out.println("  21. °èÁ¤ ºí·ÏÇØÁ¦(Login_ID)");
		System.out.println("  30. °èÁ¤ »èÁ¦(Login_ID)");
		System.out.println(" 100. µÚ·Î°¡±â");
		System.out.print("\n  ¸Þ´º¸¦ ¼±ÅÃÇØÁÖ¼¼¿ä : ");
		choice = sc.nextInt();
	}
	
	public void selectCustomerListMenu() {
		switch (choice) {
		case 1:
			// °èÁ¤ °Ë»ö(Login_ID) 
			searchCustomerByLoginID();
			break;
		case 2:
			// °èÁ¤ °Ë»ö(NickName)
			searchCustomerByNickName();
			break;
		case 3:
			// °èÁ¤ °Ë»ö(Phone_Number)
			searchCustomerByPhoneNum();
			break;
		case 4:
			// °èÁ¤ °Ë»ö(E-mail)
			searchCustomerByEmail();
			break;
		case 10:
			// °èÁ¤ ¼öÁ¤(Login_ID)
			modifyCustomerByID();
			break;
		case 20:
			// °èÁ¤ ºí·Ï¼³Á¤(Login_ID)
			blockCustomerByLoginID();
			break;
		case 21:
			// °èÁ¤ ºí·ÏÇØÁ¦(Login_ID)
			releaseCustomerByLoginID();
			break;
		case 30:
			// °èÁ¤ »èÁ¦(Login_ID)
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
		
		HotelUICommon.menuStart("°èÁ¤ °Ë»ö(id)");
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢          °èÁ¤ °Ë»ö (id)         ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥\n");
		
		System.out.print("   Ã£°íÀÚÇÏ´Â °èÁ¤ÀÇ ·Î±×ÀÎ ¾ÆÀÌµð ÀÔ·Â : ");
		loginID = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByID(loginID);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", ¾ÆÀÌµð : " + resInfo.getLoginId()
					+ ", ºñ¹Ð¹øÈ£ ¸¸·á : " + resInfo.getIsPasswdExpired()
					+ ", ´Ð³×ÀÓ : " + resInfo.getNickName()
					+ ", ÀÌ¸§ : " + resInfo.getFirstName()
					+ ", ¼º : " + resInfo.getLastName()
					+ ", ³ªÀÌ : " + resInfo.getAge() + "\n"
					+ "\tÀüÈ­¹øÈ£ : " + resInfo.getPhoneNumber()
					+ ", ¸¶Áö¸· ·Î±×ÀÎ ÀÏÀÚ : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   Á¤»óÀûÀ¸·Î °Ë»öµÇ¾ú½À´Ï´Ù. (^^)");
		} else {
			System.out.println("\n   ÇØ´çÇÏ´Â ¾ÆÀÌµð°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerByNickName() {
		String nickName = null;
		
		HotelUICommon.menuStart("°èÁ¤ °Ë»ö(NickName)");
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢       °èÁ¤ °Ë»ö (NickName)      ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥\n");
		
		sc = new Scanner(System.in);
		System.out.print("   Ã£°íÀÚÇÏ´Â °èÁ¤ÀÇ ´Ð³×ÀÓ ÀÔ·Â : ");
		nickName = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByNickName(nickName);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", ¾ÆÀÌµð : " + resInfo.getLoginId()
					+ ", ºñ¹Ð¹øÈ£ ¸¸·á : " + resInfo.getIsPasswdExpired()
					+ ", ´Ð³×ÀÓ : " + resInfo.getNickName()
					+ ", ÀÌ¸§ : " + resInfo.getFirstName()
					+ ", ¼º : " + resInfo.getLastName()
					+ ", ³ªÀÌ : " + resInfo.getAge() + "\n"
					+ "\tÀüÈ­¹øÈ£ : " + resInfo.getPhoneNumber()
					+ ", ¸¶Áö¸· ·Î±×ÀÎ ÀÏÀÚ : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   Á¤»óÀûÀ¸·Î °Ë»öµÇ¾ú½À´Ï´Ù. (^^)");
		} else {
			System.out.println("\n   ÇØ´çÇÏ´Â ´Ð³×ÀÓÀÌ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerByPhoneNum() {
		String phoneNum = null;
		
		HotelUICommon.menuStart("°èÁ¤ °Ë»ö(Phone_Number)");
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢     °èÁ¤ °Ë»ö (Phone_Number)    ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥\n");
		
		sc = new Scanner(System.in);
		System.out.print("   Ã£°íÀÚÇÏ´Â °èÁ¤ÀÇ ÀüÈ­¹øÈ£ ÀÔ·Â : ");
		phoneNum = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByPhoneNum(phoneNum);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", ¾ÆÀÌµð : " + resInfo.getLoginId()
					+ ", ºñ¹Ð¹øÈ£ ¸¸·á : " + resInfo.getIsPasswdExpired()
					+ ", ´Ð³×ÀÓ : " + resInfo.getNickName()
					+ ", ÀÌ¸§ : " + resInfo.getFirstName()
					+ ", ¼º : " + resInfo.getLastName()
					+ ", ³ªÀÌ : " + resInfo.getAge() + "\n"
					+ "\tÀüÈ­¹øÈ£ : " + resInfo.getPhoneNumber()
					+ ", ¸¶Áö¸· ·Î±×ÀÎ ÀÏÀÚ : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   Á¤»óÀûÀ¸·Î °Ë»öµÇ¾ú½À´Ï´Ù. (^^)");
		} else {
			System.out.println("\n   ÇØ´çÇÏ´Â ÀüÈ­¹øÈ£°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerByEmail() {
		String email = null;
		
		HotelUICommon.menuStart("°èÁ¤ °Ë»ö(E-mail)");
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢        °èÁ¤ °Ë»ö (E-mail)       ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥\n");
		
		sc = new Scanner(System.in);
		System.out.print("   Ã£°íÀÚÇÏ´Â °èÁ¤ÀÇ ÀÌ¸ÞÀÏ ÀÔ·Â : ");
		email = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByEmail(email);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", ¾ÆÀÌµð : " + resInfo.getLoginId()
					+ ", ºñ¹Ð¹øÈ£ ¸¸·á : " + resInfo.getIsPasswdExpired()
					+ ", ´Ð³×ÀÓ : " + resInfo.getNickName()
					+ ", ÀÌ¸§ : " + resInfo.getFirstName()
					+ ", ¼º : " + resInfo.getLastName()
					+ ", ³ªÀÌ : " + resInfo.getAge() + "\n"
					+ "\tÀüÈ­¹øÈ£ : " + resInfo.getPhoneNumber()
					+ ", ¸¶Áö¸· ·Î±×ÀÎ ÀÏÀÚ : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   Á¤»óÀûÀ¸·Î °Ë»öµÇ¾ú½À´Ï´Ù. (^^)");
		} else {
			System.out.println("\n   ÇØ´çÇÏ´Â ÀüÈ­¹øÈ£°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyCustomerByID() {
		String loginId, loginPasswd;
		String nickName, firstName, lastName;
		int age;
		String phoneNumber, email;
		
		HotelUICommon.menuStart("°èÁ¤ ¼öÁ¤(Login_ID)");
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢       °èÁ¤ ¼öÁ¤(Login_ID)      ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ¼öÁ¤ÇÏ°íÀÚ ÇÏ´Â °èÁ¤ÀÇ ¾ÆÀÌµð ÀÔ·Â : ");
		loginId = sc.next();
		
		if (HotelBasicUI.getCustomerDAO().getCustomerByID(loginId) == null) {
			System.out.println("\n   ¼öÁ¤ÇÏ°íÀÚ ÇÏ´Â ¾ÆÀÌµð°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}

		System.out.print("   1. ÆÐ½º¿öµå ÀÔ·Â : ");
		loginPasswd = sc.next();
		sc.nextLine();
		System.out.print("   2. ´Ð³×ÀÓ ÀÔ·Â : ");
		nickName = sc.nextLine();
		System.out.print("   3. ÀÌ¸§(First name) ÀÔ·Â : ");
		firstName = sc.nextLine();
		System.out.print("   4. ¼º(Last name) ÀÔ·Â : ");
		lastName = sc.nextLine();
		System.out.print("   5. ³ªÀÌ ÀÔ·Â : ");
		age = sc.nextInt();
		System.out.print("   6. ÀüÈ­¹øÈ£ ÀÔ·Â : ");
		phoneNumber = sc.next();
		System.out.print("   7. ÀÌ¸ÞÀÏ ÀÔ·Â : ");
		email = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().modifyCustomerById(loginId, loginPasswd, nickName,
				firstName, lastName, age, phoneNumber, email)) {
			System.out.println("\n   Á¤»óÀûÀ¸·Î µî·ÏµÇ¾ú½À´Ï´Ù. (^^)\n");
		} else {
			System.out.println("\n   µî·Ï¿¡ ½ÇÆÐÇÏ¿´½À´Ï´Ù.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	
	public void blockCustomerByLoginID() {
		String loginID = null;
		
		HotelUICommon.menuStart("°èÁ¤ ºí·Ï¼³Á¤(Login_ID)");
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢      °èÁ¤ ºí·Ï¼³Á¤(Login_ID)    ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ºí·ÏÇÏ°íÀÚ ÇÏ´Â °èÁ¤ÀÇ ¾ÆÀÌµð ÀÔ·Â : ");
		loginID = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().blockCustomerByLoginID(loginID, true)) {
			System.out.println("\n   Á¤»óÀûÀ¸·Î ºí·ÏÃ³¸® µÇ¾ú½À´Ï´Ù.");
		} else {
			System.out.println("\n   ÇØ´çÇÏ´Â ¾ÆÀÌµð°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void releaseCustomerByLoginID() {
		String loginID = null;
		
		HotelUICommon.menuStart("°èÁ¤ ºí·ÏÇØÁ¦(Login_ID)");
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢      °èÁ¤ ºí·ÏÇØÁ¦(Login_ID)    ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ºí·ÏÇØÁ¦ÇÏ°íÀÚ ÇÏ´Â °èÁ¤ÀÇ ¾ÆÀÌµð ÀÔ·Â : ");
		loginID = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().blockCustomerByLoginID(loginID, false)) {
			System.out.println("\n   Á¤»óÀûÀ¸·Î ºí·ÏÇØÁ¦Ã³¸® µÇ¾ú½À´Ï´Ù.");
		} else {
			System.out.println("\n   ÇØ´çÇÏ´Â ¾ÆÀÌµð°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteCustomerByLoginID() {
		String loginID = null;
		
		HotelUICommon.menuStart("°èÁ¤ »èÁ¦(Login_ID)");
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢       °èÁ¤ »èÁ¦(Login_ID)      ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥\n");
		
		sc = new Scanner(System.in);
		System.out.print("   »èÁ¦ÇÏ°íÀÚ ÇÏ´Â °èÁ¤ÀÇ ¾ÆÀÌµð ÀÔ·Â : ");
		loginID = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().deleteCustomerByLoginID(loginID)) {
			System.out.println("\n   Á¤»óÀûÀ¸·Î »èÁ¦Ã³¸® µÇ¾ú½À´Ï´Ù.");
		} else {
			System.out.println("\n   ÇØ´çÇÏ´Â ¾ÆÀÌµð°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
