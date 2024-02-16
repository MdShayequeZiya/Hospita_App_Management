package hospita_app.service;

import java.util.Scanner;

import hospita_app_bi.dao.ItemDao;
import hospita_app_bi.dto.Item;
import hospita_app_bi.dto.MedOrder;

public class ItemHelper {
	
	private static Scanner scanner = new Scanner(System.in);
	private static ItemDao itemDao = new ItemDao();
	
	public static Item addItem(MedOrder medOrder) {
		
		System.out.println("1. FETCH FROM EXISTING ITEM REPOSITORY"
				+ "\n2. CREATING A NEW ITEM");
		
		int choice = scanner.nextInt();
		scanner.nextLine();
		Item item = null;
		switch (choice) {
		case 1:
			System.out.println("ENTER THE ITEM ID:");
			int itemId = scanner.nextInt();
			scanner.nextLine();
			
			item = itemDao.findItem(itemId);
			System.out.println(item.getItemName());
			break;
		case 2:
			item = createItem();
		default:
			System.out.println("INVALID OPTION!");
			break;
		}
		
		return item;
	}
	
	public static Item createItem() {
		
		Item item = new Item();
		System.out.println("ENTER THE ITEM NAME: ");
		item.setItemName(scanner.nextLine());
		
		System.out.println("ENTER THE ITEM PRICE: ");
		item.setPrice(scanner.nextDouble());
		scanner.nextLine();
		
		System.out.println("ENTER THE ITEM EXPIRY DATE: ");
		item.setExpiryDate(scanner.nextLine());
		
		System.out.println("ENTER THE ITEM TYPE: ");
		item.setItemType(scanner.nextLine());
		
//		item.setMedOrders(new ArrayList<MedOrder>());
		
		return item;
	}

}
