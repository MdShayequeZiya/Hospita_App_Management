package hospita_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hospita_app_bi.dao.EncounterDao;
import hospita_app_bi.dao.MedOrderDao;
import hospita_app_bi.dto.Encounter;
import hospita_app_bi.dto.Item;
import hospita_app_bi.dto.MedOrder;

public class MedOrderHelper {
	private static Scanner scanner = new Scanner(System.in);
	
	private static EncounterDao encounterDao = new EncounterDao();
	private static MedOrderDao medOrderDao = new MedOrderDao();

	public static MedOrder createMedOrders() {
		
		
		System.out.println("ENTER THE ENCOUNTER ID: ");
		int encounterId = scanner.nextInt();
		scanner.nextLine();

		Encounter encounter = encounterDao.findEncounter(encounterId);
		
		if (encounter == null) {

			System.out.println("NO ENCOUNTER FOUND WITH THIS ID!");
			System.out.println("DO YOU WISH TO ADD A NEW ENCOUNTER ?");
			System.out.println("1. YES");
			System.out.println("2. NO");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				encounter = EncounterHelper.createEncounter();
				break;
			case 2:
				System.out.println("TRY WITH A VALID ENCOUNTER ID THEN!");
				System.out.println("RETURNING... BACK TO MAIN MENU!");
				return null;
			default:
				System.out.println("INVALID CHOICE!!");
				System.out.println("RETURNING... BACK TO MAIN MENU!");
				return null;
			}

		}
		
		MedOrder medOrder = new MedOrder();
		
		System.out.println("INVOICE NUMBER: ");
		medOrder.setInvoiceNumber(scanner.nextLine());
		
		System.out.println("ENTER THE QUANTITY: ");
		int quantity = scanner.nextInt();
		scanner.nextLine();
		
		List<Item> items = new ArrayList<Item>();
		for(int i =0; i<quantity; i++) {
			
			Item addedItem = ItemHelper.addItem(medOrder);
			if(addedItem != null) {
				items.add(addedItem);
			}
			
		}
		
		medOrder.setItems(items);
		
		System.out.println("SELECT THE MODE OF PAYMENT: ");
		medOrder.setPaymentMode(scanner.nextLine());
		
		medOrder.setQuantity(items.size());
		medOrder.setEncounter(encounter);
		//System.out.println("hi");
		List<MedOrder> medOrders = encounter.getMedOrders();
		medOrders.add(medOrder);
		

		Encounter updatedEncounter = encounterDao.updateEncounter(encounter);
		if(updatedEncounter != null) {
			
			System.out.println("MEDORDER SAVED SUCCESSFULLY!");
			return medOrder;
			
		}
		
		return null;
		
	}
	
	public static boolean deleteMedOrder() {
		
		System.out.println("ENTER THE MEDORDER ID YOU WANT TO DELETE: ");
		int medOrderId = scanner.nextInt();
		scanner.nextLine();
		
		MedOrder medOrder = medOrderDao.findMedOrder(medOrderId);
		if(medOrder != null) {
			
			boolean deletedMedOrder = medOrderDao.deleteMedOder(medOrder);
			System.out.println("MEDORDER DELETED SUCCESSFULLY!");
			return deletedMedOrder;
		}
		
		return false;
		
	}

	public static void findMedOrder() {

		System.out.println("ENTER THE MEDORDER ID: ");
		int medOrderId = scanner.nextInt();
		scanner.nextLine();
		
		MedOrder order = medOrderDao.findMedOrder(medOrderId);
		
		if(order != null) {
			
			System.out.println("\n\n*********** MEDORDER DETAILS *************\n");
			System.out.println("INVOICE NUMBER: "+order.getInvoiceNumber());
			System.out.println("ORDER DATE AND TIME: "+order.getOrderDateTime());
			System.out.println("VISITED DOCTOR: "+order.getEncounter().getVisitedDoctor());
			System.out.println("QUANTITY IN THE ITEMS LIST: "+ order.getQuantity());
			System.out.println("\n**********************************************\n");
			
		}else {
			System.out.println("NO MEDORDER FOUND WITH THIS ID!");
		}
	}
}
