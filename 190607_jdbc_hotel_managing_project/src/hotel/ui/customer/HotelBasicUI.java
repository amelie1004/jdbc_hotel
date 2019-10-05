package hotel.ui.customer;

import java.util.Scanner;

import hotel.common.HotelDBCommon;
import hotel.common.HotelUICommon;
import hotel.dao.customer.HotelCustomerDAO;
import hotel.dao.customer.HotelMemoDAO;
import hotel.dao.customer.HotelReservationDAO;
import hotel.dao.customer.HotelRoomDisplayDAO;

public class HotelBasicUI {
	private static HotelCustomerDAO CustomerDAO = null;
	private static HotelMemoDAO MemoDAO = null;
	private static HotelRoomDisplayDAO RoomDisplayDAO = null;
	private static HotelReservationDAO ReservationDAO = null;

	private HotelCustomerUI customerUI = new HotelCustomerUI();
	private HotelMemoUI memoUI = new HotelMemoUI();
	private HotelRoomDisplayUI roomDisplayUI = new HotelRoomDisplayUI();
	private HotelReservationUI reservationUI = new HotelReservationUI();

	private Scanner sc = new Scanner(System.in);
	private int choice = 0;

	public HotelBasicUI() {
		HotelDBCommon.Load_JDBC_Driver();

		CustomerDAO = new HotelCustomerDAO();
		MemoDAO = new HotelMemoDAO();
		RoomDisplayDAO = new HotelRoomDisplayDAO();
		ReservationDAO = new HotelReservationDAO();

		HotelUICommon.menuListStart("���� �޴�");

		while (HotelUICommon.isMenuRun(0)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();

			showMainMenu();

			switch (choice) {
			case 1:
				if (!HotelCustomerDAO.isLogin()) {
					// �α׾ƿ� ���� -- ȸ������
					customerUI.insertCustomerList();
				} else {
					// �α��� ���� -- ���� ������ �� ���
					roomDisplayUI.printAllAccesibleRoomList();
				}
				break;
			case 2:
				if (!HotelCustomerDAO.isLogin()) {
					// �α׾ƿ� ���� -- �α��� �޴�
					customerUI.loginCustomer();
				} else {
					// �α��� ���� -- ���� ������ �� ���
					reservationUI.printAllReservedInfoByCustomerID();
				}
				break;
			case 10:
				if (HotelCustomerDAO.isLogin()) {
					// �α��� ���� -- �� �����ϱ�
					reservationUI.insertReservationInfoByRoomID();
				}
				break;
			case 20:
				if (HotelCustomerDAO.isLogin()) {
					// �α��� ���� -- �� ���� ����ϱ�
					reservationUI.cancelReservationInfoByRoomID();
				}
				break;
			case 30:
				if (HotelCustomerDAO.isLogin()) {
					// �α��� ���� -- ���� �޸� ����
					memoUI.startCustomerMemoMenu();
				}
				break;
			case 40:
				if (HotelCustomerDAO.isLogin()) {
					// �α��� ���� -- �α׾ƿ� �޴�
					customerUI.logoutCustomer();
				}
				break;
			case 50:
				if (!HotelCustomerDAO.isLogin()) {
					// �α׾ƿ� ����
					customerUI.deleteCustomerInLogOff();
				} else {
					// �α��� ���� -- ȸ��Ż��
					customerUI.deleteCustomerInLogOn();
				}
				break;
			case 100:
				// ���α׷� ����
				if (HotelCustomerDAO.isLogin()) {
					if (HotelBasicUI.getCustomerDAO().logout(HotelCustomerDAO.getLoginID())) {
						System.out.println("\n   ���������� �α׾ƿ��Ǿ����ϴ�. (^^)");
					} else {
						System.out.println("\n   �α׾ƿ��� �����Ͽ����ϴ�.");
					}
				}
				exitProgram();
				break;
			default:
				break;
			}
		}
	}

	public static HotelCustomerDAO getCustomerDAO() {
		return CustomerDAO;
	}

	public static HotelMemoDAO getMemoDAO() {
		return MemoDAO;
	}

	public static HotelRoomDisplayDAO getRoomDisplayDAO() {
		return RoomDisplayDAO;
	}

	public static HotelReservationDAO getReservationDAO() {
		return ReservationDAO;
	}

	public void showMainMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��   SC Hotel Customer System  ��");
		System.out.println("��������������������������������������������������������������\n");
		if (!HotelCustomerDAO.isLogin()) {
			// �α׾ƿ� ����
			System.out.println("   1. ȸ������");
			System.out.println("   2. �α���");
			System.out.println("  50. ȸ��Ż��");
		} else {
			// �α��� ����
			System.out.println("   1. ���� ���� ������ �� ���");
			System.out.println("   2. ���� ������ �� ���");
			System.out.println("  10. �� �����ϱ�");
			System.out.println("  20. �� ���� ����ϱ�");
			System.out.println("  30. ���� �޸� ����");
			System.out.println("  40. �α׾ƿ�");
			System.out.println("  50. ȸ��Ż��");
		}
		System.out.println(" 100. ���α׷� ����");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}

	public void exitProgram() {
		HotelUICommon.menuListStop();
		System.out.println("\n���α׷��� �����մϴ�. �����մϴ�.\n");
	}
}
