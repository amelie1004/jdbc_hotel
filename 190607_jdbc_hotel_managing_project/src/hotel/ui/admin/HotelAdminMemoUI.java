package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.MemoInfo;

public class HotelAdminMemoUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminMemoUI() {
		
	}
	
	public void startMemoMenu() {
		HotelUICommon.menuListStart("고객 메모 관리");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showMemoMenu();
			selectMemoMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showMemoMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│           고객 메모 관리                    │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   2. 메모삭제");
		System.out.println("  10. 메모조회(id)");
		System.out.println("  20. 메모조회(전체)");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectMemoMenu() {
		switch (choice) {
		case 2:
			deleteCustomerMemo();
			break;
		case 10:
			searchCustomerMemoByID();
			break;
		case 20:
			searchAllCustomerMemo();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void deleteCustomerMemo() {
		int memoNo;
		
		HotelUICommon.menuStart("메모삭제");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            메모삭제                          │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   삭제하고자 하는 글 번호 입력 : ");
		memoNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getMemoDAO().deleteMemo(memoNo)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerMemoByID() {
		String loginID;
		
		HotelUICommon.menuStart("메모조회(id)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          메모조회(id)          │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   조회하고자 하는 아이디 입력 : ");
		loginID = sc.next();
		
		List<MemoInfo> memoList = HotelAdminBasicUI.getMemoDAO().searchMemo(loginID);
		if (memoList.size() > 0) {
			System.out.println();
			
			for (int i=0; i<memoList.size(); i++) {
				System.out.println("번호 : " + memoList.get(i).getMemoNo()
						+ ", 아이디 : " + memoList.get(i).getLoginID()
						+ ", 제목 : " + memoList.get(i).getMemoTitle()
						+ ", 내용 : " + memoList.get(i).getMemoDetailStr()
						+ ", 작성일 : " + memoList.get(i).getWrittenDate());
			}
			
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchAllCustomerMemo() {
		HotelUICommon.menuStart("메모조회(전체)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         메모조회(전체)          │");
		System.out.println("└─────────────────────────────┘\n");
		
		List<MemoInfo> memoList = HotelAdminBasicUI.getMemoDAO().searchAllMemo();
		if (memoList.size() > 0) {
			for (int i=0; i<memoList.size(); i++) {
				System.out.println("번호 : " + memoList.get(i).getMemoNo()
						+ ", 아이디 : " + memoList.get(i).getLoginID()
						+ ", 제목 : " + memoList.get(i).getMemoTitle()
						+ ", 내용 : " + memoList.get(i).getMemoDetailStr()
						+ ", 작성일 : " + memoList.get(i).getWrittenDate());
			}
			
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
