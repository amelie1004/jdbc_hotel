package hotel.ui.customer;

import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.dao.customer.HotelCustomerDAO;

public class HotelCustomerUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelCustomerUI() {
		
	}
	
	// 1-1-(1) : ȸ������ �޴�
	public void insertCustomerList() {
		String loginId, loginPasswd;
		String nickName, firstName, lastName;
		int age;
		String phoneNumber, email;
		
		boolean isRun = true;
		
		HotelUICommon.menuStart("ȸ������");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��             ȸ������                       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		while (isRun) {
			sc = new Scanner(System.in);
			System.out.print("   1. ���̵� �Է� : ");
			loginId = sc.next();
			System.out.print("   2. �н����� �Է� : ");
			loginPasswd = sc.next();
			sc.nextLine();
			System.out.print("   3. �г��� �Է� : ");
			nickName = sc.nextLine();
			System.out.print("   4. �̸�(First name) �Է� : ");
			firstName = sc.nextLine();
			System.out.print("   5. ��(Last name) �Է� : ");
			lastName = sc.nextLine();
			System.out.print("   6. ���� �Է� : ");
			age = sc.nextInt();
			System.out.print("   7. ��ȭ��ȣ �Է� : ");
			phoneNumber = sc.next();
			System.out.print("   8. �̸��� �Է� : ");
			email = sc.next();
			
			if (HotelBasicUI.getCustomerDAO().getCustomerByID(loginId) == null) {
				isRun = false;
				if (HotelBasicUI.getCustomerDAO().insertCustomer(loginId, loginPasswd, nickName,
						firstName, lastName, age, phoneNumber, email) > 0) {
					System.out.println("\n   ���������� ��ϵǾ����ϴ�. (^^)\n");
				} else {
					System.out.println("\n   ��Ͽ� �����Ͽ����ϴ�.\n");
				}
			} else {
				System.out.println("\n   �������̵� �����մϴ�.\n");
			}
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	// 1-2-(1) : �α��� �޴�
	public void loginCustomer() {
		String loginId, loginPasswd;
		
		HotelUICommon.menuStart("�α���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��             �α���                          ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. ���̵� �Է� : ");
		loginId = sc.next();
		System.out.print("   2. �н����� �Է� : ");
		loginPasswd = sc.next();
		
		if (HotelBasicUI.getCustomerDAO().login(loginId, loginPasswd)) {
			System.out.println("\n   ���������� �α��εǾ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �α��ο� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	// 1-40-(2) : �α׾ƿ��޴�
	public void logoutCustomer() {
		HotelUICommon.menuStart("�α׾ƿ�");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��             �α׾ƿ�                       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		if (HotelBasicUI.getCustomerDAO().logout(HotelCustomerDAO.getLoginID())) {
			System.out.println("\n   ���������� �α׾ƿ��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �α׾ƿ��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	// 1-50-(1) : ȸ��Ż�� (�α׾ƿ� ����)
	public void deleteCustomerInLogOff() {
		String loginID, passwd;
		
		HotelUICommon.menuStart("ȸ��Ż��");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��             ȸ��Ż��                       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   1. Ż���ϰ��� �ϴ� ���̵� �Է� : ");
		loginID = sc.next();
		System.out.print("   2. ��й�ȣ �Է� : ");
		passwd = sc.nextLine();
		
		if (HotelBasicUI.getCustomerDAO().deleteCustomerByIDAndPasswd(loginID, passwd) > 0) {
			System.out.println("\n   ȸ�� Ż�� �Ϸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ȸ�� Ż�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	// 1-50-(2) : ȸ��Ż�� (�α��� ����)
	public void deleteCustomerInLogOn() {
		String answer;
		
		HotelUICommon.menuStart("ȸ��Ż��");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��             ȸ��Ż��                       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   ���� �α��ε� �������� ���� Ż���Ͻðڽ��ϱ�? (Y/N)");
		answer = sc.next();
		
		if (answer.equals("Y") || answer.equals("y")) {
			if (HotelBasicUI.getCustomerDAO().deleteCustomerByID(HotelCustomerDAO.getLoginID()) > 0) {
				System.out.println("\n   ȸ�� Ż�� �Ϸ�Ǿ����ϴ�. (^^)\n");
			} else {
				System.out.println("\n   ȸ�� Ż�� �����Ͽ����ϴ�.\n");
			}
		} else {
			System.out.println("\n   ȸ�� Ż�� ����ϼ̽��ϴ�.");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
