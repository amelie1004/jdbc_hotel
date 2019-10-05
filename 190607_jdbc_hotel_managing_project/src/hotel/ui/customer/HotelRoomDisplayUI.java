package hotel.ui.customer;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.RoomDisplayInfo;

public class HotelRoomDisplayUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelRoomDisplayUI() {
		
	}
	
	public void printAllAccesibleRoomList() {
		HotelUICommon.menuStart("예약 가능한 방 출력");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          예약 가능한 방 출력              │");
		System.out.println("└─────────────────────────────┘\n");
		
		List<RoomDisplayInfo> resList = HotelBasicUI.getRoomDisplayDAO().getAllAccessibleRoomList();
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("거래등록번호 : " + resList.get(i).getDisplayNo()
						+ ", 방 번호 : " + resList.get(i).getRoomNo() + "\n"
						+ ", 거래시작일자 : " + resList.get(i).getStartDate().toString() + ", 거래종료일자 : " + resList.get(i).getEndDate().toString() + "\n"
						+ ", 1일당 가격 : " + resList.get(i).getPricePerDay() + ", 핫딜 시작 일자 : " + resList.get(i).getHotdealStartDate());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		HotelUICommon.menuEnd(sc);
	}
	
	
}
