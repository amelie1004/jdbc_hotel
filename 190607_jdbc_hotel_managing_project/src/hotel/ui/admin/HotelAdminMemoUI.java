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
		HotelUICommon.menuListStart("�� �޸� ����");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showMemoMenu();
			selectMemoMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showMemoMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��           �� �޸� ����                    ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   2. �޸����");
		System.out.println("  10. �޸���ȸ(id)");
		System.out.println("  20. �޸���ȸ(��ü)");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
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
		
		HotelUICommon.menuStart("�޸����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            �޸����                          ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� �� ��ȣ �Է� : ");
		memoNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getMemoDAO().deleteMemo(memoNo)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerMemoByID() {
		String loginID;
		
		HotelUICommon.menuStart("�޸���ȸ(id)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          �޸���ȸ(id)          ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   ��ȸ�ϰ��� �ϴ� ���̵� �Է� : ");
		loginID = sc.next();
		
		List<MemoInfo> memoList = HotelAdminBasicUI.getMemoDAO().searchMemo(loginID);
		if (memoList.size() > 0) {
			System.out.println();
			
			for (int i=0; i<memoList.size(); i++) {
				System.out.println("��ȣ : " + memoList.get(i).getMemoNo()
						+ ", ���̵� : " + memoList.get(i).getLoginID()
						+ ", ���� : " + memoList.get(i).getMemoTitle()
						+ ", ���� : " + memoList.get(i).getMemoDetailStr()
						+ ", �ۼ��� : " + memoList.get(i).getWrittenDate());
			}
			
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchAllCustomerMemo() {
		HotelUICommon.menuStart("�޸���ȸ(��ü)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         �޸���ȸ(��ü)          ��");
		System.out.println("��������������������������������������������������������������\n");
		
		List<MemoInfo> memoList = HotelAdminBasicUI.getMemoDAO().searchAllMemo();
		if (memoList.size() > 0) {
			for (int i=0; i<memoList.size(); i++) {
				System.out.println("��ȣ : " + memoList.get(i).getMemoNo()
						+ ", ���̵� : " + memoList.get(i).getLoginID()
						+ ", ���� : " + memoList.get(i).getMemoTitle()
						+ ", ���� : " + memoList.get(i).getMemoDetailStr()
						+ ", �ۼ��� : " + memoList.get(i).getWrittenDate());
			}
			
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
