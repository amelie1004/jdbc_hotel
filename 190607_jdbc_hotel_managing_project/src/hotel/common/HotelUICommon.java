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
		System.out.print("현재 위치 : ");
		System.out.print(MenuNaviList.get(0));

		if (MenuNaviList.size() > 1) {
			for (int i = 1; i < MenuNaviList.size(); i++) {
				System.out.print(" -> " + MenuNaviList.get(i));
			}
		}
		System.out.println("\n");
	}

	// 이거를.. 대체할만한 무언가가 있으면 좋을텐데.. 뭔가 없을까..;;
	public static void clearConsole() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public static void enterEndKey(Scanner scanner) {
		while (true) {
			System.out.print("[Enter키를 입력하면 이전 메뉴로 돌아갑니다.]");
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
