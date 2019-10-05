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
		HotelUICommon.menuListStart("�� ���� ����");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showCustomerListMenu();
			selectCustomerListMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showCustomerListMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��           �� ���� ����                    ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. ���� �˻�(Login_ID)");
		System.out.println("   2. ���� �˻�(NickName)");
		System.out.println("   3. ���� �˻�(Phone_Number)");
		System.out.println("   4. ���� �˻�(E-mail)");
		System.out.println("  10. ���� ����(Login_ID)");
		System.out.println("  20. ���� ��ϼ���(Login_ID)");
		System.out.println("  21. ���� �������(Login_ID)");
		System.out.println("  30. ���� ����(Login_ID)");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectCustomerListMenu() {
		switch (choice) {
		case 1:
			// ���� �˻�(Login_ID) 
			searchCustomerByLoginID();
			break;
		case 2:
			// ���� �˻�(NickName)
			searchCustomerByNickName();
			break;
		case 3:
			// ���� �˻�(Phone_Number)
			searchCustomerByPhoneNum();
			break;
		case 4:
			// ���� �˻�(E-mail)
			searchCustomerByEmail();
			break;
		case 10:
			// ���� ����(Login_ID)
			modifyCustomerByID();
			break;
		case 20:
			// ���� ��ϼ���(Login_ID)
			blockCustomerByLoginID();
			break;
		case 21:
			// ���� �������(Login_ID)
			releaseCustomerByLoginID();
			break;
		case 30:
			// ���� ����(Login_ID)
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
		
		HotelUICommon.menuStart("���� �˻�(id)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ���� �˻� (id)         ��");
		System.out.println("��������������������������������������������������������������\n");
		
		System.out.print("   ã�����ϴ� ������ �α��� ���̵� �Է� : ");
		loginID = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByID(loginID);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", ���̵� : " + resInfo.getLoginId()
					+ ", ��й�ȣ ���� : " + resInfo.getIsPasswdExpired()
					+ ", �г��� : " + resInfo.getNickName()
					+ ", �̸� : " + resInfo.getFirstName()
					+ ", �� : " + resInfo.getLastName()
					+ ", ���� : " + resInfo.getAge() + "\n"
					+ "\t��ȭ��ȣ : " + resInfo.getPhoneNumber()
					+ ", ������ �α��� ���� : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)");
		} else {
			System.out.println("\n   �ش��ϴ� ���̵� �������� �ʽ��ϴ�.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerByNickName() {
		String nickName = null;
		
		HotelUICommon.menuStart("���� �˻�(NickName)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��       ���� �˻� (NickName)      ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ã�����ϴ� ������ �г��� �Է� : ");
		nickName = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByNickName(nickName);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", ���̵� : " + resInfo.getLoginId()
					+ ", ��й�ȣ ���� : " + resInfo.getIsPasswdExpired()
					+ ", �г��� : " + resInfo.getNickName()
					+ ", �̸� : " + resInfo.getFirstName()
					+ ", �� : " + resInfo.getLastName()
					+ ", ���� : " + resInfo.getAge() + "\n"
					+ "\t��ȭ��ȣ : " + resInfo.getPhoneNumber()
					+ ", ������ �α��� ���� : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)");
		} else {
			System.out.println("\n   �ش��ϴ� �г����� �������� �ʽ��ϴ�.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerByPhoneNum() {
		String phoneNum = null;
		
		HotelUICommon.menuStart("���� �˻�(Phone_Number)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��     ���� �˻� (Phone_Number)    ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ã�����ϴ� ������ ��ȭ��ȣ �Է� : ");
		phoneNum = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByPhoneNum(phoneNum);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", ���̵� : " + resInfo.getLoginId()
					+ ", ��й�ȣ ���� : " + resInfo.getIsPasswdExpired()
					+ ", �г��� : " + resInfo.getNickName()
					+ ", �̸� : " + resInfo.getFirstName()
					+ ", �� : " + resInfo.getLastName()
					+ ", ���� : " + resInfo.getAge() + "\n"
					+ "\t��ȭ��ȣ : " + resInfo.getPhoneNumber()
					+ ", ������ �α��� ���� : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)");
		} else {
			System.out.println("\n   �ش��ϴ� ��ȭ��ȣ�� �������� �ʽ��ϴ�.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchCustomerByEmail() {
		String email = null;
		
		HotelUICommon.menuStart("���� �˻�(E-mail)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ���� �˻� (E-mail)       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ã�����ϴ� ������ �̸��� �Է� : ");
		email = sc.next();
		
		CustomerInfo resInfo = HotelAdminBasicUI.getCustomerDAO().getCustomerByEmail(email);
		
		if (resInfo != null) {
			System.out.println("No : " + resInfo.getCustomerNo()
					+ ", ���̵� : " + resInfo.getLoginId()
					+ ", ��й�ȣ ���� : " + resInfo.getIsPasswdExpired()
					+ ", �г��� : " + resInfo.getNickName()
					+ ", �̸� : " + resInfo.getFirstName()
					+ ", �� : " + resInfo.getLastName()
					+ ", ���� : " + resInfo.getAge() + "\n"
					+ "\t��ȭ��ȣ : " + resInfo.getPhoneNumber()
					+ ", ������ �α��� ���� : " + resInfo.getLastLoginDate()
					+ ", Block : " + resInfo.getIsBlocked());
			
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)");
		} else {
			System.out.println("\n   �ش��ϴ� ��ȭ��ȣ�� �������� �ʽ��ϴ�.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyCustomerByID() {
		String loginId, loginPasswd;
		String nickName, firstName, lastName;
		int age;
		String phoneNumber, email;
		
		HotelUICommon.menuStart("���� ����(Login_ID)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��       ���� ����(Login_ID)      ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ������ ���̵� �Է� : ");
		loginId = sc.next();
		
		if (HotelBasicUI.getCustomerDAO().getCustomerByID(loginId) == null) {
			System.out.println("\n   �����ϰ��� �ϴ� ���̵� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}

		System.out.print("   1. �н����� �Է� : ");
		loginPasswd = sc.next();
		sc.nextLine();
		System.out.print("   2. �г��� �Է� : ");
		nickName = sc.nextLine();
		System.out.print("   3. �̸�(First name) �Է� : ");
		firstName = sc.nextLine();
		System.out.print("   4. ��(Last name) �Է� : ");
		lastName = sc.nextLine();
		System.out.print("   5. ���� �Է� : ");
		age = sc.nextInt();
		System.out.print("   6. ��ȭ��ȣ �Է� : ");
		phoneNumber = sc.next();
		System.out.print("   7. �̸��� �Է� : ");
		email = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().modifyCustomerById(loginId, loginPasswd, nickName,
				firstName, lastName, age, phoneNumber, email)) {
			System.out.println("\n   ���������� ��ϵǾ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��Ͽ� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	
	public void blockCustomerByLoginID() {
		String loginID = null;
		
		HotelUICommon.menuStart("���� ��ϼ���(Login_ID)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��      ���� ��ϼ���(Login_ID)    ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ����ϰ��� �ϴ� ������ ���̵� �Է� : ");
		loginID = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().blockCustomerByLoginID(loginID, true)) {
			System.out.println("\n   ���������� ���ó�� �Ǿ����ϴ�.");
		} else {
			System.out.println("\n   �ش��ϴ� ���̵� �������� �ʽ��ϴ�.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void releaseCustomerByLoginID() {
		String loginID = null;
		
		HotelUICommon.menuStart("���� �������(Login_ID)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��      ���� �������(Login_ID)    ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ��������ϰ��� �ϴ� ������ ���̵� �Է� : ");
		loginID = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().blockCustomerByLoginID(loginID, false)) {
			System.out.println("\n   ���������� �������ó�� �Ǿ����ϴ�.");
		} else {
			System.out.println("\n   �ش��ϴ� ���̵� �������� �ʽ��ϴ�.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteCustomerByLoginID() {
		String loginID = null;
		
		HotelUICommon.menuStart("���� ����(Login_ID)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��       ���� ����(Login_ID)      ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ������ ���̵� �Է� : ");
		loginID = sc.next();
		
		if (HotelAdminBasicUI.getCustomerDAO().deleteCustomerByLoginID(loginID)) {
			System.out.println("\n   ���������� ����ó�� �Ǿ����ϴ�.");
		} else {
			System.out.println("\n   �ش��ϴ� ���̵� �������� �ʽ��ϴ�.");
		}

		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
