package hotel.ui.customer;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.dao.customer.HotelCustomerDAO;
import hotel.vo.MemoInfo;

public class HotelMemoUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelMemoUI() {
		
	}
	
	public void startCustomerMemoMenu() {
		HotelUICommon.menuListStart("개인 메모 관리");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showCustomerMemoMenu();
			selectCustomerMemoMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showCustomerMemoMenu() {
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│           개인 메모 관리                    │");
		System.out.println("└─────────────────────────────┘\n");
		System.out.println("   1. 메모등록");
		System.out.println("   2. 메모검색(내용+제목)");
		System.out.println("   3. 메모전체보기");
		System.out.println("   4. 메모상세보기");
		System.out.println("   5. 메모수정");
		System.out.println("   6. 메모삭제");
		System.out.println(" 100. 뒤로가기");
		System.out.print("\n  메뉴를 선택해주세요 : ");
		choice = sc.nextInt();
	}
	
	public void selectCustomerMemoMenu() {
		switch (choice) {
		case 1:
			// 메모등록
			insertCustomerMemo();
			break;
		case 2:
			// 메모검색(내용+제목)
			searchCustomerMemoByTitleAndContents();
			break;
		case 3:
			// 메모전체보기
			searchAllCustomerMemo();
			break;
		case 4:
			// 메모상세보기
			searchDetailCustomerMemo();
			break;
		case 5:
			// 메모수정
			modifyCustomerMemo();
			break;
		case 6:
			// 메모삭제
			deleteCustomerMemo();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertCustomerMemo() {
		String memoTitle, memoContents;
		
		HotelUICommon.menuStart("메모등록");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│             메모등록                       │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   1. 글 제목 : ");
		memoTitle = sc.nextLine();
		System.out.print("   2. 글 내용 : ");
		memoContents = sc.nextLine();
		
		if (HotelBasicUI.getMemoDAO().insertMemo(HotelCustomerDAO.getLoginID(), memoTitle, memoContents)) {
			System.out.println("\n   정상적으로 등록되었습니다. (^^)\n");
		} else {
			System.out.println("\n   등록에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerMemoByTitleAndContents() {
		String memoTitle, memoContents;
		
		HotelUICommon.menuStart("메모검색(내용+제목)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│        메모검색 (내용+제목)       │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. 검색에 포함시키는 글 제목 : ");
		memoTitle = sc.nextLine();
		System.out.print("   2. 검색에 포함시키는 글 내용 : ");
		memoContents = sc.nextLine();
		
		List<MemoInfo> memoList = HotelBasicUI.getMemoDAO().searchMemoByTitleAndContents(HotelCustomerDAO.getLoginID(), memoTitle, memoContents);
		if (memoList.size() > 0) {
			System.out.println();
			
			for (int i=0; i<memoList.size(); i++) {
				System.out.println("번호 : " + memoList.get(i).getMemoNo()
						+ ", 제목 : " + memoList.get(i).getMemoTitle()
						+ ", 작성일 : " + memoList.get(i).getWrittenDate());
						//+ ", 내용 : " + memoList.get(i).getMemoDetailStr());
			}
			
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
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
		
		if (HotelBasicUI.getMemoDAO().deleteMemo(HotelCustomerDAO.getLoginID(), memoNo)) {
			System.out.println("\n   정상적으로 삭제되었습니다. (^^)\n");
		} else {
			System.out.println("\n   삭제에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyCustomerMemo() {
		int memoNo;
		String memoTitle, memoContents;
		
		HotelUICommon.menuStart("메모수정");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            메모수정                         │");
		System.out.println("└─────────────────────────────┘\n");

		sc = new Scanner(System.in);
		System.out.print("   1. 수정하고자 하는 글 번호 입력 : ");
		memoNo = sc.nextInt();
		sc.nextLine();
		System.out.print("   2. 수정하고자 하는 글 제목 : ");
		memoTitle = sc.nextLine();
		System.out.print("   3. 수정하고자 하는 글 내용 : ");
		memoContents = sc.nextLine();
		
		if (HotelBasicUI.getMemoDAO().modifyMemo(HotelCustomerDAO.getLoginID(), memoNo, memoTitle, memoContents)) {
			System.out.println("\n   정상적으로 수정되었습니다. (^^)\n");
		} else {
			System.out.println("\n   수정에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchAllCustomerMemo() {
		HotelUICommon.menuStart("메모조회(전체)");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          메모조회(전체)         │");
		System.out.println("└─────────────────────────────┘\n");
		
		List<MemoInfo> memoList = HotelBasicUI.getMemoDAO().searchAllMemo(HotelCustomerDAO.getLoginID());
		if (memoList.size() > 0) {
			System.out.println();
			
			for (int i=0; i<memoList.size(); i++) {
				System.out.println("번호 : " + memoList.get(i).getMemoNo()
						+ ", 제목 : " + memoList.get(i).getMemoTitle()
						+ ", 작성일 : " + memoList.get(i).getWrittenDate());
						//+ ", 내용 : " + memoList.get(i).getMemoDetailStr());
			}
			
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchDetailCustomerMemo() {
		int memoNo;
		
		HotelUICommon.menuStart("메모상세보기");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│           메모상세보기                      │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   검색하고자 하는 글 번호 입력 : ");
		memoNo = sc.nextInt();
		
		MemoInfo resMemo = HotelBasicUI.getMemoDAO().searchDetailCustomerMemo(HotelCustomerDAO.getLoginID(), memoNo);
		if (resMemo != null) {
			System.out.println("번호 : " + resMemo.getMemoNo()
					+ ", 제목 : " + resMemo.getMemoTitle()
					+ ", 내용 : " + resMemo.getMemoDetailStr());
			
			System.out.println("\n   정상적으로 조회되었습니다. (^^)\n");
		} else {
			System.out.println("\n   조회에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
