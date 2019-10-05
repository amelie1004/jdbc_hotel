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
		HotelUICommon.menuListStart("���� �޸� ����");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showCustomerMemoMenu();
			selectCustomerMemoMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showCustomerMemoMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��           ���� �޸� ����                    ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. �޸���");
		System.out.println("   2. �޸�˻�(����+����)");
		System.out.println("   3. �޸���ü����");
		System.out.println("   4. �޸�󼼺���");
		System.out.println("   5. �޸����");
		System.out.println("   6. �޸����");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectCustomerMemoMenu() {
		switch (choice) {
		case 1:
			// �޸���
			insertCustomerMemo();
			break;
		case 2:
			// �޸�˻�(����+����)
			searchCustomerMemoByTitleAndContents();
			break;
		case 3:
			// �޸���ü����
			searchAllCustomerMemo();
			break;
		case 4:
			// �޸�󼼺���
			searchDetailCustomerMemo();
			break;
		case 5:
			// �޸����
			modifyCustomerMemo();
			break;
		case 6:
			// �޸����
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
		
		HotelUICommon.menuStart("�޸���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��             �޸���                       ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   1. �� ���� : ");
		memoTitle = sc.nextLine();
		System.out.print("   2. �� ���� : ");
		memoContents = sc.nextLine();
		
		if (HotelBasicUI.getMemoDAO().insertMemo(HotelCustomerDAO.getLoginID(), memoTitle, memoContents)) {
			System.out.println("\n   ���������� ��ϵǾ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��Ͽ� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerMemoByTitleAndContents() {
		String memoTitle, memoContents;
		
		HotelUICommon.menuStart("�޸�˻�(����+����)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        �޸�˻� (����+����)       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. �˻��� ���Խ�Ű�� �� ���� : ");
		memoTitle = sc.nextLine();
		System.out.print("   2. �˻��� ���Խ�Ű�� �� ���� : ");
		memoContents = sc.nextLine();
		
		List<MemoInfo> memoList = HotelBasicUI.getMemoDAO().searchMemoByTitleAndContents(HotelCustomerDAO.getLoginID(), memoTitle, memoContents);
		if (memoList.size() > 0) {
			System.out.println();
			
			for (int i=0; i<memoList.size(); i++) {
				System.out.println("��ȣ : " + memoList.get(i).getMemoNo()
						+ ", ���� : " + memoList.get(i).getMemoTitle()
						+ ", �ۼ��� : " + memoList.get(i).getWrittenDate());
						//+ ", ���� : " + memoList.get(i).getMemoDetailStr());
			}
			
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
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
		
		if (HotelBasicUI.getMemoDAO().deleteMemo(HotelCustomerDAO.getLoginID(), memoNo)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyCustomerMemo() {
		int memoNo;
		String memoTitle, memoContents;
		
		HotelUICommon.menuStart("�޸����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��            �޸����                         ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   1. �����ϰ��� �ϴ� �� ��ȣ �Է� : ");
		memoNo = sc.nextInt();
		sc.nextLine();
		System.out.print("   2. �����ϰ��� �ϴ� �� ���� : ");
		memoTitle = sc.nextLine();
		System.out.print("   3. �����ϰ��� �ϴ� �� ���� : ");
		memoContents = sc.nextLine();
		
		if (HotelBasicUI.getMemoDAO().modifyMemo(HotelCustomerDAO.getLoginID(), memoNo, memoTitle, memoContents)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchAllCustomerMemo() {
		HotelUICommon.menuStart("�޸���ȸ(��ü)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          �޸���ȸ(��ü)         ��");
		System.out.println("��������������������������������������������������������������\n");
		
		List<MemoInfo> memoList = HotelBasicUI.getMemoDAO().searchAllMemo(HotelCustomerDAO.getLoginID());
		if (memoList.size() > 0) {
			System.out.println();
			
			for (int i=0; i<memoList.size(); i++) {
				System.out.println("��ȣ : " + memoList.get(i).getMemoNo()
						+ ", ���� : " + memoList.get(i).getMemoTitle()
						+ ", �ۼ��� : " + memoList.get(i).getWrittenDate());
						//+ ", ���� : " + memoList.get(i).getMemoDetailStr());
			}
			
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchDetailCustomerMemo() {
		int memoNo;
		
		HotelUICommon.menuStart("�޸�󼼺���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��           �޸�󼼺���                      ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   �˻��ϰ��� �ϴ� �� ��ȣ �Է� : ");
		memoNo = sc.nextInt();
		
		MemoInfo resMemo = HotelBasicUI.getMemoDAO().searchDetailCustomerMemo(HotelCustomerDAO.getLoginID(), memoNo);
		if (resMemo != null) {
			System.out.println("��ȣ : " + resMemo.getMemoNo()
					+ ", ���� : " + resMemo.getMemoTitle()
					+ ", ���� : " + resMemo.getMemoDetailStr());
			
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
