package hospita_app.service;

import java.util.Arrays;
import java.util.Scanner;

import hospita_app_bi.dao.HospitalDao;
import hospita_app_bi.dto.Branch;
import hospita_app_bi.dto.Hospital;

public class HospitalHelper {
	
	private static Scanner scanner = new Scanner(System.in);
	private static HospitalDao hosptialDao = new HospitalDao();
	
	
	public static Hospital createHospital() {
		
		Hospital hospital = new Hospital();
		
		System.out.println("ENTER THE HOSPITAL NAME: ");
		hospital.setHospitalName(scanner.nextLine());
		
		System.out.println("ENTER THE HOSPITAL FOUNDER NAME: ");
		hospital.setFounderName(scanner.nextLine());
		
		System.out.println("ENTER THE HOSPITAL YEAR FOUND: ");
		hospital.setYearFound(scanner.nextLine());
		
		
		// calling the branchhelper to create a new branch
		
		Branch branch = BranchHelper.createBranch(hospital);
		
		hospital.setBranches(Arrays.asList(branch));
		
		
		hospital = hosptialDao.saveHospital(hospital);
		if(hospital != null) {
			
			System.out.println("NEW HOSPITAL AND BRANCH CREATED SUCCESSFULLY!");
			return hospital;
			
		}
		
		return null;
		
	}
	
	
	public static boolean deleteHospital() {
		
		System.out.println("ENTER THE HOSPITAL ID TO DELETE: ");
		int hospitalId = scanner.nextInt();
		
		 boolean deletedHospital = hosptialDao.deleteHospital(hospitalId);
		 
		 if(deletedHospital) {
			 System.out.println("HOSPITAL DELETED SUCCESSFULLY!");
		 }
		 
		 return deletedHospital;
	}
	
	
	
	public static void close() {
		scanner.close();
	}


	public static void findHospital() {

		System.out.println("ENTER THE HOSPITAL ID: ");
		int hospitalId = scanner.nextInt();
		scanner.nextLine();
		
		Hospital hospital = hosptialDao.findHospital(hospitalId);
		
		if(hospital != null) {
			
			System.out.println("\n\n*********** HOSPITAL DETAILS *************\n");
			System.out.println("HOSPITAL NAME: "+hospital.getHospitalName());
			System.out.println("HOSPITAL FOUNDER NAME: "+ hospital.getFounderName());
			System.out.println("HOSPTIAL YEAR FOUND: "+ hospital.getYearFound());
			System.out.println("\n**********************************************\n");
			
		}else {
			
			System.out.println("NO HOSPITAL FOUND WITH THIS ID.");
			
		}
	}

}

	