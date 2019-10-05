package hotel.common;

import java.util.ArrayList;
import java.util.Scanner;

public class HotelUICommon {
	private static ArrayList<String> MenuNaviList = new ArrayList<String>();
	private static ArrayList<Integer> MenuRunSigList = new ArrayList<>();
	
	public static void menuListStart(String menuName) {
		MenuRunSigList.add(1);
		MenuNaviList.add(menuName);
	}

	public static void menuListStop() {
		MenuRunSigList.set(MenuRunSigList.size() - 1, 0);
	}

	public static void menuListEnd() {
		MenuRunSigList.remove(MenuRunSigList.size() - 1);
		MenuNaviList.remove(MenuNaviList.size() - 1);
	}

	public static boolean isMenuRun(int depth) {
		if (MenuRunSigList.get(depth) == 1)
			return true;

		return false;
	}

	public static void showMenuNaviDesc() {
		System.out.print("���� ��ġ : ");
		System.out.print(MenuNaviList.get(0));

		if (MenuNaviList.size() > 1) {
			for (int i = 1; i < MenuNaviList.size(); i++) {
				System.out.print(" -> " + MenuNaviList.get(i));
			}
		}
		System.out.println("\n");
	}

	// �̰Ÿ�.. ��ü�Ҹ��� ���𰡰� ������ �����ٵ�.. ���� ������..;;
	public static void clearConsole() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public static void enterEndKey(Scanner scanner) {
		while (true) {
			System.out.print("[EnterŰ�� �Է��ϸ� ���� �޴��� ���ư��ϴ�.]");
			String s = scanner.nextLine();
			if (s.equals("") | s.equals("pl exit")) {
				break;
			}
		}
	}
	
	public static void menuStart(String menuName) {
		HotelUICommon.menuListStart(menuName);
		HotelUICommon.clearConsole();
		HotelUICommon.showMenuNaviDesc();
	}
	
	public static void menuEnd(Scanner fromSC) {
		HotelUICommon.enterEndKey(fromSC);
		HotelUICommon.menuListEnd();
	}
}
