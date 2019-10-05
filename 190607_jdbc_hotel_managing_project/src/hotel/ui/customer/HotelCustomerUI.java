package hotel.ui.customer;

import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.dao.customer.HotelCustomerDAO;

public class HotelCustomerUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelCustomerUI() {
		
	}
	
	// 1-1-(1) : 회원가입 메뉴
	public void insertCustomerList() {
		String loginId, loginPasswd;
		String nickName, firstName, lastName;
		int age;
		String phoneNumber, email;
		
		boolean isRun = true;
		
		HotelUICommon.menuStart("회원가입");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│             회원가입                       │");
		System.out.println("└─────────────────────────────┘\n");
		
		while (isRun) {
			sc = new Scanner(System.in);
			System.out.print("   1. 아이디 입력 : ");
			loginId = sc.next();
			System.out.print("   2. 패스워드 입력 : ");
			loginPasswd = sc.next();
			sc.nextLine();
			System.out.print("   3. 닉네임 입력 : ");
			nickName = sc.nextLine();
			System.out.print("   4. 이름(First name) 입력 : ");
			firstName = sc.nextLine();
			System.out.print("   5. 성(Last name) 입력 : ");
			lastName = sc.nextLine();
			System.out.print("   6. 나이 입력 : ");
			age = sc.nextInt();
			System.out.print("   7. 전화번호 입력 : ");
			phoneNumber = sc.next();
			System.out.print("   8. 이메일 입력 : ");
			email = sc.next();
			
			if (HotelBasicUI.getCustomerDAO().getCustomerByID(loginId) == null) {
				isRun = false;
				if (HotelBasicUI.getCustomerDAO().insertCustomer(loginId, loginPasswd, nickName,
						firstName, lastName, age, phoneNumber, email) > 0) {
					System.out.println("\n   정상적으로 등록되었습니다. (^^)\n");
				} else {
					System.out.println("\n   등록에 실패하였습니다.\n");
				}
			} else {
				System.out.println("\n   같은아이디가 존재합니다.\n");
			}
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	// 1-2-(1) : 로그인 메뉴
	public void loginCustomer() {
		String loginId, loginPasswd;
		
		HotelUICommon.menuStart("로그인");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│             로그인                          │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. 아이디 입력 : ");
		loginId = sc.next();
		System.out.print("   2. 패스워드 입력 : ");
		loginPasswd = sc.next();
		
		if (HotelBasicUI.getCustomerDAO().login(loginId, loginPasswd)) {
			System.out.println("\n   정상적으로 로그인되었습니다. (^^)\n");
		} else {
			System.out.println("\n   로그인에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	// 1-40-(2) : 로그아웃메뉴
	public void logoutCustomer() {
		HotelUICommon.menuStart("로그아웃");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│             로그아웃                       │");
		System.out.println("└─────────────────────────────┘\n");
		
		if (HotelBasicUI.getCustomerDAO().logout(HotelCustomerDAO.getLoginID())) {
			System.out.println("\n   정상적으로 로그아웃되었습니다. (^^)\n");
		} else {
			System.out.println("\n   로그아웃에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	// 1-50-(1) : 회원탈퇴 (로그아웃 상태)
	public void deleteCustomerInLogOff() {
		String loginID, passwd;
		
		HotelUICommon.menuStart("회원탈퇴");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│             회원탈퇴                       │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   1. 탈퇴하고자 하는 아이디 입력 : ");
		loginID = sc.next();
		System.out.print("   2. 비밀번호 입력 : ");
		passwd = sc.nextLine();
		
		if (HotelBasicUI.getCustomerDAO().deleteCustomerByIDAndPasswd(loginID, passwd) > 0) {
			System.out.println("\n   회원 탈퇴가 완료되었습니다. (^^)\n");
		} else {
			System.out.println("\n   회원 탈퇴에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	// 1-50-(2) : 회원탈퇴 (로그인 상태)
	public void deleteCustomerInLogOn() {
		String answer;
		
		HotelUICommon.menuStart("회원탈퇴");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│             회원탈퇴                       │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   현재 로그인된 계정으로 정말 탈퇴하시겠습니까? (Y/N)");
		answer = sc.next();
		
		if (answer.equals("Y") || answer.equals("y")) {
			if (HotelBasicUI.getCustomerDAO().deleteCustomerByID(HotelCustomerDAO.getLoginID()) > 0) {
				System.out.println("\n   회원 탈퇴가 완료되었습니다. (^^)\n");
			} else {
				System.out.println("\n   회원 탈퇴에 실패하였습니다.\n");
			}
		} else {
			System.out.println("\n   회원 탈퇴를 취소하셨습니다.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
