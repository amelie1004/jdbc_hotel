package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.HotelManagerInfo;

public class HotelAdminHotelManagerUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelManagerUI() {
		
	}
	
	public void startHotelManagerMenu() {
		HotelUICommon.menuListStart("ȣ�� �Ŵ��� ����");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelManagerMenu();
			selectHotelManagerMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelManagerMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� �Ŵ��� ����                   ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. ȣ�� �Ŵ��� �߰�");
		System.out.println("  10. ȣ�� �Ŵ��� ����");
		System.out.println("  20. ȣ�� �Ŵ��� ����");
		System.out.println("  30. ȣ�� �Ŵ��� ��ȸ(ID)");
		System.out.println("  31. ȣ�� �Ŵ��� ��ȸ(Login_id)");
		System.out.println("  32. ȣ�� �Ŵ��� ��ȸ(�̸�)");
		System.out.println("  33. ȣ�� �Ŵ��� ��ȸ(��ȭ��ȣ)");
		System.out.println("  34. ȣ�� �Ŵ��� ��ȸ(E-mail)");
		System.out.println("  40. ȣ�� �Ŵ��� ��ü ���");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelManagerMenu() {
		switch (choice) {
		case 1:
			// ȣ�� �Ŵ��� �߰�
			insertHotelManager();
			break;
		case 10:
			// ȣ�� �Ŵ��� ����
			modifyHotelManager();
			break;
		case 20:
			// ȣ�� �Ŵ��� ����
			deleteHotelManager();
			break;
		case 30:
			// ȣ�� �Ŵ��� ��ȸ(ID)
			searchHotelManagerByID();
			break;
		case 31:
			// ȣ�� �Ŵ��� ��ȸ(Login_id)
			searchHotelManagerByLoginID();
			break;
		case 32:
			// ȣ�� �Ŵ��� ��ȸ(�̸�)
			searchHotelManagerByName();
			break;
		case 33:
			// ȣ�� �Ŵ��� ��ȸ(��ȭ��ȣ)
			searchHotelManagerByTelephone();
			break;
		case 34:
			// ȣ�� �Ŵ��� ��ȸ(E-mail)
			searchHotelManagerByEmail();
			break;
		case 40:
			// ȣ�� �Ŵ��� ��ü ���
			printAllHotelManager();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelManager() {
		int hotelNo, bossManagerNo;
		String loginID, loginPasswd;
		String firstName, lastName;
		String telephone, email;
		
		HotelUICommon.menuStart("ȣ�� �Ŵ��� �߰�");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� �Ŵ��� �߰�                   ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. ȣ�� ��ȣ �Է� : ");
		hotelNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   ȣ�� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. �α��� ���̵� �Է� : ");
		loginID = sc.next();
		if (HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByLoginID(loginID) != null) {
			System.out.println("\n   �α��� ���̵� �̹� �����մϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   3. �α��� �н����� �Է� : ");
		loginPasswd = sc.next();
		sc.nextLine();
		System.out.print("   4. �̸�(First name) �Է� : ");
		firstName = sc.nextLine();
		System.out.print("   5. ��(Last name) �Է� : ");
		lastName = sc.nextLine();
		System.out.print("   6. ��ȭ��ȣ �Է� : ");
		telephone = sc.next();
		System.out.print("   7. �̸��� �Է� : ");
		email = sc.next();
		System.out.print("   8. ������ ��ȣ �Է�(������ 0) : ");
		bossManagerNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelManagerDAO().insertHotelManagerInfo(hotelNo, loginID, loginPasswd, firstName, lastName, telephone, email, bossManagerNo)) {
			System.out.println("\n   ���������� �߰��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �߰��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelManager() {
		int managerNo, hotelNo, bossManagerNo;
		String loginID, loginPasswd;
		String firstName, lastName;
		String telephone, email;
		
		HotelUICommon.menuStart("ȣ�� �Ŵ��� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� �Ŵ��� ����                   ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   1. �Ŵ��� ��ȣ �Է� : ");
		managerNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByID(managerNo) == null) {
			System.out.println("\n   �Ŵ��� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. ȣ�� ��ȣ �Է� : ");
		hotelNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   ȣ�� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   3. �α��� ���̵� �Է� : ");
		loginID = sc.next();
		if (HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByLoginID(loginID) != null) {
			System.out.println("\n   �α��� ���̵� �̹� �����մϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. �α��� �н����� �Է� : ");
		loginPasswd = sc.next();
		System.out.print("   5. �̸�(First name) �Է� : ");
		firstName = sc.next();
		System.out.print("   6. ��(Last name) �Է� : ");
		lastName = sc.next();
		System.out.print("   7. ��ȭ��ȣ �Է� : ");
		telephone = sc.next();
		System.out.print("   8. �̸��� �Է� : ");
		email = sc.next();
		System.out.print("   9. ������ ��ȣ �Է�(������ 0) : ");
		bossManagerNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelManagerDAO().updateHotelManagerInfo(managerNo, hotelNo, loginID, loginPasswd, firstName, lastName, telephone, email, bossManagerNo)) {
			System.out.println("\n   ���������� �߰��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �߰��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelManager() {
		int managerNo;
		
		HotelUICommon.menuStart("ȣ�� �Ŵ��� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� �Ŵ��� ����                   ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ���� �ϰ��� �ϴ� �Ŵ��� ��ȣ �Է� : ");
		managerNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelManagerDAO().deleteHotelManagerByID(managerNo)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelManagerByID() {
		int managerNo;
		
		HotelUICommon.menuStart("ȣ�� �Ŵ��� ��ȸ(ID)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� �Ŵ��� ��ȸ(ID)       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ��ȸ �ϰ��� �ϴ� �Ŵ��� ��ȣ �Է� : ");
		managerNo = sc.nextInt();
		
		HotelManagerInfo resInfo = HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByID(managerNo);
		if (resInfo != null) {
			System.out.println();
			System.out.println("�Ŵ��� ��ȣ : " + resInfo.getManagerNo()
					+ ", ���̵� : " + resInfo.getManagerId() + ", �Ҽ�ȣ�ڹ�ȣ : " + resInfo.getHotelNo()
					+ ", �̸� : " + resInfo.getFirstName() + ", �� : " + resInfo.getLastName()
					+ ", ��ȭ��ȣ : " + resInfo.getTelephoneNum() + ", �̸��� : " + resInfo.getEmail()
					+ ", �����Ŵ��� ��ȣ : " + resInfo.getBossManagerNo() + "\n");
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelManagerByLoginID() {
		String loginID;
		
		HotelUICommon.menuStart("ȣ�� �Ŵ��� ��ȸ(Login_id)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��     ȣ�� �Ŵ��� ��ȸ(Login_id)    ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ��ȸ �ϰ��� �ϴ� �Ŵ��� ���̵� �Է� : ");
		loginID = sc.next();
		
		HotelManagerInfo resInfo = HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByLoginID(loginID);
		if (resInfo != null) {
			System.out.println();
			System.out.println("�Ŵ��� ��ȣ : " + resInfo.getManagerNo()
					+ ", ���̵� : " + resInfo.getManagerId() + ", �Ҽ�ȣ�ڹ�ȣ : " + resInfo.getHotelNo()
					+ ", �̸� : " + resInfo.getFirstName() + ", �� : " + resInfo.getLastName()
					+ ", ��ȭ��ȣ : " + resInfo.getTelephoneNum() + ", �̸��� : " + resInfo.getEmail()
					+ ", �����Ŵ��� ��ȣ : " + resInfo.getBossManagerNo() + "\n");
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelManagerByName() {
		String nameStr;
		
		HotelUICommon.menuStart("ȣ�� �Ŵ��� ��ȸ(�̸�)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��       ȣ�� �Ŵ��� ��ȸ(�̸�)       ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ��ȸ �ϰ��� �ϴ� �Ŵ��� �̸� �Է� : ");
		nameStr = sc.nextLine();
		
		List<HotelManagerInfo> resList = HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByName(nameStr);
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("�Ŵ��� ��ȣ : " + resList.get(i).getManagerNo()
						+ ", ���̵� : " + resList.get(i).getManagerId() + ", �Ҽ�ȣ�ڹ�ȣ : " + resList.get(i).getHotelNo()
						+ ", �̸� : " + resList.get(i).getFirstName() + ", �� : " + resList.get(i).getLastName()
						+ ", ��ȭ��ȣ : " + resList.get(i).getTelephoneNum() + ", �̸��� : " + resList.get(i).getEmail()
						+ ", �����Ŵ��� ��ȣ : " + resList.get(i).getBossManagerNo());
			}
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelManagerByTelephone() {
		String telephone;
		
		HotelUICommon.menuStart("ȣ�� �Ŵ��� ��ȸ(��ȭ��ȣ)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��      ȣ�� �Ŵ��� ��ȸ(��ȭ��ȣ)     ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ��ȸ �ϰ��� �ϴ� ��ȭ��ȣ �Է� : ");
		telephone = sc.next();
		
		HotelManagerInfo resInfo = HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByTelephone(telephone);
		if (resInfo != null) {
			System.out.println();
			System.out.println("�Ŵ��� ��ȣ : " + resInfo.getManagerNo()
					+ ", ���̵� : " + resInfo.getManagerId() + ", �Ҽ�ȣ�ڹ�ȣ : " + resInfo.getHotelNo()
					+ ", �̸� : " + resInfo.getFirstName() + ", �� : " + resInfo.getLastName()
					+ ", ��ȭ��ȣ : " + resInfo.getTelephoneNum() + ", �̸��� : " + resInfo.getEmail()
					+ ", �����Ŵ��� ��ȣ : " + resInfo.getBossManagerNo() + "\n");
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelManagerByEmail() {
		String email;
		
		HotelUICommon.menuStart("ȣ�� �Ŵ��� ��ȸ(E-mail)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��      ȣ�� �Ŵ��� ��ȸ(E-mail)     ��");
		System.out.println("��������������������������������������������������������������\n");
		
		sc = new Scanner(System.in);
		System.out.print("   ��ȸ �ϰ��� �ϴ� �̸��� �Է� : ");
		email = sc.next();
		
		HotelManagerInfo resInfo = HotelAdminBasicUI.getHotelManagerDAO().getHotelManagerByEmail(email);
		if (resInfo != null) {
			System.out.println();
			System.out.println("�Ŵ��� ��ȣ : " + resInfo.getManagerNo()
					+ ", ���̵� : " + resInfo.getManagerId() + ", �Ҽ�ȣ�ڹ�ȣ : " + resInfo.getHotelNo()
					+ ", �̸� : " + resInfo.getFirstName() + ", �� : " + resInfo.getLastName()
					+ ", ��ȭ��ȣ : " + resInfo.getTelephoneNum() + ", �̸��� : " + resInfo.getEmail()
					+ ", �����Ŵ��� ��ȣ : " + resInfo.getBossManagerNo() + "\n");
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelManager() {
		HotelUICommon.menuStart("ȣ�� �Ŵ��� ��ü ���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� �Ŵ��� ��ü ���              ��");
		System.out.println("��������������������������������������������������������������\n");
		
		List<HotelManagerInfo> resList = HotelAdminBasicUI.getHotelManagerDAO().getAllHotelManager();
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("�Ŵ��� ��ȣ : " + resList.get(i).getManagerNo()
						+ ", ���̵� : " + resList.get(i).getManagerId() + ", �Ҽ�ȣ�ڹ�ȣ : " + resList.get(i).getHotelNo()
						+ ", �̸� : " + resList.get(i).getFirstName() + ", �� : " + resList.get(i).getLastName()
						+ ", ��ȭ��ȣ : " + resList.get(i).getTelephoneNum() + ", �̸��� : " + resList.get(i).getEmail()
						+ ", �����Ŵ��� ��ȣ : " + resList.get(i).getBossManagerNo());
			}
			System.out.println("\n   ���������� ��ȸ�Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ��ȸ�� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
