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
		HotelUICommon.menuStart("���� ������ �� ���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ���� ������ �� ���              ��");
		System.out.println("��������������������������������������������������������������\n");
		
		List<RoomDisplayInfo> resList = HotelBasicUI.getRoomDisplayDAO().getAllAccessibleRoomList();
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("�ŷ���Ϲ�ȣ : " + resList.get(i).getDisplayNo()
						+ ", �� ��ȣ : " + resList.get(i).getRoomNo() + "\n"
						+ ", �ŷ��������� : " + resList.get(i).getStartDate().toString() + ", �ŷ��������� : " + resList.get(i).getEndDate().toString() + "\n"
						+ ", 1�ϴ� ���� : " + resList.get(i).getPricePerDay() + ", �ֵ� ���� ���� : " + resList.get(i).getHotdealStartDate());
			}
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		HotelUICommon.menuEnd(sc);
	}
	
	
}
