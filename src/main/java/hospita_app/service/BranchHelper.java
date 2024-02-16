package hospita_app.service;

import java.util.Scanner;

import hospita_app_bi.dao.BranchDao;
import hospita_app_bi.dao.HospitalDao;
import hospita_app_bi.dto.Address;
import hospita_app_bi.dto.Branch;
import hospita_app_bi.dto.Hospital;

public class BranchHelper {
	
	private static Scanner scanner = new Scanner(System.in);
	private static HospitalDao hospitalDao = new HospitalDao();
	private static BranchDao branchDao = new BranchDao();
	
	
	public static  Branch createBranch(Hospital hospital) {
		
		Branch branch = new Branch();
		
		System.out.println("ENTER THE BRANCH NAME: ");
		branch.setBranchName(scanner.nextLine());
		
		System.out.println("ENTER THE BRANCH HEAD: ");
		branch.setBranchHead(scanner.nextLine());
		
		System.out.println("IS ICU AVAILABLE? YES/NO");
		branch.setIsICUAvailable(scanner.nextLine());
		
		Address address = AddressHelper.createAddress(branch);
		if(address != null) {
			
			branch.setAddress(address);
			
		}
		
		if(hospital != null) {
			branch.setHospital(hospital);
		}
		
		return branch;
		
	}
	
	public static void addNewBranch() {
		
		System.out.println("ENTER THE HOSPITAL ID YOU WANT TO CREATE BRANCH: ");
		int hospitalId = scanner.nextInt();
		scanner.nextLine();

		Hospital hospital = hospitalDao.findHospital(hospitalId);

		if (hospital == null) {

			System.out.println("SORRY NO HOSPITAL FOUND WITH THAT ID!");
			System.out.println("DO YOU WISH TO SAVE THE HOSPITAL?");
			System.out.println("1. YES CONTINUE");
			System.out.println("2. NO");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				hospital = HospitalHelper.createHospital();
				return;

			case 2:
				return;
			default:
				System.out.println("WRONG INPUT CHOICE. RETURNING BACK!!");
				return;
			}

		}
		
		Branch createdBranch = createBranch(hospital);
		
		Hospital updatedHospitalWithBranch = hospitalDao.updateBranchList(hospitalId, createdBranch);
		if(updatedHospitalWithBranch != null) {
			
			System.out.println("NEW BRANCH ADDED SUCCESSFULLY FOR "+hospital.getHospitalName()+"!");
			return;
		}
		
		return;
		
	}
	
	public static void close() {
		scanner.close();
	}

	public static boolean deleteBranch() {
		
		System.out.println("ENTER THE BRANCH ID: ");
		int branchId = scanner.nextInt();
		scanner.nextLine();
		
		Branch branch = branchDao.findBranch(branchId);
		
		if(branch != null) {
			
			boolean deletedBranch = branchDao.deleteBranch(branch);
			if(deletedBranch) {
				
				System.out.println("BRANCH "+ branch.getBranchName()+ " DELETED SUCCESSFULLY!");
				return true;
			}
			
		}else {
			System.out.println("NO BRANCH FOUND WITH THIS ID!");
		}
		
		return false;
		
	}

	public static void findBranch() {

		System.out.println("ENTER THE BRANCH ID: ");
		int branchId = scanner.nextInt();
		scanner.nextLine();
		
		Branch branch = branchDao.findBranch(branchId);
		
		if(branch != null) {
			
			System.out.println("\n\n*********** BRANCH DETAILS *************\n");
			System.out.println("BRANCH NAME: "+branch.getBranchName());
			System.out.println("BRANCH HOSPITAL: "+branch.getHospital().getHospitalName());
			System.out.println("BRANCH HEAD: "+ branch.getBranchHead());
			System.out.println("BRANCH ADDRESS: \n"+ branch.getAddress());
			System.out.println("\n**********************************************\n");
			
		}else {
			
			System.out.println("NO BRANCH FOUND WITH THIS ID.");
			
		}
	}

}
