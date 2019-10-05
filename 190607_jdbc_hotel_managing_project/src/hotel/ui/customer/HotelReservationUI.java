package hotel.ui.customer;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.dao.customer.HotelCustomerDAO;
import hotel.vo.CustomerInfo;
import hotel.vo.HotelReservationInfo;
import hotel.vo.RoomDisplayInfo;

public class HotelReservationUI {
	private Scanner sc = new Scanner(System.in);
	
	public HotelReservationUI() {
		
	}
	
	public void printAllReservedInfoByCustomerID() {
		HotelUICommon.menuStart("현재 예약한 방 출력");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│         현재 예약한 방 출력                 │");
		System.out.println("└─────────────────────────────┘\n");
		
		CustomerInfo customerInfo = HotelBasicUI.getCustomerDAO().getCustomerByID(HotelCustomerDAO.getLoginID());
		int customerNo = customerInfo.getCustomerNo();
		List<HotelReservationInfo> resList = HotelBasicUI.getReservationDAO().getAllReservedInfoByCustomerNo(customerNo);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("예약 번호 : " + resList.get(i).getReservationNo() + ", 고객 번호 : " + resList.get(i).getCustomerNo()
							+ ", 방 번호 : " + resList.get(i).getRoomNo() + ", 시작 일자 : " + resList.get(i).getStartDate() + ", 종료 일자 : " + resList.get(i).getEndDate()
							+ ", 결제 일자 : " + resList.get(i).getSignDate() + ", 결제취소 일자 : " + resList.get(i).getSignCancelDate() + "\n"
							+ ", 성인 수 : " + resList.get(i).getAdultCnt() + ", 청소년 수 : " + resList.get(i).getChildrenCnt() + ", 핫딜 유무 : " + resList.get(i).getIsHotdeal() + "\n"
							+ ", 고객 요청 사항 : " + resList.get(i).getRequestStr());
			}
			System.out.println("\n   정상적으로 검색되었습니다. (^^)\n");
		} else {
			System.out.println("\n   검색에 실패하였습니다.\n");
		}
		
		HotelUICommon.menuEnd(sc);
	}
	
	public void insertReservationInfoByRoomID() {
		int roomNo, adultCnt, childrenCnt;
		Date startDate, endDate, signDate;
		String requestStr;
		
		HotelUICommon.menuStart("방 예약하기");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│            방 예약하기                     │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		
		CustomerInfo customerInfo = HotelBasicUI.getCustomerDAO().getCustomerByID(HotelCustomerDAO.getLoginID());
		int customerNo = customerInfo.getCustomerNo();
		
		System.out.print("   1. 예약하고자 하는 거래등록번호 입력 : ");
		int roomDisplayNo = sc.nextInt();
		RoomDisplayInfo displayInfo = HotelBasicUI.getRoomDisplayDAO().getRoomDisplayInfoByID(roomDisplayNo);
		if (displayInfo == null) {
			System.out.println("\n   거래등록번호가 존재하지 않습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		} else {
			roomNo = displayInfo.getRoomNo();
		}
		
		System.out.print("   2. 숙박시작일자 입력(YYYY-MM-DD) : ");
		String startDateStr = sc.next();
		startDate = java.sql.Date.valueOf(startDateStr);
		
		System.out.print("   3. 숙박종료일자 입력(YYYY-MM-DD) : ");
		String endDateStr = sc.next();
		endDate = java.sql.Date.valueOf(endDateStr);
		
		if (startDate.after(endDate)) {
			System.out.println("\n   숙박일자가 잘못 입력되었습니다.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. 성인 인원 수 입력 : ");
		adultCnt = sc.nextInt();
		System.out.print("   5. 청소년 인원 수 입력 : ");
		childrenCnt = sc.nextInt();
		
		sc.nextLine();
		System.out.print("   6. 추가 요구사항 입력 : ");
		requestStr = sc.nextLine();
		
		if (HotelBasicUI.getReservationDAO().insertReservationInfoByRoomID(customerNo, roomNo, startDate, endDate, adultCnt, childrenCnt, requestStr)) {
			System.out.println("\n   정상적으로 예약되었습니다. (^^)\n");
		} else {
			System.out.println("\n   예약에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void cancelReservationInfoByRoomID() {
		int reservationNo;
		
		HotelUICommon.menuStart("방 예약 취소하기");
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│          방 예약 취소하기                   │");
		System.out.println("└─────────────────────────────┘\n");
		
		sc = new Scanner(System.in);
		System.out.print("   취소하고자 하는 예약번호 입력 : ");
		reservationNo = sc.nextInt();
		
		CustomerInfo customerInfo = HotelBasicUI.getCustomerDAO().getCustomerByID(HotelCustomerDAO.getLoginID());
		int customerNo = customerInfo.getCustomerNo();
		
		if (HotelBasicUI.getReservationDAO().cancelSignReservationInfo(customerNo, reservationNo)) {
			System.out.println("\n   정상적으로 취소되었습니다. (^^)\n");
		} else {
			System.out.println("\n   취소에 실패하였습니다.\n");
		}
		
		sc = new Scanner(System.in);
		
		HotelUICommon.menuEnd(sc);
	}
}
