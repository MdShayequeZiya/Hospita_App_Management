package hospita_app_bi.controller;

import java.util.Scanner;

import hospita_app.service.BranchHelper;
import hospita_app.service.EncounterHelper;
import hospita_app.service.HospitalHelper;
import hospita_app.service.MedOrderHelper;

public class HosptialController {
	
	private static boolean flag = true;
	
	public static void main(String[] args) {
		
		System.out.println("WELCOME TO THE HOSPITAL MANAGEMENT SYSTEM!");
		Scanner scanner = new Scanner(System.in);
		
		while(flag) {
			
			System.out.println("\n======= SAVE AND DELETE OPERATIONS ======\n"
					+ "\n1. SAVE A HOSPITAL"
					+ "\n2. DELETE A HOSPITAL"
					+ "\n3. ADD A NEW BRANCH TO EXISTING HOSPITAL"
					+ "\n4. DELETE THE BRANCH"
					+ "\n5. CREATE AN ENCOUNTER"
					+ "\n6. DELETE AN ENCOUNTER"
					+ "\n7. CREATE A MEDORDER"
					+ "\n8. DELETE A MEDORDER\n"
					+ "\n======= FIND OPERATIONS =========\n"
					+ "\n9. FIND HOSPITAL BY ID"
					+ "\n10. FIND BRANCH BY ID"
					+ "\n11. FIND ENCOUNTER BY ID"
					+ "\n12. FIND MEDORDER BY ID"
					+ "\n======= UPDATE OPERARIONS ========\n"
					+ "\n13. UPDATE HOSPITAL"
					+ "\n14. UPDATE BRANCH OF A HOSPTIAL"
					+ "\n15. UPDATE ENCOUNTER"
					+ "\n16. UPDATE MEDORDER"
					+ "\n======== EXITING APPLICATION OPTION ====="
					+ "\n17. EXIT"
					+ "\n\nPLEASE CHOOSE AN OPTION FROM ABOVE: - ");
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
			case 1:
				HospitalHelper.createHospital();
				break;
			case 2: 
				HospitalHelper.deleteHospital();
				break;
			case 3:
				BranchHelper.addNewBranch();
				break;
			case 4: 
				BranchHelper.deleteBranch();
				break;
			case 5:
				EncounterHelper.createEncounter();
				break;
			case 6:
				EncounterHelper.deleteEncounter();
				break;
			case 7:
				MedOrderHelper.createMedOrders();
				break;
			case 8:
				MedOrderHelper.deleteMedOrder();
				break;
			case 9:
				HospitalHelper.findHospital();
				break;
			case 10: 
				BranchHelper.findBranch();
				break;
			case 11:
				EncounterHelper.findEncounter();
				break;
			case 12: 
				MedOrderHelper.findMedOrder();
				break;
			case 17:
				flag = false;
				System.out.println("THANK YOU FOR YOUR TIME!");
				HospitalHelper.close();
				break;
			default:
				System.out.println("WRONG INPUT. TRY AGAIN!");
				break;
			}		
			
		}
		
		scanner.close();
		
	}

}
