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
		HotelUICommon.menuListStart("CustomerClass ����");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showCustomerClassMenu();
			selectCustomerClassMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showCustomerClassMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��       Customer Class        ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. �������");
		System.out.println("   2. ��������");
		System.out.println("   3. ��������");
		System.out.println("  20. ������ȸ(��ü)");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
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
		
		HotelUICommon.menuStart("�������");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��             �������                       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. Class ��ȣ �Է� : ");
		classNo = sc.nextInt();
		System.out.print("   2. Class �̸� �Է� : ");
		className = sc.nextLine();
		System.out.print("   3. Class ������ �Է� : ");
		discountRate = sc.nextInt();
		
		if (HotelAdminBasicUI.getCustomerClassDAO().insertClass(classNo, className, discountRate) > 0) {
			System.out.println("\n   ���������� ��ϵǾ����ϴ�. (^^)");
		} else {
			System.out.println("\n   ��Ͽ� �����Ͽ����ϴ�.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteCustomerClass() {
		int classNo;
		
		HotelUICommon.menuStart("��������");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��             ��������                       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� Class�� ��ȣ �Է� : ");
		classNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getCustomerClassDAO().deleteCustomerClass(classNo) > 0) {
			System.out.println("\n   ������ �����Ͽ����ϴ�.");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyCustomerClass() {
		String className;
		int classNo, discountRate;
		
		HotelUICommon.menuStart("��������");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��             ��������                       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. Class ��ȣ �Է� : ");
		classNo = sc.nextInt();
		System.out.print("   2. �����ϰ��� �ϴ� Class �̸� �Է� : ");
		className = sc.nextLine();
		System.out.print("   3. �����ϰ��� �ϴ� Class ������ �Է� : ");
		discountRate = sc.nextInt();
		
		CustomerClassInfo countryInfo = new CustomerClassInfo(classNo, className, discountRate);
		
		if (HotelAdminBasicUI.getCustomerClassDAO().updateCustomerClass(countryInfo) > 0) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchAllCustomerClass() {
		HotelUICommon.menuStart("������ȸ(��ü)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ������ȸ(��ü)         ��");
		System.out.println("��������������������������������������������������������������\n");
		
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
			System.out.println("\n   �����Ͱ� �������� �ʽ��ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
