package hotel.ui.admin;

import java.util.List;
import java.util.Scanner;

import hotel.common.HotelUICommon;
import hotel.vo.HotelInfo;

public class HotelAdminHotelListUI {
	private Scanner sc = new Scanner(System.in);
	private int choice = 0;
	
	public HotelAdminHotelListUI() {
		
	}
	
	public void startHotelListMenu() {
		HotelUICommon.menuListStart("ȣ�� ����Ʈ ����");

		while (HotelUICommon.isMenuRun(2)) {
			HotelUICommon.clearConsole();
			HotelUICommon.showMenuNaviDesc();
			
			showHotelListMenu();
			selectHotelListMenu();
		}

		HotelUICommon.menuListEnd();
	}
	
	public void showHotelListMenu() {
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� ����Ʈ ����                   ��");
		System.out.println("��������������������������������������������������������������\n");
		System.out.println("   1. ȣ�� ���� ���");
		System.out.println("   2. ȣ�� �⺻���� ����");
		System.out.println("   3. ȣ�� ��Ÿ���� ����");
		System.out.println("  10. ȣ�� ���� �˻�(id)");
		System.out.println("  11. ȣ�� ���� �˻�(�̸�)");
		System.out.println("  12. ȣ�� ���� �˻�(����)");
		System.out.println("  13. ȣ�� ���� �˻�(����)");
		System.out.println("  20. ȣ�� ���� ����");
		System.out.println("  50. ȣ�� ��ü ���");
		System.out.println(" 100. �ڷΰ���");
		System.out.print("\n  �޴��� �������ּ��� : ");
		choice = sc.nextInt();
	}
	
	public void selectHotelListMenu() {
		switch (choice) {
		case 1:
			// ȣ�� ���� ��� (not null)
			insertHotelList();
			break;
		case 2:
			// ȣ�� �⺻���� ����
			modifyHotelListBasicInfo();
			break;
		case 3:
			// ȣ�� ��Ÿ���� ����
			modifyHotelListEtcInfo();
			break;
		case 10:
			// ȣ�� ���� �˻�(id)
			searchHotelListByID();
			break;
		case 11:
			// ȣ�� ���� �˻�(�̸�)
			searchHotelListByName();
			break;
		case 12:
			// ȣ�� ���� �˻�(����)
			searchHotelListByCountry();
			break;
		case 13:
			// ȣ�� ���� �˻�(����)
			searchHotelListByCity();
			break;
		case 20:
			// ȣ�� ���� ����
			deleteHotelListByID();
			break;
		case 50:
			// ȣ�� ��ü ���
			printAllHotelList();
			break;
		case 100:
			HotelUICommon.menuListStop();
			break;
		default:
			break;
		}
	}
	
	public void insertHotelList() {
		String hotelName;
		int hotelBrandNo, hotelTypeNo, hotelLocNo;
		int hotelStar, hotelRoomCnt;
		
		HotelUICommon.menuStart("ȣ�� ���� ���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��           ȣ�� ���� ���                    ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   1. ȣ�� �̸� �Է� : ");
		hotelName= sc.nextLine();
		System.out.print("   2. ȣ�� �귣�� ��ȣ �Է� : ");
		hotelBrandNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelBrandDAO().getHotelBrandByID(hotelBrandNo) == null) {
			System.out.println("\n   ȣ�� �귣�� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   3. ȣ�� Ÿ�� ��ȣ �Է� : ");
		hotelTypeNo = sc.nextInt();
		if (HotelAdminBasicUI.getHotelTypeDAO().getHotelTypeByID(hotelTypeNo) == null) {
			System.out.println("\n   ȣ�� Ÿ�� ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   4. ȣ�� ��ġ ��ȣ �Է� : ");
		hotelLocNo = sc.nextInt();
		if (HotelAdminBasicUI.getLocationCityDAO().getLocationCity(hotelLocNo) == null) {
			System.out.println("\n   ȣ�� ��ġ ��ȣ�� �������� �ʽ��ϴ�.\n");
			sc = new Scanner(System.in);
			HotelUICommon.menuEnd(sc);
			return;
		}
		
		System.out.print("   5. ȣ�� Star �Է� : ");
		hotelStar = sc.nextInt();
		System.out.print("   6. ȣ�� �� ���� �Է� : ");
		hotelRoomCnt = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelListDAO().insertHotelListInfo(hotelName, hotelBrandNo, hotelTypeNo, hotelLocNo, hotelStar, hotelRoomCnt)) {
			System.out.println("\n   ���������� �߰��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �߰��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelListBasicInfo() {
		int hotelNo;
		
		String hotelName;
		int hotelBrandNo, hotelTypeNo, hotelLocNo;
		int hotelStar, hotelRoomCnt;
		
		HotelUICommon.menuStart("ȣ�� �⺻���� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� �⺻���� ����                  ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ȣ�� ��ȣ �Է� : ");
		hotelNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   �˻��Ͻ� ȣ���� ã���� �����ϴ�.\n");
		}  else {
			System.out.println("   ȣ�� �⺻���� ������ �����մϴ�.\n");
			System.out.print("   1. ȣ�� �̸� �Է� : ");
			hotelName= sc.nextLine();
			System.out.print("   2. ȣ�� �귣�� ��ȣ �Է� : ");
			hotelBrandNo = sc.nextInt();
			if (HotelAdminBasicUI.getHotelBrandDAO().getHotelBrandByID(hotelBrandNo) == null) {
				System.out.println("\n   ȣ�� �귣�� ��ȣ�� �������� �ʽ��ϴ�.\n");
				sc = new Scanner(System.in);
				HotelUICommon.menuEnd(sc);
				return;
			}
			
			System.out.print("   3. ȣ�� Ÿ�� ��ȣ �Է� : ");
			hotelTypeNo = sc.nextInt();
			if (HotelAdminBasicUI.getHotelTypeDAO().getHotelTypeByID(hotelTypeNo) == null) {
				System.out.println("\n   ȣ�� Ÿ�� ��ȣ�� �������� �ʽ��ϴ�.\n");
				sc = new Scanner(System.in);
				HotelUICommon.menuEnd(sc);
				return;
			}
			
			System.out.print("   4. ȣ�� ��ġ ��ȣ �Է� : ");
			hotelLocNo = sc.nextInt();
			if (HotelAdminBasicUI.getLocationCityDAO().getLocationCity(hotelLocNo) == null) {
				System.out.println("\n   ȣ�� ��ġ ��ȣ�� �������� �ʽ��ϴ�.\n");
				sc = new Scanner(System.in);
				HotelUICommon.menuEnd(sc);
				return;
			}
			
			System.out.print("   5. ȣ�� Star �Է� : ");
			hotelStar = sc.nextInt();
			System.out.print("   6. ȣ�� �� ���� �Է� : ");
			hotelRoomCnt = sc.nextInt();
			
			if (HotelAdminBasicUI.getHotelListDAO().updateHotelListBasicInfo(hotelNo, hotelName, hotelBrandNo, hotelTypeNo, hotelLocNo, hotelStar, hotelRoomCnt)) {
				System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
			} else {
				System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
			}
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void modifyHotelListEtcInfo() {
		int hotelNo;
		
		String hotelAddress;
		int hotelWorkerCnt, bossHotelNo;
		int can_breakfast, can_swimming, can_wifi, can_parking, can_pet, can_smoke;
		
		HotelUICommon.menuStart("ȣ�� ��Ÿ���� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� ��Ÿ���� ����                  ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ȣ�� ��ȣ �Է� : ");
		hotelNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo) == null) {
			System.out.println("\n   �˻��Ͻ� ȣ���� ã���� �����ϴ�.\n");
		}  else {
			System.out.println("   ȣ�� ��Ÿ���� ������ �����մϴ�.\n");
			sc.nextLine();
			System.out.print("   1. ȣ�� ���ּ� �Է� : ");
			hotelAddress = sc.nextLine();
			System.out.print("   2. ȣ�� ���� �� �Է� : ");
			hotelWorkerCnt = sc.nextInt();
			System.out.print("   3. ���� ȣ�� ��ȣ �Է�(���� ��� 0) : ");
			bossHotelNo = sc.nextInt();
			System.out.print("   4. ��ħ �Ļ� ���� ���� �Է�(0/1) : ");
			can_breakfast = sc.nextInt();
			System.out.print("   5. ������ �̿� ���� ���� �Է�(0/1) : ");
			can_swimming = sc.nextInt();
			System.out.print("   6. Wifi �̿밡�� ���� �Է�(0/1) : ");
			can_wifi = sc.nextInt();
			System.out.print("   7. ���� ���� ���� �Է�(0/1) : ");
			can_parking = sc.nextInt();
			System.out.print("   8. �ֿϵ��� ���� ���� ���� �Է�(0/1) : ");
			can_pet = sc.nextInt();
			System.out.print("   9. �� ���� ���� �Է�(0/1) : ");
			can_smoke = sc.nextInt();
			
			if (HotelAdminBasicUI.getHotelListDAO().updateHotelListEtcInfo(hotelNo, hotelAddress, hotelWorkerCnt, bossHotelNo, can_breakfast, can_swimming, can_wifi, can_parking, can_pet, can_smoke)) {
				System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
			} else {
				System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
			}
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelListByID() {
		int hotelNo;
		HotelUICommon.menuStart("ȣ�� ���� �˻�(id)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��         ȣ�� ���� �˻�(id)       ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �˻��ϰ��� �ϴ� ȣ�� ��ȣ �Է� : ");
		hotelNo = sc.nextInt();
		
		HotelInfo resInfo = HotelAdminBasicUI.getHotelListDAO().getHotelListByID(hotelNo);
		
		if (resInfo != null) {
			System.out.println("��ȣ : " + resInfo.getHotelNo()
					+ ", ȣ���̸� : " + resInfo.getHotelName()
					+ ", ȣ�ں귣�� : " + resInfo.getBossHotelNo()
					+ ", ȣ��Ÿ�� : " + resInfo.getTypeNo() + "\n"
					+ ", ȣ������ : " + resInfo.getCityNo()
					+ ", ȣ���ּ� : " + resInfo.getHotelAddress() + "\n"
					+ ", ȣ��Star : " + resInfo.getStar()
					+ ", ȣ�� �� ���� : " + resInfo.getRoomCnt()
					+ ", ȣ�� ���� �� : " + resInfo.getWorkerCnt() + "\n"
					+ ", ��ħ�Ļ� ���� : " + resInfo.getCanBreakfast()
					+ ", �������� ���� : " + resInfo.getCanSwimming()
					+ ", Wifi ���� : " + resInfo.getCanSwimming()
					+ ", �������� ���� : " + resInfo.getCanParking()
					+ ", �ֿϵ��� ���డ�� ���� : " + resInfo.getCanPet()
					+ ", �� ���� ���� : " + resInfo.getCanSmoke() + "\n"
					+ ", ���� ȣ�� ��ȣ : " + resInfo.getBossHotelNo());
			
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelListByName() {
		String hotelName;
		HotelUICommon.menuStart("ȣ�� ���� �˻�(�̸�)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� ���� �˻�(�̸�)       ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �˻��ϰ��� �ϴ� ȣ�� �̸� �Է� : ");
		hotelName = sc.nextLine();
		
		HotelInfo resInfo = HotelAdminBasicUI.getHotelListDAO().getHotelListByName(hotelName);
		
		if (resInfo != null) {
			System.out.println("��ȣ : " + resInfo.getHotelNo()
					+ ", ȣ���̸� : " + resInfo.getHotelName()
					+ ", ȣ�ں귣�� : " + resInfo.getBossHotelNo()
					+ ", ȣ��Ÿ�� : " + resInfo.getTypeNo() + "\n"
					+ ", ȣ������ : " + resInfo.getCityNo()
					+ ", ȣ���ּ� : " + resInfo.getHotelAddress() + "\n"
					+ ", ȣ��Star : " + resInfo.getStar()
					+ ", ȣ�� �� ���� : " + resInfo.getRoomCnt()
					+ ", ȣ�� ���� �� : " + resInfo.getWorkerCnt() + "\n"
					+ ", ��ħ�Ļ� ���� : " + resInfo.getCanBreakfast()
					+ ", �������� ���� : " + resInfo.getCanSwimming()
					+ ", Wifi ���� : " + resInfo.getCanSwimming()
					+ ", �������� ���� : " + resInfo.getCanParking()
					+ ", �ֿϵ��� ���డ�� ���� : " + resInfo.getCanPet()
					+ ", �� ���� ���� : " + resInfo.getCanSmoke() + "\n"
					+ ", ���� ȣ�� ��ȣ : " + resInfo.getBossHotelNo());
			
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelListByCountry() {
		int countryID;
		HotelUICommon.menuStart("ȣ�� ���� �˻�(����)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� ���� �˻�(����)       ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �˻��ϰ��� �ϴ� ȣ���� �����ȣ �Է� : ");
		countryID = sc.nextInt();
		
		List<HotelInfo> resList = HotelAdminBasicUI.getHotelListDAO().getHotelListByCountry(countryID);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
			System.out.println("��ȣ : " + resList.get(i).getHotelNo()
					+ ", ȣ���̸� : " + resList.get(i).getHotelName()
					+ ", ȣ�ں귣�� : " + resList.get(i).getBossHotelNo()
					+ ", ȣ��Ÿ�� : " + resList.get(i).getTypeNo() + "\n"
					+ ", ȣ������ : " + resList.get(i).getCityNo()
					+ ", ȣ���ּ� : " + resList.get(i).getHotelAddress() + "\n"
					+ ", ȣ��Star : " + resList.get(i).getStar()
					+ ", ȣ�� �� ���� : " + resList.get(i).getRoomCnt()
					+ ", ȣ�� ���� �� : " + resList.get(i).getWorkerCnt() + "\n"
					+ ", ��ħ�Ļ� ���� : " + resList.get(i).getCanBreakfast()
					+ ", �������� ���� : " + resList.get(i).getCanSwimming()
					+ ", Wifi ���� : " + resList.get(i).getCanSwimming()
					+ ", �������� ���� : " + resList.get(i).getCanParking()
					+ ", �ֿϵ��� ���డ�� ���� : " + resList.get(i).getCanPet()
					+ ", �� ���� ���� : " + resList.get(i).getCanSmoke() + "\n"
					+ ", ���� ȣ�� ��ȣ : " + resList.get(i).getBossHotelNo());
			}
			
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void searchHotelListByCity() {
		int cityID;
		HotelUICommon.menuStart("ȣ�� ���� �˻�(����)");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��        ȣ�� ���� �˻�(����)       ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �˻��ϰ��� �ϴ� ȣ���� ���ù�ȣ �Է� : ");
		cityID = sc.nextInt();
		
		List<HotelInfo> resList = HotelAdminBasicUI.getHotelListDAO().getHotelListByCity(cityID);
		
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("��ȣ : " + resList.get(i).getHotelNo()
						+ ", ȣ���̸� : " + resList.get(i).getHotelName()
						+ ", ȣ�ں귣�� : " + resList.get(i).getBossHotelNo()
						+ ", ȣ��Ÿ�� : " + resList.get(i).getTypeNo() + "\n"
						+ ", ȣ������ : " + resList.get(i).getCityNo()
						+ ", ȣ���ּ� : " + resList.get(i).getHotelAddress() + "\n"
						+ ", ȣ��Star : " + resList.get(i).getStar()
						+ ", ȣ�� �� ���� : " + resList.get(i).getRoomCnt()
						+ ", ȣ�� ���� �� : " + resList.get(i).getWorkerCnt() + "\n"
						+ ", ��ħ�Ļ� ���� : " + resList.get(i).getCanBreakfast()
						+ ", �������� ���� : " + resList.get(i).getCanSwimming()
						+ ", Wifi ���� : " + resList.get(i).getCanSwimming()
						+ ", �������� ���� : " + resList.get(i).getCanParking()
						+ ", �ֿϵ��� ���డ�� ���� : " + resList.get(i).getCanPet()
						+ ", �� ���� ���� : " + resList.get(i).getCanSmoke() + "\n"
						+ ", ���� ȣ�� ��ȣ : " + resList.get(i).getBossHotelNo());
			}
			
			System.out.println("\n   ���������� �˻��Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   �˻��� �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void deleteHotelListByID() {
		int hotelNo;
		HotelUICommon.menuStart("ȣ�� ���� ����");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� ���� ����                      ��");
		System.out.println("��������������������������������������������������������������\n");

		sc = new Scanner(System.in);
		System.out.print("   �����ϰ��� �ϴ� ȣ�� ��ȣ �Է� : ");
		hotelNo = sc.nextInt();
		
		if (HotelAdminBasicUI.getHotelListDAO().deleteHotelListByID(hotelNo)) {
			System.out.println("\n   ���������� �����Ǿ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ������ �����Ͽ����ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
	
	public void printAllHotelList() {
		HotelUICommon.menuStart("ȣ�� ��ü ���");
		
		System.out.println("��������������������������������������������������������������");
		System.out.println("��          ȣ�� ��ü ���                      ��");
		System.out.println("��������������������������������������������������������������\n");
		
		List<HotelInfo> resList = HotelAdminBasicUI.getHotelListDAO().getAllHotelList();
		if (resList.size() > 0) {
			System.out.println();
			for (int i=0; i<resList.size(); i++) {
				System.out.println("��ȣ : " + resList.get(i).getHotelNo()
						+ ", ȣ���̸� : " + resList.get(i).getHotelName()
						+ ", ȣ�ں귣�� : " + resList.get(i).getBrandNo()
						+ ", ȣ��Ÿ�� : " + resList.get(i).getTypeNo() + "\n"
						+ "\t, ȣ������ : " + resList.get(i).getCityNo()
						+ ", ȣ���ּ� : " + resList.get(i).getHotelAddress() + "\n"
						+ "\t, ȣ��Star : " + resList.get(i).getStar()
						+ ", ȣ�� �� ���� : " + resList.get(i).getRoomCnt()
						+ ", ȣ�� ���� �� : " + resList.get(i).getWorkerCnt() + "\n"
						+ "\t, ��ħ�Ļ� ���� : " + resList.get(i).getCanBreakfast()
						+ ", �������� ���� : " + resList.get(i).getCanSwimming()
						+ ", Wifi ���� : " + resList.get(i).getCanSwimming()
						+ ", �������� ���� : " + resList.get(i).getCanParking()
						+ ", �ֿϵ��� ���డ�� ���� : " + resList.get(i).getCanPet()
						+ ", �� ���� ���� : " + resList.get(i).getCanSmoke() + "\n"
						+ "\t, ���� ȣ�� ��ȣ : " + resList.get(i).getBossHotelNo());
			}
			System.out.println("\n   ���������� ��µǾ����ϴ�. (^^)\n");
		} else {
			System.out.println("\n   ����Ʈ�� �������� �ʽ��ϴ�.\n");
		}
		
		sc = new Scanner(System.in);
		HotelUICommon.menuEnd(sc);
	}
}
