package hospita_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hospita_app_bi.dao.BranchDao;
import hospita_app_bi.dao.EncounterDao;
//import hospita_app_bi.dao.EncounterDao;
import hospita_app_bi.dao.PersondDao;
import hospita_app_bi.dto.Branch;
import hospita_app_bi.dto.Encounter;
import hospita_app_bi.dto.Person;

public class EncounterHelper {

	private static Scanner scanner = new Scanner(System.in);
	private static BranchDao branchDao = new BranchDao();
	private static PersondDao personDao = new PersondDao();
	private static EncounterDao encounterDao = new EncounterDao();

	public static Encounter createEncounter() {

		System.out.println("ENTER THE PERSON ID: ");
		int personId = scanner.nextInt();
		scanner.nextLine();

		System.out.println("ENTER THE BRANCH ID: ");
		int branchId = scanner.nextInt();
		scanner.nextLine();

		Branch branch = branchDao.findBranch(branchId);
		Person person = personDao.findPerson(personId);

		if (branch == null) {
			System.out.println("SORRY NO BRANCH FOUND WITH THIS ID!");
			System.out.println("RETURNING... ON MAIN MENU");
			return null;
		} else if (person == null) {
			System.out.println("INVALID PERSON ID");
			System.out.println("NEW PERSON?");
			System.out.println("1. YES \n2. NO");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				person = createPerson(new ArrayList<Encounter>());
				break;
			case 2:
				System.out.println("PLEASE TRY AGAIN WITH VALID PERSON ID!");
				return null;
			default:
				System.out.println("INVALID CHOICE!");
				System.out.println("RETURNING... BACK TO THE MAIN MENU!");
				break;
			}
		}
		
		List<Encounter> previousEncounters = person.getEncounters();
		
		Encounter encounter = new Encounter();
		
		System.out.println("ENTER THE SYMPTOM: ");
		encounter.setSymptom(scanner.nextLine());
		
		System.out.println("ENTER THE VISITED DOCTER: ");
		encounter.setVisitedDoctor(scanner.nextLine());
		
		encounter.setPerson(person);
		
		encounter.setBranch(branch);
		previousEncounters.add(encounter);
		person.setEncounters(previousEncounters);
		
		Person savedPerson = personDao.savePerson(person);
		if(savedPerson != null) {
			
			System.out.println("PERSON AND ENCOUNTER SAVED SUCCESSFULLY!");
			
		}
		
		return encounter;

	}

	public static Person createPerson(List<Encounter> encounters) {

		Person person = new Person();

		System.out.println("ENTER THE PERSON NAME: ");
		person.setPersonName(scanner.nextLine());

		System.out.println("ENTER THE PERSON AGE: ");
		person.setPersonAge(scanner.nextInt());
		scanner.nextLine();

		System.out.println("ENTER THE PERSON BLOOD GROUP: ");
		person.setBloodGroup(scanner.nextLine());

		System.out.println("ENTER THE PERSON WEIGHT: ");
		person.setWeight(scanner.nextDouble());
		scanner.nextLine();
		
		person.setEncounters(encounters);

		return person;

	}
	
	public static boolean deleteEncounter() {
		
		System.out.println("ENTER THE ENCOUNTER ID TO DELETE: ");
		int encounterId = scanner.nextInt();
		scanner.nextLine();
		
		Encounter encounter = encounterDao.findEncounter(encounterId);
		if(encounter != null) {
			
			boolean deletedEncounter = encounterDao.deleteEncounter(encounter);
			System.out.println("ENCOUNTER DELETED SUCCESSFULLY!");
			return deletedEncounter;
			
		}
		return false;
	}

	public static void findEncounter() {

		System.out.println("ENTER THE ENCOUNTER ID TO SEARCH: ");
		
		int encounterId = scanner.nextInt();
		scanner.nextLine();
		
		Encounter encounter = encounterDao.findEncounter(encounterId);
		
		if(encounter != null) {
			System.out.println("\n\n*********** ENCOUNTER DETAILS *************\n");
			System.out.println("ENCOUNTER BRANCH: "+encounter.getBranch().getBranchName());
			System.out.println("ENCOUNTER PERSON: "+encounter.getPerson().getPersonName());
			System.out.println("SYMPTOM: "+encounter.getSymptom());
			System.out.println("VISITED DOCTOR: "+ encounter.getVisitedDoctor());
			System.out.println("\n**********************************************\n");
			
		}else {
			System.out.println("NO ENCOUNTER FOUND WITH THIS ID!");
		}
		
	}

}
