package hotel.ui.admin;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.RoomDisplayInfo;

public class HotelAdminHotelRoomDisplayUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelRoomDisplayUI() {
		
	}
	
	public void startHotelRoomDisplayMenu() {
		HotelUICommon.menuListStart("熨夢 葬蝶お 婦葬");

		while (HotelUICommon.isMenuRun(1)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelRoomDisplayMenu();
			selectHotelRoomDisplayMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelRoomDisplayMenu() {
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛          熨夢 葬蝶お 婦葬                   弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		System.out.println("   1. 寞 蹺陛");
		System.out.println("  10. ⑷營 蛔煙脹 寞 熱薑");
		System.out.println("  20. ⑷營 蛔煙脹 寞 餉薯");
		System.out.println("  30. ⑷營 蛔煙脹 寞 褻��(ID)");
		System.out.println("  31. ⑷營 蛔煙脹 寞 褻��(RoomID)");
		System.out.println("  40. ⑷營 剪楚醞檣 寞 瞪羹 轎溘");
		System.out.println(" 100. 菴煎陛晦");
		System.out.print("\n  詭景蒂 摹鷗п輿撮蹂 : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelRoomDisplayMenu() {
		switch (choice) {
		case 1:
			// 寞 蹺陛
			insertRoom();
			break;
		case 10:
			// ⑷營 剪楚醞檣 寞 熱薑
			modifyRoom();
			break;
		case 20:
			// ⑷營 剪楚醞檣 寞 餉薯
			deleteRoomByID();
			break;
		case 30:
			// ⑷營 剪楚醞檣 寞 褻��(ID)
			searchRoomByID();
			break;
		case 31:
			// ⑷營 剪楚醞檣 寞 褻��(RoomID)
			searchRoomByRoomID();
			break;
		case 40:
			// ⑷營 剪楚醞檣 寞 瞪羹 轎溘
			printAllRoomByDealingState();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertRoom() {
		int roomNo, pricePerDay, isDealed; 
		Date dealStartDate, dealEndDate;
		
		HotelUICommon.menuStart("寞 蹺陛");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛             寞 蹺陛                         弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   1. 蛔煙脹 寞 廓�� 殮溘 : ");
		roomNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByID(roomNo) == null) {
			System.out.println("\n   寞 廓�� 陛 襄營ж雖 彊蝗棲棻.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. 剪楚 衛濛 橾濠 殮溘(YYYY-MM-DD) : ");
		String dealStartStr = sc.next();
		dealStartDate = java.sql.Date.valueOf(dealStartStr);
		
		System.out.print("   3. 剪楚 謙猿 橾濠 殮溘(YYYY-MM-DD) : ");
		String dealEndStr = sc.next();
		dealEndDate = java.sql.Date.valueOf(dealEndStr);
		
		if (dealStartDate.after(dealEndDate)) {
			System.out.println("\n   陳瞼蒂 澀跤 殮溘ж樟蝗棲棻.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 1橾渡 渠罹 陛問 : ");
		pricePerDay = sc.nextInt();
		
		System.out.print("   5. 剪楚 陛棟 嶸鼠(0/1) : ");
		isDealed = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().insertRoomDisplayInfo(roomNo, dealStartDate, dealEndDate, pricePerDay, isDealed)) {
			System.out.println("\n   薑鼻瞳戲煎 蹺陛腎歷蝗棲棻. (^^)\n");
		} else {
			System.out.println("\n   蹺陛縑 褒ぬж艘蝗棲棻.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyRoom() {
		int roomDisplayNo;
		int roomNo, pricePerDay, isDealed; 
		Date dealStartDate, dealEndDate, hotdealDate;
		
		HotelUICommon.menuStart("⑷營 蛔煙脹 寞 熱薑");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛         ⑷營 蛔煙脹 寞 熱薑                 弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   熱薑ж堅濠 ж朝 剪楚蛔煙廓�� 殮溘 : ");
		roomDisplayNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().getRoomDisplayInfoByID(roomDisplayNo) != null) {
			System.out.println("\n   剪楚蛔煙廓�� 陛 襄營ж雖 彊蝗棲棻.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   1. 蛔煙脹 寞 廓�� 殮溘 : ");
		roomNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelRoomDAO().getHotelRoomInfoByID(roomNo) == null) {
			System.out.println("\n   寞 廓�� 陛 襄營ж雖 彊蝗棲棻.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   2. 剪楚 衛濛 橾濠 殮溘(YYYY-MM-DD) : ");
		String dealStartStr = sc.next();
		dealStartDate = java.sql.Date.valueOf(dealStartStr);
		
		System.out.print("   3. 剪楚 謙猿 橾濠 殮溘(YYYY-MM-DD) : ");
		String dealEndStr = sc.next();
		dealEndDate = java.sql.Date.valueOf(dealEndStr);
		
		if (dealStartDate.after(dealEndDate)) {
			System.out.println("\n   陳瞼蒂 澀跤 殮溘ж樟蝗棲棻.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 1橾渡 渠罹 陛問 : ");
		pricePerDay = sc.nextInt();
		
		System.out.print("   5. н裁 衛濛 橾濠 殮溘(0/YYYY-MM-DD) : ");
		String hotdealStr = sc.next();
		hotdealDate = java.sql.Date.valueOf(hotdealStr);
		
		if (hotdealDate.after(dealStartDate)) {
			System.out.println("\n   陳瞼蒂 澀跤 殮溘ж樟蝗棲棻.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   6. 剪楚 陛棟 嶸鼠(0/1) : ");
		isDealed = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().updateRoomDisplayInfo(roomDisplayNo, roomNo, dealStartDate, dealEndDate, pricePerDay, hotdealDate, isDealed)) {
			System.out.println("\n   薑鼻瞳戲煎 熱薑腎歷蝗棲棻. (^^)\n");
		} else {
			System.out.println("\n   熱薑縑 褒ぬж艘蝗棲棻.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteRoomByID() {
		int roomDisplayNo;
		HotelUICommon.menuStart("⑷營 蛔煙脹 寞 餉薯");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛         ⑷營 蛔煙脹 寞 餉薯                 弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   餉薯ж堅濠 ж朝 剪楚蛔煙廓�� 殮溘 : ");
		roomDisplayNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelRoomDisplayDAO().deleteRoomDisplayInfo(roomDisplayNo)) {
			System.out.println("\n   薑鼻瞳戲煎 餉薯腎歷蝗棲棻. (^^)\n");
		} else {
			System.out.println("\n   餉薯縑 褒ぬж艘蝗棲棻.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchRoomByID() {
		int roomDisplayNo;
		HotelUICommon.menuStart("⑷營 蛔煙脹 寞 褻��(ID)");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛       ⑷營 蛔煙脹 寞 褻��(ID)      弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   匐儀ж堅濠 ж朝 剪楚蛔煙廓�� 殮溘 : ");
		roomDisplayNo = sc.nextInt();
		
		RoomDisplayInfo resInfo = HotelAdminBasicUI.getHotelRoomDisplayDAO().getRoomDisplayInfoByID(roomDisplayNo);
		if (resInfo != null) {
			System.out.println();
			System.out.println("剪楚蛔煙廓�� : " + resInfo.getDisplayNo()
					+ ", 寞 廓�� : " + resInfo.getRoomNo() + "\n"
					+ ", 剪楚衛濛橾濠 : " + resInfo.getStartDate().toString() + ", 剪楚謙猿橾濠 : " + resInfo.getEndDate().toString() + "\n"
					+ ", 1橾渡 陛問 : " + resInfo.getPricePerDay() + ", н裁 衛濛 橾濠 : " + resInfo.getHotdealStartDate());
			System.out.println("\n   薑鼻瞳戲煎 匐儀腎歷蝗棲棻. (^^)\n");
		} else {
			System.out.println("\n   匐儀縑 褒ぬж艘蝗棲棻.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchRoomByRoomID() {
		int roomDisplayNo;
		HotelUICommon.menuStart("⑷營 蛔煙脹 寞 褻��(RoomID)");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛      ⑷營 蛔煙脹 寞 褻��(RoomID)   弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		sc = new Scanner(System.in);
		
		System.out.print("   匐儀ж堅濠 ж朝 寞 廓�� 殮溘 : ");
		roomDisplayNo = sc.nextInt();
		
		List<RoomDisplayInfo> resList = HotelAdminBasicUI.getHotelRoomDisplayDAO().getRoomDisplayInfoByRoomID(roomDisplayNo);
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("剪楚蛔煙廓�� : " + resList.get(i).getDisplayNo()
						+ ", 寞 廓�� : " + resList.get(i).getRoomNo() + "\n"
						+ ", 剪楚衛濛橾濠 : " + resList.get(i).getStartDate().toString() + ", 剪楚謙猿橾濠 : " + resList.get(i).getEndDate().toString() + "\n"
						+ ", 1橾渡 陛問 : " + resList.get(i).getPricePerDay() + ", н裁 衛濛 橾濠 : " + resList.get(i).getHotdealStartDate());
			}
			System.out.println("\n   薑鼻瞳戲煎 匐儀腎歷蝗棲棻. (^^)\n");
		} else {
			System.out.println("\n   匐儀縑 褒ぬж艘蝗棲棻.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllRoomByDealingState() {
		HotelUICommon.menuStart("⑷營 剪楚醞檣 寞 瞪羹 轎溘");
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛      ⑷營 剪楚醞檣 寞 瞪羹 轎溘              弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎\n");
		
		List<RoomDisplayInfo> resList = HotelAdminBasicUI.getHotelRoomDisplayDAO().getAllRoomDisplayInfoByDealed();
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("剪楚蛔煙廓�� : " + resList.get(i).getDisplayNo()
						+ ", 寞 廓�� : " + resList.get(i).getRoomNo() + "\n"
						+ ", 剪楚衛濛橾濠 : " + resList.get(i).getStartDate().toString() + ", 剪楚謙猿橾濠 : " + resList.get(i).getEndDate().toString() + "\n"
						+ ", 1橾渡 陛問 : " + resList.get(i).getPricePerDay() + ", н裁 衛濛 橾濠 : " + resList.get(i).getHotdealStartDate());
			}
			System.out.println("\n   薑鼻瞳戲煎 匐儀腎歷蝗棲棻. (^^)\n");
		} else {
			System.out.println("\n   匐儀縑 褒ぬж艘蝗棲棻.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
