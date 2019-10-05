package hotel.ui.admin;

import java.util.Scanner;

import hotel.common.HotelUICommon;

public class HotelAdminHotelUI {
	private HotelAdminHotelListUI hotelListUI = new HotelAdminHotelListUI();
	private HotelAdminHotelRoomUI hotelRoomUI = new HotelAdminHotelRoomUI();
	private HotelAdminHotelManagerUI hotelManagerUI = new HotelAdminHotelManagerUI();
	private HotelAdminHotelBrandUI hotelBrandUI = new HotelAdminHotelBrandUI();
	private HotelAdminHotelTypeUI hotelTypeUI = new HotelAdminHotelTypeUI();
	private HotelAdminHotelRoomTypeUI hotelRoomTypeUI = new HotelAdminHotelRoomTypeUI();
	private HotelAdminHotelRoomStatusUI hotelRoomStatusUI = new HotelAdminHotelRoomStatusUI();
	
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelUI() {
		
	}
	
	public void startHotelMenu() {
		HotelUICommon.menuListStart("ȣ�� ���� ����");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelMenu();
			selectHotelMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��           ȣ�� ���� ����                    ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. ȣ�� ����Ʈ ����");
		System.out.println("   2. ȣ�� �� ����");
		System.out.println("   3. ȣ�� �Ŵ��� ����");
		System.out.println("  11. ȣ�� �귣���� ����");
		System.out.println("  12. ȣ�� Ÿ�Ը�� ����");
		System.out.println("  21. ȣ�� �� Ÿ�Ը�� ����");
		System.out.println("  22. ȣ�� �� ���¸�� ����");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelMenu() {
		switch (choice) {
		case 1:
			// ȣ�� ����Ʈ ����
			hotelListUI.startHotelListMenu();
			break;
		case 2:
			// ȣ�� �� ����
			hotelRoomUI.startHotelRoomMenu();
			break;
		case 3:
			// ȣ�� �Ŵ��� ����
			hotelManagerUI.startHotelManagerMenu();
			break;
		case 11:
			// ȣ�� �귣���� ����
			hotelBrandUI.startHotelBrandMenu();
			break;
		case 12:
			// ȣ�� Ÿ�Ը�� ����
			hotelTypeUI.startHotelTypeMenu();
			break;
		case 21:
			// ȣ�� �� Ÿ�Ը�� ����
			hotelRoomTypeUI.startHotelRoomTypeMenu();
			break;
		case 22:
			// ȣ�� �� ���¸�� ����
			hotelRoomStatusUI.startHotelRoomStatusMenu();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
}
