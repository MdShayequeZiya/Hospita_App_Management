package hospita_app_bi.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hospita_app_bi.dto.Branch;
import hospita_app_bi.dto.Encounter;

public class BranchDao {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital2");
	private EntityManager manager = factory.createEntityManager();
	private EntityTransaction transaction = manager.getTransaction();

	public Branch findBranch(int branchId) {
		return manager.find(Branch.class, branchId);
	}

	public boolean deleteBranch(Branch branch) {

		transaction.begin();
		manager.remove(branch);
		transaction.commit();

		return true;
	}

	public Branch updateEncounterList(int branchId, Encounter encounter) {

		Branch branch = findBranch(branchId);

		List<Encounter> encounters = branch.getEncounters();

		if (encounters == null) {
			encounters = new ArrayList<Encounter>(Arrays.asList(encounter));
		} else if (!encounters.contains(encounter)) {
			encounters.add(encounter);
		} else {
			return null;
		}

		branch.setEncounters(encounters);

		transaction.begin();

		manager.merge(branch);

		transaction.commit();

		return branch;

	}
}
