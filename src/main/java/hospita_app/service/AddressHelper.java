package hospita_app.service;

import java.util.Scanner;

import hospita_app_bi.dto.Address;
import hospita_app_bi.dto.Branch;

public class AddressHelper {
	
	private static Scanner scanner = new Scanner(System.in);
	

	public static Address createAddress(Branch branch) {
		
		Address address = new Address();
		System.out.println("ENTER THE BLOCK OF THE BRANCH: ");
		address.setBlock(scanner.nextLine());
		
		System.out.println("ENTER THE BRANCH CITY: ");
		address.setCity(scanner.nextLine());
		
		System.out.println("ENTER THE BRANCH STATE: ");
		address.setState(scanner.nextLine());
		
		System.out.println("ENTER THE BRANCH PINCODE: ");
		address.setPincode(scanner.nextLine());
		
		address.setBranch(branch);
		
		return address;
	}
	
	public static void close() {
		scanner.close();
	}

}
